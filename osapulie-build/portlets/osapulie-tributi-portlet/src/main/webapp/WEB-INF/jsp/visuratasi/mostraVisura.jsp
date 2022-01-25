<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="visuraTasiAction">
		<portlet:param name="action" value="visura" />		
</portlet:actionURL>

<div class="mainDiv">

	<form:form id="visura" action="${visuraTasiAction}" method="post" cssClass="printForm"> 
		<div class="marginBottom10">
			<label><spring:message code="label.annoInizio" />:</label>&nbsp;
			<%--<input type="text" id="startYear" name="startYear" />&nbsp;&nbsp; --%>
			<select name="startYear">
			  <c:forEach var="i" begin="${annoCorrente-10}" end="${annoCorrente}" step="1">
			      <option value="${i}" <c:if test="${i == yearStart}"> selected="selected" </c:if> >${i}</option>
			   </c:forEach>
			</select>
			
			<label><spring:message code="label.annoFine" />:</label>&nbsp;
			<%--<input type="text" id="endYear" name="endYear" />&nbsp;&nbsp; --%>
			<select name="endYear">
			  <c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
			      <option value="${i}" <c:if test="${i == yearEnd}"> selected="selected" </c:if> >${i}</option>
			   </c:forEach>
			</select>
			<input type="submit" name="invia" value='<spring:message code="button.cerca" />'/>
		</div>
		
		<c:if test="${! empty dati}">
			<fieldset>
				<legend>
					<label><spring:message code="label.descrizione" />&nbsp;<spring:message code="label.titoloTasi" /></label>
				</legend>
		
				<c:forEach var="elenco" begin="0" items="${dati.elencoPagamentiTasi}">
					<table class="genericTable elencoRisultati">
						<tr>
							<th><spring:message code="label.numeroConto" /></th>
							<th><spring:message code="label.annoRiferimento" /></th>
							<th><spring:message code="label.dataAggiornamento" /></th>
						</tr>
						<tr>
							<td>${elenco.contoCorrente}</td>
							<td>${elenco.annoRiferimento}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elenco.dataAggiornamento.time}" /></td>
						</tr>
					</table>				
				
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>	
						<c:forEach var="utenza" begin="0" items="${elenco.posizioni}">
							<tr>
								<td width="25%"><label><spring:message code="label.indirizzoUtenzaImmobile" /></label></td>
								<td>${utenza.indirizzoUtenza.via.descrizione}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.caratteristicaImmobile" /></label></td>
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
								<td>${utenza.valoreImmobile}</td>
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
										<c:when test="${utenza.posseduto3112}">
											<spring:message code="label.si" />
										</c:when>
										<c:otherwise>
											<spring:message code="label.no" />
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.riduzione" /></label></td>
								<td>
									<c:choose>
										<c:when test="${utenza.riduzione}">
											<spring:message code="label.si" />
										</c:when>
										<c:otherwise>
											<spring:message code="label.no" />
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.abitazionePrincipale" /></label></td>
								<td>
									<c:choose>
										<c:when test="${utenza.abitazionePrincipale}">
											<spring:message code="label.si" />
										</c:when>
										<c:otherwise>
											<spring:message code="label.no" />
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
			</fieldset>
			
			<c:if test="${!empty dati.elencoPagamentiTasi}">
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
