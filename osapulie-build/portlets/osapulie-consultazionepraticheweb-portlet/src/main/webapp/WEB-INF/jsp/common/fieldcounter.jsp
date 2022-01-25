<form:input id="text${nomeCampo }" path="${nomeCampo }" readonly="true" />&nbsp;
<portlet:actionURL var="aggiornaCounterUrl">
	<portlet:param name="action" value="addCounter" />
	<portlet:param name="idField" value="${item.campiAggiuntivi.id}" />
	<portlet:param name="idFieldPratica" value="${item.id}" />
</portlet:actionURL>
<input type="checkbox" name="aggionaCounter${k}" value="S"/>&nbsp;<spring:message code="button.aggiornaCounter" />
&nbsp;<form:errors path="${nomeCampo }" cssStyle="color:red"/>