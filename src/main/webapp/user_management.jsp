<%@ page import="java.util.List" %>
<%@ page import="gov.armamentis.model.UserModel" %>
<%@ page session="true" %>

<%
    // Session user check
    UserModel currentUser = (UserModel) session.getAttribute("user");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    if (!"admin".equalsIgnoreCase(currentUser.getRole())) {
        out.println("Access denied.");
        return;
    }

    // Get users list from request attribute (set by servlet)
    List<UserModel> users = (List<UserModel>) request.getAttribute("users");
    if (users == null) {
        // Redirect to servlet action to load users if accessed directly
        response.sendRedirect("UserServlet?action=listUsers");
        return;
    }

    String deleted = request.getParameter("deleted");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>User Management</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/management.css">

</head>
<body>
    <h1>User Management</h1>

    <% if ("true".equals(deleted)) { %>
        <div class="msg success">User deleted successfully.</div>
    <% } else if ("false".equals(deleted)) { %>
        <div class="msg error">Failed to delete user.</div>
    <% } %>

    <table>
        <thead>
            <tr>
                <th>UserID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <% for (UserModel user : users) { %>
            <tr>
                <td><%= user.getUserID() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getRole() %></td>
                <td>
                    <% if (user.getUserID() != currentUser.getUserID()) { %>
                        <a href="UserServlet?action=deleteUser&id=<%= user.getUserID() %>"
                           onclick="return confirm('Are you sure you want to delete user <%= user.getName() %>?');"
                           class="btn delete-btn">Delete</a>
                    <% } else { %>
                        <span class="info">You</span>
                    <% } %>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
