<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv richiestaDelega">
	<c:choose>
		<c:when test="${delegaList != null}">
			<fieldset>
			 	<legend><spring:message code="label.listaDelegati"/></legend>
			 	<display:table id="paginatedTable" requestURI="${home}" name="delegaList" uid="delega" cellpadding="2" cellspacing="0" pagesize="20" defaultorder="ascending" defaultsort="1" sort="list" class="genericTable elencoRisultati">
					<display:column sortable="true" property="delegato.partitaIva" titleKey="label.pivacf" />
					<display:column sortable="true" property="delegato.ragioneSociale" titleKey="label.ragioneSociale" />
					<display:column sortable="true" property="comuneIsa.nome" titleKey="label.comune" />
					<display:column sortable="true" property="dataRichiesta" titleKey="label.dataRichiesta" format="{0,date,dd/MM/yyyy}"/>
					<display:column sortable="true" titleKey="label.dataApprovazione" class="center">
						<c:choose>
							<c:when test="${delega.attiva}">
								<fmt:formatDate value="${delega.dataAttivazione}" pattern="dd/MM/yyyy"/>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column sortable="false" titleKey="label.stato" class="center">
						<c:choose>
							<c:when test="${delega.attiva}">
								<img src="<%=request.getContextPath() %>/images/ok.png" alt="<spring:message code="label.images.delegaApprovata"/>" title="<spring:message code="label.images.delegaApprovata"/>"/>							
							</c:when>
							<c:otherwise>
								<img src="<%=request.getContextPath() %>/images/ko.png" alt="<spring:message code="label.images.delegaNonApprovata"/>" title="<spring:message code="label.images.delegaNonApprovata"/>"/>								
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column sortable="false" class="center">
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
					</display:column>
				</display:table>
				<portlet:renderURL var="newDelega">
					<portlet:param name="action" value="editDelega" />
				</portlet:renderURL>
				<div class="container_pulsante">
					<a class="custom_pulsante" href="${newDelega}">
						<spring:message code="button.nuovo" />
					</a>
				</div>
			</fieldset>
		</c:when>
		<c:otherwise>
			<div>
				<strong><spring:message code="label.delegatiNonPresenti"/></strong>					
			</div>
		</c:otherwise>
	</c:choose>
</div>