<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="saveDelegaUrl">
	<portlet:param name="action" value="saveDelega" />
</portlet:actionURL>
<div class="mainDiv editDelega">
	<fieldset>
		<c:choose>
			<c:when test="${delega.idDelega != 0}">
	 			<legend><spring:message code="label.editDelega"/></legend>
	 		</c:when>
	 		<c:otherwise>
	 			<legend><spring:message code="label.addDelega"/></legend>
	 		</c:otherwise>
	 	</c:choose>
	 	<form:form action="${saveDelegaUrl}" commandName="delega" name="delega" method="post">
	 		<form:hidden path="idDelegato" />
	 		<form:hidden path="idDelegante" />
	 		<form:hidden path="idDelega" />
		 	<table class="genericTable">
		 		<tr>
		 			<td width="250">
		 				<spring:message code="label.comune"/>:
		 			</td>
		 			<td>
		 				<form:select path="idComuneIsa">
							<form:option value="0"><spring:message code="label.seleziona"/></form:option>
						    <form:options items="${comuniIsaList}" itemLabel="nome" itemValue="id"/>
						</form:select>
						<input type="submit" name="cambio" value="<spring:message code="button.edit"/>">
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.pivacf"/>*:
		 			</td>
		 			<td>
		 				<form:select path="piva">
							<form:option value="0"><spring:message code="label.seleziona"/></form:option>
							<c:forEach var="profiloUtenteProfessionista" items="${profiliUtenteProfessionistaAttivi}">
						        <form:option value="${profiloUtenteProfessionista.partitaIva}"><c:out value="${profiloUtenteProfessionista.partitaIva} - ${profiloUtenteProfessionista.ragioneSociale}"/></form:option>
						    </c:forEach>
						</form:select>
		 				<form:errors path="piva" cssStyle="color:red"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<spring:message code="label.servizi"/>:
		 			</td>
		 			<td>
	 					<c:forEach items="${serviziComuneIsaList}" var="servizio" varStatus="status">
 							<input type="checkbox" name="serviziAssociatiStrings" value="${servizio.id}" <c:if test="${serviziDelegaMap[servizio.id] != null}">checked="checked"</c:if>>&nbsp; <c:out value="${servizio.nomeServizio}"/></br>
	 					</c:forEach>
		 			</td>
		 		</tr>
		 	</table>
		 	<%@ include file="../common/footer.jsp"%>
		 	<div class="container_pulsante">
				<input type="submit" value="<spring:message code="button.salva"/>" onclick="javascript:return(confirm('<spring:message code="message.confirm" />'))">
				<span class="spacer"></span>
				<a class="custom_pulsante" href="${home}">
					<spring:message code="button.annulla" />
				</a>
			</div>
	 	</form:form>
 	</fieldset>
</div>