<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv pinDetail">
	<c:choose>
		<c:when test="${pin != null}">
			<h1><spring:message code="label.dettaglioPin" arguments="${pin.comuneIsa.nome}"/></h1>
			<fieldset>
				<legend><spring:message code="label.dettaglioPin2"/></legend>
				<table>
			 		<tr>
			 			<td>
			 				<spring:message code="label.dataRichiesta"/>:
			 			</td>
			 			<td>
			 				<fmt:formatDate value="${pin.dataRichiesta}" pattern="dd/MM/yyyy - HH:mm"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
			 				<spring:message code="label.stato"/>:
			 			</td>
			 			<td>
			 				<c:out value="${pin.stato}"/>
			 			</td>
			 		</tr>
			 		<c:if test="${pin.stato == PortletConstants.STATO_PIN_RESPINTO}">
				 		<tr>
				 			<td>
				 				<spring:message code="label.note"/>:
				 			</td>
				 			<td>
				 				<c:out value="${pin.note}"/>
				 			</td>
				 		</tr>
			 		</c:if>
			 	</table>
			</fieldset>
			<fieldset>
				<legend><spring:message code="label.dettaglioRichiedente"/></legend>
			 	<table>
			 		<tr>
			 			<td>
			 				<spring:message code="label.nome"/>:
			 			</td>
			 			<td>
			 				<c:out value="${pin.profiloUtenteCittadino.nome}"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
			 				<spring:message code="label.cognome"/>:
			 			</td>
			 			<td>
			 				<c:out value="${pin.profiloUtenteCittadino.cognome}"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
			 				<spring:message code="label.cf"/>:
			 			</td>
			 			<td>
			 				<c:out value="${pin.profiloUtenteCittadino.codiceFiscale}"/>
			 			</td>
			 		</tr>
			 	</table>
		 	</fieldset>
		 	<div class="container_pulsante">
		 		<c:if test="${pin.stato == PortletConstants.STATO_PIN_RICHIESTO}">
			 		<portlet:actionURL var="updatePinAssegnato">
						<portlet:param name="action" value="updatePinAssegnato" />
						<portlet:param name="idPin" value="${pin.id}" />
					</portlet:actionURL>
					<a class="custom_pulsante" href="${updatePinAssegnato}" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
						<spring:message code="button.attivaRichiestaPin" />
					</a>
					<span class="spacer"></span>
					<a class="custom_pulsante" href="#" onclick="openCloseDiv('motivazionePinRespinto');return false;">
						<spring:message code="button.respingiRichiestaPin" />
					</a>		
					<!-- Blocco per invio motivazione respinta richiesta PIN -->
					<portlet:actionURL var="updatePinRespinto">
						<portlet:param name="action" value="updatePinRespinto" />
						<portlet:param name="idPin" value="${pin.id}" />
					</portlet:actionURL>
					<form:form action="${updatePinRespinto}" method="post" commandName="pin">
						<div id="motivazionePinRespinto" class="displayNone">
							<spring:message code="label.motivazione" />:&nbsp;
							<form:textarea path="note" cols="30" rows="5"/>
							<div class="container_pulsante">
								<input type="submit" value="<spring:message code="button.respingiRichiestaPin"/>" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
							</div>
						</div>
					</form:form>
				</c:if>			
				<a class="custom_pulsante" href="${home}">
					<spring:message code="button.annulla" />
				</a>
			</div>
		</c:when>
		<c:otherwise>
			<spring:message code="label.accessDenied"/>.								
		</c:otherwise>
	</c:choose>
</div>