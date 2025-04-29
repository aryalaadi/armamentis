package gov.armamentis.servlet;

import gov.armamentis.model.UserModel;
import gov.armamentis.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/profile/update")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user from session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserModel user = (UserModel) session.getAttribute("user");

        // Get the new name from the request
        String newName = request.getParameter("name");

        // Validate the new name
        if (newName == null || newName.trim().isEmpty()) {
            response.getWriter().println("Name cannot be empty.");
            return;
        }

        // Update the user's name in the database
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE User SET name = ? WHERE userid = ?");
            stmt.setString(1, newName);
            stmt.setInt(2, user.getUserID());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                // Update session object with new name
                user.setName(newName);
                session.setAttribute("user", user);

                // Redirect back to profile page after successful update
                response.sendRedirect("/armamentis/profile.jsp");
            } else {
                response.getWriter().println("Profile update failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
