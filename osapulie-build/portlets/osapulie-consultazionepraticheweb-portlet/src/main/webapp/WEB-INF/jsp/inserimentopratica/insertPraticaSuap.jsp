<%@ include file="../common/common.jsp"%>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioniTab.js" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioni.js" type="text/javascript"></script>
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
	formLoading('editForm');
});
});
</script>		
<portlet:renderURL var="annullaUrl" />
<portlet:actionURL var="saveEntityUrl">
	<portlet:param name="action" value="save" />
</portlet:actionURL>
<form:form id="editForm" commandName="datiPratica" method="post" action="${saveEntityUrl}" enctype="multipart/form-data">
<div id="tab">
    <ul>
    	<li id="link_apertura" class="activelink"><a href="javascript:vis_apertura()"><spring:message code="tab.dati" /></a></li>
        <li id="link_documenti" ><a href="javascript:vis_documenti()"><spring:message code="tab.allegati" /></a></li>              
     </ul>
    </div>
    <div class="schede" id="apertura">
	<table style="width: 97%;">
		<tr>
			<td colspan="2" style="text-align:center;">
				<h3 align=center style='margin-top:7.0pt;text-align:center'>${datiPratica.tipologia.descrizione}</h3>
			</td>
			</tr>
		<tr>
			<td>
				<spring:message code="label.dataRichiesta" />:*
			</td>
			<td>
				<form:input maxlength="10" size="11" readonly="true" id="textDataRichiesta" path="dataRichiesta" onblur="controllaData(this);" />&nbsp;
				<form:errors path="dataRichiesta" cssStyle="color:red"/>
			</td>			
		</tr>					
		<tr>
			<td>
				<spring:message code="label.oggettoRichiesta" />:*
			</td>
			<td>
				<form:textarea rows="4" cols="100" maxlength="500" id="textOggettoRichiesta" path="oggettoRichiesta" /><br/>
				<form:errors path="oggettoRichiesta" cssStyle="color:red"/>
			</td>
		</tr>	
	
		<tr><td><br/></td></tr>
		
		
<tr>
	<td colspan="2">
		<fieldset>
			<legend>
				<spring:message code="label.immobile" />
			</legend>
			<table style="width: 100%;">
				<tr>
					<!-- 
					<td><spring:message code="label.denominazione" />:*</td>
					<td><form:input size="30" maxlength="255"
							id="textdenominazione" path="immobileDen" /> <br />
					<form:errors path="immobileDen" cssStyle="color:red" /></td> 
					-->
					<td><spring:message code="label.tipologia" />:*</td>
					<td><form:select path="immobileTipo">
							<c:forEach var="item" begin="0" items="${tipologieImm}">
								<option value="${item.id}"
									<c:if test="${datiPratica.immobileTipo.id == item.id}"> selected="selected" </c:if>>${item.descrizione}</option>
							</c:forEach>
						</form:select> <br />
					<form:errors path="immobileTipo" cssStyle="color:red" /></td>
					<td><spring:message code="label.indirizzo" />:*</td>
					<td><form:input size="30" maxlength="255" id="textindirizzo"
							path="immobileInd" /> <br />
					<form:errors path="immobileInd" cssStyle="color:red" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.piano" />:*</td>
					<td><form:input size="15"  maxlength="3" id="textPiano"
							path="immobilePiano" /> <br />
					<form:errors path="immobilePiano" cssStyle="color:red" /></td>
					<td><spring:message code="label.interno" />:</td>
					<td><form:input size="30" maxlength="3" id="textinterno"
							path="immobileInterno" /> <br />
					<form:errors path="immobileInterno" cssStyle="color:red" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.cap" />:*</td>
					<td><form:input size="8" maxlength="5" id="textcap"
							path="immobileCap" /> <form:errors path="immobileCap"
							cssStyle="color:red" /></td>
					<td><spring:message code="label.comune" />:*</td>
					<td><form:select path="immobileComune">
							<c:forEach var="item" begin="0" items="${comuni}">
								<option value="${item.id}"
									<c:if test="${datiPratica.immobileComune.codiceIstat1 == item.codiceIstat1}"> selected="selected" </c:if>>${item.denominazione}</option>
							</c:forEach>
						</form:select> <br />
					<form:errors path="immobileComune" cssStyle="color:red" /></td>


				</tr>				
			</table>
		</fieldset>
	</td>
