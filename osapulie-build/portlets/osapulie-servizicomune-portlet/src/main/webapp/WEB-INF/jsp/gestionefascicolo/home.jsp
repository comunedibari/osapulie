<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<script type="text/javascript"  >
$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
</script>
<div class="mainDiv">
<c:choose>
	<c:when test="${access}">
		<fieldset>
			<legend><spring:message code="label.ricercaRichieste"/></legend>
			<form id="searchForm" action="${home}" method="post">
				<table>
					<tr>
						<td>
							<spring:message code="label.cognome"/>:
						</td>
						<td>
							<input type="text" name="profiloUtenteCittadino.cognome" value="${cognome}">
						</td>
						<td>
							<spring:message code="label.nome"/>:
						</td>
						<td>
							<input type="text" name="profiloUtenteCittadino.nome" value="${nome}">
						</td>
						<td>
							<spring:message code="label.cf"/>:
						</td>
						<td>
							<input type="text" name="profiloUtenteCittadino.codifceFiscale" value="${cf}">
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.datarichiesta.da"/>:
						</td>
						<td>
							<input type="text" class="data_input" name="dataRichiestaDa" value="${dataRichiestaDa}">
						</td>
						<td>
							<spring:message code="label.datarichiesta.a"/>:
						</td>
						<td colspan="2">
							<input type="text" class="data_input" name="dataRichiestaA" value="${dataRichiestaA}">
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.servizio"/>:&nbsp;
						</td>
						<td> 
							<select name="servizioSelect" class="searchSelect">
								<option value=""><spring:message code="label.select"/></option>
								<c:forEach items="${servizioList}" var="servizio">
									<option value="${servizio.id}" <c:if test="${servizioSelect == servizio.id}">selected="selected"</c:if>><c:out value="${servizio.nomeServizio}"/></option>
								</c:forEach>
							</select>
						</td>
						<td>
							<spring:message code="label.azienda"/>:&nbsp;
						</td>
						<td colspan="2">
							<select name="idAzienda" class="searchSelect">
								<option value=""><spring:message code="label.select"/></option>
								<c:forEach items="${aziende}" var="azienda">
									<option value="${azienda.id}" <c:if test="${idAzienda == azienda.id}">selected="selected"</c:if>><c:out value="${azienda.ragioneSociale}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div class="container_pulsante">
					<input type="submit" value="<spring:message code="button.cerca" />" />
				</div>
			</form>
		</fieldset>
		<c:choose>
			<c:when test="${richiestaServizioList != null}">
				<fieldset>
					<legend>
						<c:choose>
							<c:when test="${comuneIsa != null}">
								<spring:message code="label.listaRichieste" arguments="${comuneIsa.nome}"/>								
							</c:when>
							<c:otherwise>
								<spring:message code="label.listaRichiesteAll"/>							
							</c:otherwise>
						</c:choose>
					</legend>						
				 	<display:table id="paginatedTable" requestURI="${home}" name="richiestaServizioList" uid="richiestaServizio" cellpadding="2" cellspacing="0" pagesize="20" defaultorder="descending" defaultsort="4" sort="list" class="elencoRisultati">
						<display:column sortable="true" titleKey="label.cognome">
							<c:choose>
								<c:when test="${!empty richiestaServizio.fascicolo.azienda}">
									<c:out value="${richiestaServizio.fascicolo.azienda.ragioneSociale}"/>
								</c:when>
								<c:otherwise>
									<c:out value="${richiestaServizio.fascicolo.cittadino.cognome}"/>
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column sortable="true" titleKey="label.nome">
							<c:choose>
								<c:when test="${!empty richiestaServizio.fascicolo.azienda}">
								</c:when>
								<c:otherwise>
									<c:out value="${richiestaServizio.fascicolo.cittadino.nome}"/>
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column sortable="true" property="comuneErogatore.nome" titleKey="label.comune" />
						<display:column sortable="true" property="dataRichiesta" titleKey="label.dataRichiesta" format="{0,date,dd/MM/yyyy - HH:mm}"/>
						<display:column sortable="true" property="nomeServizio" titleKey="label.nomeServizio" />
						<display:column sortable="true" titleKey="label.delegato">
							<c:if test="${!empty richiestaServizio.delegato}">
								<div title="${richiestaServizio.delegato.profiloUtenteCittadino.codiceFiscale}">
									<c:out value="${richiestaServizio.delegato.azienda.ragioneSociale}"/>&nbsp;-&nbsp;<c:out value="${richiestaServizio.delegato.profiloUtenteCittadino.cognome}"/>&nbsp;<c:out value="${richiestaServizio.delegato.profiloUtenteCittadino.nome}"/>
								</div>
							</c:if>
						</display:column>
						<display:column sortable="true" property="numeroProtocollo" titleKey="label.protocollo" />
						<display:column sortable="false" titleKey="label.infoAggiuntive" headerClass="infoAggiuntiveMap">
							<div>
								<c:forEach var="infoAggiuntive" items="${richiestaServizio.infoAggiuntiveMap}">
								    ${infoAggiuntive.key}:&nbsp;${infoAggiuntive.value}
								</c:forEach>
							</div>
						</display:column>
					</display:table>
				</fieldset>
			</c:when>
			<c:otherwise>
				<spring:message code="label.richiesteNonPresenti"/>.						
			</c:otherwise>
		</c:choose>
		</c:when>
	<c:otherwise>
		<spring:message code="label.accessDenied"/>.
	</c:otherwise>
</c:choose>
</div>