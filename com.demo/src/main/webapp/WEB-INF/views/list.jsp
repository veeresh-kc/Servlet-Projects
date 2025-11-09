<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix ="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee List</title>
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 40px;
  background-color: #f4f6f8;
  color: #333;
}

h1 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 20px;
}

nav {
  text-align: center;
  margin-bottom: 25px;
}

nav a {
  background-color: #3498db;
  color: white;
  text-decoration: none;
  padding: 8px 16px;
  border-radius: 6px;
  font-weight: bold;
  transition: 0.2s;
}

nav a:hover {
  background-color: #2980b9;
}

h2 {
  color: #2c3e50;
  margin-bottom: 10px;
}

table {
  width: 100%;
  border-collapse: collapse;
  background-color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

th, td {
  padding: 10px 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #3498db;
  color: white;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

tr:hover {
  background-color: #f1f9ff;
}

td a {
  color: #3498db;
  text-decoration: none;
  margin-right: 10px;
  font-weight: bold;
}

td a:hover {
  text-decoration: underline;
}

p {
  text-align: center;
  background-color: #fff;
  padding: 15px;
  border-radius: 6px;
  box-shadow: 0 0 6px rgba(0,0,0,0.1);
  color: #555;
}

</style>
</head>
<body>
<h1>Employee List</h1>
<nav>
<a href="${pageContext.request.contextPath}/employees/new">Add New Emlployee</a>
</nav>
<div>
<h2>Employee List</h2>
<c:choose>
<c:when test="${empty employees}">
<p>No Employee Records Found. Create a new Employee using the Add new Employeee Link</p>
 </c:when>
 <c:otherwise>
 <table border=2px solid black>
 <thead>
 <tr>
 <th>Emp ID</th>
 <th>Emp Name</th>
 <th>Email</th>
 <th>salary</th>
 <th>Department</th>
 <th>Created</th>
 <th>Actions</th>
 </tr>
 </thead>
 <tbody>
 <c:forEach var="e" items="${employees}">
 <tr>
 	<td>${e.id}</td>
 	<td> <a href="${pageContext.request.contextPath}/employees/view?id=${e.id}">${e.name}</a></td>
 	<td>${e.email}</td>
 	<td>${e.sal}</td>
 	<td>${e.dept}</td>
 	<td>${e.createdAt}</td>
 	<td>
 	<a href="${pageContext.request.contextPath}/employees/edit?id=${e.id}">Edit</a>
 	<a href="${pageContext.request.contextPath}/employees/delete?id=${e.id}" onClick="return confirm('Do you want to delete this Employee?')">Delete</a>
 	</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </c:otherwise>
</c:choose>
</div>
</body>
</html>