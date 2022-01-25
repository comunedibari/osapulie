<%@ include file="../common/common.jsp"%>
 <style type="text/css" media="print">
 #column-1{
	display: none;
	}
#footer{
	display: none;
}
.dockbar {
	display: none;
	}
header{
	display: none;
	}
	.site-breadcrumbs {
	display: none;
	}
	input[type="submit"]{
	display: none;
	}
	.portlet-borderless-bar{
	display: none;
	}
	  </style>
 
<script src="/gestionepratiche-portlet/javascript/funzioni.js" type="text/javascript"></script>

<portlet:actionURL var="ricercaUrl">
	<portlet:param name="action" value="home" />
</portlet:actionURL>

<portlet:actionURL var="inserisciTypeUrl">
	<portlet:param name="action" value="newTypePraticaForm" />
</portlet:actionURL>

<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>

<div class="mainDiv">
	<div>		
		<%-- <form:form id="commandForm" commandName="page" method="post" action="${inserisciTypeUrl}">
			<input name="Nuovo" type="submit" value="<spring:message code="button.nuovo" />" />
		</form:form> --%>
		<c:choose>
			<c:when test="${! empty tipologie }">
				<form:form id="commandForm" commandName="page" method="post" action="${inserisciTypeUrl}">
					<input name="Nuovo" type="submit" value="<spring:message code="button.nuovo" />" />
				</form:form>
			</c:when>
			<c:otherwise>
				<h3><spring:message code="label.noTipiPratiche"/></h3>
			</c:otherwise>
		</c:choose>	
	</div>
			
	<!-- Elenco delle pratiche -->
	<c:choose>
		<c:when test="${!empty elencoPraticheWeb}">
		<br/>
	<h3><spring:message code="label.elenco" /></h3>
	<a href="javascript:this.print()" title="<spring:message code="button.stampa" />"><img src="<%=request.getContextPath() %>/images/print2.png" alt="<spring:message code="button.stampa" />" border="0px"></a>
	
	<form:form id="elencoPraticheWebForm" commandName="page" method="post">	 
		<display:table id="paginatedTable" requestURI="${ricercaUrl}" name="elencoPraticheWeb" uid="pratica" pagesize="" sort="list" class="elencoRisultati">
			<display:column titleKey="label.oggettoRichiesta" >
				<c:choose>
						<c:when test="${pratica.invioPec != 'S'}">
					<portlet:actionURL var="selectPraticaUrl">
							<portlet:param name="action" value="detail" />
							<portlet:param name="id" value="${pratica.id}" />						
						</portlet:actionURL>
				   	<a href="${selectPraticaUrl}" title="<spring:message code="button.select" />">
						<c:out value="${pratica.oggettoRichiesta }"></c:out>
					</a>
				</c:when>
					<c:otherwise>	
						<c:out value="${pratica.oggettoRichiesta }"></c:out>
						</c:otherwise>
						</c:choose>
			</display:column>	
			<display:column titleKey="label.dataRichiesta" sortable="true" sortName="pratica.dataRichiesta" >
				<fmt:formatDate value="${pratica.dataRichiesta.time}" pattern="dd/MM/yyyy" />
			</display:column>
			<display:column titleKey="label.stato" sortable="true" >
					<c:if test="${pratica.stato eq 'A'}">ACCETTATA</c:if>
					<c:if test="${pratica.stato eq 'P'}">PRESENTATA</c:if>
					<c:if test="${pratica.stato eq 'R'}">RIFIUTATA<br/>${pratica.motivoRifiuto }</c:if>
				</display:column>
			<display:column property="tipologia.descrizione" sortable="true" titleKey="label.tipologia" />
			<display:column titleKey="label.richiedente" sortable="true" >
				<c:if test="${pratica.richNominativo != null}">
					<c:out value="${pratica.richNominativo}"/>
				</c:if>
			</display:column>
				<display:column titleKey="label.immobile" sortable="true" >
					<c:if test="${pratica.immobileTipo.descrizione != null}">
						<c:out value="${pratica.immobileTipo.descrizione}"/>
					</c:if>
				</display:column>
			<display:column sortable="false" style="width: 30px;padding: 0px 5px 0px 5px;">
				<c:if test="${pratica.invioPec == 'D'}">
					<portlet:resourceURL var="downloadURL" id="downloadModello" escapeXml="false">
								<portlet:param name="id" value="${pratica.id}" />
					</portlet:resourceURL>				
					<a href="${downloadURL }" title="<spring:message code="link.download" />"><img src="<%=request.getContextPath() %>/images/download.png" alt="<spring:message code="link.download" />" width="25px" /></a>
				</c:if>
			</display:column>
				<display:column sortable="false" style="width: 30px;padding: 0px 5px 0px 5px;">
					<c:if test="${pratica.invioPec != 'S'}">
						<portlet:actionURL var="uploadUrl">
							<portlet:param name="ope" value="getUploadForm" />
							<portlet:param name="id" value="${pratica.id}" />
						</portlet:actionURL>
						<a href="${uploadUrl }" title="<spring:message code="button.invia" />"><img src="<%=request.getContextPath() %>/images/da_inviare.png" alt="" width="25px" alt="<spring:message code="button.invia" />"/></a>
					</c:if>
					<c:if test="${pratica.invioPec == 'S'}">
						<img src="<%=request.getContextPath() %>/images/inviato.png" alt="" width="25px" />
					</c:if>	
				</display:column>				
		</display:table>
	</form:form>
	</c:when>
		<c:otherwise>
			<c:if test="${!empty elencoPraticheWeb }">
			<div>
				<h3><spring:message code="label.noPratiche"/></h3>
			</div>								
			</c:if>
		</c:otherwise>
	</c:choose>	
</div>

