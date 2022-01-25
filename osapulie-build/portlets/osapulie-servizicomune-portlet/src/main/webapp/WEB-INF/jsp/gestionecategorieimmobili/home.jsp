<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="updateAnnoUrl">
	<portlet:param name="action" value="updateAnno" />
</portlet:actionURL>
<portlet:actionURL var="saveEntityUrl">
	<portlet:param name="action" value="save" />
</portlet:actionURL>

<div class="mainDiv">
	<c:choose>
		<c:when test="${access}">
			<form:form id="saveForm" commandName="gestioneCategorieImmobiliForm" method="post" action="${saveEntityUrl}">
				<div>
					<spring:message code="label.gestioneComuneDi" arguments="${comuneIsa.nome}"/>
				</div>
				<div class="selectAnnoDiv">
					<label for="selectAnno"><spring:message code="label.selezionaAnno"/>:</label>
					<form:select id="selectAnno" path="anno" onchange="updateAnno('${updateAnnoUrl}');" >
						<form:option value=""><spring:message code="label.seleziona" /></form:option>
						<form:options items="${anni}"/>
					</form:select>
					<form:errors path="anno" cssStyle="color:red"/>
				</div>
				<display:table id="paginatedTable" requestURI="${home}" name="gestioneCategorieImmobiliForm.categoriaImmobileForms" uid="categoriaImmobileForm" excludedParams="*" cellpadding="2" cellspacing="0" pagesize="20" defaultorder="ascending" defaultsort="1" sort="list" class="genericTable elencoRisultati" >
					<display:column property="descrizione" titleKey="label.categoriaImmobile" />
					<display:column sortable="false" style="width:70px">
						<portlet:renderURL var="editCategoriaImmobileUrl">
							<portlet:param name="action" value="edit" />
							<portlet:param name="idCategoriaImmobile" value="${categoriaImmobileForm.idCategoriaImmobile}" />
							<portlet:param name="anno" value="${gestioneCategorieImmobiliForm.anno}" />
						</portlet:renderURL>
						<portlet:renderURL var="delCategoriaImmobileUrl">
							<portlet:param name="action" value="delete" />
							<portlet:param name="idCategoriaImmobile" value="${categoriaImmobileForm.idCategoriaImmobile}" />
						</portlet:renderURL>
						<span>
							<a href="${editCategoriaImmobileUrl}" title="<spring:message code="button.edit" />"> <div class="iconModifica">&nbsp;</div>	</a>
					   		<a href="${delCategoriaImmobileUrl}" title="<spring:message code="button.delete" />" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
					   			<div class="iconElimina">&nbsp;</div>
					   		</a>
					   	</span>
					</display:column>
				</display:table>
			</form:form>
			<div class="container_pulsante">
				<portlet:renderURL var="addCategoriaImmobileUrl">
					<portlet:param name="action" value="addCategoriaImmobile" />
				</portlet:renderURL>
				<a class="custom_pulsante" href="${addCategoriaImmobileUrl}" title="<spring:message code="label.aggiungiCategoriaImmobile" />">
					<spring:message code="label.aggiungiCategoriaImmobile" />
				</a>
			</div>
		</c:when>
		<c:otherwise>
			<spring:message code="label.accessDenied"/>.
		</c:otherwise>
	</c:choose>
</div>