<%@page isELIgnored="false" %>
<%
	String s=request.getParameter("providerid");
%>
<html>
<body>
	<h4>Sending Message to <%=s%></h4>
	<h3>Message Form</h3>
	<form action="savemessage">
		<pre>
		To	:	<input type="text" name="provider.email" value="<%=s%>"/>	
		From  	:	<input type="text" name="user.email" value="${userid}"/>
		Message :	<input type="text" name="message"/>
			<input type="submit" value="Message"/>
		</pre>
	</form>
</body>
</html>