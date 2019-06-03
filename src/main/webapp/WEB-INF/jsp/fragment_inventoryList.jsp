<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle var="bi" basename="inventory"/>
<fmt:setBundle basename="fragmentInventoryList"/>

<script>
$(function() {
var $state = $("#list thead input[type='checkbox']");

$state.on('change', function(ev){			
	var $chcks = $(".cb-item");
	
	if($state.is(':checked')){
		$chcks.prop('checked', true).trigger('change');
	}else{
		$chcks.prop('checked', false).trigger('change');
	}
});

$('#list').DataTable({
    "aaSorting": [[ 1, "asc" ]],
    "iDisplayLength": 50,
   	"pageLength" : 50,
    "bFilter" : true,               
	"bLengthChange": false,
    "bInfo" : true,
    "columnDefs": [ { "targets": 0, "width": "3%", "orderable": false },{ "targets": 2, "width": "10%" },{ "targets": 3, "width": "22%" },{ "targets": 4, "width": "12%" }, { "targets": 5, "width": "10%" }, { "targets": 6, "orderable": false, "width": "5%" },  ],
    "language": {
        "sInfo":'_START_ - _END_ | _TOTAL_',
        "oPaginate": { 
            "sFirst":    '<fmt:message key="First"/>',
            "sLast" :    '<fmt:message key="Last"/>',
            "sNext" :    '<fmt:message key="Next"/>',
            "sPrevious": '<fmt:message key="Previous"/>'
         },
       },	                
});
});
</script>
	
	
<table class="table table-bordered table-striped" id="list">
	<thead>
		<tr>
			<th class="no-sorting" style="text-align:center;">
				<input type="checkbox" >
			</th>
			<th><fmt:message key="Name" bundle="${bi}"/></th>
			<th><fmt:message key="CustomID" bundle="${bi}"/></th>
			<th><fmt:message key="Brand" bundle="${bi}"/></th>
			<th><fmt:message key="Condition" bundle="${bi}"/></th>
			<th><fmt:message key="Price" bundle="${bi}"/> (<c:out value='${sessionScope.SESSION_PRETTY_CURRENCY}'/>)</th>
			<th><fmt:message key="Image" bundle="${bd}"/></th>
		</tr>
	</thead>
	
	<tbody class="middle-align">
		<c:forEach var="item" items="${LIST_ITEMS}">
		<tr>
			<td style="text-align:center;">
				<input type="checkbox" name="item" class="cb-item" value='${fn:escapeXml(item.id)}'>
			</td>
			<td>
				<c:choose>
				      <c:when test="${not empty item.name}"><a href="${pageContext.request.contextPath}/inventory/${fn:escapeXml(item.id)}"><c:out value="${item.name}"/></a></c:when>
					  <c:otherwise><fmt:message key="BrokenItem"/></c:otherwise> 						
				</c:choose>						
			</td>
			<td><c:out value="${item.idCustom}"></c:out></td>
			<td><c:out value="${item.brand}"/></td>
			<td><c:out value="${item.condition}"/></td>
			<td><c:out value="${item.price}"/></td>
			<td style="text-align:center;">
					<img class="pic-thumb" 
					
					onerror='this.onerror = null; this.src="${pageContext.request.contextPath}/assets/images/album-img-1.png"'
					src='http://martiply.s3.amazonaws.com/i/${fn:escapeXml(item.id)}/0xsmall.jpg' style="min-height:60px; max-height:60px; max-width:60px; min-width:60px" />
			</td>
		</tr>
		</c:forEach>

	</tbody>
</table>
			
