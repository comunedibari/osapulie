<%@ include file="../../common/import.jsp"%>

<div><form:errors path="cessazioneDom" cssStyle="color:red"/></div> 
<table class="genericTable customtable" id="domestica_table">
<c:forEach items="${datiTari.cessazioneDom}" var="immobili" varStatus="status">

	 <tr class="padding-top-th">
		<c:if test="${empty datiTari.cessazioneDom[status.index].identificativoUtenza}">
		<th rowspan="4" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateCessazione" value="${datiTari.cessazioneDom[status.index].identificativoUtenza}" />
	 	</th>
		</c:if>
		<c:if test="${!empty datiTari.cessazioneDom[status.index].identificativoUtenza}">
		<th rowspan="3" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateCessazione" value="${datiTari.cessazioneDom[status.index].identificativoUtenza}" />
	 	</th>
		<th colspan="12">
				<spring:message	code="label.indirizzo.immobile" />:
				<label>${datiTari.cessazioneDom[status.index].indirizzoTextHidden},&nbsp;${datiTari.cessazioneDom[status.index].civicoTextHidden}
				<c:if test="${!empty datiTari.cessazioneDom[status.index].esponente}">
					&nbsp;${datiTari.cessazioneDom[status.index].esponente}
				</c:if>
				</label>
		</th>
		</c:if>				
	 </tr>
	<c:if test="${empty datiTari.cessazioneDom[status.index].identificativoUtenza}">
		<tr>
			<td colspan="12">
				<spring:message	code="label.indirizzo.immobile" />*:
				<osapulie:stradario id="stradarioImmobile_cessazioneDom_${status.index}" 
					viaName="cessazioneDom[${status.index}].indirizzo" 
					civicoName="cessazioneDom[${status.index}].civico" 
					esponenteName="cessazioneDom[${status.index}].esponente" 
					viaOptionValue="${datiTari.cessazioneDom[status.index].indirizzo}" 
					civicoOptionValue="${datiTari.cessazioneDom[status.index].civico}" 
					esponenteValue="${datiTari.cessazioneDom[status.index].esponente}" 
					viaOptionText="${datiTari.cessazioneDom[status.index].indirizzoTextHidden}"
					civicoOptionText="${datiTari.cessazioneDom[status.index].civicoTextHidden}" 
					viaTextHiddenName="cessazioneDom[${status.index}].indirizzoTextHidden"
					civicoTextHiddenName="cessazioneDom[${status.index}].civicoTextHidden"
					localitaHiddenName="cessazioneDom[${status.index}].localitaHidden"
					localitaValue="${datiTari.cessazioneDom[status.index].localitaHidden}"
					codiceViaHiddenName="cessazioneDom[${status.index}].codiceViaHidden"
					codiceViaValue="${datiTari.cessazioneDom[status.index].codiceViaHidden}"
					loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
				<div><form:errors path="cessazioneDom[${status.index}].indirizzo" cssStyle="color:red"/></div>
				<div><form:errors path="cessazioneDom[${status.index}].esponente" cssStyle="color:red"/></div>
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
			<spring:message code="label.mq" />*
		</td>
	</tr>
	 <tr class="border-bottom padding-bottom-td">
	 	<c:if test="${!empty datiTari.cessazioneDom[status.index].identificativoUtenza}">
		<td>
			<label>${datiTari.cessazioneDom[status.index].cap}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].piano}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].scala}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].interno}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].suffisso}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].sezione}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].foglio}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].particella}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].subalterno}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].categoria}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].tipo}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneDom[status.index].mq}</label>
		</td>
		</c:if>
		<c:if test="${empty datiTari.cessazioneDom[status.index].identificativoUtenza}">
		<td>
			<form:input path="cessazioneDom[${status.index}].cap" size="2" maxlength="5"/>
			<div><form:errors path="cessazioneDom[${status.index}].cap" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].piano" size="2" maxlength="2"/>
			<div><form:errors path="cessazioneDom[${status.index}].piano" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].scala" size="2" maxlength="2"/>
			<div><form:errors path="cessazioneDom[${status.index}].scala" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].interno" size="2" maxlength="2"/>
			<div><form:errors path="cessazioneDom[${status.index}].interno" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].suffisso" size="2"/>
			<div><form:errors path="cessazioneDom[${status.index}].suffisso" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].sezione" size="3"/>
			<div><form:errors path="cessazioneDom[${status.index}].sezione" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].foglio" size="3"/>
			<div><form:errors path="cessazioneDom[${status.index}].foglio" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].particella" size="3"/>
			<div><form:errors path="cessazioneDom[${status.index}].particella" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].subalterno" size="3"/>
			<div><form:errors path="cessazioneDom[${status.index}].subalterno" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:select path="cessazioneDom[${status.index}].categoria">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
			    <form:options items="${categorieCatastaliMap}" />
			</form:select>
			<div><form:errors path="cessazioneDom[${status.index}].categoria" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:select id="cessazioneDom[${status.index}].tipo" path="cessazioneDom[${status.index}].tipo">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
				<form:options items="${tipologieImmobileMap}" />
			</form:select>
			<div><form:errors path="cessazioneDom[${status.index}].tipo" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneDom[${status.index}].mq" size="2"/>
			<div><form:errors path="cessazioneDom[${status.index}].mq" cssStyle="color:red"/></div>
			</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<span><spring:message code="label.dati.indicativi"/>.</span><br>