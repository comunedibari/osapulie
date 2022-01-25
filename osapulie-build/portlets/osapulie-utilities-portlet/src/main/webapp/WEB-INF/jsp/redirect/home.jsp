<%@ include file="../common/common.jsp"%>
<c:if test="${not empty redirectUrl}">
	<script type="text/javascript">
		window.location = '${redirectUrl}';
	</script>
</c:if>