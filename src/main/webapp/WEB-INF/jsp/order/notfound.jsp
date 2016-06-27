<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
 <head>
  <title>Not found</title>
 </head>
 <body align = center>
 <hr>
	<header>
		<h1>TestMakakus</h1>
		<hr>
		<button type="button"><a href="/TestMakakus/order/createform">Create order</a></button>
		<button type="button"><a href="/TestMakakus/order/list">Get all orders</a></button>
		<button type="button"><a href="/TestMakakus/order/searchform">Find order by id</a></button>
		<button type="button"><a href="/TestMakakus/order/deleteform">Delete order by id</a></button>
	</header>
<hr>
<b>Order not found!</b>
		<a href="/TestMakakus/">Return</a>
<hr>
	<footer>
		(c) TestMakakus Inc. 2016
	</footer>
<hr>
 </body>
</html>