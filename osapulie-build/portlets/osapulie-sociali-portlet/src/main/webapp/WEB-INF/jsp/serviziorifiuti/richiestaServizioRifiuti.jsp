<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneUrlCambio">
	<portlet:param name="ope" value="cambioSoggetto" />
</portlet:actionURL>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="servizioRifiutiReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="idFormCambio" value="cambioSoggetto" />

<div class="mainDiv richiestaServizioRifiuti">
	<form:form id="${idFormCambio}" action="${dichiarazioneUrlCambio}" method="post" commandName="soggettoRichiedente">
		<c:if test="${empty download}">
		
			<div class="marginBottom10">
				<label><strong><spring:message code="label.soggetto" />:</strong></label>&nbsp;&nbsp;
				<select name="codFisc">
					<c:forEach var="item" begin="0" items="${componentiNucleoFamiliare}">
						<option value="${item.codiceFiscale}" <c:if test="${soggettoRichiedente.cf == item.codiceFiscale}"> selected="selected" </c:if>="">${item.cognome}
							${item.nome}</option>
					</c:forEach>
				</select>
				<input type="submit" name="cambio" value='<spring:message code="button.back" />'>
			</div>
		 </c:if>
	</form:form>
		
	<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiServiziRifiuti">
		<input type="hidden" name="codFisc" value="${soggettoRichiedente.cf}" />
	
		<c:if test="${empty download}">
			
			<fieldset>
				<legend>
					<spring:message code="label.legendRichiedente" />
				</legend>
				<table class="genericTable">
					<tr>
						<td width="25%">
							<label><spring:message code="label.cognome" />:</label>
						</td>
						<td width="25%">
							${datiServiziRifiuti.cognome}
						</td>
						<td width="25%">
							<label><spring:message code="label.nome" />:</label>
						</td>
						<td width="25%">
							${datiServiziRifiuti.nome}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.comuneResidenza" />:</label>
						</td>
						<td>
							${datiServiziRifiuti.comune}
						</td>
						<td>
							<label><spring:message code="label.provRes" />:</label>
						</td>
						<td>
							${datiServiziRifiuti.provincia}
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.indRes" />:</label>
						</td>
						<td colspan="3">
							${datiServiziRifiuti.indirizzo}
						</td>
					</tr>
				</table>
			<table class="genericTable">
				<tr>
					<td>
						<label><spring:message code="label.telefono" />: </label>
					</td>
					<td>
						<form:input path="telefono" /> 
						<div><form:errors path="telefono" cssStyle="color:red"/></div>
					</td>	  
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.email" />: </label>
					</td>
					<td>
						<form:input path="email" /> 
						<div><form:errors path="email" cssStyle="color:red"/></div>
					</td>	  
				</tr>
				<tr>
			       <td width="25%">
				  	  	<label><spring:message code="label.comeContattare" /></label>
				  	</td>
					<td>	
						  <form:select path="modalitaContatto">  
			                <form:option value="telefono"><spring:message code="label.telefono"/></form:option>  
			                <form:option value="email"><spring:message code="label.email"/></form:option>  
			             </form:select>  
					</td>
				</tr>
			</table>
			</fieldset>

			
	 		<%-- TIPOLOGIA DI MATERIALE --%>
	 		<fieldset>
			   <legend><spring:message code="label.materiali" /></legend>
			   <table class="genericTable">
			    	<tr>
				   		<c:forEach var="tipoMateriale" items="${tipologieMateriali}" varStatus="status">
					   		<c:if test="${status.count%3==0}">
						   			<td><form:checkbox path="materiali" value="${tipoMateriale}" label="${tipoMateriale}" /></td>  
								</tr>
								<tr>
					   		</c:if>
						   	<c:if test="${status.count%3>0}">
						 		<td><form:checkbox path="materiali" value="${tipoMateriale} " label="${tipoMateriale}" /></td>  
						   	</c:if>
				   		</c:forEach>
			   		</tr>
			   </table>
	       </fieldset>
	       
	        <table class="genericTable">
	        	<tr>
	        		<td width="25%">
						<label><spring:message code="label.altro" />: </label>
					</td>
					<td>
					    <form:textarea path="altro" rows="5" cols="50"  />
					</td>
				</tr>
				<tr>
					<td>
						<label><spring:message code="label.note" />: </label>
					</td>
					<td>
						<form:textarea path="note" rows="5" cols="50"  />
					</td>
				</tr>
			</table>
			
			<div class="container_pulsante">
				<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
			</div>
			
		</c:if>
		
		<c:if test="${!empty download}">
			<div class="container_pulsante">
				<a href="${dichiarazioneReportURL}" class="custom_pulsante"><spring:message code="link.dichiarazione" /></a>
				<span class="spacer"></span>
				<a href="<portlet:renderURL portletMode="view"/>" class="custom_pulsante"><spring:message code="button.home" /></a>
			</div>
		</c:if>
		
	</form:form>
</div>