<%@ include file="../common/common.jsp"%>
<div class="mainDiv">
	<c:if test="${not empty servizio}">
		<div class="descrizioneServizioDiv">
			<div class="nomeServizio">
				<span><c:out value="${servizio.nomeServizio}"/></span>
			</div>
			<div class="tipologieServizio">
				<span class="titoletto"><spring:message code="label.tipologie"/>:</span>&nbsp;
					<c:forEach items="${servizio.tipologie}" var="tipologia">
							<span><i class="fa fa-caret-right"></i>&nbsp;<c:out value="${tipologia.nome}"/></span>&nbsp;&nbsp;
					</c:forEach>
			</div>
			<div class="areaTematicaServizio">
				<span class="titoletto"><spring:message code="label.areaTematica"/>:</span>&nbsp;
					<span><i class="fa fa-caret-right"></i>&nbsp;<c:out value="${servizio.areaTematica.nome}"/></span>&nbsp;&nbsp;
			</div>
			<div class="comuniServizio">
				<span class="titoletto"><spring:message code="label.comuni"/>:</span>&nbsp;
				
				<c:choose>
					<c:when test="${!empty servizio.comuni}">
						<!-- REGIONE_PUGLIA -->
		   				<c:set var="regionePugliaEnable" value="false" />
						<c:forEach var="authenticationChannel" items="${authenticationChannels}">
							<c:if test="${authenticationChannel eq AuthenticationChannel.REGIONE_PUGLIA}">
								<c:set var="regionePugliaEnable" value="true" />
							</c:if>
						</c:forEach>
						<!-- SPID -->
		   				<c:set var="spidEnable" value="false" />
						<c:forEach var="authenticationChannel" items="${authenticationChannels}">
							<c:if test="${authenticationChannel eq AuthenticationChannel.SPID}">
								<c:set var="spidEnable" value="true" />
							</c:if>
						</c:forEach>
						
						<table>
							<thead>
								<tr>
									<th rowspan="2" style="width: 80%;"></th>
									<th colspan="${fn:length(authenticationChannels)}" class="center"><spring:message code="label.modalitaAutenticazione"/></th>
								</tr>
								<tr>
									<c:if test="${regionePugliaEnable}">
										<th class="center"><spring:message code="label.autenticazione.REGIONE_PUGLIA"/></th>
			   						</c:if>
									<c:if test="${spidEnable}">
										<th class="center"><spring:message code="label.autenticazione.SPID"/></th>
			   						</c:if>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td colspan="3">
										<ul>
											<!-- REGIONE_PUGLIA -->
											<c:if test="${regionePugliaEnable}">
												<li>
													<i class="fa fa-lock fa-lg"></i>&nbsp;:&nbsp;<span><spring:message code="label.autenticazione.REGIONE_PUGLIA.forte.legenda"/></span>
												</li>
												<li>
													<i class="fa fa-unlock fa-lg"></i>&nbsp;:&nbsp;<span><spring:message code="label.autenticazione.REGIONE_PUGLIA.debole.legenda"/></span>
												</li>
											</c:if>
											<!-- SPID -->
											<c:if test="${spidEnable}">
												<c:forEach begin="1" end="3" varStatus="loop">
													<li>
														<i class="fa fa-spid-level${loop.count}"></i>&nbsp;:&nbsp;<span><spring:message code="label.autenticazione.SPID.livello.legenda" arguments="${loop.count}"/></span>
													</li>
												</c:forEach>
											</c:if>
										</ul>
									</td>
								</tr>
							</tfoot>
							<c:forEach items="${servizio.comuni}" var="comuneIsa" varStatus="status">
								<tr>
									<td><c:out value="${comuneIsa.comuneISA.nome}"/></td>
									<c:if test="${regionePugliaEnable}">
										<td class="center">
											<c:choose>
								   				<c:when test="${comuneIsa.autenticazioneForte}">
									   				<i class="fa fa-lock fa-lg"" title="<spring:message code="label.autenticazione.REGIONE_PUGLIA.forte"/>"></i>&nbsp;
								   				</c:when>
								   				<c:otherwise>
									   				<i class="fa fa-unlock fa-lg"" title="<spring:message code="label.autenticazione.REGIONE_PUGLIA.debole"/>"></i>&nbsp;
								   				</c:otherwise>
								   			</c:choose>
										</td>
			   						</c:if>
									<c:if test="${spidEnable}">
										<td class="center">
											<i class="fa fa-spid-level${comuneIsa.livelloAutenticazione}" title="<spring:message code="label.autenticazione.SPID.livello" arguments="${comuneIsa.livelloAutenticazione}"/>"></i>&nbsp;
										</td>
			   						</c:if>
								</tr>
							</c:forEach>
							
						</table>
					</c:when>
					<c:otherwise>
						<spring:message code="label.no.comuni"/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:if>
</div>