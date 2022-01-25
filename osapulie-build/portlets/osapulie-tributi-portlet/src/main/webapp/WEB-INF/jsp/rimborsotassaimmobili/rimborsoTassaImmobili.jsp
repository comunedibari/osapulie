<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoUrlGenera">
	<portlet:param name="ope" value="generaRimborso" />
</portlet:actionURL>

<portlet:resourceURL var="rimborsoReportURL"
	id="rimborsoTassaImmobiliReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="rimborsoIci" />
<c:set var="dati" value="${datiRimbTassaImmobili}" />

<div class="mainDiv">
	<c:choose>
		<c:when test="${!empty datiRimbTassaImmobili}">
			<form:form id="${idForm}" action="${rimborsoUrlGenera}" method="post" commandName="datiRimbTassaImmobili">
				<c:if test="${empty download}">
					<%@ include file="../common/rimborsiDatiAnagrafici.jsp"%>				
					<fieldset>
						<legend>
							<spring:message code="label.legend" />
						</legend>
						<div class="marginBottom10">
							<label><spring:message code="label.descrizioneTassa" />*:</label>&nbsp;&nbsp;
							<form:select path="descrizioneTassa">
							   <form:option value=" " label=" "/>
							   <form:option value="ICI" label="ICI"/>
							   <form:option value="IMU" label="IMU"/>
							   <form:option value="TASI" label="TASI"/>
							</form:select>
							<div><form:errors path="descrizioneTassa" cssStyle="color:red"/></div>
						</div>
						<div class="marginBottom10">
							<label><spring:message code="label.anno" /></label>&nbsp;&nbsp;
							<form:select path="anno">
								<c:forEach var="i" begin="${annoCorrente-4}" end="${annoCorrente}" step="1">
							    	<option value="${i}" <c:if test="${i == datiRimbTassaImmobili.anno}"> selected="selected" </c:if> >${i}</option>
								</c:forEach>
							</form:select>
						</div>
						<table>
								<tr>
									<td>
										<form:textarea cssClass="textarea" path="motivo" cols="100" rows="5"/>  
										<div><form:errors path="motivo" cssStyle="color:red"/></div>
									</td>
								</tr>
							</table>				
					</fieldset>
									
					<fieldset>
						<legend>
							<spring:message code="label.legendPagamento" />
						</legend>
						<table class="genericTable">
							<tr>
								<td>
									<label><spring:message	code="label.rimborso" />*:</label>
									<form:input path="rimborso" />
									<div><form:errors path="rimborso" cssStyle="color:red"/></div>
								</td>
							</tr>
						</table>
						<div><label><spring:message	code="label.modalita" />*:</label></div>
						<div><form:radiobutton path="modalitaRimborso" value="mandato"/>&nbsp;<spring:message code="label.mandato" /><br/></div>	
						<div><form:radiobutton path="modalitaRimborso" value="accredito"/>&nbsp;<spring:message code="label.accredito" /><br/></div>
						<div id="div_iban">
							<table>
								<tr>
									<td>
										<label><spring:message	code="label.iban" />:</label>&nbsp;
									</td>
									<td>
										<form:input path="iban" />&nbsp;
										<div><form:errors path="iban" cssStyle="color:red"/></div>
									</td>
								</tr>
							</table>
						</div>	
						<div><form:radiobutton path="modalitaRimborso" value="compensazione"/>&nbsp;<spring:message code="label.compensazione" /><br/></div>
						<div id="div_compensazione">
							<table>
								<tr>
									<td>
										<label><spring:message	code="label.tipo.imposta" />:</label>&nbsp;
									</td>
									<td>
									    <form:select path="impostaCompensazione">
										   <form:option value="ICI" label="ICI"/>
										   <form:option value="IMU" label="IMU"/>
										   <form:option value="TASI" label="TASI"/>
										</form:select>
									</td>
								</tr>
								<tr>
									<td>
										<label><spring:message	code="label.anno" />:</label>&nbsp;
									</td>
									<td>
										<form:input path="annoCompensazione" />&nbsp;
										<div><form:errors path="annoCompensazione" cssStyle="color:red"/></div>
									</td>
								</tr>
							</table>
						</div>		
						<div><form:radiobutton path="modalitaRimborso" value="riversamento"/>&nbsp;<spring:message code="label.riversamento" /><br/></div>	
						<div id="div_riversamento">
							<table>
								<tr>
									<td>
										<label><spring:message	code="label.comune" />:</label>&nbsp;
									</td>
									<td>
										<form:input path="comuneRiversamento" />&nbsp;
										<div><form:errors path="comuneRiversamento" cssStyle="color:red"/></div>
									</td>
								</tr>
								<tr>
									<td>
										<label><spring:message	code="label.anno" />:</label>&nbsp;
									</td>
									<td>
										<form:input path="annoRiversamento" />&nbsp;
										<div><form:errors path="annoRiversamento" cssStyle="color:red"/></div>
									</td>
								</tr>
							</table>
						</div>	
						<div><form:errors path="modalitaRimborso" cssStyle="color:red"/></div>
					</fieldset>
					
					<%-- Allegati --%>
					<fieldset>
						<legend><spring:message code="legend.allegati" /></legend>
						<table class="genericTable">
							<tr>
								<td width="180">
									<label><spring:message code="label.allegato.uno" /></label>
								</td>
								<td>
									<form:input path="allegatoUno" />
								</td>
								<td width="180">
									<label><spring:message code="label.allegato.due" /></label>
								</td>
								<td>
									<form:input path="allegatoDue" />
								</td>
							</tr>
							<tr>
								<td width="180">
									<label><spring:message code="label.allegato.tre" /></label>
								</td>
								<td>
									<form:input path="allegatoTre" />
								</td>
								<td width="180">
									<label><spring:message code="label.allegato.quattro" /></label>
								</td>
								<td>
									<form:input path="allegatoQuattro" />
								</td>
							</tr>
							<tr>
								<td colspan="4"><br><strong><spring:message code="label.allegati.vincolo" /></strong></td>
							</tr>
						</table>
					</fieldset>	
						
						
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
						<a href="${rimborsoReportURL}" class="custom_pulsante"><spring:message code="link.dichiarazione" /></a>
						<span class="spacer"></span>
						<a href="<portlet:renderURL portletMode="view"/>" class="custom_pulsante"><spring:message code="button.home" /></a>
					</div>
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
		
		var partitaIva = $("#pIva").val();
		var cognome = $("#cognome").val() || "";
		if(partitaIva != '' && cognome == ""){
			$("#div_personaGiuridica").show();
			$("#div_personaFisica").hide();
		}else{
			$("#div_personaGiuridica").hide();
			$("#div_personaFisica").show();
		}
		

		var modalita = "${datiRimbTassaImmobili.modalitaRimborso}";
		console.log(modalita);
		
		if(modalita == 'accredito'){
			$("#div_iban").show();
		}else{
			$("#div_iban").hide();
		}
		if(modalita == 'compensazione'){
			$("#div_compensazione").show();
		}else{
			$("#div_compensazione").hide();
		}		
		if(modalita == 'riversamento'){
			$("#div_riversamento").show();
		}else{
			$("#div_riversamento").hide();
		}
		
		$("input[name='modalitaRimborso']").change(function() {
			
			if($(this).val() == 'accredito'){
				$("#div_iban").show();
			}else{
				$("#div_iban").hide();
			}
			if($(this).val() == 'compensazione'){
				$("#div_compensazione").show();
			}else{
				$("#div_compensazione").hide();
			}		
			if($(this).val() == 'riversamento'){
				$("#div_riversamento").show();
			}else{
				$("#div_riversamento").hide();
			}
	    });
		
	});
</script>