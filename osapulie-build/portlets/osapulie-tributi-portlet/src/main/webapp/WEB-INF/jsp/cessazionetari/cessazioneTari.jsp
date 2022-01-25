<%@ include file="../common/common.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
</script>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL" id="cessazioneTariReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<div class="mainDiv cessazioneTari">
	<c:choose>
		<c:when test="${!empty datiTari}">
			<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiTari">
				<c:if test="${empty download}">
					<table class="genericTable">
						<tr>
							<td width="180">
								<label>
									<spring:message code="label.tipoUtenza" />*:
								</label> 
							</td>
							<td>
								<form:select id="tipoUtenzaSelect" path="tipoUtenza">
								   <form:option value="domestica" label="Domestica"/>
								   <form:option value="non_domestica" label="Non domestica"/>
								</form:select>
								<div><form:errors path="tipoUtenza" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.cognome" />*:</label>
							</td>
							<td width="180">
								<form:input path="cognome" />
								<div><form:errors path="cognome" cssStyle="color:red"/></div>
							</td>
							<td width="180">
								<label><spring:message	code="label.nome" />*:</label>
							</td>
							<td colspan="3">
								<form:input path="nome" />
								<div><form:errors path="nome" cssStyle="color:red"/></div>
							</td>
						</tr>	
						<tr>
							<td width="180">
								<label><spring:message code="label.cf" />*:</label>
							</td>
							<td colspan="5">
								<form:input path="codiceFiscale" />
								<div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
							</td>
							<td width="180">
								<form:input path="dataNascita" size="10" id="data_nascita_input" cssClass="data_input"/>
								<div><form:errors path="dataNascita" cssStyle="color:red"/></div>
							</td>
							<td width="180">
								<label><spring:message code="label.comN" />*:</label>
							</td>
							<td width="180">
								<form:input path="comuneNascita" />
								<div><form:errors path="comuneNascita" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message code="label.prov" />*</label>
							</td>
							<td>
								<form:input path="provinciaNascita" size="3"/>
								<div><form:errors path="provinciaNascita" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.indir" />*:</label>
							</td>
							<td width="180">
								<form:input path="indirizzoResidenza" />
								<div><form:errors path="indirizzoResidenza" cssStyle="color:red"/></div>
							</td>	
							<td width="180">
								<label><spring:message code="label.comuneIscrizione" />*:</label>
							</td>
							<td width="180">
								<form:input path="comuneResidenza" />
								<div><form:errors path="comuneResidenza" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message code="label.prov" />*:</label>
							</td>
							<td>
								<form:input path="provResidenza" size="3"/>
								<div><form:errors path="provResidenza" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message	code="label.inQualitaDi" />:</label>
							</td>
							<td width="180">
								<form:input path="ruolo" />
								<div><form:errors path="ruolo" cssStyle="color:red"/></div> 
							</td>
							<td width="180">
								<label><spring:message	code="label.societa" />:</label>
							</td>
							<td width="180">
								<form:input path="nomeSocieta" />
								<div><form:errors path="nomeSocieta" cssStyle="color:red"/></div> 
							</td>
							<td width="180">
								<label><spring:message	code="label.comuneSedeLegale" />:</label>
							</td>
							<td width="180">
								<form:select id="cittaSedeLegale" path="cittaSedeLegale">
									<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
									<form:options items="${comuniList}" itemLabel="denominazione" itemValue="codiceIstat1"  />
								</form:select>
								<div><form:errors path="cittaSedeLegale" cssStyle="color:red"/></div> 
							</td>		
						</tr>
						<tr>
							<td width="180">
								<label><spring:message	code="label.viaSedeLegale" />:</label>
							</td>
							<td width="180">
								<form:input path="viaSedeLegale" />
								<div><form:errors path="viaSedeLegale" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.numeroSedeLegale" />:</label>
							</td>
							<td>
								<form:input path="civicoSedeLegale" />
								<div><form:errors path="civicoSedeLegale" cssStyle="color:red"/></div> 
							</td>
							<td width="180">
								<label><spring:message	code="label.cap" />:</label>
							</td>
							<td width="180">
								<form:input path="capSedeLegale" /> <div><form:errors path="capSedeLegale" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message	code="label.partitaIva" />:</label>
							</td>
							<td width="180">
								<form:input path="partitaIva" />
								<div><form:errors path="partitaIva" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.telefono" />*:</label>
							</td>
							<td width="180">
								<form:input path="telefono" />
								<div><form:errors path="telefono" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message code="label.fax" />:</label>
							</td>
							<td>
								<form:input path="fax" />
								<div><form:errors path="fax" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.email" />*:</label>
							</td>
							<td>
								<form:input path="email" />
								<div><form:errors path="email" cssStyle="color:red"/></div> 
							</td>
							<td width="180">
								<label><spring:message	code="label.pec" />:</label>
							</td>
							<td width="180">
								<form:input path="pec" />
								<div><form:errors path="pec" cssStyle="color:red"/></div> 
							</td>
						</tr>
					</table>
						 <form:checkbox id="chk_delega" path="flagDelega"/><label><spring:message code="check.delega" /></label>
						 <div id="dati_delegato" style="display:none;">
						 	<label><spring:message code="label.nominativo.delegato" />*:</label>
							<form:input path="nominativoDelegato" />
							<div><form:errors path="nominativoDelegato" cssStyle="color:red"/></div> 
						 </div>
					 <br>
					 <br>
					<%-- Immobile di riferimento --%>
					<fieldset>
						<legend><spring:message code="legend.unitaImmobiliare" /></legend>
						<label><spring:message code="msg.unitaImmobiliare" /></label>
						<table>
							<tr>
								<td>
									<label><spring:message code="label.indir" />*:</label>
								</td>
								<td width="180">
									<form:input path="viaUnitaImmobiliare" />
									<div><form:errors path="viaUnitaImmobiliare" cssStyle="color:red"/></div> 
								</td>
								<td>
									<label><spring:message code="label.civico" />*:</label>
								</td>
								<td width="180">
									<form:input path="civicoUnitaImmobiliare" />
									<div><form:errors path="civicoUnitaImmobiliare" cssStyle="color:red"/></div> 
								</td>
							</tr>
							<tr>
								<td>
									<label><spring:message code="label.datiCatastali" /></label>
								</td>
							</tr>
							<tr>	
								<td>
									<label><spring:message code="label.catastali.fg" /></label>
								</td>
								<td>
									<form:input path="fgUnitaImmobiliare" />
									<div><form:errors path="fgUnitaImmobiliare" cssStyle="color:red"/></div> 								
								</td>
								<td>
									<label><spring:message code="label.catastali.num" /></label>
								</td>
								<td>
									<form:input path="numUnitaImmobiliare" />
									<div><form:errors path="numUnitaImmobiliare" cssStyle="color:red"/></div> 								
								</td>
								<td>
									<label><spring:message code="label.catastali.sub" /></label>
								</td>
								<td>
									<form:input path="subUnitaImmobiliare" />
									<div><form:errors path="subUnitaImmobiliare" cssStyle="color:red"/></div> 								
								</td>
							</tr>
						</table>
					</fieldset>
					<%-- Motivazioni --%>
					<fieldset>
						<legend><spring:message code="legend.motivazioni" /></legend>
						<div><form:errors path="motivi" cssStyle="color:red"/></div> 
						<table class="genericTable">
							<tr>
								<td>
								 <div id="div_decesso">
									<form:checkbox id="chk_decesso" path="motivi" value="decesso"/><spring:message code="check.decesso" />
									<div id="div_deceduto" style="display:none">
										<label><spring:message	code="label.personaDeceduta" />*:</label>&nbsp;
										<form:input path="nominativoDeceduto" />&nbsp;
										<label><spring:message	code="label.dataDecesso" />*:</label>&nbsp;
										<form:input path="dataDecesso" id="data_decesso" size="5" cssClass="data_input"/>&nbsp;
									</div>
								</div>	
								<div id="div_cessazione" style="display:none;">
									<form:checkbox id="chk_cessazione" path="motivi" value="cessazione"/><spring:message code="check.cessazione" />
									<div id="div_cessato" style="display:none">
										<label><spring:message	code="label.attivitaCessata" />*:</label>&nbsp;
										<form:input path="attivitaCessata" />&nbsp;
									</div>
								</div>
								</td>
							</tr>
							<tr>
								<td>
									<form:checkbox id="chk_duplicazione" path="motivi" value="duplicazione"/><spring:message code="check.duplicazione" />
									<div id="div_duplicazione" style="display:none;">
										<label><spring:message code="label.soggetto" />*:</label>&nbsp;
										<form:input path="soggettoDuplicazione" />&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td>
								    <form:checkbox id="chk_trasferimento" path="motivi" value="trasferimento"/><spring:message code="check.trasferimento" />
									<div id="div_trasferimento" style="display:none;">
										<label><spring:message	code="label.comune" />*:</label>&nbsp;
										<form:input path="comuneEmigrazione" />&nbsp;
										<label><spring:message	code="label.prov" />*:</label>&nbsp;
										<form:input path="provEmigrazione" size="3" />&nbsp;
										<label><spring:message	code="label.via" />*:</label>&nbsp;
										<form:input path="viaEmigrazione" />&nbsp;
										<label><spring:message	code="label.num" />*:</label>&nbsp;
										<form:input path="civicoEmigrazione" size="2" />&nbsp;
									</div>
								</td>
							</tr>
							<tr>
							   <td>
								  <form:checkbox path="motivi" value="concessione"/><spring:message code="check.immobile.concessione" />
							  </td>
							</tr>
							<tr>
							   <td>
									<form:checkbox path="motivi" value="restituito"/><spring:message code="check.immobile.restituzione" />
								</td>
							</tr>
							<tr>
							   <td>
								<form:checkbox path="motivi" value="vuoto"/><spring:message code="check.immobile.vuoto" />
								</td>
							</tr>
							<tr>
							  <td>
								<form:checkbox path="motivi" value="venduto"/><spring:message code="check.immobile.venduto" />
							  </td>
							</tr>
							<tr>
								<td>
									<form:checkbox id="chk_altro_motivo" path="motivi" value="altro"/><spring:message code="check.altro" />
									<div id="div_altro_motivo" style="display:none;">
										<form:textarea path="altriMotivi" rows="3" cols="100"/>
									</div>
								</td>
							</tr>
						</table>
					</fieldset>
				    
				    <%-- Allegati --%>
					<fieldset>
						<legend><spring:message code="legend.allegati" /></legend>
						<table>
							<tr>
								<td>
							    	<form:checkbox path="allegati" value="avviso_pagamento"/><spring:message code="check.avvisoPagamento" />
								</td>
							</tr>
							<tr>
								<td>
									<form:checkbox path="allegati" value="documento_identita"/><spring:message code="check.documentoIdentita" />
								</td>
							</tr>
							<tr>
								<td>
								<form:checkbox id="chk_allegati" path="allegati" value="altro"/><spring:message code="check.altro" />
									<div id="div_altro_allegato" style="display:none;">
										<form:textarea path="altriAllegati" rows="3" cols="100"/>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label><spring:message	code="label.estremiDocumento" />:</label>&nbsp;
									<form:input path="estremiDocumento" />&nbsp;
								</td>
							</tr>
						</table>
						
					</fieldset>
					<%@ include file="../common/footer.jsp"%>
					<div class="container_pulsante">
						<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
					</div>
				</c:if>
				
				<c:if test="${!empty download}">
					<div class="container_pulsante">
						<a href="${dichiarazioneReportURL}" class="custom_pulsante">
							<spring:message code="link.dichiarazione" />
						</a>
					</div>
					<p style="text-align: center;">
						<a href="<portlet:renderURL portletMode="view"/>">- <spring:message code="button.home" /> -</a>
					</p>
				</c:if>
			</form:form>
		</c:when>
		<c:otherwise>
			<div>
				<strong><spring:message code="errore.pdds.codice3"/></strong>
			</div>								
		</c:otherwise>
	</c:choose>
