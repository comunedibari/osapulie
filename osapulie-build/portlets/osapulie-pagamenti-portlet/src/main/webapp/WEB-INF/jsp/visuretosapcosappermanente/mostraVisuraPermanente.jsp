<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="visuraOsapPermanente">
	<portlet:param name="action" value="visura" />		
</portlet:actionURL>
<%
	Calendar today = new GregorianCalendar();
%>
<div class="mainDiv">
	<form:form id="visura" action="${visuraOsapPermanente}" method="post" commandName="dati" cssClass="printForm"> <!--  commandName oggetto da cui prende le properties -->
		<div class="marginBottom10">			
			<spring:message code="label.da"/>:&nbsp;
			<liferay-ui:input-date
				dayParam="dayStart"
				dayValue="${dayStart}"
				disabled="<%= false %>"
				firstDayOfWeek="<%= today.getFirstDayOfWeek() - 1 %>"
				monthParam="monthStart"
				monthValue="${monthStart}"
				yearParam="yearStart"
				yearValue="${yearStart}"
				yearRangeStart="<%= today.get(Calendar.YEAR) - 100 %>"
				yearRangeEnd="<%= today.get(Calendar.YEAR) %>"
			/>&nbsp;&nbsp;
			<spring:message code="label.a"/>:&nbsp;
			<liferay-ui:input-date
				dayParam="dayEnd"
				dayValue="${dayEnd}"
				disabled="<%= false %>"
				firstDayOfWeek="<%= today.getFirstDayOfWeek() - 1 %>"
				monthParam="monthEnd"
				monthValue="${monthEnd}"
				yearParam="yearEnd"
				yearValue="${yearEnd}"
				yearRangeStart="<%= today.get(Calendar.YEAR) - 100 %>"
				yearRangeEnd="<%= today.get(Calendar.YEAR) %>"
			/>&nbsp;&nbsp;
			<input type="submit" name="invia" value="Invia"/>
		</div>
		
		<c:if test="${! empty dati}">
			<fieldset>
				<legend>
					<label><spring:message code="label.descrizione" />&nbsp;<spring:message code="label.titolo" /></label>
				</legend>
		
				<c:forEach var="elencoOsap" begin="0" items="${dati.elencoOsapPermanente}">
					<table class="genericTable elencoRisultati">
						<tr>
							<th><spring:message code="label.numeroConto" /></th>
							<th><spring:message code="label.annoRiferimento" /></th>
							<th><spring:message code="label.numeroDocumento" /></th>
							<th><spring:message code="label.importoDocumento" /></th>
							<th><spring:message code="label.dataAggiornamento" /></th>
						</tr>
						<tr>
							<td>${elencoOsap.contoCorrente}</td>
							<td>${elencoOsap.annoRiferimento}</td>
							<td>${elencoOsap.numeroDocumento}</td>
							<td>${elencoOsap.importoDocumento}&nbsp;&euro;</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elencoOsap.dataAggiornamento.time}" /></td>
						</tr>
					</table>
					
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>
						<c:forEach var="utenza" begin="0" items="${elencoOsap.posizioniOsap}">
							<tr>
								<td width="25%"><label><spring:message code="label.zonaUtenza" /></label></td>
								<td>${utenza.zonaUtenza}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.indirizzoUtenza" /></label></td>
								<td>${utenza.indirizzoUtenza}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.superficie" /></label></td>
								<td>${utenza.superficie}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.mesi" /></label></td>
								<td>${utenza.mesi}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.descrizioneTariffa" /></label></td>
								<td>${utenza.descrizioneTariffa}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoDaPagare" /></label></td>
								<td>${utenza.importoDaPagare}&nbsp;&euro;</td>
							</tr>
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
					
					<table class="genericTable richTable">
						<tr><td colspan="2" class="cellaPiena"></td></tr>
						<c:forEach var="rata" begin="0" items="${elencoOsap.rate}">
							<tr>
								<td width="25%"><label><spring:message code="label.identificativoRata" /></label></td>
								<td>${rata.identificativoRata}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.numeroRata" /></label></td>
								<td>${rata.numeroRata}</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoRata" /></label></td>
								<td>${rata.importoRata}&nbsp;&euro;</td>
							</tr>
							<tr>
								<td><label><spring:message code="label.scadenzaRata" /></label></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${rata.scadenzaRata.time}" /></td>
							</tr>
							<tr>
								<td><label><spring:message code="label.dataPagamento" /></label></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${rata.dataPagamento.time}" /></td>
							</tr>
							<tr>
								<td><label><spring:message code="label.importoPagato" /></label></td>
								<td>${rata.importoPagato}&nbsp;&euro;</td>
							</tr>
							<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:forEach>
					</table>
				</c:forEach>
			</fieldset>
			
			<div class="container_pulsante">
				<c:if test="${sendReportsEnable}">
					<input type="submit" name="inviaSegnalazione" value="<spring:message code="button.inviaSegnalazione" />"/>
				</c:if>
				<%@ include file="../common/valutaservizio.jsp"%>
			</div>
		</c:if> 	
	</form:form>
	
	<c:if test="${empty dati}">
		<div class="portlet-msg-alert">
			<spring:message code="error.label.datiNonDisponibili" />
		</div>
	</c:if>
</div>