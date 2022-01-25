<%@ include file="common.jsp"%>
<portlet:actionURL var="confermaPagamento">
	<portlet:param name="action" value="confermaPagamento" />		
</portlet:actionURL>

<div class="mainDiv">
	<form:form id="pagamento" action="${confermaPagamento}" method="post" commandName="paymentRequestDto">
		<input type="hidden" name="identificativoRata" value="${identificativoRata}" />
		<form:hidden path="causale"/>
		<form:hidden path="idServizio"/>
		<form:hidden path="codiceCategoriaServizio"/>
		<form:hidden path="codiceOrganizzazione"/>
		<form:hidden path="callBackUrlOrganizzazione"/>
		<form:hidden path="callBackEndpointWsUrlOrganizzazione"/>
		<form:hidden path="emailQuietanza"/>
		<table class="pagamentoTable">
			<tr>
				<td>
					<spring:message code="label.nomeECognome" />
				</td>
				<td>
					<span><c:out value="${paymentRequestDto.denominazioneCliente}"/></span>
					<form:hidden path="denominazioneCliente"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.cf" />
				</td>
				<td>
					<span><c:out value="${paymentRequestDto.idFiscaleCliente}"/></span>
					<form:hidden path="idFiscaleCliente"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.descrizioneServizio" />
				</td>
				<td>
					<span><c:out value="${paymentRequestDto.descrizioneServizio}"/></span>
					<form:hidden path="descrizioneServizio"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.identificativoCredito" />
				</td>
				<td>
					<span><c:out value="${paymentRequestDto.identificativoCredito}"/></span>
					<form:hidden path="identificativoCredito"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.importoServizio" />
				</td>
				<td>
					<span><fmt:formatNumber value="${paymentRequestDto.importoServizio}" type="currency" currencySymbol="&euro;"/></span>
					<form:hidden path="importoServizio"/>
				</td>
			</tr>
		</table>
		<div class="container_pulsante">
			<portlet:renderURL var="homeURL">
			</portlet:renderURL>
			<a href="${homeURL}" class="custom_pulsante">
				<spring:message code="label.annulla"  />
			</a>  
			<span class="spacer"></span>
			<c:choose>
				<c:when test="${pagamentoAbilitato}">
					<input type="submit" name="invia" value='<spring:message code="label.confermaPagamento" />'/>
				</c:when>
				<c:otherwise>
					<input type="button" disabled="disabled" name="invia" value='<spring:message code="label.confermaPagamento" />'/>
				</c:otherwise>
			</c:choose>
		</div>
	</form:form>
</div>