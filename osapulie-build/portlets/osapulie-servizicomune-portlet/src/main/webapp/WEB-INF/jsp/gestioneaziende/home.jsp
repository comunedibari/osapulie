<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv gestioneAziende">
	<fieldset>
		<legend><spring:message code="label.ricercaAziende"/></legend>
		<form:form id="searchForm" action="${home}" modelAttribute="aziendaSearchForm" method="post">
			<table>	
				<tr>
					<td>
						<spring:message code="label.piva"/>:&nbsp;<form:input path="partitaIva"/>
					</td>
					<td>
						<spring:message code="label.ragioneSociale"/>:&nbsp;<form:input path="ragioneSociale"/>
					</td>
					<td>
						<spring:message code="label.codiceFiscaleResponsabile"/>:&nbsp;<form:input path="codiceFiscaleResponsabile"/>
					</td>
					<td>
						<spring:message code="label.stato"/>:&nbsp;
						<select name="stato" class="searchSelect">
							<option value=""><spring:message code="label.seleziona"/></option>
							<option value="true" <c:if test="${aziendaSearchForm != null && aziendaSearchForm.stato == 'true'}">selected="selected"</c:if>><spring:message code="label.select.statoAziendaAttiva"/></option>
							<option value="false" <c:if test="${aziendaSearchForm != null && aziendaSearchForm.stato == 'false'}">selected="selected"</c:if>><spring:message code="label.select.statoAziendaNonAttiva"/></option>
						</select>
					</td>
				</tr>
			</table>
			<div class="container_pulsante">
				<input type="submit" value="<spring:message code="button.cerca" />" />
			</div>
		</form:form>	
	</fieldset>
	<fieldset>
		<legend><spring:message code="label.listaAziende"/></legend>
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
			<display:column sortable="true" property="partitaIva" titleKey="label.partitaIvaCf" />
			<display:column sortable="true" property="ragioneSociale" titleKey="label.ragioneSociale" />
			<display:column sortable="true" property="sede.via" titleKey="label.sede" />
			<display:column sortable="true" property="sede.nrCivico" titleKey="label.sede.nCivicosede" />
			<display:column sortable="true" property="sede.comune.denominazione" titleKey="label.sede.comune" />
			<display:column sortable="true" property="mail" titleKey="label.sede.mail" />
			<display:column sortable="true" property="mailPec" titleKey="label.sede.mailPec" />
			<display:column sortable="true" property="responsabile.codiceFiscale" titleKey="label.responsabile" />
			<display:column sortable="true" property="tipo" titleKey="label.tipo" class="center"  headerClass="center"/>
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
			<display:column sortable="false" class="center" headerClass="center operazioni">
				<portlet:renderURL var="editAziendaUrl">
					<portlet:param name="action" value="editAzienda" />
					<portlet:param name="idAzienda" value="${azienda.id}" />
				</portlet:renderURL>
		   		<a href="${editAziendaUrl}" title="<spring:message code="button.edit" />">
		   			<spring:message code="button.edit" />
		   		</a>
		   		&nbsp;
				<portlet:actionURL var="delAziendaUrl">
					<portlet:param name="action" value="delAzienda" />
					<portlet:param name="idAzienda" value="${azienda.id}" />
				</portlet:actionURL>
		   		<a href="${delAziendaUrl}" title="<spring:message code="button.delete" />" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
		   			<spring:message code="button.delete" />
		   		</a>
			</display:column>
		</display:table>
	</fieldset>
</div>