package gov.armamentis.servlet;

import gov.armamentis.dao.InventoryDAO;
import gov.armamentis.model.InventoryModel;
import gov.armamentis.model.UserModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final InventoryDAO dao = new InventoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        List<InventoryModel> list;
        try {
            list = dao.getAll();
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.setAttribute("inventoryList", list);
        req.getRequestDispatcher("inventory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                InventoryModel inv = new InventoryModel();
                inv.setUserID(user.getUserID()); // Assign current user by default
                inv.setQuantity(0);               // Default quantity
                // Other fields can stay null or defaults
                dao.insert(inv);

            } else if ("edit".equals(action)) {
                InventoryModel inv = new InventoryModel();
                inv.setInventoryID(parseInt(req.getParameter("inventoryID")));
                inv.setUserID(parseInt(req.getParameter("userID")));
                inv.setWeaponID(parseInt(req.getParameter("weaponID")));
                inv.setQuantity(parseInt(req.getParameter("quantity"), 0)); // default 0
                inv.setUnitID(parseInt(req.getParameter("unitID")));
                inv.setDateIssued(parseDate(req.getParameter("dateIssued")));
                inv.setDateReturned(parseDate(req.getParameter("dateReturned")));
                dao.update(inv);

            } else if ("delete".equals(action)) {
                Integer id = parseInt(req.getParameter("inventoryID"));
                if (id != null) {
                    dao.delete(id);
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/inventory");
    }

    // Overloaded parseInt with default value
    private Integer parseInt(String p) {
        return parseInt(p, null);
    }
    private Integer parseInt(String p, Integer defaultValue) {
        if (p == null || p.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(p);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private Date parseDate(String p) {
        return (p == null || p.isEmpty()) ? null : Date.valueOf(p);
    }
}
