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
		<tr>
				<td style="text-align: right;"><c:set var="size" value="60" scope="page"/>
					<c:set var="nomeCampo" value="campiaggiuntivi[1].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[1].campiAggiuntivi.label}:
				</td>
				<td>
					<%@ include file="../common/fieldinputtext.jsp"%>	
				</td>	
		</tr>
		<tr><td><br/></td></tr>		
		<tr>
			<td colspan="2">
				<c:set var="nomeCampo" value="campiaggiuntivi[2].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[2].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[2].valore}" scope="page"/>
				<c:set var="pos" value="1" scope="page"/>
				<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
						<c:set var="check" value="" scope="page"/>
						<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
								<c:if test="${valoriName==tokenName}">
									<c:set var="check" value="checked" scope="page"/>
								</c:if>
						</c:forTokens>	
					   <input type="checkbox" name="${nomeCampo }" value="${tokenName}" ${check} />&nbsp;${tokenName}
					   <c:if test="${pos == 4 }">
					   &nbsp;
					   ${datiPratica.campiaggiuntivi[3].campiAggiuntivi.label} <c:set var="nomeCampo" value="campiaggiuntivi[3].valore" scope="page"/> 
								<c:set var="size" value="10" scope="page"/>
								<%@ include file="../common/fieldinputtext.jsp"%>
						${datiPratica.campiaggiuntivi[4].campiAggiuntivi.label} <c:set var="nomeCampo" value="campiaggiuntivi[4].valore" scope="page"/> 
								<c:set var="size" value="10" scope="page"/>
								<%@ include file="../common/fielddate.jsp"%>						
					   </c:if>
					   <c:if test="${pos == 7 }">
					   &nbsp;${datiPratica.campiaggiuntivi[5].campiAggiuntivi.label} <c:set var="nomeCampo" value="campiaggiuntivi[5].valore" scope="page"/> 
								<c:set var="size" value="50" scope="page"/>
								<%@ include file="../common/fieldinputtext.jsp"%>
					   </c:if>
					   <br/>
					   <c:set var="nomeCampo" value="campiaggiuntivi[2].valore" scope="page"/>
					   <c:set var="pos" value="${ (pos +1)}" scope="page"/>
					</c:forTokens>
					<form:errors path="${nomeCampo }" cssStyle="color:red"/>									
			</td>
			</tr>
			<tr><td><br/></td></tr>	
			<%@ include file="insertPraticaImmobile.jsp"%>		
		<tr>
				<td>
					<c:set var="nomeCampo" value="campiaggiuntivi[6].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[6].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldinputtext.jsp"%>	
				</td>	
		</tr>
		<tr>
				<td>
					<c:set var="nomeCampo" value="campiaggiuntivi[7].valore" scope="page"/>
					${datiPratica.campiaggiuntivi[7].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldtextarea.jsp"%>					
				</td>	
		</tr>
		<tr><td><br/></td></tr>		
		<%@ include file="insertPraticaDichiarante.jsp"%>
		<tr>
			<td>
				${datiPratica.campiaggiuntivi[8].campiAggiuntivi.label}:</td>
			<td>
				<c:set var="nomeCampo" value="campiaggiuntivi[8].valore" scope="page"/>
					<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[8].campiAggiuntivi.listaValori}" scope="page"/>
					<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[8].valore}" scope="page"/>
					<%@ include file="../common/fieldradiobutton.jsp"%>								
			</td>
			</tr>
			<tr><td></td>
			<td>
				<c:set var="nomeCampo" value="campiaggiuntivi[94].valore" scope="page"/>
				<c:set var="size" value="50" scope="page"/>
				<%@ include file="../common/fieldinputtext.jsp"%>
				<br/>
				<c:set var="nomeCampo" value="campiaggiuntivi[9].valore" scope="page"/>
				<c:set var="size" value="20" scope="page"/>
				${datiPratica.campiaggiuntivi[9].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> &nbsp;
				<c:set var="nomeCampo" value="campiaggiuntivi[10].valore" scope="page"/>
				<c:set var="size" value="30" scope="page"/>
				${datiPratica.campiaggiuntivi[10].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> <br/>
				<c:set var="nomeCampo" value="campiaggiuntivi[11].valore" scope="page"/>
				<c:set var="size" value="30" scope="page"/>
				${datiPratica.campiaggiuntivi[11].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> &nbsp;
				<c:set var="nomeCampo" value="campiaggiuntivi[12].valore" scope="page"/>
				<c:set var="size" value="10" scope="page"/>
				${datiPratica.campiaggiuntivi[12].campiAggiuntivi.label}: <%@ include file="../common/fieldinputtext.jsp"%> <br/>
			</td></tr>
			<tr><td><br/></td></tr>	
			<tr>
			<td colspan="2">
				${datiPratica.campiaggiuntivi[13].campiAggiuntivi.label}:</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:set var="nomeCampo" value="campiaggiuntivi[13].valore" scope="page"/>
							<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[13].campiAggiuntivi.listaValori}" scope="page"/>
							<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[13].valore}" scope="page"/>
							<%@ include file="../common/fieldradiobutton.jsp"%>								
					</td>
				</tr>
				<tr>
					<td colspan="2">
						${datiPratica.campiaggiuntivi[14].campiAggiuntivi.label}: &nbsp;
						<c:set var="nomeCampo" value="campiaggiuntivi[14].valore" scope="page"/> 
						<c:set var="size" value="60" scope="page"/>
						<%@ include file="../common/fieldinputtext.jsp"%>
					</td>
				</tr>
			<tr><td><br/></td></tr>	
			<tr><td colspan="2" STYLE="text-align: center;"><strong>CHIEDE</strong></td></tr>
			<tr>
				<td colspan="2">
					<strong>${datiPratica.campiaggiuntivi[15].campiAggiuntivi.label}:</strong></td>
					</tr>
					<tr>
				<td colspan="2">
					<table style="padding:0px;margin:0px;">
					<c:set var="nomeCampo" value="campiaggiuntivi[15].valore" scope="page"/>
						<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[15].campiAggiuntivi.listaValori}" scope="page"/>
						<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[15].valore}" scope="page"/>
						<c:set var="pos" value="1" scope="page"/>
						<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
							<c:set var="check" value="" scope="page"/>
							<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
									<c:if test="${valoriName==tokenName}">
										<c:set var="check" value="checked" scope="page"/>
									</c:if>
							</c:forTokens>	
						   <tr>
						   <td style="border: solid 1px;padding-right: 5px;"><input type="checkbox" name="${nomeCampo }" value="${tokenName}" ${check} />&nbsp;</td>
						   <td style="border: solid 1px;">${tokenName}
						   		<c:if test="${pos == 1 }">
						   			<table style="padding:0px;margin:0px;">
										<c:set var="nomeCampo" value="campiaggiuntivi[16].valore" scope="page"/>
											<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[16].campiAggiuntivi.listaValori}" scope="page"/>
											<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[16].valore}" scope="page"/>
											<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
												<c:set var="check" value="" scope="page"/>
												<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
														<c:if test="${valoriName==tokenName}">
															<c:set var="check" value="checked" scope="page"/>
														</c:if>
												</c:forTokens>	
											   <tr>
											   <td style="border: solid 1px;padding-right: 5px;"><input type="checkbox" name="${nomeCampo }" value="${tokenName}" ${check} />&nbsp;</td>
											   <td style="border: solid 1px;">${tokenName}
											   </td>
										   </tr>
										</c:forTokens>
									</table>
									<c:set var="nomeCampo" value="campiaggiuntivi[15].valore" scope="page"/>									
									<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[15].valore}" scope="page"/>						
						   		</c:if>
						   		<c:if test="${pos == 3 }">
						   			${datiPratica.campiaggiuntivi[17].campiAggiuntivi.label}<br/>
						   			<c:set var="nomeCampo" value="campiaggiuntivi[17].valore" scope="page"/> 
									<c:set var="size" value="120" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>
						   			<c:set var="nomeCampo" value="campiaggiuntivi[15].valore" scope="page"/>									
									<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[15].valore}" scope="page"/>
						   		</c:if>
						   		<c:if test="${pos == 7 }">
						   			${datiPratica.campiaggiuntivi[18].campiAggiuntivi.label}<br/>
						   			<c:set var="nomeCampo" value="campiaggiuntivi[18].valore" scope="page"/> 
									<c:set var="size" value="120" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>
						   			<c:set var="nomeCampo" value="campiaggiuntivi[15].valore" scope="page"/>									
									<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[15].valore}" scope="page"/>
						   		</c:if>
						   </td>
						   </tr>
						   <c:set var="pos" value="${pos + 1 }" scope="page"/>
						</c:forTokens>
						<form:errors path="${nomeCampo }" cssStyle="color:red"/>						
					</table>								
				</td>
			</tr>
			<tr><td><br/></td></tr>
			
			<tr><td colspan="2"><strong>Consapevole che le dichiarazioni mendaci e le falsit&agrave; in atti comportano le sanzioni previste dall'art. 76 del d.P.R. n. 445/2000 e la decadenza dai benefici conseguiti con il provvedimento emanato sulla base della dichiarazione non veritiera;</strong></td></tr>
			<tr><td colspan="2" STYLE="text-align: center;"><strong>DICHIARA:</strong></td></tr>
			<tr><td colspan="2">
			a)  che i dati personali sopra riportati sono veritieri;<br/>
			b)  di essere legittimato, secondo le vigenti norme, alla presentazione della domanda di rilascio di permesso di costruire;<br/>
			c)  che le opere sopra citate riguardano un intervento di nuova costruzione su area libera;<br/>
			d)  ${datiPratica.campiaggiuntivi[19].campiAggiuntivi.label}:<br/>
				<c:set var="nomeCampo" value="campiaggiuntivi[19].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[19].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[19].valore}" scope="page"/>
				<c:set var="numCampo" value="20" scope="page"/>
				<c:set var="maxCampo" value="44" scope="page"/>
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
					<c:if test="${tokenName == 'Altro' }">
				   		<c:set var="nomeCampo" value="campiaggiuntivi[44].valore" scope="page"/> 
						<c:set var="size" value="120" scope="page"/>
						<%@ include file="../common/fieldinputtext.jsp"%>
					</c:if>
				   <br/>
				   <c:set var="numCampo" value="${(numCampo +2)}" scope="page"/>
				   <c:set var="nomeCampo" value="campiaggiuntivi[19].valore" scope="page"/>
				</c:forTokens>				
				<form:errors path="${nomeCampo }" cssStyle="color:red"/>
				<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<c:set var="nomeCampo" value="campiaggiuntivi[45].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[45].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[45].valore}" scope="page"/>
				<%@ include file="../common/fieldcheckbox.jsp"%>
				<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;${datiPratica.campiaggiuntivi[46].campiAggiuntivi.label}:<br/><br/>
				<div style="padding-left: 16px;"><c:set var="nomeCampo" value="campiaggiuntivi[46].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[46].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[46].valore}" scope="page"/>
				<!--  inizio -->
				<c:set var="valoreIndice" value="0" scope="page"/>
				<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
					<c:set var="check" value="" scope="page"/>
					<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
							<c:if test="${valoriName==tokenName}">
								<c:set var="check" value="checked" scope="page"/>
							</c:if>
					</c:forTokens>	
				   <input type="checkbox" name="${nomeCampo }" value="${tokenName}" ${check} />&nbsp;${tokenName}
				   <c:if test="${valoreIndice==1}">
				   		&nbsp;&nbsp;&nbsp;&nbsp;${datiPratica.campiaggiuntivi[95].campiAggiuntivi.label}
				   		<c:set var="nomeCampo" value="campiaggiuntivi[95].valore" scope="page"/> 
							<%@ include file="../common/fielddate.jsp"%>
							<c:set var="nomeCampo" value="campiaggiuntivi[46].valore" scope="page"/>
				   </c:if>
				   <c:set var="valoreIndice" value="${valoreIndice + 1 }" scope="page"/>
				   <br/>
				</c:forTokens>
				<form:errors path="${nomeCampo }" cssStyle="color:red"/>
				<!-- fine -->
				</div>
				<br/>
			e)  <div style="padding-left: 17px;"><c:set var="nomeCampo" value="campiaggiuntivi[47].valore" scope="page"/>
				<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[47].campiAggiuntivi.listaValori}" scope="page"/>
				<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[47].valore}" scope="page"/>
				<%@ include file="../common/fieldradiobutton.jsp"%>
				</div>
					<div style="padding-left: 37px"><c:set var="nomeCampo" value="campiaggiuntivi[48].valore" scope="page"/>
					<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[48].campiAggiuntivi.listaValori}" scope="page"/>
					<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[48].valore}" scope="page"/>
					<c:set var="numCampo" value="49" scope="page"/>
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
					   <c:set var="nomeCampo" value="campiaggiuntivi[48].valore" scope="page"/>
					</c:forTokens>				
					<form:errors path="${nomeCampo }" cssStyle="color:red"/>
					</div>
					<br/>
			f)  di essere informato che, ai sensi dell'art. 15 d.P.R. n.380/2001:<br/>
				-	il termine per l'inizio dei lavori in oggetto non pu&ograve; essere superiore ad un anno dal rilascio del titolo;<br/>
				-	il termine entro il quale l'opera deve essere completata non pu&ograve; superare i tre anni dall'inizio dei lavori.<br/>
			g)  di aver provveduto agli adempimenti previsti dal d.lgs. 81/2008 (vedi allegata tabella B);<br/>		
			h)  di aver provveduto alla verifica della documentazione prevista in merito agli adempimenti dell'impresa affidataria e delle imprese esecutrici e dei lavoratori autonomi nelle ipotesi di cui al comma 9, lettera a) e b), dell'art. 90 del d.lgs. n. 81 del 09/04/2008;<br/>
			i)  di allegare, ai sensi del d.lgs. n. 81/2008, la dichiarazione dell'organico medio annuo distinto per qualifica, la dichiarazione relativa all'applicazione del contratto collettivo ai lavoratori dipendenti dell'impresa e i dati identificativi della ditta esecutrice dei lavori necessari ai fini dell'acquisizione del D.U.R.C.;<br/>
			j)  di essere a conoscenza che per le opere ricomprese nell'ambito di applicazione del d.P.R. n.81/2008, ai sensi dell'art. 90, c. 9 lett. c), deve essere trasmessa, al Comune, copia della notifica preliminare precedentemente o contestualmente trasmessa alla ASL e all'Ufficio Provinciale del Lavoro competenti per territorio, ove questa &egrave; prevista, e di essere consapevole che l'inosservanza del suddetto obbligo impedisce l'inizio dei lavori;<br/>
			k)  che, nei casi disciplinati dall'art. 24 del d.P.R. n. 380/01, entro quindici giorni dalla data dell'ultimazione dei lavori di finitura dell'intervento, &egrave; tenuto a presentare all'ufficio comunale competente domanda di rilascio del certificato di agibilit&agrave; con le modalit&agrave; di cui all'art. 25 d.P.R. n.380/01;<br/>
			l)  ${datiPratica.campiaggiuntivi[57].campiAggiuntivi.label}:<br/>
				<div style="padding-left: 17px;"><c:set var="nomeCampo" value="campiaggiuntivi[57].valore" scope="page"/>
				<%@ include file="../common/fieldtextarea.jsp"%>
				</div><br/>
			m)  ${datiPratica.campiaggiuntivi[58].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[58].valore" scope="page"/>
									<c:set var="size" value="80" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%><br/>
				${datiPratica.campiaggiuntivi[59].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[59].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[60].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[60].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[61].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[61].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[62].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[62].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;					
									<br/>
				${datiPratica.campiaggiuntivi[63].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[63].valore" scope="page"/>
									<c:set var="size" value="30" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[64].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[64].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[65].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[65].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[66].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[66].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
									<br/>
			n)  ${datiPratica.campiaggiuntivi[67].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[67].valore" scope="page"/>
									<c:set var="size" value="80" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%><br/>
				${datiPratica.campiaggiuntivi[68].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[68].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[69].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[69].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[70].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[70].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[71].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[71].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;					
									<br/>
				${datiPratica.campiaggiuntivi[72].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[72].valore" scope="page"/>
									<c:set var="size" value="30" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[73].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[73].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[74].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[74].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[75].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[75].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
									<br/>
			o)  ${datiPratica.campiaggiuntivi[76].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[76].valore" scope="page"/>
									<c:set var="size" value="80" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%><br/>
				${datiPratica.campiaggiuntivi[77].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[77].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[78].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[78].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[79].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[79].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[80].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[80].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;					
									<br/>
				${datiPratica.campiaggiuntivi[81].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[81].valore" scope="page"/>
									<c:set var="size" value="30" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[82].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[82].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[83].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[83].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[84].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[84].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
									<br/>
			p)  ${datiPratica.campiaggiuntivi[85].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[85].valore" scope="page"/>
									<c:set var="size" value="80" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%><br/>
				${datiPratica.campiaggiuntivi[86].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[86].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[87].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[87].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[88].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[88].valore" scope="page"/>
									<c:set var="size" value="20" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[89].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[89].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;					
									<br/>
				${datiPratica.campiaggiuntivi[90].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[90].valore" scope="page"/>
									<c:set var="size" value="30" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[91].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[91].valore" scope="page"/>
									<c:set var="size" value="5" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[92].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[92].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
				${datiPratica.campiaggiuntivi[93].campiAggiuntivi.label}: <c:set var="nomeCampo" value="campiaggiuntivi[93].valore" scope="page"/>
									<c:set var="size" value="15" scope="page"/>
									<%@ include file="../common/fieldinputtext.jsp"%>&nbsp;&nbsp;
									<br/>
			q)  provveder&agrave; al pagamento della TOSAP al momento del rilascio dell'atto autorizzativo (per l'installazione di strutture su suolo pubblico)
				</td></tr>				
				<tr><td><br/></td></tr>
				<tr><td colspan="2" STYLE="text-align: center;"><strong>E SOLLEVA</strong></td></tr>
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
							<!-- <td><form:checkbox path="allegati[${j }].caricato" /></td>-->							
							<td><form:input type="file" size="20" path="allegati[${j}].allegato" />
							
							<input type="hidden" name="allegati[${j }].nomeFile" id="allegati[${j }].nomeFile" />
							
							<c:if test="${!empty item.uuidFile && item.uuidFile != ''}">
										<br />
										<portlet:resourceURL var="downloadAllegato" id="downloadAllegato">
											<portlet:param name="uuidAllegato" value="${item.uuidFile }" />
											<portlet:param name="fileNameAllegato" value="${item.nomeFile }" />
										</portlet:resourceURL>
										<a href="${downloadAllegato}" target="_blank"><spring:message code="link.download" /></a>
									
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
		<script language="javascript">${tab }();</script>
	</c:if>
	<%@ include file="../common/footer.jsp"%>
	<br />	
	<div class="buttonsDiv">
				<input name="Salva" type="submit" value="<spring:message code="button.salva" />" onclick="javascript:return(confirm('<spring:message code="message.confirmSave" />'))"/>
		<a href="${annullaUrl}" id="annulla" title="<spring:message code="button.annulla" />">
			<spring:message code="button.annulla" />
		</a>
	</div>	
</form:form>