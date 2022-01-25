<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoUrlGenera">
	<portlet:param name="ope" value="generaRimborso" />
</portlet:actionURL>

<portlet:resourceURL var="rimborsoReportURL" id="rimborsoServiziCimiterialiReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="rimborsoServiziCimiteriali" />
<c:set var="dati" value="${datiRimbServiziCimiteriali}" />
<c:choose>
	<c:when test="${!empty datiRimbServiziCimiteriali}">
		<form:form id="${idForm}" action="${rimborsoUrlGenera}"	method="post" commandName="datiRimbServiziCimiteriali" cssClass="rimborsiForm printForm">
			<c:if test="${empty download}">
				<%@ include file="../common/rimborsiDatiAnagrafici.jsp"%>				
				<fieldset>
					<legend>
						<spring:message code="label.legend" />
					</legend>
					<label><strong><spring:message code="label.anno" /></strong></label>
					<select name="anno">
						<c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
					    	<option value="${i}" <c:if test="${i == datiRimbServiziCimiteriali.anno}"> selected="selected" </c:if> >${i}</option>
						</c:forEach>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="cambio" value="<spring:message code="button.modAnno" />"/><br/>
					<form:errors path="numeroDocumento" cssStyle="color:red"/>
					<c:choose>
						<c:when test="${datiRimbServiziCimiteriali.numeroDocumento != null}">
							<table>
								<tr>
									<th>
										<spring:message	code="label.numeroDocumento" />
									</th>
									
									<th>
										<spring:message	code="label.descrizioneDocumento" />
									</th>
									<th>
										<spring:message	code="label.contoCorrente" />
									</th>
									<th>
										<spring:message	code="label.importoDocumento" />
									</th>
									<th>
										<spring:message	code="label.importoDovuto" />*
									</th>
								</tr>
								<tr>
									<td>
										<c:out value="${datiRimbServiziCimiteriali.numeroDocumento}"/>
									</td>
									<td>
										<c:out value="${datiRimbServiziCimiteriali.descrizione}"/>
									</td>
									<td>
										<c:out value="${datiRimbServiziCimiteriali.contoCorrente}"/>
									</td>
									<td>
										<c:out value="${datiRimbServiziCimiteriali.importoDocumento}"/>
									</td>
									<td>
										<input type="text" name="importoDovuto" value="${datiRimbServiziCimiteriali.importoDovuto}">
										<form:errors path="importoDovuto" cssStyle="color:red"/>
									</td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<p><strong class="red"><spring:message code="errore.pdds.codice3"/></strong></p>
						</c:otherwise>
					</c:choose>
				</fieldset>
				<%@ include file="../common/rimborsiDatiGenerali.jsp"%>
				<%@ include file="../common/footer.jsp"%>
				<br />	
				<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
				<br />	
			</c:if>
			<c:if test="${!empty download}">
				<a href="${rimborsoReportURL}">
					<em><spring:message	code="link.dichiarazione" /></em></a>
				<br />
				<br />
				<p style="text-align: center;">
					<a href="<portlet:renderURL portletMode="view"/>"> - <spring:message code="button.home" /> -</a>
				</p>
			</c:if>
		</form:form>
	</c:when>
	<c:otherwise>
			<div>
				<strong><spring:message code="errore.pdds.codice3"/></strong>
			</div>								
		</c:otherwise>
	</c:choose>