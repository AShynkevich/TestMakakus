<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
  <title>Get All Orders</title>
 </head>
 <body>
  <jsp:include page="/WEB-INF/jsp/tiles/header.jsp" flush="true"/>
  <jsp:include page="/WEB-INF/jsp/tiles/navigator.jsp" flush="true"/>
  <table align = center>
   <caption><b>Orders table</b></caption>
    <th>ID</th>
    <th>Name</th>
    <th>Amount</th>
    <th>Price</th>
    <c:forEach var="order" items="${orderList}">
      <tr><td><c:out value="${order.id}"/></td><td><c:out value="${order.orderName}"/></td>
      <td><c:out value="${order.amount}"/></td><td><c:out value="${order.price}"/></td></tr>
    </c:forEach>
   </table>
  <a href="/TestMakakus/">Return</a>
  <jsp:include page="/WEB-INF/jsp/tiles/footer.jsp" flush="true"/>
 </body>
</html>