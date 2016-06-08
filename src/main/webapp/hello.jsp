<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
	<link rel="stylesheet" href="main.css">
        <title>
            Hello World Sample
        </title>
    </head>
 
    <body>
		<table>
			<caption><b>Orders table</b></caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Amount</th>
				<th>Price</th>
			</tr>
			<tr><td><c:out value="${order.id}"/></td><td><c:out value="${order.orderName}"/></td>
			<td><c:out value="${order.price}"/></td><td><c:out value="${order.amount}"/></td></tr>
		</table>
    </body>
</html>