<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="store"/>

	<c:if test="${not empty SESSION_PAGE_NOT_AVAILABLE}">
		<script>
			var opts = {
				"closeButton": true,
				"debug": false,
				"positionClass": "toast-top-full-width",
				"onclick": null,
				"showDuration": "300",
				"hideDuration": "1000",
				"timeOut": "5000",
				"extendedTimeOut": "1000",
				"showEasing": "swing",
				"hideEasing": "linear",
				"showMethod": "fadeIn",
				"hideMethod": "fadeOut"
			};
						
			$(function() {
				toastr.warning('${fn:escapeXml(SESSION_PAGE_NOT_AVAILABLE)}', null, opts);	
			});
		</script>
	</c:if>
	
	<c:if test="${not empty LIST_STORES}">
    	<div class="row">
		<div class="col-md-12">
		
			<div class="panel panel-default">				
				<table class="table">
					<thead>
						<tr>
							<th><fmt:message key="StoreName"/></th>
							<th><fmt:message key="Address"/></th>
							<th><fmt:message key="Phone"/></th>
							<th><fmt:message key="Coupon"/></th>
						</tr>
					</thead>
								
					<tbody>
						<c:forEach var="store" items="${LIST_STORES}">
							<tr>
							   <td> <strong><a href="${pageContext.request.contextPath}/store/${fn:escapeXml(store.storeId)}"><c:out value="${store.name}"/></a></strong></td>
	   						   <td> <c:out value="${store.address}"/></td> 
	   						   <td> <c:out value="${store.phone}"/></td>
	    					   <td> 
	    					   <c:choose>
	    					   	<c:when test="${not empty store.coupon && store.coupon.endSec gt TS_NOW}">
	    					   		<a href="${pageContext.request.contextPath}/cp/view/${fn:escapeXml(store.coupon.cpId)}"><i class="fa fa-line-chart"></i> <c:out value="${store.coupon.cpId}"/></a>					   	
	    					   	</c:when>
	    					   	<c:otherwise>
	    					   		<fmt:message key="None"/>
	    					   	</c:otherwise>
	    					   </c:choose>
	    					   </td>	
    					    </tr>					  
						</c:forEach>						
					</tbody>
				</table>
				<br/>
				<p class="pull-right"><fmt:message key="ContactUsToRegister"/></p>
				
			</div>
			
		</div>				
	</div>
	</c:if>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script> 