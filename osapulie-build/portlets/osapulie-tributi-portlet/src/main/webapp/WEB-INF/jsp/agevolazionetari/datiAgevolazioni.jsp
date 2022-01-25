<fieldset>
	<legend>
		<spring:message code="label.legendDatiAgevolazioni" />
	</legend>

	<label><spring:message code="label.aDecorrereDal" />*:</label>
	<form:input path="dataDecorrenza" id="data_adecorrereda_input" size="10" cssClass="data_input"/>
	<div><form:errors path="dataDecorrenza" cssStyle="color:red"/></div>
	<label><spring:message code="label.chiede" />:</label>


	<table class="genericTable">
		<tr>
			<td colspan="2">
				<div><form:errors path="codiceAgevolazione" cssStyle="color:red"/></div>
			</td>
		</tr>
		
		<c:forEach items="${agevolazioniList}" var="agevolazione" varStatus="status">
		<tr>
			<td>
				<form:radiobutton path="codiceAgevolazione" value="${agevolazione.chiave}"/>
				<label>${agevolazione.valore}:</label>
				<ul>
					<c:forEach items="${agevolazioniDescrizioniList}" var="agevolazioneDescrizione" varStatus="status">
					<c:if test="${agevolazioneDescrizione.chiave == agevolazione.chiave}">
						<li>- ${agevolazioneDescrizione.valore}</li>
					</c:if>
					</c:forEach>
				</ul>
			</td>
		</tr>
		</c:forEach>
	</table>
</fieldset>