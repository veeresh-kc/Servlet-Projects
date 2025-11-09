<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix ="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<c:choose>
	<c:when test="${not empty employee}">Edit Employee Records</c:when>
	<c:otherwise>Create New Employee</c:otherwise>
	</c:choose>
</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
}

h1 {
    text-align: center;
    color: #333;
}

nav {
    text-align: center;
    margin-bottom: 15px;
}

table {
    margin: 0 auto;
    border-collapse: collapse;
}

td {
    padding: 8px;
}

input, button, a {
    padding: 6px 10px;
    font-size: 14px;
}

button {
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}

a {
    text-decoration: none;
    color: #007BFF;
}

a:hover {
    text-decoration: underline;
}
</style>

</head>

<body>
<h1><c:choose>
<c:when test="${not empty employee}">Edit Employee</c:when>
<c:otherwise>New Employee</c:otherwise>
</c:choose>
</h1>
<nav>
<a href="${pageContext.request.contextPath}/employees">Back</a>
</nav>
<div>
	<c:choose>
		<c:when test="${not empty employee}">
			<c:set var="formAction" value="/employees/update" />
		</c:when>
		<c:otherwise>
			<c:set var="formAction" value="/employees/insert" />
		</c:otherwise>
	</c:choose>
<form action="${pageContext.request.contextPath}${formAction}" method="post">
	<c:if test="${not empty employee}">
	<input type="hidden" name="id" value="${employee.id}">
	</c:if>
	<table>
	<tr>
	<td>Name: </td>
	<td><input type="text" name="name" required value="${employee.name}" placeholder="Employee Name"></td>
	</tr>
	
	<tr>
	<td>Email: </td>
	<td><input type="email" name="email" required value="${employee.email}" placeholder="Employee Email"></td>
	</tr>
	
	<tr>
	<td>Salary: </td>
	<td><input type="number" name="sal" step="0.01" min="0" required value="${employee.sal}" placeholder="Employee Salary"></td>
	</tr>
	
	<tr>
	<td>Department: </td>
	<td><input type="text" name="dept" required value="${employee.dept}" placeholder="Department Name"></td>
	</tr>
	
	<tr>
	<td><button type="submit">Save</button> </td>
	<td><a href="${pageContext.request.contextPath}/employees">Cancel</a></td>
	</tr>
	</table>
</form>
</div>
</body>
</html>