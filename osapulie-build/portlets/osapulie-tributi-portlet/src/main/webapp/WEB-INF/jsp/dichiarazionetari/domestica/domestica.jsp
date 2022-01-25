<div id="domestica"> 
	<fieldset>
		<legend><spring:message code="legend.dichiarazione" /></legend>
		
		<div id="div_domestica_trasferimento">
			<label><spring:message code="label.trasferimento" /></label>
	    </div>
		<div id="div_domestica_cessazione">
		<fieldset>
		 	<legend><spring:message code="legend.cessazione" /></legend>
			<jsp:include page="../dichiarazionetari/tables/table_dom_cessazione.jsp"></jsp:include>
			<jsp:include page="../dichiarazionetari/domestica/dom_cessazione.jsp"></jsp:include>
		</fieldset>
		</div>
		<div id="div_domestica_iscrizione">
		<fieldset>
		 	<legend><spring:message code="legend.iscrizione" /></legend>
			<jsp:include page="../dichiarazionetari/tables/table_dom_iscrizione.jsp"></jsp:include>
			<jsp:include page="../dichiarazionetari/domestica/dom_iscrizione.jsp"></jsp:include>
		</fieldset>
		</div>
		<div id="div_domestica_variazione">
		<fieldset>
		 	<legend><spring:message code="legend.variazione" /></legend>
			<jsp:include page="../dichiarazionetari/tables/table_dom_variazione.jsp"></jsp:include>
			<jsp:include page="../dichiarazionetari/domestica/dom_variazione.jsp"></jsp:include>
		</fieldset>
		</div>
		
		<div id="div_sgravio">
			<label><spring:message code="label.chiede" />: </label>
			<table class="genericTable">
				<tr>
					<td>
						<form:select id="select_dom_rimborso" path="concessioneQuoteDom">
						   <form:option value="sgravio" label="Sgravio/Discarico"/>
						   <form:option value="rimborso" label="Rimborso"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>
						<div id="div_dom_rimborso">
							<table class="genericTable">
								<tr>
									<td>
										<form:radiobutton name="btn_dom_cessazione"path="modalitaRimborsoDom" value="filiale"/><spring:message code="label.filiale" />
									</td>
								</tr>
								<tr>
									<td>
										<form:radiobutton name="btn_dom_cessazione" path="modalitaRimborsoDom" value="accredito"/><spring:message code="label.accredito" />
										<div><form:errors path="modalitaRimborsoDom" cssStyle="color:red"/></div>
										<div id="div_dom_iban" style="display:none;">
											<label><spring:message	code="label.iban" />*:</label>&nbsp;
											<form:input path="ibanDom" />&nbsp;
											<div><form:errors path="ibanDom" cssStyle="color:red"/></div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</fieldset>
</div>