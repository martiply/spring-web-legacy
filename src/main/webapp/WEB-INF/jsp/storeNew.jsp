<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel panel-default">
			
				<div class="panel-heading">
					<div class="panel-title">
						Input validation
					</div>
				</div>
				
				<div class="panel-body">
				
					<form role="form" id="form1" method="post" class="validate"  action="${pageContext.request.contextPath}/asu">
						
						<div class="form-group">
							<div class="row" >
								<div class="col-md-4">
									<label class="control-label">Store name</label>
							
									<input type="text" class="form-control" name="name" data-validate="required,minlength[3],maxlength[30]" data-message-required="Required. Must be between 3 to 30 characters." placeholder="" />
								</div>
							</div>
						</div>
					
						
						<div class="form-group">
							<div class="row">								
								<div class="col-md-4">
									<label class="control-label">Address</label>
									<input type="text" class="form-control" name="address" data-validate="required,minlength[4],maxlength[30]" data-message-required="Required. Must be between 3 to 30 characters." placeholder="" />								
								</div>
							</div>							
						</div>	
						
						<div class="form-group">
							
							<div class="row">
							
								<div class="col-md-1">
									<label class="control-label">Phone</label>								
									<input type="text" class="form-control" name="phone" data-validate="required,number,maxlength[12]" data-message-required="Required" placeholder="" />																
								</div>
								
								<div class="col-md-1">
									<label class="control-label">Zip</label>
									<input type="text" class="form-control" name="zip" data-validate="required,maxlength[10]" data-message-required="Required" placeholder="" />																
								</div>
														
								<div class="col-md-2">							
									<label class="control-label">Country</label>	

									<select class="form-control" id="country" name="tz">
										<option value="7">Indonesia</option>
										<option value="9">Japan</option>
									</select>										
								</div>

							</div>
							
						</div>		
						
						<div class="form-group">
								<div class="row">
									<div class="col-md-2">								
										<label class="control-label">Opening time</label>
										<div class="input-group input-group-minimal">
										
											<div class="input-group-addon">
												<a href="#"><i class="fa-clock-o"></i></a>
											</div>
											<input type="text" class="form-control timepicker" name="open" data-validate="required" data-template="dropdown" data-show-seconds="false" data-default-time="09:30" data-show-meridian="false" data-minute-step="5" data-second-step="1" />
											

										</div>
									</div>
									
									<div class="col-md-2">								
										<label class="control-label">Closing time</label>
										<div class="input-group input-group-minimal">
										
											<div class="input-group-addon">
												<a href="#"><i class="fa-clock-o"></i></a>
											</div>
											<input type="text" class="form-control timepicker" name="close" data-validate="required" data-template="dropdown" data-show-seconds="false" data-default-time="20:00" data-show-meridian="false" data-minute-step="5" data-second-step="1" />
											

										</div>
									</div>
								</div>										
						</div>
						
						
						<div class="form-group">
								<div class="row">									
									<div class="col-md-2">		
										<label class="control-label">Latitude</label>
														
										<div class="input-group input-group-sm input-group-minimal">
											<span class="input-group-addon">
												<i class="fa-arrows"></i>
											</span>
											<input type="text" class="form-control" data-validate="required,number" id="lat" name="lat"  />
										</div>
									</div>
									

									
									<div class="col-md-2">		
										<label class="control-label">Longitude</label>
														
										<div class="input-group input-group-sm input-group-minimal">
											<span class="input-group-addon">
												<i class="fa-arrows-alt"></i>
											</span>
											<input type="text" class="form-control" data-validate="required,number" id="lng" name="lng"/>
										</div>
									</div>
								</div>
						</div>
						<p>Check the latitude and longitude of your store <a href="http://itouchmap.com/latlong.html" target="_blank">here</a></p>
											
						<br/>	
							<script type="text/javascript">
								$(function() {
									$("#tags").tagsinput({
										maxTags: 6,
										maxChars: 10,
										trimValue: true,
										allowDuplicates: false										
										});
								});
							</script>				
						<div class="form-group">
							<div class="row" >
								<div class="col-md-4">
									<label class="control-label" for=tags>Tags</label>
									<p>Add tags (keywords) that describe your business. Press ENTER after typing a tag</p>		
					
									<input type="text" class="form-control" name="tag" id="tags"/>		
									
														
								</div>
								

							</div>							
						</div>
						
						<div class="form-group">
							<div class="row" >
								<div class="col-md-4">
									<label class="control-label">Store introduction</label>
							
									<textarea class="form-control" name="story" rows="3" name="max_field" data-validate="maxlength[1000]" placeholder="" ></textarea>
								</div>
							</div>
						</div>
						

						<div class="form-group">
							<button type="submit" class="btn btn-success">Register</button>
						</div>
					</form>
				
				</div>
				

			
</div>



