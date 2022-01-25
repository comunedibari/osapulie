<%@ include file="../../common/import.jsp"%>

<div><form:errors path="variazioneNonDom" cssStyle="color:red"/></div> 
<table class="genericTable customtable" id="non_domestica_table">
<c:forEach items="${datiTari.variazioneNonDom}" var="variazione" varStatus="status">
	
	<tr class="padding-top-th">
		<c:if test="${empty datiTari.variazioneNonDom[status.index].identificativoUtenza}">
		<th rowspan="4" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateVariazione" value="${datiTari.variazioneNonDom[status.index].identificativoUtenza}" />
	 	</th>
		</c:if>
		<c:if test="${!empty datiTari.variazioneNonDom[status.index].identificativoUtenza}">
	 	<th rowspan="3" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateVariazione" value="${datiTari.variazioneNonDom[status.index].identificativoUtenza}" />
	 	</th>
		<th colspan="12">
				<spring:message	code="label.indirizzo.immobile" />:
				<label>${datiTari.variazioneNonDom[status.index].indirizzoTextHidden}, ${datiTari.variazioneNonDom[status.index].civicoTextHidden}</label>
				<c:if test="${!empty datiTari.variazioneNonDom[status.index].esponente}">
					&nbsp;${datiTari.variazioneNonDom[status.index].esponente}
				</c:if>
				</label>
		</th>
		</c:if>				
	 </tr>
	<c:if test="${empty datiTari.variazioneNonDom[status.index].identificativoUtenza}">
		<tr>
			<td colspan="12">
				<osapulie:stradario id="stradarioImmobile_variazioneNonDom_${status.index}" 
					viaName="variazioneNonDom[${status.index}].indirizzo" 
					civicoName="variazioneNonDom[${status.index}].civico" 
					esponenteName="variazioneNonDom[${status.index}].esponente" 
					viaOptionValue="${datiTari.variazioneNonDom[status.index].indirizzo}" 
					civicoOptionValue="${datiTari.variazioneNonDom[status.index].civico}" 
					esponenteValue="${datiTari.variazioneNonDom[status.index].esponente}" 
					viaOptionText="${datiTari.variazioneNonDom[status.index].indirizzoTextHidden}"
					civicoOptionText="${datiTari.variazioneNonDom[status.index].civicoTextHidden}" 
					viaTextHiddenName="variazioneNonDom[${status.index}].indirizzoTextHidden"
					civicoTextHiddenName="variazioneNonDom[${status.index}].civicoTextHidden"
					localitaHiddenName="variazioneNonDom[${status.index}].localitaHidden"
					localitaValue="${datiTari.variazioneNonDom[status.index].localitaHidden}"
					codiceViaHiddenName="variazioneNonDom[${status.index}].codiceViaHidden"
					codiceViaValue="${datiTari.variazioneNonDom[status.index].codiceViaHidden}"
					loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
				<div><form:errors path="variazioneNonDom[${status.index}].indirizzo" cssStyle="color:red"/></div>
				<div><form:errors path="variazioneNonDom[${status.index}].esponente" cssStyle="color:red"/></div>
				<br>
			</td>
		</tr>
	</c:if>
	
	<tr>
		<td>
			<spring:message code="label.cap" />*
		</td>
		<td>
			<spring:message code="label.piano" />
		</td>
		<td>
			<spring:message code="label.sc" />
		</td>
		<td>
			<spring:message code="label.int" />
		</td>
		<td>
			<spring:message code="label.suffisso" />
		</td>
		<td>
			<spring:message code="label.sez" />
		</td>
		<td>
			<spring:message code="label.foglioCatastale" />*
		</td>
		<td>
			<spring:message code="label.particella" />*
		</td>
		<td>
			<spring:message code="label.sub" />
		</td>
		<td>
			<spring:message code="label.codiceAteco" />*
		</td>
		<td>
			<spring:message code="label.utenza.riduzione" />
		</td>
		<td>
			<spring:message code="label.tipologia.superficie" />
		</td>
		<td>
			<spring:message code="label.disuperficie.damq" />*
		</td>
		<td>
			<spring:message code="label.disuperficie.amq" />*
		</td>
		<td>
			<spring:message code="label.dicategoria.da" />
		</td>
		<td>
			<spring:message code="label.dicategoria.a" />
		</td>
	</tr>
	
	<tr class="border-bottom padding-bottom-td">
		<c:if test="${!empty datiTari.variazioneNonDom[status.index].identificativoUtenza}">
		<td>
			<label>${datiTari.variazioneNonDom[status.index].cap}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].piano}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].scala}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].interno}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].suffisso}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].sezione}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].foglio}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].particella}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].subalterno}</label>
		</td>
		<td>
			<form:input path="variazioneNonDom[${status.index}].codAteco" size="3"/>
			<div><form:errors path="variazioneNonDom[${status.index}].codAteco" cssStyle="color:red"/></div>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].utenzaConRiduzione}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].tipologiaSuperficie}</label>
		</td>
		<td>
			<label>${datiTari.variazioneNonDom[status.index].varDiSuperficieDa}</label>
		</td>
		<td>
			<form:input path="variazioneNonDom[${status.index}].varDiSuperficieA" size="2"/>
			<div><form:errors path="variazioneNonDom[${status.index}].varDiSuperficieA" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="variazioneNonDom[${status.index}].varDiCategoriaDa" size="3"/>
			<div><form:errors path="variazioneNonDom[${status.index}].varDiCategoriaDa" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="variazioneNonDom[${status.index}].varDiCategoriaA" size="3"/>
			<div><form:errors path="variazioneNonDom[${status.index}].varDiCategoriaA" cssStyle="color:red"/></div>
		</td>
		</c:if>
		<c:if test="${empty datiTari.variazioneNonDom[status.index].identificativoUtenza}">
			<td>
				<form:input path="variazioneNonDom[${status.index}].cap" size="2" maxlength="5"/>
				<div><form:errors path="variazioneNonDom[${status.index}].cap" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].piano" size="2" maxlength="2"/>
				<div><form:errors path="variazioneNonDom[${status.index}].piano" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].scala" size="2" maxlength="2"/>
				<div><form:errors path="variazioneNonDom[${status.index}].scala" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].interno" size="2" maxlength="2"/>
				<div><form:errors path="variazioneNonDom[${status.index}].interno" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].suffisso" size="2"/>
				<div><form:errors path="variazioneNonDom[${status.index}].suffisso" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].sezione" size="3"/>
				<div><form:errors path="variazioneNonDom[${status.index}].sezione" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].foglio" size="3"/>
				<div><form:errors path="variazioneNonDom[${status.index}].foglio" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].particella" size="3"/>
				<div><form:errors path="variazioneNonDom[${status.index}].particella" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].subalterno" size="3"/>
				<div><form:errors path="variazioneNonDom[${status.index}].subalterno" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].codAteco" size="3"/>
				<div><form:errors path="variazioneNonDom[${status.index}].codAteco" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:select id="variazioneNonDom[${status.index}].utenzaConRiduzione" path="variazioneNonDom[${status.index}].utenzaConRiduzione">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
					<form:option value="si" label="SI"/>
					<form:option value="no" label="NO"/>
				</form:select>
			</td>
			<td>
				<form:select id="variazioneNonDom[${status.index}].tipologiaSuperficie" path="variazioneNonDom[${status.index}].tipologiaSuperficie">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
					<form:option value="C" label="Coperta"/>
					<form:option value="S" label="Scoperta"/>
				</form:select>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].varDiSuperficieDa" size="2"/>
				<div><form:errors path="variazioneNonDom[${status.index}].varDiSuperficieDa" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].varDiSuperficieA" size="2"/>
				<div><form:errors path="variazioneNonDom[${status.index}].varDiSuperficieA" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].varDiCategoriaDa" size="3"/>
				<div><form:errors path="variazioneNonDom[${status.index}].varDiCategoriaDa" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneNonDom[${status.index}].varDiCategoriaA" size="3"/>
				<div><form:errors path="variazioneNonDom[${status.index}].varDiCategoriaA" cssStyle="color:red"/></div>
			</td>
		</c:if>
		</tr>
	</c:forEach>
</table>