<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prime number generator</title>
</head>
<body>
<h2><%= "Prime Number Generator" %></h2>
<form action="<%=request.getContextPath() %>/gen" method="POST">
    <p>Type the start number: <input type="text" size=20 name="valueOfPrimeNumber"></p>
    <p>Type the quantity [0-300]: <input type="text" id="id1" size=20 name="valueOfQuantity"></p>
     <button id ="button" >Generate prime numbers</button>
</form>
</body>
</html>