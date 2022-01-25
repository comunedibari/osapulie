<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="variazioneImuReport" escapeXml="false">
</portlet:resourceURL>

<script>
	function  changeAcquisto(indice) {
		var chk= document.getElementById('chkAcquisto['+indice+']');
		if(chk.checked){
			document.getElementById('chkCessione['+indice+']').checked=false;
		}
	}
	
	function  changeCessione(indice) {
		var chk= document.getElementById('chkCessione['+indice+']');
		if(chk.checked){
			document.getElementById('chkAcquisto['+indice+']').checked=false;
		}
	}
	
	function cambiaCaratteristicaImmobile(indice){
		var valoreCaratteristicaImmobile= document.getElementById('posizioni['+indice+'].caratteristicaImmobile').value;
		if(valoreCaratteristicaImmobile=='1' || valoreCaratteristicaImmobile == '3'){
			document.getElementById('colonnaLabelInagibile['+indice+']').style.visibility = 'hidden';
			document.getElementById('colonnaTxtInagibile['+indice+']').style.visibility = 'hidden';
			document.getElementById('chkInagibile['+indice+']').value= '';
		}
		else{
			document.getElementById('colonnaLabelInagibile['+indice+']').style.visibility = 'visible';
			document.getElementById('colonnaTxtInagibile['+indice+']').style.visibility = 'visible';
		}
			
	}
