<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="visuraPosizioneElettoraleUrl">
	<portlet:param name="action" value="visura" />		
</portlet:actionURL>

<portlet:actionURL var="inviaSegnalazioneUrl">
	<portlet:param name="action" value="inviaSegnalazione" />		
</portlet:actionURL>
	
<portlet:renderURL var="homeUrl" />

<div class="mainDiv">
	<div class="printDiv">
		<div class="container_pulsante">
			<a href="${homeUrl}" title="<spring:message code="button.back" />" class="custom_pulsante"><spring:message code="button.back" /></a>
		</div>
		
		<c:if test="${ empty datiElettorali.errore}">
			<form:form id="inviaSegnalazione" action="${inviaSegnalazioneUrl}" method="post" commandName="datiElettorali">
				<fieldset>
					<legend>
						<label><spring:message code="label.descrizione" />&nbsp;<spring:message code="label.titolo" /></label>
					</legend>
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>
						<tr>
							<td width="25%"><label><spring:message code="label.cf" /></label></td>
							<td>${codiceFiscale}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.nome" /></label></td>
							<td>${componenteFamiglia.nome}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.cognome" /></label></td>
							<td>${componenteFamiglia.cognome}</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.dataN" /></label></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataNascita.time}" /></td>
						</tr>
						<tr>
							<td><label><spring:message code="label.indRes" /></label></td>
							<td>
								${datiAnagrafici.toponimoIndirizzo} ${datiAnagrafici.descrizioneVia} ${datiAnagrafici.numeroCivico}
								<c:if test="${!empty datiAnagrafici.esponente}">
									${datiAnagrafici.esponente}  
								</c:if>
								<c:if test="${!empty datiAnagrafici.piano}">
									p.${datiAnagrafici.piano}  
								</c:if>
							</td>
						</tr>
					<c:forEach var="item" begin="0" items="${datiElettorali.posizioniElettorali}">
						<tr>
							<td class="cellaVuota"></td>
							<td class="cellaVuota"></td>
						</tr>
						<tr>
								<td><label><spring:message code="label.statoIscr" /></label></td>
								<td><c:if test="${!empty item.annoIscrizioneElett}">Iscritto</c:if></td>
							</tr>
							<tr>
								<td><label><spring:message code="label.dataIscr" /></label></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.dataVerbaleIscrizione.time}" /></td>
							</tr>
							<tr>
								<td><label><spring:message code="label.annoIscr" /></label></td>
								<td>${item.annoIscrizioneElett}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.verbaleIscr" /></label></td>
								<td>${item.numVerbaleIscrizione}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.numSez" /></label></td>
								<td>${item.numeroSezione}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.listaGen" /></label></td>
								<td>${item.numeroListaGenerale}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.listaSez" /></label></td>
								<td>${item.numeroListaSezionale}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.descrizioneLista" /></label></td>
								<td>${item.descrizioneLista}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.tipologiaElettore" /></label></td>
								<td>${item.tipoElettore}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.fascicolo" /></label></td>
								<td>${item.numeroFascicolo}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.tessera" /></label></td>
								<td>
									<table class="genericTable richTable richTableWhite noMargin">
										<tr><td colspan="2" class="cellaPiena"></td></tr>
										<tr>
											<td width="25%"><label><spring:message code="label.num" /></label></td>
											<td>${item.numeroTesseraElettorale}</td>
										</tr>
										<tr>
											<td><label><spring:message code="label.dataRil" /></label></td>
											<td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.dataRilascioTesseraElett.time}" /></td>
										</tr>
										<tr><td colspan="2" class="cellaVuota"></td></tr>
									</table>
								</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.iscr" /></label></td>
								<td>
									<table class="genericTable richTable richTableWhite noMargin">
										<tr><td colspan="2" class="cellaPiena"></td></tr>
										<tr>
											<td width="25%"><label><spring:message code="label.scrutatori" /></label></td>
											<td>
												<c:if test="${item.iscrizioneAlboScrutatori == true}">Si</c:if>
												<c:if test="${item.iscrizioneAlboScrutatori == false}">No</c:if>
											</td>
										</tr>
										<tr>
											<td><label><spring:message code="label.presidenti" /></label></td>
											<td>
												<c:if test="${item.iscrizioneAlboScrutatori == true}">Si</c:if>
												<c:if test="${item.iscrizioneAlboScrutatori == false}">No</c:if>
											</td>
										</tr>
										<!--  <tr><td colspan="2" class="cellaVuota"></td></tr>-->
									</table>
								</td>
							</tr>
					
						</c:forEach>						
						
					
					</table>
				</fieldset>
				<div class="container_pulsante">
					<c:if test="${sendReportsEnable}">
						<input type="submit" name="invia" value="<spring:message code="button.inviaSegnalazione" />"/>
					</c:if>
					<%@ include file="../common/valutaservizio.jsp"%>
				</div>
			</form:form>
		</c:if> 	
	
		<c:if test="${! empty datiElettorali.errore }">
			<div class="portlet-msg-error"><spring:message code="errore.pdds.codice${datiElettorali.errore.codice }" /></div>
		</c:if>
	</div>
</div>