<%@ page import="java.util.List, gov.armamentis.dao.WeaponDAO, gov.armamentis.dao.WeaponTypeDAO, gov.armamentis.model.WeaponModel, gov.armamentis.model.WeaponTypeModel" %>
<%@ page session="true" %>

<%
    if(session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Optional role check (only admin allowed)
    gov.armamentis.model.UserModel user = (gov.armamentis.model.UserModel) session.getAttribute("user");
    if(!"admin".equalsIgnoreCase(user.getRole())) {
        response.getWriter().println("Access denied.");
        return;
    }

    List<WeaponModel> weapons = new WeaponDAO().all();
    WeaponTypeDAO typeDAO = new WeaponTypeDAO();
%>

<html>
<head>
<title>Weapons Management</title>
<style>
  body { font-family: sans-serif; margin: 20px; }
  table { border-collapse: collapse; width: 100%; }
  th, td { padding: 8px 12px; border: 1px solid #ccc; }
  th { background-color: #f2f2f2; }
  a.button { text-decoration: none; padding: 6px 10px; border: 1px solid #333; border-radius: 4px; background-color: #ddd; }
</style>
</head>
<body>

<h2>Weapons Management 
  <a href="weaponForm.jsp" class="button">+ Add Weapon</a>
</h2>

<table>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Type</th>
    <th>Actions</th>
</tr>

<%
    for (WeaponModel w : weapons) {
        WeaponTypeModel type = typeDAO.findById(w.getTypeID());
%>
<tr>
    <td><%= w.getWeaponID() %></td>
    <td><%= w.getName() %></td>
    <td><%= (type != null) ? type.getTypeName() : "Unknown" %></td>
    <td>
        <a href="weaponForm.jsp?id=<%= w.getWeaponID() %>" class="button">Edit</a>
        <a href="weapon?op=delete&id=<%= w.getWeaponID() %>" class="button"
           onclick="return confirm('Delete this weapon?');">Delete</a>
    </td>
</tr>
<%
    }
%>

</table>

</body>
</html>