</script>

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
	<c:when test="${!empty datiVariazioneImu}">
		<form:form id="${idForm}" action="${dichiarazioneUrlGenera}"
			method="post" commandName="datiVariazioneImu">
			<c:if test="${empty download}">
				<fieldset>
					<legend>
						<spring:message code="label.contribuente" />
					</legend>
					<table class="genericTable">
						<tr>
							<td width="180"><label><spring:message
										code="label.cognome" />*:</label></td>
							<td width="180"><form:input path="cognome" />
								<div>
									<form:errors path="cognome" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.nome" />*:</label></td>
							<td colspan="3"><form:input path="nome" />
								<div>
									<form:errors path="nome" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.cf" />*:</label></td>
							<td width="180"><form:input path="codiceFiscale" />
								<div>
									<form:errors path="codiceFiscale" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.sesso" />*:</label></td>
							<td colspan="3">
								<form:radiobutton path="sesso" value="M" />M
								<form:radiobutton path="sesso" value="F" />F
								<div>
									<form:errors path="sesso" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180">
							<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label></td>
							<td width="180">
					 			<form:input path="dataNascita" id="data_nascita_input" size="10" cssClass="data_input"/>
								<div>
									<form:errors path="dataNascita" cssStyle="color:red" />
								</div>
							</td>
							<td width="180"><label><spring:message
										code="label.comN" />*:</label></td>
							<td><form:input path="comuneNascita" />
								<div>
									<form:errors path="comuneNascita" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.provN" />*:</label></td>
							<td><form:input path="provinciaNascita" size="3" />
								<div>
									<form:errors path="provinciaNascita" cssStyle="color:red" />
								</div></td>
						</tr>

						<tr>
							<td width="180"><label><spring:message
										code="label.comuneIscrizione" />*:</label></td>
							<td width="180"><form:input path="comuneResidenza" />
								<div>
									<form:errors path="comuneResidenza" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.provRes" />*:</label></td>
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
										code="label.indRes" />*:</label></td>
							<td width="180" colspan="5"><form:input path="indirizzoResidenza" />
								<div>
									<form:errors path="indirizzoResidenza" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.scala" />:</label></td>
							<td width="180"><form:input path="scala" size="7" /></td>
							<td width="180"><label><spring:message
										code="label.piano" />:</label></td>
							<td><form:input path="piano" size="7" /></td>
							<td><label><spring:message code="label.interno" />:</label>
							</td>
							<td><form:input path="interno" size="7" /></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.telefono" />:</label></td>
							<td width="180"><form:input path="telefono" /></td>
							<td width="180"><label><spring:message
										code="label.email" />:</label></td>
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
										code="label.cognome" />* :</label></td>
							<td width="180"><form:input path="cognomeDic" />
								<div>
									<form:errors path="cognomeDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.nome" />* :</label></td>
							<td colspan="3"><form:input path="nomeDic" />
								<div>
									<form:errors path="nomeDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.cf" />*:</label></td>
							<td width="180"><form:input path="codiceFiscaleDic" />
								<div>
									<form:errors path="codiceFiscaleDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.sesso" />*:</label></td>
							<td colspan="3">
								<form:radiobutton path="sessoDic" value="M" />M
								<form:radiobutton path="sessoDic" value="F" />F
								<div>
									<form:errors path="sessoDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.naturaCarica" />*:</label></td>
							<td colspan="5"><form:input path="naturaCarica" size="40" />
								<div>
									<form:errors path="naturaCarica" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label></td>
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
							<td><label><spring:message code="label.provN" />*:</label>
							</td>
							<td><form:input path="provinciaNascitaDic" size="3" />
								<div>
									<form:errors path="provinciaNascitaDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.comuneIscrizione" />*:</label></td>
							<td width="180"><form:input path="comuneResidenzaDic" />
								<div>
									<form:errors path="comuneResidenzaDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.provRes" />*:</label></td>
							<td><form:input path="provResidenzaDic" size="3" />
								<div>
									<form:errors path="provResidenzaDic" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.cap" />*:</label></td>
							<td><form:input path="capResidenzaDic" size="8" />
								<div>
									<form:errors path="capResidenzaDic" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="180"><label><spring:message
										code="label.indRes" />*:</label></td>
							<td width="180"><form:input path="indirizzoResidenzaDic" />
								<div>
									<form:errors path="indirizzoResidenzaDic" cssStyle="color:red" />
								</div></td>
							<td width="180"><label><spring:message
										code="label.civicoRes" />*:</label></td>
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
							<spring:message code="label.contitolari" />
						</legend>
						<input type="hidden" name="rimuoviContitolareIndex" id="rimuoviContitolareIndex" value="" />
						<c:set var="i" value="0" scope="page" />
						<c:forEach var="item" begin="0" items="${datiVariazioneImu.contitolari}">
							<b><spring:message	code="label.numOrdine" />: ${i + 1}</b>
							<br>
					    	<table class="genericTable">
									<tr>
										<td width="180"><label><spring:message
													code="label.cognome" />*:</label></td>
										<td width="180"><form:input path="contitolari[${i}].cognome" />
											<div>
												<form:errors path="contitolari[${i}].cognome" cssStyle="color:red" />
											</div></td>
										<td width="180"><label><spring:message
													code="label.nome" />*:</label></td>
										<td colspan="3"><form:input path="contitolari[${i}].nome" />
											<div>
												<form:errors path="contitolari[${i}].nome" cssStyle="color:red" />
											</div></td>
									</tr>
									<tr>
										<td width="180"><label><spring:message
													code="label.cf" />*:</label></td>
										<td width="180"><form:input path="contitolari[${i}].codiceFiscale" />
											<div>
												<form:errors path="contitolari[${i}].codiceFiscale" cssStyle="color:red" />
											</div></td>
										<td width="180"><label><spring:message
													code="label.sesso" />*:</label></td>
										<td colspan="3">
												<form:radiobutton path="contitolari[${i}].sesso" value="M" />M
												<form:radiobutton path="contitolari[${i}].sesso" value="F" />F
											<div>
												<form:errors path="contitolari[${i}].sesso" cssStyle="color:red" />
											</div></td>
									</tr>
									<tr>
										<td width="180"><label><spring:message
													code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label></td>
										<td width="180"> 
									 		<form:input path="contitolari[${i}].dataNascita" id="data_nascita_cont_input_${i}" size="10" cssClass="data_input"/>
											<div>
												<form:errors path="contitolari[${i}].dataNascita" cssStyle="color:red" />
											</div>
										</td>
										<td width="180"><label><spring:message
													code="label.comN" />*:</label></td>
										<td><form:input path="contitolari[${i}].comuneNascita" />
											<div>
												<form:errors path="contitolari[${i}].comuneNascita" cssStyle="color:red" />
											</div></td>
										<td><label><spring:message code="label.provN" />*:</label></td>
										<td><form:input path="contitolari[${i}].provinciaNascita" size="3" />
											<div>
												<form:errors path="contitolari[${i}].provinciaNascita" cssStyle="color:red" />
											</div></td>
									</tr>
		
									<tr>
									<td width="180"><label><spring:message
												code="label.comuneIscrizione" />*:</label></td>
									<td width="180"><form:input path="contitolari[${i}].comuneResidenza" />
										<div>
											<form:errors path="contitolari[${i}].comuneResidenza" cssStyle="color:red" />
										</div></td>
									<td width="180"><label><spring:message
												code="label.provRes" />*:</label></td>
									<td><form:input path="contitolari[${i}].provResidenza" size="3" />
										<div>
											<form:errors path="contitolari[${i}].provResidenza" cssStyle="color:red" />
										</div></td>
									<td><label><spring:message code="label.cap" />*:</label></td>
									<td><form:input path="contitolari[${i}].capResidenza" size="8" />
										<div>
											<form:errors path="contitolari[${i}].capResidenza" cssStyle="color:red" />
										</div></td>
								</tr>
								<tr>
									<td width="180"><label><spring:message
												code="label.indRes" />*:</label></td>
									<td width="180"><form:input path="contitolari[${i}].indirizzoResidenza" />
										<div>
											<form:errors path="contitolari[${i}].indirizzoResidenza" cssStyle="color:red" />
										</div></td>
									<td width="180"><label><spring:message
												code="label.civicoRes" />*:</label></td>
									<td colspan="3"><form:input path="contitolari[${i}].civico" size="7" />
										<div>
											<form:errors path="contitolari[${i}].civico" cssStyle="color:red" />
										</div></td>
								</tr>
								<tr>
									<td width="180"><label><spring:message
												code="label.telefono" />:</label></td>
									<td width="180"><form:input path="contitolari[${i}].telefono" /></td>
									<td width="180"><label><spring:message
												code="label.email" />:</label></td>
									<td colspan="3"><form:input path="contitolari[${i}].email" />
													<div>
											<form:errors path="contitolari[${i}].email" cssStyle="color:red" />
										</div>
									</td>
								</tr>
								<tr>
									<td width="180"><label><spring:message
												code="label.percPoss" />* :</label></td>
									<td width="180"><form:input path="contitolari[${i}].percentualePossesso" />
													<div>
														<form:errors path="contitolari[${i}].percentualePossesso" cssStyle="color:red" />
													</div>
									</td>
									<td width="180"><label><spring:message
												code="label.detrazioneAbitazionePrincipale" />:</label></td>
									<td width="180"><form:input path="contitolari[${i}].detrazioneAbitazionePrincipale" />
													<div>
														<form:errors path="contitolari[${i}].detrazioneAbitazionePrincipale" cssStyle="color:red" />
													</div>
									</td>
								</tr>
							</table>
							<div class="container_pulsante">
								<input type="submit" name="rimuoviContitolare" value="Cancella contitolare" 
									onclick="document.getElementById('rimuoviContitolareIndex').value='${i}';return true;"/>
							</div>
							<br />
							<br />
							<c:set var="i" value="${i+1}" scope="page" />	
						</c:forEach>
						<div><form:errors path="contitolari" cssStyle="color:red"/></div> 
						
						<div class="container_pulsante">
							<input type="submit" name="aggiungiContitolare" id="aggiungiContitolare"
								value="Aggiungi contitolare" />
						</div>
					</fieldset>
					
					<fieldset>
						<legend>
							<spring:message code="label.immobili" />
						</legend>
						<c:choose>
							<c:when test="${!empty datiVariazioneImu.posizioni}">
								<c:if test="${!empty situazione}">
									<c:set var="annoRiferimentoString">${situazione.annoRiferimento}</c:set>
									<c:if test="${annoCorrente != annoRiferimentoString}">
										<span class="red"><spring:message code="label.dati.noAnnoCorrente.noStar" arguments="${annoRiferimentoString}"/>.</span><br>
									</c:if>
								</c:if>
								<c:set var="j" value="0" scope="page" />
								<c:forEach var="item" begin="0" items="${datiVariazioneImu.posizioni}">
									<b><spring:message	code="label.numOrdine" />: ${j + 1}</b>
									<br>
							    	<table class="genericTable">
								    	<tr>
									    	<td width="180">
												<label><spring:message code="label.caratteristicaImmobile" />&nbsp;(1) *:</label>
											</td>
											<td>
												<c:set var="tipoImmobile" value="${item.caratteristicaImmobile}"></c:set>
												<form:select path="posizioni[${j}].caratteristicaImmobile" onchange="cambiaCaratteristicaImmobile('${j}');"
													id="posizioni[${j}].caratteristicaImmobile">		
													<option value="" 
														<c:if test="${tipoImmobile == null || tipoImmobile == '' || tipoImmobile == '0'}"> selected="selected" </c:if>>---</option>
										    		<option value="1" 
										    			<c:if test="${tipoImmobile == '1'}"> selected="selected" </c:if>>1 - Terreno</option>
										    		<option value="3" 
										    			<c:if test="${tipoImmobile == '3'}"> selected="selected" </c:if>>3 - Fabbricato con valore determinato sulla base della rendita catastale</option>
													<option value="4" 
										    			<c:if test="${tipoImmobile == '4'}"> selected="selected" </c:if>>4 - Fabbricato con valore determinato sulla base delle scritture contabili</option>
													<option value="5.1" 
										    			<c:if test="${tipoImmobile == '5.1'}"> selected="selected" </c:if>>5.1 - Per Immobili non produttivi di reddito fondiario, ai sensi dell'art. 43 del TUIR</option>
													<option value="5.2" 
										    			<c:if test="${tipoImmobile == '5.2'}"> selected="selected" </c:if>>5.2 - Per Immobili Locati</option>
												</form:select>
												<div><form:errors path="posizioni[${j}].caratteristicaImmobile" cssStyle="color:red"/></div>
												<c:remove var="tipoImmobile" ></c:remove>
											</td>
										</tr>
										<tr>
											<td width="180">
												<label><spring:message code="label.indir" />&nbsp;(2) *:</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].indirizzoUtenza.via.descrizione" />
												<div><form:errors path="posizioni[${j}].indirizzoUtenza.via.descrizione" cssStyle="color:red"/></div>	 
											</td>
										</tr>
									</table>
		
									<table class="genericTable">
										<tr>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.sezione" />&nbsp;(3):</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.foglioCatastale" />&nbsp;(4):</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.particella" />&nbsp;(5):</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.sub" />&nbsp;(6):</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.categoriaImmobile" />&nbsp;(7):</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.classe" />&nbsp;(8):</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.nProt" />&nbsp;(9):</label>
											</td>
											<td style="text-align:center;width:12.5%;background:#FFF3E1;">
												<label><spring:message code="label.anno" />&nbsp;(10):</label>
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
											<td id="colonnaLabelInagibile[${j}]">
												<label><spring:message code="label.inagibile" />(11):</label>
											</td>
											<td id="colonnaTxtInagibile[${j}]">
												<form:checkbox path="posizioni[${j}].inagibile" id="chkInagibile[${j}]"/>					
												<div><form:errors path="posizioni[${j}].inagibile" cssStyle="color:red"/></div>
											</td>
											<td>
												<label><spring:message code="label.valore" />(12) *:</label>
											</td>
											<td colspan="3">
												<form:input path="posizioni[${j}].valoreImmobile" />
												<div><form:errors path="posizioni[${j}].valoreImmobile" cssStyle="color:red"/></div>
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.percPoss" />&nbsp;(13):</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].percentualePossesso" />
												<div><form:errors path="posizioni[${j}].percentualePossesso" cssStyle="color:red"/></div>
											</td>
											<td>
												<label><spring:message code="label.riduzione" />&nbsp;(14):</label>
											</td>
											<td>
												<form:checkbox path="posizioni[${j}].riduzione" />
												<div><form:errors path="posizioni[${j}].riduzione" cssStyle="color:red"/></div>
											</td>
											<td>
												<label><spring:message code="label.esenzione" />&nbsp;(15):</label>
											</td>
											<td>
												<form:checkbox path="posizioni[${j}].esenzione" />
												<div><form:errors path="posizioni[${j}].esenzione" cssStyle="color:red"/></div>
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.dataInizioPossesso" />(16):</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].dataInizioPossesso" id="data_iniziopossesso_input_${j}" size="10" cssClass="data_input"/>
												<div><form:errors path="posizioni[${j}].dataInizioPossesso" cssStyle="color:red"/></div>
											</td>
											<td>
												<label><spring:message code="label.detrazioneAbitazionePrincipale" />(17):</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].importoDetrazioneAbPrincipale"/>
												<div><form:errors path="posizioni[${j}].importoDetrazioneAbPrincipale" cssStyle="color:red"/></div>
											</td>
											<td>
												<label><spring:message code="label.dataUltimazioneLavori" />(18):</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].dataUltimazioneLavori" id="data_ultimazionelavori_input_${j}" size="10" cssClass="data_input"/>
												<div><form:errors path="posizioni[${j}].dataUltimazioneLavori" cssStyle="color:red"/></div>
											</td>
										</tr>
										<tr>
											<td>
												<label><spring:message code="label.acquistoCessione" />:</label>
											</td>
											<td>
												 <form:checkbox path="posizioni[${j}].diAcquisto" id="chkAcquisto[${j}]" onchange="changeAcquisto('${j}');" />	 &nbsp;	 
												 <form:checkbox path="posizioni[${j}].diCessione" id="chkCessione[${j}]" onchange="changeCessione('${j}');" />	 &nbsp;	
												 <div><form:errors path="posizioni[${j}].diCessione" cssStyle="color:red"/></div> 
											</td>
											<td>
												<label><spring:message code="label.presso" />:</label>
											</td>
											<td>
												<form:input path="posizioni[${j}].agenziaEntrate"/>
											</td>
											<td>
												<label><spring:message code="label.estremi" />:</label>
											</td>
											<td colspan="3">
												<form:input path="posizioni[${j}].estremiTitolo"/>
											</td>
										</tr>
									</table>
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