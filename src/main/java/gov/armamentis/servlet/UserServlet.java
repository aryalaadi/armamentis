package gov.armamentis.servlet;

import gov.armamentis.model.UserModel;
import gov.armamentis.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            handleRegister(request, response);
        } else if ("login".equals(action)) {
            handleLogin(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String rawPassword = request.getParameter("password");
        String role = request.getParameter("role");

        String[] hashedPasswordData = hashPassword(rawPassword);
        String hashedPassword = hashedPasswordData[0]; // hashed password
        String salt = hashedPasswordData[1]; // the salt

        try (Connection conn = DBUtil.getConnection()) {
            // Ensure email is unique (a simple check)
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM User WHERE email = ?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                response.getWriter().println("Email already in use. Please choose another one.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (name, email, password, salt, role) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, salt);
            stmt.setString(5, role);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("login.jsp");
            } else {
                response.getWriter().println("Registration failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String rawPassword = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE email = ?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                String storedSalt = rs.getString("salt");

                // Hash the input password with the stored salt
                String inputHashedPassword = hashPassword(rawPassword, storedSalt);

                if (storedHashedPassword.equals(inputHashedPassword)) {
                    UserModel user = new UserModel(
                            rs.getInt("userid"),
                            rs.getString("name"),
                            rs.getString("email"),
                            storedHashedPassword,  // You could pass the hashed password if needed
                            rs.getString("role")
                    );
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    Cookie roleCookie = new Cookie("role", user.getRole());
                    roleCookie.setMaxAge(60 * 60); // 1 hour
                    response.addCookie(roleCookie);
                    // Role-based redirection
                    if ("admin".equalsIgnoreCase(user.getRole())) {
                        response.sendRedirect("dashboard.jsp");
                    } else {
                        response.sendRedirect("profile.jsp");
                    }
                } else {
                    request.setAttribute("error", "Invalid credentials");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private String[] hashPassword(String password) {
        // Generate a salt using SecureRandom
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);

        String salt = bytesToHex(saltBytes);
        String hashedPassword = hashPassword(password, salt);

        return new String[]{hashedPassword, salt};
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes()); // Add salt to the password hash
            byte[] hashed = md.digest(password.getBytes());
            return bytesToHex(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
