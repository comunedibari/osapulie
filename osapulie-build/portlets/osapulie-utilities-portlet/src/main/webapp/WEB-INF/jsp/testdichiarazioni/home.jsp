<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="testUrl">
	<portlet:param name="action" value="avviaTest" />		
</portlet:actionURL>
<div class="mainDiv">
	<fieldset>
		<legend><spring:message code="label.testServizi"/></legend>
		<div>
			<form:form action="${testUrl}" method="post" commandName="testDichiarazioniForm">
				<table>
					<tr>
						<td><spring:message code="label.idBackup"/></td>
						<td><spring:message code="label.timeout"/></td>
						<td><spring:message code="label.operazioni"/></td>
					</tr>
					<tr>
						<td>
							<form:input path="idBackup"/>
						</td>
						<td>
							<form:input path="timeout"/>
						</td>
						<td>
							<input type="submit" value="<spring:message code="button.test"/>">
						</td>
					</tr>
				</table>
			</form:form>
			<div id="xmlDiv">
				<div id="xmlEsito">
					<c:if test="${!empty xml}">
						<span class="bold"><spring:message code="label.xmlEsito"/>:</span>
						<c:out value="${xml}"/>
					</c:if>
				</div>
			</div>
		</div>
	</fieldset>
</div>