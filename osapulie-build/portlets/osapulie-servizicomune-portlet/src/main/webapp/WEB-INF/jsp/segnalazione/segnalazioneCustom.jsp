<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="invioSegnalazioneCustomUrl">
	<portlet:param name="action" value="invioSegnalazioneCustom" />		
</portlet:actionURL>

<portlet:actionURL var="annullaSegnalazioneUrl">
	<portlet:param name="action" value="annullaSegnalazione" />		
</portlet:actionURL>

<div class="mainDiv">
	<div class="motiviSegnalazioneDiv">
		<c:choose>
			<c:when test="${!empty segnalazioneCustomForm}">
				<form:form id="segnalazioneForm" action="${invioSegnalazioneCustomUrl}" commandName="segnalazioneCustomForm" method="post">
					<table class="genericTable">
						<tr>
							<td>
								<c:out value="${segnalazioneCustomForm.descrizione}"/>:
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.motivazione" />*:<br>
								<c:forEach items="${segnalazioneCustomForm.motivazioni}" var="motivazione">
									<form:radiobutton path="motivazione" value="${motivazione}" label="${motivazione}" htmlEscape="false"/><br>
								</c:forEach>
								<form:errors path="motivazione" cssStyle="color:red"/>
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.note"/>*:<br>
								<div>
									<form:textarea path="note" cssStyle="height: 80px; width: 100%;"/>
								</div>
								<form:errors path="note" cssStyle="color:red"/>
							</td>
						</tr>
					</table>
					<%@ include file="../common/footer.jsp"%>
					<div class="container_pulsante">
						<input type="submit" value="<spring:message code="button.invia"/>" onclick="javascript:return(confirm('<spring:message code="message.confirm.invio.segnalazione"/>'))">
						<span class="spacer"></span>
						<a class="custom_pulsante" href="${annullaSegnalazioneUrl}">
							<spring:message code="button.annulla" />
						</a>
					</div>
				</form:form>
			</c:when>
			<c:otherwise>
				<div class="container_pulsante">
					<c:choose>
						<c:when test="${!segnalazioneInviata}">
							<a class="custom_pulsante" href="javascript:history.go(-1);">
								<spring:message code="button.indietro" />
							</a>
						</c:when>
						<c:otherwise>
							<a class="custom_pulsante" href="${successReturnUrl}">
								<spring:message code="button.continua" />
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>