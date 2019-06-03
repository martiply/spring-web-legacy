<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle var="bi" basename="inventory"/>
<fmt:setBundle basename="store"/>

<script src="https://maps.googleapis.com/maps/api/js"></script>
<script>
function initialize() {
    var mapCanvas = document.getElementById('map-canvas');
    var storeLatlng = new google.maps.LatLng(<c:out value="${STORE.lat}" />, <c:out value="${STORE.lng}" />);

    var mapOptions = {
      center: storeLatlng,
      zoom: 19,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(mapCanvas, mapOptions);
    var marker = new google.maps.Marker({
        position: storeLatlng,
        map: map,
        title: '<c:out value="${name}" />'
    });

}
google.maps.event.addDomListener(window, 'load', initialize);				
</script>


<div id="map-canvas" style="height: 300px; width: 100%"></div>
<div class="panel panel-default">
	<div class="panel-heading">
			<c:out value="${STORE.name}" />	
	</div>

	<div class="panel-body">
		<div class="row pull-right">
			<a href="${pageContext.request.contextPath}/store/edit/${fn:escapeXml(STORE.storeId)}" class="btn btn-blue" role="button"><fmt:message key="EditThisStore"/></a>
		</div>
		
		
		<p class="text-primary"><c:out value="${STORE.address}" /></p>
		
		<p class="text-primary">
			<c:out value="${STORE.zip}" /> ,			
			<span>
				<c:choose>
				      <c:when test="${STORE.tz == 7}"><fmt:message key="Indonesia" bundle="${bd}"/></c:when>		
				      <c:otherwise><fmt:message key="Japan" bundle="${bd}"/></c:otherwise>
				</c:choose>
			</span>
		</p>
		
		<p class="text-primary"><c:out value="${STORE.phone}" /></p>
	
		<p class="text-primary"><i class="fa fa-clock-o"></i><c:out value="${STORE.open}" /> - <c:out value="${STORE.close}" /> </p>

		<br/>
		<c:forEach var="tag" items="${fn:split(STORE.tag,',')}">
		   <div class="label label-primary"><c:out value="${tag}"/></div>
		</c:forEach>
					
	</div>
</div>

<c:if test="${not empty STORE.story}">
	<div class="panel panel-default">
	
		<div class="panel-heading">
			<h3 class="panel-title"><fmt:message key="StoreIntroduction"/><br/></h3>
		</div>
		<div class="panel-body">
			<c:out value="${STORE.story}"/>		
		</div>
	</div>
</c:if>


<ul class="list-group">
  <li class="list-group-item active"><h4><fmt:message key="StorePictures"/></h4></li>
  <li class="list-group-item"><fmt:message key="StorePicturesInfo"/></li>
  <fmt:message key="GeneralImageInfo" bundle="${bd}">
	   	<fmt:param value="<ins><a href='${pageContext.request.contextPath}/tos'>"/>
	  	<fmt:param value="</a></ins>"></fmt:param>
  </fmt:message>
</ul>


<section class="gallery-env">			
	<div class="row">	
		<div class="col-md-9">
			<!-- Album Images -->
			<div class="album-images row">
				<c:forEach var="i" begin="0" end="1">
   					<div class="col-md-3">
						<div class="album-image">
						
							<div class="thumb" style="cursor:pointer" >
								<img class="pic-thumb" data-toggle="modal" data-target="#modal-upload" data-pic-n="${fn:escapeXml(i)}" id="pic-thumb-${fn:escapeXml(i)}" 								
								onerror='this.onerror = null; this.src="${pageContext.request.contextPath}/assets/images/album-img-1.png"'
								src='http://martiply.s3.amazonaws.com/store/${fn:escapeXml(STORE.storeId)}/${fn:escapeXml(i)}normal.jpg' style="min-height:325px; max-height:325px; max-width:325px; min-width:325px;" />
							</div>
							
							<span>
							<strong>
							<c:choose>
							      <c:when test="${i =='0'}"><fmt:message key="Primary"/></c:when>
							      <c:otherwise><fmt:message key="Alternative"/> <c:out value="${i}"/></c:otherwise>
							</c:choose>						
							</strong>
							</span>
	
	
							<div class="image-options pic-del" data-pic-n="${fn:escapeXml(i)}" style="cursor:pointer"  data-toggle="modal" data-target="#modal-delete">
								<i class="fa-trash"></i>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</section>


<div class="modal fade" id="modal-upload">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
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
<div class="modal fade" id="modal-delete">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<h4 class="modal-title"></h4>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-white modal-ask-delete-cancel" data-dismiss="modal"><fmt:message key="Cancel" bundle="${bd}"/></button>
					<button type="submit" id="exec-delete" data-loading-text="<i class='fa fa-spin fa-spinner'></i>" class="btn btn-danger"><fmt:message key="Delete" bundle="${bd}"/></button>				
				</div>
				
			</div>
		</div>
</div>

<script>
$(function(){
	var s = 1;
	var picN;
	$dropzone_filetable = $("#dropzone-filetable");
	
	dropzone = $("#advancedDropzone").dropzone({
			  	url: '<c:out value="${pageContext.servletContext.contextPath}"/>/imu',					        	 									
		 	    acceptedFiles: "image/*",
	 		    maxFilesize: 2, // MB
		  	    maxFiles: 1,
		  	    
				init: function() {
	
			     	var _this = this;
			     	
			        this.on("maxfilesexceeded", function(file) {
			            this.removeAllFiles();
			            this.addFile(file);
			     	 });
			     	
			     	$( ".pic-thumb" ).click(function(e) {
						$("#dropzone-filetable tr#dropzone-only-row").remove();
						_this.removeAllFiles();
						picN = $(this).data('pic-n');
					   	if (picN == 0){
					   		$('#modal-upload .modal-title').text('<fmt:message key="Primary"/>');
					   	}else{
					   		$('#modal-upload .modal-title').text('<fmt:message key="Alternative"/>' + " " + picN);
					   	}
					    _this.options.paramName = 's-${fn:escapeXml(STORE.storeId)}-' + picN;
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
						$( "#pic-thumb-" + picN ).attr('src', response + "?"+ new Date().getTime());
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
	
	var picNdel;
 	$( ".pic-del" ).click(function(e) {
		picNdel = $(this).data('pic-n');		
	   	if (picNdel == 0){	   		
	   		$('#modal-delete .modal-title').text('<fmt:message key="Primary"/>' + " " + '<fmt:message key="willBeDeleted" bundle="${bd}"/>');
	   	}else{
	   		$('#modal-delete .modal-title').text('<fmt:message key="Alternative" /> ' + picNdel + ' <fmt:message key="willBeDeleted" bundle="${bd}"/>');
	   	}
	   
	});
 	
 	$( "#exec-delete" ).click(function(e) {
	      var btn = $(this).button('loading');

	   	  $.post("${pageContext.servletContext.contextPath}/imd", {
	   	   	    a:"s-${fn:escapeXml(STORE.storeId)}-" + picNdel,
	   	   	  },
	   	   	  
	   	   	  function(data,status){
 	 		      btn.button('reset');	

	   	   		  if (data == 0){
	   	   			$('#pic-thumb-' + picNdel).attr("src", "${pageContext.request.contextPath}/assets/images/album-img-1.png");
	   	   		  }else if (data == -1){
					window.location.replace("${pageContext.request.contextPath}/login");
	   	   		  }else{
						toastr.error(data, null, opts);
	   	   		  }
 	   			  $('#modal-delete').modal('hide');
 	   	  });
	});

});
</script>

<c:choose>
	<c:when test="${empty LIST_ITEMS}">
	
	<div class="panel panel-default">
		<div class="panel-body">
			<p class="text-primary"><fmt:message key="NoItem"/></p>
			<p class="vertical-top">
				<a href="${pageContext.request.contextPath}/store/inventory/${fn:escapeXml(STORE.storeId)}" class="btn btn-blue" role="button"><fmt:message key="AssociateItems"/></a>
			</p>
		</div>
		
	</div>	
	
	</c:when>
    <c:otherwise>
    
		<a href="${pageContext.request.contextPath}/store/inventory/${fn:escapeXml(STORE.storeId)}" class="btn btn-blue" role="button"><fmt:message key="AssociateMoreItems"/></a>		
		<div class="panel panel-default">
			
			<div class="panel-body">
				<ul class="pull-right list-unstyled list-inline" >
	
					<li>
						<button id="open-modal-destroy" data-toggle="modal" class="btn btn-white btn-icon disabled" data-target="#modal-destroy">
							<i class="fa-scissors"></i>
							<fmt:message key="Remove" bundle="${bd}"/>
						</button>
						<script>						
						</script>
					</li>
				</ul>		
				<script>
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
												
					 	$("#exec-destroy").click(function(e){
					 		var btn = $(this).button('loading');					 		
					 		var dels = [];
															
					 		$(".cb-item:checked").each(function(){
			 				     dels.push($(this).val());
			 				});
					 							 		
					   	    $.post("${pageContext.servletContext.contextPath}/storeinv", 
					   			  	{				   	    	
					   	    		  a: "remove",
					   	   	  		  p: dels
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
		
			    <c:import url="fragment_inventoryList.jsp">
			    	<c:param  name="nameType" value="full"/>
			    </c:import>
		
			</div>
		</div>
		
	<div class="modal fade" id="modal-destroy">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<h4 class="modal-title"><fmt:message key="RemoveAsk"/></h4>
				</div>
				
				<div class="modal-body">
					<p class="text-primary"><fmt:message key="RemoveAskInfo1"/><p>					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white modal-ask-delete-cancel" data-dismiss="modal"><fmt:message key="Cancel" bundle="${bd}"/></button>
					<button type="button" id="exec-destroy" data-loading-text="<i class='fa fa-spin fa-spinner'></i>" class="btn btn-danger"><fmt:message key="Remove" bundle="${bd}"/></button>				
				</div>
				
			</div>
		</div>
	</div>

    </c:otherwise>
</c:choose>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.js"></script>
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>
