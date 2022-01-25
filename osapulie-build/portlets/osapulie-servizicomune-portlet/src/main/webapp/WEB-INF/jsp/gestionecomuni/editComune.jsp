<%@ include file="../common/common.jsp"%>

<portlet:renderURL var="annullaUrl" />

<portlet:actionURL var="saveEntityUrl">
	<portlet:param name="action" value="save" />
</portlet:actionURL>

<div class="mainDiv">
	<form:form id="editForm" commandName="entity" method="post" action="${saveEntityUrl}" enctype="multipart/form-data">
		<fieldset>
			<legend><spring:message code="label.datiComune" /></legend>
			<table class="genericTable">
				<tr>
					<td>
						<label><spring:message code="label.nome" />:</label>
					</td>
					<td>
						<form:input id="textNome" path="nome" />
						<div><form:errors path="nome" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.descrizione" />:</label>
					</td>
					<td>
						<form:input id="textDescrizione" path="descrizione" />
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.codiceIstat" />:</label>
					</td>
					<td>
						<form:input id="textCodiceIstat" path="codiceIstat" />
						<div><form:errors path="codiceIstat" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.cap" />:</label>
					</td>
					<td>
						<form:input id="textCap" path="cap" />
						<div><form:errors path="cap" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.logo" />:</label>
					</td>
					<td>
						<form:input path="logoFile" type="file" />
						<div>
							<c:choose>
								<c:when test="${fn:length(entity.logo) > 0}">
									<portlet:resourceURL var="showLogoUrl" id="showLogo">
										<portlet:param name="id" value="${entity.id}" />
									</portlet:resourceURL>
									<img class="logoComuneImg" src="${showLogoUrl}" alt="<spring:message code="label.logo" />" title="<spring:message code="label.logo" />"/>
									<portlet:actionURL var="deleteLogoUrl">
										<portlet:param name="action" value="deleteLogo" />
									</portlet:actionURL>
									<a href="#" onclick="deleteLogo('<spring:message code="message.confirmLogoDelete" />','${deleteLogoUrl}');return false;">
										<img class="deleteLogoImg" src="<%=request.getContextPath() %>/images/ko.png" alt="<spring:message code="label.eliminaLogo" />" title="<spring:message code="label.eliminaLogo" />"/>
									</a>
								</c:when>
								<c:otherwise>
									<img class="logoComuneImg" src="<%=request.getContextPath() %>/images/no_logo.png" alt="<spring:message code="label.noLogo" />" title="<spring:message code="label.noLogo" />"/>
								</c:otherwise>
							</c:choose>
						</div>
						<div><form:errors path="logoFile" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.uriServiceGateway" />:</label>
					</td>
					<td>
						<form:input id="textUriServiceGateway" path="uriServizioGateway" />
						<div><form:errors path="uriServizioGateway" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.canaleComunicazione" />:</label>
					</td>
					<td>
						<form:select path="canaleComunicazione">
							<form:option value=""><spring:message code="label.seleziona" /></form:option>
							<form:option value="${PortletConstants.CANALE_COMUNICAZIONE_PEC}"><spring:message code="label.select.pec" /></form:option>
							<form:option value="${PortletConstants.CANALE_COMUNICAZIONE_PROTOCOLLO}"><spring:message code="label.protocollo" /></form:option>
						</form:select>
						<div><form:errors path="canaleComunicazione" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.gestoreComune" />:</label>
					</td>
					<td>
						<form:input id="textGestoreComune" path="gestoreComune.codiceFiscale" />
						<div><form:errors path="gestoreComune.codiceFiscale" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.visura.posizioni.tributarie.attivo" />:</label>
					</td>
					<td>
						<form:checkbox path="visuraPosizioniTributarieActive"/>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.attivo" />:</label>
					</td>
					<td>
						<form:checkbox path="attivo"/>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend><spring:message code="label.datiPec" /></legend>
			<table class="genericTable">
				<tr>
					<td>
						<label><spring:message code="label.pec" />:</label>
					</td>
					<td>
						<form:input id="textPec" path="pec" />
						<div><form:errors path="pec" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span class="italic"><spring:message code="label.email.isa" /></span>:
						&nbsp;<span class="italic"><c:out value="${emailHostIsa}"/></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span class="italic"><spring:message code="label.pec.isa" /></span>:
						&nbsp;<span class="italic"><c:out value="${pecHostIsa}"/></span>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend><spring:message code="label.datiProtocollo" /></legend>
			<table class="genericTable">
				<tr>
					<td>
						<label><spring:message code="label.codiceAOO" />:</label>
					</td>
					<td>
						<form:input id="codiceAOO" path="codiceAOO" />
						<div><form:errors path="codiceAOO" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.amministrazione" />:</label>
					</td>
					<td>
						<form:input id="amministrazione" path="amministrazione" />
						<div><form:errors path="amministrazione" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.uriProtocollo" />:</label>
					</td>
					<td>
						<form:input id="uriProtocollo" path="uriProtocollo" />
						<div><form:errors path="uriProtocollo" cssStyle="color:red"/></div>
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.downloadAllegati" />:</label>
					</td>
					<td>
						<form:checkbox path="downloadAllegati"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="container_pulsante">
			<input name="Salva" type="submit" value="<spring:message code="button.salva" />" onclick="javascript:return(confirm('<spring:message code="message.confirmSave" />'))"/>
			<span class="spacer"></span>
			<a class="custom_pulsante" href="${annullaUrl}" title="<spring:message code="button.annulla" />">
				<spring:message code="button.annulla" />
			</a>
		</div>
	</form:form>
</div>