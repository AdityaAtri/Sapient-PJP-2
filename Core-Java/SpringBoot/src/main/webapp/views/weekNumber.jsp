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
	<h2>Find Week Number</h2>
	<form method="post">
		Date: <input type="text" name="givenDate" /> <input type="submit" />
	</form>
	<font color="green">${givenDate}</font>
	<br>
	<font color="green">${weekNumber}</font>
	<br>
<br>
<a href="/weeknumber/all">see all week number operations</a>
</body>
</html>