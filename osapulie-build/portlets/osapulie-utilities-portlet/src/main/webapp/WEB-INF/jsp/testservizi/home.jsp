<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="testUrl">
	<portlet:param name="action" value="avviaTest" />		
</portlet:actionURL>
<div class="mainDiv">
	<fieldset>
		<legend><spring:message code="label.testServizi"/></legend>
		<div>
			<form:form action="${testUrl}" method="post" commandName="testServiziForm">
				<table>
					<tr>
						<td><spring:message code="label.codiceFiscale"/></td>
						<td><spring:message code="label.partitaIva"/></td>
						<td><spring:message code="label.periodo"/></td>
						<td><spring:message code="label.comune"/></td>
						<td><spring:message code="label.operazioni"/></td>
					</tr>
					<tr>
						<td>
							<form:input path="codiceFiscale"/>
						</td>
						<td>
							<form:input path="partitaIva"/>
						</td>
						<td>
							<label><spring:message code="label.annoInizio" />:</label>&nbsp;
							<select name="startYear">
							  <c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
							      <option value="${i}" <c:if test="${i == yearStart}"> selected="selected" </c:if> >${i}</option>
							   </c:forEach>
							</select>
							<label><spring:message code="label.annoFine" />:</label>&nbsp;
							<select name="endYear">
							  <c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
							      <option value="${i}" <c:if test="${i == yearEnd}"> selected="selected" </c:if> >${i}</option>
							   </c:forEach>
							</select>
						</td>
						<td>
							<form:select path="idComune">
								<form:options items="${comuni}" itemLabel="nome" itemValue="codiceIstat"/>
							</form:select>
						</td>
						<td>
							<input type="submit" value="<spring:message code="button.test"/>">
						</td>
					</tr>
				</table>
			</form:form>
			<div id="xmlDiv">
				<div id="xmlDatiAnagrafici">
					<c:if test="${!empty xmlDatiAnagrafici}">
						<span class="bold"><spring:message code="label.xmlDatiAnagrafici"/>:</span>
						<c:out value="${xmlDatiAnagrafici}"/>
					</c:if>
				</div>
				<div id="xmlDatiAnagraficiGenerali">
					<c:if test="${!empty xmlDatiAnagraficiGenerali}">
						<span class="bold"><spring:message code="label.xmlDatiAnagraficiGenerali"/>:</span>
						<c:out value="${xmlDatiAnagraficiGenerali}"/>
					</c:if>
				</div>
				<div id="xmlVisuraPosizioneTributaria">
					<c:if test="${!empty xmlVisuraPosizioneTributaria}">
						<span class="bold"><spring:message code="label.xmlVisuraPosizioneTributaria"/>:</span>
						<c:out value="${xmlVisuraPosizioneTributaria}"/>
					</c:if>
				</div>
			</div>
		</div>
	</fieldset>
</div>