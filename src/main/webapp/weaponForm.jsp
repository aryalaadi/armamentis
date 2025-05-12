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

<html>
<head><title><%= weapon != null ? "Edit" : "Add" %> Weapon</title></head>
<body>
<h2><%= weapon != null ? "Edit" : "Add" %> Weapon</h2>

<form action="/armamentis/weapon" method="post">
    <input type="hidden" name="op" value="<%= weapon != null ? "update" : "add" %>"/>
    
    <% if (weapon != null) { %>
        <input type="hidden" name="weaponID" value="<%= weapon.getWeaponID() %>"/>
    <% } %>

    Name: <input type="text" name="name" value="<%= weapon != null ? weapon.getName() : "" %>" required/><br/><br/>

    Type: 
    <select name="typeID" required>
        <% 
            for (WeaponTypeModel type : types) {
        %>
            <option value="<%= type.getTypeID() %>" <%= (weapon != null && weapon.getTypeID() == type.getTypeID()) ? "selected" : "" %>>
                <%= type.getTypeName() %>
            </option>
        <% 
            }
        %>
    </select><br/><br/>

    <button type="submit">Save</button>
    <a href="weaponList.jsp">Cancel</a>
</form>

</body>
</html>
