<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="gov.armamentis.model.UnitModel" %>
<%@ page import="java.util.List" %>
<%
    List<UnitModel> unitList = (List<UnitModel>) request.getAttribute("unitList");
%>
<html>
<head>
    <title>Manage Units</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/unit.css">
</head>
<body>
<h1>Unit Management (Admin Only)</h1>

<!-- Add Unit Form -->
<form method="post" action="<%= request.getContextPath() %>/unit">
    <input type="hidden" name="action" value="add" />
    Name: <input type="text" name="name" required />
    <button type="submit">Add Unit</button>
</form>

<!-- List of Units -->
<table border="1">
    <tr>
        <th>Unit ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    <% 
        if (unitList != null) {
            for (UnitModel unit : unitList) { 
    %>
    <tr>
        <td><%= unit.getUnitID() %></td>
        <td><%= unit.getName() %></td>
        <td>
            <!-- Edit Form -->
            <form method="post" action="<%= request.getContextPath() %>/unit" style="display:inline;">
                <input type="hidden" name="action" value="edit" />
                <input type="hidden" name="unitID" value="<%= unit.getUnitID() %>" />
                <input type="text" name="name" value="<%= unit.getName() %>" required />
                <button type="submit">Update</button>
            </form>

            <!-- Delete Form -->
            <form method="post" action="<%= request.getContextPath() %>/unit" style="display:inline;" onsubmit="return confirm('Are you sure?');">
                <input type="hidden" name="action" value="delete" />
                <input type="hidden" name="unitID" value="<%= unit.getUnitID() %>" />
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <% 
            }
        } 
    %>
</table>

</body>
</html>
