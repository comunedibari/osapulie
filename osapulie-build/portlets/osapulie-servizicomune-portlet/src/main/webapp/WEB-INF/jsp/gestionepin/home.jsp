<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv">
<c:choose>
	<c:when test="${access}">
		<fieldset>
			<legend><spring:message code="label.ricercaPin"/></legend>
			<form:form id="searchForm" action="${home}" commandName="pin" method="post">
				<table>
					<tr>
						<td>
							<spring:message code="label.cognome"/>:&nbsp;<input type="text" name="profiloUtenteCittadino.cognome" value="${cognome}">
						</td>
						<td>
							<spring:message code="label.nome"/>:&nbsp;<input type="text" name="profiloUtenteCittadino.nome" value="${nome}">
						</td>
						<td>
							<spring:message code="label.cf"/>:&nbsp;<input type="text" name="profiloUtenteCittadino.codifceFiscale" value="${cf}">
						</td>
						<td>
							<spring:message code="label.stato"/>:&nbsp;
							<select name="statoPinSelect" class="searchSelect">
								<option value=""><spring:message code="label.select"/></option>
								<option value="${PortletConstants.STATO_PIN_RICHIESTO}" <c:if test="${statoPinSelect == PortletConstants.STATO_PIN_RICHIESTO}">selected="selected"</c:if>><spring:message code="label.select.pinRichiesto"/></option>
								<option value="${PortletConstants.STATO_PIN_RESPINTO}" <c:if test="${statoPinSelect == PortletConstants.STATO_PIN_RESPINTO}">selected="selected"</c:if>><spring:message code="label.select.pinRespinto"/></option>
								<option value="${PortletConstants.STATO_PIN_ANNULLATO}" <c:if test="${statoPinSelect == PortletConstants.STATO_PIN_ANNULLATO}">selected="selected"</c:if>><spring:message code="label.select.pinAnnullato"/></option>
								<option value="${PortletConstants.STATO_PIN_ASSEGNATO}" <c:if test="${statoPinSelect == PortletConstants.STATO_PIN_ASSEGNATO}">selected="selected"</c:if>><spring:message code="label.select.pinAssegnato"/></option>
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
			<c:when test="${pinList != null}">
				<fieldset>
					<legend>
						<c:choose>
							<c:when test="${comuneIsa != null}">
								<spring:message code="label.listaPin" arguments="${comuneIsa.nome}"/>								
							</c:when>
							<c:otherwise>
								<spring:message code="label.listaPinAll"/>							
							</c:otherwise>
						</c:choose>
					</legend>						
				 	<display:table id="paginatedTable" requestURI="${home}" name="pinList" uid="pin" cellpadding="2" cellspacing="0" pagesize="20" defaultorder="descending" defaultsort="4" sort="list" class="elencoRisultati">
						<display:column sortable="true" property="profiloUtenteCittadino.cognome" titleKey="label.cognome" />
						<display:column sortable="true" property="profiloUtenteCittadino.nome" titleKey="label.nome" />
						<display:column sortable="true" property="comuneIsa.nome" titleKey="label.comune" />
						<display:column sortable="true" property="dataRichiesta" titleKey="label.dataRichiesta" format="{0,date,dd/MM/yyyy - HH:mm}"/>
						<display:column sortable="true" titleKey="label.dataAttivazione">
							<c:choose>
								<c:when test="${pin.dataAttivazione != null}">
									<fmt:formatDate value="${pin.dataAttivazione}" pattern="dd/MM/yyyy - HH:mm"/>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column sortable="false" titleKey="label.stato" property="stato" class="center" headerClass="center"/>
						<display:column sortable="false">
							<portlet:renderURL var="detailPinUrl">
								<portlet:param name="action" value="detailPin" />
								<portlet:param name="idPin" value="${pin.id}" />
							</portlet:renderURL>
					   		<a href="${detailPinUrl}" title="<spring:message code="button.detail" />">
					   			<spring:message code="button.detail" />
					   		</a>
						</display:column>
					</display:table>
					<portlet:renderURL var="newPin">
						<portlet:param name="action" value="editPin" />
					</portlet:renderURL>
				</fieldset>
			</c:when>
			<c:otherwise>
				<spring:message code="label.pinNonPresenti"/>.						
			</c:otherwise>
		</c:choose>
		</c:when>
	<c:otherwise>
		<spring:message code="label.accessDenied"/>.
	</c:otherwise>
</c:choose>
</div>