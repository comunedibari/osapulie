<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>


<script type="text/javascript">
	$(document).ready(function() {
						
						$.datepicker.setDefaults($.datepicker.regional['it']);
						$(".data_input").datepicker({
							dateFormat : "dd/mm/yy"
						});
			});
			
</script>

<div class="mainDiv">
	<fieldset>
		<legend><spring:message code="label.ricercaRichieste"/></legend>
		<form:form id="searchForm" action="${home}" modelAttribute="monitorSearchForm" method="post">
			<table>	
				<tr>
					<td>
						<label><spring:message code="label.comune"/>:</label>&nbsp;&nbsp;<form:select  path="comune" >
					  																		<form:option value = "" label = ""/> 
																							<form:options items="${comuni}" itemLabel="nome" itemValue="id" /></form:select>
					</td>
					<td>
						<label><spring:message code="label.intermediario"/>:</label>&nbsp;&nbsp;<form:select path="intermediario" >
																									<form:option value = "" label = ""/>
																									<form:options items="${aziende}" itemLabel="ragioneSociale" itemValue="id"/></form:select>
					</td>
					<td>
						<label><spring:message code="label.tipologia"/>:</label>&nbsp;&nbsp;<form:select path="tipologia" >
																									<form:option value = "" label = ""/>
																									<form:options items="${tipologie}" itemLabel="nome" itemValue="id"/></form:select>
					</td>
					
					
					<td>
						<label><spring:message code="label.codiceFiscaleResponsabile"/>:</label>&nbsp;&nbsp;<form:input path="codiceFiscaleResponsabile"/>
					</td>					
				</tr>
				 <tr>
					<td>
						<label><spring:message code="label.dataRichiestaDa"/>:</label>&nbsp;&nbsp;<br><form:input path="dataRichiestaDa"  type="date" size="10" cssClass="data_input" />
				    </td>
				    <td>
						<label><spring:message code="label.dataRichiestaA"/>:</label>&nbsp;&nbsp;<br><form:input path="dataRichiestaA"  type="date" size="10" cssClass="data_input" />
				    </td>
				    <td>
				    </td>
				    <td>
				    </td>
		 		</tr> 
			</table>
			<div class="container_pulsante">
				<input type="submit" value="<spring:message code="button.cerca" />" />
			</div>
		</form:form>	
	</fieldset>
	<fieldset>
		<legend><spring:message code="label.listaRichieste"/></legend>
		<display:table id="paginatedTable" 
			requestURI="${home}"
			name="richieste"
			uid="richieste" 
			cellpadding="2" 
			cellspacing="0" 
			pagesize="20" 
			defaultorder="ascending" 
			defaultsort="1" 
			sort="list" 
			class="genericTable elencoRisultati">
			<display:column sortable="true" property="comuneErogatore.nome" titleKey="label.comune" />
			<display:column sortable="true" property="delegato.azienda.ragioneSociale" titleKey="label.intermediario" />
			<display:column sortable="true" property="delegato.profiloUtenteCittadino.codiceFiscale" titleKey="label.codiceFiscale" />
			<display:column sortable="true" property="servizio.tipologie[0].nome" titleKey="label.tipologia" />
		 	<%-- <display:column sortable="true" titleKey="label.tipologia"><s:iterator value="#richieste">
        																	<s:property value="servizio.tipologie.nome" />
    																   </s:iterator>
    		</display:column> --%>
			<display:column sortable="true" property="dataRichiesta" titleKey="label.dataRichiesta" format="{0,date,dd/MM/yyyy - HH:mm}"/>
		</display:table>
	</fieldset>
</div>
<!-- <script type="text/javascript">

$(document).ready(function(){

		$.datepicker.setDefaults($.datepicker.regional['it']);
		$(".data_input").datepicker({
				dateFormat : "dd/mm/yy"
		});
	});
	
</script>
 -->