<table class="genericTable" id="non_domestica_table">
	<tr>
		<th>
			<spring:message	code="label.indir" />*
		</th>
		<th>
			<spring:message code="label.num" />*
		</th>
		<th>
			<spring:message code="label.piano" />
		</th>
		<th>
			<spring:message code="label.sc" />
		</th>
		<th>
			<spring:message code="label.int" />
		</th>
		<th>
			<spring:message code="label.esp" />
		</th>
		<th>
			<spring:message code="label.suffisso" />
		</th>
		<th>
			<spring:message code="label.sez" />*
		</th>
		<th>
			<spring:message code="label.foglioCatastale" />*
		</th>
		<th>
			<spring:message code="label.particella" />*
		</th>
		<th>
			<spring:message code="label.sub" />*
		</th>
		<th>
			<spring:message code="label.codiceAteco" />*
		</th>
		<th id="cessazione_column">
			<spring:message code="label.mq" />*
		</th>
		<th id="common_column">
			<spring:message code="label.utenza.riduzione" />
		</th>
		<th id="common_column">
			<spring:message code="label.tipologia.superficie" />
		</th>
		<th id="iscrizione_column">
			<spring:message code="label.superficie.totale" />
		</th>
		<th id="iscrizione_column">
			<spring:message code="label.intassabile.fissi" />
		</th>
		<th id="iscrizione_column">
			<spring:message code="label.intassabile.altre" />
		</th>
		<th id="iscrizione_column">
			<spring:message code="label.tassabile.superficie" />
		</th>
		<th id="variazione_column">
			<spring:message code="label.disuperficie.damq" />
		</th>
		<th id="variazione_column">
			<spring:message code="label.disuperficie.amq" />
		</th>
		<th id="variazione_column">
			<spring:message code="label.dicategoria.da" />
		</th>
		<th id="variazione_column">
			<spring:message code="label.dicategoria.a" />
		</th>
	</tr>
	<c:forEach var="i" begin="1" end="5" step="1">
	<tr>
		<td>
			<form:input path="indirizzo${i}" size="15"/>
			<div><form:errors path="indirizzo${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="civico{i}" size="2"/>
			<div><form:errors path="civico${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="piano${i}" size="2" maxlength="2"/>
			<div><form:errors path="piano${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="scala${i}" size="2" maxlength="2"/>
			<div><form:errors path="scala${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="interno${i}" size="2" maxlength="2"/>
			<div><form:errors path="interno${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="esponente${i}" size="2"/>
			<div><form:errors path="esponente${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="suffisso${i}" size="2"/>
			<div><form:errors path="suffisso${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="sezione${i}" size="3"/>
			<div><form:errors path="sezione${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="foglio${i}" size="3"/>
			<div><form:errors path="foglio${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="particella${i}" size="3"/>
			<div><form:errors path="particella${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="subalterno${i}" size="3"/>
			<div><form:errors path="subalterno${i}" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="codAteco${i}" size="3"/>
			<div><form:errors path="codAteco${i}" cssStyle="color:red"/></div>
		</td>
		<td id="cessazione_column">
			<form:input path="mq${i}" size="2"/>
			<div><form:errors path="mq${i}" cssStyle="color:red"/></div>
		</td>
		<td id="common_column">
			<form:select id="utenzaConRiduzione${i}" path="utenzaConRiduzione${i}">
				<form:option value=" " label=" "/>
				<form:option value="si" label="SI"/>
				<form:option value="no" label="NO"/>
			</form:select>
		</td>
		<td id="common_column">
			<form:select id="tipologiaSuperficie${i}" path="tipologiaSuperficie${i}">
				<form:option value=" " label=" "/>
				<form:option value="C" label="Coperta"/>
				<form:option value="S" label="Scoperta"/>
			</form:select>
		</td>
		<td id="iscrizione_column">
			<form:input path="superficieTotale${i}" size="2"/>
			<div><form:errors path="superficieTotale${i}" cssStyle="color:red"/></div>
		</td>
		<td id="iscrizione_column">
			<form:input path="intassabileFissi${i}" size="2"/>
			<div><form:errors path="intassabileFissi${i}" cssStyle="color:red"/></div>
		</td>								
		<td id="iscrizione_column">
			<form:input path="intassabileAltre${i}" size="2"/>
			<div><form:errors path="intassabileAltre${i}" cssStyle="color:red"/></div>
		</td>							
		<td id="iscrizione_column">
			<form:input path="superficieTassabile${i}" size="2"/>
			<div><form:errors path="superficieTassabile${i}" cssStyle="color:red"/></div>
		</td>
		<td id="variazione_column">
			<form:input path="varDiSuperficieDa${i}" size="2"/>
			<div><form:errors path="varDiSuperficieDa${i}" cssStyle="color:red"/></div>
		</td>
		<td id="variazione_column">
			<form:input path="varDiSuperficieA${i}" size="2"/>
			<div><form:errors path="varDiSuperficieA${i}" cssStyle="color:red"/></div>
		</td>
		<td id="variazione_column">
			<form:input path="varDiCategoriaDa${i}" size="2"/>
			<div><form:errors path="varDiCategoriaDa${i}" cssStyle="color:red"/></div>
		</td>
		<td id="variazione_column">
			<form:input path="varDiCategoriaA${i}" size="2"/>
			<div><form:errors path="varDiCategoriaA${i}" cssStyle="color:red"/></div>
		</td>
	</tr>
	</c:forEach>
</table>