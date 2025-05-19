<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/register.css">
</head>
<body>
<div class="register-container">
    <h2 class="register-title">Nova Civis</h2>

    <form class="register-form" action="user?action=register" method="post">
        <label>Name</label>
        <input type="text" name="name" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <label>Role</label>
        <select name="role" required>
            <option value="Admin">Admin</option>
            <option value="Quartermaster">Quartermaster</option>
            <option value="Soldier">Soldier</option>
        </select>

        <button type="submit">Register</button>
    </form>

    <div class="login-link">
        Already enlisted? <a href="login.jsp">Return to login</a>
    </div>
</div>
</body>
</html>
