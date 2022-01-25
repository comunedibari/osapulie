<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="mostraReportAziendeUrl">
	<portlet:param name="action" value="mostraReportAziende" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
</portlet:renderURL>

<script type="text/javascript"  >
$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
</script>
<div class="mainDiv mostraReportAziende">
	<div class="selectAziendaDiv">
		<spring:message code="label.seleziona.azienda"/>
		<form:form action="${mostraStatisticheAziendaUrl}" method="POST" commandName="fascicoloAziendaReportForm">
			<form:select path="idAzienda">
				<form:option value="0"><spring:message code="label.seleziona"/></form:option>
			    <form:options items="${aziende}" itemLabel="ragioneSociale" itemValue="id"/>
			</form:select>
			<button type="submit" class="custom_pulsante"><spring:message code="button.aggiorna"/></button>
		</form:form>
	</div>
	<fieldset>
		<legend>
			<c:choose>
				<c:when test="${!empty azienda}">	
					<spring:message code="label.fascicoloDi.azienda" arguments="${azienda.tipo}"/>&nbsp;${azienda.ragioneSociale}&nbsp;-&nbsp;${fn:toUpperCase(azienda.partitaIva)}
				</c:when>
				<c:otherwise>
					<spring:message code="label.fascicolo.azienda"/>
				</c:otherwise>
			</c:choose>
		</legend>
		<form:form id="ricercaForm" action="${mostraReportAziendeUrl}" commandName="fascicoloAziendaReportForm" method="post">
			<table>
				<tr>
					<td>
						<spring:message code="label.datarichiesta.da"/>:&nbsp;<form:input path="dataRichiestaDa" id="dataRichiestaDa" size="10" cssClass="data_input"/>
					</td>
					<td>
						<spring:message code="label.datarichiesta.a"/>:&nbsp;<form:input path="dataRichiestaA" id="dataRichiestaA" size="10" cssClass="data_input"/>
					</td>
					<td>
						<spring:message code="label.servizio"/>:&nbsp;
						<form:select path="idServizio">
							<form:option value=""><spring:message code="label.seleziona" /></form:option>
							<form:options items="${servizi}" itemLabel="nomeServizio" itemValue="id"/>
						</form:select>
					</td>
				</tr>
			</table>
			<div class="container_pulsante">
				<input type="submit" value="<spring:message code="button.cerca" />" />
			</div>
		</form:form>
		<c:choose>
			<c:when test="${!empty richiesteServizi}">	
				<h3><spring:message code="label.storicoRichieste"/></h3>
				<div class="downloadReportPdfDiv">
					<portlet:resourceURL var="downloadReportPDFUrl" id="downloadReportPDF">
						<portlet:param name="idAzienda" value="${azienda.id}" />
					</portlet:resourceURL>
	 				<a href="${downloadReportPDFUrl}" title="<spring:message code="label.scarica.report.pdf"/>">
	 					<spring:message code="label.scarica.report.pdf"/>
	 				</a>
				</div>
				<portlet:renderURL var="renderReportAziendeUrl">
					<portlet:param name="action" value="renderReportAziende" />
				</portlet:renderURL>
				<display:table id="richiesteServiziTable" name="richiesteServizi" requestURI="${renderReportAziendeUrl}" pagesize="10" uid="richiesta" class="genericTable elencoRisultati" defaultsort="2" defaultorder="descending">
					<display:column property="id" titleKey="displaycolumn.label.id" sortable="true" />
					<display:column property="dataRichiesta" titleKey="displaycolumn.label.dataRichiesta" sortable="true" format="{0,date,dd/MM/yyyy - HH:mm:ss}"/>
					<display:column titleKey="displaycolumn.label.cittadino" sortable="true">
						<div title="<c:out value="${richiesta.fascicolo.cittadino.cognome} ${richiesta.fascicolo.cittadino.nome}"/>">
							<c:out value="${richiesta.fascicolo.cittadino.codiceFiscale}"/>
						</div>
					</display:column>
					<display:column property="nomeServizio" titleKey="displaycolumn.label.servizio" sortable="true" />
					<display:column property="comuneErogatore.nome" sortable="true" titleKey="displaycolumn.label.comune" />
					<display:column sortable="true" titleKey="displaycolumn.label.operatore" >
						<div title="${richiesta.delegato.profiloUtenteCittadino.codiceFiscale}">
							<c:out value="${richiesta.delegato.profiloUtenteCittadino.cognome}"/>&nbsp;<c:out value="${richiesta.delegato.profiloUtenteCittadino.nome}"/>
						</div>
					</display:column>
					<display:column property="numeroProtocollo" sortable="false" titleKey="displaycolumn.label.numeroProtocollo" />
					<display:column sortable="false" titleKey="displaycolumn.label.infoAggiuntive" headerClass="infoAggiuntiveMap">
						<div>
							<c:forEach var="infoAggiuntive" items="${richiesta.infoAggiuntiveMap}">
							    ${infoAggiuntive.key}:&nbsp;${infoAggiuntive.value}
							</c:forEach>
						</div>
					</display:column>
					<display:column titleKey="displaycolumn.label.downloadRicevuta" headerClass="center" class="center">
						<portlet:resourceURL var="downloadRicevutaPDFUrl" id="downloadRicevutaPDF">
							<portlet:param name="idRichiesta" value="${richiesta.id}" />
						</portlet:resourceURL>
						<a href="${downloadRicevutaPDFUrl}" title="<spring:message code="label.download.ricevuta" />">
							<img src="${pageContext.request.contextPath}/images/ricevuta.png" alt='<spring:message code="label.download.ricevuta" />' style="width: 25px;"/>
						</a>
					</display:column>
				</display:table>	
			</c:when>
			<c:otherwise>	
				<div>
					<strong><spring:message code="label.nessunaRichiesta"/></strong>			
				</div>
			</c:otherwise>
		</c:choose>
		<div class="container_pulsante">
			<a class="custom_pulsante" href="${homeUrl}" title="<spring:message code="label.indietro" />"><spring:message code="label.indietro" /></a>
		</div>
	</fieldset>
</div>