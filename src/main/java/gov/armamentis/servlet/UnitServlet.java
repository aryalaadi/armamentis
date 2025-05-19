package gov.armamentis.servlet;

import gov.armamentis.dao.UnitDAO;
import gov.armamentis.model.UnitModel;
import gov.armamentis.model.UserModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/unit")
public class UnitServlet extends HttpServlet {
    private final UnitDAO unitDAO = new UnitDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<UnitModel> unitList = unitDAO.getAll();
            request.setAttribute("unitList", unitList);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.getRequestDispatcher("unit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("add".equalsIgnoreCase(action)) {
                UnitModel unit = buildUnitFromRequest(request);
                unitDAO.insert(unit);
            } else if ("edit".equalsIgnoreCase(action)) {
                UnitModel unit = buildUnitFromRequest(request);
                unit.setUnitID(Integer.parseInt(request.getParameter("unitID")));
                unitDAO.update(unit);
            } else if ("delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("unitID"));
                unitDAO.delete(id);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect("unit");
    }

    private UnitModel buildUnitFromRequest(HttpServletRequest request) {
        UnitModel unit = new UnitModel();
        unit.setName(request.getParameter("name"));
        return unit;
    }
}
