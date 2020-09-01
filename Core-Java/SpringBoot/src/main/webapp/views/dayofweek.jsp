<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>day of week</title>
</head>
<body>
<a href="/index">Back To Menu</a>
	<h2>Day of Week</h2>
	<form method="post">
		Date: <input type="text" name="givenDate" /> <input type="submit" />
	</form>
	<font color="green">${givenDate}</font>
	<br>
	<font color="green">${day}</font>
	<br>
<br>
<a href="/dayofweek/all">see all day of week operations</a>
</body>
</html>