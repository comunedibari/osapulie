<%@ include file="../common/common.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
</script>

<portlet:actionURL var="autocertificazioneUrlCambio">
	<portlet:param name="ope" value="cambioSoggetto" />
</portlet:actionURL>

<portlet:actionURL var="autocertificazioneUrlGenera">
	<portlet:param name="ope" value="generaAutocertificazione" />
</portlet:actionURL>

<portlet:resourceURL var="autocertificazioneReportURL"
	id="autocertificazioneReport" escapeXml="false">
	<portlet:param name="codFisc"
		value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="produrreAutocertificazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv">
	<form:form id="${idFormCambio}" action="${autocertificazioneUrlCambio}" method="post" commandName="datiAutocertificazione">
		<c:if test="${!empty datiAnagrafici.componentiNucleoFamiliare}">
			<div class="marginBottom10">
				<label><spring:message code="label.soggetto" />:</label>&nbsp;&nbsp;
				<select name="codFisc">
					<c:forEach var="item" begin="0"
						items="${datiAnagrafici.componentiNucleoFamiliare}">
						<option value="${item.codiceFiscale}"
							<c:if test="${componenteFamiglia.codiceFiscale == item.codiceFiscale}"> selected="selected" </c:if>>${item.cognome}
							${item.nome}</option>
					</c:forEach>
				</select>&nbsp;&nbsp;
				<input type="submit" name="cambio" value="<spring:message code="button.cambiaSoggetto" />" />
			</div>	
		</c:if>
		<fieldset>
			<legend>
				<spring:message code="label.riepilogo" />
			</legend>
			<table class="genericTable">
				<tr>
					<td width="180">
						<label><spring:message code="label.cf" />:</label>
					</td>
					<td>
						${datiAutocertificazione.codiceFiscaleRichiedente}
					</td>
				</tr>
				<tr>
					<td width="180">
						<label><spring:message code="label.nome" />:</label>
					</td>
					<td>
						${datiAutocertificazione.nomeRichiedente}
					</td>
				</tr>
				<tr>
					<td width="180">
						<label><spring:message code="label.cognome" />:</label>
					</td>
					<td>
						${datiAutocertificazione.cognomeRichiedente}
					</td>
				</tr>
				<tr>
					<td width="180">
						<label><spring:message code="label.dataN" />:</label>
					</td>
					<td>
						<c:choose>
							<c:when test="${!empty datiAnagrafici.componentiNucleoFamiliare}">
								${datiAutocertificazione.dataNascitaRichiedente}
							</c:when>
							<c:otherwise>
								<form:input path="dataNascitaRichiedente" id="dataNascitaComponente" type="text" cssClass="data_input"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td width="180">
						<label><spring:message code="label.comN" />:</label>
					</td>
					<td>
						<c:choose>
							<c:when test="${!empty datiAnagrafici.componentiNucleoFamiliare}">
								${datiAutocertificazione.comuneNascitaRichiedente}
							</c:when>
							<c:otherwise>
								<form:input path="comuneNascitaRichiedente" id="comuneNascitaComponente" type="text"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td width="180">
						<label><spring:message code="label.comRes" />:</label>
					</td>
					<td>
						<c:choose>
							<c:when test="${!empty datiAnagrafici.componentiNucleoFamiliare}">
								${datiAutocertificazione.comuneResidenzaRichiedente}
							</c:when>
							<c:otherwise>
								<form:input path="comuneResidenzaRichiedente" id="comuneResidenzaRichiedente" type="text"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td width="180">
						<label><spring:message code="label.indRes" />:</label>
					</td>
					<td>
						<c:choose>
							<c:when test="${!empty datiAnagrafici.componentiNucleoFamiliare}">
								${datiAnagrafici.toponimoIndirizzo} ${datiAnagrafici.descrizioneVia} ${datiAnagrafici.numeroCivico}
								<c:if test="${!empty datiAnagrafici.esponente}">
									${datiAnagrafici.esponente}  
								</c:if>
								<c:if test="${!empty datiAnagrafici.piano}">
									p. ${datiAnagrafici.piano}  
								</c:if>
								<c:if test="${!empty datiAnagrafici.scala}">
									s. ${datiAnagrafici.scala}
								</c:if>
							</c:when>
							<c:otherwise>
								<form:input path="viaResidenzaRichiedente" id="viaResidenzaRichiedente" type="text"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
			<c:if test="${empty datiAnagrafici.componentiNucleoFamiliare}">
				<div class="center">
					<input type="submit" name="aggiorna" value="<spring:message code="button.aggiorna" />" />
				</div>
			</c:if>
		</fieldset>
	</form:form>
	
	<form:form id="${idForm}" action="${autocertificazioneUrlGenera}" method="post" commandName="datiAutocertificazione">
		<c:if test="${empty download}">
			<fieldset>
				<legend>
					<spring:message code="label.legend" />
				</legend>
				<input type="hidden" name="codFisc" value="${componenteFamiglia.codiceFiscale}" /> 
				<div class="marginBottom10">
					<label><spring:message code="label.tipologia" />:</label>&nbsp;&nbsp;
					<select name="tipologiaCertificato">
						<option></option>
						<option value="ATTONOTORIO" <c:if test="${!empty datiAutocertificazione.tipologia && datiAutocertificazione.tipologia == 'ATTONOTORIO' }">selected="selected"</c:if> >
								<spring:message code="label.attonotorio" />
						</option>
						<option value="FIGLIO"
							<c:if test="${datiAutocertificazione.tipologia == 'FIGLIO' }">selected="selected"</c:if> >
							<spring:message code="label.figlio" />
						</option>
						<option value="DECESSO"
							<c:if test="${datiAutocertificazione.tipologia == 'DECESSO' }">selected="selected"</c:if> >
							<spring:message code="label.decesso" />
						</option>
						<option value="REDDITO"
							<c:if test="${datiAutocertificazione.tipologia == 'REDDITO' }">selected="selected"</c:if> >
							<spring:message code="label.reddito" />
						</option>
						<option value="DISOCCUPAZIONE"
							<c:if test="${datiAutocertificazione.tipologia == 'DISOCCUPAZIONE' }">selected="selected"</c:if> >
							<spring:message code="label.disoccupazione" />
						</option>
						<option
							value="LEVA"
							<c:if test="${datiAutocertificazione.tipologia == 'LEVA' }">selected="selected"</c:if> >
							<spring:message code="label.leva" />
						</option>
						<option value="CONDANNE"
							<c:if test="${datiAutocertificazione.tipologia == 'CONDANNE' }">selected="selected"</c:if> >
							<spring:message code="label.condanne" />
						</option>
						<option value="ALBI"
							<c:if test="${datiAutocertificazione.tipologia == 'ALBI' }">selected="selected"</c:if> >
							<spring:message code="label.albi" />
						</option>
						<option value="CURATORE"
							<c:if test="${datiAutocertificazione.tipologia == 'CURATORE' }">selected="selected"</c:if> >
							<spring:message code="label.curatore" />
						</option>
						<option
							value="PIVA"
							<c:if test="${datiAutocertificazione.tipologia == 'PIVA' }">selected="selected"</c:if> >
							<spring:message code="label.piva" />
						</option>
						<option
							value="CODFISC"
							<c:if test="${datiAutocertificazione.tipologia == 'CODFISC' }">selected="selected"</c:if> >
							<spring:message code="label.codFisc" />
						</option>
						<option value="GENERICA"
							<c:if test="${datiAutocertificazione.tipologia == 'GENERICA' }">selected="selected"</c:if> >
							<spring:message code="label.dichGeneriche" />
						</option>
					</select>&nbsp;&nbsp;
					<input type="submit" name="carica_modello"	value="Carica modello" />
				</div>
		
				<c:if test="${!empty datiAutocertificazione.tipologia}">
					<table class="genericTable">
		 				<c:if test="${datiAutocertificazione.tipologia == 'ATTONOTORIO'  || datiAutocertificazione.tipologia == 'GENERICA'}">
	 					<tr>
	 						<td>
								<label><spring:message code="label.dichiarazione" />:</label>
							</td>
							<td colspan="3">
								<form:textarea path="generica" rows="10" cols="50"  />
								<div><form:errors path="generica" cssStyle="color:red"/></div>				 
							</td>
	 					</tr>
		 				</c:if>
						<c:if test="${datiAutocertificazione.tipologia == 'FIGLIO'  || datiAutocertificazione.tipologia == 'DECESSO' || datiAutocertificazione.tipologia == 'CURATORE'}">
			 				<tr>
								<td>
							 		<label><spring:message code="label.nome" />:</label>
							 	</td>
					  			<td>
									<form:input path="nome" /> 
									<div><form:errors path="nome" cssStyle="color:red"/></div>				 
								</td>
					 			<td>
					 				<label><spring:message code="label.cognome" />:</label>
					 			</td>
				  				<td>
									<form:input path="cognome" /> 
									<div><form:errors path="cognome" cssStyle="color:red"/></div>				 
								</td>
							</tr>
							<tr>
				 				<td>
				 					<label><spring:message code="label.comN" />:</label>
				 				</td>
			  					<td>
									<form:input path="comuneNascita" /> 
									<div><form:errors path="comuneNascita" cssStyle="color:red"/></div>				 
								</td>
								 <td>
								 	<label><spring:message code="label.provincia" />:</label>
								 </td>
								  <td>
									<form:input path="provinciaNascita"  /> 
									<div><form:errors path="provinciaNascita" cssStyle="color:red"/></div>				 
								</td>
							</tr>
							<tr>
								<td>
				 					<label><spring:message code="label.dataNascita" />:</label>
				 				</td>
								<td colspan="3">
									<form:input path="dataNascita" id="dataNascitaDP" type="text" cssClass="data_input"/>
									<div><form:errors path="dataNascita" cssStyle="color:red"/></div>
								</td>
							</tr>
		 				</c:if>	
		 				<c:if test="${datiAutocertificazione.tipologia == 'DECESSO' || datiAutocertificazione.tipologia == 'CURATORE'}">
			 				<tr>
			  					<td>
									<label><spring:message code="label.comRes" />:</label>
				 				</td>
				  				<td>
									<form:input path="comuneResidenza" /> 
									<div><form:errors path="comuneResidenza" cssStyle="color:red"/></div>				 
								</td>
				 				<td>
									<label><spring:message code="label.provincia" />:</label>
				 				</td>
				  				<td>
									<form:input path="provinciaResidenza" /> 
									<div><form:errors path="provinciaResidenza" cssStyle="color:red"/></div>				 
								</td>
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.indRes" />:</label>
				 				</td>
				  				<td>
									<form:input path="viaResidenza" /> 
									<div><form:errors path="viaResidenza" cssStyle="color:red"/></div>				 
								</td>
								<td>
									<label><spring:message code="label.civRes" />:</label>
								</td>
				  				<td>
									<form:input path="civicoResidenza" /> 
									<div><form:errors path="civicoResidenza" cssStyle="color:red"/></div>				 
								</td>
							</tr>
	 					</c:if>
						<c:if test="${datiAutocertificazione.tipologia == 'DECESSO' }">
							<tr>
		 						<td>
									<label><spring:message code="label.parentela" />:</label>
			 					</td>
			  					<td>
									<form:input path="parentela" /> 
									<div><form:errors path="parentela" cssStyle="color:red"/></div>				 
								</td>
			 					<td>
									<label><spring:message code="label.dataM" />:</label>
			 					</td>
		 						<%-- 
								  <td class="liferayUiDate">
										<liferay-ui:input-date dayValue="${inputDataMorteDay}" monthValue="${inputDataMorteMonth}" yearValue="${inputDataMorteYear}" dayParam="dataMorteDay" monthParam="dataMorteMonth" yearParam="dataMorteYear" yearRangeStart="1900" yearRangeEnd="2030" />
									</td>--%>
								<td>
									<form:input path="dataMorte" id="dataMorteDP" type="text" cssClass="data_input"/>
									<div><form:errors path="dataMorte" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.comM" />:</label>
								 </td>
								 <td>
									<form:input path="comuneMorte" /> 
									<div><form:errors path="comuneMorte" cssStyle="color:red"/></div>				 
								</td>
								<td>
									<label><spring:message code="label.provincia" />:</label>
								</td>
								<td>
									<form:input path="provinciaMorte" /> 
									<div><form:errors path="provinciaMorte" cssStyle="color:red"/></div>				 
								</td>
							</tr>
		 				</c:if>
						<c:if test="${datiAutocertificazione.tipologia == 'REDDITO' || datiAutocertificazione.tipologia == 'DISOCCUPAZIONE' }">
							<tr>
								<td>
									<label><spring:message code="label.anno" />:</label>
			 					</td>
			  					<td colspan="3">
			  						<%--  <form:input path="anno" /> --%>
								 	<form:select path="anno" items="${yearsMap}" />
								 	<div><form:errors path="anno" cssStyle="color:red"/></div>				 
								</td>
							</tr>
						</c:if> 
						<c:if test="${datiAutocertificazione.tipologia == 'REDDITO'}">
							<tr>
								<td>
									<label><spring:message code="label.redditoTestoLibero" />:</label>
								 </td>
								 <td colspan="3">
									<form:input path="reddito" /> 
									<div><form:errors path="reddito" cssStyle="color:red"/></div>				 
								</td>
							</tr>
						</c:if>
						<c:if test="${datiAutocertificazione.tipologia == 'LEVA'}">
							<tr>
								<td>
									<label><spring:message code="label.posLeva" />:</label>
								</td>
								<td colspan="3">
									<form:input path="leva" /> 
									<div><form:errors path="leva" cssStyle="color:red"/></div>				 
								</td>
							</tr>
						</c:if>
						<c:if test="${datiAutocertificazione.tipologia == 'ALBI'}">
							<tr>
								<td>
									<label><spring:message code="label.albo" />:</label>
			 					</td>
			  					<td colspan="3">
									<form:input path="albo" /> 
									<div><form:errors path="albo" cssStyle="color:red"/></div>				 
								</td>
							</tr>
						</c:if>
						<c:if test="${datiAutocertificazione.tipologia == 'PIVA'}">
							<tr>
								<td>
									<label><spring:message code="label.partitaiva" />:</label>
			 					</td>
			  					<td colspan="3">
									<form:input path="iva" /> 
									<div><form:errors path="iva" cssStyle="color:red"/></div>				 
								</td>
							</tr>
						</c:if>
						<c:if test="${datiAutocertificazione.tipologia == 'CODFISC'}">
							<tr>
								<td>
									<label><spring:message code="label.cf" />:</label>
			 					</td>
			  					<td colspan="3">
									<form:input path="codiceFiscale" /> 
									<div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>				 
								</td>
							</tr>
						</c:if>
					</table>
					<div class="container_pulsante">
						<input type="submit" name="genera" value="<spring:message code="button.autocertificazione" />" />
					</div>
				</c:if>
			</fieldset>
		</c:if>
		
		<c:if test="${!empty download}">
			<div class="container_pulsante">
				<a href="${autocertificazioneReportURL}" class="custom_pulsante evaluationServiceDownloadLink"><spring:message code="link.certificato" /></a>
				<span class="spacer"></span>
				<a href="<portlet:renderURL portletMode="view"/>" class="custom_pulsante"><spring:message code="button.home" /></a>
				<%@ include file="../common/valutaservizio.jsp"%>
			</div>
		</c:if>
	</form:form>
</div>