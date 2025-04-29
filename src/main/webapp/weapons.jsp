<%@ page import="java.util.List, gov.armamentis.dao.WeaponDAO, gov.armamentis.model.WeaponModel" %>
<%@ page session="true" %>
<%
  if(session.getAttribute("user")==null) response.sendRedirect("login.jsp");
  List<WeaponModel> weapons = new WeaponDAO().all();
%>
<html><body>
  <h2>Weapons <a href="weaponForm.jsp">[+]</a></h2>
  <table border="1">
    <tr><th>ID</th><th>Name</th><th>Type</th><th>Actions</th></tr>
    <c:forEach var="w" items="${weapons}">
      <tr>
        <td>${w.weaponID}</td>
        <td>${w.name}</td>
        <td>${w.typeID}</td>
        <td>
          <a href="weapon?op=edit&id=${w.weaponID}">Edit</a> |
          <a href="weapon?op=delete&id=${w.weaponID}"
             onclick="return confirm('Delete?')">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</body></html>
