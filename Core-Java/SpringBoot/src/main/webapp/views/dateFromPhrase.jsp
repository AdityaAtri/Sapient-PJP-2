<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Date From Phrase</title>
</head>
<body>
<a href="/index">Back To Menu</a>
<h2>Find Date From Phrase</h2>
	<form method="post">
		Phrase: <input type="text" name="phrase" /> <input type="submit" />
	</form>
	<font color="green">${givenDate}</font>
	<br>
	<font color="green">${phrase}</font>
	<br>
	Final Date :<font color="green">${finalDate}</font>
<br>
<br>
<a href="/datefromphrase/all">see all date from phrase operations</a>	
	
</body>
</html>