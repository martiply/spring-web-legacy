<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="coupon"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

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
    $('#endDp').datepicker({
        format: '<fmt:message key="DateFormat"/>',
        startDate: '+1d',
        endDate: '+45d'
    }); 
    
    $('#endDp').datepicker().on('changeDate', function(ev){   	
    	var ms = moment($(this).datepicker('getUTCDate')).unix();
        $('#end').val(ms);
   }); 
});
</script>

	
<div class="panel panel-default">	
	<div class="panel-body">
		
		<div class="form-horizontal">		
		
			<c:if test="${CPID != '0'}">
	   			<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="TweetID"/></label>
					
					<div class="col-md-4">									
						<input id="cpId" name="cpId" class="form-control" placeholder="${fn:escapeXml(CPID)}" disabled="" type="text" value="${fn:escapeXml(CPID)}">
					</div>															
				</div>	
				<br/>
			</c:if>
																		
			<div class="form-group">									
				<label class="col-md-1  control-label"><fmt:message key="Content"/></label>
				
				<div class="col-md-4">
					<div class="well">
						<c:out value="${CONTENT}"/>
					</div>					
					<input type="hidden" id="content" value="${CONTENT}">
				</div>															
			</div>	
			<br/>
			
			<div class="form-group">
				<label class="col-md-1 control-label"><fmt:message key="ValidUntil"/></label>									
				<div class="col-md-4">
					<input type="text" id="endDp" name="endDp" class="form-control" data-date-format="mm-dd-yyyy">
					<input type="hidden" id="end" name="end">
					<p id="endError" class="text-danger"></p>
				</div>
			</div>
			<br/>
			<div class="form-group">
				<label class="col-md-1 control-label"><fmt:message key="Terms"/></label>
		
				<div class="row">
				<div class="col-md-4">
					<textarea class="form-control" id="terms" name="terms" rows="3" style="resize: none;"><fmt:message key="TermsTemplate"/></textarea>	
					<p id="termsError" class="text-danger"></p>
										
				</div>
					<div class="col-md-3">
						<p><fmt:message key="TermsInfo"/></p>
					</div>
				</div>
			</div>
			<br/>
			<div class="form-group">
				<label class="col-md-1 control-label"><fmt:message key="Bid"/></label>
				
				<div class="row">
					<div class="col-md-4">
					
						<div class="input-group">
								<span class="input-group-addon"><c:out value='${sessionScope.SESSION_PRETTY_CURRENCY}'/></span>
							
								<div id="spinnerBid" class="input-group spinner col-sm-12"  data-min="${sessionScope.SESSION_DEFAULT_BID}" data-max="999999" data-step="${sessionScope.SESSION_BID_INCREMENT_STRING}">
																	
									<span class="input-group-btn">
										<button class="btn btn-info btn-single" data-type="decrement">-</button>
									</span>
									<input id="bid" class="form-control text-center no-left-border" value="${sessionScope.SESSION_DEFAULT_BID}" readonly="" type="text">
									<span class="input-group-btn">
										<button class="btn btn-info btn-single" data-type="increment">+</button>
									</span>
								</div>
						</div>
						
					</div>
						<p><fmt:message key="SeeRules"><fmt:param value="<a data-toggle='modal' href='#modal-bid-rules'>"/><fmt:param value="</a>"/></fmt:message></p>
				</div>
			</div>
			<br/>			
			<div class="form-group">
				<label class="col-md-1 control-label"><fmt:message key="Image"/></label>
		
				<div class="row">
				<div class="col-md-4">
					<div class="album-image">
						<div class="thumb" style="cursor:pointer" >
							<img class="pic-thumb" data-toggle="modal" data-target="#modal-upload" id="pic-thumb" 
							src='${pageContext.request.contextPath}/assets/images/album-img-1.png' 
							style="min-height:325px; max-height:325px; max-width:325px; min-width:325px;" />
						</div>
						<p id="picError" class="text-danger"></p>
						
					</div>					
				</div>
						<p><fmt:message key="SeeRules"><fmt:param value="<a data-toggle='modal' href='#modal-img-rules'>"/><fmt:param value="</a>"/></fmt:message></p>
				</div>
			</div>
			<br/>
			<div class="form-group">
				<label class="col-md-1 control-label"><fmt:message key="StoreThisOfferApplies"/></label>		
				<div class="row">
					<div class="col-md-7">
						<c:set var="stores" scope="session" value="${sessionScope.SESSION_AVAILABLE_STORES}"/>
						<p id="storeError" class="text-danger"></p>
						<table class="table responsive">
								<thead>
										<tr>
											<th>#</th>
											<th>ID</th>
											<th><fmt:message key="Name"/></th>
											<th><fmt:message key="Address"/></th>
										</tr>
								</thead>
								<tbody>
									 <c:forEach items="${stores}" var="store">
								
									<tr>
										 <td>
										 <div style="position: relative;" class="icheckbox_square-blue">
			       						 <input class="icheck" checked="" type="checkbox" value="${store.storeId}">
			       						 </div>
			       						 </td>
										 <td><c:out value="${store.storeId}"/></td>
										 <td><c:out value="${store.name}"/></td>
										 <td><c:out value="${store.address}"/></td>
									</tr>
									
									</c:forEach>
								</tbody>
						</table>
					
										
					<script>
						$(function(){			
							$('input.icheck').iCheck({
								checkboxClass: 'icheckbox_square-blue',
								radioClass: 'iradio_square-yellow'
							});		
						});
					</script>
					</div>
				</div>
			</div>
			
			<button id="create" class="btn btn-success col-md-offset-1"><fmt:message key="Verify"/></button>
		</div>
	</div>			