</div>

<script type="text/javascript">

	$(document).ready(function(){
		
		//TODO manca il controllo sul tipo utenza
		
		if($('#chk_trasferimento').is(':checked')){
			$("#div_trasferimento").show();
		};
		if($('#chk_delega').is(':checked')){
			$("#dati_delegato").show();
		};
		if($('#chk_decesso').is(':checked')){
			$("#div_deceduto").show();
		};
		if($('#chk_cessazione').is(':checked')){
			$("#div_cessato").show();
		};
		if($('#chk_duplicazione').is(':checked')){
			$("#div_duplicazione").show();
		};
		if($('#chk_altro_motivo').is(':checked')){
			$("#div_altro_motivo").show();
		};
		if($('#chk_allegati').is(':checked')){
			$("#div_altro_allegato").show();
		};
		
		$( "#tipoUtenzaSelect" ).change(function() {
			  var flag = $(this).val();
			  console.log(flag);
			  if(flag=='domestica'){
				  $("#div_decesso").show();
				  $("#div_cessazione").hide();
			  }else{
				  $("#div_cessazione").show();
				  $("#div_decesso").hide();
			  };
			});
		
		$('#chk_trasferimento').click(function() {
		    var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("#div_trasferimento").show();
		    } else {
		    	$("#div_trasferimento").hide();
		    };
		});
		
		$('#chk_delega').click(function() {
		    var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("#dati_delegato").show();
		    } else {
		    	$("#dati_delegato").hide();
		    };
		});
		
		$('#chk_decesso').click(function() {
		    var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("#div_deceduto").show();
		    } else {
		    	$("#div_deceduto").hide();
		    };
		});
		
		$('#chk_cessazione').click(function() {
		    var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("#div_cessato").show();
		    } else {
		    	$("#div_cessato").hide();
		    };
		});
		
		$('#chk_duplicazione').click(function() {
		    var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("#div_duplicazione").show();
		    } else {
		    	$("#div_duplicazione").hide();
		    };
		});
		
		$('#chk_altro_motivo').click(function() {
		    var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("#div_altro_motivo").show();
		    } else {
		    	$("#div_altro_motivo").hide();
		    };
		});
		
		$('#chk_allegati').click(function() {
		    var $this = $(this);
		    if ($this.is(':checked')) {
		    	$("#div_altro_allegato").show();
		    } else {
		    	$("#div_altro_allegato").hide();
		    };
		});
	});
	
</script>