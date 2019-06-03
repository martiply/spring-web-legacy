<!doctype html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Martiply</title>

<script>
	var welcomeServlet = "cf"; // client facing 


	function redirectpage(dest) {
		if (window.location.replace)
			window.location.replace(dest)
		else
			window.location = dest
	}

	redirectpage(welcomeServlet);				
</script>
</head>
</html>