<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="welcome"/>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="${l}"> <!--<![endif]-->
<head>
	<!-- DEFAULT META TAGS -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="description" content='<fmt:message key="Description"/>'>
	
	<title><fmt:message key="Title"/></title>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,800,600|Raleway:300,600,900' rel='stylesheet' type='text/css'>
	<link rel='stylesheet' id='default-style-css'  href='${pageContext.request.contextPath}/assets/css/welcome/style.css'/>
	<link rel='stylesheet' id='flexslider-style-css'  href='https://cdnjs.cloudflare.com/ajax/libs/flexslider/2.5.0/flexslider.min.css'/>
	<link rel='stylesheet' id='easy-opener-style-css'  href='${pageContext.request.contextPath}/assets/css/easy-opener/easy-opener.css' />
	<link rel='stylesheet' id='jplayer-style-css'  href='https://cdnjs.cloudflare.com/ajax/libs/jplayer/2.9.2/skin/blue.monday/css/jplayer.blue.monday.min.css'/>
	<link rel='stylesheet' id='isotope-style-css'  href='${pageContext.request.contextPath}/assets/css/isotope/isotope.css' />
	<link rel="stylesheet" id='rsplugin-style-css' href="${pageContext.request.contextPath}/assets/css/rs-plugin/settings.css" />
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel='stylesheet' id='retina-style-css' href='${pageContext.request.contextPath}/assets/css/retina/retina.css' />
	<link rel='stylesheet' id='mqueries-style-css'  href='${pageContext.request.contextPath}/assets/css/welcome/mqueries.css'>
	<link rel='stylesheet' id='lightbox-style-css'  href='${pageContext.request.contextPath}/assets/css/lightbox/lightbox.css'/>
	<link rel="shortcut icon" href='${pageContext.request.contextPath}/assets/images/favicon.png'/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> 
	<script src='https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js'></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.1/js/lightbox.min.js"></script>

</head>

<body> 
<div id="page-loader">
	<div class="page-loader-inner">
    	<div class="loader-logo"><img src="${pageContext.request.contextPath}/assets/images/logo_web.png" alt="Logo"/></div>
		<div class="loader-icon"><span class="spinner"></span><span></span></div>
	</div>
</div>


