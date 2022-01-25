<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv">
	<div class="comuneSelezionatoTitle">
		<c:if test="${comuneSelezionato != null}">
			<spring:message code="label.serviziComuneDi" arguments="${comuneSelezionato.nome}"/>
			<c:if test="${delega != null}">
				&nbsp;-&nbsp;<spring:message code="label.delegatoDa" arguments="${delega.delegante.cognome}&nbsp;${delega.delegante.nome},${delega.delegante.codiceFiscale}"/>
			</c:if>
		</c:if>
	</div>
	<c:choose>
		<c:when test="${!empty mappaServizi}">
			<c:forEach var="entry" items="${mappaServizi}" varStatus="counter">
				<div class="areaTematicaServiziTitle aperto">
					<spring:message code="label.areaTematicaServizio" arguments="${entry.key.nome}"/>
				</div>
				<div>
					<display:table id="paginatedTable_${counter.index}" htmlId="servizio_${counter.index}" requestURI="${home}" name="${entry.value}" uid="servizio" cellpadding="2" cellspacing="0" pagesize="30" defaultorder="ascending" defaultsort="1" sort="list" class="genericTable elencoRisultati">
						<display:column sortable="true" property="nomeServizio" titleKey="label.nomeServizio" />
						<display:column sortable="true" property="descrizione" titleKey="label.descrizioneServizio" />
						<display:column sortable="false">
					   		<a href="${servizio.uri}" title="<spring:message code="label.vaiAlServizio"/>">
					   			<i class="fa fa-angle-double-right fa-2x"></i>
							</a>
						</display:column>
					</display:table>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>
				<strong><spring:message code="label.noServizi"/></strong>
			</div>								
		</c:otherwise>
	</c:choose>
</div>