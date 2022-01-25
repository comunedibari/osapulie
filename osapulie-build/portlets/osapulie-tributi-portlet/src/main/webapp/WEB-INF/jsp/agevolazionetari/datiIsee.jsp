<fieldset>
	<legend>
		<spring:message code="label.legendDatiIseeFamiglia" />
	</legend>

	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message code="label.isee.famiglia1"/></label>
			</td>
			<td>
				<form:input path="numComponentiNucleoFamiliare" size="10"/>
				<div><form:errors path="numComponentiNucleoFamiliare" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.famiglia2"/></label>
			</td>
			<td>
				<form:input path="numSoggettiHandicap" size="10"/>
				<div><form:errors path="numSoggettiHandicap" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.famiglia3"/></label>
			</td>
			<td>
				<form:checkbox path="presenzaFigliUnGenitore" />
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.famiglia4"/></label>
			</td>
			<td>
				<form:checkbox path="presenzaFigliAttivitaGenitori" />
			</td>
		</tr>
	</table>
</fieldset>

<fieldset>
	<legend>
		<spring:message code="label.legendDatiIseePatrimonio" />
	</legend>

	<table class="genericTable">
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio1"/></label>
			</td>
			<td width="40" align="right">
				<label><spring:message code="label.isee.patrimonio1.rc"/></label>
			</td>
			<td>
				<form:input path="rc" size="10"/>
				<div><form:errors path="rc" cssStyle="color:red"/></div>
			</td>
			<td width="50" align="right">
				<label><spring:message code="label.isee.patrimonio1.pag"/></label>
			</td>
			<td>
				<form:input path="pag" size="10"/>
				<div><form:errors path="pag" cssStyle="color:red"/></div>
			</td>
			<td  width="80" align="right">
				<label><spring:message code="label.isee.patrimonio1.rcpag"/></label>
			</td>
			<td>
				<form:input path="rcpag" size="10"/>
				<div><form:errors path="rcpag" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio2"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio2.rpm"/></label>
			</td>
			<td>
				<form:input path="rpm" size="10"/>
				<div><form:errors path="rpm" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio3"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio3.dc"/></label>
			</td>
			<td>
				<form:input path="dc" size="10"/>
				<div><form:errors path="dc" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio4"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio4.pmo"/></label>
			</td>
			<td>
				<form:input path="pmo" size="10"/>
				<div><form:errors path="pmo" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio5"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio5.dpm"/></label>
			</td>
			<td>
				<form:input path="dpm" size="10"/>
				<div><form:errors path="dpm" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio6"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio6.pim"/></label>
			</td>
			<td>
				<form:input path="pim" size="10"/>
				<div><form:errors path="pim" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio7"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio7.dpi"/></label>
			</td>
			<td>
				<form:input path="dpi" size="10"/>
				<div><form:errors path="dpi" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio8"/></label>
				<form:input path="codiceFiscaleContribuente" size="20"/>
				<div><form:errors path="codiceFiscaleContribuente" cssStyle="color:red"/></div>
			</td>
			<td colspan="6">
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<label><spring:message code="label.isee.patrimonio9.da"/></label>
				<form:input path="dataAttestazione" id="data_isee_da_input" size="10" cssClass="data_input"/>
				<form:errors path="dataAttestazione" cssStyle="color:red"/>
				<label><spring:message code="label.isee.patrimonio9.a"/></label>
				<form:input path="dataScadenza" id="data_isee_a_input" size="10" cssClass="data_input"/>
				<form:errors path="dataScadenza" cssStyle="color:red"/>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio10"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio10.euro"/></label>
			</td>
			<td>
				<form:input path="valoreISE" size="10"/>
				<div><form:errors path="valoreISE" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio11"/></label>
			</td>
			<td colspan="5" align="right">
				<label></label>
			</td>
			<td>
				<form:input path="scalaEquivalenza" size="10"/>
				<div><form:errors path="scalaEquivalenza" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.isee.patrimonio12"/></label>
			</td>
			<td colspan="5" align="right">
				<label><spring:message code="label.isee.patrimonio12.euro"/></label>
			</td>
			<td>
				<form:input path="valoreISEE" size="10"/>
				<div><form:errors path="valoreISEE" cssStyle="color:red"/></div>
			</td>
		</tr>
	</table>
</fieldset>