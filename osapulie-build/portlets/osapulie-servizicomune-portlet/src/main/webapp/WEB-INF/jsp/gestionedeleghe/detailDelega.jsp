<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv delegaDetail">
	<c:choose>
		<c:when test="${delega != null}">
			<h1><spring:message code="label.dettaglioDelega" arguments="${delega.comuneIsa.nome}"/></h1>
			<fieldset>
				<legend><spring:message code="label.dettaglioDelega2"/></legend>
				<table>
			 		<tr>
			 			<td>
			 				<spring:message code="label.dataRichiesta"/>:
			 			</td>
			 			<td>
			 				<fmt:formatDate value="${delega.dataRichiesta}" pattern="dd/MM/yyyy - HH:mm"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
			 				<spring:message code="label.stato"/>:
			 			</td>
			 			<td>
				 			<c:choose>
								<c:when test="${delega.attiva}">
									<img src="<%=request.getContextPath() %>/images/ok.png" alt="<spring:message code="label.images.delegaApprovata"/>" title="<spring:message code="label.images.delegaApprovata"/>"/>							
									<span><spring:message code="label.images.delegaApprovata"/></span>
								</c:when>
								<c:otherwise>
									<img src="<%=request.getContextPath() %>/images/ko.png" alt="<spring:message code="label.images.delegaNonApprovata"/>" title="<spring:message code="label.images.delegaNonApprovata"/>"/>								
									<span><spring:message code="label.images.delegaNonApprovata"/></span>
								</c:otherwise>
							</c:choose>
			 			</td>
			 		</tr>
			 	</table>
			</fieldset>
			<fieldset>
				<legend><spring:message code="label.dettaglioDelegante"/></legend>
			 	<table>
			 		<tr>
			 			<td>
			 				<spring:message code="label.nome"/>:
			 			</td>
			 			<td>
			 				<c:out value="${delega.delegante.nome}"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
			 				<spring:message code="label.cognome"/>:
			 			</td>
			 			<td>
			 				<c:out value="${delega.delegante.cognome}"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
			 				<spring:message code="label.cf"/>:
			 			</td>
			 			<td>
			 				<c:out value="${delega.delegante.codiceFiscale}"/>
			 			</td>
			 		</tr>
			 	</table>
		 	</fieldset>
			<fieldset>
				<legend><spring:message code="label.dettaglioDelegato"/></legend>
			 	<table>
			 		<tr>
			 			<td>
			 				<spring:message code="label.ragioneSociale"/>:
			 			</td>
			 			<td>
			 				<c:out value="${delega.delegato.ragioneSociale}"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td>
			 				<spring:message code="label.pivacf"/>:
			 			</td>
			 			<td>
			 				<c:out value="${delega.delegato.partitaIva}"/>
			 			</td>
			 		</tr>
			 	</table>
		 	</fieldset>
			<fieldset>
				<legend><spring:message code="label.dettaglioServiziDelega"/></legend>
			 	<table>
			 		<tr>
			 			<th>
			 				<spring:message code="label.nomeServizio"/>
			 			</th>
			 			<th>
			 				<spring:message code="label.descrizioneServizio"/>
			 			</th>
			 			<th>
			 				<spring:message code="label.codiceServizio"/>
			 			</th>
			 		</tr>
			 		<c:forEach items="${delega.servizi}" var="servizio">
			 			<tr>
				 			<td>
				 				<c:out value="${servizio.nomeServizio}"/>
				 			</td>
				 			<td>
				 				<c:out value="${servizio.descrizione}"/>
				 			</td>
				 			<td>
				 				<c:out value="${servizio.codiceServizio}"/>
				 			</td>
				 		</tr>
			 		</c:forEach>
			 	</table>
		 	</fieldset>
		 	<div class="container_pulsante">
		 		<portlet:actionURL var="updateDelega">
					<portlet:param name="action" value="updateDelega" />
					<portlet:param name="idDelega" value="${delega.id}" />
				</portlet:actionURL>
				<c:choose>
					<c:when test="${delega.attiva}">
						<a class="custom_pulsante" href="${updateDelega}&stato=false" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
							<spring:message code="button.disattiva" />
						</a>							
					</c:when>
					<c:otherwise>
						<a class="custom_pulsante" href="${updateDelega}&stato=true" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
							<spring:message code="button.attiva" />
						</a>								
					</c:otherwise>
				</c:choose>
				<span class="spacer"></span>
				<a class="custom_pulsante" href="${home}">
					<spring:message code="button.annulla" />
				</a>
			</div>
			</c:when>
		<c:otherwise>
			<spring:message code="label.accessDenied"/>.								
		</c:otherwise>
	</c:choose>
</div>