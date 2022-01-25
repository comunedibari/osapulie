<fieldset>
	<legend>
		<spring:message code="label.legendDatiAnagrafici" />
	</legend>
	<table class="genericTable">
		<tr>
			<td>
				<label>
					<spring:message code="label.codContribuente" />*:
				</label> 
			</td>
			<td>
				<form:input path="idContribuente" />
				<div><form:errors path="idContribuente" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.sesso" />*:</label>
			</td>
			<td colspan="3">
				<div>
					<form:radiobutton path="sesso" value="M" /><label>M</label>
					<form:radiobutton path="sesso" value="F" /><label>F</label>
				</div>
				<div><form:errors path="sesso" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.cognome" />*:</label>
			</td>
			<td>
				<form:input path="cognome" />
				<div><form:errors path="cognome" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message	code="label.nome" />*:</label>
			</td>
			<td>
				<form:input path="nome" />
				<div><form:errors path="nome" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
			</td>
			<td>
				<form:input path="dataNascita" size="10" id="data_nascita_input" cssClass="data_input"/>
				<div><form:errors path="dataNascita" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.statoEstero" /></label>
			</td>
			<td>
				<form:select id="statoEsteroSelect" path="statoEstero" cssStyle="width: 100px;">
					<form:option value="">Italia</form:option>
					<form:option value="998">-- NON PRESENTE --</form:option>
					<form:options items="${listaStatiEsteri}" itemValue="codiceStato" itemLabel="denominazione"/>
				</form:select>
				<div><form:errors path="statoEstero" cssStyle="color:red"/></div>
			</td>
			<td class="campiNascitaItalia" style="display:none">
				<label><spring:message code="label.provN" />*</label>
			</td>
			<td class="campiNascitaItalia" style="display:none">
				<form:select cssClass="provinciaSelect" id="provinciaNascita" path="provinciaNascita">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
					<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
				</form:select>
				<div><form:errors path="provinciaNascita" cssStyle="color:red"/></div>
			</td>
			<td class="campiNascitaItalia" style="display:none">
				<label><spring:message code="label.comN" />*:</label>
			</td>
			<td class="campiNascitaItalia" style="display:none">
				<div class="comuneSelect">
					<form:select id="comuneNascita" path="comuneNascita">
						<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
						<c:if test="${not empty datiAgevolazioneTari.comuneNascita}">
							<form:option selected="selected" value="${datiAgevolazioneTari.comuneNascita}">${datiAgevolazioneTari.comuneNascitaHidden}</form:option>
						</c:if>
					</form:select>
					<div class="loader">
					    <img src="${pageContext.request.contextPath}/images/loader.gif" alt="Caricamento..."/>
					</div>
					<div class="reset"></div>
				</div>
				<div><form:errors path="comuneNascita" cssStyle="color:red"/></div>
			</td>
			<td class="campiNascitaEstero" style="display:none">
				<label><spring:message code="label.comN" />*:</label>
			</td>
			<td class="campiNascitaEstero" style="display:none" colspan="2">
				<form:select id="comuneNascitaEstero" path="codiceNascitaEstero" cssStyle="width:100%">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
					<c:if test="${not empty datiAgevolazioneTari.codiceNascitaEstero}"><form:option selected="selected" value="${datiAgevolazioneTari.codiceNascitaEstero}">${datiAgevolazioneTari.comuneNascitaEstero}</form:option></c:if>
				</form:select>
				<div><form:errors path="codiceNascitaEstero" cssStyle="color:red"/></div>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="label.cf" />*:</label>
			</td>
			<td>
				<form:input path="codiceFiscale" />
				<div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message code="label.provResidenza" />*:</label>
			</td>
			<td>
				<form:select cssClass="provinciaSelect" id="provinciaResidenza" path="provinciaResidenza">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
					<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
				</form:select>
				<div><form:errors path="provinciaResidenza" cssStyle="color:red"/></div>
			</td>
			<td>
				<label><spring:message code="label.comuneIscrizione" />*:</label>
			</td>
			<td>
				<div class="comuneSelect">
					<form:select id="comuneResidenza" path="comuneResidenza">
						<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
						<c:if test="${not empty datiAgevolazioneTari.comuneResidenza}">
							<form:option selected="selected" value="${datiAgevolazioneTari.comuneResidenza}">${datiAgevolazioneTari.comuneResidenzaHidden}</form:option>
						</c:if>
					</form:select>
					<div class="loader">
					    <img src="${pageContext.request.contextPath}/images/loader.gif" alt="Caricamento..."/>
					</div>
					<div class="reset"></div>
				</div>
				<div><form:errors path="comuneResidenza" cssStyle="color:red"/></div>
			</td>
		</tr>
	<c:choose>
	<c:when test="${datiAgevolazioneTari.indirizzoResidenzaConStradario}">
		<tr id="indirizzoResidenzaConStradario">
			<td width="180">
				<label><spring:message code="label.indir" />*:</label>
			</td>
			<td width="180" colspan="3">
				<osapulie:stradario id="indirizzoResidenza" 
					viaName="indirizzoResidenza" 
					civicoName="civicoResidenza"
					esponenteName="esponenteResidenza" 
					viaOptionValue="${datiAgevolazioneTari.indirizzoResidenza}" 
					civicoOptionValue="${datiAgevolazioneTari.civicoResidenza}" 
					esponenteValue="${datiAgevolazioneTari.esponenteResidenza}" 
					viaOptionText="${datiAgevolazioneTari.indirizzoResidenzaTextHidden}"
					civicoOptionText="${datiAgevolazioneTari.civicoResidenzaTextHidden}" 
					viaTextHiddenName="indirizzoResidenzaTextHidden"
					civicoTextHiddenName="civicoResidenzaTextHidden"
					loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
				<div><form:errors path="indirizzoResidenza" cssStyle="color:red"/></div> 
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr id="indirizzoResidenzaSenzaStradario">
			<td width="180">
				<label><spring:message code="label.indir" />*:</label>
			</td>
			<td width="180">
				<form:input path="indirizzoResidenza" />
				<div><form:errors path="indirizzoResidenza" cssStyle="color:red"/></div> 
			</td>
			<td width="180">
				<label><spring:message code="label.numeroCivico" />*:</label>
			</td>
			<td width="180">
				<form:input path="civicoResidenza" />
				<div><form:errors path="civicoResidenza" cssStyle="color:red"/></div> 
			</td>
			<td width="180">
				<label><spring:message code="label.esp" />:</label>
			</td>
			<td width="180">
				<form:input path="esponenteResidenza" />
				<div><form:errors path="esponenteResidenza" cssStyle="color:red"/></div> 
			</td>
		</tr>
	</c:otherwise>
	</c:choose>
		<tr>
			<td width="180">
				<label><spring:message	code="label.scala" />:</label>
			</td>
			<td width="180">
				<form:input path="scalaResidenza"/>
				<div><form:errors path="scalaResidenza" cssStyle="color:red"/></div> 
			</td>
			<td width="180">
				<label><spring:message code="label.piano" />:</label>
			</td>
			<td width="180">
				<form:input path="pianoResidenza"/>
				<div><form:errors path="pianoResidenza" cssStyle="color:red"/></div> 
			</td>
			<td width="180">
				<label><spring:message code="label.interno" />:</label>
			</td>
			<td width="180">
				<form:input path="internoResidenza"/>
				<div><form:errors path="internoResidenza" cssStyle="color:red"/></div> 
			</td>
		</tr>
		<tr>
			<td width="180">
				<label><spring:message code="label.telefono" />*:</label>
			</td>
			<td width="180">
				<form:input path="telefono" />
				<div><form:errors path="telefono" cssStyle="color:red"/></div> 
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
</fieldset>