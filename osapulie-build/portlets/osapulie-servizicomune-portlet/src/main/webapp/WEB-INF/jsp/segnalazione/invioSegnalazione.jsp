<%@page import="it.osapulie.shared.service.SegnalazioneFoglia"%>
<%@page import="it.osapulie.shared.service.Segnalazione"%>
<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="invioSegnalazioneUrl">
		<portlet:param name="action" value="invioSegnalazione" />		
</portlet:actionURL>

<portlet:renderURL var="homeUrl" />

<div class="mainDiv">
	<form:form id="invioSegnalazioniForm" action="${invioSegnalazioneUrl}" commandName="datiSegnalazione" method="post">
		
		<div class="marginBottom5">
			<span><spring:message code="label.segnalazione.intestazione" /></span>&nbsp;
			<span><c:out value="${profiloUtente.cognome}"/></span>&nbsp;
			<span><c:out value="${profiloUtente.nome}"/></span>,
		</div>
		<div class="marginBottom5">
			<span><spring:message code="label.segnalazione.riferimento.visura" /></span>&nbsp;
			<b><c:out value="${segnalazione.servizio}"/></b>&nbsp;
			<span><spring:message code="label.segnalazione.riferimento.testo" /></span>
		</div>
		<ul>		
			<%
				Segnalazione segnalazione = (Segnalazione) request.getAttribute("segnalazione");
				for(SegnalazioneFoglia foglia :segnalazione.getListaSegnalazioni()){
					request.setAttribute("rigaSegnalazione", foglia);
				%>
					<jsp:include page="rigoInvioSegnalazioni.jsp"/>
				<%	
				}
			%>
		</ul>
		<table class="genericTable">
			<tr>
				<td>
					<label><spring:message code="label.segnalazione.commento" /></label>
				</td>
			</tr>
			<tr>
				<td>
					${segnalazione.note}
				</td>
			</tr>
		</table>
		
		<c:if test="${!cntrSegnalazione}">
			<div class="container_pulsante">
				<input type="submit" name="back" value="<spring:message code="button.back"/>"/>
				<span class="spacer"></span>
				<input type="submit" name="invia" value="<spring:message code="button.invia"/>"/>
			</div>
		</c:if>
	</form:form>
</div>