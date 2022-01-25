<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ include file="../common/common.jsp"%>

<div class="mainDiv">
	<c:if test="${! empty dati}">
		<fieldset>
			<legend>
				<label><spring:message code="label.descrizione" />&nbsp;<spring:message code="label.titolo" /></label>
			</legend>
			
			<c:forEach var="elencoOsap" begin="0" items="${dati.elencoPagamentiOsapTemporanea}">
				<table class="genericTable elencoRisultati">
					<tr>
						<th><spring:message code="label.numeroConto" /></th>
						<th><spring:message code="label.annoRiferimento" /></th>
						<th><spring:message code="label.numeroDocumento" /></th>
						<th><spring:message code="label.importoDocumento" /></th>
						<th><spring:message code="label.dataAggiornamento" /></th>
					</tr>
					<tr>
						<td>${elencoOsap.contoCorrente}</td>
						<td>${elencoOsap.annoRiferimento}</td>
						<td>${elencoOsap.numeroDocumento}</td>
						<td>${elencoOsap.importoDocumento}&nbsp;&euro;</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elencoOsap.dataAggiornamento.time}" /></td>
					</tr>
				</table>	
				
				<table class="genericTable richTable">
					<tr><td colspan="2" class="cellaPiena"></td></tr>
					<c:forEach var="utenza" begin="0" items="${elencoOsap.posizioniOsap}" varStatus="numeroPosizione">
						<tr>
							<td width="25%"><label><spring:message code="label.zonaUtenza" /></label></td>
							<td>${utenza.zona}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.indirizzoUtenza" /></label></td>
							<td>${utenza.indirizzoUtenza}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.superficie" /></label></td>
							<td>${utenza.superficie}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.durataOccupazione" /></label></td>
							<td>${utenza.durataOccupazione}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.descrizioneTariffa" /></label></td>
							<td>${utenza.descrizioneTariffa}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.importoDaPagare" /></label></td>
							<td>${utenza.importoDaPagare}&nbsp;&euro;</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.importoPagato" /></label></td>
							<td>${utenza.importoPagato}&nbsp;&euro;</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.dataPagamento" /></label></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${utenza.dataPagamento.time}" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<portlet:renderURL var="pagamentoRichiestaDati" windowState="NORMAL">
									<portlet:param name="action" value="renderDatiPagamento" />
									<portlet:param name="numeroPosizione" value="${numeroPosizione.index}" />
								</portlet:renderURL>
								<div class="container_pulsante">
									<a href="${pagamentoRichiestaDati}" >
										<input type="button" name="invia" value="Paga Ora" class="custom_pulsante"/>
									</a>
								</div>
							</td>
						</tr>
						<tr><td colspan="2" class="cellaVuota"></td></tr>
					</c:forEach>
				</table>
			</c:forEach>
		</fieldset>
	</c:if> 	
	<c:if test="${empty dati}">
		<div class="portlet-msg-alert">
			<spring:message code="error.label.datiNonDisponibili" />
		</div>
	</c:if>
</div>