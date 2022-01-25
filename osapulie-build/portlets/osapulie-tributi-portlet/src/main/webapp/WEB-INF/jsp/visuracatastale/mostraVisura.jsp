<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="visuraPosizioneAnagraficaUrl">
	<portlet:param name="action" value="visura" />		
</portlet:actionURL>
<div class="mainDiv">
		<fieldset>
			<legend><spring:message code="label.intestatario" /></legend>
			<table class="genericTable richTable">
				<tr><td colspan="2" class="cellaPiena"></td></tr>
					<tr>
						<td width="25%"><label><spring:message code="label.nome" /></label></td>
						<td>${intestatario.nome}</td>
					</tr>
					<tr>
						<td width="25%"><label><spring:message code="label.cognome" /></label></td>
						<td>${intestatario.cognome}</td>
					</tr>
					<tr>
						<td width="25%"><label><spring:message code="label.comuneN" /></label></td>
						<td>${intestatario.comuneNascita}</td>
					</tr>
					<tr>
						<td width="25%"><label><spring:message code="label.dataN" /></label></td>
						<td><fmt:formatDate value="${intestatario.dataNascita.time}" type="date" pattern="dd/MM/yyyy" dateStyle="short" /></td>
					</tr>
			</table>
		</fieldset>
	<c:if test="${ not empty fabbricati}">
		<fieldset>
			<legend><spring:message code="label.fabbricati" /></legend>
			<table class="genericTable richTable">
					<tr><td colspan="2" class="cellaPiena"></td></tr>
						<c:forEach var="fabbricato" begin="0" items="${fabbricati}" varStatus="status">
						<c:if test="${not empty fabbricato}">
						<tr>
							<td width="25%"><label><spring:message code="label.numeroImmobile" /></label></td>
							<td>${status.count}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.indirizzo" /></label></td>
							<td>${fabbricato.indirizzo}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.civico" /></label></td>
							<td>${fabbricato.civico}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.sezione" /></label></td>
							<td>${fabbricato.sezioneUrbana}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.foglio" /></label></td>
							<td>${fabbricato.foglio}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.particella" /></label></td>
							<td>${fabbricato.particella}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.subalterno" /></label></td>
							<td>${fabbricato.subalterno}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.zona" /></label></td>
							<td>${fabbricato.zona}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.categoria" /></label></td>
							<td>${fabbricato.categoria}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.classe" /></label></td>
							<td>${fabbricato.classe}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.consistenza" /></label></td>
							<td>${fabbricato.consistenza}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.superficie" /></label></td>
							<td>${fabbricato.superficie}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.rendita" /></label></td>
							<td>${fabbricato.rendita}</td>
						</tr>
					
						<tr><td colspan="2" class="cellaVuota"></td></tr>
						</c:if>
					</c:forEach>
				</table>
			</fieldset>
		</c:if>
		
	<c:if test="${ not empty terreni}">
		<fieldset>
			<legend><spring:message code="label.terreni" /></legend>
			<table class="genericTable richTable">
				<tr>
					<td colspan="2" class="cellaPiena"></td>
				</tr>
				<c:forEach var="terreno" begin="0" items="${terreni}" varStatus="status">
					<c:if test="${not empty terreno}">
						<tr>
							<td width="25%"><label><spring:message code="label.numeroImmobile" /></label></td>
							<td>${status.count}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.sezione" /></label></td>
							<td>${terreno.sezione}</td>
						</tr>
										<tr>
							<td width="25%"><label><spring:message code="label.foglio" /></label></td>
							<td>${terreno.foglio}</td>
						</tr>
												<tr>
							<td width="25%"><label><spring:message code="label.numero" /></label></td>
							<td>${terreno.particella}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.subalterno" /></label></td>
							<td>${terreno.subalterno}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.classe" /></label></td>
							<td>${terreno.classe}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.superficie" /></label></td>
							<td>${terreno.superficie}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.deduzione" /></label></td>
							<td>${terreno.deduzione}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.redditoDominicale" /></label></td>
							<td>${terreno.redditoDominicale}</td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.redditoAgrario" /></label></td>
							<td>${terreno.redditoAgrario}</td>
						</tr>
						<tr><td colspan="2" class="cellaVuota"></td></tr>
					</c:if>
				</c:forEach>
			</table>
		</fieldset>
	</c:if>
	<div class="container_pulsante">
		<%@ include file="../common/valutaservizio.jsp"%>
	</div>
</div>