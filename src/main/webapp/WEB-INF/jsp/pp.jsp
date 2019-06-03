<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="legals"/>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="<fmt:message key="WebDescription" bundle="${bd}"/>" />
	<meta name="author" content="" />

	<title><fmt:message key="Privacy.Title"/></title>

	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	<script src="${pageContext.request.contextPath}/assets/js/resizeable.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/joinable.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-api.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-toggles.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>
	<script src="//cdn.jsdelivr.net/jquery.validation/1.14.0/jquery.validate.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/gsap/latest/TweenMax.min.js"></script>	

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body class="page-body">		
	<div class="page-container">			
		<div class="main-content">
				<div class="panel panel-default col-md-6">	
				<div class="panel-heading">
					<fmt:message key="Privacy.Title"/>	
					<div class="panel-options">
						<a href="${pageContext.servletContext.contextPath}/home">
							<i class="fa-close"></i>
						</a>
					</div>				
				</div>
				<div class="panel-body">
					<fmt:message key="Privacy.Content"/>
				</div>				
				</div>
		</div>		
	</div>


</body>
</html>