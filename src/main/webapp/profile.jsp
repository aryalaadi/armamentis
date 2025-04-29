<%@ page import="gov.armamentis.model.UserModel" %>
<%
  // Check if the user is logged in
  UserModel u = (UserModel) session.getAttribute("user");
  
  if (u == null) {
    // If the user is not logged in, redirect to login page
    response.sendRedirect("login.jsp");
    return;
  }
%>

<html>
<body>
  <h2>Your Profile</h2>
  <p>Name: <%= u.getName() %></p>
  <p>Role: <%= u.getRole() %></p>
  <a href="profileEdit.jsp">Edit Profile</a>
</body>
</html>
