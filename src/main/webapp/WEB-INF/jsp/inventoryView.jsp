<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle var="bi" basename="inventory"/>
<fmt:setBundle basename="inventoryView"/>

<div class="row">
<div class="col-md-12">
	<a href="${pageContext.request.contextPath}/inventory/edit/${fn:escapeXml(ITEM.id)}" class="btn btn-blue"><fmt:message key="EditItem"/></a>
	<button data-toggle="modal" data-target="#modal-destroy"  class="btn btn-danger pull-right"><fmt:message key="DeleteItem"/></button>
</div>
</div>

<div class="panel panel-default">
	<div class="panel-heading">		
		<fmt:message key="BasicInfo"/>
	</div>
	
	<table class="table">
		<thead>
			<tr>
				<th class="middle-align col-md-1"><fmt:message key="Name" bundle="${bi}"/></th>
				<th class="col-md-11"><c:out value="${ITEM.name}"/></th>
			</tr>
		</thead>

		<tbody>

			<tr>
				<td class="middle-align col-md-1"><fmt:message key="GTIN" bundle="${bi}" /></td>
				<td class="col-md-11">
				<c:choose>
				      <c:when test="${ITEM.idType=='gtin'}"><c:out value="${ ITEM.gtin }"/></c:when>		
				      <c:otherwise><fmt:message key="NoGTIN"/></c:otherwise>
				</c:choose>
				</td>
			</tr>
			
			<tr>
				<td class="middle-align"><fmt:message key="CustomID" bundle="${bi}"/></td>
				<td><c:out value="${ ITEM.idCustom }"/></td>
			</tr>
			<tr>
				<td class="middle-align"><fmt:message key="Brand" bundle="${bi}"/></td>
				<td><c:out value="${ ITEM.brand }"/></td>
			</tr>			
			<tr>
				<td class="middle-align"><fmt:message key="Condition" bundle="${bi}"/></td>
				<td><c:out value="${ ITEM.condition }"/></td>
			</tr>
			<tr>
				<td class="middle-align"><fmt:message key="Category" bundle="${bi}"/></td>
				<td><c:out value="${ ITEM.category }"/></td>
			</tr>
			<tr>
				<td class="middle-align"><fmt:message key="Price" bundle="${bi}"/></td>
				<td><c:out value="${sessionScope.SESSION_PRETTY_CURRENCY}"/><c:out value="${ ITEM.price }"/></td>
			</tr>				
			<tr>
				<td class="middle-align"><fmt:message key="Description" bundle="${bi}"/></td>
				<td><c:out value="${ ITEM.description }"/></td>
			</tr>
			
		</tbody>
</table>
					
</div>

<script>
$(function(){
	$('#saleStart').text(moment.unix('${fn:escapeXml(ITEM.saleStart)}').format('<fmt:message key="DateFormat"/>'));
	$('#saleEnd').text(moment.unix('${fn:escapeXml(ITEM.saleEnd)}').format('<fmt:message key="DateFormat"/>'));		
});
</script>

<c:if test="${ITEM.saleStart gt 0}">
<div class="panel panel-default">
	<div class="panel-heading">
		<fmt:message key="Sale"/>
	</div>
	<table class="table">
		<tbody>	
			<tr>
				<td class="middle-align col-md-1"><fmt:message key="SalePrice" bundle="${bi}"/></td>
				<td class="col-md-11"><c:out value="${ ITEM.salePrice }"/></td>
			</tr>
			<tr>
				<td class="middle-align"><fmt:message key="SaleStart"/></td>
				<td id="saleStart"><c:out value="${ ITEM.saleStart }"/></td>
			</tr>	
			<tr>
				<td class="middle-align"><fmt:message key="SaleEnd"/></td>
				<td id="saleEnd"><c:out value="${ ITEM.saleEnd }"/></td>
			</tr>					
		</tbody>
	</table>
</div>
</c:if>
	

<c:if test="${not empty ITEM.apparelExtension}">
	<div class="panel panel-default">
		<div class="panel-heading">
			<fmt:message key="ApparelSpecifics"/>
		</div>
	
		<table class="table">	
			<tbody>	
				<tr>
					<td class="middle-align col-md-1"><fmt:message key="Gender" bundle="${bi}"/></td>
					<td class="col-md-11"><c:out value="${ ITEM.apparelExtension.gender }"/></td>
				</tr>			
				<tr>
					<td class="middle-align"><fmt:message key="Age" bundle="${bi}"/></td>
					<td><c:out value="${ ITEM.apparelExtension.age }"/></td>
				</tr>
				<tr>
					<td class="middle-align"><fmt:message key="SizeSystem" bundle="${bi}"/></td>
					<td><c:out value="${ ITEM.apparelExtension.sizeSystem }"/></td>
				</tr>
				<tr>
					<td class="middle-align"><fmt:message key="Size" bundle="${bi}"/></td>
					<td><c:out value="${ ITEM.apparelExtension.size }"/></td>
				</tr>
				<tr>
					<td class="middle-align"><fmt:message key="Color" bundle="${bi}"/></td>
					<td><c:out value="${ ITEM.apparelExtension.color }"/></td>
				</tr>
				<tr>
					<td class="middle-align"><fmt:message key="Feature" bundle="${bi}"/></td>
					<td><c:out value="${ ITEM.apparelExtension.feature }"/></td>
				</tr>
				<tr>
					<td class="middle-align"><fmt:message key="Size" bundle="${bi}"/></td>
					<td><c:out value="${ ITEM.apparelExtension.material }"/></td>
				</tr>
				<tr>
					<td class="middle-align"><fmt:message key="GroupID" bundle="${bi}"/></td>
					<td><c:out value="${ ITEM.apparelExtension.groupId }"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</c:if>

