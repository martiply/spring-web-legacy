<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle var="bi" basename="inventory"/>
<fmt:setBundle basename="inventorySingleItem"/>

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
	
function resetForm(){
   	$("#form").validate().resetForm();
	$(".validate-has-error").removeClass('validate-has-error');		
}
		
$(function() {
	setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);	
	
    $("#form").validate({
        // Specify the validation rules
        rules: {
            idCustom: {
                maxlength: 20
            },
            category: {
                required: true,
                maxlength: 200
            },
            price: {
                required: true,
                number: true
            },
            description: {
            	maxlength: 1000
            },
            url: {
            	maxlength: 500
            },
            salePrice: {
                required: true,
                number: true
            },
            saleRange: {
                required: true,
            },
            gender: {
                required: true,
            },
            age: {
                required: true,
            },
            sizeSystem: {
                required: true,
            },
            size: {
                required: true,
                maxlength: 15
            },
            color: {
                required: true,
                maxlength: 15
            },
            feature: {
                required: true,
                maxlength: 60
            },
            material: {
                required: true,
                maxlength: 40
            },
            groupId: {
                maxlength: 15
            }
        },
        
        // Specify the validation error messages
        messages: {
            idCustom: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="20"/></fmt:message>',
            category: {
                required: '<fmt:message key="Required" bundle="${bi}"/>',
                maxlength: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="200"/></fmt:message>'
            },
            price: {
                required: '<fmt:message key="Required" bundle="${bi}"/>',
                number: '<fmt:message key="NumberOnly" bundle="${bi}"/>'
            },
            description: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="1000"/></fmt:message>',
            url: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="500"/></fmt:message>',
            salePrice: {
                required: '<fmt:message key="Required" bundle="${bi}"/>',
                number: '<fmt:message key="NumberOnly" bundle="${bi}"/>'
            },
            saleRange: '<fmt:message key="Required" bundle="${bi}"/>',	     
            gender: '<fmt:message key="Required" bundle="${bi}"/>',	            
            age: '<fmt:message key="Required" bundle="${bi}"/>',	            
            sizeSystem: '<fmt:message key="Required" bundle="${bi}"/>',	            
            size: {
                required: '<fmt:message key="Required" bundle="${bi}"/>',
                maxlength: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="15"/></fmt:message>'
            },	            
            color: {
                required: '<fmt:message key="Required" bundle="${bi}"/>',
                maxlength: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="15"/></fmt:message>'
            },	            
            feature: {
                required: '<fmt:message key="Required" bundle="${bi}"/>',
                maxlength: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="60"/></fmt:message>'
            },	            
            size: {
                required: '<fmt:message key="Required" bundle="${bi}"/>',
                maxlength: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="40"/></fmt:message>'
            },	            
            groupId: {
                maxlength: '<fmt:message key="AtMostNChars" bundle="${bi}"><fmt:param value="15"/></fmt:message>'
            }                      
        },        
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        },
        
        submitHandler: function(form) {      
       	    $.post("${pageContext.request.contextPath}/ani", $('#form').serialize(),
      	   	 	 
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
        }
    });
});	
</script>
<form role="form" id="form" >
	<input type="hidden" name="formType" value="edit">
	
	<div class="panel panel-default">	
		<div class="panel-body">
			<input type="hidden" name="idType" id="idType" value="${fn:escapeXml(ITEM.idType)}">
			
			<div class="form-horizontal">		
		       <c:if test="${ITEM.idType=='gtin'}">					
					<div class="form-group">				
						<label class="col-md-1 control-label" ><fmt:message key="GTIN"  bundle="${bi}"/></label>
						<div class="col-md-4">
			    			<input type="text" class="form-control"  placeholder="${fn:escapeXml(ITEM.gtin)}" disabled="" >
							<input type="hidden" name="gtin" value="${fn:escapeXml(ITEM.gtin)}">						
						</div>		
					</div>
					<br/>
				</c:if>
				<div class="form-group">
				
					<label class="col-md-1 control-label" ><fmt:message key="CustomID"  bundle="${bi}"/></label>

					<div class="col-md-4">
						<input type="text" placeholder="${fn:escapeXml(ITEM.idCustom)}" class="form-control" name="idCustom" disabled="">
						<input type="hidden" name="idCustom" value="${fn:escapeXml(ITEM.gtin)}">												
						<p><fmt:message key="CustomIDInfo"/></p>
						
					</div>

				</div>

				<br/>
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="Brand"  bundle="${bi}"/></label>
					
					<div class="col-md-4">
							<input class="form-control" placeholder="${fn:escapeXml(ITEM.brand)}" type="text" disabled="">	
							<input type="hidden" name="brand" value="${fn:escapeXml(ITEM.brand)}">													
					</div>																
				</div>
					
				<br/>								
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="Name"  bundle="${bi}"/></label>
					
					<div class="col-md-4">
							<input class="form-control" placeholder="${fn:escapeXml(ITEM.name)}" type="text" disabled="">	
							<input type="hidden" name="name" value="${fn:escapeXml(ITEM.name)}">
																																		
					</div>																
				</div>
				
					
				<br/>								
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="Category"  bundle="${bi}"/></label>
					
					<div class="col-md-6">
							<input type="text" value="${fn:escapeXml(ITEM.category)}" class="form-control" id="category" name="category"/>	
							<p><fmt:message key="CategoryInfo1"/></p>
							<br/>
							<dl>
							  <dt>Example: chocolate product</dt>
							  <dd><code>Food, Beverages & Tobacco > Food Items > Candy & Gum > Candy & Chocolate > Chocolate Bars</code></dd>
							</dl>
							
							<dl>
							  <dt>Example: shirts</dt>
			 				 <dd><code>Apparel & Accessories > Clothing > Shirts & Tops > T-Shirts</code></dd>
							</dl>
							
					</div>																
				</div>
					
				<br/>	
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="Price"  bundle="${bi}"/></label>
					
					<div class="col-md-4">
							<div class="input-group">					
								<span class="input-group-addon"><c:out value="${sessionScope.SESSION_PRETTY_CURRENCY }"/></span>									
								<input type="text" value="${fn:escapeXml(ITEM.price)}" class="form-control" id="price" name="price"/>	
							</div>
							
					</div>																
				</div>
					
				<br/>	
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="Condition"  bundle="${bi}"/></label>
					
					<div class="col-md-4">					
						<input class="form-control" placeholder="${fn:escapeXml(ITEM.condition)}" disabled="" type="text">	
						<input type="hidden" name="cond" value="${fn:escapeXml(ITEM.condition)}">
					</div>																
				</div>
				
				<br/>	
				<div class="form-group">
					<label class="col-md-1 control-label"><fmt:message key="Description"  bundle="${bi}"/></label>
			
					<div class="col-md-6">
						<textarea class="form-control" name="description" rows="3" id="description" style="resize: none"></textarea>
						<p><fmt:message key="Optional"/> <fmt:message key="DescriptionInfo"/></p>
					</div>
				</div>
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="Url" bundle="${bi}"/></label>
					
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon">http://</span>				
							<input type="text" value="${fn:escapeXml(ITEM.url)}" class="form-control" id="url" name="url" />	
						</div>
						<p><fmt:message key="Optional"/> <fmt:message key="UrlInfo"/></p>
							
					</div>																
				</div>

			</div>
			
		</div>				
	</div>	
