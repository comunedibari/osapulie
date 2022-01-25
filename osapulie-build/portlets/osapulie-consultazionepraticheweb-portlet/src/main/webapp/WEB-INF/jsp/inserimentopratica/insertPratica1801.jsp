<%@ include file="../common/common.jsp"%>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioniTab.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioni.js"
	type="text/javascript"></script>
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
	if (!fileName|| 0 ===fileName.length)
		$("#allegati\\["+part2[0]+"\\]\\.nomeFile").val('');
	else
		$("#allegati\\["+part2[0]+"\\]\\.nomeFile").val(fileName);
});
});
</script>
<portlet:renderURL var="annullaUrl" />
<portlet:actionURL var="saveEntityUrl">
	<portlet:param name="action" value="save" />
</portlet:actionURL>
<form:form id="editForm" commandName="datiPratica" method="post"
	action="${saveEntityUrl}" enctype="multipart/form-data">
	<div id="tab">
		<ul>
			<li id="link_apertura" class="activelink"><a
				href="javascript:vis_apertura()"><spring:message code="tab.dati" /></a></li>
			<li id="link_documenti"><a href="javascript:vis_documenti()"><spring:message
						code="tab.allegati" /></a></li>
		</ul>
	</div>
	<div class="schede" id="apertura">
		<table style="width: 97%;">
			<tr>
				<td colspan="2" style="text-align: center;">
					<h3 align=center style='margin-top: 7.0pt; text-align: center'>${datiPratica.tipologia.descrizione}</h3>
				</td>
			</tr>
			<tr>
				<td><spring:message code="label.dataRichiesta" />:*</td>
				<td><form:input maxlength="10" size="8" readonly="true"
						id="textDataRichiesta" path="dataRichiesta"
						onblur="controllaData(this);" />&nbsp; <form:errors
						path="dataRichiesta" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.oggettoRichiesta" />:*</td>
				<td><form:textarea rows="4" cols="100" maxlength="500"
						id="textOggettoRichiesta" path="oggettoRichiesta" /><br /> 
					<form:errors path="oggettoRichiesta" cssStyle="color:red" /></td>
			</tr>
			<!-- *********************STRUTTURA ORGANIZZATIVA RICHIEDENTE*****************-->
			<td><spring:message code="label.richiedente" />:*</td>
				<td>
				<c:choose>
					<c:when test="${datiPratica.richNominativo != null && datiPratica.richNominativo != ''}">
						<div>
							${datiPratica.richNominativo}
							<br />
							${datiPratica.richIndirizzo}
							<br />
							${datiPratica.richCap}&nbsp;${datiPratica.richComune.denominazione}
						</div>
					</c:when>
					<c:otherwise>
						<input name="cerca_richiedente" type="submit" value="<spring:message code="button.cercarichiedente" />" />
					</c:otherwise>
				</c:choose> 
				
				<form:errors path="richNominativo" cssStyle="color:red" /></td>
			<!-- ***************************CAMPI AGGIUNTIVI******************************-->
			<tr>
				<td><c:set var="size" value="60" scope="page"/>
					<c:set var="nomeCampo" value="campiaggiuntivi[0].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[0].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldtextarea.jsp"%>	
				</td>	
			</tr>
			<tr>
				<td><c:set var="size" value="60" scope="page"/>
					<c:set var="nomeCampo" value="campiaggiuntivi[1].valore" scope="page"/>
					<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[1].campiAggiuntivi.listaValori}" scope="page"/>
					${datiPratica.campiaggiuntivi[1].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldselect.jsp"%>	
				</td>	
			</tr>
			<tr>
				<td><c:set var="size" value="60" scope="page"/>
					<c:set var="nomeCampo" value="campiaggiuntivi[2].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[2].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldinputtext.jsp"%>	
				</td>	
			</tr>
		</table>
	</div>
	<!-- ************************************************************************************ -->

	<!-- ***************************ALLEGATI PRATICA*******************************************-->
	<div class="schede" id="documenti" style="display: none;">
		<c:if test="${! empty datiPratica.allegati }">
			<table class="elencoRisultati" id="tableDocumenti"
				style="width: 99%; margin-top: 10px; margin-left: 5px;">
				<tr>
					<th><spring:message code="label.doc" /></th>
					<!-- <th><spring:message code="label.caricato" /></th> -->
					<th><spring:message code="label.doc" /></th>
					<th>&nbsp;</th>
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
						<!-- <td><form:checkbox path="allegati[${j }].caricato" /></td>-->
						<td><form:input type="file" size="20"
								path="allegati[${j}].allegato" />
								<input type="hidden" name="allegati[${j }].nomeFile" id="allegati[${j }].nomeFile" /></td>
						<td><form:errors path="allegati[${j}]" cssStyle="color:red" /></td>
						<td>
						<c:if test="${!empty item.uuidFile && item.uuidFile != ''}">
										<br />
										<portlet:resourceURL var="downloadAllegato" id="downloadAllegato">
											<portlet:param name="uuidAllegato" value="${item.uuidFile }" />
											<portlet:param name="fileNameAllegato" value="${item.nomeFile }" />
										</portlet:resourceURL>
										<a href="${downloadAllegato}" target="_blank"><spring:message code="link.download" /></a>
									
								</c:if>
								</td> 
					</tr>
					<c:set var="j" value="${j+1}" scope="page" />
				</c:forEach>
			</table>
		</c:if>
	</div>
	<!-- *******************************FINE***************************************************** -->
	<br />
	<c:if test="${!empty tab }">
		<script language="javascript">${tab }();</script>
	</c:if>
	<%@ include file="../common/footer.jsp"%>
	<br />
	<div class="buttonsDiv">
		<input name="Salva" type="submit"
			value="<spring:message code="button.salva" />"
			onclick="javascript:return(confirm('<spring:message code="message.confirmSave" />'))" />
		<a href="${annullaUrl}" id="annulla"
			title="<spring:message code="button.annulla" />"> <spring:message
				code="button.annulla" />
		</a>
	</div>
</form:form>