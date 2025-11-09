<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<title>Employee Details</title>
<style>
body {
  font-family: Arial, sans-serif;
  background-color: #f7f9fb;
  color: #333;
  margin: 0;
  padding: 0;
}

header {
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
}

header nav a {
  color: white;
  text-decoration: none;
  font-weight: bold;
}

header nav a:hover {
  text-decoration: underline;
}

main {
  max-width: 600px;
  margin: 40px auto;
  background: white;
  padding: 20px 30px;
  border-radius: 10px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

section div {
  text-align: right;
  margin-bottom: 15px;
}

section div a {
  margin-left: 10px;
  padding: 6px 12px;
  background-color: #007bff;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  font-size: 14px;
}

section div a:hover {
  background-color: #0056b3;
}

table {
  width: 100%;
  border-collapse: collapse;
}

td {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

td:first-child {
  font-weight: bold;
  width: 40%;
}
</style>

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
	<td> ${employee.sal} </td>
</tr>
<tr>
	<td> Employee Department </td>
	<td> ${employee.dept} </td>
</tr>

<tr>
	<td> Employee CreatedAt </td>
	<td> ${employee.createdAt} </td>
</tr>
</table>
</section>
</main>
</body>
</html>
