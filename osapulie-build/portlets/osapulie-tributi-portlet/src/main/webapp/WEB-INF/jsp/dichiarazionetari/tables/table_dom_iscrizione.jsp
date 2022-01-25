<%@ include file="../../common/import.jsp"%>

<div><form:errors path="iscrizioneDom" cssStyle="color:red"/></div> 
<table class="genericTable" id="domestica_table">
	 <c:forEach items="${datiTari.iscrizioneDom}" var="immobili" varStatus="status">

	 <tr>
		<td colspan="5">
			<osapulie:stradario id="stradarioImmobile_iscrizioneDom_${status.index}" 
				viaName="iscrizioneDom[${status.index}].indirizzo" 
				civicoName="iscrizioneDom[${status.index}].civico"
				esponenteName="iscrizioneDom[${status.index}].esponente" 
				viaOptionValue="${datiTari.iscrizioneDom[status.index].indirizzo}" 
				civicoOptionValue="${datiTari.iscrizioneDom[status.index].civico}" 
				esponenteValue="${datiTari.iscrizioneDom[status.index].esponente}" 
				viaOptionText="${datiTari.iscrizioneDom[status.index].indirizzoTextHidden}"
				civicoOptionText="${datiTari.iscrizioneDom[status.index].civicoTextHidden}" 
				viaTextHiddenName="iscrizioneDom[${status.index}].indirizzoTextHidden"
				civicoTextHiddenName="iscrizioneDom[${status.index}].civicoTextHidden"
				localitaHiddenName="iscrizioneDom[${status.index}].localitaHidden"
				localitaValue="${datiTari.iscrizioneDom[status.index].localitaHidden}"
				codiceViaHiddenName="iscrizioneDom[${status.index}].codiceViaHidden"
				codiceViaValue="${datiTari.iscrizioneDom[status.index].codiceViaHidden}"
				loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
			<div><form:errors path="iscrizioneDom[${status.index}].indirizzo" cssStyle="color:red"/></div>
			<div><form:errors path="iscrizioneDom[${status.index}].esponente" cssStyle="color:red"/></div>
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
			<spring:message code="label.cat" />
		</th>
		<th>
			<spring:message code="label.uso" />*
		</th>
		<th>
			<spring:message code="label.mq" />*
		</th>
	</tr>

	 <tr class="border-bottom padding-bottom-td">
		<td>
			<form:input path="iscrizioneDom[${status.index}].cap" size="2" maxlength="5"/>
			<div><form:errors path="iscrizioneDom[${status.index}].cap" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].piano" size="2" maxlength="2"/>
			<div><form:errors path="iscrizioneDom[${status.index}].piano" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].scala" size="2" maxlength="2"/>
			<div><form:errors path="iscrizioneDom[${status.index}].scala" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].interno" size="2" maxlength="2"/>
			<div><form:errors path="iscrizioneDom[${status.index}].interno" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].suffisso" size="2"/>
			<div><form:errors path="iscrizioneDom[${status.index}].suffisso" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].sezione" size="3"/>
			<div><form:errors path="iscrizioneDom[${status.index}].sezione" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].foglio" size="3"/>
			<div><form:errors path="iscrizioneDom[${status.index}].foglio" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].particella" size="3"/>
			<div><form:errors path="iscrizioneDom[${status.index}].particella" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].subalterno" size="3"/>
			<div><form:errors path="iscrizioneDom[${status.index}].subalterno" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:select path="iscrizioneDom[${status.index}].categoria">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
			    <form:options items="${categorieCatastaliMap}" />
			</form:select>
			<div><form:errors path="iscrizioneDom[${status.index}].categoria" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:select id="iscrizioneDom[${status.index}].tipo" path="iscrizioneDom[${status.index}].tipo">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
				<form:options items="${tipologieImmobileMap}" />
			</form:select>
			<div><form:errors path="iscrizioneDom[${status.index}].tipo" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="iscrizioneDom[${status.index}].mq" size="2"/>
			<div><form:errors path="iscrizioneDom[${status.index}].mq" cssStyle="color:red"/></div>
			</td>
		</tr>
	</c:forEach>
</table>
<span><spring:message code="label.dati.indicativi"/>.</span><br>