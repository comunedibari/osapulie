<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="updateProfiloUtente">
	<portlet:param name="action" value="updateProfiloUtente" />		
</portlet:actionURL>

<div class="mainDiv profiloUtente">
	<c:choose>
		<c:when test="${profiloUtente != null}">
			<form:form id="profiloUtenteForm" action="${updateProfiloUtente}" method="post" commandName="profiloUtente">
				
				<fieldset>
					<legend><spring:message code="label.datiAnagrafici"/></legend>	
					<table class="genericTable">
						<tr>
							<td width="250">
								<label><spring:message code="label.datiAnagrafici.nome"/>*:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
										<c:out value="${profiloUtente.nome}"/>		
									</c:when>
									<c:otherwise>					
										<form:input id="textNome" path="nome" />
										<div>
											<form:errors path="nome" cssStyle="color:red"/>
										</div>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message code="label.datiAnagrafici.cognome"/>*:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
										<c:out value="${profiloUtente.cognome}"/>		
									</c:when>
									<c:otherwise>					
										<form:input id="textCognome" path="cognome"/>
										<div>
											<form:errors path="cognome" cssStyle="color:red"/>
										</div>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>	
							<td>
								<label><spring:message code="label.datiAnagrafici.secondoNome"/>:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
										<c:out value="${profiloUtente.secondoNome}"/>		
									</c:when>
									<c:otherwise>					
										<form:input id="textSecondoNome" path="secondoNome"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>	
							<td>
								<label><spring:message code="label.datiAnagrafici.email"/>*:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp}">
										<c:out value="${profiloUtente.mail}"/>		
									</c:when>
									<c:otherwise>					
										<form:input id="textEmail" path="mail" size="40"/>
										<div>
											<form:errors path="mail" cssStyle="color:red"/>
										</div>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>	
							<td>
								<label><spring:message code="label.datiAnagrafici.pec"/>:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp}">
										<c:out value="${profiloUtente.mailPec}"/>		
									</c:when>
									<c:otherwise>					
										<form:input id="textPec" path="mailPec" size="40"/>
										<div>
											<form:errors path="mailPec" cssStyle="color:red"/>
										</div>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>	
							<td>
								<label><spring:message code="label.datiAnagrafici.canaleComunicazione"/>:</label>
							</td>
							<td>
								<form:radiobuttons path="canaleComunicazione" items="${canaliComunicazione}" />
							</td>
						</tr>
						<c:if test="${!disableForIdp}">
							<tr>	
								<td>
									<label><spring:message code="label.datiAnagrafici.dataNascita"/>:</label>
								</td>
								<td>
									<c:choose>
										<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
											<fmt:formatDate pattern="dd/MM/yyyy" value="${profiloUtente.dataNascita}" />
										</c:when>
										<c:otherwise>					
											<liferay-ui:input-date dayValue="${inputDataNascitaDay}" monthValue="${inputDataNascitaMonth}" yearValue="${inputDataNascitaYear}" dayParam="dataNascitaDay" monthParam="dataNascitaMonth" yearParam="dataNascitaYear" yearRangeStart="1900" yearRangeEnd="2030" />
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>	
								<td>
									<label><spring:message code="label.datiAnagrafici.sesso"/>:</label>
								</td>
								<td>
									<c:choose>
										<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
											<c:if test="${profiloUtente.uomo}">
												M
											</c:if>
											<c:if test="${!profiloUtente.uomo}">
												F
											</c:if>
										</c:when>
										<c:otherwise>					
											<form:radiobutton label="M" value="true" id="radioSessoM" path="uomo" />
											<form:radiobutton label="F" value="false" id="radioSessoF" path="uomo" />
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:if>
						<tr>	
							<td>
								<label><spring:message code="label.datiAnagrafici.residenza.indirizzo"/>:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
										<c:out value="${profiloUtente.viaResidenza}"/>		
									</c:when>
									<c:otherwise>					
										<form:input id="textIndirizzoResidenza" path="viaResidenza"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>	
							<td>
								<label><spring:message code="label.datiAnagrafici.residenza.nrCivico"/>:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
										<c:out value="${profiloUtente.nrCivicoResidenza}"/>		
									</c:when>
									<c:otherwise>					
										<form:input id="texNrCivicoe" path="nrCivicoResidenza"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>	
							<td>
								<label><spring:message code="label.datiAnagrafici.residenza.comune"/>:</label>
							</td>
							<td>
								<c:choose>
									<c:when test="${disableForIdp || profiloUtente.autenticazioneForte}">
										<c:out value="${profiloUtente.comuneResidenzaString}"/>		
									</c:when>
									<c:otherwise>					
										<form:select path="comuneResidenza" id="selectComuneResidenza">
											<form:option value="0"><spring:message code="label.seleziona"/></form:option>
											<form:options items="${comuneList}" itemLabel="denominazione" itemValue="id"/>
										</form:select>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</fieldset>
				
				<fieldset>
					<legend><spring:message code="label.preferenze"/></legend>
					<table class="genericTable">
						<tr>	
							<td width="250">
								<label><spring:message code="label.preferenze.comune"/>:</label>
							</td>
							<td>
								<form:select path="comuneIsa" id="selectComuneIsa">
									<form:option value="0"><spring:message code="label.seleziona"/></form:option>
									<form:options items="${comuneISAList}" itemLabel="nome" itemValue="id"/>
								</form:select>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend><spring:message code="label.datiAutenticazione"/></legend>
					<table class="genericTable">
						<tr>
							<td width="250">
								<spring:message code="label.datiAutenticazione.username"/>*:
							</td>
							<td>
								<c:choose>
									<c:when test="${!profiloUtente.autenticatoForte && !disableForIdp}">
										<form:input id="textUsername" path="username" cssClass="upperCase" maxlength="16"/>
										<div>
											<form:errors path="username" cssStyle="color:red"/>
										</div>
									</c:when>
									<c:otherwise>
										<c:out value="${profiloUtente.username}"/>									
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<c:if test="${!profiloUtente.autenticatoForte && !disableForIdp}">
							<tr>
								<td>
									<spring:message code="label.datiAutenticazione.password"/>*:
								</td>
								<td>
									<form:password id="textPassword" path="password"/>
									<div>
										<form:errors path="password" cssStyle="color:red"/>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<spring:message code="label.datiAutenticazione.rePassword"/>*:
								</td>
								<td>
									<form:password id="textPassword" path="rePassword"/>
									<div>
										<form:errors path="rePassword" cssStyle="color:red"/>
									</div>
								</td>
							</tr>
						</c:if>
						<tr>	
							<td>
								<spring:message code="label.datiAutenticazione.tipoAutenticazione"/>
							</td>
							<td class="autenticazioneTd">
								<c:choose>
									<c:when test="${profiloUtente.autenticazioneForte}">
										<img src="<%=request.getContextPath() %>/images/ok.png" alt="<spring:message code="label.images.autenticatoForteOk"/>" title="<spring:message code="label.images.autenticatoForteOk"/>"/>
										<span><spring:message code="label.images.autenticatoForteOk"/></span>
									</c:when>
									<c:otherwise>
										<img src="<%=request.getContextPath() %>/images/ko.png" alt="<spring:message code="label.images.autenticatoForteKo"/>" title="<spring:message code="label.images.autenticatoForteKo"/>"/>
										<span><spring:message code="label.images.autenticatoForteKo"/></span>
										<c:if test="${!disableForIdp}">
											<div>
												<portlet:renderURL var="renderRichiediPin">
													<portlet:param name="action" value="renderRichiediPin" />		
												</portlet:renderURL>
												<a href="${renderRichiediPin}">
													<spring:message code="label.richiediPin"/>
												</a>
												<c:if test="${profiloUtente.statoPin ==  PortletConstants.STATO_PIN_RICHIESTO}">
													<span class="italic"><spring:message code="label.pinRichiesto"/></span>
												</c:if>
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>
								<%-- <portlet:renderURL var="renderSmartCard">
									<portlet:param name="action" value="renderSmartCard" />		
								</portlet:renderURL>
								<a href="${renderSmartCard}">
									<spring:message code="label.datiAnagraficiAvanzati.agganciaSmartCard"/>
								</a> --%>
							</td>
						</tr>
					</table>
				</fieldset>
				<%@ include file="../common/footer.jsp"%>
				<div class="container_pulsante">
					<input type="submit" name="invia" value="<spring:message code="button.salva"/>" <c:if test="${!profiloUtente.autenticazioneForte}">onclick="javascript:return confirm('<spring:message code="confirm.modificaCodiceFiscale"/>');"</c:if>/>
				</div>
			</form:form>
			</c:when>
		<c:otherwise>
			<div>
				<strong><spring:message code="label.noUserFound"/></strong>
			</div>							
		</c:otherwise>
	</c:choose>
</div>