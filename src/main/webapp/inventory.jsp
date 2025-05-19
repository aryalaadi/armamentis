<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="gov.armamentis.model.InventoryModel" %>
<%@ page import="gov.armamentis.model.UserModel" %>

<%
    UserModel user = (UserModel)session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); return;
    }
    List<InventoryModel> list = (List<InventoryModel>)request.getAttribute("inventoryList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory - Armamentis</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/inventory.css">
</head>
<body>
  <h1>Inventory</h1>

  <form method="post" action="inventory">
    <input type="hidden" name="action" value="add"/>
    <button type="submit">Add Blank Inventory</button>
  </form>
  <br>

  <table>
    <thead>
      <tr>
        <th>ID</th><th>UserID</th><th>WeaponID</th><th>Qty</th>
        <th>UnitID</th><th>Issued</th><th>Returned</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <%
        for (InventoryModel inv : list) {
      %>
      <tr>
        <td><%=inv.getInventoryID()%></td>
        <td><%=inv.getUserID()%></td>
        <td><%=inv.getWeaponID()%></td>
        <td><%=inv.getQuantity()%></td>
        <td><%=inv.getUnitID()%></td>
        <td><%=inv.getDateIssued()%></td>
        <td><%=inv.getDateReturned()%></td>
        <td>
          <form method="post" action="inventory" style="display:inline">
            <input type="hidden" name="action" value="delete"/>
            <input type="hidden" name="inventoryID" value="<%=inv.getInventoryID()%>"/>
            <button type="submit">Del</button>
          </form>
          
          <!-- EDIT BUTTON: changed to GET to show form -->
          <form method="get" action="inventory" style="display:inline">
            <input type="hidden" name="action" value="edit"/>
            <input type="hidden" name="inventoryID" value="<%=inv.getInventoryID()%>"/>
            <button type="submit">Edit</button>
          </form>
        </td>
      </tr>
      <%
        }
      %>
    </tbody>
  </table>

  <%
    String act = request.getParameter("action");
    String id  = request.getParameter("inventoryID");
    if ("edit".equals(act) && id != null) {
      InventoryModel e = null;
      for (InventoryModel inv : list) {
          if (inv.getInventoryID() == Integer.parseInt(id)) {
              e = inv;
              break;
          }
      }
      if (e != null) {
  %>
<div class="edit-form-container">
  <h2>Edit #<%=e.getInventoryID()%></h2>
  <form method="post" action="inventory" class="edit-form">
    <input type="hidden" name="action" value="edit"/>
    <input type="hidden" name="inventoryID" value="<%=e.getInventoryID()%>"/>

    <div class="form-field">
      <label>User ID:</label>
      <input name="userID" value="<%=e.getUserID()%>"/>
    </div>

    <div class="form-field">
      <label>Weapon ID:</label>
      <input name="weaponID" value="<%=e.getWeaponID()%>"/>
    </div>

    <div class="form-field">
      <label>Quantity:</label>
      <input name="quantity" value="<%=e.getQuantity()%>"/>
    </div>

    <div class="form-field">
      <label>Unit ID:</label>
      <input name="unitID" value="<%=e.getUnitID()%>"/>
    </div>

    <div class="form-field">
      <label>Issued:</label>
      <input type="date" name="dateIssued" value="<%=e.getDateIssued()%>"/>
    </div>

    <div class="form-field">
      <label>Returned:</label>
      <input type="date" name="dateReturned" value="<%=e.getDateReturned()%>"/>
    </div>

    <button type="submit">Save</button>
  </form>
</div>

  <% 
      } else {
  %>
  <p>Inventory item not found.</p>
  <% } } %>
</body>
</html>
