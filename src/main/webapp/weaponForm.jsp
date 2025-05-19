<%@ page import="java.util.*, gov.armamentis.dao.*, gov.armamentis.model.*" %>
<%@ page session="true" %>

<%
    // Check if user is logged in
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch the weapon types
    WeaponTypeDAO weaponTypeDAO = new WeaponTypeDAO();
    List<WeaponTypeModel> types = weaponTypeDAO.all();

    // Check if it's an edit operation
    String idStr = request.getParameter("id");
    WeaponModel weapon = null;
    if (idStr != null) {
        int id = Integer.parseInt(idStr);
        WeaponDAO weaponDAO = new WeaponDAO();
        weapon = weaponDAO.find(id);  // Find weapon by ID
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= weapon != null ? "Edit" : "Add" %> Weapon - Armamentis</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/weaponForm.css">
</head>
<body>
    <div class="weapon-form-container">
        <h1><%= weapon != null ? "Edit" : "Add" %> Weapon</h1>
        <form action="<%= request.getContextPath() %>/weapon" method="post" class="weapon-form">
            <input type="hidden" name="op" value="<%= weapon != null ? "update" : "add" %>"/>
            
            <% if (weapon != null) { %>
                <input type="hidden" name="weaponID" value="<%= weapon.getWeaponID() %>"/>
            <% } %>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= weapon != null ? weapon.getName() : "" %>" required/>

            <label for="typeID">Type:</label>
            <select id="typeID" name="typeID" required>
                <% for (WeaponTypeModel type : types) { %>
                    <option value="<%= type.getTypeID() %>" <%= (weapon != null && weapon.getTypeID() == type.getTypeID()) ? "selected" : "" %>>
                        <%= type.getTypeName() %>
                    </option>
                <% } %>
            </select>

            <div class="form-buttons">
                <button type="submit" class="btn save-btn">Save</button>
                <a href="weaponList.jsp" class="btn cancel-btn">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