<ul class="list-group">
  <li class="list-group-item active"><h4><fmt:message key="ItemImages"/></h4></li>
  <fmt:message key="PicturesInfo"/>
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
				<c:forEach var="i" begin="0" end="7">
   					<div class="col-md-3 col-sm-4 col-xs-6">
						<div class="album-image">
							<div class="thumb" style="cursor:pointer" >
								<img class="pic-thumb" data-toggle="modal" data-target="#modal-upload" data-pic-n="${fn:escapeXml(i)}" id="pic-thumb-${fn:escapeXml(i)}" 
								
								onerror='this.onerror = null; this.src="${pageContext.request.contextPath}/assets/images/album-img-1.png"'
								src='http://martiply.s3.amazonaws.com/i/${fn:escapeXml(ITEM.id)}/${fn:escapeXml(i)}normal.jpg' style="min-height:220px; max-height:220px; max-width:100%;" />
							</div>
	
							<span><strong>
							<c:choose>
							      <c:when test="${i =='0'}"><fmt:message key="PrimaryImage"/></c:when>
							      <c:otherwise><fmt:message key="Alternative"/><c:out value="${i}"/></c:otherwise>
							</c:choose>						
							</strong></span>
	
	
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
					   		$('#modal-upload .modal-title').text('<fmt:message key="PrimaryImage"/>');
					   	}else{
					   		$('#modal-upload .modal-title').text('<fmt:message key="Alternative"/>' + " " + picN);
					   	}
					    _this.options.paramName = 'i-${fn:escapeXml(ITEM.id)}-' + picN;
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

					}else{
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
	   		$('#modal-delete .modal-title').text('<fmt:message key="PrimaryImage"/>' + ' ' + '<fmt:message key="willBeDeleted" bundle="${bd}"/>');
	   	}else{
	   		$('#modal-delete .modal-title').text('<fmt:message key="Alternative"/>' + " " + picNdel + ' ' + '<fmt:message key="willBeDeleted" bundle="${bd}"/>');
	   	}
	   
	});
 	 	
 	$("#exec-delete" ).click(function(e) {
 	   	  $.post("${pageContext.servletContext.contextPath}/imd", {
 	   	   	    a:"i-${fn:escapeXml(ITEM.id)}-" + picNdel
 	   	   	  },
 	   	   	  
 	   	   	  function(data,status){
 	   	   		  if (data == 0){
 	   	   			$('#pic-thumb-' + picNdel).attr("src", "${pageContext.request.contextPath}/assets/images/album-img-1.png");
 	   	   		  }else{
					toastr.error(data, null, opts);
 	   	   		  }
   	   			  $('#modal-delete').modal('hide');
   	   	  });
 	});
 	
 	$("#exec-destroy").click(function(e){ 		
	   	  $.post("${pageContext.servletContext.contextPath}/idel", 
	   			  	{
	   	   	  		  a:[<c:choose>
						      <c:when test="${ITEM.idType=='gtin'}">value='%d_${fn:escapeXml(ITEM.gtin)}_${fn:escapeXml(ITEM.condition)}'</c:when>
								
						      <c:otherwise>value='%d_${fn:escapeXml(ITEM.brand)}_${fn:escapeXml(ITEM.name)}_${fn:escapeXml(ITEM.condition)}'</c:otherwise>
						</c:choose>
						]
	   	   	  		},
	   	   	 	 
	   	   	  function(data,status){
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
					<button type="submit" id="exec-delete" class="btn btn-danger"><fmt:message key="Delete" bundle="${bd}"/></button>				
				</div>
				
			</div>
		</div>
</div>

<div class="modal fade" id="modal-destroy">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<h4 class="modal-title"><fmt:message key="DeleteItemAsk" bundle="${bi}"/></h4>
					<p class="text-primary"><fmt:message key="DeleteItemAskInfo" bundle="${bi}"/><p>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-white modal-ask-delete-cancel" data-dismiss="modal">Cancel</button>
					<button type="button" id="exec-destroy" class="btn btn-danger">Delete</button>				
				</div>
				
			</div>
		</div>

</div>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.js"></script>