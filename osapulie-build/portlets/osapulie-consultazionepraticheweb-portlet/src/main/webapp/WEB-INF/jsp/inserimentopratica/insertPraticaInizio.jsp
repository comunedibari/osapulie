		<tr>
			<td colspan="2" style="text-align:center;">
				<h3 align=center style='margin-top:7.0pt;text-align:center'>${datiPratica.tipologia.descrizione}</h3>
			</td>
			</tr>
		<tr>
			<td>
				<spring:message code="label.dataRichiesta" />:*
			</td>
			<td>
				<form:input maxlength="10" size="11" readonly="true" id="textDataRichiesta" path="dataRichiesta" onblur="controllaData(this);" />&nbsp;
				<form:errors path="dataRichiesta" cssStyle="color:red"/>
			</td>			
		</tr>					
		<tr>
			<td>
				<spring:message code="label.oggettoRichiesta" />:*
			</td>
			<td>
				<form:textarea rows="4" cols="100" maxlength="500" id="textOggettoRichiesta" path="oggettoRichiesta" /><br/>
				<form:errors path="oggettoRichiesta" cssStyle="color:red"/>
			</td>
		</tr>	
		<tr>
			<td>
				<spring:message code="label.titolare" />:*
			</td>
			<td>
				<form:input maxlength="255" size="60" id="textTitolare" path="titolare" />&nbsp;                
				<form:errors path="titolare" cssStyle="color:red"/>
			</td>			
		</tr>
		<tr>
			<td>
				<spring:message code="label.tecnico" />:*
			</td>
			<td>
				<form:input maxlength="255" size="60" id="textTecnico" path="tecnico" />&nbsp;                
				<form:errors path="tecnico" cssStyle="color:red"/>
			</td>			
		</tr>
		<tr>
			<td>
				<spring:message code="label.impresa" />:*
			</td>
			<td>
				<form:input maxlength="255" size="60" id="textImpresa" path="impresa" />&nbsp;                
				<form:errors path="impresa" cssStyle="color:red"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:set var="nomeCampo" value="campiaggiuntivi[69].valore" scope="page"/>
						<c:set var="valoriCampo" value="${datiPratica.campiaggiuntivi[69].campiAggiuntivi.listaValori}" scope="page"/>
						<c:set var="valoreCampo" value="${datiPratica.campiaggiuntivi[69].valore}" scope="page"/>
						<%@ include file="../common/fieldcheckbox.jsp"%>
			</td>			
		</tr>
		<tr><td><br/></td></tr>
		