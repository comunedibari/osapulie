<%@ include file="../common/common.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
</script>

<portlet:actionURL var="dichiarazioneUrlCambio">
	<portlet:param name="ope" value="cambioSoggetto" />
</portlet:actionURL>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="dichiarazioneCaseRiposoReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv dichiarazioneCaseRiposo">
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
			
			<fieldset>
				<legend>
					<spring:message code="label.legendRichiedente" />
				</legend>
				<table class="genericTable">
					<tr>
						<td width="25%">
							<label><spring:message code="label.cognome" />:</label>
						</td>
						<td width="25%">
							${soggettoRichiedente.cognome}
						</td>
						<td width="25%">
							<label><spring:message code="label.nome" />:</label>
						</td>
						<td width="25%">
							${soggettoRichiedente.nome}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.dataN" />:</label>
						</td>
						<td colspan="3">
							${soggettoRichiedente.dataNascita}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comN" />:</label>
						</td>
						<td>
							${soggettoRichiedente.comuneNascita}
						</td>
						<td>
							<label><spring:message code="label.provN" />:</label>
						</td>
						<td>
							${soggettoRichiedente.provinciaNascita}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comuneResidenza" />:</label>
						</td>
						<td>
							${soggettoRichiedente.comuneResidenza}
						</td>
						<td>
							<label><spring:message code="label.provRes" />: </label>
						</td>
						<td>
							${soggettoRichiedente.provinciaResidenza}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.indRes" />: </label>
						</td>
						<td colspan="3">
							${soggettoRichiedente.indirizzoResidenza}
						</td>
					</tr>
				</table> 
			</fieldset>
		 </c:if>
	</form:form>
		
	<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiDichiarazione">
		<input type="hidden" name="codFisc" value="${soggettoRichiedente.cf}" />
		<c:if test="${empty download}">
	
			<table class="genericTable">
				<tr>
					<td><label><spring:message code="label.telefono" />*: </label></td>
					<td>
						<form:input path="telefono" />
						<div><form:errors path="telefono" cssStyle="color:red"/></div>
					</td>
					<td><label><spring:message code="label.aTitoloPersonale" />:</label></td>
					<td>
						<form:select id="selectRuolo" path="ruolo" items="${ruoliRichiedente}"  />						 
						<div><form:errors path="ruolo" cssStyle="color:red"/></div>
					</td>
					<td>
						<div id="affinitaDiv" style="display:none">
							<label><spring:message code="label.ruolo" />:</label>
							<form:input path="affinita" /> 
							<div><form:errors path="affinita" cssStyle="color:red"/></div> 
						</div>
					</td>
				</tr>
			</table>
	
			<!-- Dati dell'anziano nel caso il richiedente sia parente o affine -->
			<div id="anzianoDiv" style="display:none">
				<fieldset>
					<legend><spring:message code="label.legendAnziano" /></legend>
					<table class="genericTable">
						<tr>
							<td width="25%">
								<label><spring:message code="label.cognome" />*:</label>
							</td>
							<td width="25%">
								<form:input path="anzCognome" /> 
								<div><form:errors path="anzCognome" cssStyle="color:red"/></div>
							</td>
							<td width="25%">
								<label><spring:message code="label.nome" />*:</label>
							</td>
							<td width="25%">
								<form:input path="anzNome" /> 
								<div><form:errors path="anzNome" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
							</td>
							<td colspan="3">
								<form:input path="anzDataNascita" id="data_nascita_input" size="10" cssClass="data_input"/>
								<div><form:errors path="anzDataNascita" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.comN" />*:</label>
							</td>
							<td>
								<form:input path="anzComuneNascita" />  
								<div><form:errors path="anzComuneNascita" cssStyle="color:red"/></div>
							</td>
								<td>
								<label><spring:message code="label.provN" />*:</label>
							</td>
							<td>
								<form:input path="anzProvinciaNascita" size="3"/> 
								<div><form:errors path="anzProvinciaNascita" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.comuneResidenza" />*:</label>
							</td>
							<td>
								<form:input path="anzComuneResidenza" /> 
								<div><form:errors path="anzComuneResidenza" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message code="label.provRes" />*: </label>
							</td>
							<td>
								<form:input path="anzProvinciaResidenza" /> 
								<div><form:errors path="anzProvinciaResidenza" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.indRes" />*: </label>
							</td>
							<td>
								<form:input path="anzIndirizzoResidenza" /> 
								<div><form:errors path="anzIndirizzoResidenza" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message code="label.numCiv" />*:</label>
							</td>
							<td>
								<form:input path="anzCivicoResidenza" /> 
								<div><form:errors path="anzCivicoResidenza" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.telefono" />*: </label>
							</td>
							<td colspan="3">
								<form:input path="anzTelefono" /> 
								<div><form:errors path="anzTelefono" cssStyle="color:red"/></div> 
							</td>
						</tr>
					</table>
				</fieldset>
			</div>

			<table class="genericTable">
				<tr>
					<td width="25%">
					 	<label><spring:message code="label.tipologia" /></label>
					</td>
					<td>
					 	<form:radiobutton path="tipologia" value="Casa di riposo" />&nbsp;<spring:message code="label.casaDiRiposo" />
					    <form:radiobutton path="tipologia" value="Casa protetta" />&nbsp;<spring:message code="label.casaProtetta" />
					    <div><form:errors path="tipologia" cssStyle="color:red"/></div> 
					</td>
				</tr>
				<tr>
					<td>
					 	<label><spring:message code="label.denominazione" /></label>
					</td>
					<td>
					 	<form:input path="denominazione" /> 
						<div><form:errors path="denominazione" cssStyle="color:red"/></div> 
					</td>
				</tr>
				<tr>
					<td>
					 	<label><spring:message code="label.ubicazione" /></label>
					</td>
					<td>
					 	<form:input path="ubicazione" /> 
						<div><form:errors path="ubicazione" cssStyle="color:red"/></div> 
					</td>
				</tr>
			</table>
		
			<fieldset>
				<legend><spring:message code="label.allegatouno" /></legend>
				<div id="datiAnagA1" style="display:none">
					<table class="genericTable">
						<tr>
							<td width="25%">
								<label><spring:message code="label.cognome" />*:</label>
							</td>
							<td width="25%">
								<form:input path="aUnoCognome" /> 
								<div><form:errors path="aUnoCognome" cssStyle="color:red"/></div>
							</td>
							<td width="25%">
								<label><spring:message code="label.nome" />*:</label>
							</td>
							<td width="25%">
								<form:input path="aUnoNome" /> 
								<div><form:errors path="aUnoNome" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.comN" />*:</label>
							</td>
							<td>
								<form:input path="aUnoComuneNascita" />  
								<div><form:errors path="aUnoComuneNascita" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message code="label.provN" />*:</label>
							</td>
							<td>
								<form:input path="aUnoProvinciaNascita" size="3"/> 
								<div><form:errors path="aUnoProvinciaNascita" cssStyle="color:red"/></div>
							</td>
						</tr>
					</table>
				</div>
			
				<table class="genericTable">
					<tr>
						<td colspan="2">
							<label><spring:message code="label.dichiarazioneSituazione" /></label>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<form:checkbox path="autosufficiente"/>&nbsp;&nbsp;<spring:message code="label.autosufficiente" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<form:checkbox path="patolTemporanea"/>&nbsp;&nbsp;<spring:message code="label.patologiaTemporanea" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<form:checkbox path="parzialmenteAutosuf"/>&nbsp;&nbsp;<spring:message code="label.parzialmenteAutosuf" />
						</td>
					</tr>
					<tr>
						<td width="50%">
							<form:radiobutton id="comuniBtnNo" path="serviziSimiliComune" value="false" />&nbsp;&nbsp;<spring:message code="label.noServiziComuni" />
						</td>
						<td>
							<form:radiobutton id="comuniBtnSi" path="serviziSimiliComune" value="true" />&nbsp;&nbsp;<spring:message code="label.siServiziComuni" />
							<div id="serviziComuneDiv" style="display:none" class="marginTop5">
								<form:textarea path="serviziComune" rows="5" cols="50"  />
								<div><form:errors path="serviziComune" cssStyle="color:red"/></div>			
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%">
							<form:radiobutton id="entiBtnNo" path="serviziSimiliEnte" value="false" />&nbsp;&nbsp;<spring:message code="label.noServiziEnti" />
						</td>
						<td>
							<form:radiobutton id="entiBtnSi" path="serviziSimiliEnte" value="true" />&nbsp;&nbsp;<spring:message code="label.siServiziEnti" />
							<div id="serviziEntiDiv" style="display:none" class="marginTop5">
								<form:textarea path="serviziEntiPubblici" rows="5" cols="50"  />
								<div><form:errors path="serviziEntiPubblici" cssStyle="color:red"/></div>		
							</div>
						</td>
					</tr>
				</table>
		
				<label><spring:message code="label.dichiarazioneStatoFamiglia" /></label>
				<table summary="Elenco componenti" class="elencoRisultati genericTable">
					<thead>
						<tr>
							<th width="33%"><spring:message code="label.cognomeNome" /></th>
							<th width="33%"><spring:message code="label.dataNascita" /></th>
							<th width="33%"><spring:message code="label.gradoParentela" /></th>
						</tr>
					</thead>
					<tbody> 
						<c:forEach var="familiare" begin="0" items="${datiDichiarazione.parenti}">
							<tr>
								<td><div>${familiare.cognome} ${familiare.nome}</div></td>
								<td><div>${familiare.dataNascita}</div></td>
								<td><div>${familiare.parentela}</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
				<label><spring:message code="label.dichiarazioneRedditoNucleoFamiliare" /></label>
				<table summary="Elenco componenti" class="elencoRisultati genericTable">
					<thead>
						<tr>
							<th width="33%"><spring:message code="label.cognomeNome" /></th>
							<th width="33%"><spring:message code="label.dataNascita" /></th>
							<th width="33%"><spring:message code="label.reddito" /></th>
						</tr>
					</thead>
					<tbody> 
						<c:forEach var="familiare" begin="0" items="${datiDichiarazione.parenti}" varStatus="status">
							<tr>
						    	<td><div>${familiare.cognome} ${familiare.nome}</div></td>
								<td><div>${familiare.dataNascita}</div></td>
								<td><div><form:input path="parenti[${status.index}].reddito"/></div></td>
							</tr>
	     				</c:forEach>
					</tbody>
	    		</table> 
			
				<table class="genericTable">
					<tr>
						<td width="50%">
							<form:radiobutton id="alimentiSiBtn" path="alimenti" value="true"/>&nbsp;&nbsp;<spring:message code="label.alimentiSi"/>
							<div id="alimentiSiRiquadro" style="display:none">
								<table summary="Elenco componenti" class="elencoRisultati genericTable">
									<thead>
										<tr>
											<th>&nbsp;</th>
											<th><spring:message code="label.cognomeNome" /></th>
											<th><spring:message code="label.gradoParentela" /></th>
										</tr>
									</thead>
									<tbody> 
										<c:forEach var="familiare" begin="0" items="${datiDichiarazione.parenti}" varStatus="status">
											<tr>
												<td><form:checkbox path="parenti[${status.index}].alimenti" /></td>
										    	<td><div>${familiare.cognome} ${familiare.nome}</div></td>
												<td><div>${familiare.parentela}</div></td>
											</tr>
					     				</c:forEach>
									</tbody>
					    		</table> 
							</div>
						</td>
						<td>
							<form:radiobutton id="alimentiNoBtn" path="alimenti" value="false"/>&nbsp;&nbsp;<spring:message code="label.alimentiNo"/>
							<div id="alimentiNoRiquadro" style="display:none">
								<%-- qui va un paragrafo, vedi modulo--%>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<form:radiobutton id="appProprioSi" path="appartamentoProprio" value="true"/>&nbsp;&nbsp;<spring:message code="label.appProprioSi"/>
						</td>
						<td>
							<form:radiobutton id="appProprioNo" path="appartamentoProprio" value="false"/>&nbsp;&nbsp;<spring:message code="label.appProprioNo"/>
							<div id="canoneDiv" style="display:none" class="marginTop5">
								<label><spring:message code="label.canone" />:</label>
								<form:input path="canoneAppartamento" />  
								<div><form:errors path="canoneAppartamento" cssStyle="color:red"/></div>
							</div>
						</td>
					</tr>
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