<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="saveUtenteCittadinoUrl">
	<portlet:param name="action" value="saveUtenteCittadino" />
</portlet:actionURL>

<div class="mainDiv profiloUtenteCittadino">
<c:choose>
	<c:when test="${profiloUtenteCittadinoForm != null}">
		<fieldset>
			<c:choose>
				<c:when test="${profiloUtenteCittadinoForm.idProfiloUtenteCittadino != 0}">
					 <legend><spring:message code="label.edit"/></legend>
				</c:when>
				<c:otherwise>
					<legend><spring:message code="label.add"/></legend>
				</c:otherwise>
			</c:choose>
			<form:form id="profiloUtenteCittadinoForm" action="${saveUtenteCittadinoUrl}" modelAttribute="profiloUtenteCittadinoForm" method="post">
		 		<form:hidden path="idProfiloUtenteCittadino" />
				<table class="genericTable">
					<tr>
						<td >
							<label><spring:message code="label.codiceFiscale"/>*:</label>
						</td>
						<td colspan="3">
						<c:choose>
							<c:when test="${profiloUtenteCittadinoForm.idProfiloUtenteCittadino != 0}">
								<c:out value="${profiloUtenteCittadinoForm.codiceFiscale}"></c:out>
								<form:hidden path="codiceFiscale"/>
							</c:when>
							<c:otherwise>
								<form:input path="codiceFiscale"/>
								<form:errors path="codiceFiscale" cssStyle="color:red"/>
							</c:otherwise>
					 	</c:choose>
						</td>
					</tr>
					<tr>
						<td >
							<label><spring:message code="label.nome"/>*:</label>
						</td>
						<td>
							<form:input path="nome"/>
					 		<form:errors path="nome" cssStyle="color:red"/>		
						</td>
						<td >
							<label><spring:message code="label.cognome"/>*:</label>
						</td>
						<td>
							<form:input path="cognome"/>
					 		<form:errors path="cognome" cssStyle="color:red"/>		
						</td>
					</tr>
					<tr>
						<td width="250">
							<label><spring:message code="label.email"/>:</label>
						</td>
						<td>
							<form:input path="email"/>
					 		<form:errors path="email" cssStyle="color:red"/>		
						</td>
						<td width="250">
							<label><spring:message code="label.pec"/>:</label>
						</td>
						<td>
							<form:input path="pec"/>
					 		<form:errors path="pec" cssStyle="color:red"/>		
						</td>
					</tr>
					<c:if test="${passwordEnable}">
						<tr>
							<td width="250">
								<label><spring:message code="label.password"/>*:</label>
							</td>
							<td>
								<form:password path="password"/>
						 		<form:errors path="password" cssStyle="color:red"/>	
							</td>
							<td width="250">
								<label><spring:message code="label.confermaPassword"/>*:</label>
							</td>
							<td>
								<form:password path="confermaPassword"/>
						 		<form:errors path="confermaPassword" cssStyle="color:red"/>		
							</td>
						</tr>
					</c:if>
				</table>
				<div><spring:message code="description.email.cittadino"/></div>
				<%@ include file="../common/footer.jsp"%>
				<div class="container_pulsante">
					<input type="submit" value="<spring:message code="button.salva"/>" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
					<span class="spacer"></span>
					<c:choose>
						<c:when test="${returnUrl != null}">
							<a class="custom_pulsante" href="${returnUrl}">
								<spring:message code="button.indietro" />
							</a>
						</c:when>
						<c:otherwise>
							<a class="custom_pulsante" href="${home}">
								<spring:message code="button.annulla" />
							</a>
						</c:otherwise>
					</c:choose>
				</div>
				</form:form>
		</fieldset>
	</c:when>
	<c:otherwise>
		<div>
			<strong><spring:message code="label.noUserFound"/></strong>
		</div>							
	</c:otherwise>
	</c:choose>
</div>