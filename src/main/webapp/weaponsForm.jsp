<%@ page import="gov.armamentis.model.WeaponModel" %>
<%
  WeaponModel w = (WeaponModel)request.getAttribute("weapon");
%>
<html><body>
  <h2><%= w.getWeaponID()==0 ? "Create" : "Edit" %> Weapon</h2>
  <form action="weapon" method="post">
    <input type="hidden" name="weaponID" value="<%=w.getWeaponID()%>"/>
    Name: <input name="name" value="<%=w.getName()%>"/><br/>
    TypeID: <input name="typeID" value="<%=w.getTypeID()%>"/><br/>
    <button type="submit">Save</button>
  </form>
</body></html>
