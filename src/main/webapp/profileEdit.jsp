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
    <title>Edit Profile</title>
</head>
<body>

<h1>Edit Profile</h1>

<form action="profile/update" method="post">
    <label>Name:</label>
    <input type="text" name="name" value="<%= u.getName() %>" required><br><br>

    <button type="submit">Save Changes</button>
</form>

</body>
</html>
