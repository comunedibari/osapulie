<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="ricercaUrl">
	<portlet:param name="action" value="searchPraticaForm" />
</portlet:actionURL>

<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>

<div class="mainDiv">
	<fieldset>
			<legend><spring:message code="label.ricercaPratiche"/></legend>
			<form:form id="searchForm" commandName="filtriRicercaPraticheOnline" action="${ricercaUrl}" method="post">
				<table>					
					<tr>
						<td>
							<spring:message code="label.oggetto"/>:&nbsp;</td>
						<td colspan="3">
							<form:input size="80" maxlength="255" path="oggetto" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.tipologia"/>:&nbsp;
						</td>
						<td colspan="3">
							<form:select path="tipologia">
								<form:option  value=""><spring:message code="label.select"/></form:option>
								<form:options items="${tipologie}" itemLabel="descrizione" itemValue="id"/>								
							</form:select>
						</td>
					</tr>	
					<tr>
						<td>
							<spring:message code="label.numero"/>:
						</td>
						<td>
							<form:input path="numero" maxlength="155" />
						</td>
						<c:if test="${showStato}">
						<td>
							<spring:message code="label.stato" />:
						</td>
						<td>
							<form:select path="statopratica">
								<option value=""><spring:message code="label.select"/></option>
								<c:forEach var="item" begin="0" items="${stati}">									
							    	<option value="${item.id}" <c:if test="${filtriRicercaPraticheOnline.statopratica == item.id}"> selected="selected" </c:if> >${item.descrizione}</option>
								</c:forEach>
							</form:select>							
						</td>
						</c:if>
					</tr>					
				</table>
				<div class="buttonsDiv">
					<input type="submit" value="<spring:message code="button.cerca" />" />					
				</div>	
					</form:form>		
		</fieldset>
	<!-- Elenco delle pratiche -->
	<c:choose>
		<c:when test="${!empty elencoPratiche}">
		<br/>
	<h3><spring:message code="label.elenco" /></h3>
	<form:form id="elencoPraticheForm" commandName="page" method="post">
	<display:table id="paginatedTable" requestURI="${ricercaUrl}" name="elencoPratiche" uid="pratica" cellpadding="2" cellspacing="0" pagesize="20" sort="list" class="elencoRisultati">
		
			<%-- <display:column property="id" titleKey="label.id" /> --%>
			<display:column property="numeroPratica" sortable="true" titleKey="label.numeroPratica" />
			<display:column property="oggettoRichiesta" titleKey="label.oggettoRichiesta" />
			<display:column titleKey="label.dataRichiesta"
			sortable="true" sortName="pratica.dataRichiesta" >
				<fmt:formatDate value="${pratica.dataRichiesta.time}" pattern="dd/MM/yyyy" />
			</display:column>
			<display:column property="tipologia.descrizione" sortable="true" titleKey="label.tipologia" />
			<c:if test="${showStato}">
			<display:column property="stato.descrizione" sortable="true" titleKey="label.stato" />
			</c:if>
			<display:column titleKey="label.richiedente">
				<c:if test="${pratica.richiedente != null}">
					<c:out value="${pratica.richiedente.nominativo}"/>
				</c:if>
			</display:column>
			<display:column sortable="false" style="width: 50px;padding: 0px 5px 0px 5px;">
				   	<portlet:actionURL var="selectPraticaUrl">
						<portlet:param name="action" value="detail" />
						<portlet:param name="id" value="${pratica.id}" />					
					</portlet:actionURL>
			   	<a href="${selectPraticaUrl}" title="<spring:message code="button.select" />">
					<spring:message code="button.select" />
				</a>		  	
			</display:column>
		</display:table>
	</form:form>
	</c:when>
		<c:otherwise>
			<c:if test="${empty elencoPratiche }">
			<div>
				<h3><spring:message code="label.noPratiche"/></h3>
			</div>								
			</c:if>
		</c:otherwise>
	</c:choose>
</div>