<%-- Allegati --%>
<div>
	<fieldset>
		<legend><spring:message code="legend.allegati" /></legend>
		<table class="genericTable">
			<tr>
				<td width="180">
					<label><spring:message code="label.allegato.uno" /></label>
				</td>
				<td>
					<form:input path="allegatoUno" />
				</td>
				<td width="180">
					<label><spring:message code="label.allegato.due" /></label>
				</td>
				<td>
					<form:input path="allegatoDue" />
				</td>
			</tr>
			<tr>
				<td width="180">
					<label><spring:message code="label.allegato.tre" /></label>
				</td>
				<td>
					<form:input path="allegatoTre" />
				</td>
				<td width="180">
					<label><spring:message code="label.allegato.quattro" /></label>
				</td>
				<td>
					<form:input path="allegatoQuattro" />
				</td>
			</tr>
			<tr>
				<td colspan="4"><br><strong><spring:message code="label.allegati.vincolo" /></strong></td>
			</tr>
		</table>
	</fieldset>	
	<div>
		<fieldset>
			<table class="genericTable">
				<tr>
					<td>	
						<label><spring:message code="label.note" />:</label>
					</td>
				</tr>
				<tr>
					<td>	
						<form:textarea path="noteGenerali" rows="3" cols="100"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
</div>