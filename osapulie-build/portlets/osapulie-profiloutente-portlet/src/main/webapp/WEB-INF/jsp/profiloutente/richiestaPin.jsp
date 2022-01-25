<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="updatePin">
	<portlet:param name="action" value="updatePin" />		
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="action" value="home" />		
</portlet:renderURL>
<div class="mainDiv richiestaPin">
	<c:choose>
		<c:when test="${profiloUtente != null && !disableForIdp}">
			<form:form id="profiloUtenteForm" action="${updatePin}" method="post" commandName="pin">
				<fieldset>
					<legend>
						<spring:message code="label.richiestaPin"/>
					</legend>
					<div>
						<span class="">
							<spring:message code="label.richiestaPin.descrizione"/>
						</span>
					</div>
					<div>
						<c:if test="${pin.stato == null || pin.stato == PortletConstants.STATO_PIN_RESPINTO || pin.stato == PortletConstants.STATO_PIN_ANNULLATO}">
							<span>
								<spring:message code="label.richiestaPin.nuova"/>
								<c:choose>
									<c:when test="${profiloUtente.mailPec != ''}">
										<spring:message code="label.richiestaPin.nuovaPec"/>							
									</c:when>
									<c:otherwise>
										<spring:message code="label.richiestaPin.nuovaNoPec"/>
									</c:otherwise>
								</c:choose>
							</span>
						</c:if>
						<c:if test="${pin.stato != null}">
							<c:choose>
								<c:when test="${pin.stato != PortletConstants.STATO_PIN_ASSEGNATO && pin.stato != PortletConstants.STATO_PIN_ANNULLATO}">
									<portlet:actionURL var="updatePinAnnullato">
										<portlet:param name="action" value="annullaPin" />		
										<portlet:param name="idPin" value="${pin.id}" />		
									</portlet:actionURL>
									<span>
										<fmt:formatDate var="dataRichiesta" value="${pin.dataRichiesta}" pattern="dd/MM/yyyy"/>
										<spring:message code="label.pinEsistente" arguments="${dataRichiesta},${pin.comuneIsa.nome}"/>
									</span>	
									<br/>					
									<a href="${updatePinAnnullato}" onclick="javascript:return confirm('<spring:message code="confirm.annullaPin"/>');" >
										<spring:message code="button.annullaPin"/>
									</a>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${pin.stato == null || pin.stato == PortletConstants.STATO_PIN_RESPINTO || pin.stato == PortletConstants.STATO_PIN_ANNULLATO}">
							<table class="genericTable">
								<tr>
									<td width="250">
										<spring:message code="label.comuneIsa"/>:
									</td>
									<td>
										<form:select path="comuneIsa.id" id="selectComuneIsa">
											<form:option value="0"><spring:message code="label.seleziona"/></form:option>
											<form:options items="${comuneISAList}" itemLabel="nome" itemValue="id"/>
										</form:select>
										<form:errors path="comuneIsa.id" cssStyle="color:red"/>
									</td>
								</tr>
							</table>
						</c:if>		
					</div>
				</fieldset>
				<div class="container_pulsante">
					<c:if test="${pin.stato == null || pin.stato == PortletConstants.STATO_PIN_RESPINTO || pin.stato == PortletConstants.STATO_PIN_ANNULLATO}">
						<input type="submit" name="<spring:message code="button.inviaRichiesta"/>" value="<spring:message code="button.inviaRichiesta"/>" onclick="javascript:return confirm('<spring:message code="confirm.richiestaPin"/>');"/>
						<span class="spacer"></span>
					</c:if>
					<a class="custom_pulsante" href="${homeUrl}">
						<spring:message code="button.annulla" />
					</a>
				</div>
			</form:form>
		</c:when>
		<c:when test="${profiloUtente != null && disableForIdp}">
			<spring:message code="label.noUserFound"/>
		</c:when>
		<c:otherwise>
			<spring:message code="label.noUserFound"/>							
		</c:otherwise>
	</c:choose>
</div>