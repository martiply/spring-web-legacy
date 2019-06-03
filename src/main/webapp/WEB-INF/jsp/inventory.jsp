<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>

<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="inventory"/>

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
	    	$('#open-modal-destroy').removeClass('disabled');
	    	$('#csv-download').removeClass('disabled');
	    }else{
	    	$('#open-modal-destroy').addClass('disabled');
	    	$('#csv-download').addClass('disabled');
	    }
	});
	
	$("#csv-download").click(function(e){
 		var btn = $(this).button('loading');
 		var checks = [];
		
 		$(".cb-item:checked").each(function(){
 			checks.push($(this).val());
		});
 		
   	    $.post("${pageContext.servletContext.contextPath}/getcsv", 
 			  	{
 	   	  		  p: checks
 	   	  		},	   	   	 	 
   	   	   function(data,status){
	 		  btn.button('reset');		   	 		    	  
   	   		  if (data == 0){
   	   				window.location = "${pageContext.request.contextPath}/getcsv";
   	   		  }else if (data == -1){
					window.location.replace("${pageContext.request.contextPath}/login");
   	   		  }else{
					toastr.error(data, null, opts);
   	   		  }
	   	  	}
 		 );			
	});
	
 	$("#exec-destroy").click(function(e){
 		var btn = $(this).button('loading');	 		
 		var dels = [];
										
 		$(".cb-item:checked").each(function(){
			     dels.push($(this).val());
		});
 							 		
   	    $.post("${pageContext.servletContext.contextPath}/idel", 
   		   {
   	   	  	 a: dels
   	   	   },
   	   	 	 
   	   	   function(data,status){
	   	 	  btn.button('reset');		   	 		    	  
   	   		  if (data == 0){
   	   				window.location.replace("${pageContext.request.contextPath}/inventory");
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


<c:choose>
	<c:when test="${empty LIST_ITEMS}">		
		<div class="panel panel-default">
			<div class="panel-body">
				<p class="text-primary"><fmt:message key="YourInventoryIsEmpty"/></p>
				<p class="vertical-top">
					<a href="inventory/preupload" class="btn btn-blue" role="button"><fmt:message key="StartAddItem"/></a>
				</p>
			</div>			
		</div>		
	</c:when>
	
    <c:otherwise>
		<a href="${pageContext.request.contextPath}/inventory/preupload" class="btn btn-blue" role="button"><fmt:message key="AddMoreItems"/></a>	
		<div class="panel panel-default">		
			<div class="panel-body">
				<ul class="pull-right list-unstyled list-inline" >
					<li>
						<button id="csv-download" class="btn btn-white btn-icon disabled" data-loading-text="<i class='fa fa-spin fa-spinner'></i>" data-action="edit">
							<i class="fa-download"></i>
							<fmt:message key="Download"/>
						</button>
					</li>
					<li>
						<button id="open-modal-destroy" data-toggle="modal" class="btn btn-white btn-icon disabled" data-target="#modal-destroy">
							<i class="fa-trash"></i>
							<fmt:message key="Delete" bundle="${bd}"/>
						</button>
					</li>
				</ul>					

			    <c:import url="fragment_inventoryList.jsp">
			    	<c:param  name="nameType" value="partial"/>
			    </c:import>		
			</div>
		</div>
		<div class="modal fade" id="modal-destroy">
			<div class="modal-dialog">
				<div class="modal-content">					
					<div class="modal-header">
						<h4 class="modal-title"><fmt:message key="DeleteItemAsk"/></h4>
						<p class="text-primary"><fmt:message key="DeleteItemAskInfo"/><p>
					</div>					
					<div class="modal-footer">
						<button type="button" class="btn btn-white modal-ask-delete-cancel" data-dismiss="modal">Cancel</button>
						<button type="button" id="exec-destroy" data-loading-text="<i class='fa fa-spin fa-spinner'></i>" class="btn btn-danger"><fmt:message key="Delete" bundle="${bd}"/></button>				
					</div>
					
				</div>
			</div>
		</div>		
    </c:otherwise>
</c:choose>


<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.css">
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>