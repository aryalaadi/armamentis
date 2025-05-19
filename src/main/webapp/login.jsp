<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/login.css">
</head>
<body>
<div class="login-container">
    <h2 class="login-title">Domus Intra</h2>

    <% 
        String error = (String) request.getAttribute("error");
        if (error != null && !error.isEmpty()) {
    %>
        <div class="error-message"><%= error %></div>
    <% 
        } 
    %>

    <form class="login-form" action="user?action=login" method="post">
        <label>Email</label>
        <input type="email" name="email" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit">Sign In</button>
    </form>

    <div class="register-link">
        New to the empire? <a href="register.jsp">Register here</a>
    </div>
</div>
</body>
</html>