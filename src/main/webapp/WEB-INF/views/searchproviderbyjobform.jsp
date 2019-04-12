<%@taglib uri="http://www.springframework.org/tags/form"  prefix="fr" %>
<%@page isELIgnored="false" %>
<html>
<body>
	<h3>Provider-Search-By-Job</h3>
	<hr>
	<fr:form action="searchbyjob" modelAttribute="service"> 
		Select-Job <fr:select path="sname" items="${services}"/>
		<input type="submit" value="Search"/>
	</fr:form>
	<hr>
	<a href="userdashboard">User-Home</a>
</body>
</html>