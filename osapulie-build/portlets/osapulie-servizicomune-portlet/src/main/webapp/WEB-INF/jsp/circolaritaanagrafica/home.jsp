<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="getAnagraficaURL">
	<portlet:param name="action" value="getAnagrafica" />		
</portlet:actionURL>

<div class="mainDiv">
	<form:form id="visura" action="${getAnagraficaURL}" method="post" commandName="datiRicerca" cssClass="printForm">
		<fieldset>
		  <legend><spring:message code="label.ricerca" /></legend>
		    <table class="genericTable">
			   <tr>
					<td>
						<label><spring:message code="label.comune"/>:</label>&nbsp;&nbsp;<form:select path="codiceIstat" items="${comuni}" />
					</td>
					<td>
						<label><spring:message code="label.cf"/>:</label>&nbsp;&nbsp;<form:input path="codiceFiscale" />
						<div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>
					</td>
				</tr>
			 </table>
			<div class="container_pulsante">
				<input type="submit" name="cerca" value="<spring:message code="button.cerca" />"/>
			</div>
		</fieldset>
	</form:form>
</div>