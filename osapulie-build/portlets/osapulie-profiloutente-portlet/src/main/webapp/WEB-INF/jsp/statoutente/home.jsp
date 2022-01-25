<%@ include file="../common/common.jsp"%>

<div class="mainDiv statoUtente">
	<c:choose>
		<c:when test="${profiloUtente != null}">
			<fieldset>
				<legend><spring:message code="label.datiAutenticazione"/></legend>	
				<table class="genericTable">
					<tr>
						<td>
							<label><spring:message code="label.benvenuto"/>:</label>
						</td>
						<td>
							<b><c:out value="${profiloUtente.nome}"/>&nbsp;<c:out value="${profiloUtente.cognome}"/></b>
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.codiceFiscale"/>:</label>
						</td>
						<td>
							<c:out value="${profiloUtente.username}"/>
						</td>
					</tr>
					<c:if test="${!empty profiloUtente.comuneResidenzaString}">
						<tr>
							<td>
								<label><spring:message code="label.comune.residenza"/>:</label>
							</td>
							<td>
								<c:out value="${profiloUtente.comuneResidenzaString}"/>
							</td>
						</tr>
					</c:if>
					<tr>	
						<td>
							<label><spring:message code="label.canaleAutenticazione"/>:</label>
						</td>
						<td class="canaleAutenticazioneTd">
							<c:choose>
								<c:when test="${profiloUtente.canaleAutenticazione == AuthenticationChannel.SPID.name}">
									<img alt="${profiloUtente.canaleAutenticazione}>" src="<%=request.getContextPath() %>/images/spid-logo-c-bb.svg" title="${profiloUtente.canaleAutenticazione}">
								</c:when>
								<c:when test="${profiloUtente.canaleAutenticazione == AuthenticationChannel.REGIONE_PUGLIA.name}">
									<img alt="${profiloUtente.canaleAutenticazione}>" src="<%=request.getContextPath() %>/images/puglia-esteso.png" title="${profiloUtente.canaleAutenticazione}" class="regione_puglia">
								</c:when>
								<c:otherwise>
									<c:out value="${profiloUtente.canaleAutenticazione}"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<c:if test="${profiloUtente.canaleAutenticazione == AuthenticationChannel.CAS.name || profiloUtente.canaleAutenticazione == AuthenticationChannel.REGIONE_PUGLIA.name}">
						<tr>	
							<td>
								<label><spring:message code="label.tipoAutenticazione"/>:</label>
							</td>
							<td class="autenticazioneTd">
								<c:choose>
									<c:when test="${profiloUtente.autenticatoForte}">
										<i class="fa fa-lock"></i>&nbsp;<span><spring:message code="label.images.autenticatoForteOk"/></span>
									</c:when>
									<c:otherwise>
										<i class="fa fa-unlock"></i>&nbsp;<span><spring:message code="label.images.autenticatoForteKo"/></span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:if>
					<c:if test="${profiloUtente.canaleAutenticazione == AuthenticationChannel.SPID.name}">
						<tr>	
							<td>
								<label><spring:message code="label.livelloAutenticazioneSPID"/>:</label>
							</td>
							<td class="livelloAutenticazioneSPIDTd">
								<img alt="<spring:message code="label.livello"/>&nbsp;${profiloUtente.livelloAutenticazione}>" src="<%=request.getContextPath() %>/images/spid-level${profiloUtente.livelloAutenticazione}-logo-bb.svg" title="<spring:message code="label.livello"/>&nbsp;${profiloUtente.livelloAutenticazione}">
							</td>
						</tr>
					</c:if>
					<c:if test="${profiloUtente.profiloEnable}">
						<tr>
							<td>
								<label><spring:message code="label.profilo"/>:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${profiloUtente.profiloAzienda}">
										<i class="fa fa-building"></i>&nbsp;<spring:message code="label.profilo.azienda"/>
									</c:when>
									<c:when test="${profiloUtente.profiloDelega}">
										<i class="fa fa-user"></i>&nbsp;<spring:message code="label.profilo.delega"/>
									</c:when>
									<c:otherwise>
										<i class="fa fa-user"></i>&nbsp;<spring:message code="label.profilo.cittadino"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:if>
					<c:if test="${profiloUtente.profiloEnable && profiloUtente.profiloDelega}">
						<tr>
							<td>
								<label><spring:message code="label.delegante"/>:</label>
							</td>
							<td>
								<c:out value="${profiloUtente.delega.delegante.nome}"/>&nbsp;<c:out value="${profiloUtente.delega.delegante.cognome}"/>
							</td>
						</tr>
					</c:if>
					<tr>
						<td>
							<label><spring:message code="label.comune.selezionato"/>:</label>
						</td>
						<td>
							<c:out value="${profiloUtente.comuneIsaString}"/>
						</td>
					</tr>
				</table>
			</fieldset>
		</c:when>
		<c:otherwise>
			<spring:message code="label.utenteNonLoggato"/>.						
		</c:otherwise>
	</c:choose>
</div>