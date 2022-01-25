<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="visuraPosizioneAnagraficaUrl">
	<portlet:param name="action" value="visura" />		
</portlet:actionURL>

<div class="mainDiv">

	<c:if test="${ empty datiAnagrafici.errore}">
		<form:form id="visura" action="${visuraPosizioneAnagraficaUrl}" method="post" commandName="datiAnagrafici" cssClass="printForm"> <!--  commandName oggetto da cui prende le properties -->
		
			<div class="marginBottom10">
				<label><spring:message code="label.soggetto" />:</label>&nbsp;
				<select name="id">
					<c:forEach var="item" begin="0" items="${datiAnagrafici.componentiNucleoFamiliare}">
				    	<option value="${item.codiceFiscale}" <c:if test="${componenteFamiglia.codiceFiscale == item.codiceFiscale}"> selected="selected" </c:if> >${item.cognome} ${item.nome}</option>
					</c:forEach>
				</select>&nbsp;&nbsp;
				<input type="submit" name="invia" value="<spring:message code="button.change"/>"/>
			</div>
			
			<fieldset>
				<legend><spring:message code="label.descrizione" /></legend>
			
				<table class="genericTable richTable">
					<tr><td colspan="2" class="cellaPiena"></td></tr>
					<tr>
						<td width="25%"><label><spring:message code="label.cf" /></label></td>
						<td>${componenteFamiglia.codiceFiscale}</td>
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
						<td><label><spring:message code="label.sesso" /></label></td>
						<td>${componenteFamiglia.sesso}</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.indRes" /></label></td>
						<td>
							${datiAnagrafici.toponimoIndirizzo} ${datiAnagrafici.descrizioneVia} ${datiAnagrafici.numeroCivico}
							<c:if test="${!empty datiAnagrafici.esponente}">
								,&nbsp;${datiAnagrafici.esponente}  
							</c:if>
							<c:if test="${!empty datiAnagrafici.piano}">
								,&nbsp;p. ${datiAnagrafici.piano}  
							</c:if>
							<c:if test="${!empty datiAnagrafici.scala}">
								,&nbsp;s. ${datiAnagrafici.scala}
							</c:if>
							<c:if test="${!empty datiAnagrafici.interno}">
								,&nbsp;int. ${datiAnagrafici.interno}
							</c:if>
						</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.statCiv" /></label></td>
						<td>${componenteFamiglia.statoCivile}</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.coniuge" /></label></td>
						<td>${componenteFamiglia.cognomeNomeConiuge}</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.parentela" /></label></td>
						<td>${componenteFamiglia.relazioneParentela}</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.cittadinanza" /></label></td>
						<td>
							<c:if test="${componenteFamiglia.cittadinanzaItaliana == true}"><spring:message code="label.si" /></c:if>
							<c:if test="${componenteFamiglia.cittadinanzaItaliana == false}"><spring:message code="label.no" /></c:if>
						</td>
					</tr>
					<%-- <tr>
						<td><label><spring:message code="label.idFam" /></label></td>
						<td>${componenteFamiglia.identificativoFamiglia}</td>
					</tr> --%>
					<tr>
						<td><label><spring:message code="label.comuneNascita" /></label></td>
						<c:if test="${componenteFamiglia.cittadinanzaItaliana == true}">
							<td>${comuneNascita}</td>
						</c:if>
						<c:if test="${componenteFamiglia.cittadinanzaItaliana == false}">
							<td>${componenteFamiglia.descrizioneComuneEsteroNascita}</td>
						</c:if>
					</tr>
					<c:if test="${componenteFamiglia.cittadinanzaItaliana == true}">
						<tr>
							<td><label><spring:message code="label.provinciaNascita" /></label></td>
							<td>${provinciaNascita}</td>
						</tr>
					</c:if>
					<tr>
						<td><label><spring:message code="label.prof" /></label></td>
						<td>${componenteFamiglia.professione}</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.studio" /></label></td>
						<td>${componenteFamiglia.titoloStudio}</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.numCI" /></label></td>
						<td>${componenteFamiglia.numeroCartaIdentita}</td>
					</tr>
					<tr>
						<td><label><spring:message code="label.dataCI" /></label></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataCartaIdentita.time}" /></td>
					</tr>
					<tr>
						<td><label><spring:message code="label.valCI" /></label></td>
						<td>
							<c:if test="${componenteFamiglia.validaCartaIdentita != null}">
								<c:if test="${componenteFamiglia.validaCartaIdentita == true}"><spring:message code="label.si" /></c:if>
								<c:if test="${componenteFamiglia.validaCartaIdentita == false}"><spring:message code="label.no" /></c:if>
							</c:if>
						</td>
					</tr>
					<!-- Atto nascita -->
					<tr>
						<td><label><spring:message code="label.attoN" /></label></td>
						<td>
							<c:if test="${!empty componenteFamiglia.numeroAttoNascita}">n. ${componenteFamiglia.numeroAttoNascita}</c:if>
							<c:if test="${!empty componenteFamiglia.parteNascita}">,&nbsp;p. ${componenteFamiglia.parteNascita}</c:if>
							<c:if test="${!empty componenteFamiglia.serieNascita}">,&nbsp;s. ${componenteFamiglia.serieNascita}</c:if>
							<c:if test="${!empty componenteFamiglia.annoAttoNascita}">,&nbsp;<spring:message code="label.anno"/>:&nbsp;${componenteFamiglia.annoAttoNascita}</c:if>
							<c:if test="${!empty componenteFamiglia.ufficioNascita}">,&nbsp;<spring:message code="label.ufficio"/>:&nbsp;${componenteFamiglia.ufficioNascita}</c:if>
							<c:if test="${!empty comuneNascita}">,&nbsp;<spring:message code="label.comune"/>:&nbsp;${comuneNascita}</c:if>
							<c:if test="${!empty provinciaNascita}">,&nbsp;<spring:message code="label.prov"/>:&nbsp;${provinciaNascita}</c:if>
						</td>
					</tr>
					<!-- Atto nascita trascritto-->
					<c:if test="${!empty componenteFamiglia.numeroAttoNascitaTrascritto && componenteFamiglia.numeroAttoNascitaTrascritto != 0}">
						<tr>
							<td><label><spring:message code="label.attoNTrascritto" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoNascitaTrascritto}">n. ${componenteFamiglia.numeroAttoNascitaTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.parteNascitaTrascritto}">,&nbsp;p. ${componenteFamiglia.parteNascitaTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.serieNascitaTrascritto}">,&nbsp;s. ${componenteFamiglia.serieNascitaTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.annoNascitaTrascritto}">,&nbsp;<spring:message code="label.anno"/>:&nbsp;${componenteFamiglia.annoNascitaTrascritto}</c:if>
								<%-- <c:if test="${!empty componenteFamiglia.ufficioNascita}">
									<spring:message code="label.ufficio"/> ${componenteFamiglia.ufficioNascita}
								</c:if> --%>
								<c:if test="${!empty comuneAttoNascitaTrascritto}">,&nbsp;<spring:message code="label.comune"/>:&nbsp;${comuneAttoNascitaTrascritto}</c:if>
								<c:if test="${!empty provinciaAttoNascitaTrascritto}">,&nbsp;<spring:message code="label.prov"/>:&nbsp;${provinciaAttoNascitaTrascritto}</c:if>
							</td>
						</tr>
					</c:if>
					<!-- Atto matrimonio -->
					<c:if test="${!empty componenteFamiglia.numeroAttoMatrimonio && componenteFamiglia.numeroAttoMatrimonio != 0}">
						<tr>
							<td><label><spring:message code="label.attoMatrimonio" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoMatrimonio}">n. ${componenteFamiglia.numeroAttoMatrimonio}</c:if>
								<c:if test="${!empty componenteFamiglia.parteMatrimonio}">,&nbsp;p. ${componenteFamiglia.parteMatrimonio}</c:if>
								<c:if test="${!empty componenteFamiglia.serieMatrimonio}">,&nbsp;s. ${componenteFamiglia.serieMatrimonio}</c:if>
								<c:if test="${!empty componenteFamiglia.annoMatrimonio}">,&nbsp;<spring:message code="label.anno"/>:&nbsp;${componenteFamiglia.annoMatrimonio}</c:if>
								<c:if test="${!empty componenteFamiglia.ufficioMatrimonio}">,&nbsp;<spring:message code="label.ufficio"/>:&nbsp;${componenteFamiglia.ufficioMatrimonio}</c:if>
								<c:if test="${!empty componenteFamiglia.luogoMatrimonio}">,&nbsp;<spring:message code="label.luogo"/>:&nbsp;${componenteFamiglia.luogoMatrimonio}</c:if>
								<c:if test="${!empty componenteFamiglia.dataMatrimonio}">,&nbsp;<spring:message code="label.dataMatrimonio"/>:&nbsp;<fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataMatrimonio.time}" /></c:if>
								<c:if test="${!empty comuneMatrimonio}">,&nbsp;<spring:message code="label.comune"/>:&nbsp; ${comuneMatrimonio}</c:if>
							</td>
						</tr>
					</c:if>
					<!-- Atto matrimonio trascritto -->
					<c:if test="${!empty componenteFamiglia.numeroAttoMatrimonioTrascritto && componenteFamiglia.numeroAttoMatrimonioTrascritto != 0}">
						<tr>
							<td><label><spring:message code="label.attoMatrimonioTrascritto" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoMatrimonioTrascritto}">n. ${componenteFamiglia.numeroAttoMatrimonioTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.parteMatrimonioTrascritto}">,&nbsp;p. ${componenteFamiglia.parteMatrimonioTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.serieMatrimonioTrascritto}">,&nbsp;s. ${componenteFamiglia.serieMatrimonioTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.annoMatrimonioTrascritto}">,&nbsp;<spring:message code="label.anno"/>:&nbsp;${componenteFamiglia.annoMatrimonioTrascritto}</c:if>
								<c:if test="${!empty comuneMatrimonioTrascritto}">,&nbsp;<spring:message code="label.comune"/>:&nbsp;${comuneMatrimonioTrascritto}</c:if>
							</td>
						</tr>
					</c:if>
					<!-- Atto divorzio -->
					<c:if test="${!empty componenteFamiglia.numeroAttoDivorzio && componenteFamiglia.numeroAttoDivorzio != 0}">
						<tr>
							<td><label><spring:message code="label.attoDivorzio" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoDivorzio}">n. ${componenteFamiglia.numeroAttoDivorzio}</c:if>
								<c:if test="${!empty componenteFamiglia.dataAttoDivorzio}">,&nbsp;<spring:message code="label.data"/> <fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataAttoDivorzio.time}" /></c:if>
								<c:if test="${!empty componenteFamiglia.tipoDivorzio}">,&nbsp;tipo: ${componenteFamiglia.tipoDivorzio}</c:if>
								<c:if test="${!empty componenteFamiglia.numeroSentenzaDivorzio}">,&nbsp;<spring:message code="label.numeroSentenzaDivorzio"/>:&nbsp;${componenteFamiglia.numeroSentenzaDivorzio}</c:if>
								<c:if test="${!empty componenteFamiglia.dataSentenzaDivorzio}">,&nbsp;<spring:message code="label.dataSentenzaDivorzio"/>:&nbsp;<fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataSentenzaDivorzio.time}" /></c:if>
								<c:if test="${!empty componenteFamiglia.dataDecorrenzaDivorzio}">,&nbsp;<spring:message code="label.dataDecorrenzaDivorzio"/>:&nbsp;<fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataDecorrenzaDivorzio.time}" /></c:if>
								<c:if test="${!empty componenteFamiglia.parteDivorzio}">,&nbsp;p. ${componenteFamiglia.parteDivorzio}</c:if>
								<c:if test="${!empty componenteFamiglia.serieDivorzio}">,&nbsp;s. ${componenteFamiglia.serieDivorzio}</c:if>
								<c:if test="${!empty componenteFamiglia.volumeDivorzio}">,&nbsp;vol. ${componenteFamiglia.volumeDivorzio}</c:if>
								<c:if test="${!empty componenteFamiglia.ufficioDivorzio}">,&nbsp;<spring:message code="label.ufficio"/>:&nbsp;${componenteFamiglia.ufficioDivorzio}</c:if>
								<c:if test="${!empty comuneTribunaleDivorzio}">,&nbsp;<spring:message code="label.comune"/>:&nbsp;${comuneTribunaleDivorzio}</c:if>
							</td>
						</tr>
					</c:if>
					<!-- Atto divorzio trascritto-->
					<c:if test="${!empty componenteFamiglia.numeroAttoDivorzioTrascritto && componenteFamiglia.numeroAttoDivorzioTrascritto != 0}">
						<tr>
							<td><label><spring:message code="label.attoDivorzioTrascritto" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoDivorzioTrascritto}">n. ${componenteFamiglia.numeroAttoDivorzioTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.dataAttoDivorzioTrascritto}">,&nbsp;<spring:message code="label.data"/>:&nbsp;<fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataAttoDivorzioTrascritto.time}" /></c:if>
								<c:if test="${!empty componenteFamiglia.parteDivorzioTrascritto}">,&nbsp;p. ${componenteFamiglia.parteDivorzioTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.serieDivorzioTrascritto}">,&nbsp;s. ${componenteFamiglia.serieDivorzioTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.volumeDivorzioTrascritto}">,&nbsp;vol. ${componenteFamiglia.volumeDivorzioTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.ufficioDivorzioTrascritto}">,&nbsp;<spring:message code="label.ufficio"/>:&nbsp;${componenteFamiglia.ufficioDivorzioTrascritto}</c:if>
								<c:if test="${!empty comuneTribunaleDivorzioTrascritto}">,&nbsp;<spring:message code="label.comune"/>:&nbsp;${comuneTribunaleDivorzioTrascritto}</c:if>
							</td>
						</tr>
					</c:if>
					<!-- Atto vedovanza-->
					<c:if test="${!empty componenteFamiglia.numeroAttoVedovanza && componenteFamiglia.numeroAttoVedovanza != 0}">
						<tr>
							<td><label><spring:message code="label.attoVedovanza" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoVedovanza}">n. ${componenteFamiglia.numeroAttoVedovanza}</c:if>
								<c:if test="${!empty componenteFamiglia.dataAttoVedovanza}">,&nbsp;<spring:message code="label.data"/> <fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataAttoVedovanza.time}" /></c:if>
								<c:if test="${!empty componenteFamiglia.parteVedovanza}">,&nbsp;p. ${componenteFamiglia.parteVedovanza}</c:if>
								<c:if test="${!empty componenteFamiglia.serieVedovanza}">,&nbsp;s. ${componenteFamiglia.serieVedovanza}</c:if>
								<c:if test="${!empty componenteFamiglia.volumeVedovanza}">,&nbsp;vol. ${componenteFamiglia.volumeVedovanza}</c:if>
								<c:if test="${!empty componenteFamiglia.ufficioVedovanza}">,&nbsp;<spring:message code="label.ufficio"/>:&nbsp;${componenteFamiglia.ufficioVedovanza}</c:if>
								<%-- <c:if test="${!empty comuneTribunaleDivorzioTrascritto}">
									<spring:message code="label.comune"/> ${comuneTribunaleDivorzioTrascritto}
								</c:if> --%>
							</td>
						</tr>
					</c:if>
					<!-- Atto morte-->
					<c:if test="${!empty componenteFamiglia.numeroAttoMorte && componenteFamiglia.numeroAttoMorte != 0}">
						<tr>
							<td><label><spring:message code="label.attoMorte" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoMorte}">n. ${componenteFamiglia.numeroAttoMorte}</c:if>
								<c:if test="${!empty componenteFamiglia.dataMorte}">,&nbsp;<spring:message code="label.data"/> <fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataMorte.time}" /></c:if>
								<c:if test="${!empty componenteFamiglia.parteMorte}">,&nbsp;p. ${componenteFamiglia.parteMorte}</c:if>
								<c:if test="${!empty componenteFamiglia.serieMorte}">,&nbsp;s. ${componenteFamiglia.serieMorte}</c:if>
								<c:if test="${!empty componenteFamiglia.annoMorte}">,&nbsp;<spring:message code="label.annoMorte"/>:&nbsp;${componenteFamiglia.annoMorte}</c:if>
								<c:if test="${!empty componenteFamiglia.ufficioMorte}">,&nbsp;<spring:message code="label.ufficio"/>:&nbsp;${componenteFamiglia.ufficioMorte}</c:if>
								<c:if test="${!empty comuneMorte}">,&nbsp;<spring:message code="label.comune"/>:&nbsp;${comuneMorte}</c:if> 
							</td>
						</tr>
					</c:if>
					<!-- Atto morte coniuge trascritto-->
					<c:if test="${!empty componenteFamiglia.numeroAttoMorteConiugeTrascritto && componenteFamiglia.numeroAttoMorteConiugeTrascritto != 0}">
						<tr>
							<td><label><spring:message code="label.attoMorteConiugeTrascritto" /></label></td>
							<td>
								<c:if test="${!empty componenteFamiglia.numeroAttoMorteConiugeTrascritto}">n. ${componenteFamiglia.numeroAttoMorteConiugeTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.parteMorteConiugeTrascritto}">,&nbsp;p. ${componenteFamiglia.parteMorteConiugeTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.serieMorteConiugeTrascritto}">,&nbsp;s. ${componenteFamiglia.serieMorteConiugeTrascritto}</c:if>
								<c:if test="${!empty componenteFamiglia.annoMorteConiugeTrascritto}">,&nbsp;<spring:message code="label.anno"/>:&nbsp;${componenteFamiglia.annoMorteConiugeTrascritto}</c:if>
								<c:if test="${!empty comuneMorteConiugeTrascritto}">,&nbsp;<spring:message code="label.comune"/>:&nbsp;${comuneMorteConiugeTrascritto}</c:if> 
							</td>
						</tr>
					</c:if>
					<!-- Pensioni -->
					<c:if test="${!empty componenteFamiglia.pensioniList && !empty componenteFamiglia.pensioniList[0]}">
						<tr>
							<td><label><spring:message code="label.pensioni" /></label></td>
							<td>
								<ul>
									<c:forEach items="${componenteFamiglia.pensioniList}" var="pensione" varStatus="status">
										<c:if test="${!empty pensione}">
											<li>
												<c:out value="${status.count}"/>)&nbsp;<span><spring:message code="label.numeroPensione" />: </span>&nbsp;<c:out value="${pensione.numeroPensione}"/>
												<span><spring:message code="label.codicePensione" />: </span>&nbsp;<c:out value="${pensione.codicePensione}"/>
											</li>	
										</c:if>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</c:if>
					<tr><td colspan="2" class="cellaVuota"></td></tr>
				</table>	
			</fieldset>	
		
			<div class="container_pulsante">
				<c:if test="${sendReportsEnable && !empty datiAnagrafici && !empty componenteFamiglia}">
					<input type="submit" name="inviaSegnalazione" value="<spring:message code="button.inviaSegnalazione" />" />
				</c:if>
			<%@ include file="../common/valutaservizio.jsp"%>
			</div>
		</form:form>
	</c:if> 
	
	<c:if test="${! empty datiAnagrafici.errore }">
		<div class="portlet-msg-error"><spring:message code="errore.pdds.codice${datiAnagrafici.errore.codice }" /></div>
	</c:if>
</div>