<%@ include file="../../common/import.jsp"%>

<%-- Motivazioni --%>
<fieldset id="motivazioniFieldset">
	<legend><spring:message code="legend.motivazioni" /></legend>
	<div><form:errors path="motivoCessazioneDom" cssStyle="color:red"/></div> 
	<table class="genericTable">
		<tr>
			<td>
				<form:radiobutton id="chk_dom_decesso" path="motivoCessazioneDom" value="decesso"/><spring:message code="check.decesso" />
				<div id="div_dom_decesso" style="display:none">
					<table class="genericTable">
						<tr>
							<td>
								<label><spring:message	code="label.personaDeceduta" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="nominativoDeceduto" />&nbsp;
								<div><form:errors path="nominativoDeceduto" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>	
							<td>
								<label><spring:message	code="label.dataDecesso" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="dataDecesso" id="data_decesso" size="10" cssClass="data_input"/>&nbsp;
								<div><form:errors path="dataDecesso" cssStyle="color:red"/></div> 
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<form:radiobutton id="chk_coabitazione" path="motivoCessazioneDom" value="coabitazione"/><spring:message code="check.coabitazione" />
				<div id="div_coabitazione" style="display:none;">
					<table class="genericTable">
						<tr>
							<td>
								<label><spring:message code="label.soggetto" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="coabitanteDom" />&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message	code="label.indirizzo" />*:</label>&nbsp;
							</td>
							<td colspan="3">
								<osapulie:stradario id="indirizzoCoabitazione" viaName="indirizzoCoabitazione" civicoName="civicoCoabitazione" 
									esponenteName="espCoabitazione" 
									viaOptionValue="${datiTari.indirizzoCoabitazione}" 
									civicoOptionValue="${datiTari.civicoCoabitazione}" 
									esponenteValue="${datiTari.espCoabitazione}" 
									viaOptionText="${datiTari.indirizzoCoabitazioneTextHidden}"
									civicoOptionText="${datiTari.civicoCoabitazioneTextHidden}" 
									viaTextHiddenName="indirizzoCoabitazioneTextHidden"
									civicoTextHiddenName="civicoCoabitazioneTextHidden"
									loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
								<div><form:errors path="indirizzoCoabitazione" cssStyle="color:red"/></div> 
								<div><form:errors path="civicoCoabitazione" cssStyle="color:red"/></div> 
								<div><form:errors path="espCoabitazione" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message	code="label.cap" />:</label>&nbsp;
							</td>
							<td>
								<form:input path="capCoabitazione"  size="2" maxlength="5"/>&nbsp;
								<div><form:errors path="capCoabitazione" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.sc" />:</label>&nbsp;
							</td>
							<td>
								<form:input path="scalaCoabitazione"  size="2" maxlength="2"/>&nbsp;
								<div><form:errors path="scalaCoabitazione" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.piano" />:</label>&nbsp;
							</td>
							<td>
								<form:input path="pianoCoabitazione" size="2" maxlength="2"/>&nbsp;
								<div><form:errors path="pianoCoabitazione" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message	code="label.int" />:</label>&nbsp;
							</td>
							<td>
								<form:input path="intCoabitazione" size="2" maxlength="2"/>&nbsp;
								<div><form:errors path="intCoabitazione" cssStyle="color:red"/></div>
							</td>
							<td>
								<label><spring:message	code="label.suff" />:</label>&nbsp;
							</td>
							<td>
								<form:input path="suffCoabitazione" size="2" />&nbsp;
								<div><form:errors path="suffCoabitazione" cssStyle="color:red"/></div>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<form:radiobutton id="chk_dom_trasferimento" path="motivoCessazioneDom" value="trasferimento"/><spring:message code="check.emigrazione" />
				<div id="div_dom_trasferimento" style="display:none;">
					<table class="genericTable">
						<tr>
							<td>
								<label><spring:message	code="label.comune" />*:</label>&nbsp;
							</td>
							<td>
								<form:select id="comuneEmigrazioneDom" path="comuneEmigrazioneDom">
									<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
									<form:options items="${comuniList}" itemLabel="denominazione" itemValue="codiceIstatAN"  />
								</form:select>
								<div><form:errors path="comuneEmigrazioneDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.prov" />*:</label>&nbsp;
							</td>
							<td>
								<form:select id="provEmigrazioneDom" path="provEmigrazioneDom">
									<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
									<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
								</form:select>
								<div><form:errors path="provEmigrazioneDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.via" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="viaEmigrazioneDom" />&nbsp;
								<div><form:errors path="viaEmigrazioneDom" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message	code="label.cap" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="capEmigrazioneDom" maxlength="5"/>&nbsp;
								<div><form:errors path="capEmigrazioneDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.num" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="civicoEmigrazioneDom" size="2" />&nbsp;
								<div><form:errors path="civicoEmigrazioneDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.esp" />:</label>&nbsp;
							</td>
							<td>
								<form:input path="esponenteEmigrazioneDom" size="2" />&nbsp;
								<div><form:errors path="esponenteEmigrazioneDom" cssStyle="color:red"/></div> 
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
		  	<td>
		   		<form:radiobutton id="motivoCessazioneDomConcessione" path="motivoCessazioneDom" value="concessione"/><spring:message code="check.immobile.concessione" />
		  	</td>
		</tr>
		<tr>
		   	<td>
		   		<form:radiobutton id="motivoCessazioneDomRestituito" path="motivoCessazioneDom" value="restituito"/><spring:message code="check.immobile.restituzione" />
			</td>
		</tr>
		<tr>
		   	<td>
		   		<form:radiobutton id="motivoCessazioneDomVuoto" path="motivoCessazioneDom" value="vuoto"/><spring:message code="check.immobile.vuoto" />
			</td>
		</tr>
		<tr>
		  	<td>
		   		<form:radiobutton id="motivoCessazioneDomVenduto" path="motivoCessazioneDom" value="venduto"/><spring:message code="check.immobile.venduto" />
		  	</td>
		</tr>
		<tr>
			<td>
		   		<form:radiobutton id="chk_altro_motivo" path="motivoCessazioneDom" value="altro"/><spring:message code="check.altro" />
				<div id="div_altro_motivo" style="display:none;">
					<form:textarea path="altriMotiviDom" rows="3" cols="100"/>
					<div><form:errors path="altriMotiviDom" cssStyle="color:red"/></div> 
				</div>
			</td>
		</tr>
	</table>
</fieldset>
