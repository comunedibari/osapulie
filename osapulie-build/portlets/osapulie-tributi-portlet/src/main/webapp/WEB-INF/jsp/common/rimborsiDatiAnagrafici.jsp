<fieldset id="div_personaFisica">
	<legend>
		<spring:message code="label.legendPersonaFisica" />
	</legend>
	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message	code="label.cognome" />*:</label>
			</td>
			<td>
				<form:input path="cognome" />
				<div><form:errors path="cognome" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.nome" />*:</label>
			</td>
			<td>
				<form:input path="nome" />
				<div><form:errors path="nome" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.cf" />*:</label>
			</td>
			<td>
				<form:input path="codiceFiscale" />
				<div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.dataN" />:</label>
			</td>
			<td class="liferayUiDate">
				<liferay-ui:input-date dayValue="${inputDataNascitaDay}" monthValue="${inputDataNascitaMonth}" yearValue="${inputDataNascitaYear}" dayParam="dataNascitaDay" monthParam="dataNascitaMonth" yearParam="dataNascitaYear" yearRangeStart="1900" yearRangeEnd="2030" />
			</td>
			<td>
				<label><spring:message	code="label.comN" />*:</label>
			</td>
			<td>
				<form:input path="comuneNascita" />
				<div><form:errors path="comuneNascita" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.prov" />*:</label>
			</td>
			<td>
				<form:input path="provinciaNascita" size="3"/>
				<div><form:errors path="provinciaNascita" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.sesso" />*:</label>
			</td>
			<td>
				<select name="sesso">	
					<option value=""></option>		
			    	<option value="M" <c:if test="${dati.sesso == 'M'}"> selected="selected" </c:if> >M</option>
			    	<option value="F" <c:if test="${dati.sesso == 'F'}"> selected="selected" </c:if> >F</option>
				</select>
		    	<div><form:errors path="sesso" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.indir" />*:</label>
			</td>
			<td>
				<form:input path="indirizzoResidenza" size="20"/>
				<div><form:errors path="indirizzoResidenza" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.comuneIscrizione" />*:</label>
			</td>
			<td>
				<form:input path="comuneResidenza" />
				<div><form:errors path="comuneResidenza" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.prov" />*:</label>
			</td>
			<td>
				<form:input path="provResidenza" size="3"/>
				<div><form:errors path="provResidenza" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.cap" />*:</label>
			</td>
			<td>
				<form:input path="capResidenza" size="8"/>
				<div><form:errors path="capResidenza" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.telefono" />*:</label>
			</td>
			<td>
				<form:input path="telefono" />
				<div><form:errors path="telefono" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
</fieldset>	
	
<fieldset  id="div_personaGiuridica">
	<legend>
		<spring:message code="label.legendPersonaGiuridica" />
	</legend>
	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message	code="label.ragsoc" />:</label>
			</td>
			<td>
				<form:input path="ragSociale" />
				<div><form:errors path="ragSociale" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.natura" />:</label>
			</td>
			<td>
				<form:input path="naturaGiuridica" />
				<div><form:errors path="naturaGiuridica" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.cf" />:</label>
			</td>
			<td>
				<form:input path="codiceFiscaleGiu" />
				<div><form:errors path="codiceFiscaleGiu" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.piva" />:</label>
			</td>
			<td>
				<form:input path="pIva" />
				<div><form:errors path="pIva" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.sede" />:</label>
			</td>
			<td>
				<form:input path="sedeLegale" size="30"/>
				<div><form:errors path="sedeLegale" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.comuneIscrizione" />:</label>
			</td>
			<td>
				<form:input path="comune" />
				<div><form:errors path="comune" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.prov" />:</label>
			</td>
			<td>
				<form:input path="provincia" size="3"/>
				<div><form:errors path="provincia" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.cap" />:</label>
			</td>
			<td>
				<form:input path="cap" size="8"/>
				<div><form:errors path="cap" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.telefono" />:</label>
			</td>
			<td>
				<form:input path="telefonoGiu" />
				<div><form:errors path="telefonoGiu" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
</fieldset>	

<fieldset>
	<legend>
		<spring:message code="label.legendRappresentante" />
	</legend>
	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message	code="label.cognome" />:</label>
			</td>
			<td>
				<form:input path="cognomeRapp" />
				<div><form:errors path="cognomeRapp" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.nome" />:</label>
			</td>
			<td>
				<form:input path="nomeRapp" />
				<div><form:errors path="nomeRapp" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.cf" />:</label>
			</td>
			<td>
				<form:input path="codiceFiscaleRapp" />
				<div><form:errors path="codiceFiscaleRapp" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.dataN" />:</label>
			</td>
			<td class="liferayUiDate">
				<liferay-ui:input-date dayValue="${inputDataNascitaRappDay}" monthValue="${inputDataNascitaRappMonth}" yearValue="${inputDataNascitaRappYear}" dayParam="dataNascitaRappDay" monthParam="dataNascitaRappMonth" yearParam="dataNascitaRappYear" yearRangeStart="1900" yearRangeEnd="2030" />
			</td>
			<td>
				<label><spring:message	code="label.comN" />:</label>
			</td>
			<td>
				<form:input path="comuneNascitaRapp" />
				<div><form:errors path="comuneNascitaRapp" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.prov" />:</label>
			</td>
			<td>
				<form:input path="provinciaNascitaRapp" size="3"/>
				<div><form:errors path="provinciaNascitaRapp" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.sesso" />:</label>
			</td>
			<td>
				<select name="sessoRapp">	
					<option></option>		
			    	<option value="M" <c:if test="${dati.sessoRapp == 'M'}"> selected="selected" </c:if> >M</option>
			    	<option value="F" <c:if test="${dati.sessoRapp == 'F'}"> selected="selected" </c:if> >F</option>
				</select>
				<div><form:errors path="sessoRapp" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.indir" />:</label>
			</td>
			<td>
				<form:input path="indirizzoResidenzaRapp" size="20"/>
				<div><form:errors path="indirizzoResidenzaRapp" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.comuneIscrizione" />:</label>
			</td>
			<td>
				<form:input path="comuneResidenzaRapp" />
				<div><form:errors path="comuneResidenzaRapp" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.prov" />:</label>
			</td>
			<td>
				<form:input path="provResidenzaRapp" size="3"/>
				<div><form:errors path="provResidenzaRapp" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.cap" />:</label>
			</td>
			<td>
				<form:input path="capResidenzaRapp" size="8"/>
				<div><form:errors path="capResidenzaRapp" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.telefono" />:</label>
			</td>
			<td>
				<form:input path="telefonoRapp" />
				<div><form:errors path="telefonoRapp" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.carica" />:</label>
			</td>
			<td colspan="5">
				<form:input path="naturaCarica" />
				<div><form:errors path="naturaCarica" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
</fieldset>