<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="gov.armamentis.model.UserModel" %>
<%@ page session="true" %>
<%
  UserModel u = (UserModel)session.getAttribute("user");
  if(u == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  String role = u.getRole();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Armamentis Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/dashboard.css">
</head>
<body>
  <div class="dashboard-container">
    <header>
      <h1>Salve, <%= u.getName() %>!</h1>
      <p class="role-text">Role: <%= role %></p>
    </header>

    <nav class="nav-links">
      <a href="weapons.jsp">Weapons</a>
      <a href="inventory">Inventory</a>
      <a href="profile.jsp">Profile</a>
      <a href="contact.jsp">Contact Us</a>
      <% if ("admin".equalsIgnoreCase(role)) { %>
        <a href="user_management.jsp">User Management</a>
      <% } %>
    </nav>

    <section class="about-section">
      <h2>About Armamentis</h2>
      <p>
        <strong>Armamentis</strong> is the central command platform for managing the military armament of the Roman Republic.
        Designed for clarity, strength, and efficiency, it empowers administrators, quartermasters, and soldiers alike to maintain 
        strict oversight of national defense resources. Whether you're tracking weapons, managing inventory, or commanding from the 
        admin panel — Armamentis ensures order, precision, and honor in every task.
      </p>
    </section>
  </div>
</body>
</html>