<!-- PAGE CONTENT -->
<div id="page-content" class="fixed-header">

	<!-- HEADER -->
	<header id="header">        
		<div class="header-inner wrapper clearfix">
                            
			<div id="logo" class="left-float">
				<a id="defaut-logo" class="logotype" href="index.html"><img src="${pageContext.request.contextPath}/assets/images/logo_web.png" alt="Logo"></a>
			</div>    
            
			<div class="menu right-float clearfix">
           	<nav id="main-nav">
            		<ul>
                  	<li class="current-menu-item"><a href="#home" class="scroll-to"><fmt:message key="Home"/></a></li>
                  	<li><a href="#about" class="scroll-to"><fmt:message key="About"/></a></li>
					<li><a href="#features" class="scroll-to"><fmt:message key="Features"/></a></li>
                  	<li><a href="#contact" class="scroll-to"><fmt:message key="Contact"/></a></li>		
                  	<li><a href="${pageContext.request.contextPath}/login"><fmt:message key="Login"/></a></li>				
					</ul>
				</nav>
              	<nav id="menu-controls">
            		<ul>
                  	<li class="current-menu-item"><a href="#home" class="scroll-to"><span class="c-dot"></span><span class="c-name"><fmt:message key="Home"/></span></a></li>
                  	<li><a href="#about" class="scroll-to"><span class="c-dot"></span><span class="c-name"><fmt:message key="About"/></span></a></li>
					<li><a href="#features" class="scroll-to"><span class="c-dot"></span><span class="c-name"><fmt:message key="Features"/></span></a></li>
                  	<li><a href="#contact" class="scroll-to"><span class="c-dot"></span><span class="c-name"><fmt:message key="Contact"/></span></a></li>
                  	<li><a href="${pageContext.request.contextPath}/login"><span class="c-dot"></span><span class="c-name"><fmt:message key="Login"/></span></a></li>
					</ul>
				</nav>  
			</div>
                    
		</div> <!-- END .header-inner -->
	</header> <!-- END header -->
	<!-- HEADER -->    
    
	<!-- PAGEBODY -->
	<div class="page-body">
    
		<!-- HOME (SLIDER) -->
		<section id="home" class="no-padding">
          	<div class="section-inner"> 
			 
            <!-- REVOLUTION SLIDER -->
            <div class="home-slider-container">
				<div class="home-slider" >
            		<ul>                 	
                 		<!-- THE FIRST SLIDE -->
                 		<li data-transition="fade" data-slotamount="1" data-masterspeed="800" >
                     		<!-- THE MAIN IMAGE IN THE FIRST SLIDE -->
							<img src="${pageContext.request.contextPath}/assets/images/slider-bg1.jpg"   alt="slidebg1"  data-bgfit="cover" data-bgposition="center center" data-bgrepeat="no-repeat">
                        	
                    		<!-- LAYER NR. 1 -->    
                        	<div class="tp-caption srcaption-bigwhite lfl ltr"
                            data-x="center" data-hoffset="0"
                            data-y="center" data-voffset="0"
                            data-speed="1000"
                            data-start="100"
                            data-easing="easeInOutQuad"
                            data-endspeed="1000"
                            data-endeasing="easeInOutQuad"
                            style="z-index: 2"><fmt:message key="MultiplyYourAd"/>
                        	</div>
                        
                        	<!-- LAYER NR. 2 -->
                        	<div class="tp-caption srcaption-miniwhite sfb"
                            data-x="center" data-hoffset="0"
                            data-y="bottom" data-voffset="-45"
                            data-speed="500"
                            data-start="1200"
                            data-easing="easeInOutQuad"
                            data-endspeed="1000"
                            data-endeasing="easeInOutQuad"
                            style="z-index: 3"><fmt:message key="IntroducingMartiply"/>
                        	</div>
							
							<!-- LAYER NR. 3 -->
                        	<div class="tp-caption srcaption-miniwhite sfb text-light"
                            data-x="center" data-hoffset="0"
                            data-y="bottom" data-voffset="15"
                            data-speed="500"
                            data-start="1300"
                            data-easing="easeInOutQuad"
                            data-endspeed="1000"
                            data-endeasing="easeInOutQuad"
                            style="z-index: 3"><a href="#about" class="sr-button sr-buttonicon small-iconbutton scroll-to"><i class="fa fa-angle-down"></i></a>
                        	</div>                           
                  	</li> <!-- end first slide -->                              
     				</ul>
    
				</div>
   			</div> <!-- END .home-slider-container -->        
			<!-- REVOLUTION SLIDER -->
       	
	    	</div> <!-- END .section-inner -->   
       	</section>
     	<!-- HOME (SLIDER) -->
		   
       <!-- ABOUT --> 
      	<section id="about">
            <div class="section-inner">          
            <div class="wrapper">

                
             	<div class="section-title">
                	<h2><fmt:message key="AboutTitle"/></h2>
              		<div class="seperator size-small"><span></span></div>
					<fmt:message key="AboutInfo"/>
              	</div>
                
				<div class="column-section clearfix">
                	<div class="column one-third align-center sr-animation sr-animation-zoomin" data-delay="200">
                  	<i class="fa fa-smile-o fa-4x xone"></i>
                    	<h4><strong><fmt:message key="CompetitivePricing"/></strong></h4>
                    	<p><fmt:message key="CompetitivePricingInfo"/></p>
                  </div>
                  <div class="column one-third align-center sr-animation sr-animation-zoomin" data-delay="400">
                  	<i class="fa fa-tags fa-4x xone"></i>
                    	<h4><strong><fmt:message key="FreeInventoryListing"/></strong></h4>
                    	<p><fmt:message key="FreeInventoryListingInfo"/></p>
                  </div>
                  <div class="column one-third last-col align-center sr-animation sr-animation-zoomin" data-delay="600">
                  	<i class="fa fa-thumbs-o-up fa-4x xone"></i>
                    	<h4><strong><fmt:message key="SignupBonus"/></strong></h4>
                    	<p><fmt:message key="SignupBonusInfo"/></p>
                  </div>
				</div> <!-- END .column-section -->
                
			</div> <!-- END .wrapper> -->
            
			</div> <!-- END .section-inner-->
		</section>
       	<!-- ABOUT -->
				

       
	   	<!-- FEATURES --> 
		<section id="features">
            <div class="section-inner">

            	<div id="features-prlx" class="horizontalsection text-light parallax-section">
					<div class="horizontalinner wrapper">												
						<div class="section-title sr-animation">
							<h2><fmt:message key="Features"/></h2>
							<div class="seperator size-small"><span></span></div>
							<fmt:message key="FeaturesInfo"/>
						</div>						
					</div>  
            	</div>
                 	
         	</div> <!-- END .section-inner-->

            <div class="section-inner">
            
            <div class="wrapper">
                
            	<div class="column-section clearfix">
                	<div class="column one-half">
                  	<fmt:message key="FeatureFrontPage"/>
                  </div>
                  <div class="column one-half last-col">
						<div class="align-center sr-animation sr-animation-zoomin" data-delay="200">
							<a href="${pageContext.request.contextPath}/assets/images/fp_jp_or.jpg" data-lightbox="image-1" data-title="Sample Frontpage">
								<img src="${pageContext.request.contextPath}/assets/images/fp_jp.png"/>
							</a>
						</div>
                  </div>
				</div> 
				
				<div class="column-section clearfix">
                	<div class="column one-half">
                  		<fmt:message key="FeatureTweet"/>					
               	    </div>
                  <div class="column one-half last-col">
						<div class="align-center sr-animation sr-animation-zoomin" data-delay="200">						
						 <video width="250" height="400" controls>
						 	<c:choose>
						 		<c:when test="${l == 'ja-JP'}">
 		 							 <source src="${pageContext.request.contextPath}/assets/videos/tweet_jp.webm" type='video/webm' />
									 <source src="${pageContext.request.contextPath}/assets/videos/tweet_jp.ogv" type='video/ogg' />
									 <source src="${pageContext.request.contextPath}/assets/videos/tweet_jp.mp4" type='video/mp4' />						 		
						 		</c:when>
						 		<c:otherwise>
 									 <source src="${pageContext.request.contextPath}/assets/videos/tweet.webm" type='video/webm' />
									 <source src="${pageContext.request.contextPath}/assets/videos/tweet.ogv" type='video/ogg' />
									 <source src="${pageContext.request.contextPath}/assets/videos/tweet.mp4" type='video/mp4' />						 		
						 		</c:otherwise>						 	
						 	</c:choose>
							<fmt:message key="YourBrowserNotSupportVideo"/>
						</video> 
						</div>
                  </div>
				</div> 
				
				<div class="column-section clearfix">
                	<div class="column one-half">
						<fmt:message key='FeatureInventoryListing'/>
                    </div>
                  <div class="column one-half last-col">
						<div class="align-center sr-animation sr-animation-zoomin" data-delay="200">						
						 <video width="250" height="400" controls>							 
							 <source src="${pageContext.request.contextPath}/assets/videos/search.webm" type='video/webm' />
							 <source src="${pageContext.request.contextPath}/assets/videos/search.ogv" type='video/ogg' />
							 <source src="${pageContext.request.contextPath}/assets/videos/search.mp4" type='video/mp4' />
							 <fmt:message key="YourBrowserNotSupportVideo"/>
						</video> 
		

						</div>
                  </div>
				</div> 
				               
			</div> <!-- END .wrapper> -->    						                                              
			</div> <!-- END .section-inner-->
		</section>
		
		<div class="spacer spacer-big"></div>
       
	   	<!-- CONTACT --> 
       <section id="contact">
	   
			<div id="contact-prlx" class="horizontalsection text-light parallax-section">
					<div class="horizontalinner wrapper align-center">
					<div class="spacer spacer-big"></div>  
                  	<h2><fmt:message key="InterestedInJoining"/></h2>
                    	<div class="spacer spacer-small"></div>
                    	<p><a href='mailto:<fmt:message key="ContactEmail"/>' class="scroll-to sr-button sr-button4 medium-button"><fmt:message key="ContactUs"/></a></p>
            		</div>
					<div class="spacer spacer-big"></div>  
            	</div>

     	</section>
		<!-- CONTACT -->
        
      	<!-- FOOTER -->  
		<footer>
			<div class="footerinner wrapper align-center text-light">
				<a id="backtotop" href="#" class="sr-button sr-buttonicon small-iconbutton"><i class="fa fa-angle-up"></i></a>
            	<p class="copyright">Copyright &copy; 2014 - Martiply</p>
         	</div>
    	</footer>
      	<!-- FOOTER -->         
        
 	</div> <!-- END .page-body -->
	<!-- PAGEBODY -->
    
</div> <!-- END #page-content -->
<!-- PAGE CONTENT -->


<!-- SCRIPTS -->
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/retina.js/1.3.0/retina.min.js'></script>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/easing/jquery.easing.compatibility.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/visible/jquery.visible.min.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/easy-opener/jquery.easy-opener.min.js'></script>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/flexslider/2.5.0/jquery.flexslider-min.js'></script>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery.isotope/2.2.1/isotope.pkgd.min.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/bgvideo/jquery.bgvideo.min.js'></script>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/fitvids/1.1.0/jquery.fitvids.min.js'></script>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jplayer/2.9.2/jplayer/jquery.jplayer.min.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/rs-plugin/themepunch/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/rs-plugin/themepunch/jquery.themepunch.revolution.min.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/parallax/jquery.parallax.min.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/counter/jquery.counter.min.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/scroll/jquery.scroll.min.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/xone/xone-header.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/xone/xone-loader.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/xone/xone-form.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/assets/js/xone/script.js'></script>
<!-- SCRIPTS -->
</body>
</html>