<html>
    <head>
        <title>
            Hello World Sample
        </title>
    </head>
 
    <body>
        <h1>
            <%
				String name = request.getParameter("name");
				if (name == null || name.length() == 0) {
            %> Hello, world ! <%
			} else {
				for (int i = 0; i < 10; i++) {
				%>Hello, world ! I'm <%= name%>! <%
				}
			}
			%>
        </h1>
    </body>
</html>