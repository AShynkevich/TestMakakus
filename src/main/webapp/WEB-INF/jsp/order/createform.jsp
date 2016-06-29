<!DOCTYPE html>
<html>
 <head>
 <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>
  <title>Creating order</title>
 </head>
 <body>
  <jsp:include page="/WEB-INF/jsp/tiles/header.jsp" flush="true"/>
  <jsp:include page="/WEB-INF/jsp/tiles/navigator.jsp" flush="true"/>
  <form id="data" action="create" method="post">
   <p><input placeholder="Order name" name="name" pattern="\w+" required></p>
   <p><input placeholder="Order amount (numbers only)" name="amount" pattern="\d{1,3}" required></p>
   <p><input placeholder="Order price (numbers only)" name="price" pattern="\-?\d+(\.\d{0,})?" required></p>
  </form>
  <p><button type="submit" form="data">Create order</button></p>
  <jsp:include page="/WEB-INF/jsp/tiles/footer.jsp" flush="true"/>
 </body>
</html>