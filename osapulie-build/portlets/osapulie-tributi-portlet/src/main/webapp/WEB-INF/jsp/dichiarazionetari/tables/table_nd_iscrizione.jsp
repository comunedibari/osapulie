<%@ include file="../../common/import.jsp"%>

<div><form:errors path="iscrizioneNonDom" cssStyle="color:red"/></div> 
<table class="genericTable" id="non_domestica_table">
 	<c:forEach items="${datiTari.iscrizioneNonDom}" var="iscrizione" varStatus="status">
	
	<tr class="padding-top-th">
		<th colspan="5">
			<spring:message	code="label.indirizzo.immobile" />*
		</th>
	 </tr>
	 <tr>
		<td colspan="5">
			<osapulie:stradario id="stradarioImmobile_iscrizioneNonDom_${status.index}" 
				viaName="iscrizioneNonDom[${status.index}].indirizzo" 
				civicoName="iscrizioneNonDom[${status.index}].civico" 
				esponenteName="iscrizioneNonDom[${status.index}].esponente" 
				viaOptionValue="${datiTari.iscrizioneNonDom[status.index].indirizzo}" 
				civicoOptionValue="${datiTari.iscrizioneNonDom[status.index].civico}" 
				esponenteValue="${datiTari.iscrizioneNonDom[status.index].esponente}" 
				viaOptionText="${datiTari.iscrizioneNonDom[status.index].indirizzoTextHidden}"
				civicoOptionText="${datiTari.iscrizioneNonDom[status.index].civicoTextHidden}" 
				viaTextHiddenName="iscrizioneNonDom[${status.index}].indirizzoTextHidden"
				civicoTextHiddenName="iscrizioneNonDom[${status.index}].civicoTextHidden"
				localitaHiddenName="iscrizioneNonDom[${status.index}].localitaHidden"
				localitaValue="${datiTari.iscrizioneNonDom[status.index].localitaHidden}"
				codiceViaHiddenName="iscrizioneNonDom[${status.index}].codiceViaHidden"
				codiceViaValue="${datiTari.iscrizioneNonDom[status.index].codiceViaHidden}"
				loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
			<div><form:errors path="iscrizioneNonDom[${status.index}].indirizzo" cssStyle="color:red"/></div>
			<div><form:errors path="iscrizioneNonDom[${status.index}].esponente" cssStyle="color:red"/></div>
			<br>
		</td>
	</tr>
	
	<tr>
		<th>
			<spring:message code="label.cap" />*
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
			<spring:message code="label.suffisso" />
		</th>
		<th>
			<spring:message code="label.sez" />
		</th>
		<th>
			<spring:message code="label.foglioCatastale" />*
		</th>
		<th>
			<spring:message code="label.particella" />*
		</th>
		<th>
			<spring:message code="label.sub" />
		</th>
		<th>
			<spring:message code="label.codiceAteco" />*
		</th>
		<th>
			<spring:message code="label.utenza.riduzione" />*
		</th>
		<th>
			<spring:message code="label.tipologia.superficie" />*
		</th>
		<th>
			<spring:message code="label.superficie.totale" />*
		</th>
		<th>
			<spring:message code="label.intassabile.fissi" />
		</th>
		<th>
			<spring:message code="label.intassabile.altre" />
		</th>
		<th>
			<spring:message code="label.tassabile.superficie" />*
		</th>
	</tr>
	<tr class="border-bottom padding-bottom-td">
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].cap" size="2" maxlength="5"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].cap" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].piano" size="2" maxlength="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].piano" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].scala" size="2" maxlength="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].scala" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].interno" size="2" maxlength="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].interno" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].suffisso" size="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].suffisso" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].sezione" size="3"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].sezione" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].foglio" size="3"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].foglio" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].particella" size="3"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].particella" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].subalterno" size="3"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].subalterno" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].codAteco" size="3"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].codAteco" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:select id="iscrizioneNonDom[${status.index}].utenzaConRiduzione" path="iscrizioneNonDom[${status.index}].utenzaConRiduzione">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
				<form:option value="si" label="SI"/>
				<form:option value="no" label="NO"/>
			</form:select>
			<div><form:errors path="iscrizioneNonDom[${status.index}].utenzaConRiduzione" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:select id="iscrizioneNonDom[${status.index}].tipologiaSuperficie" path="iscrizioneNonDom[${status.index}].tipologiaSuperficie">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
				<form:option value="C" label="Coperta"/>
				<form:option value="S" label="Scoperta"/>
			</form:select>
			<div><form:errors path="iscrizioneNonDom[${status.index}].tipologiaSuperficie" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].superficieTotale" size="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].superficieTotale" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].intassabileFissi" size="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].intassabileFissi" cssStyle="color:red"/></div>
		</td>								
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].intassabileAltre" size="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].intassabileAltre" cssStyle="color:red"/></div>
		</td>							
		<td>
			<form:input path="iscrizioneNonDom[${status.index}].superficieTassabile" size="2"/>
			<div><form:errors path="iscrizioneNonDom[${status.index}].superficieTassabile" cssStyle="color:red"/></div>
			</td>
		</tr>
	</c:forEach>
</table>