<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="home"/>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="<fmt:message key="WebDescription" bundle="${bd}"/>" />
	<meta name="author" content="" />

	<title><fmt:message key="Martiply" bundle="${bd}"/></title>

	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/linecons/css/linecons.css">	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/center-crop.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/gsap/latest/TweenMax.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/resizeable.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/joinable.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-api.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-toggles.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-widgets.js"></script>
    
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<div class="page-loading-overlay">
	<div class="loader-1"></div>
</div>

<body class="page-body">
	
	<div class="page-container">
	
	    <c:import url="sideMenu.jsp">
	   		<c:param name="sideItem" value="${PAGE_SIDE_ITEM}"/>
	    </c:import>
	
		<div class="main-content">		
					
			<nav class="navbar user-info-navbar"  role="navigation"><!-- User Info, Notifications and Menu Bar -->			
				<!-- Left links for user info navbar -->
				<ul class="user-info-menu left-links list-inline list-unstyled">		
					<li class="hidden-sm hidden-xs">
						<a href="#" data-toggle="sidebar">
							<i class="fa-bars"></i>
						</a>
					</li>
									
					<li>			
						<h3 class="title"><c:out value="${HOME_TITLE}"></c:out></h3>
						<p class="text-primary"><c:out value="${HOME_SUBTITLE}"></c:out></p>			
					</li>
					
				</ul>				
			</nav>
							
	   	    <c:import url="${PAGE_CONTENT}"/>		  
	   	     
			<br />			
			<br />
			<br />
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-1">				
				<div class="footer-inner">			
					<div class="footer-text">
						&copy; 2015 
						<strong><fmt:message key="Martiply" bundle="${bd}"/></strong> 
						<a href="${pageContext.servletContext.contextPath}/tos">Terms of Service</a> | 
						<a href="${pageContext.servletContext.contextPath}/pp">Privacy Policy</a>						
					</div>
					
					
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">					
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
						</a>						
					</div>					
				</div>				
			</footer>
		</div>
	</div>
	
    


</body>
</html>