<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="gov.armamentis.model.UserModel" %>
<%
  UserModel u = (UserModel) session.getAttribute("user");

  if (u == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile - Armamentis</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/profile.css">
</head>
<body>
  <div class="profile-container">
    <h1>Profile</h1>
    <div class="profile-info">
      <p><strong>Name:</strong> <%= u.getName() %></p>
      <p><strong>Email:</strong> <%= u.getEmail() %></p>
      <p><strong>Role:</strong> <%= u.getRole() %></p>
    </div>
<div class="profile-actions">
  <a href="profileEdit.jsp" class="edit-button">Edit Profile</a>
  <a href="dashboard.jsp" class="dashboard-button">Dashboard</a>
</div>

  </div>
</body>
</html>