</tr>
<tr>
	<td><br /></td>
</tr>
		
		<%@ include file="insertPraticaDichiarante.jsp"%>
				
		</table>
	</div>    
<!-- ************************************************************************************ -->

    <!-- ***************************ALLEGATI PRATICA*******************************************-->
   <div class="schede" id="documenti" style="display: none;">
   			<c:if test="${! empty datiPratica.allegati }">   
				<table class="elencoRisultati" id="tableDocumenti" style="width:99%;margin-top: 10px;margin-left: 5px;">
					<tr><th><spring:message code="label.doc" /></th>
					<!-- <th><spring:message code="label.caricato" /></th> -->
					<th><spring:message code="label.doc" /></th>
					<th>&nbsp;</th>
							</tr>
					<c:set var="j" value="0" scope="page" />
					<c:forEach var="item" begin="0" items="${datiPratica.allegati}">
						<tr>
							<td>${item.allegati.nomeDocumento }
							<c:if test="${!empty item.allegati.obbligatorio && item.allegati.obbligatorio == true}">
								*
							</c:if>	
							</td>
												
							<td>
							<c:if test="${empty item.uuidFile || item.uuidFile eq ''}">
								<form:input type="file" size="20" path="allegati[${j}].allegato" />
								<input type="hidden" name="allegati[${j }].nomeFile" id="allegati[${j }].nomeFile" />
							</c:if>
							<c:if test="${!empty item.uuidFile && item.uuidFile != ''}">
										<portlet:resourceURL var="downloadAllegato" id="downloadAllegato">
											<portlet:param name="uuidAllegato" value="${item.uuidFile }" />
											<portlet:param name="fileNameAllegato" value="${item.nomeFile }" />
										</portlet:resourceURL>
										<a href="${downloadAllegato}" title="<spring:message code="link.download" />" target="_blank"><img src="<%=request.getContextPath() %>/images/download.png" alt="<spring:message code="link.download" />" width="25px" /></a>
										&nbsp;&nbsp;&nbsp;										
										<portlet:actionURL var="deleteAllegato">
											<portlet:param name="action" value="deleteAllegato" />
											<portlet:param name="uuidAllegato" value="${item.uuidFile }" />
											<portlet:param name="fileNameAllegato" value="${item.nomeFile }" />
										</portlet:actionURL>
										<a href="javascript:if(confirm('<spring:message code="message.confirm" />'))document.location='${deleteAllegato}';" title="Elimina allegato" target="_blank"><img src="<%=request.getContextPath() %>/images/delete.png" alt="Elimina allegato" width="25px" /></a>
									
								</c:if>		
							</td>
							<td><form:errors path="allegati[${j}]" cssStyle="color:red"/></td>
						</tr>
						<c:set var="j" value="${j+1}" scope="page" />	
					</c:forEach>
				 </table>
			</c:if>		        
    </div>
     <!-- *******************************FINE***************************************************** -->   
	<br />	
	<c:if test="${!empty tab }">
		<script language="javascript">${tab}();</script>
	</c:if>
	<%@ include file="../common/footer.jsp"%>
	<br />	
	<div class="buttonsDiv">
		<a href="javascript:if(confirm('<spring:message code="message.confirmSave" />')) formLoading('editForm');" ><spring:message code="button.salva" /></a>
		
		<a href="${annullaUrl}" id="annulla" title="<spring:message code="button.annulla" />">
			<spring:message code="button.annulla" />
		</a>
	</div>	
</form:form>