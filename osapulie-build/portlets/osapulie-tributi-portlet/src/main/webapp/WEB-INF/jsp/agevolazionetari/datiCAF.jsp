<fieldset>
	<legend>
		<spring:message code="label.legendDatiCAF" />
	</legend>
	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message code="label.nominativoCAF" />*:</label>
			</td>
			<td>
				<form:input path="nominativoCAF" />
				<div><form:errors path="nominativoCAF" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message code="label.pecCAF" />*:</label>
			</td>
			<td>
				<form:input path="pecCAF" />
				<div><form:errors path="pecCAF" cssStyle="color:red"/></div>
			</td>
		</tr>	
		<tr>
			<td>
				<label><spring:message code="label.codiceFiscaleCAF" />*:</label>
			</td>
			<td>
				<form:input path="codiceFiscaleCAF" />
				<div><form:errors path="codiceFiscaleCAF" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message code="label.partitaIvaCAF" />*:</label>
			</td>
			<td>
				<form:input path="partitaIvaCAF" />
				<div><form:errors path="partitaIvaCAF" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
	<hr>
	<table class="genericTable">
		<tr>
			<td>
				<form:checkbox path="presaVisione" />
			</td>
			<td>
				<label><spring:message code="label.presavisione"/></label>
				<div><form:errors path="presaVisione" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
	
</fieldset>