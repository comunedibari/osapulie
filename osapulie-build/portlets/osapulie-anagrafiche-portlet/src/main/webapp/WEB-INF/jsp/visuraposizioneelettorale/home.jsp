<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="visuraPosizioneElettoraleUrl">
	<portlet:param name="action" value="visura" />		
</portlet:actionURL>

<c:if test="${ empty datiAnagrafici.errore}">	
	<div class="mainDiv">
		<form:form id="visuraElettorale" action="${visuraPosizioneElettoraleUrl}" method="post" commandName="datiAnagrafici" cssClass="printForm">
			<div class="marginBottom10">
				<label>Soggetto della visura:</label>&nbsp;
				<select name="codFisc">
					<c:forEach var="item" begin="0" items="${datiAnagrafici.componentiNucleoFamiliare}">
				    	<option value="${item.codiceFiscale}">${item.cognome} ${item.nome}</option>
					</c:forEach>
				</select>&nbsp;&nbsp;
				<input type="submit" name="invia" value="Carica dati elettorali"/>
			</div>
		</form:form>
	</div>
</c:if> 	

<c:if test="${! empty datiAnagrafici.errore }">
	<div class="portlet-msg-error">
		<spring:message code="errore.pdds.codice${datiAnagrafici.errore.codice }" />
	</div>
</c:if>