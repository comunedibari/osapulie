<%@ include file="../../common/import.jsp"%>

<fieldset>
	<legend><spring:message code="label.legend" /></legend>			
</fieldset>	

<br>
<table class="genericTable">
	<tr>
		<td align="center">
			<label><spring:message code="label.isResidente" /></label>
			<form:select id="select_is_residente" path="residente">
				<form:option value="false" label="NO"/>
		   		<form:option value="true" label="SI"/>
			</form:select>
		</td>
	</tr>
</table>

<div>
	<form:checkbox path="pertinenza" />
	<label><spring:message code="label.pertinenza" /></label>
</div>

<table id="residenteNo" class="genericTable">
	<tr>
		<td align="center">
			<label><spring:message code="label.nonResidenti" /></label>
		</td>
	</tr>
	<tr>
		<td>
			<form:checkbox path="nonResidente"/>
			<label><spring:message code="label.checkNonResidente" /></label>
			<div><form:errors path="nonResidente" cssStyle="color:red"/></div> 
		</td>	
	</tr>
	<tr>
		<td>
			<label><spring:message code="label.dichiarazioneNonResidenza" /></label>
			<form:input path="totaleNucleoInResidenza" size="3" />
			<div><form:errors path="totaleNucleoInResidenza" cssStyle="color:red"/></div> 
		</td>
	</tr>
	<tr>
		<td>	
			<label><spring:message code="label.note" />:</label>
		</td>
	</tr>
	<tr>
		<td>	
			<form:textarea path="noteNonResidente" maxlength="100" rows="3" cols="100"/>
		</td>
	</tr>
	<tr>
		<td>
			<div>
				<c:forEach items="${riduzioniDomesticheIscrizione}" var="riduzioneDomestica" varStatus="status">
					<form:checkbox path="riduzioniDomesticheIscrizione[${status.index}].codiceArticolo" 
						id="riduzioniDomesticheIsc${status.index}"  cssClass="riduzioniDomIsc" value="${riduzioneDomestica.codiceArticolo}"
						onclick="toggleRiduzione('#riduzioniDomesticheIsc${status.index}', '#valoriRiduzioneDomIsc${status.index}');"/>
					
					<label><spring:message code="label.chiedo.la" arguments="${riduzioneDomestica.descrizioneArticolo}" /></label>
					<ul id="valoriRiduzioneDomIsc${status.index}" style="display: none;">
						<c:forEach items="${riduzioneDomestica.valori}" var="valore" varStatus="status2">
							<li>
								<form:radiobutton path="riduzioniDomesticheIscrizione[${status.index}].valori[${status.index}].chiave" value="${valore.chiave}" label=" ${valore.valore}" />
							</li>
						</c:forEach>
					</ul>
					<div><form:errors path="riduzioniDomesticheIscrizione[${status.index}].codiceArticolo" cssStyle="color:red"/></div> 
				</c:forEach>
			</div>
		</td>
	</tr>
</table>
<div id="residenteSi">
	<table class="genericTable">
		<tr>
			<td align="center">
				<label><spring:message code="label.residenti" /></label>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.totaleComponenti" />*:</label>
				<form:input path="totaleNucleoFamiliare" />
				<div><form:errors path="totaleNucleoFamiliare" cssStyle="color:red"/></div> 
			</td>
		</tr>
		<tr>
			<td>
				<form:checkbox path="detenzioneImmobile" />
				<label><spring:message	code="label.checkDetenzioneImmobile" /></label>
				<div><form:errors path="detenzioneImmobile" cssStyle="color:red"/></div> 
			</td>
		</tr>
	</table>	
	<fieldset>
		<legend><spring:message code="label.legendComponenti" /></legend>	
				
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
			</tr>
	
			<c:forEach items="${datiTari.occupanti}" var="occupante" varStatus="status">
			<tr>
				<td>
					<form:input path="occupanti[${status.index}].cognome" size="20"/>
					<div><form:errors path="occupanti[${status.index}].cognome" cssStyle="color:red"/></div>
				</td>
				
				<td>
					<form:input path="occupanti[${status.index}].nome" size="20"/>
					<div><form:errors path="occupanti[${status.index}].nome" cssStyle="color:red"/></div>
				</td>
				<td>
					<form:input path="occupanti[${status.index}].dataNascita" id="occupanti[${status.index}].dataNascita" cssClass="data_input" size="10"/>
					<div><form:errors path="occupanti[${status.index}].dataNascita" cssStyle="color:red"/></div>
				</td>
				<td>
					<form:input path="occupanti[${status.index}].codiceFiscale" size="21"/>
					<div><form:errors path="occupanti[${status.index}].codiceFiscale" cssStyle="color:red"/></div>
				</td>
			</tr>
			</c:forEach>
		</table>
	</fieldset>					
</div>