<script>
var salePriceInit;
var saleStartInit;
var saleEndInit;
var dateRangeInit;

function toggleSale(test){
	if (test){
       	$("#salePrice").val(salePriceInit).prop('disabled', false);
       	$("#saleRange").val(dateRangeInit).prop('disabled', false);		      	
	}else{
       	$("#salePrice").val('').prop('disabled', true);
   		$("#saleRange").val('').prop('disabled', true);
   		$('#saleStart').val('');
   		$('#saleEnd').val('');
	}		
}

$(function() {
	<c:choose>
      <c:when test="${ITEM.saleStart gt 0}">
      		dateRangeInit = moment.unix('${fn:escapeXml(ITEM.saleStart)}').format('<fmt:message key="DateFormat"/>') + ' - ' + moment.unix('${fn:escapeXml(ITEM.saleEnd)}').format('<fmt:message key="DateFormat"/>');
      		$('#saleStart').val('${fn:escapeXml(ITEM.saleStart)}');
      		$('#saleEnd').val('${fn:escapeXml(ITEM.saleEnd)}');	      		
      		saleStartInit = moment.unix('${fn:escapeXml(ITEM.saleStart)}').format('<fmt:message key="DateFormat"/>');
      		saleEndInit = moment.unix('${fn:escapeXml(ITEM.saleEnd)}').format('<fmt:message key="DateFormat"/>');
      		salePriceInit = '${fn:escapeXml(ITEM.salePrice)}';
      		$('#saleToggle').prop('checked', true);
      		toggleSale(true);
      </c:when>
      <c:otherwise>
 			saleStartInit = moment().add(2,'days');
 			saleEndInit = moment().add(14,'days');	      
      		toggleSale(false);
 	  </c:otherwise>
	</c:choose>
			
	$('#saleToggle').change(
	    function(){
	    	resetForm();
	        toggleSale(this.checked);
	});
	
	var now = new Date();
	
	$('#saleRange').daterangepicker(
		  { 
		    format: '<fmt:message key="DateFormat"/>',
		    startDate: saleStartInit,
		    endDate: saleEndInit,
		    minDate: moment().add(2,'days'),
		    maxDate: moment().add(30, 'days')
		  }		
	);
	
	$('#saleRange').on('apply.daterangepicker', function(ev, picker) {
		$('#saleStart').val(Math.round(picker.startDate.valueOf() / 1000)); //secondize UTC
		$('#saleEnd').val(Math.round(picker.endDate.valueOf() / 1000));
	});
	
});

