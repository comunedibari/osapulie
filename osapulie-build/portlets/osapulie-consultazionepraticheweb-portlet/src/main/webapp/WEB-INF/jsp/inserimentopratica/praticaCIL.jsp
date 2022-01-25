<%@ include file="../common/common.jsp"%>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioniTab.js?123" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioni.js?123" type="text/javascript"></script>
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
		<%@ include file="insertPraticaInizio.jsp"%>	
		<tr>
				<td><c:set var="size" value="60" scope="page"/>
					<c:set var="nomeCampo" value="campiaggiuntivi[0].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[0].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldinputtext.jsp"%>	
				</td>	
		</tr>
		<tr><td><br/></td></tr>		
		<tr>
			<td colspan="2">
				<c:set var="nomeCampo" value="campiaggiuntivi[1].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[1].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[1].valore}" scope="page"/>
				<%@ include file="../common/fieldcheckbox.jsp"%>								
			</td>
			</tr>
			<tr><td><br/></td></tr>		
			<tr>
				<td colspan="2" style="border:1px solid #000;">
					<c:set var="nomeCampo" value="campiaggiuntivi[2].valore" scope="page"/>
					<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[2].campiAggiuntivi.listaValori}" scope="page"/>
					<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[2].valore}" scope="page"/>
					<%@ include file="../common/fieldradiobutton.jsp"%>					
				</td>
			</tr>
			
			<tr><td><br/></td></tr>
			<%@ include file="insertPraticaImmobile.jsp"%>			
		<tr>
				<td>
					<c:set var="nomeCampo" value="campiaggiuntivi[3].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[3].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldinputtext.jsp"%>	
				</td>	
		</tr>
		<tr>
				<td>
					<c:set var="nomeCampo" value="campiaggiuntivi[4].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[4].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldtextarea.jsp"%>					
				</td>	
		</tr>
		<tr><td><br/></td></tr>	
		<%@ include file="insertPraticaDichiarante.jsp"%>
		<tr>
			<td>
				${datiPratica.campiaggiuntivi[5].campiAggiuntivi.label}:</td>
			<td>
				<c:set var="nomeCampo" value="campiaggiuntivi[5].valore" scope="page"/>
					<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[5].campiAggiuntivi.listaValori}" scope="page"/>
					<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[5].valore}" scope="page"/>
					<%@ include file="../common/fieldradiobutton.jsp"%>								
			</td>
			</tr>
			<tr><td></td>
			<td>
			<c:set var="nomeCampo" value="campiaggiuntivi[67].valore" scope="page"/>
				<c:set var="size" value="50" scope="page"/>
				<%@ include file="../common/fieldinputtext.jsp"%>
				<br/>
				<c:set var="nomeCampo" value="campiaggiuntivi[6].valore" scope="page"/>
				<c:set var="size" value="20" scope="page"/>
				${datiPratica.campiaggiuntivi[6].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> &nbsp;
				<c:set var="nomeCampo" value="campiaggiuntivi[7].valore" scope="page"/>
				<c:set var="size" value="30" scope="page"/>
				${datiPratica.campiaggiuntivi[7].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> <br/>
				<c:set var="nomeCampo" value="campiaggiuntivi[8].valore" scope="page"/>
				<c:set var="size" value="30" scope="page"/>
				${datiPratica.campiaggiuntivi[8].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> &nbsp;
				<c:set var="nomeCampo" value="campiaggiuntivi[9].valore" scope="page"/>
				<c:set var="size" value="10" scope="page"/>
				${datiPratica.campiaggiuntivi[9].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> <br/>
			</td></tr>
			<tr><td><br/></td></tr>	
			<tr>
			<td colspan="2">
				${datiPratica.campiaggiuntivi[10].campiAggiuntivi.label}:</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:set var="nomeCampo" value="campiaggiuntivi[10].valore" scope="page"/>
							<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[10].campiAggiuntivi.listaValori}" scope="page"/>
							<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[10].valore}" scope="page"/>
							<%@ include file="../common/fieldradiobutton.jsp"%>								
					</td>
				</tr>
				<tr>
					<td colspan="2">
						${datiPratica.campiaggiuntivi[11].campiAggiuntivi.label}: &nbsp;
						<c:set var="nomeCampo" value="campiaggiuntivi[11].valore" scope="page"/> 
						<c:set var="size" value="60" scope="page"/>
						<%@ include file="../common/fieldinputtext.jsp"%>
					</td>
				</tr>
			<tr><td><br/></td></tr>	
			<tr><td colspan="2" STYLE="text-align: center;"><strong>COMUNICA</strong></td></tr>
			<tr><td colspan="2">ricorrendo la fattispecie di cui all'art 6, comma 2, del d.P.R. 6 giugno 2001 n. 380 che il giorno <c:set var="nomeCampo" value="campiaggiuntivi[12].valore" scope="page"/> <%@ include file="../common/fielddate.jsp"%> dar&agrave; inizio ai lavori edili sottodescritti, nel pieno rispetto delle prescrizioni degli strumenti urbanistici comunali, e comunque nel rispetto delle altre normative di settore aventi incidenza sulla disciplina dell'attivit&agrave; edilizia e, in particolare, delle norme antisismiche, di sicurezza, antincendio, igienico - sanitarie, di quelle relative all'efficienza energetica nonch&egrave; delle disposizioni contenute nel "Codice dei beni culturali e del paesaggio" di cui al decreto legislativo 22 gennaio 2004, n. 42.</td></tr>
			<tr>
				<td colspan="2">
					<strong>${datiPratica.campiaggiuntivi[13].campiAggiuntivi.label}:</strong></td>
					</tr>
					<tr>
				<td colspan="2">
					<c:set var="nomeCampo" value="campiaggiuntivi[13].valore" scope="page"/>
						<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[13].campiAggiuntivi.listaValori}" scope="page"/>
						<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[13].valore}" scope="page"/>
						<%@ include file="../common/fieldcheckbox.jsp"%>								
				</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
			<td colspan="2"><fieldset>
			<legend><spring:message code="label.datiimpresa"/></legend>
				<table style="width: 100%;">
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[14].campiAggiuntivi.label}: &nbsp;</td>
							<td  colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[14].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[15].campiAggiuntivi.label}: &nbsp;</td>
							<td  colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[15].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[16].campiAggiuntivi.label}: &nbsp;</td>
							<td  colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[16].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[17].campiAggiuntivi.label}: &nbsp;</td>
							<td colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[17].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[18].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[18].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>					
						<td>
							${datiPratica.campiaggiuntivi[19].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[19].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[20].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[20].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>					
						<td>
							${datiPratica.campiaggiuntivi[21].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[21].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
				</table>
				</fieldset>
				</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:set var="nomeCampo" value="campiaggiuntivi[22].valore" scope="page"/>
						<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[22].campiAggiuntivi.listaValori}" scope="page"/>
						<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[22].valore}" scope="page"/>
						<%@ include file="../common/fieldcheckbox.jsp"%>								
					</td>
				</tr>
				<!-- <tr>
				<td>${datiPratica.campiaggiuntivi[68].campiAggiuntivi.label}:</td>
				<td><c:set var="nomeCampo" value="campiaggiuntivi[68].allegatoContent" scope="page"/> 
				<input type="hidden" name="campiaggiuntivi[68].allegatoNome" id="campiaggiuntivi[68].allegatoNome" />
						<form:input type="file" size="20" path="${ nomeCampo}" />						
						<c:if test="${!empty datiPratica.campiaggiuntivi[68].uuidFile && datiPratica.campiaggiuntivi[68].uuidFile != ''}">
										<br />
										<portlet:resourceURL var="downloadAllegato" id="downloadAllegato">
											<portlet:param name="uuidAllegato" value="${datiPratica.campiaggiuntivi[68].uuidFile }" />
											<portlet:param name="fileNameAllegato" value="${datiPratica.campiaggiuntivi[68].nomeFile }" />
										</portlet:resourceURL>
										<a href="${downloadAllegato}" target="_blank"><spring:message code="link.download2" /></a>
									
								</c:if>
					&nbsp;<form:errors path="campiaggiuntivi[68].valore" cssStyle="color:red"/></td>
			</tr>	 -->		
				<tr>
					<td>${datiPratica.campiaggiuntivi[23].campiAggiuntivi.label}:</td>
					<td><c:set var="nomeCampo" value="campiaggiuntivi[23].valore" scope="page"/> 
							<%@ include file="../common/fieldtextarea.jsp"%></td>
				</tr>
				<tr><td><br/></td></tr>
			<tr>
			<td colspan="2"><fieldset>
			<legend><spring:message code="label.datitecnico"/></legend>
				<table style="width: 100%;">
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[24].campiAggiuntivi.label}: &nbsp;</td>
							<td  colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[24].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[25].campiAggiuntivi.label}: &nbsp;</td>
							<td  colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[25].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[26].campiAggiuntivi.label}: &nbsp;</td>
							<td  colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[26].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[27].campiAggiuntivi.label}: &nbsp;</td>
							<td colspan="3">
							<c:set var="nomeCampo" value="campiaggiuntivi[27].valore" scope="page"/> 
							<c:set var="size" value="60" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[28].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[28].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>					
						<td>
							${datiPratica.campiaggiuntivi[29].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[29].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
					<tr>
						<td>
							${datiPratica.campiaggiuntivi[30].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[30].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>					
						<td>
							${datiPratica.campiaggiuntivi[31].campiAggiuntivi.label}: &nbsp;</td>
							<td>
							<c:set var="nomeCampo" value="campiaggiuntivi[31].valore" scope="page"/> 
							<c:set var="size" value="20" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
						</td>
					</tr>
				</table>
				</fieldset>
				</td>
				</tr>
			<tr><td><br/></td></tr>	
			<tr><td colspan="2" STYLE="text-align: center;"><strong>DICHIARAZIONE DI CONFORMITA'</strong></td></tr>
			<tr><td colspan="2">Il sottoscritto, tecnico abilitato come sopra generalizzato e incaricato di redigere la relazione tecnica e gli opportuni elaborati progettuali, attesta, sotto la propria responsabilità, che i lavori sono conformi agli strumenti urbanistici approvati e ai regolamenti edilizi vigenti, nonché che sono compatibili con la normativa in materia sismica e con quella sul rendimento energetico nell'edilizia e che non vi è interessamento delle parti strutturali dell'edificio.
			</td></tr>
			<tr><td><br/></td></tr>
			<tr>
				<td colspan="2">
					<strong>${datiPratica.campiaggiuntivi[32].campiAggiuntivi.label}:</strong></td>
					</tr>
					<tr>
				<td colspan="2">
					<c:set var="nomeCampo" value="campiaggiuntivi[32].valore" scope="page"/>
						<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[32].campiAggiuntivi.listaValori}" scope="page"/>
						<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[32].valore}" scope="page"/>
						<%@ include file="../common/fieldcheckbox.jsp"%>								
				</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr><td colspan="2">Consapevole che le dichiarazioni mendaci e le falsit&agrave; in atti comportano le sanzioni previste dall'art. 76 del d.P.R. 445/2000;</td></tr>
			<tr><td colspan="2" STYLE="text-align: center;"><strong>DICHIARA/DICHIARANO:</strong></td></tr>
			<tr><td colspan="2">
			a)  che i dati personali sopra riportati sono veritieri;<br/>
			b)  di essere legittimato il dichiarante, secondo le vigenti norme, alla presentazione della comunicazione di inizio lavori;<br/>
			c)  ${datiPratica.campiaggiuntivi[33].campiAggiuntivi.label}:<br/>
				<c:set var="nomeCampo" value="campiaggiuntivi[33].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[33].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[33].valore}" scope="page"/>
				<c:set var="numCampo" value="34" scope="page"/>
				<c:set var="maxCampo" value="56" scope="page"/>
				<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
					<c:set var="check" value="" scope="page"/>
					<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
							<c:if test="${valoriName==tokenName}">
								<c:set var="check" value="checked" scope="page"/>
							</c:if>
					</c:forTokens>	
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="${nomeCampo }" value="${tokenName}" ${check} />&nbsp;${tokenName}
				   <c:if test="${numCampo < maxCampo}"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   ${datiPratica.campiaggiuntivi[numCampo].campiAggiuntivi.label} <c:set var="nomeCampo" value="campiaggiuntivi[${numCampo}].valore" scope="page"/> 
							<c:set var="size" value="10" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
					${datiPratica.campiaggiuntivi[(numCampo + 1)].campiAggiuntivi.label} <c:set var="nomeCampo" value="campiaggiuntivi[${(numCampo+ 1)}].valore" scope="page"/> 
							<c:set var="size" value="10" scope="page"/>
							<%@ include file="../common/fielddate.jsp"%>
					</c:if>
				   <br/>
				   <c:set var="numCampo" value="${(numCampo +2)}" scope="page"/>
				   <c:set var="nomeCampo" value="campiaggiuntivi[33].valore" scope="page"/>
				</c:forTokens>				
				<form:errors path="${nomeCampo }" cssStyle="color:red"/>
				<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<c:set var="nomeCampo" value="campiaggiuntivi[56].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[56].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[56].valore}" scope="page"/>
				<%@ include file="../common/fieldcheckbox.jsp"%>
				<br/>
			d)  <div style="padding-left: 17px;"><c:set var="nomeCampo" value="campiaggiuntivi[57].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[57].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[57].valore}" scope="page"/>
				<%@ include file="../common/fieldradiobutton.jsp"%>
				</div>
					<div style="padding-left: 37px"><c:set var="nomeCampo" value="campiaggiuntivi[58].valore" scope="page"/>
					<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[58].campiAggiuntivi.listaValori}" scope="page"/>
					<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[58].valore}" scope="page"/>
					<c:set var="numCampo" value="59" scope="page"/>
					<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
						<c:set var="check" value="" scope="page"/>
						<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
								<c:if test="${valoriName==tokenName}">
									<c:set var="check" value="checked" scope="page"/>
								</c:if>
						</c:forTokens>	
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="${nomeCampo }" value="${tokenName}" ${check} />&nbsp;${tokenName}
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   ${datiPratica.campiaggiuntivi[numCampo].campiAggiuntivi.label} <c:set var="nomeCampo" value="campiaggiuntivi[${numCampo}].valore" scope="page"/> 
								<c:set var="size" value="10" scope="page"/>
								<%@ include file="../common/fieldinputtext.jsp"%>
						${datiPratica.campiaggiuntivi[(numCampo + 1)].campiAggiuntivi.label} <c:set var="nomeCampo" value="campiaggiuntivi[${(numCampo+ 1)}].valore" scope="page"/> 
								<c:set var="size" value="10" scope="page"/>
								<%@ include file="../common/fielddate.jsp"%>						
					   <br/>
					   <c:set var="numCampo" value="${(numCampo +2)}" scope="page"/>
					   <c:set var="nomeCampo" value="campiaggiuntivi[58].valore" scope="page"/>
					</c:forTokens>				
					<form:errors path="${nomeCampo }" cssStyle="color:red"/>
					</div>
					<br/>
			e)  ${datiPratica.campiaggiuntivi[65].campiAggiuntivi.label}:<br/>
				<div style="padding-left: 17px;"><c:set var="nomeCampo" value="campiaggiuntivi[65].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[65].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[65].valore}" scope="page"/>
				<%@ include file="../common/fieldradiobutton.jsp"%>
				${datiPratica.campiaggiuntivi[66].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[66].valore" scope="page"/> 
							<c:set var="size" value="10" scope="page"/>
							<%@ include file="../common/fieldinputtext.jsp"%>
				</div>
				</td></tr>
				<tr><td><br/></td></tr>
				<tr><td colspan="2" STYLE="text-align: center;"><strong>SI IMPEGNA / SI IMPEGNANO</strong></td></tr>
				<tr><td colspan="2"><strong>ricorrendone la fattispecie:</strong>
				<ul>
					<li>ad osservare tutte le indicazioni e prescrizioni previste nel vigente strumento urbanistico e piani attuativi;</li>
					<li>a munirsi di tutte le autorizzazioni, nulla-osta, pareri, atti di assenso comunque denominati, previsti dalle vigenti norme, prima della esecuzione dei lavori (PAI, Soprintendenza Archeologica, Soprintendenza ai Monumenti, PUTT/P, ecc...);</li>
					<li>ad avvalersi di impresa regolarmente iscritta alla Camera di Commercio, Industria ed Artigianato, in regola con il DURC;</li>
					<li>alla piena osservanza delle norme in materia di smaltimento rifiuti (smaltimento rifiuti presso discariche autorizzate);</li>
					<li>alla piena e perfetta osservanza di quanto previsto dal d. lgs. n.81/08;</li>
					<li>alla piena osservanza delle norme in materia di impianti di cui al DM n.37/2008 e di risparmio energetico;</li>
					<li>alla piena osservanza delle norme in materia di abbattimento barriere architettoniche;</li>
					<li>a rispettare i diritti dei terzi;</li>
					<li>ad installare apposita tabella di cantiere;</li>
					<li>a comunicare la fine dei lavori, valida anche ai fini di cui all'articolo 17, primo comma, lettera b), del regio decreto-legge 13 aprile 1939, n. 652, convertito, con modificazioni, dalla legge 11 agosto 1939, n. 1249, per l'inoltro da parte dell'amministrazione comunale ai competenti uffici dell'Agenzia delle entrate (Aggiornamento catastale);</li>
					<li>a richiedere la nuova agibilit&agrave; nei casi previsti dalle vigenti norme;</li>
					<li>a richiedere apposita autorizzazione nel caso di occupazione di aree pubbliche o di uso pubblico (per montaggio ponteggi ecc.).</li>
				</ul>
				</td></tr>
				<tr><td><br/></td></tr>
				<tr><td colspan="2" STYLE="text-align: center;"><strong>E SOLLEVA / SOLLEVANO</strong></td></tr>
				<tr><td colspan="2">il Comune di TARANTO da ogni responsabilit&agrave; nei confronti dei terzi.</td></tr>			
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
							
						<!-- 	<c:if test="${!empty item.urlAllegato && item.urlAllegato != ''}">
								<br/>
								<a href="${item.urlAllegato }" target="_blank"><spring:message code="link.download2" /></a>
								&nbsp;&nbsp;
								 -->
							<!-- 	<portlet:actionURL var="delAllUrl">
									<portlet:param name="action" value="deleteAllegato" />
									<portlet:param name="nome" value="${item.allegati.nomeDocumento}" />
								</portlet:actionURL>-->
								<!-- <a title="<spring:message code="button.delete" />" href="javascript:if(confirm('<spring:message code="message.confirm" />'))document.location='${delAllUrl}';">
									<img alt="<spring:message code="button.delete" />" src="<%=request.getContextPath() %>/images/ko.png"> 
								</a> -->
							<!--</c:if>-->							
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
				<!-- <input name="Salva" type="submit" value="<spring:message code="button.salva" />" onclick="javascript:return(confirm('<spring:message code="message.confirmSave" />'))"/> -->
		<a href="javascript:if(confirm('<spring:message code="message.confirmSave" />')) formLoading('editForm');" ><spring:message code="button.salva" /></a>
		<a href="${annullaUrl}" id="annulla" title="<spring:message code="button.annulla" />">
			<spring:message code="button.annulla" />
		</a>
	</div>	
</form:form>