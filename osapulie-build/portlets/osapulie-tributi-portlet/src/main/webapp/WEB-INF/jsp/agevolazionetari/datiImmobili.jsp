<fieldset>
	<legend>
		<spring:message code="label.legendDatiImmobili" />
	</legend>
	
	<c:forEach items="${datiAgevolazioneTari.immobili}" var="immobile" varStatus="status">
	
	<c:if test="${status.index > 0}">
	<hr>
	</c:if>
	
	<table class="genericTable">
		<tr>
			<td width="180">
			    <c:choose>
			        <c:when test="${status.index == 0}">
						<label><spring:message code="label.indirAbitazione" />*:</label>
			        </c:when>
			        <c:otherwise>
			        <label><spring:message code="label.indirPertinenza" />*:</label>
			        </c:otherwise>
			    </c:choose>
			</td>
			<td>
				<osapulie:stradario id="stradarioImmobile_${status.index}" 
					viaName="immobili[${status.index}].indirizzo" 
					civicoName="immobili[${status.index}].civico"
					esponenteName="immobili[${status.index}].esponente" 
					viaOptionValue="${datiAgevolazioneTari.immobili[status.index].indirizzo}" 
					civicoOptionValue="${datiAgevolazioneTari.immobili[status.index].civico}" 
					esponenteValue="${datiAgevolazioneTari.immobili[status.index].esponente}" 
					viaOptionText="${datiAgevolazioneTari.immobili[status.index].indirizzoTextHidden}"
					civicoOptionText="${datiAgevolazioneTari.immobili[status.index].civicoTextHidden}" 
					viaTextHiddenName="immobili[${status.index}].indirizzoTextHidden"
					civicoTextHiddenName="immobili[${status.index}].civicoTextHidden"
					localitaHiddenName="immobili[${status.index}].localitaHidden"
					localitaValue="${datiAgevolazioneTari.immobili[status.index].localitaHidden}"
					codiceViaHiddenName="immobili[${status.index}].codiceViaHidden"
					codiceViaValue="${datiAgevolazioneTari.immobili[status.index].codiceViaHidden}"
					loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
				
				<div><form:errors path="immobili[${status.index}].indirizzo" cssStyle="color:red"/></div> 
			</td>
		</tr>
	</table>
	<table class="genericTable shrinked">
		<tr>
			<td>
				<label><spring:message	code="label.esp" />:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].esponente" size="3"/>
				<div><form:errors path="immobili[${status.index}].esponente" cssStyle="color:red"/></div> 
			</td>
			<td>
				<label><spring:message	code="label.scala" />:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].scala" size="3"/>
				<div><form:errors path="immobili[${status.index}].scala" cssStyle="color:red"/></div> 
			</td>
			<td>
				<label><spring:message code="label.piano" />:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].piano" size="3"/>
				<div><form:errors path="immobili[${status.index}].piano" cssStyle="color:red"/></div> 
			</td>
			<td>
				<label><spring:message code="label.interno" />:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].interno" size="3"/>
				<div><form:errors path="immobili[${status.index}].interno" cssStyle="color:red"/></div> 
			</td>
			<td></td>
		</tr>
		<tr>
			<td>
				<label><spring:message	code="label.mq" />:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].superficie" size="3"/>
				<div><form:errors path="immobili[${status.index}].superficie" cssStyle="color:red"/></div> 
			</td>
			<td>
				<label><spring:message	code="label.sezione" />:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].sezione" size="3"/>
				<div><form:errors path="immobili[${status.index}].sezione" cssStyle="color:red"/></div> 
			</td>
			<td>
				<label><spring:message	code="label.foglio" />*:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].foglio" size="3"/>
				<div><form:errors path="immobili[${status.index}].foglio" cssStyle="color:red"/></div> 
			</td>
			<td>
				<label><spring:message code="label.particella" />*:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].particella" size="3"/>
				<div><form:errors path="immobili[${status.index}].particella" cssStyle="color:red"/></div> 
			</td>
			<td>
				<label><spring:message code="label.sub" />:</label>
			</td>
			<td>
				<form:input path="immobili[${status.index}].subalterno" size="3"/>
				<div><form:errors path="immobili[${status.index}].subalterno" cssStyle="color:red"/></div> 
			</td>
		</tr>
	</table>
	<br>
	</c:forEach>
</fieldset>