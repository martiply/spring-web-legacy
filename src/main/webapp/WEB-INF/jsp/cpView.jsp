<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle var="bd" basename="base"/>
<fmt:setBundle basename="coupon"/>

<script>
	$(function(){			
		if( ! $.isFunction($.fn.dxChart))
			return;		
		$('#end').text(moment.unix(${fn:escapeXml(COUPON.endSec)}).format("MMM DD"));					
	
	});
</script>
			

<div class="col-sm-12">				
	<c:if test="${not empty STATS.catalogDailyStats}">
		<script>
			$(function(){	
				var dataSource = [];					                  
				<c:forEach items="${STATS.catalogDailyStats}" var="element"> 					
					dataSource.push({date:moment.unix(${fn:escapeXml(element.midnightTimestamp)}).format("MMM DD"), shares:${fn:escapeXml(element.nShares)}});
				</c:forEach>		            
		           			
				$("#chart").dxChart({
					dataSource: dataSource,
					commonSeriesSettings: {
						argumentField: "date",
			            point: {
			                visible: true,
			                border: {
			                    visible: true,
			                    color: 'white',
			                    width: 2
			                },
					        symbol: 'circle',
					        size: 6
			            },
		
					},
					series: [
						{ valueField: "shares", name: "Number of Shares", color: "#8dc63f" }
					],
					argumentAxis:{
						valueMarginsEnabled:false,
						grid:{
							visible: true,
							opacity: 0.3
						},
						type: 'discrete',
						discreteAxisDivisionMode: 'crossLabels'
		
					},
					tooltip:{
						enabled: true
					},
					legend: {
						verticalAlignment: "bottom",
						horizontalAlignment: "center",
						visible: false
					},
					commonPaneSettings: {
						border:{
							visible: true,
							right: false
						}	   
					}
				});
			})
		</script>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="ShareChartTitle"/></h3>
			</div>
			<div class="panel-body">		
				<div class="dx-visibility-change-handler" id="chart" style="height: 300px; width: 100%;"></div>
			</div>
		</div>
	</c:if>	

	<div class="panel panel-default">
	<div class="panel-heading">
		<fmt:message key="Details"/>
	</div>	
	<table class="table">
		<thead>
			<tr>
				<th class="middle-align col-md-1"><fmt:message key="TweetID"/></th>
				<th class="col-md-11"><c:out value="${COUPON.cpId}"/></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="middle-align"><fmt:message key="Content"/></td>
				<td ><c:out value="${COUPON.content}"/></td>
			</tr>			
			<tr>
				<td class="middle-align"><fmt:message key="Terms"/></td>
				<td><c:out value="${COUPON.terms}"/></td>
			</tr>
			<tr>
				<td class="middle-align"><fmt:message key="ValidUntil"/></td>
				<td id="end"></td>
			</tr>		
	
		</tbody>
	</table>	
	</div>
		
	<div class="panel panel-default">
		<div class="panel-heading">
			<fmt:message key="OtherMetrics"/>
		</div>
		
		<c:choose>
		      <c:when test="${STATS.nShares > 0}">
			      <table class="table">
			      		<thead>
							<tr>
								<td class="middle-align col-md-2"><fmt:message key="TotalClicks"/></td>
								<td class="col-md-10"><c:out value="${STATS.nShares}"/></td>
							</tr>
			      		</thead>

						<tbody>
							<tr>
								<td class="middle-align"><fmt:message key="AverageRate"/></td>
								<td><c:out value="${ sessionScope.SESSION_PRETTY_CURRENCY}"/><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2" value="${ STATS.meanBid }" /></td>
							</tr>								
							<tr>
								<td class="middle-align"><fmt:message key="AverageRank"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="1" value="${ STAT.meanRank }" /></td>
							</tr>										
						</tbody>
					</table>
		      </c:when>
		
		      <c:otherwise>
		      	 <div class="panel-body">
		     		 <p class="text-primary"><fmt:message key="NotAvailableAtThisMoment"/></p>
		     	 </div>
		      </c:otherwise>
		</c:choose>
	</div>						
</div>
								
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>				
<script src="${pageContext.request.contextPath}/assets/js/devexpress-web-14.1/js/globalize.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/devexpress-web-14.1/js/dx.chartjs.js"></script>