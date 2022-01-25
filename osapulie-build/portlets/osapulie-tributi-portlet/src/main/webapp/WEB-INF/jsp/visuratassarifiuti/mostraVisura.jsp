<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="visuraTassaRifiutiAction">
	<portlet:param name="action" value="visura" />		
</portlet:actionURL>
<div class="mainDiv">
	<form:form id="visura" action="${visuraTassaRifiutiAction}" method="post" cssClass="printForm"> 
		<div class="marginBottom10">
			<label><spring:message code="label.anno" />:</label>&nbsp;
			<select name="year">
			  <c:forEach var="i" begin="${annoCorrente-10}" end="${annoCorrente}" step="1">
			      <option value="${i}" <c:if test="${i == year}"> selected="selected" </c:if> >${i}</option>
			   </c:forEach>
			</select>
			<input type="submit" name="invia" value='<spring:message code="button.cerca" />' />
		</div>
		<c:if test="${!empty dati}">
			<!-- Elenco Pagamenti Tassa Rifiuti -->
			<c:choose>
				<c:when test="${!empty dati.elencoPagamenti}">
					<fieldset>
						<legend>
							<label><spring:message code="label.descrizioneTassa" />&nbsp;<spring:message code="label.titoloTassaRifiuti" /></label>
						</legend>
						
						<c:forEach var="elenco" begin="0" items="${dati.elencoPagamenti}">
							<table class="genericTable elencoRisultati">
								<tr>
									<c:if test="${!empty elenco.contoCorrente}">
										<th><spring:message code="label.contoCorrente" /></th>
									</c:if>
									<c:if test="${!empty elenco.annoRiferimento}">
										<th><spring:message code="label.annoRiferimento" /></th>
									</c:if>
									<c:if test="${!empty elenco.numeroDocumento}">
										<th><spring:message code="label.numeroDocumento" /></th>
									</c:if>
									<c:if test="${!empty elenco.dataAggiornamento}">
										<th><spring:message code="label.dataAggiornamento" /></th>
									</c:if>
									<th><spring:message code="label.descrizioneTassa" /></th>
								</tr>
								<tr>
									<c:if test="${!empty elenco.contoCorrente}">
										<td>${elenco.contoCorrente}</td>
									</c:if>
									<c:if test="${!empty elenco.annoRiferimento}">
										<td>${elenco.annoRiferimento}</td>
									</c:if>
									<c:if test="${!empty elenco.numeroDocumento}">
										<td>${elenco.numeroDocumento}</td>
									</c:if>
									<c:if test="${!empty elenco.dataAggiornamento}">
										<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elenco.dataAggiornamento.time}" /></td>
									</c:if>
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
									<c:if test="${!empty utenza.indirizzoUtenza}">
										<tr>
											<td><label><spring:message code="label.indirizzoUtenza" /></label></td>
											<td>${utenza.indirizzoUtenza.via.descrizione}</td>
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
										<td>${utenza.importoAgevolazione}</td>
									</tr>	
									<tr>
										<td><label><spring:message code="label.riduzione" /></label></td>
										<td>${utenza.riduzione}</td>
									</tr>	
									<tr>
										<td><label><spring:message code="label.importoRiduzione" /></label></td>
										<td>${utenza.importoRiduzione}</td>
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
										<td>${utenza.addizionaleComunale}</td>
									</tr>	
									<tr>
										<td><label><spring:message code="label.maggiorazioneStato" /></label></td>
										<td>${utenza.maggiorazioneStato}</td>
									</tr>	
									<tr>
										<td><label><spring:message code="label.addizionaleProvinciale" /></label></td>
										<td>${utenza.addizionaleProvinciale}</td>
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
									<c:if test="${!empty utenza.occupazioneNucleoFamiliare}">
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
							
							<c:if test="${!empty elenco.rate}">
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
										<tr><td colspan="2" class="cellaVuota"></td></tr>
									</c:forEach>
								</table>
							</c:if>
						</c:forEach>
					</fieldset>
				</c:when>
				<c:otherwise>
					<div class="portlet-msg-info">
						<spring:message code="label.nessunDato" />
					</div>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${!empty dati.elencoPagamenti}">
				<div class="container_pulsante">
					<c:if test="${sendReportsEnable}">
						<input type="submit" name="inviaSegnalazione" value="<spring:message code="button.inviaSegnalazione" />" />
					</c:if>
					<%@ include file="../common/valutaservizio.jsp"%>
				</div>
			</c:if>
		</c:if> 	
	</form:form>
	
	<c:if test="${empty dati}">
		<div class="portlet-msg-alert">
			<spring:message code="exception.contactAdmin" />
		</div>
	</c:if>
</div>