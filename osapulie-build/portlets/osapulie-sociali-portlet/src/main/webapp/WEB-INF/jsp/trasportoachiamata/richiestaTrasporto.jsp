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
	id="richiestaTrasportoReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv richiestaTrasporto">
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
		
	<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiDichiarazione">
		<input type="hidden" name="codFisc" value="${soggettoRichiedente.cf}" />
	
		<c:if test="${empty download}">
		
			<table class="genericTable">
				<tr>
					<td width="25%">
						<label><spring:message code="label.tipoRichiesta" />: </label>
					</td>
					<td>
					    <form:select path="tipoRichiesta">  
			                <form:option value="Prima richiesta"><spring:message code="label.primaRichiesta" /></form:option>  
			                <form:option value="Rinnovo"><spring:message code="label.rinnovo" /></form:option>  
			            </form:select>  
						<div><form:errors path="tipoRichiesta" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.tipoRichiedente" />*: </label>
					</td>
					<td>
						<form:select id="selectTipo" path="tipoRichiedente" items="${ruoliRichiedente}"  />						 
						<div><form:errors path="tipoRichiedente" cssStyle="color:red"/></div>
					</td>
				</tr>
			</table>
			<!-- Dati del disabile nel caso il richiedente NON sia il disabile  -->
			<div id="disabileDiv" style="display:none" >
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
					</table>
				</fieldset>
			</div>
				
			<table class="genericTable">
				<tr>
					<td width="25%">
						<label><spring:message code="label.telefono" />*: </label>
					</td>
					<td width="25%">
						<form:input path="disTelefono" /> 
						<div><form:errors path="disTelefono" cssStyle="color:red"/></div> 
					</td>
					<td width="25%">
						<label><spring:message code="label.email" />*: </label>
					</td>
					<td width="25%">
						<form:input path="disEmail" /> 
						<div><form:errors path="disEmail" cssStyle="color:red"/></div> 
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.sediaARotelle" />*: </label>
					</td>
					<td colspan="3">
				   		<form:radiobutton path="sediaARotelle" value="false" />&nbsp;&nbsp;<spring:message code="label.no" />&nbsp;&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="sediaARotelle" value="true" />&nbsp;&nbsp;<spring:message code="label.si" />
					</td>
				</tr>
			</table>

			 <table class="genericTable">
				<tr>
					<td width="25%">
						 <label><spring:message code="label.isee" />*: </label>
					</td>
					<td width="25%">
					  	<form:input path="isee" /> 
					  	<div><form:errors path="isee" cssStyle="color:red"/></div> 
					</td>
					<td width="25%">
						 <label><spring:message code="label.annoIsee" />*: </label>
					</td>
					<td width="25%">
					  	<form:input path="annoIsee" /> 
					  	<div><form:errors path="annoIsee" cssStyle="color:red"/></div> 
					</td>
				</tr>
				<tr>
					<td width="25%">
					  	<label><spring:message code="label.tipoSportello" />: </label>
					</td>
					<td width="25%">
						<form:select path="tipoSportello">  
			                <form:option value="INPS"><spring:message code="label.inps" /></form:option>  
			                <form:option value="CAF"><spring:message code="label.caf" /></form:option>  
			            </form:select>  
					</td>
				   <td>
					 	<label><spring:message code="label.sportello" />: </label>
					</td>
					<td>
					 	<form:input path="sportello" /> 
					  	<div><form:errors path="sportello" cssStyle="color:red"/></div> 
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.via" />: </label>
					</td>
					<td>
					 	<form:input path="indirizzoSportello" /> 
					  	<div><form:errors path="indirizzoSportello" cssStyle="color:red"/></div> 
					</td>
				</tr>
			</table>

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