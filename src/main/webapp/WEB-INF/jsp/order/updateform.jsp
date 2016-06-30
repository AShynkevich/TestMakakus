<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css' />" />
  <title>Update form</title>
 </head>
 <body  align = center>
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
  <form id="data" action="update" method="post">
   <p><input placeholder="Order id" name="Id" required></p>
   <p><input placeholder="Order name" name="name" pattern="\w+" required></p>
   <p><input placeholder="Order amount (numbers only)" name="amount" pattern="\d{1,3}" required></p>
   <p><input placeholder="Order price (numbers only)" name="price" pattern="\-?\d+(\.\d{0,})?" required></p>
  </form>
  <p><button type="submit" form="data">Update order</button></p>
  <a href="/TestMakakus/">Return</a>
  <jsp:include page="/WEB-INF/jsp/tiles/footer.jsp" flush="true"/>
 </body>
</html>
