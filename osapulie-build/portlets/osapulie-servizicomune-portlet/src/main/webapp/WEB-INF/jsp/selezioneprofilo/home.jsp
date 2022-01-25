<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="updateComuneSelezionato">
	<portlet:param name="action" value="updateComuneSelezionato" />		
</portlet:actionURL>
<portlet:actionURL var="updateProfiloSelezionato">
	<portlet:param name="action" value="updateProfiloSelezionato" />		
</portlet:actionURL>
<script type="text/javascript">
$(document).ready(function(){
	$('.${renderResponse.namespace}select').select2({
		theme: "classic",
	  	language: "it"
	});
});
</script>
<div class="mainDiv">
	<c:choose>
		<c:when test="${comuniList != null}">
			<fieldset>
				<legend><spring:message code="label.selezioneComune"/></legend>	
				<form:form id="updateComuneSelezionatoForm" commandName="comuneSelezionato" method="post" action="${updateComuneSelezionato}">
					<table class="genericTable">
						<tr>
							<td>
								<spring:message code="label.scegliComune"/>:
							</td>
							<td>
								<form:select cssClass="${renderResponse.namespace}select" path="codiceIstat">
									<form:option value="0"><spring:message code="label.seleziona"/></form:option>
								    <form:options items="${comuniList}" itemLabel="nome" itemValue="codiceIstat"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="container_pulsante">
									<input type="submit" value="<spring:message code="button.aggiorna"/>">
								</div>
							</td>
						</tr>
					</table>
				</form:form>
			</fieldset>
			<c:if test="${!empty profiliMap && fn:length(profiliMap) gt 1}">
				<!-- Selezione Profilo -->
				<fieldset>
					<legend><spring:message code="label.selezioneProfilo"/></legend>	
					<form:form id="updateProfiloForm" commandName="profilo" method="post" action="${updateProfiloSelezionato}">
						<table class="genericTable">
							<tr>
								<td>
									<spring:message code="label.scegliProfilo"/>:
								</td>
								<td>
									<form:select cssClass="${renderResponse.namespace}select" multiple="single" path="valore">
						          		<c:forEach var="gruppoProfili" items="${profiliMap}" varStatus="gruppoProfiliIndex">
						           			<optgroup label="${gruppoProfili.key}">
							           			<form:options items="${gruppoProfili.value}" itemLabel="denominazione" itemValue="valore"/>        
							           		</optgroup>
						          		</c:forEach>        
							        </form:select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="container_pulsante">
										<input type="submit" value="<spring:message code="button.aggiorna"/>">
									</div>
								</td>
							</tr>
						</table>
					</form:form>
				</fieldset>
			</c:if>
		</c:when>
		<c:otherwise>
			<div><strong><spring:message code="label.utenteNonLoggato"/></strong></div>					
		</c:otherwise>
	</c:choose>
</div>