<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<title>Employee Details</title>
</head>
<body>
<header>
<nav>
<a href="${pageContext.request.contextPath}/employees">Back</a>
</nav>
</header>
<main>
<section>
<div>
<a href="${pageContext.request.contextPath}/employees/edit?id=${employee.id}">Edit</a>
<a href="${pageContext.request.contextPath}/employees/delete?id=${employee.id}">Delete</a>
</div>
<table>	
<tr>
	<td> Employee Name </td>
	<td> ${employee.name} </td>
</tr>

<tr>
	<td> Employee Email </td>
	<td> ${employee.email} </td>
</tr>

<tr>
	<td> Employee Salary </td>
	<td> ${employee.salary} </td>
</tr>
<tr>
	<td> Employee Department </td>
	<td> ${employee.depart} </td>
</tr>

<tr>
	<td> Employee CreatedAt </td>
	<td> ${employee.name} </td>
</tr>
</table>
</section>
</main>
</body>
</html>
