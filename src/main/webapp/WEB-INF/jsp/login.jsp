<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="login"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="<fmt:message key="WebDescription" bundle="${bd}"/>" />

	<title><fmt:message key="Login.Title"/></title>

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
	<script src="//cdn.jsdelivr.net/jquery.validation/1.14.0/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/latest/TweenMax.min.js"></script>	
	<script src="${pageContext.request.contextPath}/assets/js/xenon-api.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-toggles.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>


	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->


</head>

<script>
$(function() { 
	<c:choose>
	    <c:when test="${not empty ERROR}">
		      $("#error").text('${fn:escapeXml(ERROR)}').show();
	    </c:when>
	
	    <c:otherwise>
	   	  $("#error").hide();						      	  
	    </c:otherwise>
    </c:choose>
    
	setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);
    
	$("form#login .form-group:has(.form-control):first .form-control").focus();


	$("form#login").validate({
		rules: {
			email: {
				required: true,
				email:true
			},
		},

		messages: {
			email: {
				required: '<fmt:message key="Login.EmailNeeded"/>',
				email: '<fmt:message key="Login.EmailForm"/>'
			},
		},

		submitHandler: function(form){
			show_loading_bar(50);
			$.ajax({
				url: "${pageContext.servletContext.contextPath}/login",
				method: 'POST',
				data: {
					email: $(form).find('#email').val(),
				},
				success: function(resp){
					show_loading_bar({
						delay: .5,
						pct: 100,
						finish: function(){
				   	   		  if (resp.indexOf("https") > -1){
				   	   				window.location = resp;
				   	   		  }else{
				   	   			    $("#error").text(resp).show();
				   	   		  }
						}
					});
				}
			});
		}
	});
   
});

</script>
<body class="page-body login-page">
	
	<div class="login-container">	
		<div class="row">	
			<div class="col-sm-6">		
				<!-- Errors container -->
				<div class="errors-container"></div>
	
				<form method="post" role="form" id="login" class="login-form fade-in-effect">
	
					<div class="login-header">
							<img src="${pageContext.request.contextPath}/assets/images/martiply_logo_text.png" alt="" width="80" />
							<p><fmt:message key="Login.LoginToClientArea"/></p>
					</div>
					
					
					<p id="error" class="bg-danger"></p>
					
	
	
					<div class="form-group">
						<label class="control-label" for="email"><fmt:message key="Login.Email"/></label>
						<input type="text" class="form-control input-dark" name="email" id="email"  />
					</div>
					
					<div class="form-group">
						<button id="login-twitter"  type="submit" class="btn btn-blue  btn-block text-left">
							<i class="fa-lock"></i>
							<fmt:message key="Login.LoginWithTwitter"/>
						</button>
					</div>
	
					<div class="login-footer">	
						<div class="info-links">
							<a href="${pageContext.servletContext.contextPath}/tos">ToS</a> -
							<a href="${pageContext.servletContext.contextPath}/pp">Privacy Policy</a>
						</div>
	
					</div>
				</form>			

			</div>	
		</div>	
	</div>


</body>
</html>