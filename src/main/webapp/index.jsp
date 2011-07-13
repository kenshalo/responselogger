<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Location Resource</title>
        <script type="text/javascript" src="/resources/jquery-1.6.2/jquery-1.6.2.min.js"></script>
		<script type="text/javascript">
			function sendRequest(b, type, code) {
				$.ajax({
    				url: '/rest/locations/' + code,
    				type: type
				});
			}
		</script>
    </head>
    <body>
		<p>
			GET requests: 
				<input type="button" value="200s" onclick="sendRequest(this, 'GET', 200);" />
				<input type="button" value="300s" onclick="sendRequest(this, 'GET', 300);" />
				<input type="button" value="400s" onclick="sendRequest(this, 'GET', 400);" />
				<input type="button" value="500s" onclick="sendRequest(this, 'GET', 500);" />
		</p>

		<p>
			PUT requests: 
				<input type="button" value="200s" onclick="sendRequest(this, 'PUT', 200);" />
				<input type="button" value="300s" onclick="sendRequest(this, 'PUT', 300);" />
				<input type="button" value="400s" onclick="sendRequest(this, 'PUT', 400);" />
				<input type="button" value="500s" onclick="sendRequest(this, 'PUT', 500);" />
		</p>

		<p>
			DELETE requests: 
				<input type="button" value="200s" onclick="sendRequest(this, 'DELETE', 200);" />
				<input type="button" value="300s" onclick="sendRequest(this, 'DELETE', 300);" />
				<input type="button" value="400s" onclick="sendRequest(this, 'DELETE', 400);" />
				<input type="button" value="500s" onclick="sendRequest(this, 'DELETE', 500);" />
		</p>
	
    </body>
</html>
