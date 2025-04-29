<%@ page import="gov.armamentis.model.UserModel" %>
<%@ page session="true" %>
<%
  UserModel u = (UserModel)session.getAttribute("user");
  if(u == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  String role = u.getRole(); // Assuming UserModel has getRole()
%>
<html>
  <body>
    <h1>Welcome, <%= u.getName() %>!</h1>

    <nav>
      <a href="weapons.jsp">Weapons</a> |
      <a href="inventory.jsp">Inventory</a> |
      <a href="maintenance.jsp">Maintenance</a> |
      <a href="profile.jsp">Profile</a>
      <% if ("admin".equalsIgnoreCase(role)) { %>
        | <a href="admin_panel.jsp">Admin Panel</a>
        | <a href="user_management.jsp">User Management</a>
      <% } %>
    </nav>

    <p>You are logged in as: <%= role %></p>
  </body>
</html>
