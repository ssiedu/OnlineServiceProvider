<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<body>
	<h3>Messages-List</h3>
	<hr>
	<table border="2">
		<tr>
			<th>MCode</th>
			<th>Sender</th>
			<th>Message</th>
			<th>Address</th>
			<th>Email</th>
			<th>Mobile</th>
			
		</tr>
		<c:forEach items="${messages}" var="message">
			<tr>
				<td>${message.messageId}</td>
				<td>${message.user.uname}</td>								
				<td>${message.message}</td>
				<td>${message.user.address}</td>
				<td>${message.user.email}</td>
				<td>${message.user.mobile}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="providerdashboard">Provider-Home</a>

</body>
</html>