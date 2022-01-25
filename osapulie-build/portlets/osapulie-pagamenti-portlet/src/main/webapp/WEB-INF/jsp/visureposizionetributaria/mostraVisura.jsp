<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="visuraPosizioneTributaria">
		<portlet:param name="action" value="visura" />		
</portlet:actionURL>

<div class="mainDiv">

	<form:form id="visura" action="${visuraPosizioneTributaria}" method="post" cssClass="printForm"> 
	
		<div class="marginBottom10">
			<label><spring:message code="label.annoInizio" />:</label>&nbsp;
			<select name="startYear">
			  <c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
			      <option value="${i}" <c:if test="${i == yearStart}"> selected="selected" </c:if> >${i}</option>
			   </c:forEach>
			</select>
			<label><spring:message code="label.annoFine" />:</label>&nbsp;
			<select name="endYear">
			  <c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
			      <option value="${i}" <c:if test="${i == yearEnd}"> selected="selected" </c:if> >${i}</option>
			   </c:forEach>
			</select>
			<input type="submit" name="invia" value='<spring:message code="button.cerca" />'/>
		</div>
	
		<c:if test="${! empty dati}">
		
			<!-- Elenco OSAP PERMANENTE -->
			<fieldset>
				<legend><spring:message code="label.titoloPermanente" /></legend>
			
				<c:forEach var="elencoOsap" begin="0" items="${dati.elencoPagamentiOsapPermananente}">
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
						<c:forEach var="utenza" begin="0" items="${elencoOsap.posizioniOsap}">
							<tr>
								<td width="25%"><label><spring:message code="label.zonaUtenza" /></label></td>
								<td>${utenza.zonaUtenza}</td>
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
								<td><label><spring:message code="label.mesi" /></label></td>
								<td>${utenza.mesi}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.descrizioneTariffa" /></label></td>
								<td>${utenza.descrizioneTariffa}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoDaPagare" /></label></td>
								<td>${utenza.importoDaPagare}&nbsp;&euro;</td>
							</tr>
							<tr><td colspan="2" class="cellaVuota"></td></tr>	
						</c:forEach>
					</table>
					
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>
						<c:forEach var="rata" begin="0" items="${elencoOsap.rate}">
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
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
				</c:forEach>
				<c:if test="${empty dati.elencoPagamentiOsapPermananente}">
					<spring:message code="label.nessunDatoPresente" />.
				</c:if>
			</fieldset>
			
			<fieldset>
				<!-- ELENCO OSAP TEMPORANEA -->
				<legend><spring:message code="label.titoloTemporanea" /></legend>
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
						<c:forEach var="utenza" begin="0" items="${elencoOsap.posizioniOsap}">
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
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
				</c:forEach>
				<c:if test="${empty dati.elencoPagamentiOsapTemporanea}">
					<spring:message code="label.nessunDatoPresente" />.
				</c:if>
			</fieldset>
			
			<c:if test="${empty partitaIvaServizio}">
				<fieldset>
					<!-- ELENCO SERVICI CIMITERIALI -->
					<legend><spring:message code="label.titoloServiciCimiteriali" /></legend>
					<c:forEach var="elenco" begin="0" items="${dati.elencoPagamentiTassaCimiteriali}">
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
							<c:forEach var="utenza" begin="0" items="${elenco.posizioniServiziCimiteriali}">
								<tr>
									<td width="25%"><label><spring:message code="label.nomeCimitero" /></label></td>
									<td>${utenza.nomecimitero}</td>
								</tr>
								<tr>
									<td><label><spring:message code="label.posizione" /></label></td>
									<td>${utenza.posizione}</td>
								</tr>
								<tr>
									<td colspan="2">
										<table class="genericTable richTable richTableWhite noMargin">
											<tr><td colspan="2" class="cellaPiena"></td></tr>
											<c:forEach var="defunto" begin="0" items="${utenza.defunti}">
												<tr>
													<td width="25%"><label><spring:message code="label.nomeDefunto" /></label></td>
													<td>${defunto.nomeDefunto}</td>
												</tr>
												<tr>
													<td><label><spring:message code="label.dataNascita" /></label></td>
													<td><fmt:formatDate pattern="dd/MM/yyyy" value="${defunto.dataNascita.time}" /></td>
												</tr>
												<tr>
													<td><label><spring:message code="label.dataMorte" /></label></td>
													<td><fmt:formatDate pattern="dd/MM/yyyy" value="${defunto.dataMorte.time}" /></td>
												</tr>
											</c:forEach>
											<tr><td colspan="2" class="cellaVuota"></td></tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<table class="genericTable richTable richTableWhite noMargin">
											<tr><td colspan="2" class="cellaPiena"></td></tr>
											<c:forEach var="lampada" begin="0" items="${utenza.lampadaVotiva}">
												<tr>
													<td width="25%"><label><spring:message code="label.mesi" /></label></td>
													<td>${lampada.mesi}</td>
												</tr>
												<tr>
													<td><label><spring:message code="label.tariffa" /></label></td>
													<td>${lampada.tariffa}&nbsp;&euro;</td>
												</tr>
												<tr>
													<td><label><spring:message code="label.importoLampada" /></label></td>
													<td>${lampada.importoLampada}</td>
												</tr>
											</c:forEach>
											<tr><td colspan="2" class="cellaVuota"></td></tr>
										</table>
									</td>
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
								<tr><td colspan="2" class="cellaVuota"></td></tr>
							</c:forEach>
						</table>
							
					</c:forEach>
					<c:if test="${empty dati.elencoPagamentiTassaCimiteriali}">
						<spring:message code="label.nessunDatoPresente" />.
					</c:if>
				</fieldset>
			</c:if>
			<fieldset>
				<!-- Elenco Pagamenti Tassa rifiuti -->
				<legend><spring:message code="label.titoloTassaRifiuti" /></legend>
				<c:forEach var="elenco" begin="0" items="${dati.elencoPagamentiTassaRifiuti}">
					<table class="genericTable elencoRisultati">
						<tr>
							<th><spring:message code="label.numeroConto" /></th>
							<th><spring:message code="label.annoRiferimento" /></th>
							<th><spring:message code="label.numeroDocumento" /></th>
							<th><spring:message code="label.importoDocumento" /></th>
							<th><spring:message code="label.dataAggiornamento" /></th>
							<th><spring:message code="label.descrizioneTassa" /></th>
						</tr>
						<tr>
							<td>${elenco.contoCorrente}</td>
							<td>${elenco.annoRiferimento}</td>
							<td>${elenco.numeroDocumento}</td>
							<td>${elenco.importoDocumento}&nbsp;&euro;</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elenco.dataAggiornamento.time}" /></td>
							<td>${elenco.descrizioneTassa}</td>
						</tr>
					</table>
					
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>
						<c:forEach var="utenza" begin="0" items="${elenco.posizioni}">
							<tr>
								<td width="25%"><label><spring:message code="label.identificativoUtenza" /></label></td>
								<td>${utenza.identificativoUtenza}</td>
							</tr>
							<c:if test="${!empty utenza.indirizzoUtenzaImmobile}">
								<tr>
									<td><label><spring:message code="label.indirizzoUtenza" /></label></td>
									<td>${utenza.indirizzoUtenzaImmobile.indirizzo}</td>
								</tr>	
							</c:if>
							<tr>
								<td><label><spring:message code="label.superficie" /></label></td>
								<td>${utenza.superficie}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.numero" /></label></td>
								<td>${utenza.numero}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.sezione" /></label></td>
								<td>${utenza.sezione}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.foglioCatastale" /></label></td>
								<td>${utenza.foglio}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.particella" /></label></td>
								<td>${utenza.particella}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.subalterno" /></label></td>
								<td>${utenza.subalterno}</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.destinazione" /></label></td>
								<td>${utenza.destinazione}</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.tipo" /></label></td>
								<td>${utenza.tipo}</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.aliquota" /></label></td>
								<td>${utenza.aliquota}</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.agevolazione" /></label></td>
								<td>${utenza.agevolazione}</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.importoAgevolazione" /></label></td>
								<td>${utenza.importoAgevolazione}&nbsp;&euro;</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.riduzione" /></label></td>
								<td>${utenza.riduzione}</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.importoRiduzione" /></label></td>
								<td>${utenza.importoRiduzione}&nbsp;&euro;</td>
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
								<td>${utenza.addizionaleComunale}&nbsp;&euro;</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.maggiorazioneStato" /></label></td>
								<td>${utenza.maggiorazioneStato}&nbsp;&euro;</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.addizionaleProvinciale" /></label></td>
								<td>${utenza.addizionaleProvinciale}&nbsp;&euro;</td>
							</tr>	
							<tr>
								<td><label><spring:message code="label.dataInizioOccupazione" /></label></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${utenza.dataInizioOccupazione.time}" /></td>
							</tr>
							
							<c:if test="${!empty utenza.dataFineOccupazione}">
							<tr>
								<td><label><spring:message code="label.dataFineOccupazione" /></label></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${utenza.dataFineOccupazione.time}" /></td>
							</tr>
							</c:if>
							<c:if test="${empty partitaIvaServizio && !empty utenza.occupazioneNucleoFamiliare}">
								<tr>
									<td><label><spring:message code="label.occupazioniNucleoFamiliare" /></label></td>
									<td>
										<ul>
											<c:forEach items="${utenza.occupazioneNucleoFamiliare}" var="occupazione">
												<li>
													<spring:message code="label.numeroComponenti" />:&nbsp;<c:out value="${occupazione.numeroComponenti}"/>
													<spring:message code="label.dataInizioOccupazione" />:&nbsp;<fmt:formatDate pattern="dd/MM/yyyy" value="${occupazione.dataFineOccupazione.time}" />
													<spring:message code="label.dataFineOccupazione" />:&nbsp;<fmt:formatDate pattern="dd/MM/yyyy" value="${occupazione.dataFineOccupazione.time}" />
													<spring:message code="label.importoTariffa" />:&nbsp;<c:out value="${occupazione.importoTariffa}"/>&nbsp;&euro;
												</li>
											</c:forEach>
										</ul>
									</td>
								</tr>
							</c:if>
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
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
				</c:forEach>
				<c:if test="${empty dati.elencoPagamentiTassaRifiuti}">
					<spring:message code="label.nessunDatoPresente" />.
				</c:if>
			</fieldset>
			
			<fieldset>
				<!-- Elenco Pagamenti Affissioni -->
				<legend><spring:message code="label.titoloAffissioni" /></legend>
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
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
				</c:forEach>
				<c:if test="${empty dati.elencoPagamentiTassaAffissioni}">
					<spring:message code="label.nessunDatoPresente" />.
				</c:if>
			</fieldset>
			
			<fieldset>
				<!-- Elenco Pagamenti Pubblicita -->
				<legend><spring:message code="label.titoloPubblicita" /></legend>
				<c:forEach var="elenco" begin="0" items="${dati.elencoPagamentiTassaPubblicita}">
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
						<c:forEach var="utenza" begin="0" items="${elenco.posizioniPubblicita}">
							<tr>
								<td width="25%"><label><spring:message code="label.indirizzoPubblicita" /></label></td>
								<td>${utenza.indirizzoPubblicita}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.descrizionePubblicita" /></label></td>
								<td>${utenza.descrizionePubblicita}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.mq" /></label></td>
								<td>${utenza.mq}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.mesi" /></label></td>
								<td>${utenza.mesi}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoPubblicita" /></label></td>
								<td>${utenza.importoPubblicita}&nbsp;&euro;</td>
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
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
				</c:forEach>
				<c:if test="${empty dati.elencoPagamentiTassaPubblicita}">
					<spring:message code="label.nessunDatoPresente" />.
				</c:if>
			</fieldset>
			
			<fieldset>
				<!-- Situazione Tassa immobili -->
				<legend><spring:message code="label.titoloTassaImmobili" /></legend>
				<c:forEach var="elenco" begin="0" items="${dati.elencoPagamentiTassaImmobili}">
					<table class="genericTable elencoRisultati">
						<tr>
							<th><spring:message code="label.numeroConto" /></th>
							<th><spring:message code="label.annoRiferimento" /></th>
							<th><spring:message code="label.dataAggiornamento" /></th>
							<th><spring:message code="label.descrizioneTassa" /></th>
						</tr>
						<tr>
							<td>${elenco.contoCorrente}</td>
							<td>${elenco.annoRiferimento}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elenco.dataAggiornamento.time}" /></td>
							<td>${elenco.descrizioneTassa}</td>
						</tr>
					</table>
					
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>
						<c:forEach var="utenza" begin="0" items="${elenco.posizioni}">
							<c:if test="${!empty utenza.indirizzoUtenzaImmobile}">
								<tr>
									<td width="25%"><label><spring:message code="label.indirizzoUtenza" /></label></td>
									<td>${utenza.indirizzoUtenzaImmobile.indirizzo}</td>
								</tr>	
							</c:if>
							<tr>
								<td width="25%"><label><spring:message code="label.caratteristicaImmobile" /></label></td>
								<td>${utenza.caratteristicaImmobile}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.numero" /></label></td>
								<td>${utenza.numero}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.sezione" /></label></td>
								<td>${utenza.sezione}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.foglioCatastale" /></label></td>
								<td>${utenza.foglio}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.particella" /></label></td>
								<td>${utenza.particella}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.subalterno" /></label></td>
								<td>${utenza.subalterno}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.valoreImmobile" /></label></td>
								<td>${utenza.valoreImmobile}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.categoriaImmobile" /></label></td>
								<td>${utenza.categoriaImmobile}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.aliquota" /></label></td>
								<td>${utenza.aliquota}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoDovuto" /></label></td>
								<td>${utenza.importoDovuto}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoDetrazioneAbPrincipale" /></label></td>
								<td>${utenza.importoDetrazioneAbPrincipale}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.mesiDetrazione" /></label></td>
								<td>${utenza.mesiDetrazione}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.percentualePossesso" /></label></td>
								<td>${utenza.percentualePossesso}&nbsp;%</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.mesiPossesso" /></label></td>
								<td>${utenza.mesiPossesso}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.posseduto3112" /></label></td>
								<td>
								<c:choose>
									<c:when test="${utenza.posseduto3112 == true}">
										<spring:message code="label.si"/>
									</c:when>
									<c:otherwise>
										<spring:message code="label.no"/>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.riduzione" /></label></td>
								<td>
								<c:choose>
									<c:when test="${utenza.riduzione == true}">
										<spring:message code="label.si"/>
									</c:when>
									<c:otherwise>
										<spring:message code="label.no"/>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.abitazionePrincipale" /></label></td>
								<td>
								<c:choose>
									<c:when test="${utenza.abitazionePrincipale == true}">
										<spring:message code="label.si"/>
									</c:when>
									<c:otherwise>
										<spring:message code="label.no"/>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
					
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>
						<c:forEach var="rata" begin="0" items="${elenco.rate}">
							<tr>
								<td width="25%"><label><spring:message code="label.importoAbitazionePrincipale" /></label></td>
								<td>${rata.importoAbitazionePrincipale}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoAreaFabbricabile" /></label></td>
								<td>${rata.importoAreaFabbricabile}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoTerreniAgricoli" /></label></td>
								<td>${rata.importoTerreniAgricoli}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoAltriFabbricati" /></label></td>
								<td>${rata.importoAltriFabbricati}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.numeroFabbricati" /></label></td>
								<td>${rata.numeroFabbricati}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoDapagare" /></label></td>
								<td>${rata.importoDaPagare}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoPagato" /></label></td>
								<td>${rata.importoPagato}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.dataPagamento" /></label></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${rata.dataPagamento.time}" /></td>
							</tr>
							<tr>
								<td><label><spring:message code="label.tipoRata" /></label></td>
								<td>${rata.tipoRata}</td>
							</tr>
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
				</c:forEach>
				<c:if test="${empty dati.elencoPagamentiTassaImmobili}">
					<spring:message code="label.nessunDatoPresente" />.
				</c:if>
			</fieldset>
			
			<c:if test="${!empty dati.elencoPagamentiTassaImmobili or !empty dati.elencoPagamentiTassaPubblicita 
				or !empty dati.elencoPagamentiTassaAffissioni
				or !empty dati.elencoPagamentiTassaRifiuti or !empty dati.elencoPagamentiTassaCimiteriali
				or !empty dati.elencoPagamentiOsapTemporanea or !empty dati.elencoPagamentiOsapPermananente}">
					
				<div class="container_pulsante">
					<c:if test="${sendReportsEnable}">
						<input type="submit" name="inviaSegnalazione" value="<spring:message code="button.inviaSegnalazione" />"/>
					</c:if>
					<%@ include file="../common/valutaservizio.jsp"%>
				</div>
			</c:if>
		</c:if> 	
	</form:form>
	
	<c:if test="${empty dati}">
		<div class="portlet-msg-alert">
			<spring:message code="error.label.datiNonDisponibili" />
		</div>
	</c:if>
</div>