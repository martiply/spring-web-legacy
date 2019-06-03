<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="store"/>

<div class="panel panel-default">			
				<div class="panel-heading">
					<div class="panel-title">
						<fmt:message key="ID"/><c:out value="${STORE.storeId}" />
					</div>
				</div>				
				<div class="panel-body">
				
					<form role="form" id="form1" method="post" class="validate" action="${pageContext.request.contextPath}/asu">
						
						<div class="form-group">
							<div class="row" >
								<div class="col-md-5">
									<label class="control-label"><fmt:message key="StoreName"/></label>
							
									<input type="text" class="form-control" name="name" data-validate="required,minlength[3],maxlength[60]" data-message-required="Required. Must be between 3 to 30 characters." value="${fn:escapeXml(STORE.name)}" placeholder="" />
								</div>
							</div>
						</div>
					
						
						<div class="form-group">
							<div class="row">								
								<div class="col-md-5">
									<label class="control-label"><fmt:message key="Address"/></label>
									<input type="text" class="form-control" name="address" data-validate="required,minlength[4],maxlength[60]" data-message-required="Required. Must be between 3 to 30 characters." value="${fn:escapeXml(STORE.address)}" />								
								</div>
							</div>							
						</div>	
						
						<div class="form-group">
							
							<div class="row">
							
								<div class="col-md-2">
									<label class="control-label"><fmt:message key="Phone"/></label>								
									<input type="text" class="form-control" name="phone" data-validate="required,number,maxlength[20]" data-message-required="Required" value="${fn:escapeXml(STORE.phone)}" />																
								</div>
								
								<div class="col-md-1">
									<label class="control-label"><fmt:message key="Zip"/></label>
									<input type="text" class="form-control" name="zip" data-validate="required,maxlength[11]" data-message-required="Required" value="${fn:escapeXml(STORE.zip)}"  />																
								</div>
														
								<div class="col-md-2">							
									<label class="control-label"><fmt:message key="Country"/></label>	
							    	<select class="form-control" id="country" name="tz">
									    <option value="9" ${STORE.tz == '9' ? 'selected' : ''}>Japan</option>							    	
									    <option value="7" ${STORE.tz == '7' ? 'selected' : ''}>Indonesia</option>
									</select>									
								</div>

							</div>
							
						</div>		
						
						<div class="form-group">
								<div class="row">
									<div class="col-md-2">								
										<label class="control-label"><fmt:message key="Opening"/></label>
										<div class="input-group input-group-minimal">
										
											<div class="input-group-addon">
												<a href="#"><i class="fa-clock-o"></i></a>
											</div>
											<input type="text" class="form-control timepicker" name="open" data-validate="required" data-template="dropdown" data-show-seconds="false" data-default-time="${fn:escapeXml(STORE.open)}" data-show-meridian="false" data-minute-step="5" data-second-step="1" />
											

										</div>
									</div>
									
									<div class="col-md-2">								
										<label class="control-label"><fmt:message key="Closing"/></label>
										<div class="input-group input-group-minimal">
										
											<div class="input-group-addon">
												<a href="#"><i class="fa-clock-o"></i></a>
											</div>
											<input type="text" class="form-control timepicker" name="close" data-validate="required" data-template="dropdown" data-show-seconds="false" data-default-time="${fn:escapeXml(STORE.close)}" data-show-meridian="false" data-minute-step="5" data-second-step="1" />
											

										</div>
									</div>
								</div>										
						</div>
						
						
						<div class="form-group">
								<div class="row">									
									<div class="col-md-2">		
										<label class="control-label"><fmt:message key="Latitude"/></label>
														
										<div class="input-group input-group-sm input-group-minimal">
											<span class="input-group-addon">
												<i class="fa-arrows"></i>
											</span>
											<input type="text" class="form-control" data-validate="required,number" id="lat" value="${fn:escapeXml(STORE.lat)}" name="lat"  />
										</div>
									</div>
																		
									<div class="col-md-2">		
										<label class="control-label"><fmt:message key="Longitude"/></label>
														
										<div class="input-group input-group-sm input-group-minimal">
											<span class="input-group-addon">
												<i class="fa-arrows-alt"></i>
											</span>
											<input type="text" class="form-control" data-validate="required,number" id="lng" value="${fn:escapeXml(STORE.lng)}" name="lng"/>
										</div>
									</div>
								</div>
						</div>
						
						<p><fmt:message key="LatlngInstruction"/></p>
											
						<br/>	
							<script>
								$(function() {
									$("#tags").tagsinput({
										maxTags: 6,
										maxChars: 10,
										trimValue: true,
										allowDuplicates: false										
										});
								});
								
								$( document ).ready(function() {
									<c:forEach var="tag" items="${fn:split(STORE.tag,',')}">
										$('#tags').tagsinput('add', '${fn:escapeXml(tag)}');
									</c:forEach>
								});
								
							</script>				
						<div class="form-group">
							<div class="row" >
								<div class="col-md-4">
									<label class="control-label" for=tags><fmt:message key="Tags"/></label>
									<p><fmt:message key="TagsInstruction"/></p>		
					
									<input type="text" class="form-control" name="tag" id="tags"/>		
									
														
								</div>
								

							</div>							
						</div>
						
						<div class="form-group">
							<div class="row" >
								<div class="col-md-5">
									<label class="control-label"><fmt:message key="Introduction"/></label>
							
									<textarea class="form-control" name="story" rows="3" name="max_field" data-validate="maxlength[1000]" ><c:out value="${STORE.story}" /></textarea>
								</div>
							</div>
						</div>
						

						<input type="hidden" name="storeId" value="${fn:escapeXml(STORE.storeId)}"/>
						<div class="form-group">
							<button type="submit" class="btn btn-success"><fmt:message key="SaveChanges" bundle="${bd}"/></button>
						</div>
					</form>				
				</div>
			
</div>

<script src="//cdn.jsdelivr.net/jquery.validation/1.14.0/jquery.validate.min.js"></script>			
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.1.62/jquery.inputmask.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/timepicker/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/tagsinput/bootstrap-tagsinput.min.js"></script>