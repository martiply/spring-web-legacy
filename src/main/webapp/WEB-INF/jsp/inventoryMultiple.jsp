<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>

<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle var="bi" basename="inventory"/>
<fmt:setBundle basename="inventoryMultiItem"/>

<script>
$(function(){
	var i = 1,
		$dropzone_filetable = $("#dropzone-filetable"),
		dropzone = $("#advancedDropzone").dropzone({
		url: '${pageContext.request.contextPath}/csv',
  	 	    acceptedFiles: "text/csv",
		    maxFilesize: 2, // MB
  	  	    maxFiles: 1,
		
		init: function() {
			
	     	var _this = this;
	     	
	        this.on("maxfilesexceeded", function(file) {
	            this.removeAllFiles();
	            this.addFile(file);
	     	 });
	     	
		},
		addedfile: function(file)
		{
			if(i == 1)
			{
				$dropzone_filetable.find('tbody').html('');
			}
			
			var size = parseInt(file.size/1024, 10);
			size = size < 1024 ? (size + " KB") : (parseInt(size/1024, 10) + " MB");
			
			var $entry = $('<tr = id="only-row">\
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
		
		success: function(file, msg)
		{
			if (msg == 0){
				file.fileEntryTd.find('td:last').html('<span class="text-success">Uploaded</span>');
				file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');
				window.location.replace("${pageContext.request.contextPath}/inventory");
			} else if (msg == -1){
				window.location.replace("${pageContext.request.contextPath}/login");
			} else{
				file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
				file.fileEntryTd.find('td:last').html('<span class="text-danger">' + msg +'</span>');
			}
		},
		
		error: function(file, msg)
		{
			
			file.fileEntryTd.find('td:last').html('<span class="text-danger">'+ msg + '</span>');
			file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
		}
	});
	
	$("#advancedDropzone").css({
		minHeight: 200
	});

});
</script>

<div class="panel panel-default">			
	<div class="panel-heading">
		<fmt:message key="CSVUpload"/>
	</div>
					
	<div class="panel-body">		
		<br>
		<div class="row">
			<div class="col-md-3 text-center">
			
				<div style="min-height: 200px;" id="advancedDropzone" class="droppable-area dz-clickable">
					<fmt:message key="DropCSVHere"/>
				</div>
				
			</div>
			<div class="col-md-9">
				
				<table class="table table-bordered table-striped" id="dropzone-filetable">
					<thead>
						<tr>
							<th width="20%">Name</th>
							<th width="20%">Upload Progress</th>
							<th width="8%">Size</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
	
				</table>
			</div>
		</div>	
	</div>			
</div>


<div class="row">				
	<div class="col-sm-7">
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<fmt:message key="CSVTemplatesTitle"/>
			</div>
			<div class="panel-body">
				<p>
				<fmt:message key="InfoCreateCSV">
				<fmt:param value="<a href='${pageContext.request.contextPath}/inventory'>"/>
				<fmt:param value="</a>"/>
				</fmt:message>
				</p>				
				
			</div>
		</div>
		
	</div>
	<div class="col-sm-5">
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<fmt:message key="Templates"/>
			</div>
			<div class="panel-body">								
				<div class="same-margin">
				<p>
				<a href="${pageContext.request.contextPath}/public/basic_template.csv"><fmt:message key="BasicTemplate"/></a>
				</p>
				<p>
				<a href="${pageContext.request.contextPath}/public/apparel_template.csv"><fmt:message key="ApparelTemplate"/></a>
				</p>
				</div>				
			</div>
		</div>		
	</div>	
	
	
<div class="col-md-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><fmt:message key="StructureTitle"/></h3>
		</div>
		
		<div class="panel-body">
		 <p>
			<div class="text-primary"><fmt:message key="StrutureInfo"/></div>
		 </p>		
		</div>
		<br/>
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="idType"/></th>
					<th><fmt:message key="gtin"/></th>
					<th><fmt:message key="idCustom"/></th>
					<th><fmt:message key="brand"/></th>
					<th><fmt:message key="name"/></th>
					<th><fmt:message key="category"/></th>
					<th><fmt:message key="price"/></th>
					<th><fmt:message key="condition"/></th>
					<th><fmt:message key="description"/></th>
					<th><fmt:message key="url"/></th>
					<th><fmt:message key="salePrice"/></th>
					<th><fmt:message key="saleStart"/></th>
					<th><fmt:message key="saleEnd"/></th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td><fmt:message key="customOrGtin"/></td>
					<td><fmt:message key="s1213digits"/></td>
					<td><fmt:message key="max20chars"/></td>
					<td><fmt:message key="max20chars"/></td>
					<td><fmt:message key="max70chars"/></td>
					<td><fmt:message key="max200chars"/></td>
					<td><fmt:message key="numeric"/></td>
					<td><fmt:message key="usedOrNew"/></td>
					<td><fmt:message key="max1000chars"/></td>
					<td><fmt:message key="max500chars"/></td>
					<td><fmt:message key="numeric"/></td>
					<td><fmt:message key="DateFormatMultiUpload"/></td>
					<td><fmt:message key="DateFormatMultiUpload"/></td>
				</tr>
				<tr>
					<td><fmt:message key="inIdInfo"/></td>
					<td><fmt:message key="inGtin"/></td>
					<td><fmt:message key="inCustomId"/></td>
					<td><fmt:message key="inBrand"/></td>
					<td><fmt:message key="inName"/></td>
					<td><fmt:message key="inCategory"/></td>
					<td><fmt:message key="inPrice"/></td>
					<td><fmt:message key="inCondition"/></td>
					<td><fmt:message key="inDescription"/></td>
					<td><fmt:message key="inUrl"/></td>
					<td><fmt:message key="inSalePrice"/></td>
					<td><fmt:message key="inSaleStart"/></td>
					<td><fmt:message key="inSaleEnd"/></td>
				</tr>

			</tbody>
		</table>
		<br/>
		<div class="text-primary"><fmt:message key="Example"/></div>
		<br/>		
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="idType"/></th>
					<th><fmt:message key="gtin"/></th>
					<th><fmt:message key="idCustom"/></th>
					<th><fmt:message key="brand"/></th>
					<th><fmt:message key="name"/></th>
					<th><fmt:message key="category"/></th>
					<th><fmt:message key="price"/></th>
					<th><fmt:message key="condition"/></th>
					<th><fmt:message key="description"/></th>
					<th><fmt:message key="url"/></th>
					<th><fmt:message key="salePrice"/></th>
					<th><fmt:message key="saleStart"/></th>
					<th><fmt:message key="saleEnd"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>custom</td>
					<td></td>
					<td>AB1000CD</td>
					<td><fmt:message key="eBrand1"/></td>
					<td><fmt:message key="eName1"/></td>
					<td>Food, Beverages & Tobacco > Beverages > Coffee</td>
					<td>11000</td>
					<td>new</td>
					<td><fmt:message key="eDescription1"/></td>
					<td>htttp://store.com/item</td>					
					<td></td>
					<td></td>
					<td></td>				
				</tr>
				<tr>
					<td>gtin</td>
					<td>4912345678901</td>
					<td></td>
					<td><fmt:message key="eBrand2"/></td>
					<td><fmt:message key="eName2"/></td>
					<td>Food, Beverages & Tobacco > Beverages > Non-Dairy Milk > Soy Milk</td>
					<td>2500</td>
					<td>new</td>
					<td><fmt:message key="eDescription2"/></td>
					<td>https://www.store.com/item</td>
					<td>2100</td>
					<td>2015/9/9</td>
					<td>2015/10/31</td>				
				</tr>				
			</tbody>	
		</table>				
	</div>	
</div>

<div class="col-md-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><fmt:message key="ApparelTableTitle"/></h3>
		</div>
		
		<div class="panel-body">
		<div class="text-primary"><fmt:message key="ApparelTableInfo"/>
		</div>		
	
		</div>
		<br/>
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="gender"/></th>
					<th><fmt:message key="age"/></th>
					<th><fmt:message key="sizeSystem"/></th>
					<th><fmt:message key="size"/></th>
					<th><fmt:message key="color"/></th>
					<th><fmt:message key="feature"/></th>
					<th><fmt:message key="material"/></th>
					<th><fmt:message key="groupId"/></th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td><fmt:message key="maleOrFemaleOrUnisex"/></td>
					<td><fmt:message key="adultOrKidsOrToddler"/></td>
					<td><fmt:message key="sizeSystemList"/></td>
					<td><fmt:message key="max20charscomma"/></td>
					<td><fmt:message key="max15charscomma"/></td>
					<td><fmt:message key="max60charscomma"/></td>
					<td><fmt:message key="max20charscomma"/></td>
					<td><fmt:message key="max15charscomma"/></td>
				</tr>
				<tr>
					<td><fmt:message key="inGender"/></td>
					<td><fmt:message key="inAge"/></td>
					<td><fmt:message key="inSizeSystem"><fmt:param value="<strong>"/><fmt:param value="</strong>"/></fmt:message></td>
					<td><fmt:message key="inSize"/></td>
					<td><fmt:message key="inColor"/></td>
					<td><fmt:message key="inFeature"/></td>
					<td><fmt:message key="inMaterial"/></td>
					<td><fmt:message key="inGroupId"/></td>
				</tr>
			</tbody>
		</table>
		<br/>
		<div class="text-primary"><fmt:message key="Example"/></div>
		<br/>
		<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="idType"/></th>
						<th><fmt:message key="gtin"/></th>
						<th><fmt:message key="idCustom"/></th>
						<th><fmt:message key="brand"/></th>
						<th><fmt:message key="name"/></th>
						<th><fmt:message key="category"/></th>
						<th><fmt:message key="price"/></th>
						<th><fmt:message key="condition"/></th>
						<th><fmt:message key="description"/></th>
						<th><fmt:message key="url"/></th>
						<th><fmt:message key="salePrice"/></th>
						<th><fmt:message key="saleStart"/></th>
						<th><fmt:message key="saleEnd"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>custom</td>
						<td>0</td>
						<td>0</td>
						<td><fmt:message key="eBrand3"/></td>
						<td><fmt:message key="eName3"/></td>
						<td>Apparel & Accessories > Shoes > Dress Shoes > Pumps > Spectator Pumps</td>
						<td>40000</td>
						<td>used</td>
						<td></td>
						<td>https://www.example.com/eName3.jpg</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
		</table>
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="gender"/></th>
					<th><fmt:message key="age"/></th>
					<th><fmt:message key="sizeSystem"/></th>
					<th><fmt:message key="size"/></th>
					<th><fmt:message key="color"/></th>
					<th><fmt:message key="feature"/></th>
					<th><fmt:message key="material"/></th>
					<th><fmt:message key="groupId"/></th>
				</tr>				
				<tr>
					<td>female</td>
					<td>adult</td>
					<td>EU</td>
					<td>36.5,37</td>
					<td><fmt:message key="eColor3"/></td>
					<td><fmt:message key="eFeature3"/></td>
					<td><fmt:message key="eMaterial3"/></td>
					<td></td>
				</tr>
			</thead>
		</table>
		

	</div>
	
</div>

				
</div>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.js"></script>