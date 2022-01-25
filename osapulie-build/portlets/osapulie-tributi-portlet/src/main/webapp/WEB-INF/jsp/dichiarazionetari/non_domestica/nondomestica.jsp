<div id="non_domestica" style="display:none;">
	<fieldset>
		<legend><spring:message code="legend.dichiarazione" /></legend>
		
		<div id="div_non_domestica_trasferimento">
			<label><spring:message code="label.trasferimento" /></label>
	    </div>
	    
	    <div id="div_non_domestica_cessazione">
		<fieldset> 
		 	<legend><spring:message code="legend.cessazione" /></legend>
			<jsp:include page="../dichiarazionetari/tables/table_nd_cessazione.jsp"></jsp:include>
			<jsp:include page="../dichiarazionetari/non_domestica/non_dom_cessazione.jsp"></jsp:include>
		</fieldset>	
		</div>
		
	    <div id="div_non_domestica_variazione">
	     <fieldset>
		 	<legend><spring:message code="legend.variazione" /></legend>
		    	<jsp:include page="../dichiarazionetari/tables/table_nd_variazione.jsp"></jsp:include>
				<jsp:include page="../dichiarazionetari/non_domestica/non_dom_variazione.jsp"></jsp:include>
			</fieldset>		
		</div>
		
		<div id="div_non_domestica_iscrizione">
		 <fieldset>
		 	<legend><spring:message code="legend.iscrizione" /></legend>
			<table class="genericTable">
				<tr>
					<td width="180">
					 <label><spring:message code="label.attivita.prevalente" /></label>
					</td>
					<td width="180">
						<form:input path="attivitaPrevalente" />
						<div><form:errors path="attivitaPrevalente" cssStyle="color:red"/></div> 
					</td>
					<td width="180">
					 <label><spring:message code="label.codiceAteco" />*</label>
					</td>
					<td width="180">
						<form:input path="codiceAteco" />
						<div><form:errors path="codiceAteco" cssStyle="color:red"/></div> 
					</td>
				</tr>
			</table>
			<jsp:include page="../dichiarazionetari/tables/table_nd_iscrizione.jsp"></jsp:include>
			<jsp:include page="../dichiarazionetari/non_domestica/non_dom_iscrizione.jsp"></jsp:include>
		 </fieldset>
	    </div>
	    
	    <div id="div_nd_sgravio">
	    	<label><spring:message code="label.chiede" />: </label>
			<table class="genericTable">
				<tr>
					<td>
						<form:select id="select_nd_rimborso" path="concessioneQuoteNonDom">
						   <form:option value="sgravio" label="Sgravio/Discarico"/>
						   <form:option value="rimborso" label="Rimborso"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>
						<div id="div_nd_rimborso">
							<table class="genericTable">
								<tr>
									<td>
										<form:radiobutton path="modalitaRimborsoNonDom" value="filiale"/><spring:message code="label.filiale" />
									</td>
								</tr>
								<tr>
									<td>
										<form:radiobutton path="modalitaRimborsoNonDom" value="accredito"/><spring:message code="label.accredito" />
										<div><form:errors path="modalitaRimborsoNonDom" cssStyle="color:red"/></div>
										<div id="div_nd_iban" style="display:none;">
											<label><spring:message	code="label.iban" />:</label>&nbsp;
											<form:input path="ibanNonDom" />&nbsp;
											<div><form:errors path="ibanNonDom" cssStyle="color:red"/></div>
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