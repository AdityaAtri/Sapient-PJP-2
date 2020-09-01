<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>duration between dates</title>
</head>
<body>
<a href="/index">Back To Menu</a>
	<h2>Duration Between</h2>
	<form method="post">
		Start Date : <input type="text" name="givenDate" />
		End Date: <input type="text" name="finalDate" /> 
		<br>
		<input type="submit" />
	</form>
	<br>
	<font color="green">${givenDate}</font> 
	<br>
	<font color="green">${finalDate}</font> 
	<br>
	<font color="green">${months}</font>months
	<br>
	<font color="green">${weeks}</font>weeks
	<br>
	<font color="green">${days}</font>days
	<br>
<br>
<a href="/durationbetweendates/all">see all duration between dates operations</a>
</body>
</html>