<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
  <h2>Register</h2>
  <form action="user?action=register" method="post">
    Name: <input type="text" name="name" required/><br/>
    Email: <input type="email" name="email" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    Role:
    <select name="role" required>
      <option value="Admin">Admin</option>
      <option value="Quartermaster">Quartermaster</option>
      <option value="Soldier">Soldier</option>
    </select><br/>
    <button type="submit">Register</button>
  </form>
  <a href="login.jsp">Already have an account? Login here</a>
</body>
</html>
