<%@ include file="../../common/import.jsp"%>

<div><form:errors path="cessazioneNonDom" cssStyle="color:red"/></div> 
<table class="genericTable customtable" id="non_domestica_table">
<c:forEach items="${datiTari.cessazioneNonDom}" var="cessazione" varStatus="status">
	
	<tr class="padding-top-th">
		<c:if test="${empty datiTari.cessazioneNonDom[status.index].identificativoUtenza}">
	 	<th rowspan="4" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateCessazione" value="${datiTari.cessazioneNonDom[status.index].identificativoUtenza}" />
	 	</th>
		</c:if>
		<c:if test="${!empty datiTari.cessazioneNonDom[status.index].identificativoUtenza}">
	 	<th rowspan="3" class="radiocolumn">
	 		<form:radiobutton path="identificativoUtenzeSelezionateCessazione" value="${datiTari.cessazioneNonDom[status.index].identificativoUtenza}" />
	 	</th>
		<th colspan="12">
				<spring:message	code="label.indirizzo.immobile" />:
				<label>${datiTari.cessazioneNonDom[status.index].indirizzoTextHidden},&nbsp;${datiTari.cessazioneNonDom[status.index].civicoTextHidden}
				<c:if test="${!empty datiTari.cessazioneNonDom[status.index].esponente}">
					&nbsp;${datiTari.cessazioneNonDom[status.index].esponente}
				</c:if>
				</label>
		</th>
		</c:if>				
	 </tr>
	<c:if test="${empty datiTari.cessazioneNonDom[status.index].identificativoUtenza}">
		<tr>
			<td colspan="12">
				<spring:message	code="label.indirizzo.immobile" />*:
				<osapulie:stradario id="stradarioImmobile_cessazioneNonDom_${status.index}" 
					viaName="cessazioneNonDom[${status.index}].indirizzo" 
					civicoName="cessazioneNonDom[${status.index}].civico" 
					esponenteName="cessazioneNonDom[${status.index}].esponente" 
					viaOptionValue="${datiTari.cessazioneNonDom[status.index].indirizzo}" 
					civicoOptionValue="${datiTari.cessazioneNonDom[status.index].civico}" 
					esponenteValue="${datiTari.cessazioneNonDom[status.index].esponente}" 
					viaOptionText="${datiTari.cessazioneNonDom[status.index].indirizzoTextHidden}"
					civicoOptionText="${datiTari.cessazioneNonDom[status.index].civicoTextHidden}" 
					viaTextHiddenName="cessazioneNonDom[${status.index}].indirizzoTextHidden"
					civicoTextHiddenName="cessazioneNonDom[${status.index}].civicoTextHidden"
					localitaHiddenName="cessazioneNonDom[${status.index}].localitaHidden"
					localitaValue="${datiTari.cessazioneNonDom[status.index].localitaHidden}"
					codiceViaHiddenName="cessazioneNonDom[${status.index}].codiceViaHidden"
					codiceViaValue="${datiTari.cessazioneNonDom[status.index].codiceViaHidden}"
					loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
				<div><form:errors path="cessazioneNonDom[${status.index}].indirizzo" cssStyle="color:red"/></div>
				<div><form:errors path="cessazioneNonDom[${status.index}].esponente" cssStyle="color:red"/></div>
				<br>
			</td>
		 </tr>
	</c:if>
	 
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
			<spring:message code="label.mq" />*
		</th>
	</tr>
	
	<tr class="border-bottom padding-bottom-td">
		<c:if test="${!empty datiTari.cessazioneNonDom[status.index].identificativoUtenza}">
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].cap}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].piano}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].scala}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].interno}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].suffisso}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].sezione}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].foglio}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].particella}</label>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].subalterno}</label>
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].codAteco" size="3"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].codAteco" cssStyle="color:red"/></div>
		</td>
		<td>
			<label>${datiTari.cessazioneNonDom[status.index].mq}</label>
		</td>
		</c:if>
		<c:if test="${empty datiTari.cessazioneNonDom[status.index].identificativoUtenza}">
		<td>
			<form:input path="cessazioneNonDom[${status.index}].cap" size="2" maxlength="5"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].cap" cssStyle="color:red"/></div>									
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].piano" size="2" maxlength="2"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].piano" cssStyle="color:red"/></div>									
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].scala" size="2" maxlength="2"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].scala" cssStyle="color:red"/></div>									
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].interno" size="2" maxlength="2"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].interno" cssStyle="color:red"/></div>								
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].suffisso" size="2"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].suffisso" cssStyle="color:red"/></div>								
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].sezione" size="3"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].sezione" cssStyle="color:red"/></div>								
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].foglio" size="3"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].foglio" cssStyle="color:red"/></div>							
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].particella" size="3"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].particella" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].subalterno" size="3"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].subalterno" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].codAteco" size="3"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].codAteco" cssStyle="color:red"/></div>
		</td>
		<td>
			<form:input path="cessazioneNonDom[${status.index}].mq" size="2"/>
			<div><form:errors path="cessazioneNonDom[${status.index}].mq" cssStyle="color:red"/></div>
		</td>
	</c:if>
	</tr>
	</c:forEach>
</table>