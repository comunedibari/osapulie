<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="mostraFascicoloUrl">
	<portlet:param name="action" value="${mostraFascicoloAction}" />
</portlet:actionURL>
<div class="mainDiv protocolloDetail">
	<c:choose>
		<c:when test="${!empty dettaglioProtocollo && !empty dettaglioProtocollo.protocolloResponse && empty dettaglioProtocollo.errore}">	
			<h3><spring:message code="label.dettagliopratica" /></h3>
			<fieldset>
				<legend><spring:message code="label.intestazione"/></legend>
				<table class="genericTable">
		 		<tr>
		 			<td>
		 				<label><spring:message code="label.numProt"/>:</label>
		 			</td>
		 			<td>
		 				<c:out value="${dettaglioProtocollo.protocolloResponse.numeroProtocollo}" />
		 			</td>
		 			<td>
		 				<label><spring:message code="label.dataRichiesta"/>:</label>
		 			</td>
		 			<td>
		 				<fmt:formatDate value="${dataProtocollazione}" pattern="dd/MM/yyyy - HH:mm"/>
		 			</td>
		 		</tr>
		 		
		 		<tr>
		 			<td>
		 				<label><spring:message code="label.amministrazione"/>:</label>
		 			</td>
		 			<td>
			 			<c:out value="${dettaglioProtocollo.protocolloResponse.amministrazione}"/>
		 			</td>
		 			<td>
		 				<label><spring:message code="label.aoo"/>:</label>
		 			</td>
		 			<td>
			 			<c:out value="${dettaglioProtocollo.protocolloResponse.areaOrganizzativaOmogenea}"/>
		 			</td>
		 		</tr>
		 		
		 		<tr>
		 			<td>
		 				<label><spring:message code="label.oggetto"/>:</label>
		 			</td>
		 			<td colspan="3">
			 			<c:out value="${dettaglioProtocollo.protocolloResponse.oggetto}"/>
		 			</td>
		 		</tr>
		 		
		 		<tr>
		 			<td>
		 				<label><spring:message code="label.mittente"/>:</label>
		 			</td>
		 			<td>
		 				<c:if test="${!empty dettaglioProtocollo.protocolloResponse.mittente.personaFisica}">
		 					<c:out value="${dettaglioProtocollo.protocolloResponse.mittente.personaFisica.cognome} ${dettaglioProtocollo.protocolloResponse.mittente.personaFisica.nome}" />
		 					&nbsp;-&nbsp;<c:out value="${dettaglioProtocollo.protocolloResponse.mittente.personaFisica.codiceFiscale}"/>
		 				</c:if>
		 			</td>
		 			<td>
		 				<label><spring:message code="label.destinatario"/>:</label>
		 			</td>
		 			<td>
		 				<c:forEach items="${dettaglioProtocollo.protocolloResponse.destinatari}" var="destinazione">
		 					<c:if test="${!empty destinazione.persona}">
			 					<c:out value="${destinazione.personaFisica.cognome} ${destinazione.personaFisica.nome}" /><br/>
			 				</c:if>
			 				<c:if test="${!empty destinazione.areaOrganizzativaOmogenea}">
			 					<c:out value="${destinazione.areaOrganizzativaOmogenea} - ${destinazione.amministrazione}" /><br/>
			 				</c:if>			 					
		 				</c:forEach>
		 			</td>
		 		</tr>
		 	</table>
		</fieldset>
		
		<c:if test="${comuneErogatore.downloadAllegati}">
			<fieldset>
				<legend><spring:message code="label.documenti"/></legend>
				<fieldset>
					<legend><spring:message code="label.documentoPrincipale"/></legend>
					<table class="genericTable">
				 		<tr>
				 			<td>
				 				<label><spring:message code="label.documento"/>:</label>
				 			</td>
				 			<td>
				 				<c:out value="${dettaglioProtocollo.protocolloResponse.documento.nomeFile}" />
				 			</td>
				 		</tr>
				 		<tr>
				 			<td>
				 				<label><spring:message code="label.titolo"/>:</label>
				 			</td>
				 			<td>
				 				<c:out value="${dettaglioProtocollo.protocolloResponse.documento.titolo}"/>
				 			</td>
				 		</tr>
				 		<tr>
				 			<td>
				 				<label><spring:message code="label.dettaglio"/>:</label>
				 			</td>
				 			<td>
				 				<c:out value="${dettaglioProtocollo.protocolloResponse.documento.dettaglio}"/>
				 			</td>
				 		</tr>
				 		<tr>
				 			<td>
				 				<label><spring:message code="label.scarica"/>:</label>
				 			</td>
				 			<td>
				 				<portlet:resourceURL var="downloadDocumentoUrl" id="downloadDocumento">
									<portlet:param name="id" value="${dettaglioProtocollo.protocolloResponse.documento.collocazioneTelematica}" />
									<portlet:param name="codiceIstat" value="${comuneErogatore.codiceIstat}" />
								</portlet:resourceURL>
				 				<a href="${downloadDocumentoUrl}" title="<spring:message code="label.scarica"/>" target="_blank">
				 					<c:out value="${dettaglioProtocollo.protocolloResponse.documento.nomeFile}" />
				 				</a>
				 			</td>
				 		</tr>
			 		</table>
		 		</fieldset>
		 		
			 	<c:if test="${!empty dettaglioProtocollo.protocolloResponse.allegati}">
				 	<fieldset>
						<legend><spring:message code="label.allegati"/></legend>
				 		<table class="genericTable">
				 			<tr>
				 				<th>
				 					<spring:message code="label.numero"/>
				 				</th>
				 				<th>
				 					<spring:message code="label.documento"/>
				 				</th>
				 				<th>
				 					<spring:message code="label.scarica"/>
				 				</th>
				 			</tr>
				 			<c:forEach items="${dettaglioProtocollo.protocolloResponse.allegati}" var="allegato" varStatus="status">
					 			<c:if test="${!empty allegato.documento}">
						 			<tr>
							 			<td style="border-bottom: 1px dotted gray;">
							 				<c:out value="${status.count}"/>
							 			</td>				 			
							 			<td style="border-bottom: 1px dotted gray;">
							 				<spring:message code="label.titolo"/>:&nbsp;<strong><c:out value="${allegato.documento.titolo}"/></strong><br/>
							 				<spring:message code="label.dettaglio"/>:&nbsp;<strong><c:out value="${allegato.documento.dettaglio}"/></strong>
							 			</td>				 			
							 			<td style="border-bottom: 1px dotted gray;">
							 				<portlet:resourceURL var="downloadDocumentoUrl" id="downloadDocumento">
												<portlet:param name="id" value="${allegato.documento.collocazioneTelematica}" />
												<portlet:param name="codiceIstat" value="${comuneErogatore.codiceIstat}" />
											</portlet:resourceURL>
							 				<a href="${downloadDocumentoUrl}" title="<spring:message code="label.scarica"/>">
							 					<c:out value="${allegato.documento.nomeFile}" />
							 				</a>
							 			</td>				 			
							 		</tr>					 		
						 		</c:if>
					 		</c:forEach>			 		
				 		</table>
				 	</fieldset>
				</c:if>
			</fieldset>
		</c:if>
	</c:when>
	<c:otherwise>	
		<h3><spring:message code="label.noProtocollo"/></h3>				
		<fieldset>
			<legend><spring:message code="label.errore"/></legend>	
			<table class="genericTable">
				<tr>
					<td>
						<spring:message code="label.codiceErrore"/>
					</td>
					<td>
						<strong><c:out value="${dettaglioProtocollo.errore.codice}"/></strong>
					</td>
				</tr>
				<tr>
					<td>
						<spring:message code="label.descrizioneErrore"/>
					</td>
					<td>
						<strong><c:out value="${dettaglioProtocollo.errore.descrizione}"/></strong>
					</td>
				</tr>
			</table>
		</fieldset>		
	</c:otherwise>
</c:choose>
	<div class="container_pulsante">
		<a class="custom_pulsante" href="${mostraFascicoloUrl}">
			<spring:message code="button.annulla" />
		</a>
	</div>
</div>