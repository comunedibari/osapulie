<fieldset>
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
				<label><spring:message	code="label.dataN" />*:</label>
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
				<form:input path="indirizzoResidenza" />
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
				<label><spring:message	code="label.telefono" />:</label>
			</td>
			<td>
				<form:input path="telefono" />
				<div><form:errors path="telefono" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.iban" />:</label>
			</td>
			<td colspan="5">
				<form:input path="iban" />
				<div><form:errors path="iban" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
</fieldset>		