<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="/index">Back To Menu</a>
	<h2>Duration Between</h2>
	<form method="post">
	<ul>
		<li>Start Date : <input type="text" name="givenDate" /></li>
		<li>Unit : <input type="text" name="unit" /> days, weeks or months </li>
		<li>Quantity : <input type="text" name="quantity" /></li>
		<li>Find date before or after current date? <input type="radio" name="addsub" value =1> After
		<input type="radio" name="addsub" value =2> Before</li>
	</ul>
		<input type="submit" />
	</form>
	<br>
	<font color="green">${givenDate}</font> 
	<br>
	Date Found: <font color="green">${finalDate}</font>
	
	<br>
<br>
<a href="/findadate/all">see all date from phrase operations</a>
</body>
</html>