</script>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="col-sm-10">
				<div class="form-block">
					<label>
						<input type="checkbox" class="cbr" id="saleToggle">
						<fmt:message key="IsSaleAvailable"/>
					</label>
				</div>
				<h6><fmt:message key="SaleInfo"/></h6>
				
			</div>
		</div>
		
		<div class="panel-body">
			
				<div class="form-horizontal">
					<div class="form-group">									
						<label class="col-md-1  control-label"><fmt:message key="SalePrice"  bundle="${bi}"/></label>
						
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><c:out value="${sessionScope.SESSION_PRETTY_CURRENCY }"/></span>	
								<input type="text" class="form-control" id="salePrice" name="salePrice"/>																							
							</div>	
						</div>																
					</div>								
					<br/>
			
					<div class="form-group">
						<label class="col-md-1 control-label"><fmt:message key="SalePeriod"  bundle="${bi}"/></label>
						
						<div class="col-md-4">
							
							<input type="text" id="saleRange" name="saleRange" class="form-control" />
							<input type="hidden" id="saleStart" name="saleStart"/>
							<input type="hidden" id="saleEnd" name="saleEnd"/>
							<p><fmt:message key="SaleInfo2"/></p>
						</div>
					</div>
				</div>
		</div>
	</div>

<c:if test="${not empty ITEM.apparelExtension.sizeSystem}">

