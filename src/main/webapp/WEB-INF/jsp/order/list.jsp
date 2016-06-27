<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
 <head>
  <title>Orders list</title>
 </head>
 <body align = center>
 <hr>
	<header>
		<h1>TestMakakus</h1>
		<hr>
		<button type="button"><a href="/TestMakakus/">Home</a></button>
		<button type="button"><a href="/TestMakakus/order/createform">Create order</a></button>
		<button type="button"><a href="/TestMakakus/order/searchform">Find order by id</a></button>
		<button type="button"><a href="/TestMakakus/order/deleteform">Delete order by id</a></button>
	</header>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"  />
	<hr>
	<table align=center>
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
	<hr>
	<footer>
		(c) TestMakakus Inc. 2016
	</footer>
	<hr>
	</body>
</html>