<c:set var="numPic"  value="${2}"/>
<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				Store pictures
			</div>
		</div>
		<div class="panel-body">
			<section class="gallery-env">			
			<div class="row">
		
				<div class="album-images row">
			
				<c:forEach var="i" begin="1" end="${numPic}">
					<c:set var="id"  value="pic-${i}"/>
					
					<c:set var="onClickModalId"  value="#modal-${i}"/>				
					<c:set var="modalOpenId"  value="modal-open-pic${i}"/>
					<c:set var="imgUrl"  value="${pageContext.request.contextPath}/assets/images/album-img-1.png"/>
	
					
	   				<div class="col-md-3 col-sm-4 col-xs-6">
						<div class="album-image">
							<a href="javascript:;" id="${fn:escapeXml(modalOpenId)}" onclick='jQuery("${fn:escapeXml(onClickModalId)}").modal("show", {backdrop: "static"});' class="thumb" data-action="edit">
								<img src="${fn:escapeXml(imgUrl)}" class="img-responsive" />
							</a>
			
							<a href="#" class="name">
								<span>
								<c:choose>
								      <c:when test="${i =='1'}">Primary</c:when>							
								      <c:otherwise>Alternative</c:otherwise>
								</c:choose>				
								</span>
							</a>
							<div class="image-options">
								<a href="#modal-ask-delete" class="open-ask-delete" data-toggle="modal" data-id="ISBN564541" data-action="trash"><i class="fa-trash"></i></a>
							</div>
						</div>
					</div>
				</c:forEach>		
		
				</div>
			</div>
			</section>
		</div>
</div>


<c:forEach var="i" begin="1" end="${numPic}">
	<c:set var="modalOpenId"  value="#modal-open-pic${i}"/>

<div class="modal fade" id="modal-${fn:escapeXml(i)}">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<c:choose>
						      <c:when test="${i =='1'}">Primary </c:when>							
						      <c:otherwise>Alternative </c:otherwise>					      
						</c:choose>			
						picture	
					</h4>
				</div>
				
				<div class="modal-body">
			
				<div class="row">
					<div class="col-sm-4 col-md-offset-4 text-center">
						
							<div id="advancedDropzone${fn:escapeXml(i)}" class="droppable-area">
								Drop Files Here
							</div>
							
						</div>
				</div>
				<div class="row">
								
					<table class="table table-bordered table-striped" id="dropzone-filetable${fn:escapeXml(i)}">
						<tbody>

						</tbody>
					</table>
						
				</div>
				
					<script type="text/javascript">
					
						jQuery(document).ready(function($){
													
								$dropzone_filetable = $("#dropzone-filetable${fn:escapeXml(i)}");
								
								$( "${fn:escapeXml(modalOpenId)}" ).click(function() {
									$("#dropzone-filetable${fn:escapeXml(i)} tr#dropzone-only-row").remove();

								});
																
								dropzone_filetable = $("#advancedDropzone${fn:escapeXml(i)}").dropzone({
				        	  	url: "/store",
				        	  	paramName: "file", // The name that will be used to transfer the file
			        	 	    acceptedFiles: "image/*",
		        	 		    maxFilesize: 2, // MB
			        	  	    maxFiles: 1,
								
								init: function() {
								      var _this = this;
									  $( "${fn:escapeXml(modalOpenId)}" ).click(function() {
									        _this.removeAllFiles();
									        // If you want to cancel uploads as well, you
									        // could also call _this.removeAllFiles(true);
									      });


								},
								
								addedfile: function(file)
								{

									$dropzone_filetable.find('tbody').html('');

									
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
								
								success: function(file)
								{
									file.fileEntryTd.find('td:last').html('<span class="text-success">Uploaded</span>');
									file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');
								},
								
								error: function(file, error)
								{
									file.fileEntryTd.find('td:last').html('<span class="text-danger">'+ error+ '</span>');
									file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
								}
							});
							
							$("#advancedDropzone${fn:escapeXml(i)}").css({
								minHeight: 200
							});
							

			
						});
					</script>
							
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-white dropzone-modal-close" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-info">Save changes</button>
				</div>
			</div>
		</div>
</div>
</c:forEach>

<script>

var delPath;
$(document).on("click", ".open-ask-delete", function () {
    delPath= $(this).data('id');
    $("#form-del").attr("action", delPath);
 });
    
    
$(document).on("click", "#exec-delete", function () {
   	  $.post("demo_test_post.asp",
   	  {
   	    name:"Donald Duck",
   	    city:"Duckburg"
   	  },
   	  
   	  function(data,status){
   	    alert("Data: " + data + "\nStatus: " + status);
   	  });
 });
        

$(document).on('hide.bs.modal', '#modal-ask-delete', function () {
	  delPath = "";
	  alert(delPath);
});

</script>

<div class="modal fade" id="modal-ask-delete">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<h4 class="modal-title">Confirm delete ?</h4>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-white modal-ask-delete-cancel" data-dismiss="modal">Cancel</button>
					<button type="submit" id="exec-delete" class="btn btn-danger">Delete</button>				
				</div>
				
			</div>
		</div>
</div>

			
<script src="${pageContext.request.contextPath}/assets/js/jquery-validate/jquery.validate.min.js"></script>			
<script src="${pageContext.request.contextPath}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/dropzone/css/dropzone.css">
<script src="${pageContext.request.contextPath}/assets/js/dropzone/dropzone.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/timepicker/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/tagsinput/bootstrap-tagsinput.min.js"></script>


