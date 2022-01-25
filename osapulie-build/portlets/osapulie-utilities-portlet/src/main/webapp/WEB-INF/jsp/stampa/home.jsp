<%@ include file="../common/common.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/print.css" type="text/css" media="print" />
<div class="printButton">
	<a href="javascript:window.print();">
		<img alt="<spring:message code="button.print"/>" src="<%=request.getContextPath() %>/images/stampa.png" title="<spring:message code="button.print"/>">
	</a>
</div>
