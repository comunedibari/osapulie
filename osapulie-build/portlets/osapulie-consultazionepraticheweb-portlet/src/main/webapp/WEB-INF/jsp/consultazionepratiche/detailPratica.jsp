<%@ include file="../common/common.jsp"%>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioniTab.js?1234" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioni.js?1234" type="text/javascript"></script>
<script>
$(document).ready(function(){
$('input[type=file]').change(function(){
	var inputName=this.name;
	var part1=inputName.split('[');
	var part2=part1[1].split(']');
	var fileName='';
	if ($(this).val())
	{
		var split = $(this).val().split(/(\\|\/)/g);
		fileName = split[split.length-1];
	}
	if(part1[0] === "allegati"){
		if (!fileName|| 0 ===fileName.length)
			$("#allegati\\["+part2[0]+"\\]\\.nomeFile").val('');
		else
			$("#allegati\\["+part2[0]+"\\]\\.nomeFile").val(fileName);
	}else{
		if (!fileName|| 0 ===fileName.length)
			$("#campiaggiuntivi\\["+part2[0]+"\\]\\.allegatoNome").val('');
		else
			$("#campiaggiuntivi\\["+part2[0]+"\\]\\.allegatoNome").val(fileName);
	}
	formLoading('detailForm');
});
});
</script>					
<portlet:renderURL var="annullaUrl" />
<portlet:actionURL var="saveEntityUrl">
	<portlet:param name="action" value="save" />
