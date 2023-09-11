<html>
	<head>
		<title>User Registration</title>
	</head>
	
	<body> 
	
		<h2>User Registration Information</h2>
		
		<form method="post">	
		UserName: <input type="text" name="username" required>
		Password: <input type="password" name="password" required>
		IP Address: <input type="text" name="ipaddr" required>
		<input type="submit">
		
		</form>
		
		<p style="color:red" >${passwd_message}</p>
		<p>${ip_message}</p>
		<p style="color:red" >${message}</p>
	
	</body>
</html>