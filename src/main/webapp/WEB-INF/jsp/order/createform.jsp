<!DOCTYPE html>
<html>
 <head>
  <title><b>Creating order</b></title>
 </head>
 <body>
  <form id="data" action="create" method="post">
   <p><input placeholder="Order name" name="name" pattern="\w+" required></p>
   <p><input placeholder="Order amount (numbers only)" name="amount" pattern="\d{1,3}" required></p>
   <p><input placeholder="Order price (numbers only)" name="price" pattern="\-?\d+(\.\d{0,})?" required></p>
  </form>
  <p><button type="submit" form="data">Create order</button></p>
 </body>
</html>