</portlet:actionURL>
<form:form id="detailForm" commandName="datiPratica" method="post" action="${saveEntityUrl}" enctype="multipart/form-data">
<div id="tab">
    <ul>
        <li id="link_apertura" class="activelink"><a href="javascript:vis_apertura()"><spring:message code="tab.dati" /></a></li>
        <li id="link_allegato" ><a href="javascript:vis_allegato()"><spring:message code="tab.doc" /></a></li>
        <li id="link_campiaggiuntivi" ><a href="javascript:vis_campiaggiuntivi()"><spring:message code="tab.cagg" /></a></li>
        <li id="link_attori" ><a href="javascript:vis_attori()"><spring:message code="tab.attori" /></a></li>
       <!--  <li id="link_attivita" ><a href="javascript:vis_attivita()"><spring:message code="tab.attivita" /></a></li>  -->      
     </ul>
    </div>
    <div class="schede" id="apertura">
	<table class="tableDett">
		<tr>
			<th>
				<spring:message code="label.dataRichiesta" />:
			</th>
			<td>
				<fmt:formatDate value="${datiPratica.dataRichiesta.time}" pattern="dd/MM/yyyy" />	
			</td>
			<th>
				<spring:message code="label.numeroPratica" />:
			</th>
			<td>
				<c:out value="${datiPratica.numeroPratica}"/>
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="label.tipologia" />:
			</th>
			<td colspan="4">
				<c:out value="${datiPratica.tipologia.descrizione}"/>				
			</td>
			</tr>					
		<tr>
			<th>
				<spring:message code="label.oggettoRichiesta" />:
			</th>
			<td colspan="5">
				<c:out value="${datiPratica.oggettoRichiesta}"/>				
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="label.richiedente" />:
			</th>
			<td>
				<c:if test="${datiPratica.richiedente != null }">
					<div><c:out value="${datiPratica.richiedente.nominativo}"/></div>
				</c:if>				
			</td>
			
			<th>
				<spring:message code="label.stato" />:
			</th>
			<td>
				<c:out value="${datiPratica.stato.descrizione}"/>
			</td>
			
			</tr>			
			
			<tr>
			<th>
				<spring:message code="label.responsabile" />:
			</th>
			<td colspan="2">
				<c:if test="${datiPratica.referente != null }">
					<div><c:out value="${datiPratica.referente.codiceFiscale}"/></div>
				</c:if>				
			</td>			
			</tr>			
			
			<tr>
			<th>
				<spring:message code="label.dataProt" />:
			</th>
			<td>
				<fmt:formatDate value="${datiPratica.dataProtocollo.time}" pattern="dd/MM/yyyy" />
			</td>
			<th>
				<spring:message code="label.numProt" />:
			</th>
			<td>
				<c:out value="${datiPratica.numeroProtocollo}"/>				
			</td>
		</tr>
			<tr>
			<!--<c:if test="${datiPratica.tipologia.showTipoInt == 4}">-->
			<th>
				<spring:message code="label.tipointervento" />:
			</th>
			<td >
				<c:out value="${datiPratica.tipoIntervento.descrizione}"/>			
			</td>
			<!--</c:if>-->
			<!--<c:if test="${datiPratica.tipologia.showLocalita == 8}">-->
			<th>
				<spring:message code="label.immobile" />:
			</th>
			<td >
				<c:if test="${datiPratica.immobile != null }">
					<div>${datiPratica.immobile.denominazione}<br/> ${datiPratica.immobile.indirizzo}<br/>
					Foglio: ${datiPratica.immobile.foglio} - Particella: ${datiPratica.immobile.particella} - Subalterni: ${datiPratica.immobile.subalterno}
					</div>
				</c:if>				
			</td>
			<!--</c:if>-->
		</tr>
		<c:if test="${!empty datiPratica.tipologia.link && datiPratica.tipologia.link != ''}">
		<tr>
			<td colspan="6"><br/><br/><br/>
				<a href="${datiPratica.tipologia.link }" target="_blank"><b>${datiPratica.tipologia.descr_link }</b></a>
			</td></tr>
		</c:if>
		<c:if test="${!empty datiPratica.tipologia.link2 && datiPratica.tipologia.link2 != ''}">
		<tr>
			<td colspan="6">
				<a href="${datiPratica.tipologia.link2 }" target="_blank"><b>${datiPratica.tipologia.descr_link2 }</b></a>
			</td></tr>
		</c:if>
		<c:if test="${!empty datiPratica.tipologia.link3 && datiPratica.tipologia.link3 != ''}">
		<tr>
			<td colspan="6">
				<a href="${datiPratica.tipologia.link3 }" target="_blank"><b>${datiPratica.tipologia.descr_link3 }</b></a>
			</td></tr>
		</c:if>
	</table>

	</div>  
 <div class="schede" id="allegato" style="display: none;">
    	<!--<c:if test="${datiPratica.id != null && datiPratica.id > 0 }">
			<script src="/html/js/liferay/widget.js" type="text/javascript"></script>
			<script type="text/javascript">
				Liferay.Widget({ url: '${urlShareSite}',height:'390px'});
			</script>	
		</c:if>-->
		 <!-- ***************************ALLEGATI PRATICA*******************************************-->
   			<c:if test="${! empty datiPratica.allegati }">   
				<table class="elencoRisultati" id="tableDocumenti" style="width:99%;margin-top: 10px;margin-left: 5px;">
					<tr><th><spring:message code="label.doc" /></th>
					<th><spring:message code="label.doc" /></th>
					<th>&nbsp;</th>
							</tr>
					<c:set var="j" value="0" scope="page" />
					<c:forEach var="item" begin="0" items="${datiPratica.allegati}">
						<tr>
							<td>${item.allegati.nomeDocumento }</td>														
							<td>
								<c:if test="${!empty item.uuidFile && item.uuidFile != ''}">
										<br />
										<portlet:resourceURL var="downloadAllegato" id="downloadAllegato">
											<portlet:param name="uuidAllegato" value="${item.uuidFile }" />
											<portlet:param name="fileNameAllegato" value="${item.nomeFile }" />
										</portlet:resourceURL>
										<a href="${downloadAllegato}" target="_blank"><spring:message code="link.download" /></a>
									
								</c:if> 
								<c:if test="${item.daIntegrare && (empty item.uuidFile || item.uuidFile eq '')}">
										<form:input type="file" size="20" path="allegati[${j}].allegato" />
									<input type="hidden" name="allegati[${j }].nomeFile" id="allegati[${j }].nomeFile" />
									
								</c:if> 
							</td>							
						</tr>
						<c:set var="j" value="${j+1}" scope="page" />	
					</c:forEach>
				 </table>
			</c:if>		        
     <!-- *******************************FINE***************************************************** -->   
	<br />	
    </div>   
    <!-- ***************************CAMPI AGGIUNTIVI*******************************************-->
   <div class="schede" id="campiaggiuntivi" style="display: none;">
			<table cellpadding="4" cellspacing="4" class="tableDett">
			<c:set var="k" value="0" scope="page" />
				<c:forEach var="item" begin="0" items="${datiPratica.campiaggiuntivi}">						
				<tr>
					<th>
						${item.campiAggiuntivi.label }
					</th>
					<td>
						${item.valore }
					</td>					
				</tr>
				</c:forEach>		
			</table>
    </div>
<!-- ************************************************************************************ -->
	<!-- ***************************ATTORI*******************************************-->
   <div class="schede" id="attori" style="display: none;">
			<table id="tableAttori" align="left" cellpadding=24 cellspacing=4 style="width:70%;" >		
				<c:if test="${! empty datiPratica.figure }">
					<c:forEach var="item" begin="0" items="${datiPratica.figure}">
						<tr>
							<th><spring:message code="label.attore" /></th>
							<td>
							<c:choose>
											<c:when test="${item.referente.chkref}">${item.referente.nominativo}</c:when>
											<c:otherwise>${item.referente.cognome}&nbsp;${item.referente.nome}</c:otherwise>
										</c:choose>
								</td>
							<th><spring:message code="label.qualifica" /></th>
							<td>
								${item.qualifica.nome }								
							</td>
						</tr>							
					</c:forEach>
				</c:if>		        
			 </table>
    </div>
	<br />	
	<%@ include file="../common/footer.jsp"%>
	<br />	
	<div class="buttonsDiv">
		<!-- <a href="javascript:if(confirm('<spring:message code="message.confirmSave" />')) formLoading('editForm');" ><spring:message code="button.salva" /></a> -->
	
		<a href="${annullaUrl}" title="<spring:message code="button.annulla" />">
			<spring:message code="button.annulla" />
		</a>
	</div>
</form:form>