<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv">
<c:choose>
	<c:when test="${access}">
		<fieldset>
			<legend><spring:message code="label.ricercaDeleghe"/></legend>
			<form:form id="searchForm" action="${home}" commandName="delega" method="post">
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
						<td>
							<spring:message code="label.stato"/>:&nbsp;
							<select name="statoDelegaSelect" class="searchSelect">
								<option value=""><spring:message code="label.select"/></option>
								<option value="${PortletConstants.STATO_DELEGA_ATTIVA}" <c:if test="${statoDelegaSelect == PortletConstants.STATO_DELEGA_ATTIVA}">selected="selected"</c:if>><spring:message code="label.select.statoDelegaApprovata"/></option>
								<option value="${PortletConstants.STATO_DELEGA_INATTIVA}" <c:if test="${statoDelegaSelect == PortletConstants.STATO_DELEGA_INATTIVA}">selected="selected"</c:if>><spring:message code="label.select.statoDelegaNonApprovata"/></option>
							</select>
						</td>
					</tr>
				</table>
				<div class="container_pulsante">
					<input type="submit" value="<spring:message code="button.cerca" />" />
				</div>
			</form:form>
		</fieldset>
		<c:choose>
			<c:when test="${delegaList != null}">
				<fieldset>
					<legend>
						<c:choose>
							<c:when test="${comuneIsa != null}">
								<spring:message code="label.listaDeleghe" arguments="Bari"/>
							</c:when>
							<c:otherwise>
								<spring:message code="label.listaDelegheAll"/>							
							</c:otherwise>
						</c:choose>
					</legend>						
				 	<display:table id="paginatedTable" requestURI="${home}" name="delegaList" uid="delega" cellpadding="2" cellspacing="0" pagesize="20" defaultorder="descending" defaultsort="5" sort="list" class="elencoRisultati">
						<display:column sortable="true" property="delegante.cognome" titleKey="label.cognome" />
						<display:column sortable="true" property="delegante.nome" titleKey="label.nome" />
						<display:column sortable="true" property="delegato.partitaIva" titleKey="label.pivacf" />
						<display:column sortable="true" property="comuneIsa.nome" titleKey="label.comune" />
						<display:column sortable="true" property="dataRichiesta" titleKey="label.dataRichiesta" format="{0,date,dd/MM/yyyy - HH:mm}"/>
						<display:column sortable="true" titleKey="label.dataApprovazione">
							<c:choose>
								<c:when test="${delega.attiva}">
									<fmt:formatDate value="${delega.dataAttivazione}" pattern="dd/MM/yyyy - HH:mm"/>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column sortable="false" titleKey="label.stato" class="center" headerClass="center">
							<c:choose>
								<c:when test="${delega.attiva}">
									<img src="<%=request.getContextPath() %>/images/ok.png" alt="<spring:message code="label.images.delegaApprovata"/>" title="<spring:message code="label.images.delegaApprovata"/>"/>							
								</c:when>
								<c:otherwise>
									<img src="<%=request.getContextPath() %>/images/ko.png" alt="<spring:message code="label.images.delegaNonApprovata"/>" title="<spring:message code="label.images.delegaNonApprovata"/>"/>								
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column sortable="false" class="center" headerClass="center">
							<portlet:renderURL var="detailDelegaUrl">
								<portlet:param name="action" value="detailDelega" />
								<portlet:param name="idDelega" value="${delega.id}" />
							</portlet:renderURL>
					   		<a href="${detailDelegaUrl}" title="<spring:message code="button.detail" />">
					   			<spring:message code="button.detail" />
					   		</a>
					   		<%-- &nbsp;-&nbsp;
							<portlet:actionURL var="delDelegaUrl">
								<portlet:param name="action" value="deleteDelega" />
								<portlet:param name="idDelega" value="${delega.id}" />
							</portlet:actionURL>
					   		<a href="${delDelegaUrl}" title="<spring:message code="button.delete" />" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
					   			<spring:message code="button.delete" />
					   		</a> --%>
						</display:column>
					</display:table>
					<portlet:renderURL var="newDelega">
						<portlet:param name="action" value="editDelega" />
					</portlet:renderURL>
				</fieldset>
			</c:when>
			<c:otherwise>
				<spring:message code="label.delegatiNonPresenti"/>.						
			</c:otherwise>
		</c:choose>
		</c:when>
	<c:otherwise>
		<spring:message code="label.accessDenied"/>.
	</c:otherwise>
</c:choose>
</div>