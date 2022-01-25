<jsp:directive.include file="includes/top.jsp" />
<div id="loginContainer">
	<div class="loginTop">
		<h1 class="loginLabel">
			<span>Login</span>
		</h1>
	</div>
	<div class="loginBottom">
		<p><spring:message code="screen.confirmation.message" arguments="${param.service}${fn:indexOf(params.service, '?') eq -1 ? '?' : '&'}ticket=${ticket}" /></p>
	</div>
</div>
<jsp:directive.include file="includes/bottom.jsp" />