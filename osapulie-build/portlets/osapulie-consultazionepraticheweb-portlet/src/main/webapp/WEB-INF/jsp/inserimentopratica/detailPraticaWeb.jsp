<%@ include file="../common/common.jsp"%>
<script src="/gestionepratiche-portlet/javascript/funzioniTab.js" type="text/javascript"></script>
<script src="/gestionepratiche-portlet/javascript/funzioni.js" type="text/javascript"></script>
			
<portlet:renderURL var="annullaUrl" />

<div id="tab">
    <ul>
    	<li id="link_apertura" class="activelink"><a href="javascript:vis_apertura()"><spring:message code="tab.dati" /></a></li>
        <li id="link_documenti" ><a href="javascript:vis_documenti()"><spring:message code="tab.allegati" /></a></li>              
     </ul>
    </div>
    <div class="schede" id="apertura">
	<table style="width: 80%;">
		
		<tr>
			<th>
				<spring:message code="label.tipologia" />:
			</th>
			<td>
				<c:out value="${datiPratica.tipologia.descrizione}"/>
			</td>
			</tr>
		<tr>
			<th>
				<spring:message code="label.dataRichiesta" />:
			</th>
			<td>
				<fmt:formatDate value="${datiPratica.dataRichiesta.time}" pattern="dd/MM/yyyy" />
			</td>			
		</tr>					
		<tr>
			<th>
				<spring:message code="label.oggettoRichiesta" />:
			</th>
			<td>
				<c:out value="${datiPratica.oggettoRichiesta}"/>				
			</td>
		</tr>
		<tr>
			<td colspan="2"><fieldset>
			<legend><strong><spring:message code="label.richiedente"/></strong></legend>
				<table style="width: 100%;">
					<tr>
		<th>
		<spring:message code="label.nominativoReferente" />:
		</th>
		<td>
			<c:out value="${datiPratica.richNominativo}"/>
		</td>	
		<th>
		<spring:message code="label.indirizzoReferente" />:
		</th>
		<td>
			<c:out value="${datiPratica.richIndirizzo}"/>
		</td>
	</tr>
	<tr>
		<th>
			<spring:message code="label.capReferente" />:
		</th>
		<td>
			<c:out value="${datiPratica.richCap}"/>
		</td>	
		<th>
			<spring:message code="label.comuneReferente" />:
		</th>		
			<td>
				<c:out value="${datiPratica.richComune.denominazione}"/>				
			</td>
		
	</tr>
	<tr>
		<th>
		<spring:message code="label.piva" />:
		</th>
		<td>
			<c:out value="${datiPratica.richPiva}"/>
		</td>
		<th>
			<spring:message code="label.cf" />:
		</th>
		<td>
			<c:out value="${datiPratica.richCf}"/>
		</td>
	</tr>	
	<tr>
		<th>
		<spring:message code="label.cell" />:
		</th>
		<td>
			<c:out value="${datiPratica.richCell}"/>
		</td>
		<th>
		<spring:message code="label.email" />:
		</th>
		<td>
			<c:out value="${datiPratica.richEmail}"/>
		</td>
	</tr>	
				</table>
			</fieldset>
			</td>
			</tr>
			<tr>
			<td colspan="2">
			<fieldset>
				<legend><strong><spring:message code="label.immobile"/></strong></legend>
				<table style="width: 100%;">
					<tr>
						<th>
							<spring:message code="label.denominazione" />:
						</th>
						<td>
							<c:out value="${datiPratica.immobile_den}"/>							
						</td>
						<th>
							<spring:message code="label.indirizzo" />:
						</th>
						<td>
							<c:out value="${datiPratica.immobile_ind}"/>	
						</td>
				</tr>		
					<tr>
						<th>
							<spring:message code="label.cap" />:
						</th>
						<td>
							<c:out value="${datiPratica.immobile_cap}"/>	
						</td>
						<th>
						<spring:message code="label.comune" />:
				</th>
						<td>
							<c:out value="${datiPratica.immobile_comune.denominazione}"/>
						</td>
						
						
				</tr>	
				
				<tr>
						<th>
							<spring:message code="label.foglio" />:
						</th>
						<td>
							<c:out value="${datiPratica.immobile_foglio}"/>	
						</td>
						<th>
						<spring:message code="label.part" />:
				</th>
						<td>
							<c:out value="${datiPratica.immobile_part}"/>
						</td>						
						
				</tr>	
				<tr>
						<th>
							<spring:message code="label.sub" />:
						</th>
						<td>
							<c:out value="${datiPratica.immobile_sub}"/>
						</td>
						<th>
						<spring:message code="label.cat" />:
				</th>
						<td>
							<c:out value="${datiPratica.immobile_cat}"/>
						</td>		
				</tr>	
				<tr>
					<th>
							<spring:message code="label.tipologia" />:
						</th>
						<td>
							<c:out value="${datiPratica.immobileTipo.descrizione}"/>
						</td>		
					<th>
							<spring:message code="label.areasin" />:
						</th>
						<td>
							<c:out value="${datiPratica.immobile_areaSin}"/>
						</td>
								
				</tr>	
				</table>
			</fieldset>										
			</td>
		</tr>		
	</table>

	</div>    
<!-- ************************************************************************************ -->

    <!-- ***************************ALLEGATI PRATICA*******************************************-->
   <div class="schede" id="documenti" style="display: none;">
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
		<a href="${annullaUrl}" id="annulla" title="<spring:message code="button.annulla" />">
			<spring:message code="button.annulla" />
		</a>
	</div>	