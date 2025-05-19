<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="gov.armamentis.model.UserModel" %>
<%@ page session="true" %>
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
    <meta charset="UTF-8">
    <title>Edit Profile - Armamentis</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/profileEdit.css">
</head>
<body>
  <div class="edit-container">
    <h1>Edit Profile</h1>
    <form action="profile/update" method="post" class="edit-form">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" value="<%= u.getName() %>" required>
      
      <button type="submit">Save Changes</button>
    </form>
  </div>
</body>
</html>
