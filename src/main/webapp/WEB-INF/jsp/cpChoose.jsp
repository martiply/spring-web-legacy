<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="coupon"/>

<c:choose>
<c:when test="${not empty sessionScope.SESSION_AVAILABLE_STORES}">

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
	function checkContent1Length(){	   
	    if ($("#content-1").val().length <= 130 && $("#content-1").val().length > 0 )  {  
	    	return 0;
	    } else if ($("#content-1").val().length == 0){
	  		return -1;  	
	    } else {
	    	return -2
	    }
	}

	$("#content-1").on('keydown', function(event) {
		 if (checkContent1Length() == -2){
			 $("#content-1-error").text('<fmt:message key="ErrorTweetTooLong"/>');
			 $( "#next-1" ).addClass("disabled");
		 }else if (checkContent1Length() == -1){
			 $( "#next-1" ).addClass("disabled");			 
		 }else{
			 $( "#next-1" ).removeClass("disabled");
			 $("#content-1-error").text('');			 
		 }
	});
	
 	$( "#next-1" ).click(function(e) {
	      var content = $('#content-1').val();
	      
	      
	      if (checkContent1Length() == 0){
		   	    $.post("${pageContext.servletContext.contextPath}/cp/choose", 
		   			  	{
				   	   	    a: "0",
				   	   	    i: content,
		   	   	  		},
		   	   	 	 
		   	   	   function(data,status){  	   	 		    	  
		   	   		  if (data == 0){
		   	   				window.location = "${pageContext.request.contextPath}/cp/new";
		   	   		  }else if (data == -1){
							window.location.replace("${pageContext.request.contextPath}/login");
		   	   		  }else{
							toastr.error(data, null, opts);
		   	   		  }

	 	   	  		}
	  			 );
	      }
	});
 	
});
</script>
<div class="panel panel-default">	
	<div class="panel-heading">
		<fmt:message key="CreateNewTweet"/>
	</div>
	
	<div class="panel-body">
		<div class="form-horizontal">	
		
			<div class="form-group">
				<label class="col-md-1 control-label" for="content-1"><fmt:message key="Content"/></label>
	
				<div class="col-md-4">
					<textarea style="resize: none;" class="form-control" rows="4" id="content-1"></textarea>
				</div>
				
				<div class="col-md-2">
					<p class="text-danger" id="content-1-error"></p>
				</div>
			</div>
			
			<button class="btn btn-blue disabled" id="next-1"><fmt:message key="Next"/></button>
		</div>		
	</div>			
</div>

<div class="panel panel-default">	
	<div class="panel-heading">
		<fmt:message key="UseExistingTweet"/>
	</div>
	
	<div class="panel-body">
		<script>		
		$(function(){		
			$('#cpId').on('input', function() {	
				$('#next').addClass('disabled');
			    if ($(this).val().length === 0 || Math.floor($(this).val()) != $(this).val() || !$.isNumeric($(this).val())) {
			    	$('#verify').addClass('disabled');
			    }else{
			    	$('#verify').removeClass('disabled');
			    }
			});
			
		 	$( "#verify" ).click(function(e) {
			      var btn = $(this).button('loading');
			      var val = $('#cpId').val();
			      
			   	  $.post("${pageContext.servletContext.contextPath}/ativ", {
			   	   	    a: val
			   	   	  },
			   	   	  
			   	   	  function(data,status){
		   	 		      btn.button('reset');	

			   	   		  if (data == -1){
							    window.location.replace("${pageContext.request.contextPath}/login");
			   	   		  }else if (data == -2){
			   	   				$('#cpIdError').text('<fmt:message key="ErrorTweetValidation"/>');	
			   	   		  }else{
			   	   		 	  $('#content-2').text(data);
			   	   		 	  $('#content-2-hidden').val(data);
						      $('#next-2').removeClass('disabled');
			   	   		  }
		 	   	  });
			});
		 	
		 	$( "#next-2" ).click(function(e) {
			      var btn = $(this).button('loading');
			      var id = $('#cpId').val();
			      var content = $('#content-2-hidden').val();
			      
			      
			   	    $.post("${pageContext.servletContext.contextPath}/cp/choose", 
			   			  	{
					   	   	    a: id,
					   	   	    i: content,
			   	   	  		},
			   	   	 	 
			   	   	   function(data,status){

			   	   		  if (data == 0){
			   	   				window.location = "${pageContext.request.contextPath}/cp/new";
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
					
		<div class="form-group">									
			<label class="col-md-1  control-label"><fmt:message key="TweetID"/></label>		
			<div class="row">
				<div class="col-md-4">
						<input type="text" id="cpId" value="" class="form-control"/>	
						<p id="cpIdError" class="text-danger"></p>																						
				</div>	
				<button id="verify" class="btn btn-secondary btn-single disabled " data-loading-text="<i class='fa fa-spin fa-spinner'></i>"><fmt:message key="Verify"/></button>
			</div>												
		</div>
		
		<div class="form-group">									
			<label class="col-md-1  control-label"><fmt:message key="Content" /></label>
			
			<div class="row">
			<div class="col-md-4">
				<div id="content-2" class="well"></div>
				<input type="hidden" id="content-2-hidden">
			</div>		
			</div>																	
		</div>					
		<button class="btn btn-blue disabled" id="next-2"><fmt:message key="Next"/></button>
				
	</div>
</div>

</c:when>

<c:otherwise>
<div class="panel panel-default">	
	<div class="panel-heading">
		<fmt:message key="MorePromoNotAllowed"/>
	</div>
	<div class="panel-body">
		<fmt:message key="MorePromoNotAllowedInfo"/>
	</div>	
</div>

</c:otherwise>
</c:choose>