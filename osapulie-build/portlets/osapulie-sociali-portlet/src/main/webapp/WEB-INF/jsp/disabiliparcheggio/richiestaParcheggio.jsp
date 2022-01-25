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
	id="disabiliParcheggioReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv richiestaParcheggio">
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
		
	<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiParcheggio">
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
						<td width="25%">
							${datiParcheggio.cognome}
						</td>
						<td width="25%">
							<label><spring:message code="label.nome" />:</label>
						</td>
						<td width="25%">
							${datiParcheggio.nome}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.dataN" />:</label>
						</td>
						<td colspan="3">
							${datiParcheggio.dataNascita}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comN" />:</label>
						</td>
						<td>
							${datiParcheggio.comuneNascita}
						</td>
						<td>
							<label><spring:message code="label.provN" />:</label>
						</td>
						<td>
							${datiParcheggio.provinciaNascita}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.cittadinanza" />*:</label>
						</td>
						<td>
							<form:input path="cittadinanza" /> 
							<div><form:errors path="cittadinanza" cssStyle="color:red"/></div>
						</td>
						<td>
							<label><spring:message code="label.stato" />*:</label>
						</td>
						<td>
							<form:input path="stato" /> 
							<div><form:errors path="stato" cssStyle="color:red"/></div>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comuneResidenza" />:</label>
						</td>
						<td>
							${datiParcheggio.comuneResidenza}
						</td>
						<td>
							<label><spring:message code="label.provRes" />:</label>
						</td>
						<td>
							${datiParcheggio.provinciaResidenza}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.indRes" />:</label>
						</td>
						<td>
							${datiParcheggio.indirizzoResidenza}
						</td>
						<td>
							<label><spring:message code="label.cap" />*:</label>
						</td>
						<td>
							<form:input path="capResidenza" /> 
							<div><form:errors path="capResidenza" cssStyle="color:red"/></div>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.telefono" />*:</label>
						</td>
						<td>
							<form:input path="telefono" /> 
							<div><form:errors path="telefono" cssStyle="color:red"/></div>
						</td>
						<td>
							<label><spring:message code="label.email" />*:</label>
						</td>
						<td>
							<form:input path="email" /> 
							<div><form:errors path="email" cssStyle="color:red"/></div>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.fax" />:</label>
						</td>
						<td colspan="3">
							<form:input path="fax" /> 
							<div><form:errors path="fax" cssStyle="color:red"/></div>
						</td>
					</tr>
				</table> 
			</fieldset>
			
			<table class="genericTable">
				<tr>
					<td>
						<label><spring:message code="label.tipoRichiedente" />: </label>
					</td>
					<td>
						<form:select id="selectTipo" path="ruolo" items="${ruoliRichiedente}"  />						 
						<div><form:errors path="ruolo" cssStyle="color:red"/></div>
					</td>
				</tr>
			</table>
			
			<!-- Dati del disabile nel caso il richiedente NON sia il disabile  -->
			<div id="disabileDiv" style="display:none" class="marginBottom10">
				<fieldset>
					<legend><spring:message code="label.legendDisabile" /></legend>
					<table class="genericTable">
						<tr>
							<td width="25%">
								<label><spring:message code="label.cognome" />*:</label>
							</td>
							<td width="25%">
								<form:input path="disCognome" /> 
								<div><form:errors path="disCognome" cssStyle="color:red"/></div>
							</td>
							<td width="25%">
								<label><spring:message code="label.nome" />*:</label>
							</td>
							<td width="25%">
								<form:input path="disNome" /> 
								<div><form:errors path="disNome" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
							</td>
							<td colspan="3">
								<form:input path="disDataNascita" id="data_nascita_input" size="10" cssClass="data_input"/>
								<div><form:errors path="disDataNascita" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.comN" />*:</label>
							</td>
							<td>
								<form:input path="disComuneNasc" /> 
								<div><form:errors path="disComuneNasc" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message code="label.provN" />*: </label>
							</td>
							<td>
								<form:input path="disProvinciaNasc" /> 
								<div><form:errors path="disProvinciaNasc" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.comuneResidenza" />*:</label>
							</td>
							<td>
								<form:input path="disComuneRes" /> 
								<div><form:errors path="disComuneRes" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message code="label.provRes" />*: </label>
							</td>
							<td>
								<form:input path="disProvinciaRes" /> 
								<div><form:errors path="disProvinciaRes" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.indRes" />*: </label>
							</td>
							<td>
								<form:input path="disIndirizzoRes" /> 
								<div><form:errors path="disIndirizzoRes" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message code="label.numCiv" />*:</label>
							</td>
							<td>
								<form:input path="disNumCivico" /> 
								<div><form:errors path="disNumCivico" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.telefono" />: </label>
							</td>
							<td>
								<form:input path="disTelefono" /> 
								<div><form:errors path="disTelefono" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message code="label.email" />: </label>
							</td>
							<td>
								<form:input path="disEmail" /> 
								<div><form:errors path="disEmail" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.fax" />: </label>
							</td>
							<td colspan="3">
								<form:input path="disFax" /> 
								<div><form:errors path="disFax" cssStyle="color:red"/></div>
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
			
			<fieldset>
				<legend><spring:message code="label.chiede" /></legend>
				<table class="genericTable">
					<tr>
						<td width="25%">
							<label><spring:message code="label.tipoRichiesta" />: </label>
						</td>
						<td>
							<form:select id="selectPass" path="richiesta" items="${tipiRichiestaPass}"  />						 
							<div><form:errors path="richiesta" cssStyle="color:red"/></div>
						</td>
					</tr>
				</table>
				
				<div id="passDiv" style="display:none" class="marginBottom10">
					<table class="genericTable">
						<tr>
							<td width="25%">
								<label><spring:message code="label.numeroPass" />*:</label>
							</td>
							<td width="25%">
								<form:input path="numeroPass" /> 
								<div><form:errors path="numeroPass" cssStyle="color:red"/></div>
							</td>
							<td width="25%">
								<label><spring:message code="label.dataScadenza" />*:</label>
							</td>
							<td width="25%">
								<form:input path="scadenzaPass" id="data_scadenzapass_input" size="10" cssClass="data_input"/>
								<div><form:errors path="scadenzaPass" cssStyle="color:red"/></div>
							</td>
						</tr>
					</table>
				</div>
				
			</fieldset>
			
			<fieldset>
				<legend><spring:message code="label.legendDelega" /></legend>
				<table class="genericTable">
					<tr>
						<td width="25%">
							<label><spring:message code="label.cognome" />:</label>
						</td>
						<td width="25%">
							<form:input path="delCognome" /> 
							<div><form:errors path="delCognome" cssStyle="color:red"/></div>
						</td>
						<td width="25%">
							<label><spring:message code="label.nome" />:</label>
						</td>
						<td width="25%">
							<form:input path="delNome" /> 
							<div><form:errors path="delNome" cssStyle="color:red"/></div>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy):</label>
						</td>
						<td colspan="3">
							<form:input path="delDataNascita" id="data_nascitadel_input" size="10" cssClass="data_input"/>
							<div><form:errors path="delDataNascita" cssStyle="color:red"/></div>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comN" />:</label>
						</td>
						<td>
							<form:input path="delComuneNasc" /> 
							<div><form:errors path="delComuneNasc" cssStyle="color:red"/></div>
						</td>
						<td>
							<label><spring:message code="label.provN" />: </label>
						</td>
						<td>
							<form:input path="delProvinciaNasc" /> 
							<div><form:errors path="delProvinciaNasc" cssStyle="color:red"/></div> 
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comuneResidenza" />:</label>
						</td>
						<td>
							<form:input path="delComuneRes" /> 
							<div><form:errors path="delComuneRes" cssStyle="color:red"/></div>
						</td>
						<td>
							<label><spring:message code="label.provRes" />: </label>
						</td>
						<td>
							<form:input path="delProvinciaRes" /> 
							<div><form:errors path="delProvinciaRes" cssStyle="color:red"/></div> 
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.indRes" />: </label>
						</td>
						<td>
							<form:input path="delIndirizzoRes" /> 
							<div><form:errors path="delIndirizzoRes" cssStyle="color:red"/></div> 
						</td>
						<td>
							<label><spring:message code="label.numCiv" />:</label>
						</td>
						<td>
							<form:input path="delNumCivico" /> 
							<div><form:errors path="delNumCivico" cssStyle="color:red"/></div>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.telefono" />: </label>
						</td>
						<td colspan="3">
							<form:input path="delTelefono" /> 
							<div><form:errors path="delTelefono" cssStyle="color:red"/></div>
						</td>
					</tr>
				</table>
			</fieldset>
				
			<div>
				<label><spring:message code="label.trattamentoDati" />:</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<form:radiobutton path="trattDatiPersonali" value="false" />&nbsp;&nbsp;<spring:message code="label.no" />&nbsp;&nbsp;&nbsp;&nbsp;
				<form:radiobutton path="trattDatiPersonali" value="true" />&nbsp;&nbsp;<spring:message code="label.si" />
			</div>
			
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