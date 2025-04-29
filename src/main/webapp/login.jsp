<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
  <h2>Login</h2>
  <c:if test="${not empty error}">
    <div style="color:red">${error}</div>
  </c:if>
  <form action="user?action=login" method="post">
    Email: <input type="email" name="email" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    <button type="submit">Login</button>
  </form>
</body>
</html>
