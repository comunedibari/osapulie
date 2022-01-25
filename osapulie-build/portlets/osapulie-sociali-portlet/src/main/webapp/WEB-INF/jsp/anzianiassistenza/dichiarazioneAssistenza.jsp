<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneUrlCambio">
	<portlet:param name="ope" value="cambioSoggetto" />
</portlet:actionURL>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="anzianiAssistenzaReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv dichiarazioneAssistenza">
	<form:form id="${idFormCambio}" action="${dichiarazioneUrlCambio}" method="post" commandName="soggettoRichiedente">
		<c:if test="${empty download}">
	
			<div class="marginBottom10">
				<label><strong><spring:message code="label.soggetto" />:</strong></label>&nbsp;&nbsp;
				<select name="codFisc">
					<c:forEach var="item" begin="0" items="${componentiNucleoFamiliare}">
						<option value="${item.codiceFiscale}" <c:if test="${soggettoRichiedente.cf == item.codiceFiscale}"> selected="selected" </c:if>="">${item.cognome}
							${item.nome}</option>
					</c:forEach>
				</select>
				<input type="submit" name="cambio" value='<spring:message code="button.back" />'>
			</div>
		</c:if>
	</form:form>
		
	<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiDichiarazione">
		<input type="hidden" name="codFisc" value="${soggettoRichiedente.cf}" />
	
		<c:if test="${empty download}">
				<fieldset>
				<legend>
					<spring:message code="label.legendRichiedente" />
				</legend>
				<table class="genericTable">
					<tr>
						<td width="25%">
							<label><spring:message code="label.cognome" />:</label>
						</td>
						<td width="25%">${datiDichiarazione.cognome}</td>
						<td width="25%">
							<label><spring:message code="label.nome" />:</label>
						</td>
						<td width="25%">${datiDichiarazione.nome}</td>
					</tr>
					<tr>
						<td width="25%">
							<label><spring:message code="label.dataN" />:</label>
						</td>
						<td colspan="3">${datiDichiarazione.dataNascita}</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comN" />:</label>
						</td>
						<td>
							${datiDichiarazione.comuneNascita}
						</td>
						<td>
							<label><spring:message code="label.provN" />:</label>
						</td>
						<td>
							${datiDichiarazione.provinciaNascita}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comuneResidenza" />:</label>
						</td>
						<td>
							${datiDichiarazione.comuneResidenza}
						</td>
						<td>
							<label><spring:message code="label.provRes" />:</label>
						</td>
						<td>
							${datiDichiarazione.provinciaResidenza}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.indRes" />:</label>
						</td>
						<td colspan="3">
							${datiDichiarazione.indirizzoResidenza}
						</td>
					</tr>
					<tr>
						<td width="25%">
							<label><spring:message code="label.telefono" />*: </label>
						</td>
						<td width="25%">
							<form:input path="telefono" /> 
							<div><form:errors path="telefono" cssStyle="color:red"/></div>
						</td>
						<td width="25%">
							<label><spring:message code="label.tesseraSanitaria" />:</label>
						</td>
						<td width="25%">
							<form:input path="codTesseraSanitaria" /> 
							<div><form:errors path="codTesseraSanitaria" cssStyle="color:red"/></div>
						</td>
					</tr>
				</table> 
			</fieldset>
			<fieldset>
				<legend><spring:message code="label.legendAffine" /></legend>
			<table class="genericTable">
				<tr>
					<td width="25%">
						<label><spring:message code="label.cognome" />*:</label>
					</td>
					<td width="25%">
						<form:input path="cognomeAffine" /> 
						<div><form:errors path="cognomeAffine" cssStyle="color:red"/></div>
					</td>
					<td width="25%">
						<label><spring:message code="label.nome" />*:</label>
					</td>
					<td width="25%">
						<form:input path="nomeAffine" /> 
						<div><form:errors path="nomeAffine" cssStyle="color:red"/></div>
					</td>
				</tr>					
				<tr>
					<td>
						<label><spring:message code="label.indRes" />*: </label>
					</td>
					<td>
						<form:input path="viaAffine" /> 
						<div><form:errors path="viaAffine" cssStyle="color:red"/></div> 
					</td>
					<td>
						<label><spring:message code="label.numCiv" />*:</label>
					</td>
					<td>
						<form:input path="civicoAffine" /> 
						<div><form:errors path="civicoAffine" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.telefono" />*: </label>
					</td>
					<td colspan="3">
						<form:input path="telefonoAffine" /> 
						<div><form:errors path="telefonoAffine" cssStyle="color:red"/></div> 
					</td>
				</tr>
			</table>
		</fieldset>
			<fieldset>
			 	<legend><spring:message code="label.allegatouno" /></legend>
			 	
			 	<table class="genericTable">
			 		<tr>
			 			<td colspan="2">
							<label><spring:message code="label.dichiarazione" /></label>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td width="50%">
							<form:radiobutton path="etaPensionabile" value="false" />&nbsp;&nbsp;<spring:message code="label.etaPensionabileSi" />
			 			</td>
			 			<td width="50%">
							<form:radiobutton path="etaPensionabile" value="true" />&nbsp;&nbsp;<spring:message code="label.etaPensionabileNo" />
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
							<form:radiobutton id="coniugatoBtnNo" path="coniugato" value="false" />&nbsp;&nbsp;<spring:message code="label.noParenti" />
			 			</td>
			 			<td>
							<form:radiobutton id="coniugatoBtnSi" path="coniugato" value="true" />&nbsp;&nbsp;<spring:message code="label.noAssistenza" />
							<div id="coniugatoDiv" style="display:none" class="marginTop5">
								<label><spring:message code="label.motiviConiugato" />*: </label>
								<br>
							  	<form:textarea path="motiviConiugato" rows="5" cols="50"  />
							  	<div><form:errors path="motiviConiugato" cssStyle="color:red"/></div>			
							</div>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
							<form:radiobutton id="comuniBtnNo" path="serviziSimiliComune" value="false" />&nbsp;&nbsp;<spring:message code="label.noServiziSimiliComune" />
			 			</td>
			 			<td>
							<form:radiobutton id="comuniBtnSi" path="serviziSimiliComune" value="true" />&nbsp;&nbsp;<spring:message code="label.siServiziSimiliComune" />
							 <div id="serviziComuneDiv" style="display:none" class="marginTop5">
								 <form:textarea path="serviziComune" rows="5" cols="50"  />
								<div><form:errors path="serviziComune" cssStyle="color:red"/></div>			
							</div>		
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
							 <form:radiobutton id="entiBtnNo" path="serviziSimiliEnte" value="false" />&nbsp;&nbsp;<spring:message code="label.noServiziSimiliEnti" />
			 			</td>
			 			<td>
							<form:radiobutton id="entiBtnSi" path="serviziSimiliEnte" value="true" />&nbsp;&nbsp;<spring:message code="label.siServiziSimiliEnti" />
							<div id="serviziEntiDiv" style="display:none" class="marginTop5">
								<form:textarea path="serviziEntiPubblici" rows="5" cols="50"  />
								<div><form:errors path="serviziEntiPubblici" cssStyle="color:red"/></div>		
							</div>
			 			</td>
					</tr>			 				
			 	</table>
			</fieldset>
				
			<fieldset>
				<legend><spring:message code="label.allegatodue" /></legend>
				
				<label class="marginBottom5"><spring:message code="label.dichiarazioneStatoFamiglia" /></label>
				<table summary="Elenco componenti" class="elencoRisultati genericTable">
					<thead>
						<tr>
							<th width="33%"><spring:message code="label.cognomeNome" /></th>
							<th width="33%"><spring:message code="label.dataNascita" /></th>
							<th width="33%"><spring:message code="label.gradoParentela" /></th>
						</tr>
					</thead>
					<tbody> 
						<c:forEach var="familiare" begin="0" items="${datiDichiarazione.parenti}" >
							<tr>
								<td><div>${familiare.cognome} ${familiare.nome}</div></td>
								<td><div>${familiare.dataNascita}</div></td>
								<td><div>${familiare.parentela}</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table> 
			</fieldset>
				
			<%@ include file="../common/footer.jsp" %>
			
			<div class="container_pulsante">
				<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
			</div>
				
		</c:if>
		
		<c:if test="${!empty download}">
			<div class="container_pulsante">
				<a href="${dichiarazioneReportURL}" class="custom_pulsante"><spring:message code="link.dichiarazione" /></a>
				<span class="spacer"></span>
				<a href="<portlet:renderURL portletMode="view"/>" class="custom_pulsante"><spring:message code="button.home" /></a>
			</div>
		</c:if>
	</form:form>
</div>