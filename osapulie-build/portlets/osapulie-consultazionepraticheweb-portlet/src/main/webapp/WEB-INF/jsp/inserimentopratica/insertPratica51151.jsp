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
			<%@ include file="insertPraticaInizio.jsp"%>
			<tr>
				<td><c:set var="size" value="60" scope="page" /> <c:set
						var="nomeCampo" value="campiaggiuntivi[0].valore" scope="page" />
					${datiPratica.campiaggiuntivi[0].campiAggiuntivi.label}:*</td>
				<td><%@ include file="../common/fieldinputtext.jsp"%>
				</td>
			</tr>
			<tr>
				<td><c:set var="size" value="60" scope="page" /> 
					<c:set var="nomeCampo" value="campiaggiuntivi[1].valore" scope="page" />
					${datiPratica.campiaggiuntivi[1].campiAggiuntivi.label}:*
				</td>
				<td>
					<%@ include file="../common/fieldinputtext.jsp"%>
				</td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td colspan="2">
					<c:set var="nomeCampoChk1" value="campiaggiuntivi[2].valore" scope="page" /> 
					<c:set var="valoriCampoChk1" value="${datiPratica.campiaggiuntivi[2].campiAggiuntivi.listaValori}" scope="page" /> 
					<c:set var="valoreCampoChk1" value="${datiPratica.campiaggiuntivi[2].valore}" scope="page" />
					<c:forTokens items="${valoriCampoChk1}" delims="," var="tokenName" varStatus="count">
						<c:set var="check" value="" scope="page" />
						<c:forTokens items="${valoreCampoChk1}" delims="," var="valoriName" varStatus="status">
							<c:if test="${valoriName==tokenName}">
								<c:set var="check" value="checked" scope="page" />
							</c:if>
						</c:forTokens>
						<input type="checkbox" name="${nomeCampoChk1}" value="${tokenName}" ${check} />
						&nbsp;${tokenName}
						<c:if test="${count.count==2}">
							&nbsp;${datiPratica.campiaggiuntivi[3].campiAggiuntivi.label}
							<c:set var="nomeCampo" value="campiaggiuntivi[3].valore" scope="page" />
							&nbsp;<form:input id="text${nomeCampo }" path="${nomeCampo }" size="4"/>&nbsp;<form:errors path="${nomeCampo }" cssStyle="color:red"/>
							&nbsp;${datiPratica.campiaggiuntivi[4].campiAggiuntivi.label}
							&nbsp;<c:set var="nomeCampo" value="campiaggiuntivi[4].valore" scope="page"/> 
							<%@ include file="../common/fielddate.jsp"%>
						</c:if>
						<c:if test="${count.count==3}">
							&nbsp;${datiPratica.campiaggiuntivi[5].campiAggiuntivi.label}
							<c:set var="nomeCampo" value="campiaggiuntivi[5].valore" scope="page" />
							&nbsp;<form:input id="text${nomeCampo }" path="${nomeCampo }" size="4"/>&nbsp;<form:errors path="${nomeCampo }" cssStyle="color:red"/>
							&nbsp;${datiPratica.campiaggiuntivi[6].campiAggiuntivi.label}
							&nbsp;<c:set var="nomeCampo" value="campiaggiuntivi[6].valore" scope="page"/> 
							<%@ include file="../common/fielddate.jsp"%>
						</c:if>
						<br />
					</c:forTokens> 
					<form:errors path="${nomeCampoChk1}" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<%@ include file="insertPraticaImmobile.jsp"%>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td><c:set var="nomeCampo" value="campiaggiuntivi[8].valore"
						scope="page" />
					${datiPratica.campiaggiuntivi[8].campiAggiuntivi.label}:*</td>
				<td><%@ include file="../common/fieldinputtext.jsp"%>
				</td>
			</tr>
			<tr>
				<td><c:set var="nomeCampo" value="campiaggiuntivi[9].valore"
						scope="page" />
					${datiPratica.campiaggiuntivi[9].campiAggiuntivi.label}:*</td>
				<td><%@ include file="../common/fieldtextarea.jsp"%>
				</td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<%@ include file="insertPraticaDichiarante.jsp"%>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td colspan="2">
					${datiPratica.campiaggiuntivi[10].campiAggiuntivi.label}:
					<br/>
					<c:set var="nomeCampoRdb1" value="campiaggiuntivi[10].valore" scope="page"/>
					<c:set var="valoriCampoRdb1" value="${datiPratica.campiaggiuntivi[10].campiAggiuntivi.listaValori}" scope="page"/>
					<c:set var="valoreCampoRdb1" value="${datiPratica.campiaggiuntivi[10].valore}" scope="page" />
					<c:forTokens items="${valoriCampoRdb1}" delims="," var="tokenName" varStatus="count">
						<c:if test="${count.count < 3}">
							<input type="radio" name="${nomeCampoRdb1 }" value="${tokenName}" <c:if test="${valoreCampoRdb1 == tokenName}">checked</c:if> /> 
							&nbsp;${tokenName}
						</c:if>
						<c:if test="${count.count==3}">
							<fieldset>
								<legend>
									<input type="radio" name="${nomeCampoRdb1 }" value="${tokenName}" <c:if test="${valoreCampoRdb1 == tokenName}">checked</c:if> /> 
									&nbsp;${tokenName}
								</legend>
								<c:set var="nomeCampo" value="campiaggiuntivi[11].valore" scope="page" /> 
								<c:set var="size" value="85" scope="page" />
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="nomeCampo" value="campiaggiuntivi[12].valore" scope="page" /> 
								<c:set var="size" value="30" scope="page" />
								${datiPratica.campiaggiuntivi[12].campiAggiuntivi.label}: 
								<%@ include	file="../common/fieldinputtext.jsp"%>
								<br/>
								<c:set var="nomeCampo" value="campiaggiuntivi[13].valore" scope="page" /> 
								<c:set var="size" value="30" scope="page" />
								${datiPratica.campiaggiuntivi[13].campiAggiuntivi.label}: 
								<%@ include	file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="nomeCampo" value="campiaggiuntivi[14].valore" scope="page" /> 
								<c:set var="size" value="50" scope="page" />
								${datiPratica.campiaggiuntivi[14].campiAggiuntivi.label}: 
								<%@ include	file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="nomeCampo" value="campiaggiuntivi[15].valore" scope="page" /> 
								<c:set var="size" value="5" scope="page" />
								${datiPratica.campiaggiuntivi[15].campiAggiuntivi.label}: 
								<%@ include	file="../common/fieldinputtext.jsp"%>
							</fieldset>
						</c:if>
						<br/>
					</c:forTokens>
					<form:errors path="${nomeCampoRdb1 }" cssStyle="color:red"/>								
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<fieldset>
						<legend>
							avente titolo alla presentazione della <b>SEGNALAZIONE CERTIFICATA DI INIZIO ATTIVITA' EDILIZIA</b> in quanto:
						</legend>
						<c:set var="nomeCampoRdb2" value="campiaggiuntivi[16].valore" scope="page"/>
						<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[16].campiAggiuntivi.listaValori}" scope="page"/>
						<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[16].valore}" scope="page" />
						<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="count">
							<input type="radio" name="${nomeCampoRdb2 }" value="${tokenName}" <c:if test="${valoreCampo == tokenName}">checked</c:if> /> 
							&nbsp;${tokenName}
							<c:if test="${count.count==2}">
								&nbsp;
								<c:set var="nomeCampoAgg" value="campiaggiuntivi[17].valore" scope="page" /> 
								<c:set var="size" value="45" scope="page" />
								<form:input id="text${nomeCampoAgg }" path="${nomeCampoAgg }" size="${size }"/>&nbsp;
								<form:errors path="${nomeCampoAgg }" cssStyle="color:red"/>
							</c:if>
							<c:if test="${count.count==3}">
								&nbsp;
								<c:set var="nomeCampoAgg" value="campiaggiuntivi[18].valore" scope="page" /> 
								<c:set var="size" value="100" scope="page" />
								<form:input id="text${nomeCampoAgg }" path="${nomeCampoAgg }" size="${size }"/>&nbsp;
								<form:errors path="${nomeCampoAgg }" cssStyle="color:red"/>
							</c:if>
							<c:if test="${count.count==5}">
								&nbsp;
								<c:set var="nomeCampoAgg" value="campiaggiuntivi[19].valore" scope="page" /> 
								<c:set var="size" value="120" scope="page" />
								<form:input id="text${nomeCampoAgg }" path="${nomeCampoAgg }" size="${size }"/>&nbsp;
								<form:errors path="${nomeCampoAgg }" cssStyle="color:red"/>
							</c:if>
							<br/>
						</c:forTokens>
						<form:errors path="${nomeCampoRdb2 }" cssStyle="color:red"/>								
					</fieldset>
				</td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td colspan="2" STYLE="text-align: left;"><strong>Consapevole che:</strong>
					<ul>
						<li><strong>
							le dichiarazioni mendaci e le falsità in atti comportano le sanzioni previste dall'art. 76 del d.P.R. 445/2000 e la decadenza dai benefici conseguiti con il provvedimento emanato sulla base della dichiarazione non veritiera;</strong>
						</li>
						<li><strong>
							la L. n. 241/1990, art. 19 comma 6, prevede - per chiunque dichiari e/o attesti falsamente l'esistenza dei requisiti e dei presupposti posti come condizioni necessarie per la presentazione della S.C.I.A. - la reclusione da 1 a 3 anni, salvo che il fatto non costituisca più grave reato.</strong>
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td colspan="2" STYLE="text-align: center;"><strong>SEGNALA</strong></td>
			</tr>
			<tr>
				<td colspan="2">l'inizio della attività edilizia, qualificata quale:
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table style="width: 97%;" border="1">
						<c:set var="nomeCampoChk2" value="campiaggiuntivi[20].valore" scope="page"/>
						<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[20].campiAggiuntivi.listaValori}" scope="page"/>
						<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[20].valore}" scope="page"/>
						<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="count">
							<c:set var="check" value="" scope="page"/>
							<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
									<c:if test="${valoriName==tokenName}">
										<c:set var="check" value="checked" scope="page"/>
									</c:if>
							</c:forTokens>
							<tr>
								<td>
									<input type="checkbox" name="${nomeCampoChk2 }" value="${tokenName}" ${check} />&nbsp;${tokenName}<br/>
									<c:if test="${count.count==9}">
										<c:set var="nomeCampoChkInt" value="campiaggiuntivi[96].valore" scope="page"/>
										<c:set var="valoriCampoChkInt" value="${datiPratica.campiaggiuntivi[96].campiAggiuntivi.listaValori}" scope="page"/>
										<c:set var="valoreCampoChkInt" value="${datiPratica.campiaggiuntivi[96].valore}" scope="page"/>
										<c:forTokens items="${valoriCampoChkInt}" delims="," var="tokenName" varStatus="count">
											<c:set var="check" value="" scope="page"/>
											<c:forTokens items="${valoreCampoChkInt}" delims="," var="valoriName" varStatus="status">
													<c:if test="${valoriName==tokenName}">
														<c:set var="check" value="checked" scope="page"/>
													</c:if>
											</c:forTokens>
											<input type="checkbox" name="${nomeCampoChkInt }" value="${tokenName}" ${check} />&nbsp;${tokenName}&nbsp;&nbsp;&nbsp;
										</c:forTokens>
									</c:if>
									<c:if test="${count.count==10}">
										<br/>
										<c:set var="nomeCampo" value="campiaggiuntivi[21].valore" scope="page" />
										<%@ include file="../common/fieldtextarea.jsp"%>
									</c:if>
								</td>
							</tr>
						</c:forTokens>
						<form:errors path="${nomeCampoChk2 }" cssStyle="color:red"/>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" STYLE="text-align: center;"><strong>DICHIARA :</strong></td>
			</tr>
			<tr>
				<td colspan="2">
					<ol style="list-style:lower-alpha outside none;">
						<c:set var="valoriCampoOL" value="${datiPratica.campiaggiuntivi[22].campiAggiuntivi.listaValori}" scope="page"/>	
						<c:forTokens items="${valoriCampoOL}" delims="," var="tokenName" varStatus="listCnt">
							<li>${tokenName}</li>
							<c:if test="${listCnt.count==3}"><!-- lettera c) -->
								<c:set var="nomeCampoChk3" value="campiaggiuntivi[23].valore" scope="page"/>
								<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[23].campiAggiuntivi.listaValori}" scope="page"/>
								<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[23].valore}" scope="page"/>
								<table style="width: 97%;">
									<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="list1chk">
										<c:set var="check" value="" scope="page"/>
										<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
											<c:if test="${valoriName==tokenName}">
												<c:set var="check" value="checked" scope="page"/>
											</c:if>
										</c:forTokens>
										<tr>
											<td>
												<c:if test="${list1chk.count<16}">
													<input type="checkbox" name="${nomeCampoChk3 }" value="${tokenName}" ${check} />&nbsp;${tokenName}<br/>
												</c:if>
											</td>
											<td width="15%">
												<c:if test="${list1chk.count<13}">
													<c:set var="indexNr" value="${23+list1chk.count}" scope="page" /> 
													<c:set var="nomeCampoNr" value="campiaggiuntivi[${indexNr}].valore" scope="page" /> 
													<c:set var="size" value="5" scope="page" />
													${datiPratica.campiaggiuntivi[23+list1chk.count].campiAggiuntivi.label}: 
													<form:input id="text${nomeCampoNr }" path="${nomeCampoNr }" size="${size }"/>
													<form:errors path="${nomeCampoNr }" cssStyle="color:red"/>
												</c:if>
											</td>
											<td width="20%">
												<c:if test="${list1chk.count<13}">
													<c:set var="indexDel" value="${35+list1chk.count}" scope="page" /> 
													<c:set var="nomeCampo" value="campiaggiuntivi[${indexDel}].valore" scope="page" /> 
													${datiPratica.campiaggiuntivi[35+list1chk.count].campiAggiuntivi.label}: 
													<%@ include file="../common/fielddate.jsp"%>
												</c:if>
											</td>
										</tr>
									</c:forTokens>&nbsp;
									<form:errors path="${nomeCampoChk3 }" cssStyle="color:red"/>
								</table>
							</c:if>
							<c:if test="${listCnt.count==4}"><!-- lettera d) -->
								<c:set var="nomeCampoRdb4" value="campiaggiuntivi[50].valore" scope="page"/>
								<c:set var="valoriCampoRdb4" value="${datiPratica.campiaggiuntivi[50].campiAggiuntivi.listaValori}" scope="page"/>
								<c:set var="valoreCampoRdb4" value="${datiPratica.campiaggiuntivi[50].valore}" scope="page"/>
								<c:forTokens items="${valoriCampoRdb4}" delims="," var="tokenName" varStatus="list3chk">
									<c:if test="${list3chk.count<3}">
										<input type="radio" name="${nomeCampoRdb4 }" value="${tokenName}" <c:if test="${valoreCampoRdb4 == tokenName}">checked</c:if> />
										&nbsp;${tokenName}
									</c:if>
									<c:if test="${list3chk.count==2}">
										<c:set var="nomeCampoChk4" value="campiaggiuntivi[51].valore" scope="page"/>
										<c:set var="valoriCampoChk4" value="${datiPratica.campiaggiuntivi[51].campiAggiuntivi.listaValori}" scope="page"/>
										<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[51].valore}" scope="page"/>
										<table style="width: 97%;">
											<c:forTokens items="${valoriCampoChk4}" delims="," var="tokenName" varStatus="list4chk">
												<c:set var="check" value="" scope="page"/>
												<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
													<c:if test="${valoriName==tokenName}">
														<c:set var="check" value="checked" scope="page"/>
													</c:if>
												</c:forTokens>
												<tr>
													<td>
														<input type="checkbox" name="${nomeCampoChk4 }" value="${tokenName}" ${check} />&nbsp;${tokenName}<br/>
													</td>
													<td>
														<c:set var="indexNr" value="${51+list4chk.count}" scope="page" /> 
														<c:set var="nomeCampoNr" value="campiaggiuntivi[${indexNr}].valore" scope="page" /> 
														<c:set var="size" value="5" scope="page" />
														${datiPratica.campiaggiuntivi[indexNr].campiAggiuntivi.label}: 
														<form:input id="text${nomeCampoNr }" path="${nomeCampoNr }" size="${size }"/>
														<form:errors path="${nomeCampoNr }" cssStyle="color:red"/>
													</td>
													<td>
														<c:set var="indexDel" value="${55+list4chk.count}" scope="page" /> 
														<c:set var="nomeCampo" value="campiaggiuntivi[${indexDel}].valore" scope="page" /> 
														${datiPratica.campiaggiuntivi[indexDel].campiAggiuntivi.label}: 
														<%@ include file="../common/fielddate.jsp"%>
													</td>
												</tr>
											</c:forTokens>
											<form:errors path="${nomeCampoChk4 }" cssStyle="color:red"/>
										</table>
									</c:if>
									<br/>
								</c:forTokens>
								<form:errors path="${nomeCampoRdb4 }" cssStyle="color:red"/>
							</c:if>
							<c:if test="${listCnt.count==11}"><!-- lettera k) -->
								&nbsp;
								<c:set var="size" value="66" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[61].valore" scope="page" />
								${datiPratica.campiaggiuntivi[61].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[62].valore" scope="page" />
								${datiPratica.campiaggiuntivi[62].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="45" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[63].valore" scope="page" />
								${datiPratica.campiaggiuntivi[63].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[64].valore" scope="page" />
								${datiPratica.campiaggiuntivi[64].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[65].valore" scope="page" />
								${datiPratica.campiaggiuntivi[65].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="62" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[66].valore" scope="page" />
								${datiPratica.campiaggiuntivi[66].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="5" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[67].valore" scope="page" />
								${datiPratica.campiaggiuntivi[67].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[68].valore" scope="page" />
								${datiPratica.campiaggiuntivi[68].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[69].valore" scope="page" />
								${datiPratica.campiaggiuntivi[69].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>
							</c:if>
							<c:if test="${listCnt.count==12}"><!-- lettera l) -->
								&nbsp;
								<c:set var="size" value="66" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[70].valore" scope="page" />
								${datiPratica.campiaggiuntivi[70].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[71].valore" scope="page" />
								${datiPratica.campiaggiuntivi[71].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="45" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[72].valore" scope="page" />
								${datiPratica.campiaggiuntivi[72].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[73].valore" scope="page" />
								${datiPratica.campiaggiuntivi[73].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[74].valore" scope="page" />
								${datiPratica.campiaggiuntivi[74].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="62" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[75].valore" scope="page" />
								${datiPratica.campiaggiuntivi[75].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="5" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[76].valore" scope="page" />
								${datiPratica.campiaggiuntivi[76].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[77].valore" scope="page" />
								${datiPratica.campiaggiuntivi[77].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[78].valore" scope="page" />
								${datiPratica.campiaggiuntivi[78].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>
							</c:if>
							<c:if test="${listCnt.count==13}"><!-- lettera m) -->
								&nbsp;
								<c:set var="size" value="66" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[79].valore" scope="page" />
								${datiPratica.campiaggiuntivi[79].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[80].valore" scope="page" />
								${datiPratica.campiaggiuntivi[80].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="45" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[81].valore" scope="page" />
								${datiPratica.campiaggiuntivi[81].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[82].valore" scope="page" />
								${datiPratica.campiaggiuntivi[82].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[83].valore" scope="page" />
								${datiPratica.campiaggiuntivi[83].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="62" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[84].valore" scope="page" />
								${datiPratica.campiaggiuntivi[84].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="5" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[85].valore" scope="page" />
								${datiPratica.campiaggiuntivi[85].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[86].valore" scope="page" />
								${datiPratica.campiaggiuntivi[86].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[87].valore" scope="page" />
								${datiPratica.campiaggiuntivi[87].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>
							</c:if>
							<c:if test="${listCnt.count==14}"><!-- lettera n) -->
								&nbsp;
								<c:set var="size" value="66" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[88].valore" scope="page" />
								${datiPratica.campiaggiuntivi[88].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[89].valore" scope="page" />
								${datiPratica.campiaggiuntivi[89].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="45" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[90].valore" scope="page" />
								${datiPratica.campiaggiuntivi[90].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="30" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[91].valore" scope="page" />
								${datiPratica.campiaggiuntivi[91].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>&nbsp;
								<c:set var="size" value="62" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[92].valore" scope="page" />
								${datiPratica.campiaggiuntivi[92].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="5" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[93].valore" scope="page" />
								${datiPratica.campiaggiuntivi[93].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[94].valore" scope="page" />
								${datiPratica.campiaggiuntivi[94].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								&nbsp;
								<c:set var="size" value="10" scope="page" /> 
								<c:set var="nomeCampo" value="campiaggiuntivi[95].valore" scope="page" />
								${datiPratica.campiaggiuntivi[95].campiAggiuntivi.label}
								<%@ include file="../common/fieldinputtext.jsp"%>
								<br/>
							</c:if>
							<c:if test="${listCnt.count==15}"><!-- lettera o) -->
								&nbsp;
								<c:set var="nomeCampo" value="campiaggiuntivi[60].valore" scope="page" />
								<%@ include file="../common/fieldtextarea.jsp"%>
								<br/>
							</c:if>
							<br/>
						</c:forTokens>
					</ol>
					
				</td>
			</tr>
			<tr>
				<td colspan="2" STYLE="text-align: center;"><strong>E SOLLEVA :</strong></td>
			</tr>
			<tr>
				<td colspan="2" STYLE="text-align: left;"></br>
					il comune di TARANTO da ogni responsabilità nei confronti dei terzi.
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
						<td><form:errors path="allegati[${j}]" cssStyle="color:red" /></td>
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