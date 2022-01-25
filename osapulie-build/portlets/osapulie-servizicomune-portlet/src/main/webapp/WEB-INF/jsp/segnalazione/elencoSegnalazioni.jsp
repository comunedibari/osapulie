<%@page import="it.osapulie.shared.service.SegnalazioneFoglia"%>
<%@page import="it.osapulie.shared.service.Segnalazione"%>
<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="confermaSegnalazioneUrl">
		<portlet:param name="action" value="confermaSegnalazione" />		
</portlet:actionURL>

<portlet:renderURL var="homeUrl" />

<c:if test="${empty errore}">
	<div class="mainDiv">
		<form:form id="segnalazioniForm" action="${confermaSegnalazioneUrl}" commandName="datiSegnalazione" method="post">
			<div class="marginBottom5">
				<span><spring:message code="label.segnalazione.intestazione" /></span>&nbsp;
				<span><c:out value="${profiloUtente.cognome}"/></span>&nbsp;
				<span><c:out value="${profiloUtente.nome}"/></span>,
			</div>
			<div class="marginBottom5">
				<span><spring:message code="label.segnalazione.riferimento.visura" /></span>&nbsp;
				<b><c:out value="${segnalazione.servizio}"/></b>&nbsp;
				<span><spring:message code="label.segnalazione.riferimento.testo" /></span>
			</div>
			<%
				if(request.getAttribute("segnalazione") != null){
			%>
			<table summary="Elenco segnalazioni" id="elencoSegnalazioni" class="elencoRisultati">
				<tr>
					<th><spring:message code="label.check"/></th>
					<th><spring:message code="label.chiave"/></th>
					<th width="170px"><spring:message code="label.valore_old"/></th>
					<th width="130px"><spring:message code="label.valore_new"/></th>
				</tr>
				<tbody> 
				<%
					Segnalazione segnalazione = (Segnalazione) request.getAttribute("segnalazione");
					
					int indice = 0;
					for(SegnalazioneFoglia foglia :segnalazione.getListaSegnalazioni()){
						request.setAttribute("indice", indice);
						request.setAttribute("rigaSegnalazione_"+indice, foglia);
					%>
						<jsp:include page="rigoElencoSegnalazioni.jsp"/>
					<%	
						indice++;
					}
				%>
				</tbody>
			</table>
			<%
				}
			%>	
			
			<table class="genericTable">
				<tr>
					<td>
						<label><spring:message code="label.segnalazione.commento" />*:</label>
					</td>
				</tr>
				<tr>
					<td>
						<textarea cols="100" rows="5" id="commento_segnalazione" name="commento_segnalazione">${segnalazione.note}</textarea>
					</td>
				</tr>
			</table>
			
			<div class="container_pulsante">
				<input type="submit" name="verifica" value="<spring:message code="button.verifica"/>"/><br/>
			</div>
		</form:form>
	</div>
</c:if>
<script type="text/javascript">
		
		jQuery().ready(controllo());
		
		function controllo()
		{
			jQuery.each(jQuery('.inputTextNewValue'), function(key, value) {
				var indice= value.id.split("valore_new_");
				if (jQuery("#check_"+indice[1]).is(':checked')) {
					jQuery("#" + value.id).removeAttr('disabled');
			    } else {
			    	jQuery("#" + value.id).attr('disabled', true);
			    }
				});

		}
		
		function toggleStatus(id,idInput) {
			
			if (jQuery(id).is(':checked')) {
				jQuery(idInput).removeAttr('disabled');
		        
		    } else {
		    	jQuery(idInput).attr('disabled', true);
		    }

		}
		</script>