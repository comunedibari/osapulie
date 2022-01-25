<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="visuraAvvisiBonariAction">
	<portlet:param name="action" value="visura" />
</portlet:actionURL>
<div class="mainDiv">
	<form:form id="visura" action="${visuraAvvisiBonariAction}" method="post" cssClass="printForm"> 
		<div class="marginBottom10">
			<label><spring:message code="label.anno" />:</label>&nbsp;
			<select name="year">
				<c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
					<option value="${i}" <c:if test="${i == year}"> selected="selected" </c:if> >${i}</option>
				</c:forEach>
			</select>
			<input type="submit" name="invia" value='<spring:message code="button.cerca" />' />
		</div>
		<c:choose>
			<c:when test="${empty risposta.errore && !empty risposta.documento}">
				<table>
					<tr>
						<th><spring:message code="label.idDocumento" /></th>
						<th><spring:message code="label.descrizioneDocumento" /></th>
						<th style="width: 20%; text-align: center;"><spring:message code="label.download" /></th>
					</tr>
					<c:forEach items="${risposta.documento}" var="documento">
						<tr>
							<td><c:out value="${documento.id}"/></td>
							<td><c:out value="${documento.descrizione}"/></td>
							<td style="text-align: center;">
								<portlet:resourceURL var="downloadDocumentoURL" id="downloadDocumento" escapeXml="false">
									<portlet:param name="idDocumento" value="${documento.id}" />
								</portlet:resourceURL>
								<a href="${downloadDocumentoURL}" class="custom_pulsante"><spring:message code="button.downloadDocumento" /></a>
							</td>
						</tr>				
					</c:forEach>
				</table>	
				<div class="container_pulsante">
					<%@ include file="../common/valutaservizio.jsp"%>
				</div>
			</c:when>
			<c:otherwise>
				<spring:message code="label.documentiNonTrovati" />
			</c:otherwise>
		</c:choose>
	</form:form>
</div>