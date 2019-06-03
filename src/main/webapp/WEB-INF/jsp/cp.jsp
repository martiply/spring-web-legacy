<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="coupon"/>

<a href="${pageContext.request.contextPath}/cp/choose" class="btn btn-blue"><fmt:message key="CreateNewCampaign"/></a>

<div class="row">
	<div class="col-md-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<fmt:message key="PromoteYourBusiness"/>
			</div>
			<div class="panel-body">
				<fmt:message key="InfoA"/>				
			</div>
		</div>
	
	</div>
	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<fmt:message key="Credit"/>
			</div>
			<div class="panel-body">
				<p><a class="text-primary"><c:out value="${sessionScope.SESSION_PRETTY_CURRENCY}"/><c:out value="${sessionScope.SESSION_CREDIT}"/></a><p>
				
				<p class="text-primary"><fmt:message key="AddCredit"/></p>							
			</div>
		</div>
	</div>
</div>

<c:if test="${not empty LIST_COUPONS}">

<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<fmt:message key="ActiveCampaigns"/>
			</div>
			<div class="panel-body">
					<table class="table responsive">
							<thead>
								<tr>
									<th class="col-md-1"></th>
									<th class="col-md-2">ID</th>
									<th class="col-md-3"><fmt:message key="Content"/></th>
									<th class="col-md-1"><fmt:message key="ValidUntil"/></th>
									<th class="col-md-1"><fmt:message key="Clicks"/></th>
									<th class="col-md-1"><fmt:message key="Bid"/>(<c:out value="${sessionScope.SESSION_PRETTY_CURRENCY}"/>)</th>		
									<th class="col-md-3"><fmt:message key="Status"/></th>								
								</tr>
							</thead>
							<tbody>
							
							
							<c:forEach var="cp"  items="${LIST_COUPONS}">
								<script>
								$(function() {
									$( ".end-moment" ).each(function( index ) {
										var prettyDate = moment.unix($(this).data('ts')).format("MMM DD");
										$(this).text(prettyDate);										
									});									
								})
								
								</script>
   								<tr>
			 						 <td style="text-align:left;">
			 						 <div class="thumb" style="cursor:pointer" >
										<img data-toggle="modal" data-target="#gallery-image-modal" class="pic-thumb" src='http://martiply.s3.amazonaws.com/coupon/${fn:escapeXml(cp.cpId)}/xsmall.jpg' style="min-height:60px; max-height:60px; max-width:60px; min-width:60px" />
									 </div>
									 </td>
									 <td><a href="${pageContext.request.contextPath}/cp/view/${fn:escapeXml(cp.cpId)}"><c:out value="${cp.cpId}"/></a></td>
									 <td><c:out value="${cp.content}"/></td>
									 
									 <td class="end-moment" data-ts="${fn:escapeXml(cp.endSec)}"></td>
									 <td><c:out value="${cp.numShares}"/></td>
									 <td><a href="#modal-bid" data-toggle="modal" data-tweet-id="${fn:escapeXml(cp.cpId)}" class="open-bid"><c:out value="${cp.bid}"/></a></td>
									 <td><c:out value="${cp.status}"/></td>

								</tr>
							</c:forEach>															
							</tbody>
					</table>
			</div>			
		</div>
</div>
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

	$('#gallery-image-modal').on('show.bs.modal', function(e) {
	
	    var src = $(e.relatedTarget).attr('src');
	    src = src.replace(/xsmall/g, "large"); 
	    $("#img-big").attr("src", src);
	
	});
	
	
	$( ".open-bid" ).click(function(e) {	
		$('#new-bid-tweet-id').val($( this ).data('tweet-id'));
		
	})
	
	$( "#submit-bid" ).click(function() {		
 		var btn = $(this).button('loading');
 		
   	    $.post("${pageContext.servletContext.contextPath}/acbid", 
   			  	{
   	   	  		  a: $('#new-bid-tweet-id').val(),
   	   	  		  i: $('#bid').val(),
   	   	  		},
   	   	 	 
   	   	   function(data,status){
	 		      btn.button('reset');	
	 		    	  
   	   		  if (data == 0){
   	   				location.reload();
   	   		  }else if (data == -1){
					window.location.replace("${pageContext.request.contextPath}/login");
   	   		  }else if (data == -2){
					toastr.error("Bid must be higher than default", null, opts);
   	   		  }else {
 	   				toastr.error(data, null, opts);
   	   		  }
   	  		}
		 );
	});
})
</script>
							
<div class="modal fade" id="modal-bid">
	<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><fmt:message key="ChangeBidAmount"/></h4>
				</div>
				
				<div class="modal-body">											
						<div id="spinnerBid" class="input-group spinner col-sm-12"  data-min="${sessionScope.SESSION_DEFAULT_BID}" data-max="999999" data-step="${sessionScope.SESSION_BID_INCREMENT_STRING}">
							
							<span class="input-group-addon"><c:out value='${sessionScope.SESSION_PRETTY_CURRENCY}'/></span>
							
							<span class="input-group-btn">
								<button class="btn btn-info btn-single" data-type="decrement">-</button>
							</span>
							<input id="bid" class="form-control text-center no-left-border" value="${sessionScope.SESSION_DEFAULT_BID}" readonly="" type="text">
							<span class="input-group-btn">
								<button class="btn btn-info btn-single" data-type="increment">+</button>
							</span>
						</div>
				</div>
									
				<div class="modal-footer">
					<input type="hidden" id="new-bid-tweet-id">
				
					<button class="btn btn-blue" id="submit-bid" data-loading-text="<i class='fa fa-spin fa-spinner'>"><fmt:message key="OK"/></button>
				</div>								
			</div>
	</div>
</div>

<div class="modal fade" id="gallery-image-modal"><div style="height: 100%;" class="modal-backdrop fade in"></div>
		<div class="modal-dialog"  style="min-height:500px; max-height:500px; max-width:500px; min-width:500px" >
			<div class="modal-content">
				
				<div class="modal-gallery-image">
					<img id="img-big" src="assets/images/album-image-full.jpg" style="min-height:500px; max-height:500px; max-width:500px; min-width:500px">
				</div>
				
				<div class="modal-footer modal-gallery-top-controls">
					<button class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
</div>
	
</c:if>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>