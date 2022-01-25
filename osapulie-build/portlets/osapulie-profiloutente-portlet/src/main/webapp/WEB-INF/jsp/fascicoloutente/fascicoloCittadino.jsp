<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="homeUrl">
</portlet:renderURL>
<div class="mainDiv mostraFascicolo">
	<fieldset>
		<legend>
			<spring:message code="label.fascicoloDi"/>&nbsp; ${profiloUtente.cognome}  ${profiloUtente.nome} - ${fn:toUpperCase(profiloUtente.codiceFiscale)}
		</legend>
		<c:choose>
			<c:when test="${!empty fascicolo}">	
				<portlet:renderURL var="renderFascicoloCittadinoUrl">
					<portlet:param name="action" value="renderFascicoloCittadino" />
				</portlet:renderURL>
				<h3><spring:message code="label.storicoRichieste"/></h3>
				<display:table id="richiesteServiziTable" name="fascicolo.richieste" requestURI="${renderFascicoloCittadinoUrl}" pagesize="20" uid="richiesta" class="genericTable elencoRisultati" defaultsort="2" defaultorder="descending">
					<display:column property="id" titleKey="displaycolumn.label.id" sortable="true" />
					<display:column property="dataRichiesta" titleKey="displaycolumn.label.dataRichiesta" sortable="true" format="{0,date,dd/MM/yyyy - HH:mm:ss}"/>
					<display:column property="nomeServizio" titleKey="displaycolumn.label.servizio" sortable="true" />
					<display:column property="comuneErogatore.nome" sortable="true" titleKey="displaycolumn.label.comune" />
					<display:column sortable="true" titleKey="displaycolumn.label.delegato" >
						<c:if test="${richiesta.delegato != null}">
							<div>
								<c:out value="${richiesta.delegato.azienda.ragioneSociale}"/>&nbsp;-&nbsp;
								<spring:message code="displaycolumn.label.operatore.nome" arguments="${richiesta.delegato.profiloUtenteCittadino.cognome}&nbsp;${richiesta.delegato.profiloUtenteCittadino.nome}"/>
							</div>
						</c:if>
					</display:column>
					<display:column titleKey="displaycolumn.label.numeroProtocollo" sortable="true">
						<c:choose>
							<c:when test="${richiesta.comuneErogatore.downloadAllegati && !empty richiesta.numeroProtocollo}">
								<portlet:actionURL var="dettaglioPraticaUrl">
									<portlet:param name="action" value="dettaglio" />
									<portlet:param name="id" value="${richiesta.numeroProtocollo}" />
									<portlet:param name="codiceIstat" value="${richiesta.comuneErogatore.codiceIstat}" />
									<portlet:param name="mostraFascicoloAction" value="mostraFascicoloAzienda" />
								</portlet:actionURL>
								<a href="${dettaglioPraticaUrl}" title="<spring:message code="label.dettagliopratica" />">
									${richiesta.numeroProtocollo}
								</a>
							</c:when>
							<c:otherwise>
								${richiesta.numeroProtocollo}
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column sortable="false" titleKey="displaycolumn.label.infoAggiuntive" headerClass="infoAggiuntiveMap">
						<div>
							<c:forEach var="infoAggiuntive" items="${richiesta.infoAggiuntiveMap}">
							    ${infoAggiuntive.key}:&nbsp;${infoAggiuntive.value}
							</c:forEach>
						</div>
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