<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoUrlGenera">
	<portlet:param name="ope" value="generaRimborso" />
</portlet:actionURL>

<portlet:resourceURL var="rimborsoReportURL"
	id="rimborsoTassaRifiutiReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="rimborsoTassaRifiuti" />
<c:set var="dati" value="${datiRimbTassaRifiuti}" />

<div class="mainDiv">
	<c:choose>
		<c:when test="${!empty datiRimbTassaRifiuti}">
			<form:form id="${idForm}" action="${rimborsoUrlGenera}"	method="post" commandName="datiRimbTassaRifiuti">
				<c:if test="${empty download}">
					<%@ include file="../common/rimborsiDatiAnagrafici.jsp"%>		
									
					<fieldset>
						<legend>
							<spring:message code="label.legend" />
						</legend>
						<c:if test="${visuraPosizioniTributarieActive}">
							<div class="marginBottom10">
								<label><spring:message	code="label.anno"/>:</label>&nbsp;&nbsp;
								<select name="anno">
									<c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
								    	<option value="${i}" <c:if test="${i == datiRimbTassaRifiuti.anno}"> selected="selected" </c:if> >${i}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;
								<input type="submit" name="cambio" value="<spring:message code="button.modAnno" />"/>
							</div>
						</c:if>
						<div class="marginBottom5">
							<label><spring:message code="label.locali" />:</label>
						</div>
						
						<table class="genericTable elencoRisultati">
							<tr>
								<th>
									<spring:message code="label.indir"/>*
								</th>
								<th>
									<spring:message code="label.sup"/>*
								</th>			
								<th>
									<spring:message code="label.dovuto"/>*
								</th>
								<th>
									<spring:message code="label.versato"/>*
								</th>		
							</tr>
							<c:forEach var="i" begin="1" end="5" step="1">
							<tr>
								<td>
									<form:input path="indirizzo${i}" size="30"/>
									<div><form:errors path="indirizzo${i}" cssStyle="color:red"/></div>
								</td>
								<td>
									<form:input path="mq${i}" size="8"/>
									<div><form:errors path="mq${i}" cssStyle="color:red"/></div> 	
								</td>	
								<td>
									<form:input path="dovuto${i}" size="8" />
									<div><form:errors path="dovuto${i}" cssStyle="color:red"/></div>
								</td>
								<td>
									<form:input path="versato${i}" size="8" />
									<div><form:errors path="versato${i}" cssStyle="color:red"/></div>
								</td>				
							</tr>
							</c:forEach>				
						</table>			
					</fieldset>
					
					<%@ include file="../common/rimborsiDatiGenerali.jsp"%>
					
					<%@ include file="../common/footer.jsp"%>
					
					<div class="container_pulsante">
						<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
					</div>
				</c:if>
					
				<c:if test="${!empty download}">
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
		var modalita = "${datiRimbTassaRifiuti.modalitaRimborso}";
		
		var partitaIva = $("#pIva").val() || ''; 
		if(partitaIva != ''){
			$("#div_personaGiuridica").show();
			$("#div_personaFisica").hide();
		}else{
			$("#div_personaGiuridica").hide();
			$("#div_personaFisica").show();
		}
		
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