<%@ include file="../../common/import.jsp"%>

<table class="genericTable">
	<%-- <tr>
		<td>
			<form:checkbox path="varCompNucleoFam" />&nbsp;
			<label><spring:message	code="label.checkVarCompNucleoFam" />*:</label>
			<div><form:errors path="varCompNucleoFam" cssStyle="color:red"/></div>
			&nbsp;<form:input path="varCompNuclDa" size="2" />&nbsp;
			<div><form:errors path="varCompNuclDa" cssStyle="color:red"/></div>
			<label><spring:message code="label.varCompNucleoFamA" />*:</label>&nbsp;
			<form:input path="varCompNuclA" size="2" />
			<div><form:errors path="varCompNuclA" cssStyle="color:red"/></div>
		</td>
	</tr> --%>
	<%-- <tr>
		<td>
			<label><spring:message code="label.note" />:</label>
		</td>
	</tr>
	<tr>
		<td>
			<form:textarea path="varCompNuclNote" rows="3" cols="100"/>
		</td>
	</tr>
	<tr>
		<td>
			<form:checkbox path="varDatiAnagrafici" />
			<label><spring:message code="label.checkVarDatiAnagrafici" /></label>
		</td>
	</tr> --%>
	
	<tr>
		<td>
			<div><form:errors path="varCompNucleoFam" cssStyle="color:red"/></div>
		</td>
	</tr>
	<tr>
		<td>
			<fieldset>
				<legend><spring:message code="label.legendVariazioneComponenti" /></legend>	
						
				<table class="genericTable">
					<tr>
						<th>
							<spring:message	code="label.cognome" />
						</th>
						<th>
							<spring:message code="label.nome" />
						</th>
						<th>
							<spring:message code="label.dataN" />
						</th>
						<th>
							<spring:message code="label.cf" />
						</th>
						<th>
							<spring:message	code="label.tipoModificaComponenti" />
						</th>
					</tr>
					
					<c:forEach items="${datiTari.variazioneOccupanti}" var="occupante" varStatus="status">
					<tr>
						<td>
							<form:input path="variazioneOccupanti[${status.index}].cognome" size="20"/>
							<div><form:errors path="variazioneOccupanti[${status.index}].cognome" cssStyle="color:red"/></div>
						</td>
						<td>
							<form:input path="variazioneOccupanti[${status.index}].nome" size="20"/>
							<div><form:errors path="variazioneOccupanti[${status.index}].nome" cssStyle="color:red"/></div>
						</td>
						<td>
							<form:input path="variazioneOccupanti[${status.index}].dataNascita" id="varOccupanti[${status.index}].dataNascita" cssClass="data_input" size="10"/>
							<div><form:errors path="variazioneOccupanti[${status.index}].dataNascita" cssStyle="color:red"/></div>
						</td>
						<td>
							<form:input path="variazioneOccupanti[${status.index}].codiceFiscale" size="21"/>
							<div><form:errors path="variazioneOccupanti[${status.index}].codiceFiscale" cssStyle="color:red"/></div>
						</td>
						<td>
							<form:select path="variazioneOccupanti[${status.index}].tipoModifica">
								<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
								<form:options items="${tipoModificaVariazioneOccupantiMap}"/>
							</form:select>
							<div><form:errors path="variazioneOccupanti[${status.index}].tipoModifica" cssStyle="color:red"/></div>
						</td>
					</tr>
					</c:forEach>
				</table>
			</fieldset>	
		</td>
	</tr>
	<tr>
		<td>
			<label><spring:message code="label.note" />:</label>
		</td>
	</tr>
	<tr>
		<td>
			<form:textarea path="varDatiAnagraficiNote" rows="3" cols="100"/>
		</td>
	</tr>
	<tr>
		<td>
			<form:radiobutton path="tipologiaRichiestaDom" value="applicazione"/><spring:message code="label.applicazione" />
			<form:radiobutton path="tipologiaRichiestaDom" value="revoca"/><spring:message code="label.revoca" />
			<label><spring:message code="label.checkVarApplicaRevocaAgevolazioni" /></label>
			<form:errors path="tipologiaRichiestaDom" cssStyle="color:red"/>
		</td>
	</tr>
	<tr>
		<td>
			<div>
				<c:forEach items="${riduzioniDomesticheVariazione}" var="riduzioneDomestica" varStatus="status">
					<form:checkbox path="riduzioniDomesticheVariazione[${status.index}].codiceArticolo" 
						id="riduzioniDomesticheVar${status.index}" cssClass="riduzioniDomVar" value="${riduzioneDomestica.codiceArticolo}" 
						onclick="toggleRiduzione('#riduzioniDomesticheVar${status.index}', '#valoriRiduzioneDomVar${status.index}');"/>
						
					<label><spring:message code="label.chiedo.la" arguments="${riduzioneDomestica.descrizioneArticolo}" /></label>
					<ul id="valoriRiduzioneDomVar${status.index}" style="display: none;">
						<c:forEach items="${riduzioneDomestica.valori}" var="valore" varStatus="status2">
							<li>
								<form:radiobutton path="riduzioniDomesticheVariazione[${status.index}].valori[${status.index}].chiave" value="${valore.chiave}" label=" ${valore.valore}" />
							</li>
						</c:forEach>
					</ul>
					<div><form:errors path="riduzioniDomesticheVariazione[${status.index}].codiceArticolo" cssStyle="color:red"/></div> 
				</c:forEach>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<form:checkbox path="altroDom" />
			<label><spring:message code="label.checkVarAltro" />
			</label>
		</td>
	</tr>
	<tr>
		<td>
			<form:textarea path="altroNoteDom" rows="3" cols="100"/>								
			<div><form:errors path="altroNoteDom" cssStyle="color:red"/></div>
		</td>
	</tr>
</table>		