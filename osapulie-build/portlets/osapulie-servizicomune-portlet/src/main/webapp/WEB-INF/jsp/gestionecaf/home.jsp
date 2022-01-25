<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="ricercaUtenteURL">
	<portlet:param name="action" value="ricercaUtente" />
</portlet:actionURL>
<div class="mainDiv">
	<c:choose>
		<c:when test="${not empty cafCorrente}">
			<div class="descrizioneCAF">
				<spring:message code="label.gestioneCAF.per" arguments="${cafCorrente.ragioneSociale}"/>
			</div>
			<fieldset>
				<legend><spring:message code="label.ricercaUtente"/></legend>
				<form:form id="ricercaUtenteForm" action="${ricercaUtenteURL}" method="post">
					<div class="ricercaUtenteDiv">
						<spring:message code="label.cf"/>:&nbsp;<input type="text" name="codiceFiscale" value="${cfProfiloUtenteCittadino}">
					</div>
					<c:if test="${!empty gestioneUtentiPageUrl}">
						<div class="aggiungiUtenteDiv">
							<a href="${gestioneUtentiPageUrl}" title="<spring:message code="button.aggiungiUtente" />">
					   			<spring:message code="button.aggiungiUtente" />
					   		</a>
						</div>
					</c:if>
					<div class="clear"></div>
					<div class="container_pulsante">
						<input type="submit" value="<spring:message code="button.cerca" />" />
					</div>
				</form:form>
			</fieldset>
			<fieldset>
				<legend><spring:message code="label.ricercaDeleghe"/></legend>
				<form:form id="ricercaDelgaForm" action="${home}" commandName="delega" method="post">
					<table>
						<tr>
							<td>
								<spring:message code="label.cognome"/>:&nbsp;<input type="text" name="delegante.cognome" value="${cognomeDelegante}">
							</td>
							<td>
								<spring:message code="label.nome"/>:&nbsp;<input type="text" name="delegante.nome" value="${nomeDelegante}">
							</td>
							<td>
								<spring:message code="label.cf"/>:&nbsp;<input type="text" name="delegante.codifceFiscale" value="${cfDelegante}">
							</td>
						</tr>
					</table>
					<div class="container_pulsante">
						<input type="submit" value="<spring:message code="button.cerca" />" />
					</div>
				</form:form>
				<c:choose>
					<c:when test="${(delegaList != null)}">
						<display:table id="paginatedTable" requestURI="${home}" name="delegaList" uid="delega" cellpadding="2" cellspacing="0" pagesize="20" defaultorder="descending" defaultsort="5" sort="list" class="elencoRisultati">
							<display:column sortable="true" property="delegante.cognome" titleKey="label.cognome" />
							<display:column sortable="true" property="delegante.nome" titleKey="label.nome" />
							<display:column sortable="true" titleKey="label.cf">
								<c:out value="${fn:toUpperCase(delega.delegante.codiceFiscale)}"/>
							</display:column>
							<display:column sortable="true" property="comuneIsa.nome" titleKey="label.comune" />
							
								<c:choose>
									<c:when test="${delega.attiva}">

									<display:column property="dataAttivazione" titleKey="label.dataAttivazione" sortable="true" format="{0,date,dd/MM/yyyy - HH:mm}" /> 
										
									</c:when>
									<c:otherwise>
										-
									</c:otherwise>
								</c:choose>
							
							<display:column sortable="false" class="center" headerClass="center">
								<portlet:renderURL var="editDelegaUrl">
									<portlet:param name="action" value="editDelega" />
									<portlet:param name="idDelega" value="${delega.id}" />
								</portlet:renderURL>
						   		<a href="${editDelegaUrl}" title="<spring:message code="button.edit" />">
						   			<spring:message code="button.edit" />
						   		</a>
						   		&nbsp;-&nbsp;
								<portlet:actionURL var="delDelegaUrl">
									<portlet:param name="action" value="deleteDelega" />
									<portlet:param name="idDelega" value="${delega.id}" />
								</portlet:actionURL>
						   		<a href="${delDelegaUrl}" title="<spring:message code="button.delete" />" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
						   			<spring:message code="button.delete" />
						   		</a>
						   		&nbsp;-&nbsp;
						   		<portlet:resourceURL id="downloadAllegato" var="downloadAllegatoUrl">
										<portlet:param name="id" value="${delega.id}" />
								</portlet:resourceURL>
							</display:column>
						</display:table>
					</c:when>
					<c:otherwise>
						<spring:message code="label.delegatiNonPresenti"/>.						
					</c:otherwise>
				</c:choose>
			</fieldset>
		</c:when>
		<c:otherwise>
			<div>
				<spring:message code="label.nessunCAF.trovato"/>
			</div>
		</c:otherwise>
	</c:choose>
</div>