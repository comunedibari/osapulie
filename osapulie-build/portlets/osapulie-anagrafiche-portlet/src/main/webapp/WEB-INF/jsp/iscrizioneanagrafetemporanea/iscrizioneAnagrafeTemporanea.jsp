<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
</script>

<portlet:actionURL var="iscrizioneUrlGenera">
	<portlet:param name="ope" value="generaIscrizione" />
</portlet:actionURL>

<portlet:resourceURL var="iscrizioneReportURL"
	id="iscrizioneAnagrafeTemporaneaReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="iscrizioneAnagrafeTemporaneaForm" />

<div class="mainDiv iscrizioneAlboPresidenti">
	<form:form id="${idForm}" action="${iscrizioneUrlGenera}" method="post" commandName="datiIscrizione">
		<c:if test="${empty download}">
			<table class="genericTable">
				<tr>
			   		<td width="25%">
			   			<label><spring:message code="label.cognome" />*:</label>
			   		</td>
			   		<td width="25%">
		        		<form:input path="cognome" /> 
			   			<div><form:errors path="cognome" cssStyle="color:red"/></div>
			   		</td>
			    	<td width="25%">
						<label><spring:message code="label.nome" />*:</label>
			   		</td>
			   		<td width="25%">
		        		<form:input path="nome" /> 
						<div><form:errors path="nome" cssStyle="color:red"/></div>
			   		</td>
			  	</tr>
			   	<tr>
			   		<td>
			   			<label><spring:message code="label.cf" />*:</label>
			   		</td>
			   		<td>
			   	 		<form:input path="codiceFiscale" /> 
			   	 		<div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>
			    	</td>
			    	<td>
			    		<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
			   		</td>
			   		<td>
						<form:input path="dataNascita" id="dataNascitaDP" size="10" cssClass="data_input"/>
						<div><form:errors path="dataNascita" cssStyle="color:red"/></div>
			   		</td>
			  	</tr>
			   	<tr>
			   		<td>
			   			<label><spring:message code="label.comN" />*:</label>
			   		</td>
			   		<td>
			   			<form:input path="comuneNascita" />
						<div><form:errors path="comuneNascita" cssStyle="color:red"/></div>	
					</td>
			    	<td>
			     		<label><spring:message code="label.provN" />*</label>
			   		</td>
			   		<td>
			   			<form:input path="provinciaNascita" size="3"/>
			   			<div><form:errors path="provinciaNascita" cssStyle="color:red"/></div> 
					</td>
		  		</tr>
			   <tr>
			   		<td>
						<label><spring:message code="label.cittadinanza" />*:</label>
			   		</td>
			   		<td>
						<form:input path="cittadinanza" />
						<div><form:errors path="cittadinanza" cssStyle="color:red"/></div>
				 	</td>
			    	<td>
						<label><spring:message code="label.comuneIscrizione" />*:</label>
			   		</td>
			   		<td>
						<form:input path="comuneIscrizione" />
						<div><form:errors path="comuneIscrizione" cssStyle="color:red"/></div>
			   		</td>
			  	</tr>
			  	<tr>
			    	<td>
						<label><spring:message code="label.indRes" />*:</label>
			   		</td>
			   		<td colspan="3">
						<form:input path="indirizzoResidenza" />
						<div><form:errors path="indirizzoResidenza" cssStyle="color:red"/></div>
			   		</td>
			  	</tr>
			  	<tr>
			  		<td>
			  			<label><spring:message code="label.statoEstero" />:</label>
			  		</td>
			   		<td>
			   			<form:input path="statoEstero" />
						<div><form:errors path="statoEstero" cssStyle="color:red"/></div>
					 </td>
			   		<td>
			   			<label><spring:message code="label.indirizzoEstero" />:</label>
			  		</td>
				   <td>
				   		<form:input path="indirizzoEstero" />
				   		<div><form:errors path="indirizzoEstero" cssStyle="color:red"/></div>
				  </td>
				</tr>
			</table>
			
			<fieldset>
				<legend>
					<spring:message code="label.legend" />
				</legend>
				<table class="genericTable">
					<tr>
						<td width="25%">
							<label><spring:message code="label.nuovoIndir" />*:</label>
						</td>
						<td width="25%">
							<form:input path="nuovaVia" />
							<div><form:errors path="nuovaVia" cssStyle="color:red"/></div>
						</td>
						<td>
							<label><spring:message code="label.numCiv" />*:</label>
						</td>
						<td>
							<form:input path="nuovoNum" size="3"/>
							<div><form:errors path="nuovoNum" cssStyle="color:red"/></div>
						</td>
						<td>
							<label><spring:message code="label.interno" />:</label>
						</td>
						<td>
							<form:input path="nuovoInterno" size="3"/>
							<div><form:errors path="nuovoInterno" cssStyle="color:red"/></div>
						</td>
					</tr>
				</table>
			</fieldset>
			
			<fieldset>
				<legend>
					<spring:message code="label.legendStranieri" />
				</legend>
				<table class="genericTable">
			 		<tr>
			  			<td width="25%">
			  				<label><spring:message code="label.questura" />:</label>
			  			</td>
			   			<td colspan="3">
			   				<form:input path="questura" />
			   				<div><form:errors path="questura" cssStyle="color:red"/></div>
						</td>
					</tr>
			  		<tr>
				  		<td width="25%">
				  			<label><spring:message code="label.datRil" />&nbsp;(dd/mm/yyyy):</label>
				  		</td>
				   		<td width="25%">
					    	<form:input path="dataRilascio" id="dataRilascioDP" size="10" cssClass="data_input"/>
							<div><form:errors path="dataRilascio" cssStyle="color:red"/></div>
				   		</td>
			    		<td width="25%">
				     		<label><spring:message code="label.numSogg" />:</label>
				    	</td>
				   		<td width="25%">
				   			<form:input path="numSogg" />
							<div><form:errors path="numSogg" cssStyle="color:red"/></div>
				   		</td>
			 		</tr>
				</table>
			</fieldset>

			<fieldset>
				<legend>
					<spring:message code="label.parente" />
				</legend>
				<c:forEach var="i" begin="1" end="7" step="1">
					<table class="genericTable" >
				 		<tr>
				  			<td width="25%">
				 				<label><spring:message code="label.cognome" />&nbsp;<spring:message code="label.nome" />&nbsp;:</label>
				  			</td>
						  	<td width="25%">
								<form:input path="parente${i}" />
						  	</td>
				  			<td width="25%">
				 				<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy):</label>
				  			</td>
				  			<td width="25%">
   				  				<form:input path="data${i}" cssClass="data_input" size="10"/>
								<div><form:errors path="data${i}" cssStyle="color:red"/></div>
				  			</td>
				 		</tr>
				  		<tr>
				  			<td width="25%">
				  				<label><spring:message code="label.comN" />:</label>
				  			</td>
				  			<td width="25%">
				  				<form:input path="comune${i}" />
								<div><form:errors path="comune${i}" cssStyle="color:red"/></div>
				  			</td>
				  			<td width="25%">
				  				<label><spring:message code="label.parentela" />:</label>
				  			</td>
				  			<td width="25%">
				  				<form:input path="parentela${i}" /> <strong><em><form:errors path="parentela${i}" cssStyle="color:red"/></em></strong>
				  			</td>
				 		</tr>
					</table>
				</c:forEach>
				
				<%@ include file="../common/footer.jsp"%>
				
				<div class="container_pulsante">
					<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
				</div>	
			</fieldset>
			
		</c:if>
		
		<c:if test="${!empty download}">
			<div class="container_pulsante">
				<a class="custom_pulsante" href="${iscrizioneReportURL}"><spring:message code="link.dichiarazione" /></a>
				<span class="spacer"></span>
				<a class="custom_pulsante" href="<portlet:renderURL portletMode="view"/>"><spring:message code="button.home" /></a>
			</div>	
		</c:if>
		
	</form:form>
</div>