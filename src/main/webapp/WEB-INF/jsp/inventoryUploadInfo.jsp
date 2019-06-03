<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="inventory"/>

<div class="panel panel-default">	
	<div class="panel-heading">		
		<div class="panel-title"><fmt:message key="ManualInput"/></div>		
	</div>	
	<div class="panel-body">
		<p class="text-primary"><fmt:message key="ManualInputInfo"/></p>	
		<br/>
		<a href="${pageContext.request.contextPath}/inventory/new" class="btn btn-secondary"><fmt:message key="Go" bundle="${bd}"/></a>		
	</div>
</div>

<div class="panel panel-default">	
	<div class="panel-heading">		
		<div class="panel-title"><fmt:message key="CSVInput"/></div>		
	</div>	
	<div class="panel-body">
		<p class="text-primary"><fmt:message key="CSVInputInfo"/></p>		
		<br/>
		<a href="${pageContext.request.contextPath}/inventory/csv" class="btn btn-secondary"><fmt:message key="Go" bundle="${bd}"/></a>		
	</div>
</div>

<div class="panel panel-default">	
	<div class="panel-heading"><fmt:message key="PleaseRemember"/></div>
	<div class="panel-body">
		<fmt:message key="PleaseRememberInfo">
			<fmt:param value="<ins><a href='${pageContext.request.contextPath}/store'>"/>
			<fmt:param value="</a></ins>"/>
			<fmt:param value="<ins><a href='${pageContext.request.contextPath}/inventory'>"/>
			<fmt:param value="</a></ins>"/>						
		</fmt:message>
	</div>
</div>
