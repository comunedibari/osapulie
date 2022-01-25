<%@ include file="../common/common.jsp"%>

<security:authentication var="principal" property="principal" />

<security:authorize access="hasRole('ROLE_User')">
	<portlet:actionURL var="mostraFascicoloCittadinoUrl">
		<portlet:param name="action" value="mostraFascicoloCittadino" />
	</portlet:actionURL>
	<div class="mainDiv fascicoloUtente">	
		<spring:message code="label.welcome" />&nbsp;<strong><c:out value="${profiloUtente.nome} ${profiloUtente.cognome}" /></strong>, <spring:message code="label.click" />
		<div class="container_pulsante">
			<a class="custom_pulsante" href="${mostraFascicoloCittadinoUrl}" title="<spring:message code="label.apri.fascicolo.cittadino" />"><spring:message code="label.apri.fascicolo.cittadino" /></a>
			<c:if test="${!empty aziende}">
				<portlet:actionURL var="mostraFascicoloAziendaUrl">
					<portlet:param name="action" value="mostraFascicoloAzienda" />
				</portlet:actionURL>
				<span class="spacer"></span>
				<a class="custom_pulsante" href="${mostraFascicoloAziendaUrl}" title="<spring:message code="label.apri.fascicolo.aziende" />"><spring:message code="label.apri.fascicolo.aziende" /></a>
			</c:if>
			<c:if test="${responsabile}">
				<!-- Statistiche -->
				<portlet:actionURL var="mostraReportAziendeUrl">
					<portlet:param name="action" value="mostraReportAziende" />
				</portlet:actionURL>
				<span class="spacer"></span>
				<a class="custom_pulsante" href="${mostraReportAziendeUrl}" title="<spring:message code="label.apri.fascicolo.report.azienda" />"><spring:message code="label.apri.fascicolo.report.azienda" /></a>
			</c:if>
		</div>
	</div>
</security:authorize>

<c:if test="${empty principal}">
	<div class="portlet-msg-info">
		<spring:message code="label.autenticazione" />
	</div>
</c:if>