<script>
$(function() {
	$("#gender").val('${fn:escapeXml(ITEM.apparelExtension.gender)}').prop('disabled', false);
	$("#age").val('${fn:escapeXml(ITEM.apparelExtension.age)}').prop('disabled', false);	
	$("#sizeSystem").val('${fn:escapeXml(ITEM.apparelExtension.sizeSystem)}').prop('disabled', false);
	$("#size").val('${fn:escapeXml(ITEM.apparelExtension.size)}').prop('disabled', false);	
	$("#color").val('${fn:escapeXml(ITEM.apparelExtension.color)}').prop('disabled', false);
	$("#feature").val('${fn:escapeXml(ITEM.apparelExtension.feature)}').prop('disabled', false);	
	$("#material").val('${fn:escapeXml(ITEM.apparelExtension.material)}').prop('disabled', false);	
	$("#groupId").val('${fn:escapeXml(ITEM.apparelExtension.groupId)}').prop('disabled', false);
});
</script>

	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="col-sm-10">
			 <fmt:message key="ApparelExtension"/>
			</div>
		</div>
		
		<div class="panel-body">
				<div class="form-horizontal">
				
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="Gender"  bundle="${bi}"/></label>
					
					<div class="col-md-1">
						<select class="form-control" id="gender" name="gender">
								<option value="female">Female</option>
								<option value="male">Male</option>
								<option value="unisex">Unisex</option>
						</select>
					</div>		
					
					<label class="col-md-1  control-label"><fmt:message key="Age"  bundle="${bi}"/></label>
					
					<div class="col-md-1">
						<select class="form-control" id="age" name="age" data-validate="required">
								<option value="adult">Adult</option>
								<option value="kids">Kids</option>
								<option value="toddler">Toddler</option>
						</select>
					</div>															
				</div>
				<br/>
				
				<div class="form-group">									
					<label class="col-md-1  control-label"><fmt:message key="SizeSystem"  bundle="${bi}"/></label>
					
					<div class="col-md-5">
							<select class="form-control" id="sizeSystem" name="sizeSystem">
									<option value="SML">SML</option>
									<option value="US">US</option>
									<option value="UK">UK</option>
									<option value="EU">EU</option>
									<option value="JP">JP</option>
							</select>
						<p><fmt:message key="SizeSystemInfo"/></p>
						
					</div>	
				</div>
				<br/>


																											
				<div class="form-group">
				
					<label class="col-md-1  control-label"><fmt:message key="Size"  bundle="${bi}"/></label>
				
					<div class="col-md-5">
						<input type="text" id="size" name="size" class="form-control" />
						<p><fmt:message key="SizeInfo"/></p>					
					</div>	
				</div>			
				<br/>
				
				<div class="form-group">
				
					<label class="col-md-1  control-label"><fmt:message key="Color"  bundle="${bi}"/></label>
				
					<div class="col-md-5">
						<input type="text" id="color" name="color"  class="form-control" />
					</div>	
				</div>
				<br/>
			
				<div class="form-group">
				
					<label class="col-md-1  control-label"><fmt:message key="Feature"  bundle="${bi}"/></label>
				
					<div class="col-md-5">
						<input type="text" id="feature" name="feature"  class="form-control" />
						<p><fmt:message key="FeatureInfo"/></p>
						<br/>
						<dl>
						  <dt><fmt:message key="Example"/></dt>
						  <dd><code><fmt:message key="FeaturesExample"/></code></dd>
						</dl>
					</div>	
				</div>
				
				<div class="form-group">
				
					<label class="col-md-1  control-label"><fmt:message key="Material"  bundle="${bi}"/></label>
				
					<div class="col-md-5">
						<input type="text" id="material" name="material" class="form-control" />
						<p><fmt:message key="MaterialInfo"/></p>
						<br/>
						<dl>
						  <dt><fmt:message key="Examples"/></dt>
						  <dd><code><fmt:message key="MaterialExample1"/></code></dd>	
  						  <dd><code><fmt:message key="MaterialExample2"/></code></dd>					  						  				  
						</dl>	
						
					</div>	
				</div>
				
				<div class="form-group">
				
					<label class="col-md-1  control-label"><fmt:message key="GroupID"  bundle="${bi}"/></label>
				
					<div class="col-md-5">
						<input type="text" id="groupId" name="groupId" class="form-control"  />
						<p><fmt:message key="Optional"/> <fmt:message key="GroupIDInfo"/></p>					
					</div>	
				</div>
				<br/>
				</div>
		</div>
	</div>
</c:if>
	
<div class="form-group">
	<button type="submit" class="btn  btn-success"><fmt:message key="SaveChanges" bundle="${bd}"/></button>
</div>
	
</form>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/daterangepicker/daterangepicker-bs3.css">
<script src="//cdn.jsdelivr.net/jquery.validation/1.14.0/jquery.validate.min.js"></script>			
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/daterangepicker/daterangepicker.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>