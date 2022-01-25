<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="mostraFascicoloAziendaUrl">
	<portlet:param name="action" value="mostraFascicoloAzienda" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
</portlet:renderURL>
<div class="mainDiv mostraFascicoloAzienda">
	<div class="selectAziendaDiv">
		<spring:message code="label.seleziona.azienda"/>
		<form:form action="${mostraFascicoloAziendaUrl}" method="POST" commandName="fascicoloAziendaForm">
			<form:select path="idAzienda">
				<form:option value="0"><spring:message code="label.seleziona"/></form:option>
			    <form:options items="${aziende}" itemLabel="ragioneSociale" itemValue="id"/>
			</form:select>
			<button type="submit" class="custom_pulsante"><spring:message code="button.aggiorna"/></button>
		</form:form>
	</div>
	<c:choose>
		<c:when test="${!empty fascicolo}">
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
				<c:choose>
					<c:when test="${!empty fascicolo.richieste}">	
						<portlet:renderURL var="renderFascicoloAziendaUrl">
							<portlet:param name="action" value="renderFascicoloAzienda" />
						</portlet:renderURL>
						<h3><spring:message code="label.storicoRichieste"/></h3>
						<display:table id="richiesteServiziTable" name="fascicolo.richieste" requestURI="${renderFascicoloAziendaUrl}" pagesize="10" uid="richiesta" class="genericTable elencoRisultati" defaultsort="2" defaultorder="descending">
							<display:column property="id" titleKey="displaycolumn.label.id" sortable="true" />
							<display:column property="dataRichiesta" titleKey="displaycolumn.label.dataRichiesta" sortable="true" format="{0,date,dd/MM/yyyy - HH:mm:ss}"/>
							<display:column property="nomeServizio" titleKey="displaycolumn.label.servizio" sortable="true" />
							<display:column property="comuneErogatore.nome" sortable="true" titleKey="displaycolumn.label.comune" />
							<display:column sortable="true" titleKey="displaycolumn.label.operatore" >
								<div>${richiesta.delegato.profiloUtenteCittadino.cognome}&nbsp;${richiesta.delegato.profiloUtenteCittadino.nome}&nbsp;-&nbsp;${richiesta.delegato.profiloUtenteCittadino.codiceFiscale}</div>
							</display:column>
							<display:column titleKey="displaycolumn.label.numeroProtocollo" sortable="true">
								<c:if test="${!empty richiesta.numeroProtocollo }">
									<portlet:actionURL var="dettaglioPraticaUrl">
										<portlet:param name="action" value="dettaglio" />
										<portlet:param name="id" value="${richiesta.numeroProtocollo}" />
										<portlet:param name="codiceIstat" value="${richiesta.comuneErogatore.codiceIstat}" />
										<portlet:param name="mostraFascicoloAction" value="mostraFascicoloAzienda" />
									</portlet:actionURL>
									<a href="${dettaglioPraticaUrl}" title="<spring:message code="label.dettagliopratica" />">
										${richiesta.numeroProtocollo}
									</a>
								</c:if>
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
		</c:when>
		<c:otherwise>
			<strong><spring:message code="label.nessun.fascicolo"/>.</strong>
		</c:otherwise>
	</c:choose>
</div>