</div>
<script>
var stores = [];
$(function() {
	
	$("#create").click(function() {		
		$('#endError').text("");		
		$('#termsError').text("");		
		$('#picError').text("");

		var isError;
		if ($('#end').val().length == 0){
			$('#endError').text('<fmt:message key="Required"/>');
			isError = true;
		}
				
		if ($('#terms').val().length == 0){
			$('#termsError').text('<fmt:message key="Required"/>');
			isError = true;
		}else if($('#terms').val().length > 500){
			$('#termsError').text('<fmt:message key="ErrorText500"/>');
			isError = true;
		}
		
		if ($('#pic-thumb').attr('src').indexOf("temp-coupon-twitter") == -1){
			$('#picError').text('<fmt:message key="ImageRequired"/>');
			isError = true;			
		}
		
 		stores = [];
		
 		$(".icheck:checked").each(function(){
		     stores.push($(this).val());

		});
 		
	    if (stores.length == 0){
	    	 $('#storeError').text('<fmt:message key="ImageRequired"/>');
	    	 isError = true;		    	 
	    }	
		
		if (isError){
			return;
		}
		
		$('#modal-confirm').modal('show'); 
		
	});
	
	
	$("#submit").click(function() {
 		var btn = $(this).button('loading');

   	    $.post("${pageContext.servletContext.contextPath}/cp/new", 
   			  	{
   	   	  		  cpId: $('#cpId').val(),
   	   	  		  content: $('#content').val(),
   	   	  		  end: $('#end').val(),
   	   	  		  terms: $('#terms').val(),
   	   	  		  bid: $('#bid').val(),
   	   	  		  img: $('#pic-thumb').attr('src'),
   	   	  		  a:stores
   	   	  		},
   	   	 	 
	   	   	   function(data,status){
	 		      btn.button('reset');	
		 		    	  
	   	   		  if (data == 0){
	   	   				window.location.replace("${pageContext.request.contextPath}/cp");
	   	   		  }else if (data == -1){
						window.location.replace("${pageContext.request.contextPath}/login");
	   	   		  }else{
						toastr.error(data, null, opts);
	   	   		  }
	   	  		}
			 );
	});			
})
</script>



<div class="modal fade" id="modal-confirm">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><fmt:message key="Disclaimer"/></h4>
				</div>
				
				<div class="modal-body">			
					<p class="text-primary"><fmt:message key="DisclaimerPreSubmit1" /></p>
				</div>
				
				<div class="modal-header">
					<h4 class="modal-title"><fmt:message key="Obligations"/></h4>
				</div>
				
				<div class="modal-body">			
					<p class="text-primary"><fmt:message key="ObligationsInfo" /></p>
					<p class="text-primary"><fmt:message key="FinalNotice"/></p>
					
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-blue" id="submit" data-loading-text="<i class='fa fa-spin fa-spinner'>"><fmt:message key="Submit" bundle="${bd}"/></button>
				</div>							
			</div>
		</div>
