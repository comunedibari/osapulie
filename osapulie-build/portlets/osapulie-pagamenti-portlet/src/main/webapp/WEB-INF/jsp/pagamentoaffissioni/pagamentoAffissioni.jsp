<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ include file="../common/common.jsp"%>

<div class="mainDiv">
	<c:if test="${! empty dati}">
		<fieldset>
			<legend>
				<label><spring:message code="label.descrizione" />&nbsp;<spring:message code="label.titoloAffissioni"/></label>
			</legend>
	
			<c:forEach var="elenco" begin="0" items="${dati.elencoPagamentiTassaAffissioni}">
				<table class="genericTable elencoRisultati">
					<tr>
						<th><spring:message code="label.numeroConto" /></th>
						<th><spring:message code="label.annoRiferimento" /></th>
						<th><spring:message code="label.numeroDocumento" /></th>
						<th><spring:message code="label.importoDocumento" /></th>
						<th><spring:message code="label.dataAggiornamento" /></th>
					</tr>
					<tr>
						<td>${elenco.contoCorrente}</td>
						<td>${elenco.annoRiferimento}</td>
						<td>${elenco.numeroDocumento}</td>
						<td>${elenco.importoDocumento}&nbsp;&euro;</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elenco.dataAggiornamento.time}" /></td>
					</tr>
				</table>
				
				<table class="genericTable richTable">
					<tr><td colspan="2" class="cellaPiena"></td></tr>
					<c:forEach var="utenza" begin="0" items="${elenco.posizioniAffissione}">
						<tr>
							<td width="25%"><label><spring:message code="label.numeroFogli" /></label></td>
							<td>${utenza.numeroFogli}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.numeroManifesti" /></label></td>
							<td>${utenza.numeroManifesti}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.giorniAffissione" /></label></td>
							<td>${utenza.giorniAffissione}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.dimensioneManifesti" /></label></td>
							<td>${utenza.dimensioneManifesti}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.tariffa" /></label></td>
							<td>${utenza.tariffa}</td>
						</tr>
						<tr><td colspan="2" class="cellaVuota"></td></tr>
					</c:forEach>
				</table>
			
				<table class="genericTable richTable">
					<tr><td colspan="2" class="cellaPiena"></td></tr>
					<c:forEach var="rata" begin="0" items="${elenco.rate}">
						<tr>
							<td width="25%"><label><spring:message code="label.identificativoRata" /></label></td>
							<td>${rata.identificativoRata}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.numeroRata" /></label></td>
							<td>${rata.numeroRata}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.importoRata" /></label></td>
							<td>${rata.importoRata}&nbsp;&euro;</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.scadenzaRata" /></label></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${rata.scadenzaRata.time}" /></td>
						</tr>
						<tr>
							<td><label><spring:message code="label.dataPagamento" /></label></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${rata.dataPagamento.time}" /></td>
						</tr>
						<tr>
							<td><label><spring:message code="label.importoPagato" /></label></td>
							<td>${rata.importoPagato}&nbsp;&euro;</td>
						</tr>
						<tr>
							<td colspan="2">
								<portlet:renderURL var="pagamentoRichiestaDati" windowState="NORMAL">
									<portlet:param name="action" value="renderDatiPagamento" />
									<portlet:param name="identificativoRata" value="${rata.identificativoRata}" />
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