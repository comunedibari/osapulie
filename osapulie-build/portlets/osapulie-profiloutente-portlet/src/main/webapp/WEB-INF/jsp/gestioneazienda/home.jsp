<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>

<div class="mainDiv profiloUtente">
	<fieldset>
		<legend><spring:message code="label.datiAzienda"/></legend>
		<display:table id="paginatedTable" 
			requestURI="${home}"
			name="aziende"
			uid="azienda" 
			cellpadding="2" 
			cellspacing="0" 
			pagesize="20" 
			defaultorder="ascending" 
			defaultsort="1" 
			sort="list" 
			class="genericTable elencoRisultati">
			<display:column sortable="true" property="partitaIva" titleKey="label.pivacf" />
			<display:column sortable="true" property="ragioneSociale" titleKey="label.ragioneSociale" />
			<display:column sortable="true" property="sede.via" titleKey="label.sede" />
			<display:column sortable="true" property="sede.nrCivico" titleKey="label.sede.nCivicosede" />
			<display:column sortable="true" property="sede.comune.denominazione" titleKey="label.sede.comune" />
			<display:column sortable="true" property="mail" titleKey="label.sede.mail" />
			<display:column sortable="true" property="mailPec" titleKey="label.sede.mailPec" />
			<display:column sortable="false" titleKey="label.stato" class="center" headerClass="center">
				<c:choose>
					<c:when test="${azienda.attiva}">
						<img src="<%=request.getContextPath() %>/images/ok.png" alt="<spring:message code="label.images.aziendaAttiva"/>" title="<spring:message code="label.images.aziendaAttiva"/>"/>							
					</c:when>
					<c:otherwise>
						<img src="<%=request.getContextPath() %>/images/ko.png" alt="<spring:message code="label.images.aziendaNonAttiva"/>" title="<spring:message code="label.images.aziendaNonAttiva"/>"/>								
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column sortable="true" property="tipo" titleKey="label.sede.tipo" />
			<display:column sortable="false" class="center" headerClass="center">
				<portlet:renderURL var="editAziendaUrl">
					<portlet:param name="action" value="editAzienda" />
					<portlet:param name="idAzienda" value="${azienda.id}" />
				</portlet:renderURL>
		   		<a href="${editAziendaUrl}" title="<spring:message code="button.edit" />">
		   			<spring:message code="button.edit" />
		   		</a>
				<c:if test="${!azienda.inUso}">
			   		&nbsp;-&nbsp;
					<portlet:actionURL var="delAziendaUrl">
						<portlet:param name="action" value="delAzienda" />
						<portlet:param name="idAzienda" value="${azienda.id}" />
					</portlet:actionURL>
			   		<a href="${delAziendaUrl}" title="<spring:message code="button.delete" />" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
			   			<spring:message code="button.delete" />
			   		</a>
		   		</c:if>
			</display:column>
		</display:table>
		<portlet:renderURL var="newAziendaUrl">
			<portlet:param name="action" value="editAzienda" />
		</portlet:renderURL>
		<div class="container_pulsante">
			<a class="custom_pulsante" href="${newAziendaUrl}">
				<spring:message code="button.nuovo" />
			</a>
		</div>
	</fieldset>
</div>