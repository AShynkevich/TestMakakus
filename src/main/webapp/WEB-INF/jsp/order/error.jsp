<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css' />" />
  <title>Something wrong!</title>
 </head>
 <body align = center>
  <jsp:include page="/WEB-INF/jsp/tiles/header.jsp" flush="true"/>
  <jsp:include page="/WEB-INF/jsp/tiles/navigator.jsp" flush="true"/>
  <p><b><c:out value="${message}"/></b></p>
  <jsp:include page="/WEB-INF/jsp/tiles/footer.jsp" flush="true"/>
 </body>
</html>