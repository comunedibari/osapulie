<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="saveAziendaUrl">
	<portlet:param name="action" value="saveAzienda" />
</portlet:actionURL>
<div class="mainDiv editAzienda">
	<fieldset>
		<legend><spring:message code="label.editAzienda"/></legend>
	 	<form:form action="${saveAziendaUrl}" modelAttribute="aziendaEditForm" method="post">
	 		<form:hidden path="idAzienda" />
		 	<table class="genericTable">
		 		<tr>
		 			<td width="250">
		 				<spring:message code="label.partitaIvaCf"/>*:
		 			</td>
		 			<td>
		 				<form:input path="partitaIva"/>
		 				<form:errors path="partitaIva" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.ragioneSociale"/>*:
		 			</td>
		 			<td>
		 				<form:input path="ragioneSociale"/>
 						<form:errors path="ragioneSociale" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.sede"/>:
		 			</td>
		 			<td>
		 				<form:input path="viaSede"/>
 						<form:errors path="viaSede" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.sede.nCivicosede"/>:
		 			</td>
		 			<td>
		 				<form:input path="nrCivicoSede"/>
 						<form:errors path="nrCivicoSede" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.sede.comune"/>*:
		 			</td>
		 			<td>
		 				<form:select path="comuneSede" id="selectComuneSede">
							<form:option value="0"><spring:message code="label.seleziona"/></form:option>
							<form:options items="${comuneList}" itemLabel="denominazione" itemValue="id"/>
						</form:select>
		 				<form:errors path="comuneSede" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.sede.mailPec"/>*:
		 			</td>
		 			<td>
		 				<form:input path="mailPec"/>
		 				<form:errors path="mailPec" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.sede.mail"/>:
		 			</td>
		 			<td>
		 				<form:input path="mail"/>
		 				<form:errors path="mail" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.tipo"/>*:
		 			</td>
		 			<td>
		 				<form:select path="tipo" id="selectTipoAzienda" onchange="checkOperatore(this)">
							<form:option value=""><spring:message code="label.seleziona"/></form:option>
							<!--<form:options items="${tipiAzienda}" itemLabel="tipo" itemValue="tipo"/>-->
							<form:option value="CAF">CAF</form:option>
							<form:option value="ENTE INTERMEDIARIO">ENTE INTERMEDIARIO</form:option>
							<form:option value="PROFESSIONISTA">Professionista</form:option>
						</form:select>
		 				<form:errors path="tipo" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.sede.tipo.ente"/>*:
		 			</td>
		 			<td>
		 				<form:select path="tipoEnte" id="selectTipoEnte">
                            <form:option value="Non indicato">Non indicato</form:option>
							<form:option value="Edicolante">Edicolante</form:option>
							<form:option value="Tabaccaio">Tabaccaio</form:option>
							<form:option value="Altro">Altro</form:option>
						</form:select>
		 			</td>
		 		</tr>
		 		<tr id="aggOperatori">
		 			<td>
		 				<label><spring:message code="label.agg.operatori"/>:</label>
		 			</td>
		 			<td>
		 				<form:checkbox path="aggOperatori" id="checkAggOp" onclick="divUtentiAssociati()"/>
		 			</td>
		 		<tr>
		 			<td>
		 				<spring:message code="label.codiceFiscaleResponsabile"/>:
		 			</td>
		 			<td>
		 				<form:input path="codiceFiscaleResponsabile"/>
		 				<form:errors path="codiceFiscaleResponsabile" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
					<td>
						<label><spring:message code="label.attiva" />:</label>
					</td>
					<td>
						<form:checkbox path="attiva"/>
					</td>
				</tr>
		 	</table>
 
			<div class="cfUtenteAssociatoDiv" id="cfUtenteAssociatoDiv">
				<form:input path="cfUtenteAssociato"/>
				<button type="submit" class="custom_pulsante" name="cfUtenteAssociatoButton">
					<spring:message code="button.aggiungi" />
				</button>
 				<form:errors path="cfUtenteAssociato" cssStyle="color:red"/>
			</div>
		 	<!-- Lista utenti associati a professionista/azienda -->
	 		<fieldset id="utentiAssociatiFieldset">
				<legend><spring:message code="label.associaUtente"/></legend>
				<portlet:renderURL var="editAziendaUrl">
					<portlet:param name="action" value="editAzienda" />
					<portlet:param name="idAzienda" value="${aziendaEditForm.idAzienda}" />
				</portlet:renderURL>
				
				<!-- Ricerca -->
				<div class="searchDiv">
					<table>	
						<tr>
							<td>
								<spring:message code="label.codiceFiscale"/>:&nbsp;<form:input path="codiceFiscaleDelegato"/>
							</td>
							<td>
								<spring:message code="label.nome"/>:&nbsp;<form:input path="nomeDelegato"/>
							</td>
							<td>
								<spring:message code="label.cognome"/>:&nbsp;<form:input path="cognomeDelegato"/>
							</td>
						</tr>
					</table>
					<div class="container_pulsante">
						<input type="submit" value="<spring:message code="button.cerca" />" name="ricercaDelegato"/>
					</div>
				</div>
				
				<display:table id="paginatedTable" 
					requestURI="${editAziendaUrl}"
					name="aziendaEditForm.profiliUtenteAssociatiSearch"
					uid="profiloUtente" 
					cellpadding="2" 
					cellspacing="0" 
					pagesize="20" 
					defaultorder="ascending" 
					defaultsort="1" 
					sort="list" 
					class="genericTable elencoRisultati">
					<display:column sortable="true" property="codiceFiscale" titleKey="label.codiceFiscale" />
					<display:column sortable="true" property="nome" titleKey="label.nome" />
					<display:column sortable="true" property="cognome" titleKey="label.cognome" />
					<display:column sortable="false" class="center" headerClass="center">
						<portlet:actionURL var="delProfiloUtenteUrl">
							<portlet:param name="action" value="delProfiloUtente" />
							<portlet:param name="idProfiloUtente" value="${profiloUtente.id}" />
						</portlet:actionURL>
				   		<a href="${delProfiloUtenteUrl}" title="<spring:message code="button.delete" />" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
				   			<spring:message code="button.delete" />
				   		</a>
					</display:column>
				</display:table>
			</fieldset>
 
		 	 
		 	 
		 	<%@ include file="../common/footer.jsp"%>
		 	<div class="container_pulsante">
				<input type="submit" value="<spring:message code="button.salva"/>" onclick="javascript:return(confirm('<spring:message code="message.confirm"/>'))">
				<span class="spacer"></span>
				<a class="custom_pulsante" href="${home}">
					<spring:message code="button.annulla" />
				</a>
			</div>
	 	</form:form>
 	</fieldset>
 	
 	<script type="text/javascript">
		$(document).ready(function(){
    		checkOperatore($("#selectTipoAzienda"));
		});
	</script>

	<script type="text/javascript">
		function checkOperatore(a){
			if(a){
				if($(a).val()!="ENTE INTERMEDIARIO")
	    		{
					$("#aggOperatori").hide()
				}
				else{
					$("#aggOperatori").show()
				}
	
			}	
			divUtentiAssociati();
		}


		function divUtentiAssociati(){
			if($("#selectTipoAzienda").val()=="ENTE INTERMEDIARIO" && !$("#checkAggOp").prop('checked')){
				$("#cfUtenteAssociatoDiv").hide();
				$("#utentiAssociatiFieldset").hide();
			}
			else{
				$("#cfUtenteAssociatoDiv").show();
				$("#utentiAssociatiFieldset").show();
			}
		}
	</script>
 	
</div>