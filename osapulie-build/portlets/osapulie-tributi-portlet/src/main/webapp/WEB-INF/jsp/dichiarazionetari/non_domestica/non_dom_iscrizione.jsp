<%@ include file="../../common/import.jsp"%>

<fieldset>
	<legend><spring:message code="legend.tipologia.riduzione" /></legend>
	<div><form:errors path="tipologiaRiduzioneNonDom" cssStyle="color:red"/></div>
	<table class="genericTable">
		<tr>
			<td>
				<div>
					<c:forEach items="${riduzioniNonDomesticheIscrizione}" var="riduzioneNonDomestica" varStatus="status">
						<form:checkbox path="riduzioniNonDomesticheIscrizione[${status.index}].codiceArticolo" 
							id="riduzioniNonDomesticheIsc${status.index}"  cssClass="riduzioniNonDomIsc" value="${riduzioneNonDomestica.codiceArticolo}"
							onclick="toggleRiduzione('#riduzioniNonDomesticheIsc${status.index}', '#valoriRiduzioneNonDomIsc${status.index}');"/>
						
						<label><spring:message code="label.chiedo.la" arguments="${riduzioneNonDomestica.descrizioneArticolo}" /></label>
						<ul id="valoriRiduzioneNonDomIsc${status.index}" style="display: none;">
							<c:forEach items="${riduzioneNonDomestica.valori}" var="valore" varStatus="status2">
								<li>
									<form:radiobutton path="riduzioniNonDomesticheIscrizione[${status.index}].valori[${status.index}].chiave" value="${valore.chiave}" label=" ${valore.valore}" />
								</li>
							</c:forEach>
						</ul>
						<div><form:errors path="riduzioniNonDomesticheIscrizione[${status.index}].codiceArticolo" cssStyle="color:red"/></div> 
					</c:forEach>
				</div>
		   </td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.note" /></label>
				<form:textarea path="noteRiduzione" rows="3" cols="100"/>
		   </td>
		</tr>
	</table>	
</fieldset>