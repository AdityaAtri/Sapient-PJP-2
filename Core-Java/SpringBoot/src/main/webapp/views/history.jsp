<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>History</title>
</head>
<body>
<a href="/index">Back To Menu</a>
<h2>History</h2>

<table border = "1">
<caption>history of operations</caption>
<tr>
	<th>Menu Option</th>
	<th>Given Date</th>
	<th>Final Date</th>
	<th>Remark</th>
</tr>
<c:forEach var="entry" items="${list}">
<tr>
	<td><c:out value="${entry.menuOption}" /></td>
	<td><c:out value="${entry.givenDate}" /></td>
	<td><c:out value="${entry.finalDate}" /></td>
	<td><c:out value="${entry.remark}" /></td>
</tr>
</c:forEach>
</table>
</body>
</html>