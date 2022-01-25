<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="agevolazioneTariUrl">
	<portlet:param name="action" value="getAgevolazioneTariForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadAgevolazioneUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<p>
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
					<c:if test="${!empty datiAgevolazioneTari && datiAgevolazioneTari.dichiarazioneCompletata == true}">
						<br>
						<spring:message code="label.home.option2.part1" /><spring:message code="link.uploadfile" /><spring:message code="label.home.option2.part2" />
					</c:if>
				</c:otherwise>
			</c:choose>
			
		</p>
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
		
		<a class="custom_pulsante" href="${agevolazioneTariUrl}">
			<spring:message code="button.dichiarazione.nuova" />
		</a>
		<c:if test="${!empty datiAgevolazioneTari && datiAgevolazioneTari.dichiarazioneCompletata == true}">
			<span class="spacer"></span>
			<a class="custom_pulsante" href="${uploadAgevolazioneUrl}">
				<spring:message code="link.uploadfile" />
			</a>
		</c:if>
	</div>
</div>