</div>

<div class="modal fade" id="modal-img-rules">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><fmt:message key="ImageRules"/></h4>
				</div>
			<ul class="list-group">
			  <li class="list-group-item "><fmt:message key="ImageRule1"/></li>
			  <fmt:message key="GeneralImageInfo" bundle="${bd}">
				   	<fmt:param value="<ins><a href='${pageContext.request.contextPath}/tos'>"/>
				  	<fmt:param value="</a></ins>"></fmt:param>
			  </fmt:message>
			</ul>
			</div>
		</div>
</div>

<div class="modal fade" id="modal-bid-rules">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><fmt:message key="FeeTitle"/></h4>
				</div>
				
				<div class="modal-body">				
					<fmt:message key="FeeRule" />
				</div>
			</div>
		</div>
</div>
	
<div class="modal fade" id="modal-upload">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><fmt:message key="PromotionalPicture"/></h4>
				</div>
				
				<div class="modal-body">
			
					<div class="row">
						<div class="col-sm-4 col-md-offset-4 text-center">							
							<div id="advancedDropzone" class="droppable-area">
								<fmt:message key="DropImageHere" bundle="${bd}"/>
							</div>								
						</div>
					</div>
					<div class="row">									
						<table class="table table-bordered table-striped" id="dropzone-filetable">
							<tbody>
	
							</tbody>
						</table>
							
					</div>
				</div>
			</div>
		</div>
</div>

<script>
$(function(){	
	var s = 1;
	$dropzone_filetable = $("#dropzone-filetable");
	
	dropzone = $("#advancedDropzone").dropzone({
			  	url: '<c:out value="${pageContext.servletContext.contextPath}"/>/imu',					        	 									
		 	    acceptedFiles: "image/jpeg",
	 		    maxFilesize: 2, // MB
		  	    maxFiles: 1,
		  		paramName: 'temp-coupon-twitter',
		  	    
				init: function() {
	
			     	var _this = this;
			     	
			        this.on("maxfilesexceeded", function(file) {
			            this.removeAllFiles();
			            this.addFile(file);
			     	 });
			     	
			     	$( ".pic-thumb" ).click(function(e) {
						$("#dropzone-filetable tr#dropzone-only-row").remove();
						_this.removeAllFiles();

		     		});
				},
				
				addedfile: function(file)
				{
					if(s == 1)
					{
						$dropzone_filetable.find('tbody').html('');
					}
					
					var size = parseInt(file.size/1024, 10);
					size = size < 1024 ? (size + " KB") : (parseInt(size/1024, 10) + " MB");
					
					var $entry = $('<tr id="dropzone-only-row">\
									<td>'+file.name+'</td>\
									<td><div class="progress progress-striped"><div class="progress-bar progress-bar-warning"></div></div></td>\
									<td>'+size+'</td>\
									<td>Uploading...</td>\
								</tr>');
					
					$dropzone_filetable.find('tbody').append($entry);
					file.fileEntryTd = $entry;
					file.progressBar = $entry.find('.progress-bar');
				},
				
				uploadprogress: function(file, progress, bytesSent)
				{
					file.progressBar.width(progress + '%');
				},
				
				success: function(file, response)
				{
					if (response.indexOf("http") > -1){
						file.fileEntryTd.find('td:last').html('<span class="text-success">Uploaded</span>');
						$( "#pic-thumb" ).attr('src', response + "?"+ new Date().getTime());
		   	   			$('#modal-upload').modal('hide');
	
					}else if (response == -1){
						window.location.replace("${pageContext.request.contextPath}/login");
					}else {
						file.fileEntryTd.find('td:last').html('<span class="text-danger">'+ response +'</span>');

					}
					file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');
				},
				
				error: function(file)
				{
					file.fileEntryTd.find('td:last').html('<span class="text-danger">Failed</span>');
					file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
				}
	});
	
	$("#advancedDropzone").css({
		minHeight: 200
	});	
});
</script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.1/skins/all.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.js"></script>                                   
<script src="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.1/icheck.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.1.62/jquery.inputmask.bundle.min.js"></script>
