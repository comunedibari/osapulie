<jsp:directive.include file="includes/top.jsp" />
	<form method="post" action="<%=response.encodeRedirectURL("login" + (request.getQueryString() != null && request.getQueryString().length() > 0 ? "?" + request.getQueryString() : ""))%>">
	
		<spring:hasBindErrors name="credentials">
			<div id="errors" class="portlet-msg-error">
				<ul>
				  <c:forEach var="error" items="${errors.allErrors}">
				      <li><spring:message code="${error.code}" text="${error.defaultMessage}" /></li>
				  </c:forEach>
		 		 </ul>
		 	 </div>
		</spring:hasBindErrors>
	
		<div id="loginContainer">
			<div class="loginTop">
				<h1 class="loginLabel">
					<span>Login</span>
				</h1>
			</div>
			<div class="loginBottom">
				<p>
					<spring:message code="screen.welcome.welcome" />
				</p>
				<p>
					<spring:message code="screen.welcome.security" />
				</p>
	
				<div>
					<label><spring:message code="screen.welcome.instructions" /></label>
					<table>
						<tr>
							<td>
								<label for="username"><spring:message code="screen.welcome.label.netid" /></label>
							</td>							
						</tr>
						<tr>
							<td>
								<input type="text" class="required" id="username" name="username" size="32" tabindex="1" accesskey="<spring:message code="screen.welcome.label.netid.accesskey" />" />
							</td>
						</tr>
						<tr>
							<td>	
								<label for="password"><spring:message code="screen.welcome.label.password" /></label>
							</td>
						</tr>
						<tr>
							<td>
								<%--
								NOTE: Certain browsers will offer the option of caching passwords for a user.  There is a non-standard attribute,
								"autocomplete" that when set to "off" will tell certain browsers not to prompt to cache credentials.  For more
								information, see the following web page:
								http://www.geocities.com/technofundo/tech/web/ie_autocomplete.html
								--%>
								<input class="required" type="password" id="password" name="password" size="32" tabindex="2" accesskey="<spring:message code="screen.welcome.label.password.accesskey" />" />
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="warn" name="warn" value="true" tabindex="3" /> 
							   	<label for="warn"  accesskey="<spring:message code="screen.welcome.label.warn.accesskey" />">
							   		<spring:message code="screen.welcome.label.warn" />
							   	</label>
							</td>
						</tr>   
					</table>
	
					<input type="hidden" name="lt" value="${flowExecutionKey}" />
					<input type="hidden" name="_eventId" value="submit" />
	
					<div class="container_pulsante">
						<input type="submit" class="custom_pulsante" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" />
						<span class="spacer"></span>
					   	<input type="reset" class="custom_pulsante" accesskey="c" value="<spring:message code="screen.welcome.button.clear" />" tabindex="5" />
						<span class="spacer"></span>
					   	<a href="/" class="custom_pulsante">HOME</a>
					</div>
				</div>
			</div>
		</div>
	</form>
<jsp:directive.include file="includes/bottom.jsp" />