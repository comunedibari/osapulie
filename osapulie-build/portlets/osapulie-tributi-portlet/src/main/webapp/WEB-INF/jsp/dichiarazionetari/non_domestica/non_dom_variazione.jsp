<%@ include file="../../common/import.jsp"%>

<table class="genericTable">
	<tr>
		<td>
			<form:radiobutton path="tipologiaRichiestaNonDom" value="applicazione"/><spring:message code="label.applicazione" />
			<form:radiobutton path="tipologiaRichiestaNonDom" value="revoca"/><spring:message code="label.revoca" />
			<form:errors path="tipologiaRichiestaNonDom" cssStyle="color:red"/>
			<label><spring:message code="label.checkVarApplicaRevocaAgevolazioni" /></label>
		</td>
	</tr>
	<tr>
		<td>
			<div>
				<c:forEach items="${riduzioniNonDomesticheVariazione}" var="riduzioneNonDomestica" varStatus="status">
					<form:checkbox path="riduzioniNonDomesticheVariazione[${status.index}].codiceArticolo" 
						id="riduzioniNonDomesticheVar${status.index}" cssClass="riduzioniNonDomVar" value="${riduzioneNonDomestica.codiceArticolo}"
						onclick="toggleRiduzione('#riduzioniNonDomesticheVar${status.index}', '#valoriRiduzioneNonDomVar${status.index}');"/>
					
					<label><spring:message code="label.chiedo.la" arguments="${riduzioneNonDomestica.descrizioneArticolo}" /></label>
					<ul id="valoriRiduzioneNonDomVar${status.index}" style="display: none;">
						<c:forEach items="${riduzioneNonDomestica.valori}" var="valore" varStatus="status2">
							<li>
								<form:radiobutton path="riduzioniNonDomesticheVariazione[${status.index}].valori[${status.index}].chiave" value="${valore.chiave}" label=" ${valore.valore}" />
							</li>
						</c:forEach>
					</ul>
					<div><form:errors path="riduzioniNonDomesticheVariazione[${status.index}].codiceArticolo" cssStyle="color:red"/></div> 
				</c:forEach>
			</div>
	   </td>
	</tr>
	<tr>
		<td>
			<form:checkbox path="altroNonDom" />
			<label><spring:message code="label.checkVarAltro" />
			</label>
		</td>
	</tr>
	<tr>
		<td>
			<form:textarea path="altroNoteNonDom" rows="3" cols="100"/>
		   <div><form:errors path="altroNoteNonDom" cssStyle="color:red"/></div>
		</td>
	</tr>
</table>