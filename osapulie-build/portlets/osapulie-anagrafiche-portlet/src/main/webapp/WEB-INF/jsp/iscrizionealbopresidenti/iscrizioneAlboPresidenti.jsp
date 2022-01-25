<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="iscrizioneUrlCambio">
	<portlet:param name="ope" value="cambioSoggetto" />
</portlet:actionURL>

<portlet:actionURL var="iscrizioneUrlGenera">
	<portlet:param name="ope" value="generaIscrizione" />
</portlet:actionURL>

<portlet:resourceURL var="iscrizioneAlboPresidentiReportURL"
	id="iscrizioneAlboPresidentiReport" escapeXml="false">
	<portlet:param name="codFisc"
		value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="produrreIscrizione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<script type="text/javascript">
	$(document).ready(function() {
	    //set initial state.
	    $('#textbox1').val($(this).is(':checked'));
	
	    $('#tipoDichiarazioneRadio1').click(function() {
	        $('#dichiarazioneCondizione').val('');
	    });
	    $('#tipoDichiarazioneRadio2').click(function() {
	        $('#dichiarazioneProfessione').val('');        
	        $('#dichiarazionePresso').val('');        
	    });
	});
</script>

<div class="mainDiv iscrizioneAlboPresidenti">
	<c:if test="${empty datiAnagrafici.errore}">
		<form:form id="${idFormCambio}" action="${iscrizioneUrlCambio}"
			method="post" commandName="datiAnagrafici">

			<div class="marginBottom10">
				<label><spring:message code="label.soggetto" /></label>
				<select name="codFisc">
					<c:forEach var="item" begin="0" items="${datiAnagrafici.componentiNucleoFamiliare}">
						<option value="${item.codiceFiscale}" <c:if test="${componenteFamiglia.codiceFiscale == item.codiceFiscale}"> selected="selected" </c:if>="">${item.cognome}
							${item.nome}</option>
					</c:forEach>
				</select>&nbsp;&nbsp;
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
						<td>${componenteFamiglia.codiceFiscale}</td>
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
							${datiAnagrafici.toponimoIndirizzo} ${datiAnagrafici.descrizioneVia} ${datiAnagrafici.numeroCivico} 
							<c:if test="${!empty datiAnagrafici.esponente}">
								${datiAnagrafici.esponente}  
							</c:if> <c:if test="${!empty datiAnagrafici.piano}">
								p. ${datiAnagrafici.piano}  
							</c:if> <c:if test="${!empty datiAnagrafici.scala}">
								s. ${datiAnagrafici.scala}
							</c:if>
						</td>
					</tr>
				</table>
			</fieldset>
		</form:form>
		
		<form:form id="${idForm}" action="${iscrizioneUrlGenera}" method="post">
			<c:if test="${empty download}">
				<fieldset>
					<legend>
						<spring:message code="label.legend" />
					</legend>
					<input type="hidden" name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
					
					<table class="genericTable">
						<tr>
							<td>
								<label><spring:message code="label.titoloStudio" />*:</label> 
							</td>
							<td colspan="3">
								<input name="titoloStudio" id="titoloStudio" type="text" maxlength="255" size="50" value="${empty iscrizione ? '' : iscrizione.titoloStudio}" />
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.annoStudio" />*:</label>
							</td>
							<td>
								<%-- 
								<input name="annoStudio" id="annoStudio"
								type="text" maxlength="4" size="3" value="${empty iscrizione ? '' : iscrizione.annoStudio}"/> &nbsp;&nbsp;&nbsp; <label
								style="width: 250px">--%>
								<select id="annoStudio" name="annoStudio"> 
									<c:forEach var="i" begin="1950" end="${annoCorrente}" step="1">
										<option value="${i}" <c:if test="${i == iscrizione.annoStudio}"> selected="selected" </c:if>>${i}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<label><spring:message code="label.sedeStudio" />*:</label>
							</td>
							<td>
								 <input name="sedeStudio" id="sedeStudio" type="text" maxlength="255" size="45" value="${empty iscrizione ? '' : iscrizione.sedeStudio}"/>
							</td>	
						</tr>
					</table>
					
				</fieldset>
				<!-- Altro -->
				<fieldset>
					<legend>
						<spring:message code="label.legend.altro" />
					</legend>
					<p><spring:message code="label.dichiarazione.1" />
					<ul>
						<li><spring:message code="label.dichiarazione.2" /></li>
						<li>
							<input id="tipoDichiarazioneRadio1" type="radio" name="tipoDichiarazione" <c:if test="${iscrizione.tipoDichiarazione == '1'}">checked="checked"</c:if> value="1">
							<spring:message code="label.dichiarazione.3" />
							<input name="dichiarazioneProfessione" id="dichiarazioneProfessione" type="text" maxlength="255" value="${empty iscrizione ? '' : iscrizione.dichiarazioneProfessione}"/>&nbsp;
							<spring:message code="label.dichiarazione.3.1" />&nbsp;
							<input name="dichiarazionePresso" id="dichiarazionePresso" type="text" maxlength="255" value="${empty iscrizione ? '' : iscrizione.dichiarazionePresso}"/>
						</li>
						<li>
							<input id="tipoDichiarazioneRadio2" type="radio" name="tipoDichiarazione" <c:if test="${iscrizione.tipoDichiarazione == '2'}">checked="checked"</c:if> value="2">
							<spring:message code="label.dichiarazione.4" />&nbsp;
							<input name="dichiarazioneCondizione" id="dichiarazioneCondizione" type="text" maxlength="255" value="${empty iscrizione ? '' : iscrizione.dichiarazioneCondizione}"/>&nbsp;
						</li>
					</ul>
				</fieldset>
				
				<%@ include file="../common/footer.jsp"%>
					
				<div class="container_pulsante">
					<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
				</div>
			</c:if>
			
			<c:if test="${!empty download}">
				<p>
					<spring:message code="label.servizio.option1.part1" /><spring:message code="link.dichiarazione" /><spring:message code="label.servizio.option1.part2" /><br>
					<spring:message code="label.servizio.option2.part1" />
					<ul>
						<li><spring:message code="label.servizio.option2.part2" /></li>
						<li><spring:message code="label.servizio.option2.part3" /></li>
					</ul>
				</p>
				<div class="container_pulsante">
					<a class="custom_pulsante" href="${iscrizioneAlboPresidentiReportURL}"><spring:message code="link.dichiarazione" /></a>
					<span class="spacer"></span>
					<a class="custom_pulsante" href="<portlet:renderURL portletMode="view"/>"><spring:message code="button.home" /></a>
				</div>
			</c:if>
		</form:form>
	</c:if>
	
	<c:if test="${! empty datiAnagrafici.errore }">
		<div>
			<strong><spring:message code="errore.pdds.codice${datiAnagrafici.errore.codice }" /></strong>
		</div>
	</c:if>

</div>