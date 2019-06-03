<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<div class="sidebar-menu toggle-others fixed">
		
			<div class="sidebar-menu-inner">
				
				<header class="logo-env">
		
					<!-- logo -->
					<div class="logo">
						<a href="${fn:escapeXml(pageContext.servletContext.contextPath)}/home" class="logo-expanded">												
							<img src="${pageContext.request.contextPath}/assets/images/martiply_logo_text.png" width="80" alt="" />
						</a>
		
						<a href="${fn:escapeXml(pageContext.servletContext.contextPath)}/home" class="logo-collapsed">
							<img src="${pageContext.request.contextPath}/assets/images/logo_simple.png" width="40" alt="" />
						</a>
					</div>
		
					<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
					<div class="mobile-menu-toggle visible-xs">
						<a href="#" data-toggle="user-info-menu">
							<i class="fa-bell-o"></i>
							<span class="badge badge-success">7</span>
						</a>
		
						<a href="#" data-toggle="mobile-menu">
							<i class="fa-bars"></i>
						</a>
					</div>						
				</header>
				

				<ul id="main-menu" class="main-menu">
					<!-- add class "multiple-expanded" to allow multiple submenus to open -->
					<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
							
							
					<li class='<c:if test="${param.sideItem == 'home'}"><c:out value="active"/></c:if>'>
						<a href='${fn:escapeXml(pageContext.servletContext.contextPath)}/home'>
							<i class="linecons-desktop"></i>
							<span class="title">Home</span>
						</a>
					</li>	
					
					<li class='<c:if test="${param.sideItem == 'store'}"><c:out value="active"/></c:if>'>
						<a href='${fn:escapeXml(pageContext.servletContext.contextPath)}/store'>
							<i class="linecons-shop"></i>
							<span class="title">Stores</span>
						</a>
					</li>	
										
					<li class='<c:if test="${param.sideItem == 'inventory'}"><c:out value="active"/></c:if>'>
						<a href='${fn:escapeXml(pageContext.servletContext.contextPath)}/inventory'>
							<i class="linecons-t-shirt"></i>
							<span class="title">Inventory</span>
						</a>
					</li>
					
					<li class='<c:if test="${param.sideItem == 'cp'}"><c:out value="active"/></c:if>' >
						<a href='${fn:escapeXml(pageContext.servletContext.contextPath)}/cp'>
							<i class="linecons-tag"></i>
							<span class="title">Promotion</span>
						</a>
					</li>	
					
					<li>
						<a href='${fn:escapeXml(pageContext.servletContext.contextPath)}/logout'>							
							<span class="title">Logout</span>
						</a>
					</li>					
				</ul>
				
			</div>
		
		</div>