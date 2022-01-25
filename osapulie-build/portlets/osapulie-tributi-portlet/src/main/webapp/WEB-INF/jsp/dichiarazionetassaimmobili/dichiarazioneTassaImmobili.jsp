<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="dichiarazioneTassaImmobiliReport" escapeXml="false">
</portlet:resourceURL>

<script>

$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
 
function resetNumeric(clazz) {
	var value = $(clazz).val();
	if(value && value == 0) $(clazz).val('');
}
 
function cambiaCaratteristicaImmobileTotalmenteImponibile(indice){
	var valoreCaratteristicaImmobile= document.getElementById('posizioniTotalmenteImponibili['+indice+'].caratteristicaImmobile').value;
	if(valoreCaratteristicaImmobile=='1' || valoreCaratteristicaImmobile == '2'){
		/* 11 */
		document.getElementById('colonnaLabelInagibileTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('colonnaTxtInagibileTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('chkInagibileTotalmenteImponibili['+indice+']').value= 'false';
		/* 14 */
		document.getElementById('colonnaLabelRiduzioneAgricoliTotalmenteImponibili['+indice+']').style.visibility = 'visible';
		document.getElementById('colonnaTxtRiduzioneAgricoliTotalmenteImponibili['+indice+']').style.visibility = 'visible';
		document.getElementById('chkRiduzioneAgricoliTotalmenteImponibili['+indice+']').value= '';
		/* 17 */
		document.getElementById('colonnaLabelDetrazioneAbPrincipaleTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('colonnaTxtDetrazioneAbPrincipaleTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
	}
	else if(valoreCaratteristicaImmobile=='3' || valoreCaratteristicaImmobile == '4' 
			|| valoreCaratteristicaImmobile == '7.1' || valoreCaratteristicaImmobile == '7.3' 
			|| valoreCaratteristicaImmobile == '8'){
		/* 11 */
		document.getElementById('colonnaLabelInagibileTotalmenteImponibili['+indice+']').style.visibility = 'visible';
		document.getElementById('colonnaTxtInagibileTotalmenteImponibili['+indice+']').style.visibility = 'visible';
		document.getElementById('chkInagibileTotalmenteImponibili['+indice+']').value= 'false';
		/* 14 */
		document.getElementById('colonnaLabelRiduzioneAgricoliTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('colonnaTxtRiduzioneAgricoliTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('chkRiduzioneAgricoliTotalmenteImponibili['+indice+']').value= '';
		/* 17 */
		document.getElementById('colonnaLabelDetrazioneAbPrincipaleTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('colonnaTxtDetrazioneAbPrincipaleTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
	}
	else if(valoreCaratteristicaImmobile=='5' || valoreCaratteristicaImmobile == '6' 
			|| valoreCaratteristicaImmobile == '7.2'){
		/* 11 */
		document.getElementById('colonnaLabelInagibileTotalmenteImponibili['+indice+']').style.visibility = 'visible';
		document.getElementById('colonnaTxtInagibileTotalmenteImponibili['+indice+']').style.visibility = 'visible';
		document.getElementById('chkInagibileTotalmenteImponibili['+indice+']').value= 'false';
		/* 14 */
		document.getElementById('colonnaLabelRiduzioneAgricoliTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('colonnaTxtRiduzioneAgricoliTotalmenteImponibili['+indice+']').style.visibility = 'hidden';
		document.getElementById('chkRiduzioneAgricoliTotalmenteImponibili['+indice+']').value= '';
		/* 17 */
		document.getElementById('colonnaLabelDetrazioneAbPrincipaleTotalmenteImponibili['+indice+']').style.visibility = 'visible';
		document.getElementById('colonnaTxtDetrazioneAbPrincipaleTotalmenteImponibili['+indice+']').style.visibility = 'visible';
	}
		
}

function cambiaCaratteristicaImmobileParzialmenteImponibile(indice){
	var valoreCaratteristicaImmobile= document.getElementById('posizioniParzialmenteImponibiliOEsenti['+indice+'].caratteristicaImmobile').value;
	if(valoreCaratteristicaImmobile=='1' || valoreCaratteristicaImmobile == '3'){
		document.getElementById('colonnaLabelInagibileParzialmenteImponibiliOEsenti['+indice+']').style.visibility = 'hidden';
		document.getElementById('colonnaTxtInagibileParzialmenteImponibiliOEsenti['+indice+']').style.visibility = 'hidden';
		document.getElementById('chkInagibileParzialmenteImponibiliOEsenti['+indice+']').value= 'false';
	}
	else{
		document.getElementById('colonnaLabelInagibileParzialmenteImponibiliOEsenti['+indice+']').style.visibility = 'visible';
		document.getElementById('colonnaTxtInagibileParzialmenteImponibiliOEsenti['+indice+']').style.visibility = 'visible';
	}
}

function  changeAcquistoTotalmenteImponibili(indice) {
	var chk= document.getElementById('chkTotalmenteImponibiliAcquisto['+indice+']');
	if(chk.checked){
		document.getElementById('chkTotalmenteImponibiliCessione['+indice+']').checked=false;
	}
}

function  changeCessioneTotalmenteImponibili(indice) {
	var chk= document.getElementById('chkTotalmenteImponibiliCessione['+indice+']');
	if(chk.checked){
		document.getElementById('chkTotalmenteImponibiliAcquisto['+indice+']').checked=false;
	}
	
}

function  changeAcquistoParzialmenteImponibili(indice) {
	var chk= document.getElementById('chkParzialmenteImponibiliAcquisto['+indice+']');
	if(chk.checked){
		document.getElementById('chkParzialmenteImponibiliCessione['+indice+']').checked=false;
	}
}

function  changeCessioneParzialmenteImponibili(indice) {
	var chk= document.getElementById('chkParzialmenteImponibiliCessione['+indice+']');
	if(chk.checked){
		document.getElementById('chkParzialmenteImponibiliAcquisto['+indice+']').checked=false;
	}
}

</script>

<c:set var="idForm" value="produrreDichiarazione" />
<div class="mainDiv dichiarazioneTassaImmobili">
	<c:choose>
		<c:when test="${!empty datiTassaImmobili}">
			<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiTassaImmobili">
				<c:if test="${empty download}">
					<table class="genericTable">
						<tr>
							<td width="180">
								<label><spring:message code="label.cognome" />*:</label>
							</td>
							<td width="180">
								<form:input path="cognome" readonly="true"/> <div><form:errors path="cognome" cssStyle="color:red"/></div>
							</td>
							<td width="180">
								<label><spring:message code="label.nome" />*:</label>
							</td>
							<td colspan="3">
								<form:input path="nome" readonly="true"/> <div><form:errors path="nome" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.cf" />*:</label>
							</td>
							<td width="180">
								<form:input path="codiceFiscale" readonly="true"/> <div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>
							</td>
							<td width="180">
								<label><spring:message code="label.sesso" />*:</label>
							</td>
							<td colspan="3">
								<form:radiobutton path="sesso" value="M" disabled="true"/>M
								<form:radiobutton path="sesso" value="F" disabled="true"/>F
								<div><form:errors path="sesso" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
							</td>
							<td width="180">
								<form:input path="dataNascita" size="10" cssClass="data_input"/> 
								<div><form:errors path="dataNascita" cssStyle="color:red"/></div>
							</td>
							<td width="180">
								<label><spring:message code="label.comN" />*:</label>
							</td>
							<td>
								<form:input path="comuneNascita" readonly="true"/>  <div><form:errors path="comuneNascita" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message code="label.provN" />*:</label>
							</td>
							<td>
								<form:input path="provinciaNascita" size="3" readonly="true"/> <div><form:errors path="provinciaNascita" cssStyle="color:red"/></div> 
							</td>
						</tr>
						
						<tr>
							<td width="180">
								<label><spring:message code="label.comuneIscrizione" />*:</label>
							</td>
							<td width="180">
								<form:input path="comuneResidenza" readonly="true"/> <div><form:errors path="comuneResidenza" cssStyle="color:red"/></div> 
							</td>
							<td width="180">
								<label><spring:message code="label.provRes" />*:</label>
							</td>
							<td>
								<form:input path="provResidenza" size="3" readonly="true"/> <div><form:errors path="provResidenza" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message code="label.cap" />*:</label>
							</td>
							<td>
								<form:input path="capResidenza" size="8" readonly="true"/> <div><form:errors path="capResidenza" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.indRes" />*:</label>
							</td>
							<td width="180" colspan="5">
								<form:input path="indirizzo" readonly="true"/> <div><form:errors path="indirizzo" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.scala" />:</label>
							</td>
							<td width="180">
								<form:input path="scala" size="7"/>
							</td>
							<td width="180">
								<label><spring:message code="label.piano" />:</label>
							</td>
							<td>
								<form:input path="piano" size="7"/>
							</td>
							<td>
								<label><spring:message code="label.interno" />:</label>
							</td>
							<td>
								<form:input path="interno" size="7"/>
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.telefono" />:</label>
							</td>
							<td width="180">
								<form:input path="telefono"/>
							</td>
							<td width="180">
								<label><spring:message code="label.email" />:</label>
							</td>
							<td  colspan="3">
								<form:input path="email"/> <div><form:errors path="email" cssStyle="color:red"/></div>
							</td>
						</tr>
					</table>
					
					<fieldset>
						<legend>
							<spring:message code="label.immobiliTotalmenteImponibili" />
						</legend>
						<input type="hidden" name="rimuoviImmobileTotalmenteImponibileIndex" id="rimuoviImmobileTotalmenteImponibileIndex" value="" />
						<c:set var="i" value="0" scope="page" />
						<c:forEach var="item" begin="0" items="${datiTassaImmobili.posizioniTotalmenteImponibili}">
					    	<table class="genericTable">
						    	<tr>
							    	<td width="180">
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.caratteristicaImmobile" />&nbsp;(1):</label>
									</td>
									<td>
										<c:set var="tipoImmobile" value="${item.caratteristicaImmobile}"></c:set>
										<form:select path="posizioniTotalmenteImponibili[${i}].caratteristicaImmobile" onchange="cambiaCaratteristicaImmobileTotalmenteImponibile('${i}');"
											id="posizioniTotalmenteImponibili[${i}].caratteristicaImmobile">		
											<option value="" 
												<c:if test="${tipoImmobile == null || tipoImmobile == '' || tipoImmobile == '0'}"> selected="selected" </c:if>>---</option>
								    		<option value="1" 
								    			<c:if test="${tipoImmobile == '1'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile1" /></option>
								    		<option value="2" 
								    			<c:if test="${tipoImmobile == '2'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile2" /></option>
								    		<option value="3" 
								    			<c:if test="${tipoImmobile == '3'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile3" /></option>
											<option value="4" 
								    			<c:if test="${tipoImmobile == '4'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile4" /></option>
											<option value="5" 
								    			<c:if test="${tipoImmobile == '5'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile5" /></option>
											<option value="6" 
								    			<c:if test="${tipoImmobile == '6'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile6" /></option>
											<option value="7.1" 
								    			<c:if test="${tipoImmobile == '7.1'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile71" /></option>
											<option value="7.2" 
								    			<c:if test="${tipoImmobile == '7.2'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile72" /></option>
											<option value="7.3" 
								    			<c:if test="${tipoImmobile == '7.3'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile73" /></option>
											<option value="8" 
								    			<c:if test="${tipoImmobile == '8'}"> selected="selected" </c:if>><spring:message code="label.caratteristicaImmobile8" /></option>
										</form:select>
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].caratteristicaImmobile" cssStyle="color:red"/></div>
										<c:remove var="tipoImmobile" ></c:remove>
									</td>
								</tr>
								<tr>
									<td width="180">
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.indir" />&nbsp;(2):</label>
									</td>
									<td>
										<form:input path="posizioniTotalmenteImponibili[${i}].indirizzoUtenza.via.descrizione" />
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].indirizzoUtenza.via.descrizione" cssStyle="color:red"/></div>	 
									</td>
								</tr>
							</table>

							<table class="genericTable">
								<tr>
									<td colspan="2">
										<label><spring:message code="label.tipoCatasto" />:</label>
									</td>
									<td colspan="2">
										<form:input path="posizioniTotalmenteImponibili[${i}].tipoCatasto" />  
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].tipoCatasto" cssStyle="color:red"/></div>
									</td>
									<td colspan="2">
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.codiceCatasto" />:</label>
									</td>
									<td colspan="2">
										<form:input path="posizioniTotalmenteImponibili[${i}].codiceCatasto"/>  
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].codiceCatasto" cssStyle="color:red"/></div>
									</td>
								</tr>
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
										<form:input path="posizioniTotalmenteImponibili[${i}].sezione" size="3"/>  
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].sezione" cssStyle="color:red"/></div> 
									</td>
									<td style="text-align:center;width:12.5%;background:#FFF3E1;">
										<form:input class="foglio" path="posizioniTotalmenteImponibili[${i}].foglio"  size="3"/>
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].foglio" cssStyle="color:red"/></div>
									</td>
									<td style="text-align:center;width:12.5%;background:#FFF3E1;">
										<form:input class="particella" path="posizioniTotalmenteImponibili[${i}].particella" size="3"/>
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].particella" cssStyle="color:red"/></div> 
									</td>
									<td style="text-align:center;width:12.5%;background:#FFF3E1;">
										<form:input class="subalterno" path="posizioniTotalmenteImponibili[${i}].subalterno" size="3" />
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].subalterno" cssStyle="color:red"/></div>
									</td>
									<td style="text-align:center;width:12.5%;background:#FFF3E1;">
										<form:input path="posizioniTotalmenteImponibili[${i}].categoriaImmobile.descrizione" />
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].categoriaImmobile.descrizione" cssStyle="color:red"/></div>
									</td>
									<td style="text-align:center;width:12.5%;background:#FFF3E1;">
										<form:input path="posizioniTotalmenteImponibili[${i}].classe" size="3"/>
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].classe" cssStyle="color:red"/></div>
									</td>
									<td style="text-align:center;width:12.5%;background:#FFF3E1;">
										<form:input path="posizioniTotalmenteImponibili[${i}].numProt" size="8"/>
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].numProt" cssStyle="color:red"/></div>
									</td>
									<td style="text-align:center;width:12.5%;background:#FFF3E1;">
										<form:input path="posizioniTotalmenteImponibili[${i}].anno" size="8"/>
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].anno" cssStyle="color:red"/></div>
									</td>
								</tr>
							</table>
							
							
							<table class="genericTable">
								<tr>
									<td id="colonnaLabelInagibileTotalmenteImponibili[${i}]">
										<label><spring:message code="label.inagibile" />:</label>
									</td>
									<td id="colonnaTxtInagibileTotalmenteImponibili[${i}]">
										<form:checkbox path="posizioniTotalmenteImponibili[${i}].inagibile" id="chkInagibileTotalmenteImponibili[${i}]"/>					
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].inagibile" cssStyle="color:red"/></div>
									</td>
									<td>
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.valore" />:</label>
									</td>
									<td>
										<form:input class="valoreImmobile" path="posizioniTotalmenteImponibili[${i}].valoreImmobile" />
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].valoreImmobile" cssStyle="color:red"/></div>
									</td>
								</tr>
								<tr>
									<td>
										<label><spring:message code="label.percPoss" />&nbsp;(13):</label>
									</td>
									<td>
										<form:input class="percentualePossesso" path="posizioniTotalmenteImponibili[${i}].percentualePossesso" />
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].percentualePossesso" cssStyle="color:red"/></div>
									</td>
									<td id="colonnaLabelRiduzioneAgricoliTotalmenteImponibili[${i}]">
										<label><spring:message code="label.riduzioneTerreniAgricoli" />:</label>
									</td>
									<td id="colonnaTxtRiduzioneAgricoliTotalmenteImponibili[${i}]">
										<form:checkbox path="posizioniTotalmenteImponibili[${i}].riduzione" id="chkRiduzioneAgricoliTotalmenteImponibili[${i}]"/>			
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].riduzione" cssStyle="color:red"/></div>
									</td>
								</tr>
								<tr>
									<td>
										<label><spring:message code="label.esenzione" />&nbsp;(15):</label>
									</td>
									<td>
										<form:checkbox path="posizioniTotalmenteImponibili[${i}].esenzione" />					
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].esenzione" cssStyle="color:red"/></div>
									</td>
									<td id="colonnaLabelDetrazioneAbPrincipaleTotalmenteImponibili[${i}]">
										<label><spring:message code="label.detrazioneAbitazionePrincipale"/>:</label>
									</td>
									<td id="colonnaTxtDetrazioneAbPrincipaleTotalmenteImponibili[${i}]">
										<form:input class="importoDetrazioneAbPrincipale" path="posizioniTotalmenteImponibili[${i}].importoDetrazioneAbPrincipale" />					
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].importoDetrazioneAbPrincipale" cssStyle="color:red"/></div>
									</td>
								</tr>
								
								<tr>
									<td>
										<label><spring:message code="label.dataInizioPossesso" />(16)&nbsp;(dd/mm/yyyy):</label>
									</td>
									<td>
										<form:input path="posizioniTotalmenteImponibili[${i}].dataInizioPossesso" size="10" id="data_iniziopossesso_input_${i}" cssClass="data_input"/> 
										<span><spring:message code="label.oVariazioniDiImposta" /></span>
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].dataInizioPossesso" cssStyle="color:red"/></div>
							 
									</td>
									<td>
										<label><spring:message code="label.dataUltimazioneLavori" />&nbsp;(dd/mm/yyyy):</label>
									</td>
									<td>
										<form:input path="posizioniTotalmenteImponibili[${i}].dataUltimazioneLavori" size="10" id="data_ultimazionelavori_input_${i}" cssClass="data_input"/> 
										<div><form:errors path="posizioniTotalmenteImponibili[${i}].dataUltimazioneLavori" cssStyle="color:red"/></div>
									</td>
								</tr>
								<tr>
									<td>
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.acquistoCessioneTotalmenteImponibili" />:</label>
									</td>
									<td colspan="3">
										 <form:checkbox path="posizioniTotalmenteImponibili[${i}].diAcquisto" id="chkTotalmenteImponibiliAcquisto[${i}]" onchange="changeAcquistoTotalmenteImponibili('${i}');" />&nbsp;Acquisto&nbsp;	 
										 <form:checkbox path="posizioniTotalmenteImponibili[${i}].diCessione" id="chkTotalmenteImponibiliCessione[${i}]" onchange="changeCessioneTotalmenteImponibili('${i}');" />&nbsp;Cessione&nbsp;	 
										  <spring:message code="label.proprietaODirittoReale" />
									</td>
								</tr>
								<tr>
									<td>
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.presso" />:</label>
									</td>
									<td>
										<form:input path="posizioniTotalmenteImponibili[${i}].agenziaEntrate"/>
									</td>
									<td>
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.estremi" />:</label>
									</td>
									<td>
										<form:input path="posizioniTotalmenteImponibili[${i}].estremiTitolo"/>
									</td>
								</tr>
								<tr>
									<td>
										<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.note" />:</label>
									</td>
									<td colspan="3">
										<form:textarea path="posizioniTotalmenteImponibili[${i}].annotazioni" cols="70" rows="5"/>
									</td>
								</tr>
							</table>
							<div class="container_pulsante">
								<input type="submit" name="rimuoviImmobileTotalmenteImponibile" value="Cancella immobile" onclick="document.getElementById('rimuoviImmobileTotalmenteImponibileIndex').value='${i}';return true;"/>
							</div>
							<c:set var="i" value="${i+1}" scope="page" />	
						</c:forEach>
						<div><form:errors path="posizioniTotalmenteImponibili" cssStyle="color:red"/></div> 
						
						<div class="container_pulsante">
							<input type="submit" name="aggiungiImmobileTotalmenteImponibile" id="aggiungiImmobileTotalmenteImponibile"
								value="Aggiungi immobile totalmente imponibile" />
						</div>
					</fieldset>
	
			
					<fieldset>
						<table class="genericTable">
							<tr>
								<td colspan="4"><br><strong><spring:message code="label.allegati.vincolo" /></strong></td>
							</tr>
						</table>
					</fieldset>	

					
					<%-- <fieldset>
						<legend>
							<spring:message code="label.immobiliParzialmenteImponibili" />
						</legend>
						<input type="hidden" name="rimuoviImmobileParzialmenteImponibileIndex" id="rimuoviImmobileParzialmenteImponibileIndex" value="" />
						<c:set var="j" value="0" scope="page" /> --%>
						<%-- <c:forEach var="item" begin="0" items="${datiTassaImmobili.posizioniParzialmenteImponibiliOEsenti}">
				    	<c:if test="${j % 2 == 0}">
				    		<table class="genericTable" style="background-color: #FFFFF0">
				    	</c:if>
				    	<c:if test="${j % 2 != 0}">
				    		<table class="genericTable" style="background-color: #FFFFFF">
				    	</c:if>
					    	<tr>
						    	<td width="180">
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.attivitaSvolta" />:</label>
								</td>
								<td colspan="3">
									 1&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta1" />&nbsp;&nbsp;
									 2&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta2" />&nbsp;&nbsp;
									 3&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta3" />&nbsp;&nbsp;
									 4&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta4" />&nbsp;&nbsp;
									 5&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta5" />&nbsp;&nbsp;
									 6&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta6" />&nbsp;&nbsp;
									 7&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta7" />&nbsp;&nbsp;
									 8&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta8" />&nbsp;&nbsp;
									 9&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta9" />&nbsp;&nbsp;
									 10&nbsp;<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].attivitaSvolta10" />
								</td>
							</tr>
					    	<tr>
						    	<td width="180">
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.caratteristicaImmobile" />&nbsp;(1):</label>
								</td>
								<td colspan="3">
									<c:set var="tipoImmobile" value="${item.caratteristicaImmobile}"></c:set>
									<form:select path="posizioniParzialmenteImponibiliOEsenti[${j}].caratteristicaImmobile" onchange="cambiaCaratteristicaImmobileParzialmenteImponibile('${j}');"
										 id="posizioniParzialmenteImponibiliOEsenti[${j}].caratteristicaImmobile">		
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
									<c:remove var="tipoImmobile" ></c:remove>				
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].caratteristicaImmobile" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td width="180">
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.indir" />&nbsp;(2):</label>
								</td>
								<td colspan="3">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].via.descrizione" />
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].via.descrizione" cssStyle="color:red"/></div>	 
								</td>
							</tr>
						</table>

						<table class="genericTable">
							<tr>
								<td colspan="2">
									<label><spring:message code="label.tipoCatasto" />:</label>
								</td>
								<td colspan="2">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].tipoCatasto" />  
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].tipoCatasto" cssStyle="color:red"/></div>
								</td>
								<td colspan="2">
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.codiceCatasto" />:</label>
								</td>
								<td colspan="2">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].codiceCatasto"/>  
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].codiceCatasto" cssStyle="color:red"/></div>
								</td>
							</tr>
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
								<td  style="text-align:center;background:#FFF3E1;">
									<label><spring:message code="label.anno" />&nbsp;(10):</label>
								</td>
							</tr>
							<tr>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].sezione" size="3"/>  
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].sezione" cssStyle="color:red"/></div> 
								</td>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].foglio" size="3"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].foglio" cssStyle="color:red"/></div>
								</td>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].particella" size="3"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].particella" cssStyle="color:red"/></div> 
								</td>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].subalterno" size="3" />
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].subalterno" cssStyle="color:red"/></div>
								</td>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].categoriaImmobile" />
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].categoriaImmobile" cssStyle="color:red"/></div>
								</td>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].classe" size="3"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].classe" cssStyle="color:red"/></div>
								</td>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].numProt" size="8"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].numProt" cssStyle="color:red"/></div>
								</td>
								<td style="text-align:center;width:12.5%;background:#FFF3E1;">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].anno" size="8"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].anno" cssStyle="color:red"/></div>
								</td>
							</tr>
						</table>

						<table class="genericTable">
							<tr>
								<td id="colonnaLabelInagibileParzialmenteImponibiliOEsenti[${j}]">
									<label><spring:message code="label.inagibile" />&nbsp;:</label>
								</td>
								<td id="colonnaTxtInagibileParzialmenteImponibiliOEsenti[${j}]">
									<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].inagibile" id="chkInagibileParzialmenteImponibiliOEsenti[${j}]"/>					
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].inagibile" cssStyle="color:red"/></div>
								</td>
								<td>
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.valore" />&nbsp;:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].valoreImmobile" />
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].valoreImmobile" cssStyle="color:red"/></div>
								</td>
								<td>
									<label><spring:message code="label.esenzione" />&nbsp;(13):</label>
								</td>
								<td>
									<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].esenzione" />					
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].esenzione" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.percPoss" />&nbsp;(14):</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].percentualePossesso" size="3"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].percentualePossesso" cssStyle="color:red"/></div>
								</td>
								<td>
									<label><spring:message code="label.dataInizioPossesso" />&nbsp;(15):</label>
								</td>
								<td colspan="3">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].dataInizioPossesso" size="10"/>
									<span><spring:message code="label.oVariazioniDiImposta" /></span>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].dataInizioPossesso" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.acquistoCessioneParzialmenteImponibili" />:</label>
								</td>
								<td colspan="5">
									 <form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].diAcquisto"  id="chkParzialmenteImponibiliAcquisto[${j}]" onchange="changeAcquistoParzialmenteImponibili('${j}');" />	 &nbsp;	 
									 <form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].diCessione" id="chkParzialmenteImponibiliCessione[${j}]" onchange="changeCessioneParzialmenteImponibili('${i}');" />	 &nbsp;	 
									 <spring:message code="label.proprietaODirittoReale" />
								</td>
							</tr>
							<tr>
								<td>
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.presso" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].agenziaEntrate"/>
								</td>
								<td>
									<a href="./guida-dichiarazione" title="<spring:message code="label.guidaDichiarazione" />" target="_blank"><i class="fa fa-info-circle"></i>
										</a><label><spring:message code="label.estremi" />:</label>
								</td>
								<td colspan="3">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].estremiTitolo"/>
								</td>
							</tr>
						</table>
						
						<div class="titoletto color9e2596">
							<spring:message code="label.attivitaDidattica" />
						</div>
						<table class="genericTable">
							<tr>
								<td>
									<label><spring:message code="label.cm" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].cmAttivitaDidattica"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].cmAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
								<td>
									<label><spring:message code="label.cms" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].cmsAttivitaDidattica"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].cmsAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									a)
								</td>
								<td colspan="2">
									<label><spring:message code="label.aAttivitaDidattica" />:</label>
								</td>
								<td width="180">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].aAttivitaDidattica"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].aAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									a1)
								</td>
								<td colspan="2">
									<label><spring:message code="label.a1AttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].a1AttivitaDidattica"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].a1AttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									b)
								</td>
								<td colspan="2">
									<label><spring:message code="label.bAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].bAttivitaDidattica"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].bAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									b1
								</td>
								<td colspan="2">
									<label><spring:message code="label.b1AttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].b1AttivitaDidattica"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].b1AttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									c)
								</td>
								<td colspan="2">
									<label><spring:message code="label.cAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].cAttivitaDidattica"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].cAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									d)
								</td>
								<td colspan="2">
									<label><spring:message code="label.dAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].dAttivitaDidattica"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].dAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									e)
								</td>
								<td colspan="2">
									<label><spring:message code="label.eAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].eAttivitaDidattica"/>&nbsp;&euro;
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].eAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									f)
								</td>
								<td colspan="2">
									<label><spring:message code="label.fAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].fAttivitaDidattica"/>&nbsp;&euro;
								<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].fAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									g)
								</td>
								<td colspan="2">
									<label><spring:message code="label.gAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].gAttivitaDidattica"/> Cm <= Cms
								</td>
							</tr>
							<tr>
								<td>
									h)
								</td>
								<td colspan="2">
									<label><spring:message code="label.hAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:checkbox path="posizioniParzialmenteImponibiliOEsenti[${j}].hAttivitaDidattica"/>  Cm > Cms
								</td>
							</tr>
							<tr>
								<td>
									i)
								</td>
								<td colspan="2">
									<label><spring:message code="label.iAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].iAttivitaDidattica"/>&nbsp;&euro;

								</td>
							</tr>
							<tr>
								<td>
									j)
								</td>
								<td colspan="2">
									<label><spring:message
										code="label.jAttivitaDidattica" />:
									</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].jAttivitaDidattica"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].jAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									k)
								</td>
								<td colspan="2">
									<label><spring:message code="label.kAttivitaDidattica" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].kAttivitaDidattica"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].kAttivitaDidattica" cssStyle="color:red"/></div> 
								</td>
							</tr>
						</table>
						
						<div class="titoletto color9e2596">
							<spring:message code="label.altreAttivita" />
						</div>
						<table class="genericTable">
							<tr>
								<td>
									<label><spring:message code="label.cenc" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].cencAltreAttivita"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].cencAltreAttivita" cssStyle="color:red"/></div> 
								</td>
								<td>
									<label><spring:message code="label.cm" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].cmAltreAttivita"/>
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].cmAltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									a)
								</td>
								<td colspan="2">
									<label><spring:message code="label.aAltreAttivita" />:</label>
								</td>
								<td width="180">
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].aAltreAttivita"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].aAltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									a1)
								</td>
								<td colspan="2">
									<label><spring:message code="label.a1AltreAttivita" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].a1AltreAttivita"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].a1AltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									b)
								</td>
								<td colspan="2">
									<label><spring:message code="label.bAltreAttivita" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].bAltreAttivita"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].bAltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									b1)
								</td>
								<td colspan="2">
									<label><spring:message code="label.b1AltreAttivita" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].b1AltreAttivita"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].b1AltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									c)
								</td>
								<td colspan="2">
									<label><spring:message code="label.cAltreAttivita" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].cAltreAttivita"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].cAltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									d)
								</td>
								<td colspan="2">
									<label><spring:message code="label.dAltreAttivita" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].dAltreAttivita"/>&nbsp;%
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].dAltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
							<tr>
								<td>
									e)
								</td>
								<td colspan="2">
									<label><spring:message code="label.eAltreAttivita" />:</label>
								</td>
								<td>
									<form:input path="posizioniParzialmenteImponibiliOEsenti[${j}].eAltreAttivita"/>&nbsp;&euro;
									<div><form:errors path="posizioniParzialmenteImponibiliOEsenti[${j}].eAltreAttivita" cssStyle="color:red"/></div>
								</td>
							</tr>
						</table>
							
						<div class="container_pulsante">
							<input type="submit" name="rimuoviImmobileParzialmenteImponibile" value="Cancella immobile" 
								onclick="document.getElementById('rimuoviImmobileParzialmenteImponibileIndex').value='${j}';return true;"/>
						</div>

						<c:set var="j" value="${j+1}" scope="page" />	
						</c:forEach> --%>
						<%-- 	
						<div><form:errors path="posizioniParzialmenteImponibiliOEsenti" cssStyle="color:red"/></div> 
						<div class="container_pulsante">	
							<input type="submit" name="aggiungiImmobileParzialmenteImponibile" id="aggiungiImmobileParzialmenteImponibile" value="Aggiungi immobile parzialmente imponibile o esente" />
						</div>						
					</fieldset> --%>
						
					<%-- <fieldset>
						<legend>
							<spring:message code="label.determinazioneImuTasi" />
						</legend>
						<table class="genericTable">
							
							<tr>
								<td>
									<label><spring:message code="label.annoDichiarazione" /></label>
								</td>
								<td>
									<select name="annoDichiarazione">
								    	<option value="${annoCorrente}">${annoCorrente}</option>
								    	<option value="${annoCorrente-1}">${annoCorrente-1}</option>
								    	<option value="${annoCorrente-2}">${annoCorrente-2}</option>
									</select>	
								
								
								</td>
							</tr>
							
							<tr>
								<td>
									<label><spring:message code="label.imuDovuta" />:</label>
								</td>	
								<td>
									<form:input path="imuDovuta"/>&nbsp;&euro;
									<div><form:errors path="imuDovuta" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message
											code="label.eccedenzaImuPrecedenteDichiarazione" />:
										</label>
								</td>	
								<td>
									<form:input path="eccedenzaImuPrecedenteDichiarazione"/>&nbsp;&euro;
									<div><form:errors path="eccedenzaImuPrecedenteDichiarazione" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
							<td>
								<label><spring:message
										code="label.eccedenzaImuPrecedenteDichiarazioneCompensataF24" />:
									</label>
							</td>	
								<td>
									<form:input path="eccedenzaImuPrecedenteDichiarazioneCompensataF24"/>&nbsp;&euro;
									<div><form:errors path="eccedenzaImuPrecedenteDichiarazioneCompensataF24" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.rateVersateImu" />:</label>
								</td>	
								<td>
									<form:input path="rateVersateImu"/>&nbsp;&euro;
									<div><form:errors path="rateVersateImu" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.imuDebito" />:</label>
								</td>	
								<td>
									<form:input path="imuDebito"/>&nbsp;&euro;
									<div><form:errors path="imuDebito" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.imuCredito" />:</label>
								</td>	
								<td>
									<form:input path="imuCredito"/>&nbsp;&euro;
									<div><form:errors path="imuCredito" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.tasiDovuta" />:</label>
								</td>	
								<td>
									<form:input path="tasiDovuta"/>&nbsp;&euro;
									<div><form:errors path="tasiDovuta" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.eccedenzaTasiPrecedenteDichiarazione" />:</label>
								</td>	
								<td>
									<form:input path="eccedenzaTasiPrecedenteDichiarazione"/>&nbsp;&euro;
									<div><form:errors path="eccedenzaTasiPrecedenteDichiarazione" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.eccedenzaTasiPrecedenteDichiarazioneCompensataF24" />:</label>
								</td>	
								<td>
									<form:input path="eccedenzaTasiPrecedenteDichiarazioneCompensataF24"/>&nbsp;&euro;
									<div><form:errors path="eccedenzaTasiPrecedenteDichiarazioneCompensataF24" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.rateVersateTasi" />:</label>
								</td>
								<td>
									<form:input path="rateVersateTasi"/>&nbsp;&euro;
									<div><form:errors path="rateVersateTasi" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.tasiDebito" />:</label>
								</td>	
								<td>
									<form:input path="tasiDebito"/>&nbsp;&euro;
									<div><form:errors path="tasiDebito" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.tasiCredito" />:</label>
								</td>	
								<td>
									<form:input path="tasiCredito"/>&nbsp;&euro;
									<div><form:errors path="tasiCredito" cssStyle="color:red"/></div>
								</td>	
							</tr>
						</table>
					</fieldset> --%>
						
					<%-- <fieldset>
						<legend>
							<spring:message code="label.compensazioneRimborsi" />
						</legend>	
						<table class="genericTable">
							<tr>
								<td rowspan="2" style="width:10%;">
									1) <spring:message code="label.imu" />
								</td>
								<td style="text-align:center;width:30%;">
									<label><spring:message code="label.imuCreditoPrecedenteDichiarazione" />:</label>
								</td>	
								<td style="text-align:center;width:30%;">
									<label><spring:message code="label.imuCreditoRimborso" />:</label>
								</td>	
								<td style="text-align:center;width:30%;">
									<label><spring:message code="label.imuCreditoCompensazione" />:</label>
								</td>	
							</tr>
							<tr>
								<td style="text-align:center;width:30%;">
									<form:input path="imuCreditoPrecedenteDichiarazione"/>
									<div><form:errors path="imuCreditoPrecedenteDichiarazione" cssStyle="color:red"/></div>
								</td>	
								<td style="text-align:center;width:30%;">
									<form:input path="imuCreditoRimborso"/>
									<div><form:errors path="imuCreditoRimborso" cssStyle="color:red"/></div>
								</td>	
								<td style="text-align:center;width:30%;">
									<form:input path="imuCreditoCompensazione"/>
									<div><form:errors path="imuCreditoCompensazione" cssStyle="color:red"/></div>
								</td>	
							</tr>
							<tr>
								<td rowspan="2" style="width:10%;">
									2) <spring:message code="label.tasi" />
								</td>
								<td style="text-align:center;width:30%;">
									<label><spring:message code="label.tasiCreditoPrecedenteDichiarazione" />:</label>
								</td>	
								<td style="text-align:center;width:30%;">
									<label><spring:message code="label.tasiCreditoRimborso" />:</label>
								</td>	
								<td style="text-align:center;width:30%;">
									<label><spring:message code="label.tasiCreditoCompensazione" />:</label>
								</td>	
							</tr>
							<tr>
								<td style="text-align:center;width:30%;">
									<form:input path="tasiCreditoPrecedenteDichiarazione"/>
									<div><form:errors path="tasiCreditoPrecedenteDichiarazione" cssStyle="color:red"/></div>
								</td>	
								<td style="text-align:center;width:30%;">
									<form:input path="tasiCreditoRimborso"/>
									<div><form:errors path="tasiCreditoRimborso" cssStyle="color:red"/></div>
								</td>	
								<td style="text-align:center;width:30%;">
									<form:input path="tasiCreditoCompensazione"/>
									<div><form:errors path="tasiCreditoCompensazione" cssStyle="color:red"/></div>
								</td>	
							</tr>
						</table>
					</fieldset> --%>
					
					<%@ include file="../common/footer.jsp"%>
						
					<div class="container_pulsante">
						<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
					</div>
				</c:if>
				<c:if test="${!empty download}">
					<p>
						<spring:message code="label.servizio.option1.part1" /><spring:message code="link.dichiarazione" /><spring:message code="label.servizio.option1.part2" /><br>
						<spring:message code="label.servizio.option2.part1" />
						<ul>
							<li><spring:message code="label.servizio.option2.part2" /></li>
							<li><spring:message code="label.servizio.option2.part3" /></li>
						</ul>
					</p>
					<div class="container_pulsante">
						<a class="custom_pulsante" href="${dichiarazioneReportURL}">
							<spring:message code="link.dichiarazione" />
						</a>
						<span class="spacer"></span>
						<a class="custom_pulsante" href="<portlet:renderURL portletMode="view"/>"><spring:message	code="button.home" /></a>
					</div>
				</c:if>
			</form:form>
			<c:remove var="i" scope="page" />
			<c:remove var="j" scope="page" />
		</c:when>
		<c:otherwise>
			<div>
				<spring:message code="errore.pdds.codice3"/>
			</div>								
		</c:otherwise>
	</c:choose>
</div>