<%@ include file="../common/common.jsp"%>

<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>

<portlet:actionURL var="saveEntityUrl">
	<portlet:param name="action" value="new" />
</portlet:actionURL>
<div class="mainDiv">
	<!-- Elenco dei comuni -->
	<form:form id="elencoComuniForm" commandName="page" method="post">
		<display:table id="paginatedTable" requestURI="${home}" name="elencoComuni" uid="comune" cellpadding="2" cellspacing="0" pagesize="20" defaultorder="ascending" defaultsort="1" sort="list" class="elencoRisultati">
			<%-- <display:column property="id" titleKey="label.id" /> --%>
			<display:column property="codiceIstat" titleKey="label.codiceIstat" />
			<display:column property="cap" titleKey="label.cap" />
			<display:column property="nome" titleKey="label.nome" />
			<display:column property="descrizione" titleKey="label.descrizione" />
			<display:column titleKey="label.gestore" >
				<c:if test="${comune.gestoreComune != null}">
					<c:out value="${comune.gestoreComune.codiceFiscale}"/>
				</c:if>
			</display:column>
			<display:column sortable="false" titleKey="label.stato" class="center" headerClass="center">
				<c:choose>
					<c:when test="${comune.attivo}">
						<img src="<%=request.getContextPath() %>/images/ok.png" alt="<spring:message code="label.attivo"/>" title="<spring:message code="label.attivo"/>"/>							
					</c:when>
					<c:otherwise>
						<img src="<%=request.getContextPath() %>/images/ko.png" alt="<spring:message code="label.non.attivo"/>" title="<spring:message code="label.non.attivo"/>"/>								
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column sortable="false" class="center" headerClass="center">
				<portlet:actionURL var="updateCatalogoServiziComuneUrl">
					<portlet:param name="action" value="updateCatalogoServizi" />
					<portlet:param name="id" value="${comune.id}" />
				</portlet:actionURL>
				<portlet:actionURL var="editComuneUrl">
					<portlet:param name="action" value="edit" />
					<portlet:param name="id" value="${comune.id}" />
				</portlet:actionURL>
				<portlet:actionURL var="delComuneUrl">
					<portlet:param name="action" value="delete" />
					<portlet:param name="id" value="${comune.id}" />
				</portlet:actionURL>
				<a href="${updateCatalogoServiziComuneUrl}" title="<spring:message code="button.aggiornaCatalogoServizi.title" />" onclick="javascript:return(confirm('<spring:message code="message.confirm.aggiornaCatalogoServizi" />'))">
					<spring:message code="button.aggiornaCatalogoServizi" />
				</a>
				&nbsp;-&nbsp;
				<a href="${editComuneUrl}" title="<spring:message code="button.edit" />">
					<spring:message code="button.edit" />
				</a>
				&nbsp;-&nbsp;
		   		<a href="${delComuneUrl}" title="<spring:message code="button.delete" />" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
		   			<spring:message code="button.delete" />
		   		</a>
			</display:column>
		</display:table>
	</form:form>
	<div class="container_pulsante">
		<form:form id="commandForm" commandName="page" method="post" action="${saveEntityUrl}">
			<input name="Nuovo" type="submit" value="<spring:message code="button.nuovo" />" />
		</form:form>
	</div>
</div>
