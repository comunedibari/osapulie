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
	id="iscrVolontariatoReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv iscrizioneVolontariato">
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
							<label><spring:message code="label.provRes" />:</label>
						</td>
						<td>
							${soggettoRichiedente.provinciaResidenza}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.indRes" />:</label>
						</td>
						<td colspan="3">
							${soggettoRichiedente.indirizzoResidenza}
						</td>
					</tr>
				</table> 
			</fieldset>
		 </c:if>
	</form:form>
		
	<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiVolontariato">
		<input type="hidden" name="codFisc" value="${soggettoRichiedente.cf}" />
	
			<c:if test="${empty download}">
			
			<fieldset>
				<legend><spring:message code="label.organizzazione" /></legend>
			  	<table class="genericTable">
					<tr>
						<td width="25%">
							<label><spring:message code="label.ruoloRichiedente" />: </label>
						</td>
						<td>
							<form:select path="ruolo" items="${ruoliRichiedente}" />
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.nomeOrganizzazione" />*: </label>
						</td>
						<td>
							<form:input path="organizzazione" /> 
							<div><form:errors path="organizzazione" cssStyle="color:red"/></div>
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.indirizzoSedeLegale" />*: </label>
						</td>
						<td>
							<form:input path="indirizzo" /> 
							<div><form:errors path="indirizzo" cssStyle="color:red"/></div>
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.telefono" />*: </label>
						</td>
						<td>
							<form:input path="telefono" /> 
							<div><form:errors path="telefono" cssStyle="color:red"/></div> 	
						</td>	  
					</tr>
					<tr>
					  	<td>
							<label><spring:message code="label.cfOrganizzazione" />*: </label>
						</td>
						<td>
							<form:input path="cfAssociazione" /> 
							<div><form:errors path="cfAssociazione" cssStyle="color:red"/></div>
						</td>	  
					</tr>
					<tr>
					  	<td>
							<label><spring:message code="label.dataCostituzione" />&nbsp;(dd/mm/yyyy)*:</label>
					  	</td>
					  	<td>
					   		<form:input path="dataCostituzione" id="data_costituzione_input" size="10" cssClass="data_input"/>
					   		<div><form:errors path="dataCostituzione" cssStyle="color:red"/></div>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comuneCostituzione" />*: </label>
						</td>
						<td>
							<form:input path="comuneCostituzione" /> 
							<div><form:errors path="comuneCostituzione" cssStyle="color:red"/></div>
						</td>	  
					</tr>	
					<tr>
						<td>
							<label><spring:message code="label.provinciaCostituzione" />*: </label>
						</td>
						<td>
							<form:input path="provCostituzione" /> 
							<div><form:errors path="provCostituzione" cssStyle="color:red"/></div> 	
						</td>	  
					</tr>
					<tr>
					  	<td>
							<label><spring:message code="label.areeIntervento" />: </label>
						</td>
						<td>
							<form:checkboxes element="div" path="aree" items="${areeIntervento}" /> 
							<div><form:errors path="aree" cssStyle="color:red"/></div>	
						</td>	  
					</tr>
				</table>
			</fieldset>
			
			<fieldset>
				<legend><spring:message code="label.legendArticoli" /></legend>
				<table class="genericTable">
					<tr>
						<td width="50%">
							<label><spring:message code="label.artDemocraticita" />: </label>
						</td>
						<td>
							<form:input path="artDemocraticita" /> 
							<div><form:errors path="artDemocraticita" cssStyle="color:red"/></div> 	
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.artNoFiniLucro" />: </label>
						</td>
						<td>
							<form:input path="artNoFiniLucro" /> 
							<div><form:errors path="artNoFiniLucro" cssStyle="color:red"/></div>
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.artElettivitaCariche" />: </label>
						</td>
						<td>
							<form:input path="artElettivitaCariche" /> 
							<div><form:errors path="artElettivitaCariche" cssStyle="color:red"/></div>	
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.artGratuitaPrestaz" />: </label>
						</td>
						<td>
							<form:input path="artGratuitaPrestaz" /> 
							<div><form:errors path="artGratuitaPrestaz" cssStyle="color:red"/></div> 	
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.artCriteriAmmissione" />: </label>
						</td>
						<td>
							<form:input path="artCriteriAmmissione" /> 
							<div><form:errors path="artCriteriAmmissione" cssStyle="color:red"/></div>	
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.artDirittiEObblighi" />: </label>
						</td>
						<td>
							<form:input path="artDirittiEObblighi" /> 
							<div><form:errors path="artDirittiEObblighi" cssStyle="color:red"/></div> 	
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.artFormazione" />: </label>
						</td>
						<td>
							<form:input path="artFormazione" /> 
							<div><form:errors path="artFormazione" cssStyle="color:red"/></div>	
						</td>	  
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.artDevoluzione" />: </label>
						</td>
						<td>
							<form:input path="artDevoluzione" /> 
							<div><form:errors path="artDevoluzione" cssStyle="color:red"/></div> 	
						</td>	  
					</tr>
				</table>
			</fieldset>
		
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