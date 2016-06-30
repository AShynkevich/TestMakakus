<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css' />" />
  <title>Get All Orders</title>
 </head>
 <body align = center>
  <jsp:include page="/WEB-INF/jsp/tiles/header.jsp" flush="true"/>
  <jsp:include page="/WEB-INF/jsp/tiles/navigator.jsp" flush="true"/>
  <p><b>Order not found!</b></p>
  <a href="/TestMakakus/">Return</a>  
  <jsp:include page="/WEB-INF/jsp/tiles/footer.jsp" flush="true"/>
 </body>
</html>