<%@ include file="../../common/import.jsp"%>

<%-- Motivazioni --%>
<fieldset id="motivazioniFieldset">
	<legend><spring:message code="legend.motivazioni" /></legend>
	<div><form:errors path="motivoCessazioneNonDom" cssStyle="color:red"/></div> 
	<table class="genericTable">
		<tr>
			<td>
				<form:radiobutton id="chk_cessazione" path="motivoCessazioneNonDom" value="cessazione"/><spring:message code="check.cessazione" />
				<div id="div_cessazione" style="display:none">
					<label><spring:message	code="label.attivitaCessata" />*:</label>&nbsp;
					<form:input path="attivitaCessata" />&nbsp;
					<div><form:errors path="attivitaCessata" cssStyle="color:red"/></div> 
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<form:radiobutton id="chk_nd_duplicazione" path="motivoCessazioneNonDom" value="duplicazione"/><spring:message code="check.duplicazione" />
				<div id="div_nd_duplicazione" style="display:none;">
					<label><spring:message code="label.soggetto" />*:</label>&nbsp;
					<form:input path="coabitanteNonDom" />&nbsp;
					<div><form:errors path="coabitanteNonDom" cssStyle="color:red"/></div> 
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<form:radiobutton id="chk_nd_denominazione" path="motivoCessazioneNonDom" value="cambio_denominazione"/><spring:message code="check.denominazione" />
				<div id="div_nd_denominazione" style="display:none;">
					<table>
						<tr>
							<td>
								<label><spring:message	code="label.da" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="vecchiaDenominazione" />&nbsp;
								<div><form:errors path="vecchiaDenominazione" cssStyle="color:red"/></div> 
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message	code="label.a" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="nuovaDenominazione" />&nbsp;
								<div><form:errors path="nuovaDenominazione" cssStyle="color:red"/></div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<%-- <tr>
			<td>
				<form:radiobutton id="chk_nd_trasferimento" path="motivoCessazioneNonDom" value="trasferimento"/><spring:message code="check.trasferimento" />
				<div id="div_nd_trasferimento" style="display:none;">
					<table>
						<tr>
							<td>
								<label><spring:message	code="label.comune" />*:</label>&nbsp;
							</td>
							<td>
								<form:select id="comuneEmigrazioneNonDom" path="comuneEmigrazioneNonDom">
									<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
									<form:options items="${comuniList}" itemLabel="denominazione" itemValue="codiceIstatAN"  />
								</form:select>
								<div><form:errors path="comuneEmigrazioneNonDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.prov" />*:</label>&nbsp;
							</td>
							<td colspan="3">
								<form:select id="provEmigrazioneNonDom" path="provEmigrazioneNonDom">
									<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
									<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
								</form:select>
								<div><form:errors path="provEmigrazioneNonDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.via" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="viaEmigrazioneNonDom" />&nbsp;
								<div><form:errors path="viaEmigrazioneNonDom" cssStyle="color:red"/></div>
							</td>
						</tr>
						<tr>
							<td>
								<label><spring:message	code="label.cap" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="capEmigrazioneNonDom" />&nbsp;
								<div><form:errors path="capEmigrazioneNonDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.num" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="civicoEmigrazioneNonDom" size="2" />&nbsp;
								<div><form:errors path="civicoEmigrazioneNonDom" cssStyle="color:red"/></div> 
							</td>
							<td>
								<label><spring:message	code="label.esp" />*:</label>&nbsp;
							</td>
							<td>
								<form:input path="esponenteEmigrazioneNonDom" size="2" />&nbsp;
								<div><form:errors path="esponenteEmigrazioneNonDom" cssStyle="color:red"/></div> 
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr> --%>
		<tr>
			<td>
				<form:radiobutton id="chk_nd_altro_motivo" path="motivoCessazioneNonDom" value="altro"/><spring:message code="check.altro" />
				<div id="div_nd_altro_motivo" style="display:none;">
					<form:textarea path="altriMotiviNonDom" rows="3" cols="100"/>
					<div><form:errors path="altriMotiviNonDom" cssStyle="color:red"/></div> 
				</div>
			</td>
		</tr>
		</table>
		<label><spring:message	code="label.specificaImmobile"/>:</label>
		<table class="genericTable">
		<tr>
			<td>
				<form:radiobutton id="specificaRilascioImmobileConcessione" path="specificaRilascioImmobile" value="concessione"/><spring:message code="check.immobile.concessione" />
			</td>
		</tr>
		<tr>
		   <td>
		   		<form:radiobutton id="specificaRilascioImmobileRestituito" path="specificaRilascioImmobile" value="restituito"/><spring:message code="check.immobile.restituzione" />
			</td>
		</tr>
		<tr>
		   	<td>
		   		<form:radiobutton id="specificaRilascioImmobileRestituitoVuoto" path="specificaRilascioImmobile" value="vuoto"/><spring:message code="check.immobile.vuoto" />
			</td>
		</tr>
		<tr>
		  	<td>
		   		<form:radiobutton id="specificaRilascioImmobileRestituitoVuoto" path="specificaRilascioImmobile" value="venduto"/><spring:message code="check.immobile.venduto" />
		  	</td>
		</tr>
		<tr>
			<td>
		   		<form:radiobutton id="chk_altra_specifica" path="specificaRilascioImmobile" value="altro"/><spring:message code="check.altro" />
				<div id="div_altra_specifica" style="display:none;">
					<form:textarea path="altroSpecificaRilascioImmobile" rows="3" cols="100"/>
					<div><form:errors path="altroSpecificaRilascioImmobile" cssStyle="color:red"/></div>
				</div>
			</td>
		</tr>
		<tr>
		  	<td>
		   		<div><form:errors path="specificaRilascioImmobile" cssStyle="color:red"/></div>
		  	</td>
		</tr>
	</table>
</fieldset>
