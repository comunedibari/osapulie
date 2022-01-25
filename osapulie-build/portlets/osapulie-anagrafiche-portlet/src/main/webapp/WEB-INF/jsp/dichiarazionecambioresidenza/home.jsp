<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneCambioResidenzaUrl">
	<portlet:param name="action" value="getDichiarazioneCambioResidenzaForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadCambioResidenzaUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<p>
			<c:choose>
				<c:when test="${isDichiarazioneTariOk}">
					<c:if test="${!empty bozza}">
						<spring:message code="label.home.bozza.part1" /><spring:message code="button.dichiarazione.bozza" /><spring:message code="label.home.bozza.part2" /><br>
					</c:if>
					<spring:message code="label.home.option1.part1" /><spring:message code="button.dichiarazione.nuova" /><spring:message code="label.home.option1.part2" />
					<c:choose>
						<c:when test="${!isInteroperabilitaEnable}">
							<br>
							<spring:message code="label.home.option2.part1" /><spring:message code="link.uploadfile" /><spring:message code="label.home.option2.part2" />
						</c:when>
						<c:otherwise>
							<c:if test="${!empty datiDichiarazione && datiDichiarazione.dichiarazioneCompletata == true}">
								<br>
								<spring:message code="label.home.option2.part1" /><spring:message code="link.uploadfile" /><spring:message code="label.home.option2.part2" />
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<spring:message code="label.home.omissione.part0" /><br>
					<spring:message code="label.home.omissione.part1" /><spring:message code="button.dichiarazione.nuova.tari" /><spring:message code="label.home.omissione.part2" /><br>
					<spring:message code="label.home.omissione.part3" /><spring:message code="button.dichiarazione.salta.invio" /><spring:message code="label.home.omissione.part4" /><br>
				</c:otherwise>
			</c:choose>
		</p>
		<c:choose>
			<c:when test="${isDichiarazioneTariOk}">
				<!-- Caricamento pulsante bozza -->
				<c:if test="${!empty bozza}">
					<portlet:actionURL var="getBozzaFromURL">
						<portlet:param name="action" value="getBozzaFrom" />
					</portlet:actionURL>
					<a class="custom_pulsante" href="${getBozzaFromURL}">
						<spring:message code="button.dichiarazione.bozza" />
					</a>
					<span class="spacer"></span>
				</c:if>
				<a class="custom_pulsante" href="${dichiarazioneCambioResidenzaUrl}">
					<spring:message code="button.dichiarazione.nuova" />
				</a>
				<c:choose>
					<c:when test="${!isInteroperabilitaEnable}">
						<span class="spacer"></span>
						<a class="custom_pulsante" href="${uploadCambioResidenzaUrl}">
							<spring:message code="link.uploadfile" />
						</a>
						
					</c:when>
					<c:otherwise>
						<c:if test="${!empty datiDichiarazione && datiDichiarazione.dichiarazioneCompletata == true}">
							<span class="spacer"></span>
							<a class="custom_pulsante" href="${uploadCambioResidenzaUrl}">
								<spring:message code="link.uploadfile" />
							</a>
					 		 	
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<span class="spacer"></span>
				<a class="custom_pulsante" href="${dichiarazioneTariUrl}">
					<spring:message code="button.dichiarazione.nuova.tari" />
				</a>
				<span class="spacer"></span>
				<portlet:actionURL var="redirectSegnalazioneCustomUrl">
					<portlet:param name="action" value="redirectSegnalazioneCustom" />
				</portlet:actionURL>
				<a class="custom_pulsante" id="dichiarazioneOmessaLink" href="${redirectSegnalazioneCustomUrl}">
					<spring:message code="button.dichiarazione.salta.invio" />
				</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>