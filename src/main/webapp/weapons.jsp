<%@ page import="java.util.List, gov.armamentis.dao.WeaponDAO, gov.armamentis.dao.WeaponTypeDAO, gov.armamentis.model.WeaponModel, gov.armamentis.model.WeaponTypeModel" %>
<%@ page session="true" %>

<%
    if(session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    gov.armamentis.model.UserModel user = (gov.armamentis.model.UserModel) session.getAttribute("user");
    if(!"admin".equalsIgnoreCase(user.getRole())) {
        response.getWriter().println("Access denied.");
        return;
    }

    String searchQuery = request.getParameter("search");
    List<WeaponModel> weapons;
    
    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
        weapons = new WeaponDAO().searchByName(searchQuery.trim());
    } else {
        weapons = new WeaponDAO().all();
    }
    
    WeaponTypeDAO typeDAO = new WeaponTypeDAO();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Weapons Management - Armamentis</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/weapons.css">
    <style>
        .search-form {
            margin: 20px 0;
            display: flex;
            gap: 10px;
        }
        .search-form input {
            padding: 8px;
            width: 300px;
        }
    </style>
</head>
<body>
    <div class="weapons-container">
        <h1>Weapons Management</h1>
        <a href="weaponForm.jsp" class="btn add-btn">+ Add Weapon</a>
        
        <form method="get" action="weapons.jsp" class="search-form">
            <input type="text" name="search" placeholder="Search by weapon name..." 
                   value="<%= searchQuery != null ? searchQuery : "" %>">
            <button type="submit" class="btn search-btn">Search</button>
            <% if (searchQuery != null && !searchQuery.trim().isEmpty()) { %>
                <a href="weapons.jsp" class="btn clear-btn">Clear</a>
            <% } %>
        </form>
        
        <table class="weapons-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (weapons.isEmpty()) {
                %>
                <tr>
                    <td colspan="4">No weapons found</td>
                </tr>
                <%
                    } else {
                        for (WeaponModel w : weapons) {
                            WeaponTypeModel type = typeDAO.findById(w.getTypeID());
                %>
                <tr>
                    <td><%= w.getWeaponID() %></td>
                    <td><%= w.getName() %></td>
                    <td><%= (type != null) ? type.getTypeName() : "Unknown" %></td>
                    <td>
                        <a href="weaponForm.jsp?id=<%= w.getWeaponID() %>" class="btn edit-btn">Edit</a>
                        <a href="weapon?op=delete&id=<%= w.getWeaponID() %>" 
                           class="btn delete-btn"
                           onclick="return confirm('Delete this weapon?');">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>