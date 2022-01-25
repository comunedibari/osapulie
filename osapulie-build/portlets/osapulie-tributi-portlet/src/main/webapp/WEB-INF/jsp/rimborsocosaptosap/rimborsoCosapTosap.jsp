<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoUrlGenera">
	<portlet:param name="ope" value="generaRimborso" />
</portlet:actionURL>

<portlet:resourceURL var="rimborsoReportURL" id="rimborsoCosapTosapReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="rimborsoCosapTosap" />
<c:set var="dati" value="${datiRimbCosapTosap}" />
<c:choose>
<c:when test="${!empty datiRimbCosapTosap}">
	<form:form id="${idForm}" action="${rimborsoUrlGenera}"	method="post" commandName="datiRimbCosapTosap" cssClass="rimborsiForm">
		<c:if test="${empty download}">
			<%@ include file="../common/rimborsiDatiAnagrafici.jsp"%>				
			<fieldset>
				<legend>
					<spring:message code="label.legend" />
				</legend>
				<label><strong><spring:message code="label.anno" /></strong></label>
				<select name="anno">
					<c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
				    	<option value="${i}" <c:if test="${i == datiRimbCosapTosap.anno}"> selected="selected" </c:if> >${i}</option>
					</c:forEach>
				</select>
				<input type="radio" name="tipoOsap" value="p" <c:if test="${tipoOsap == 'p'}"> checked="checked" </c:if>>&nbsp;<spring:message code="label.osapPermanente" />
				<input type="radio" name="tipoOsap" value="t" <c:if test="${tipoOsap == 't'}"> checked="checked" </c:if>>&nbsp;<spring:message code="label.osapTemporanea" />
				<input type="submit" name="cambio" value="<spring:message code="button.edit" />"/><br/>
				<form:errors path="numeroDocumento" cssStyle="color:red"/>
				<c:choose>
					<c:when test="${!empty datiRimbCosapTosap.posizioniOsapMap}">
						<table>
							<!-- Distinzione tra Osap Permanente e Temporanea -->
							<div class="descriptionOsapTosap">
								<spring:message code="label.numeroDocumento" />:&nbsp;<b><c:out value="${datiRimbCosapTosap.numeroDocumento}"/></b>,&nbsp;
								<spring:message code="label.contoCorrente" />:&nbsp;<b><c:out value="${datiRimbCosapTosap.contoCorrente}"/></b>,
								<spring:message code="label.importoDocumento" />:&nbsp;<b><c:out value="${datiRimbCosapTosap.importoDocumento}"/></b>
							</div>
							
							<c:if test="${tipoOsap == 'p'}">
								<tr>
									<th>
										<spring:message	code="label.numero" />
									</th>
									<th>
										<spring:message	code="label.indirizzoUtenza" />
									</th>
									<th>
										<spring:message	code="label.zonaUtenza" />
									</th>
									<th>
										<spring:message	code="label.superficie" />
									</th>
									<th>
										<spring:message	code="label.descrizioneTariffa" />
									</th>
									<th>
										<spring:message	code="label.mesi" />
									</th>
									<th>
										<spring:message	code="label.importoDaPagare" />
									</th>
									<th>
										<spring:message	code="label.importoDovuto" />*
									</th>
								</tr>
								<c:forEach items="${datiRimbCosapTosap.posizioniOsapMap}" var="posizioneOsap" varStatus="status">
									<tr>
										<td>
											<c:out value="${status.count}"/>)
										</td>
										<td>
											<c:out value="${posizioneOsap.value.indirizzoUtenza}"/>
										</td>
										<td>
											<c:out value="${posizioneOsap.value.zonaUtenza}"/>
										</td>
										<td>
											<c:out value="${posizioneOsap.value.superficie}"/>
										</td>
										<td>
											<c:out value="${posizioneOsap.value.descrizioneTariffa}"/>
										</td>
										<td>
											<c:out value="${posizioneOsap.value.mesi}"/>
										</td>
										<td>
											<fmt:formatNumber value="${posizioneOsap.value.importoDaPagare}" type="currency" currencySymbol="&euro;"/>
										</td>
										<td>
											<input type="text" name="importoDovuto" value="${posizioneOsap.value.importoDovuto}">
											<form:errors path="importoDovuto" cssStyle="color:red"/>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${tipoOsap == 't'}">
								<tr>
									<th>
										<spring:message	code="label.numero" />
									</th>
									<th>
										<spring:message	code="label.indirizzoUtenza" />
									</th>
									<th>
										<spring:message	code="label.zonaUtenza" />
									</th>
									<th>
										<spring:message	code="label.superficie" />
									</th>
									<th>
										<spring:message	code="label.descrizioneTariffa" />
									</th>
									<th>
										<spring:message	code="label.dataPagamento" />
									</th>
									<th>
										<spring:message	code="label.importoDaPagare" />
									</th>
									<th>
										<spring:message	code="label.importoPagato" />
									</th>
									<th>
										<spring:message	code="label.importoDovuto" />
									</th>
								</tr>
								<c:forEach items="${datiRimbCosapTosap.posizioniOsapMap}" var="posizioneOsap" varStatus="status">
									<tr>
										<td>
											<c:out value="${status.count}"/>)
										</td>
										<td>
											<c:out value="${posizioneOsap.value.indirizzoUtenza}"/>
										</td>
										<td>
											<c:out value="${posizioneOsap.value.zonaUtenza}"/>
										</td>
										<td>
											<c:out value="${posizioneOsap.value.superficie}"/>
										</td>
										<td>
											<c:out value="${posizioneOsap.value.descrizioneTariffa}"/>
										</td>
										<td>
											<fmt:formatDate value="${posizioneOsap.value.dataPagamento}" pattern="dd/MM/yyyy"/>
										</td>
										<td>
											<fmt:formatNumber value="${posizioneOsap.value.importoDaPagare}" type="currency" currencySymbol="&euro;"/>
										</td>
										<td>
											<fmt:formatNumber value="${posizioneOsap.value.importoPagato}" type="currency" currencySymbol="&euro;"/>
										</td>
										<td>
											<input type="text" name="importoDovuto" value="${posizioneOsap.value.importoDovuto}">
											<form:errors path="importoDovuto" cssStyle="color:red"/>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</c:when>
						<c:otherwise>
							<p><strong class="red"><spring:message code="errore.pdds.codice3"/></strong></p>
						</c:otherwise>
					</c:choose>
			</fieldset>
			<%@ include file="../common/rimborsiDatiGenerali.jsp"%>
			<%@ include file="../common/footer.jsp"%>
			<div class="buttonsDiv">
				<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
			</div>	
		</c:if>
		<c:if test="${!empty download}">
			<div class="container_pulsante">
				<a href="${rimborsoReportURL}" class="custom_pulsante"><spring:message code="link.dichiarazione" /></a>
				<span class="spacer"></span>
				<a href="<portlet:renderURL portletMode="view"/>" class="custom_pulsante"><spring:message code="button.home" /></a>
			</div>
		</c:if>
	</form:form>
</c:when>
	<c:otherwise>
			<div>
				<strong><spring:message code="errore.pdds.codice3"/></strong>
			</div>								
		</c:otherwise>
	</c:choose>