package gov.armamentis.servlet;

import gov.armamentis.dao.WeaponDAO;
import gov.armamentis.model.WeaponModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/weapon")
public class WeaponServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final WeaponDAO dao = new WeaponDAO();

    public WeaponServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session check (just like your user servlet should have — optional)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String op = request.getParameter("op");
        if ("add".equals(op)) {
            handleAdd(request, response);
        } else if ("update".equals(op)) {
            handleUpdate(request, response);
        } else if ("delete".equals(op)) {
            handleDelete(request, response);
        } else {
            response.sendRedirect("weapons.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Just redirect all GETs to POST logic
        doPost(request, response);
    }

    private void handleAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int typeID = Integer.parseInt(request.getParameter("typeID"));

        WeaponModel w = new WeaponModel();
        w.setName(name);
        w.setTypeID(typeID);

        dao.add(w);
        response.sendRedirect("weapons.jsp");
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int weaponID = Integer.parseInt(request.getParameter("weaponID"));
        String name = request.getParameter("name");
        int typeID = Integer.parseInt(request.getParameter("typeID"));

        WeaponModel w = new WeaponModel();
        w.setWeaponID(weaponID);
        w.setName(name);
        w.setTypeID(typeID);

        dao.update(w);
        response.sendRedirect("weapons.jsp");
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int weaponID = Integer.parseInt(request.getParameter("id"));
        dao.delete(weaponID);
        response.sendRedirect("weapons.jsp");
    }
}
