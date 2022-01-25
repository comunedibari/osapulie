<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneUrlCambio">
	<portlet:param name="ope" value="cambioSoggetto" />
</portlet:actionURL>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="dichiarazioneCambioDomicilioReport" escapeXml="false">
	<portlet:param name="codFisc"
		value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv richiestaCertificato">
	<c:if test="${empty datiAnagrafici.errore}">
	
	<form:form id="${idFormCambio}" action="${dichiarazioneUrlCambio}" method="post" commandName="datiAnagrafici">
		<div class="marginBottom10">
			<label><strong><spring:message code="label.soggetto" />:</strong></label>&nbsp;&nbsp;
			<select name="codFisc">
				<c:forEach var="item" begin="0" items="${datiAnagrafici.componentiNucleoFamiliare}">
					<option value="${item.codiceFiscale}" <c:if test="${componenteFamiglia.codiceFiscale == item.codiceFiscale}"> selected="selected" </c:if>="">${item.cognome}
						${item.nome}</option>
				</c:forEach>
			</select>
			<input type="submit" name="cambio" value='<spring:message code="button.back" />'>
		</div>
		<fieldset>
			<legend>
				<spring:message code="label.riepilogo" />
			</legend>
			<table class="genericTable">
				<tr>
					<td width="180">
						<label><spring:message code="label.cf" />:</label>
					</td>
						<td >
							${componenteFamiglia.codiceFiscale}
						</td>
					</tr>
					<tr>
						<td width="180">
							<label><spring:message code="label.nome" />:</label>
						</td>
						<td>
							${componenteFamiglia.nome}
						</td>
					</tr>
					<tr>
						<td width="180">
							<label><spring:message code="label.cognome" />:</label>
						</td>
						<td>
							${componenteFamiglia.cognome}
						</td>
					</tr>
					<tr>
						<td width="180">
							<label><spring:message code="label.dataN" />:</label>
						</td>
						<td>
							<fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataNascita.time}" /> 
						</td>
					</tr>
					<tr>
						<td width="180">
							<label><spring:message code="label.comN" />:</label>
						</td>
						<td>
							${comuneNascita}
						</td>
					</tr>
					<tr>
						<td width="180">
							<label><spring:message code="label.indRes" />:</label>
						</td>
						<td>
							${datiAnagrafici.toponimoIndirizzo}
							${datiAnagrafici.descrizioneVia} ${datiAnagrafici.numeroCivico}
							<c:if test="${!empty datiAnagrafici.esponente}">
								${datiAnagrafici.esponente}  
							</c:if>
							<c:if test="${!empty datiAnagrafici.piano}">
								p.${datiAnagrafici.piano}  
							</c:if>
							<c:if test="${!empty datiAnagrafici.scala}">
								s.${datiAnagrafici.scala}
							</c:if>
						</td>
					</tr>
				</table> 
			</fieldset>
		</form:form>
		
		<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post">
			<c:if test="${empty download}">
				<fieldset>
					<legend>
						<spring:message code="label.legend" />
					</legend>
					<input type="hidden" name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
					
					<table class="genericTable">
						<tr>
							<td width="180">
								<label><spring:message code="label.nuovoIndir" />*:</label>
							</td>
							<td width="180">
								<input name="nuovoIndir" id="nuovoIndir" type="text" maxlength="255" size="50" value="<c:if test="${!empty dichiarazione}">${dichiarazione.nuovoIndir}</c:if>" />
<%-- 								<div><form:errors path="nuovoIndir" cssStyle="color:red"/></div> --%>
							</td>
							<td width="180">
								<label><spring:message code="label.numCiv" />*:</label>
							</td>
							<td colspan="3">
								<input name="civico" id="civico" type="text" maxlength="5" size="3" value="<c:if test="${!empty dichiarazione}">${dichiarazione.civico}</c:if>"/>
<%-- 								<div><form:errors path="civico" cssStyle="color:red"/></div> --%>
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.interno" />:</label>
							</td>
							<td width="180">
								<input name="interno" id="interno" type="text" maxlength="5" size="3" value="<c:if test="${!empty dichiarazione}">${dichiarazione.interno}</c:if>"/>
							</td>
							<td width="180">
								<label><spring:message code="label.scala" />:</label>
							</td>
							<td>
								<input name="scala" id="scala" type="text" maxlength="3" size="3" value="<c:if test="${!empty dichiarazione}">${dichiarazione.scala}</c:if>"/>
							</td>
							<td>
								<label><spring:message code="label.piano" />:</label>
							</td>
							<td>
								<input name="piano" id="piano" type="text" maxlength="3" size="3" value="<c:if test="${!empty dichiarazione}">${dichiarazione.piano}</c:if>"/>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<label><spring:message code="label.parente" />:</label>
							</td>
						</tr>
					</table>
		
					<table summary="Elenco componenti" id="elencoRisultati" class="elencoRisultati genericTable">
						<thead> <tr> <th>&nbsp;</th> <th>Cognome Nome</th> <th>Codice fiscale</th> <th>Data di nascita</th> <th>Comune di Nascita</th></tr></thead>
						<tbody> 
							<c:forEach var="familiare" begin="0" items="${datiAnagrafici.componentiNucleoFamiliare}">
								<c:if test="${!(componenteFamiglia.codiceFiscale == familiare.codiceFiscale)}"> 
									<tr>
										<td><div><input type="checkbox" name="<c:out value="parenti[]" />" value="${familiare.codiceFiscale}" /></div></td>
										<td><div>${familiare.cognome} ${familiare.nome}</div></td>
										<td><div>${familiare.codiceFiscale}</div></td>
										<td><div><fmt:formatDate pattern="dd/MM/yyyy" value="${familiare.dataNascita.time}" /></div></td>
										<td><div>${familiare.codiceIstatComuneNascita}</div></td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table> 
					<!--<c:forEach var="i" begin="1" end="7" step="1">
						<input name="<c:out value="parente${i}" />"
							id="<c:out value="parente${i}" />" type="text" maxlength="255"
							size="50" />
						<br />
						<br />
					</c:forEach>
					-->
					<%@ include file="../common/footer.jsp"%>
		
					<div class="container_pulsante">
						<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
					</div>
		
				</fieldset>
			</c:if>
			
			<c:if test="${!empty download}">
				<div class="container_pulsante">
					<a href="${dichiarazioneReportURL}" class="custom_pulsante"><spring:message code="link.dichiarazione" /></a>
					<span class="spacer"></span>
					<a href="<portlet:renderURL portletMode="view"/>" class="custom_pulsante"><spring:message code="button.home" /></a>
				</div>
			</c:if>
		</form:form>
	</c:if>
	<c:if test="${! empty datiAnagrafici.errore }">
		<strong><spring:message code="errore.pdds.codice${datiAnagrafici.errore.codice }" /></strong>
	</c:if>
</div>