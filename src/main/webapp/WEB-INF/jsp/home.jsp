<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="home"/>

<div class="row">
	<div class="col-md-3">
		
		<div class="xe-widget xe-counter xe-counter-blue" data-count=".num" data-from="0" data-to="${fn:escapeXml(ITEM_COUNT)}" data-duration="3" data-easing="true">
			<div class="xe-icon">
				<i class="linecons-t-shirt"></i>
			</div>
			<div class="xe-label">
				<strong class="num"><c:out value="${ITEM_COUNT}"/></strong>
				<span><fmt:message key="Items"/></span>
			</div>
		</div>
	
	</div>
	<div class="col-md-3">			
		<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="0" data-to="${fn:escapeXml(sessionScope.SESSION_CREDIT)}" data-prefix="${fn:escapeXml(sessionScope.SESSION_PRETTY_CURRENCY)}" data-duration="1" data-easing="false">
			<div class="xe-icon">
				<i class="linecons-wallet"></i>
			</div>
			<div class="xe-label">
				<strong class="num"><c:out value="${CREDIT}"/></strong>
				<span><fmt:message key="Credit"/></span>
			</div>
		</div>		
	</div>
</div>

<br/>
<br/>
<div class="row">
	<div class="col-md-6">									
		<ul class="list-group list-group-minimal">
			<li class="list-group-item active">
				<fmt:message key="Notifications"/>
			</li>
			<c:choose>
				<c:when test="${not empty NOTIFICATIONS}">			
					<c:forEach var="note"  items="${NOTIFICATIONS}">
						<li class="list-group-item">
							<c:out value="${note}"/>
						</li>
					</c:forEach>		
				</c:when>
				<c:otherwise>
					<li class="list-group-item">
						<fmt:message key="empty"/>
					</li>	
				</c:otherwise>
			</c:choose>
		</ul>									
	</div>	
</div>		
