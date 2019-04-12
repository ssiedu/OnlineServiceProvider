<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<body>
	<h3>Providers-List</h3>
	<hr>
	<table border="2">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Address</th>
			<th>Mobile</th>
			<th>Job</th>
			<th>Charge</th>
		</tr>
		<c:forEach items="${providers}" var="provider">
			<tr>
				<td>${provider.name}</td>
				<td>${provider.email}</td>
				<td>${provider.address}</td>
				<td>${provider.mobile}</td>
				<td>${provider.service.sname}</td>				
				<td>${provider.service.charge}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="userdashboard">User-Home</a>

</body>
</html>