<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneTariUrl">
	<portlet:param name="action" value="getDichiarazioneTariForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadTariUrl">
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
					<c:if test="${!empty datiTari && datiTari.dichiarazioneCompletata == true}">
						<br>
						<spring:message code="label.home.option2.part1" /><spring:message code="link.uploadfile" /><spring:message code="label.home.option2.part2" />
					</c:if>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${!isInteroperabilitaEnable}">
				
			</c:if>
			<c:if test="${invioSegnalazioneCustomEnable}">	
				<br><spring:message code="label.home.option3.part1" /><spring:message code="button.dichiarazione.salta.invio" /><spring:message code="label.home.option3.part2" />
			</c:if>
		</p>
		<c:if test="${!empty bozza}">
			<!-- Caricamento pulsante bozza -->
			<portlet:actionURL var="getBozzaFromURL">
				<portlet:param name="action" value="getBozzaFrom" />
			</portlet:actionURL>
			<a class="custom_pulsante" href="${getBozzaFromURL}">
				<spring:message code="button.dichiarazione.bozza" />
			</a>
			<span class="spacer"></span>
		</c:if>
		<a href="${dichiarazioneTariUrl}" class="custom_pulsante">
			<spring:message code="button.dichiarazione.nuova" />
		</a>
		<c:choose>
			<c:when test="${!isInteroperabilitaEnable}">
				<span class="spacer"></span>
				<a href="${uploadTariUrl}" class="custom_pulsante">
					<spring:message code="link.uploadfile" />
				</a>
			</c:when>
			<c:otherwise>
				<c:if test="${!empty datiTari && datiTari.dichiarazioneCompletata == true}">
					<span class="spacer"></span>
					<a href="${uploadTariUrl}" class="custom_pulsante">
						<spring:message code="link.uploadfile" />
					</a>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${invioSegnalazioneCustomEnable}">	
			<!-- Caricamento pulsante CAF per recesso invio dichiarazione -->
			<span class="spacer"></span>
			<portlet:actionURL var="redirectSegnalazioneCustomUrl">
				<portlet:param name="action" value="redirectSegnalazioneCustom" />
			</portlet:actionURL>
			<a class="custom_pulsante backgroundRed" id="dichiarazioneOmessaLink" href="${redirectSegnalazioneCustomUrl}">
				<spring:message code="button.dichiarazione.salta.invio" />
			</a>
		</c:if>	
	</div>
</div>