<%@ include file="../../common/import.jsp"%>

<div><form:errors path="variazioneDom" cssStyle="color:red"/></div> 
<table class="genericTable customtable" id="domestica_table">
 <c:forEach items="${datiTari.variazioneDom}" var="immobili" varStatus="status">
	 
	<tr class="padding-top-th">
		<c:if test="${empty datiTari.variazioneDom[status.index].identificativoUtenza}">
		<th rowspan="4" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateVariazione" value="${datiTari.variazioneDom[status.index].identificativoUtenza}" />
	 	</th>
		</c:if>
		<c:if test="${!empty datiTari.variazioneDom[status.index].identificativoUtenza}">
		<th rowspan="3" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateVariazione" value="${datiTari.variazioneDom[status.index].identificativoUtenza}" />
	 	</th>
		<th colspan="13">
				<spring:message	code="label.indirizzo.immobile" />:
				<label>${datiTari.variazioneDom[status.index].indirizzoTextHidden},&nbsp;${datiTari.variazioneDom[status.index].civicoTextHidden}
				<c:if test="${!empty datiTari.variazioneDom[status.index].esponente}">
					&nbsp;${datiTari.variazioneDom[status.index].esponente}
				</c:if>
				</label>
		</th>
		</c:if>				
	 </tr>
	<c:if test="${empty datiTari.variazioneDom[status.index].identificativoUtenza}">
		<tr>
			<td colspan="13">
				<spring:message	code="label.indirizzo.immobile" />*:
				<osapulie:stradario id="stradarioImmobile_variazioneDom_${status.index}" 
					viaName="variazioneDom[${status.index}].indirizzo" 
					civicoName="variazioneDom[${status.index}].civico" 
					esponenteName="variazioneDom[${status.index}].esponente" 
					viaOptionValue="${datiTari.variazioneDom[status.index].indirizzo}" 
					civicoOptionValue="${datiTari.variazioneDom[status.index].civico}" 
					esponenteValue="${datiTari.variazioneDom[status.index].esponente}" 
					viaOptionText="${datiTari.variazioneDom[status.index].indirizzoTextHidden}"
					civicoOptionText="${datiTari.variazioneDom[status.index].civicoTextHidden}" 
					viaTextHiddenName="variazioneDom[${status.index}].indirizzoTextHidden"
					civicoTextHiddenName="variazioneDom[${status.index}].civicoTextHidden"
					localitaHiddenName="variazioneDom[${status.index}].localitaHidden"
					localitaValue="${datiTari.variazioneDom[status.index].localitaHidden}"
					codiceViaHiddenName="variazioneDom[${status.index}].codiceViaHidden"
					codiceViaValue="${datiTari.variazioneDom[status.index].codiceViaHidden}"
					loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
				<div><form:errors path="variazioneDom[${status.index}].indirizzo" cssStyle="color:red"/></div>
				<div><form:errors path="variazioneDom[${status.index}].esponente" cssStyle="color:red"/></div>
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
			<spring:message code="label.cat" />
		</td>
		<td>
			<spring:message code="label.uso" />*
		</td>
		<td>
			<spring:message code="label.damq" />*
		</td>
		<td>
			<spring:message code="label.amq" />*
		</td>
	</tr>
	 <tr>
	 	<c:if test="${!empty datiTari.variazioneDom[status.index].identificativoUtenza}">
		 	<td>
				<label>${datiTari.variazioneDom[status.index].cap}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].piano}</label>
			</td>
		 	<td>
				<label>${datiTari.variazioneDom[status.index].scala}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].interno}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].suffisso}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].sezione}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].foglio}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].particella}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].subalterno}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].categoria}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].tipo}</label>
			</td>
			<td>
				<label>${datiTari.variazioneDom[status.index].varDiSuperficieDa}</label>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].varDiSuperficieA" size="2"/>
				<div><form:errors path="variazioneDom[${status.index}].varDiSuperficieA" cssStyle="color:red"/></div>
			</td>
	 	</c:if>
	 	<c:if test="${empty datiTari.variazioneDom[status.index].identificativoUtenza}">
			<td>
				<form:input path="variazioneDom[${status.index}].cap" size="2" maxlength="5"/>
				<div><form:errors path="variazioneDom[${status.index}].cap" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].piano" size="2" maxlength="2"/>
				<div><form:errors path="variazioneDom[${status.index}].piano" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].scala" size="2" maxlength="2"/>
				<div><form:errors path="variazioneDom[${status.index}].scala" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].interno" size="2" maxlength="2"/>
				<div><form:errors path="variazioneDom[${status.index}].interno" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].suffisso" size="2"/>
				<div><form:errors path="variazioneDom[${status.index}].suffisso" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].sezione" size="3"/>
				<div><form:errors path="variazioneDom[${status.index}].sezione" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].foglio" size="3"/>
				<div><form:errors path="variazioneDom[${status.index}].foglio" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].particella" size="3"/>
				<div><form:errors path="variazioneDom[${status.index}].particella" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].subalterno" size="3"/>
				<div><form:errors path="variazioneDom[${status.index}].subalterno" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:select path="variazioneDom[${status.index}].categoria">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
				    <form:options items="${categorieCatastaliMap}" />
				</form:select>
				<div><form:errors path="variazioneDom[${status.index}].categoria" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:select id="variazioneDom[${status.index}].tipo" path="variazioneDom[${status.index}].tipo">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
					<form:options items="${tipologieImmobileMap}" />
				</form:select>
				<div><form:errors path="variazioneDom[${status.index}].tipo" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].varDiSuperficieDa" size="2"/>
				<div><form:errors path="variazioneDom[${status.index}].varDiSuperficieDa" cssStyle="color:red"/></div>
			</td>
			<td>
				<form:input path="variazioneDom[${status.index}].varDiSuperficieA" size="2"/>
				<div><form:errors path="variazioneDom[${status.index}].varDiSuperficieA" cssStyle="color:red"/></div>
			</td>
		</c:if>
		</tr>
		<tr>
			<td colspan="13" class="noborder"> 
			</td>
		</tr>
	</c:forEach>
</table>
<span><spring:message code="label.dati.indicativi"/>.</span><br>