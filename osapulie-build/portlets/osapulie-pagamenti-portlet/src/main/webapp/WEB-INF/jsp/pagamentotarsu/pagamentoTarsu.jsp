<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ include file="../common/common.jsp"%>

<div class="mainDiv">
	<c:if test="${! empty dati}">
		<fieldset>
			<legend>
				<label><spring:message code="label.descrizione" />&nbsp;<spring:message code="label.titoloTarsu" /></label>
			</legend>
			
			<c:forEach var="elenco" begin="0" items="${dati.elencoPagamentoTarsu}">
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
					<c:forEach var="utenza" begin="0" items="${elenco.posizioniTarsu}">
						<tr>
							<td width="25%"><label><spring:message code="label.identificativoUtenza" /></label></td>
							<td>${utenza.identificativoUtenza}</td>
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
							<td><label><spring:message code="label.descrizioneTariffa" /></label></td>
							<td>${utenza.descrizioneTariffa}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.importoTariffa" /></label></td>
							<td>${utenza.importoTariffa}&nbsp;&euro;</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.importoDaPagare" /></label></td>
							<td>${utenza.importoDaPagare}&nbsp;&euro;</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.addizionaleComunale" /></label></td>
							<td>${utenza.addizionaleEca}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.maggiorazione" /></label></td>
							<td>${utenza.maggiorazioneEca}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.addizionaleProvinciale" /></label></td>
							<td>${utenza.addizionaleProvinciale}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.importoRiduzioni" /></label></td>
							<td>${utenza.importoRiduzioni}&nbsp;&euro;</td>
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
							<td>${rata.importoRata}</td>
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
							<td>${rata.importoPagato}</td>
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