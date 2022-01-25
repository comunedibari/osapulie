<fieldset>
	<legend>
		<spring:message code="label.legendPagamento" />
	</legend>
	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message	code="label.rimborso" />*:</label>
			</td>
			<td>
				<form:input path="rimborso" />
				<div><form:errors path="rimborso" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.modalita" />*:</label>
			</td>
			<td>
				<form:checkbox path="mandato" />&nbsp;<spring:message	code="label.mandato" /><br/>
				<form:checkbox path="assegno" />&nbsp;<spring:message code="label.assegno" /><br/>
				<form:checkbox path="accredito" />&nbsp;<spring:message code="label.accredito" /><br/>
				<div><form:errors path="mandato" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message	code="label.banca" />:</label>
			</td>
			<td>
				<form:input path="banca" size="40"/>
				<div><form:errors path="banca" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message code="label.filiale" />:</label>
			</td>
			<td>
				<form:input path="filiale" size="40"/>
				<div><form:errors path="filiale" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.iban" />:</label>
			</td>
			<td>
				<form:input path="iban" size="40"/>
				<div><form:errors path="iban" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.intestatoA" />:</label>
			</td>
			<td>
				<form:input path="intestatario" size="40" />
				<div><form:errors path="intestatario" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
</fieldset>

<fieldset>
	<legend>
		<label><spring:message code="label.motivo" />*</label>
	</legend>
	<table>
		<tr>
			<td>
				<form:textarea cssClass="textarea" path="motivo" cols="100" rows="5"/>  
				<div><form:errors path="motivo" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>	
</fieldset> 