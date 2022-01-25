<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="variazioneTasiReport" escapeXml="false">
</portlet:resourceURL>

<script type="text/javascript">
$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
</script>


<c:set var="idForm" value="produrreDichiarazione" />
<c:choose>
	<c:when test="${!empty datiVariazioneTasi}">
		<form:form id="${idForm}" action="${dichiarazioneUrlGenera}"
			method="post" commandName="datiVariazioneTasi">
			<c:if test="${empty download}">
				<fieldset>
					<legend>
						<spring:message code="label.contribuente" />
					</legend>
					<table class="genericTable">
						<tr>
							<td width="180"><label><spring:message
										code="label.cognome" /> *:</label></td>
							<td width="180"><form:input path="cognome" />
								<div>
									<form:errors path="cognome" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.nome" /> *:</label></td>
							<td colspan="3"><form:input path="nome" />
								<div>
									<form:errors path="nome" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.cf" /> *:</label></td>
							<td width="180"><form:input path="codiceFiscale" />
								<div>
									<form:errors path="codiceFiscale" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.sesso" /> *:</label></td>
							<td colspan="3">
								<form:radiobutton path="sesso" value="M" />M
								<form:radiobutton path="sesso" value="F" />F
								<div>
									<form:errors path="sesso" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180">
							  <label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy) *:</label>
							</td>
							<td width="180">
								 <form:input path="dataNascita" id="data_nascita_input" size="10" cssClass="data_input"/>
								 <div><form:errors path="dataNascita" cssStyle="color:red" /> </div>
							</td>
							<td width="180"><label><spring:message
										code="label.comN" />*:</label></td>
							<td><form:input path="comuneNascita" />
								<div>
									<form:errors path="comuneNascita" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.provN" /> *:</label></td>
							<td><form:input path="provinciaNascita" size="3" />
								<div>
									<form:errors path="provinciaNascita" cssStyle="color:red" />
								</div></td>
						</tr>

						<tr>
							<td width="180"><label><spring:message
										code="label.comuneIscrizione" /> *:</label></td>
							<td width="180"><form:input path="comuneResidenza" />
								<div>
									<form:errors path="comuneResidenza" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.provRes" /> *:</label></td>
							<td><form:input path="provResidenza" size="3" />
								<div>
									<form:errors path="provResidenza" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.cap" />*:</label></td>
							<td><form:input path="capResidenza" size="8" />
								<div>
									<form:errors path="capResidenza" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.indRes" /> *:</label></td>
							<td width="180" colspan="5"><form:input path="indirizzoResidenza" />
								<div>
									<form:errors path="indirizzoResidenza" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.scala" /> :</label></td>
							<td width="180"><form:input path="scala" size="7" /></td>
							<td width="180"><label><spring:message
										code="label.piano" /> :</label></td>
							<td><form:input path="piano" size="7" /></td>
							<td><label><spring:message code="label.interno" />:</label>
							</td>
							<td><form:input path="interno" size="7" /></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.telefono" /> :</label></td>
							<td width="180"><form:input path="telefono" /></td>
							<td width="180"><label><spring:message
										code="label.email" /> :</label></td>
							<td colspan="3"><form:input path="email" />
								<div><form:errors path="email" cssStyle="color:red"/></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						<spring:message code="label.dichiarante" />
					</legend>
					<table class="genericTable">
						<tr>
							<td width="180"><label><spring:message
										code="label.cognome" /> *:</label></td>
							<td width="180"><form:input path="cognomeDic" />
								<div>
									<form:errors path="cognomeDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.nome" /> *:</label></td>
							<td colspan="3"><form:input path="nomeDic" />
								<div>
									<form:errors path="nomeDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.cf" /> *:</label></td>
							<td width="180"><form:input path="codiceFiscaleDic" />
								<div>
									<form:errors path="codiceFiscaleDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.sesso" /> *:</label></td>
							<td colspan="3">
								<form:radiobutton path="sessoDic" value="M" />M
								<form:radiobutton path="sessoDic" value="F" />F
								<div>
									<form:errors path="sessoDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.naturaCarica" /> *:</label></td>
							<td colspan="5"><form:input path="naturaCarica" size="40" />
								<div>
									<form:errors path="naturaCarica" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.dataN" />&nbsp;(dd/mm/yyyy) *:</label></td>
							<td width="180">
						  		<form:input path="dataNascitaDic" id="data_nascitadic_input" size="10" cssClass="data_input"/>
								<div>
									<form:errors path="dataNascitaDic" cssStyle="color:red" />
								</div>
							</td>
							<td width="180"><label><spring:message
										code="label.comN" />*:</label></td>
							<td><form:input path="comuneNascitaDic" />
								<div>
									<form:errors path="comuneNascitaDic" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.provN" /> *:</label>
							</td>
							<td><form:input path="provinciaNascitaDic" size="3" />
								<div>
									<form:errors path="provinciaNascitaDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.comuneIscrizione" /> *:</label></td>
							<td width="180"><form:input path="comuneResidenzaDic" />
								<div>
									<form:errors path="comuneResidenzaDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.provRes" /> *:</label></td>
							<td><form:input path="provResidenzaDic" size="3" />
								<div>
									<form:errors path="provResidenzaDic" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.cap" /> *:</label></td>
							<td><form:input path="capResidenzaDic" size="8" />
								<div>
									<form:errors path="capResidenzaDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.indRes" /> *:</label></td>
							<td width="180"><form:input path="indirizzoResidenzaDic" />
								<div>
									<form:errors path="indirizzoResidenzaDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.civicoRes" /> *:</label></td>
							<td colspan="3"><form:input path="civicoDic" size="5" />
								<div>
									<form:errors path="civicoDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.telefono" />:</label></td>
							<td width="180"><form:input path="telefonoDic" /></td>
							<td width="180"><label><spring:message
										code="label.email" />:</label></td>
							<td colspan="3"><form:input path="emailDic" />
								<div><form:errors path="emailDic" cssStyle="color:red"/></div>
							</td>
						</tr>
					</table>
				</fieldset>
					
				<fieldset>
					<legend>
						<spring:message code="label.immobili" />
					</legend>
					<c:choose>
						<c:when test="${!empty datiVariazioneTasi.posizioni}">
							<c:if test="${!empty situazione}">
								<c:set var="annoRiferimentoString">${situazione.annoRiferimento}</c:set>
								<c:if test="${annoCorrente != annoRiferimentoString}">
									<span class="red"><spring:message code="label.dati.noAnnoCorrente.noStar" arguments="${annoRiferimentoString}"/>.</span><br>
								</c:if>
							</c:if>
							<c:set var="j" value="0" scope="page" />
							<c:forEach var="item" begin="0" items="${datiVariazioneTasi.posizioni}">
								<b><spring:message	code="label.numOrdine" />: ${j + 1}</b>
								<br>
							    	<table class="genericTable">
							    		
										<tr>
											<td>
												<label><spring:message code="label.ubicazione" /> *:</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].indirizzoUtenza.via.descrizione" />
												<div><form:errors path="posizioni[${j}].indirizzoUtenza.via.descrizione" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.decorrenza" />(dd/mm/yyyy) :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].dataInizioOccupazione" id="data_iniziooccupazione_input_${j}" size="10" cssClass="data_input"/>
												<div><form:errors path="posizioni[${j}].dataInizioOccupazione" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.valore" />&nbsp; *:</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].valoreImmobile" />
												<div><form:errors path="posizioni[${j}].valoreImmobile" cssStyle="color:red"/></div>	 
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.superficieOccupata" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].superficieOccupata" />
												<div><form:errors path="posizioni[${j}].superficieOccupata" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.superficiEscluse" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].superficiEscluse" />
												<div><form:errors path="posizioni[${j}].superficiEscluse" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.areeScoperteOperative" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].areeScoperteOperative" />
												<div><form:errors path="posizioni[${j}].areeScoperteOperative" cssStyle="color:red"/></div>	 
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.cognomeProprietario" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].cognomeProprietario" />
												<div><form:errors path="posizioni[${j}].cognomeProprietario" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.nomeProprietario" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].nomeProprietario"/>
												<div><form:errors path="posizioni[${j}].nomeProprietario" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.comuneProprietario" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].comuneResidenzaProprietario"/>
												<div><form:errors path="posizioni[${j}].comuneResidenzaProprietario" cssStyle="color:red"/></div>	 
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.provinciaProprietario" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].provinciaResidenzaProprietario" />
												<div><form:errors path="posizioni[${j}].provinciaResidenzaProprietario" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.indirizzoProprietario" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].indirizzoResidenzaProprietario" />
												<div><form:errors path="posizioni[${j}].indirizzoResidenzaProprietario" cssStyle="color:red"/></div>	 
											</td>
											<td>
												<label><spring:message code="label.civicoRes" /> :</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].civicoProprietario" />
												<div><form:errors path="posizioni[${j}].civicoProprietario" cssStyle="color:red"/></div>	 
											</td>
										</tr>
									</table>
		
									<table class="genericTable">
										<tr>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.sezione" />&nbsp;:</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.foglioCatastale" />&nbsp;:</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.particella" />&nbsp;:</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.sub" />&nbsp;:</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.categoriaImmobile" />&nbsp;:</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.classe" />&nbsp;:</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.nProt" />&nbsp;:</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.anno" />&nbsp;:</label>
											</td>
										</tr>
										<tr>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].sezione" size="3"/>  
												<div><form:errors path="posizioni[${j}].sezione" cssStyle="color:red"/></div> 
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].foglio" size="3"/>
												<div><form:errors path="posizioni[${j}].foglio" cssStyle="color:red"/></div>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].particella" size="3"/>
												<div><form:errors path="posizioni[${j}].particella" cssStyle="color:red"/></div> 
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].subalterno" size="3" />
												<div><form:errors path="posizioni[${j}].subalterno" cssStyle="color:red"/></div>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].categoriaImmobile" />
												<div><form:errors path="posizioni[${j}].categoriaImmobile" cssStyle="color:red"/></div>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].classe" size="3"/>
												<div><form:errors path="posizioni[${j}].classe" cssStyle="color:red"/></div>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].numProt" size="8"/>
												<div><form:errors path="posizioni[${j}].numProt" cssStyle="color:red"/></div>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<form:input path="posizioni[${j}].anno" size="4"/>
												<div><form:errors path="posizioni[${j}].anno" cssStyle="color:red"/></div>
											</td>
										</tr>
									</table>
									
									<table class="genericTable">
										<tr>
											<td>
												<label><spring:message code="label.titoloProprietà" />&nbsp;:</label>
											</td>
											<td>
												<form:radiobutton path="posizioni[${j}].titoloProprietà" 
													value="PROPRIETA" />Di Proprietà&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
											<td>		
												<form:radiobutton path="posizioni[${j}].titoloProprietà" 
													value="LOCAZIONE" />Di Locazione&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
											<td>
												<form:radiobutton path="posizioni[${j}].titoloProprietà" 
													value="USUFRUTTO" />Di Usufrutto&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
											<td>
												<form:radiobutton path="posizioni[${j}].titoloProprietà" 
													value="ALTRO" />Altro (specificare 
														<form:input path="posizioni[${j}].altroTitoloProprietà"/>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<div><form:errors path="posizioni[${j}].titoloProprietà" cssStyle="color:red"/></div>
												<div><form:errors path="posizioni[${j}].altroTitoloProprietà" cssStyle="color:red"/></div>
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.tipologiaUtenza" />:</label>
											</td>
											<td>
												<form:radiobutton path="posizioni[${j}].tipologiaUtenza" 
													value="DOMESTICA" />Domestica&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>		
											<td colspan="3">
												<form:radiobutton path="posizioni[${j}].tipologiaUtenza" 
													value="NON DOMESTICA" />Non Domestica&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<div><form:errors path="posizioni[${j}].tipologiaUtenza" cssStyle="color:red"/></div>
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.destinazioneProprietà" />:</label>
											</td>
											<td>
												<form:radiobutton path="posizioni[${j}].destinazioneProprietà" 
													value="ABITATIVO" />Uso Abitativo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
											<td>		
												<form:radiobutton path="posizioni[${j}].destinazioneProprietà" 
													value="A DISPOSIZIONE" />A Disposizione&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
											<td>
												<form:radiobutton path="posizioni[${j}].destinazioneProprietà" 
													value="BOX" />Box&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
											<td>	<form:radiobutton path="posizioni[${j}].destinazioneProprietà" 
													value="ALTRO" />Altro (specificare 
														<form:input path="posizioni[${j}].altroDestinazioneProprietà"/>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<div><form:errors path="posizioni[${j}].destinazioneProprietà" cssStyle="color:red"/></div>
													<div><form:errors path="posizioni[${j}].altroDestinazioneProprietà" cssStyle="color:red"/></div>
											</td>
										</tr>
									</table>
									<br />
									<br />
									<br />
									<br />
									<c:set var="j" value="${j+1}" scope="page" />	
								</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="portlet-msg-alert">
								<spring:message code="error.label.datiNonDisponibili" />
							</div>
						</c:otherwise>
					</c:choose>
					<div><form:errors path="posizioni" cssStyle="color:red"/></div> 
				</fieldset>
					
				<fieldset>
					<legend>
						<spring:message code="label.componentiFamiglia" />
					</legend>
					<input type="hidden" name="rimuoviFamiliareIndex" id="rimuoviFamiliareIndex" value="" />
					<c:set var="i" value="0" scope="page" />
					<c:forEach var="item" begin="0" items="${datiVariazioneTasi.familiari}">
						<b><spring:message	code="label.numOrdine" />: ${i + 1}</b>
						<br>
				    	<table class="genericTable">
								<tr>
									<td><label><spring:message
												code="label.cognome" /> *:</label></td>
									<td><form:input path="familiari[${i}].cognome" />
										<div>
											<form:errors path="familiari[${i}].cognome" cssStyle="color:red" />
										</div></td>
									<td><label><spring:message
												code="label.nome" /> *:</label></td>
									<td colspan="3"><form:input path="familiari[${i}].nome" />
										<div>
											<form:errors path="familiari[${i}].nome" cssStyle="color:red" />
										</div></td>
								</tr>
								<tr>
									<td><label><spring:message
												code="label.comN" /> :</label></td>
									<td><form:input path="familiari[${i}].comuneNascita" />
										<div>
											<form:errors path="familiari[${i}].comuneNascita" cssStyle="color:red" />
										</div>
									</td>
									<td><label><spring:message	code="label.sesso" /> *:</label></td>
									<td>
											<form:radiobutton path="familiari[${i}].sesso" value="M" />M
											<form:radiobutton path="familiari[${i}].sesso" value="F" />F
										<div>
											<form:errors path="familiari[${i}].sesso" cssStyle="color:red" />
										</div></td>
								</tr>
								<tr>
									<td><label><spring:message
												code="label.dataN" />&nbsp;(dd/mm/yyyy) *:</label></td>
									<td>
									 	<form:input path="familiari[${i}].dataNascitaString" id="data_nascitafamiliari_input_${i}" size="10" cssClass="data_input"/>
										<div>
											<form:errors path="familiari[${i}].dataNascitaString" cssStyle="color:red" />
										</div>
									</td>
									<td width="180"><label><spring:message
												code="label.relazioneParentela" /> *:</label></td>
									<td><form:input path="familiari[${i}].parentela" />
										<div>
											<form:errors path="familiari[${i}].parentela" cssStyle="color:red" />
										</div></td>
								</tr>
	
						</table>
						<div class="container_pulsante">
							<input type="submit" name="rimuoviFamiliare" value="Cancella familiare" 
								onclick="document.getElementById('rimuoviFamiliareIndex').value='${i}';return true;"/>
						</div>
						<br />
						<br />
						<c:set var="i" value="${i+1}" scope="page" />	
					</c:forEach>
					<div><form:errors path="familiari" cssStyle="color:red"/></div> 
					
					<div class="container_pulsante">
						<input type="submit" name="aggiungiFamiliare" id="aggiungiFamiliare"
							value="Aggiungi familiare" />
					</div>
				</fieldset>
				
				<fieldset>
					<legend>
						<spring:message code="label.note" />
					</legend>
					<form:textarea path="note" cols="100" rows="5" />
				</fieldset>
				<br />
				<%@ include file="../common/footer.jsp"%>
				<br />
				<input type="submit" name="genera"
					value="<spring:message code="button.dichiarazione" />" />
				<br />
			</c:if>
			<c:if test="${!empty download}">
				<a href="${dichiarazioneReportURL}"> <em><spring:message
							code="link.dichiarazione" /> </em>
				</a>
				<br />
				<br />
				<p style="text-align: center;">
					<a href="<portlet:renderURL portletMode="view"/>">- <spring:message
							code="button.home" /> -
					</a>
				</p>
			</c:if>
		</form:form>
		<c:remove var="i" scope="page" />
		<c:remove var="j" scope="page" />
	</c:when>
	<c:otherwise>
		<div>
			<strong><spring:message code="errore.pdds.codice3" /></strong>
		</div>
	</c:otherwise>
</c:choose>