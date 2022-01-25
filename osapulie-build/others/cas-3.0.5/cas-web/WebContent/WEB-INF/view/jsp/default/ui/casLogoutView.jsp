<jsp:directive.include file="includes/top.jsp" />

		<div id="loginContainer">
			<div class="loginTop">
				<h1 class="loginLabel">
					<span>Login</span>
				</h1>
			</div>
			<div class="loginBottom">
				<div class="portlet-msg-success"><spring:message code="screen.logout.header" /></div>
	
				<p><spring:message code="screen.logout.success" /></p>
				<p><spring:message code="screen.logout.security" /></p>
				
				<%--
				 Implementation of support for the "url" parameter to logout as recommended in CAS spec section 2.3.1.
				 A service sending a user to CAS for logout can specify this parameter to suggest that we offer
				 the user a particular link out from the logout UI once logout is completed.  We do that here.
				--%>
				<c:if test="${not empty url}">
				<p>
					<spring:message code="screen.logout.redirect" arguments="${url}" />
				</p>
				</c:if>
				<div class="container_pulsante">
				   	<a href="/" class="custom_pulsante">HOME</a>
				</div>
			</div>
		</div>
<jsp:directive.include file="includes/bottom.jsp" />