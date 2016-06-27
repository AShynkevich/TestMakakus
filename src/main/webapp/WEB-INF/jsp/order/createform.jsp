<!DOCTYPE html>
<html>
 <head>
  <title>Create form</title>
 </head>
 <body align = center>
 <hr>
	<header>
		<h1>TestMakakus</h1>
		<hr>
		<button type="button"><a href="/TestMakakus/">Home</a></button>
		<button type="button"><a href="/TestMakakus/order/list">Get all orders</a></button>
		<button type="button"><a href="/TestMakakus/order/searchform">Find order by id</a></button>
		<button type="button"><a href="/TestMakakus/order/deleteform">Delete order by id</a></button>
	</header>
<hr>
	<p><b>Create order</b></p>
	<form id="data" action="create" method="post">
	<p><input placeholder="Order name" name="name" pattern="\w+" required></p>
	<p><input placeholder="Order amount (numbers only)" name="amount" pattern="\d{1,3}" required></p>
	<p><input placeholder="Order price (numbers only)" name="price" pattern="\-?\d+(\.\d{0,})?" required></p>
	</form>
<hr>
	<p><button type="submit" form="data">Create order</button></p>
	<a href="/TestMakakus/">Return</a>
<hr>
	<footer>
		(c) TestMakakus Inc. 2016
	</footer>
<hr>
 </body>
</html>