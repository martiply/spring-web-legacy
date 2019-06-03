<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="store"/>

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

$(function(){				
					
	$(".cb-item").change(function(){
	    if ($('.cb-item:checked').length > 0) {
	    	$('#items-associate').removeClass('disabled');
	    }else{
	    	$('#items-associate').addClass('disabled');
	    }
	});
	
	$("#items-associate").click(function(e){
 		var btn = $(this).button('loading');
 		var checks = [];
		
 		$(".cb-item:checked").each(function(){
 			checks.push($(this).val());
		});
 		
 		
   	    $.post("${pageContext.servletContext.contextPath}/storeinv", 
  			  	{
  	    		  a: 'add',
  	   	  		  p: checks
  	   	  		},
   	   	 	 
   	   	   function(data,status){
	 		      btn.button('reset');		   	 		    	  
   	   		  if (data == 0){
   	   				location.reload(true);
   	   		  }else if (data == -1){
					window.location.replace("${pageContext.request.contextPath}/login");
   	   		  }else{
					toastr.error(data, null, opts);
   	   		  }

	   	  		}
 			 );
		
	});
});
</script>
<a class="btn btn-blue" href="${pageContext.request.contextPath}/store/${fn:escapeXml(storeId)}"><fmt:message key="BackToStore"/></a>

<c:choose>
	<c:when test="${empty LIST_ITEMS}">	
	<div class="panel panel-default">
		<div class="panel-body">
			<fmt:message key="NoItemInfo">
				<fmt:param value="<a href='${pageContext.request.contextPath}/inventory'><ins>"/><fmt:param value="</ins></a>"/>
			</fmt:message>					
		</div>		
	</div>		
	</c:when>
    <c:otherwise>
		<div class="panel panel-default">
			
			<div class="panel-body">
				<ul class="pull-right list-unstyled list-inline" >	
					<li>
						<button id="items-associate" class="btn btn-white btn-icon disabled" data-loading-text="<i class='fa fa-spin fa-spinner'></i>" data-action="edit">
							<i class="fa-download"></i>
							<fmt:message key="AssociateItems"/>
						</button>
					</li>
				</ul>				
			    <c:import url="fragment_inventoryList.jsp">
			    	<c:param  name="nameType" value="full"/>
			    </c:import>
		
			</div>
		</div>

    </c:otherwise>
</c:choose>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.css">
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>