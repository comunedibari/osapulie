<%@ page contentType="text/html" isELIgnored="false" %>

<%@ include file="../common/common.jsp"%>

<!-- DATI COMPONENTE CHE HA TRASFERITO LA PROPRIA RESIDENZA -->
	<fieldset>
		<legend>
			<spring:message code="label.parente" />
		</legend>
		<div>
			<form:errors path="verificaFamiliari" cssStyle="color:red" />
		</div>
		<div class="selectNumAllegatiDiv" id="numeroAllegati">
			<form:select path="numeroParenti">
				<form:option value="0" label="Nessuno" />
				<c:forEach var="item" begin="1" end="7" varStatus="status">
					<form:option value="${item}" label="${item}" />
				</c:forEach>
			</form:select>
			<input type="submit" name="selectNumParenti" value="Seleziona" />
		</div>
		<br />
		<c:if
			test="${datiDichiarazione.numeroParenti!=null && datiDichiarazione.numeroParenti>0}">
			<c:forEach var="familiare" items="${datiDichiarazione.familiari}" varStatus="status">
				<form:hidden path="familiari[${status.index}].identificativoUtente" />
				<fieldset>
					<legend>Familiare ${status.index +1}</legend>
					<table class="genericTable">
						<tr>
							<td width="25%"><label><spring:message code="label.cognome" />*:</label></td>
							<td width="25%"><form:input
									path="familiari[${status.index}].cognome" id="cognome" />
								<div>
									<form:errors path="familiari[${status.index}].cognome" cssStyle="color:red" />
								</div></td>
							<td width="25%"><label><spring:message code="label.nome" />*:</label></td>
							<td width="25%"><form:input path="familiari[${status.index}].nome" id="nome" />
								<div>
									<form:errors path="familiari[${status.index}].nome" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.parentela" />*:</label>
							</td>
							<td><form:select path="familiari[${status.index}].parentela">
									<form:option value="">
										<spring:message code="label.select.seleziona" />
									</form:option>
									<form:options items="${vocabolarioRelazioniDiParentela}" />
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].parentela" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td><label><spring:message code="label.dataN" />*(dd/mm/yyyy):</label>
							</td>
							<td><form:input path="familiari[${status.index}].dataNascitaString" cssClass="data_input data_input_other" type="text" />
								<div>
									<form:errors path="familiari[${status.index}].dataNascitaString" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.sesso" />*:</label>
							</td>
							<td><form:radiobutton path="familiari[${status.index}].sesso" value="M" />M&nbsp;&nbsp;
								<form:radiobutton path="familiari[${status.index}].sesso" value="F" />F
								<div>
									<form:errors path="familiari[${status.index}].sesso" cssStyle="color:red" />
								</div></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><label><spring:message code="label.statoNascita" /></label></td>
							<td><form:select cssClass="statoEsteroSelect" id="familiari[${status.index}].statoEsteroSelect" path="familiari[${status.index}].statoEsteroNascita" cssStyle="width: 100px;">
									<form:option value="">Italia</form:option>
									<form:option value="998">-- NON PRESENTE --</form:option>
									<form:options items="${listaStatiEsteri}" itemValue="codiceStato" itemLabel="denominazione" />
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].statoEsteroNascita" cssStyle="color:red" />
								</div></td>
							<td class="campiNascitaItalia" style="display: none"><label><spring:message code="label.provN" />*</label></td>
							<td class="campiNascitaItalia" style="display: none"><form:select
									cssClass="provinciaSelect" id="familiari[${status.index}].provinciaNascita" path="familiari[${status.index}].provinciaNascita">
									<form:option value="">
										<spring:message code="label.select.seleziona" />
									</form:option>
									<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla" />
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].provinciaNascita" cssStyle="color:red" />
								</div></td>
							<td class="campiNascitaItalia" style="display: none"><label><spring:message code="label.comN" />*:</label></td>
							<td class="campiNascitaItalia" style="display: none">
								<div class="comuneSelect">
									<form:select id="comuneNascita" path="familiari[${status.index}].comuneNascita">
										<form:option value="">
											<spring:message code="label.select.seleziona" />
										</form:option>
										<c:if test="${not empty familiare.comuneNascita}">
											<form:option selected="selected" value="${familiare.comuneNascita}">${familiare.comuneNascitaHidden}</form:option>
										</c:if>
									</form:select>
									<div class="loader">
										<img
											src="${pageContext.request.contextPath}/images/loader.gif"
											alt="Caricamento..." />
									</div>
									<div class="reset"></div>
								</div>
								<div>
									<form:errors path="familiari[${status.index}].comuneNascita" cssStyle="color:red" />
								</div>
							</td>
							<td class="campiNascitaEstero" style="display: none"><label><spring:message code="label.comN" />*:</label></td>
							<td class="campiNascitaEstero" style="display: none" colspan="2"><form:select cssClass="comuneNascitaEsteroSelect" id="familiari[${status.index}].comuneNascitaEstero" path="familiari[${status.index}].comuneNascitaEstero" cssStyle="width:100%">
									<form:option value="">
										<spring:message code="label.select.seleziona" />
									</form:option>
									<c:if test="${not empty familiare.comuneNascitaEstero}">
										<form:option selected="selected" value="${familiare.comuneNascitaEstero}">${familiare.comuneNascitaEsteroHidden}</form:option>
									</c:if>
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].comuneNascitaEstero" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td><label><spring:message code="label.cf" />*:</label></td>
							<td><form:input path="familiari[${status.index}].codiceFiscale" name="codiceFiscale" id="codiceFiscale" />
								<div>
									<form:errors path="familiari[${status.index}].codiceFiscale" cssStyle="color:red" />
								</div>
								<div>
									<form:errors path="familiari[${status.index}].identificativoUtente" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.statoCivile" />*:</label></td>
							<td><form:select path="familiari[${status.index}].statoCivile">
									<form:option value="">
										<spring:message code="label.select.seleziona" />
									</form:option>
									<form:options items="${vocabolarioStatiCivili}" />
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].statoCivile" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.cittadinanza" />*:</label></td>
							<td><form:select path="familiari[${status.index}].cittadinanza" cssStyle="width: 100px;">
									<form:option value="">
										<spring:message code="label.select.seleziona" />
									</form:option>
									<form:option value="100">Italia</form:option>
									<form:option value="998">-- NON PRESENTE --</form:option>
									<form:options items="${listaStatiEsteri}" itemValue="codiceStato" itemLabel="denominazione" />
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].cittadinanza" cssStyle="color:red" />
								</div></td>
						</tr>
					</table>
					<!-- DATI LAVORO E ISTRUZIONE -->
					<table class="genericTable">
						<tr>
							<td><label><spring:message code="label.professione" />:</label></td>
							<td>
								<ul>
									<form:radiobuttons path="familiari[${status.index}].professione" items="${vocabolarioPosizioniProfessionali}" element="li" />
								</ul>
							</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.condNonProfessionale" />:</label></td>
							<td>
								<ul>
									<form:radiobuttons path="familiari[${status.index}].condNonProfessionale" items="${vocabolarioCondizioniNonProfessionali}" element="li" />
								</ul>
							</td>
						</tr>
						<tr>
							<td><label><spring:message code="label.titoloStudio" />:</label></td>
							<td>
								<ul>
									<form:radiobuttons path="familiari[${status.index}].titoloStudio" items="${vocabolarioTitoliDiStudio}" element="li" />
								</ul>
							</td>
						</tr>
					</table>
					<!-- Dati anagrafici avanzati -->
					<table class="genericTable">
						<tr>
							<td><label><spring:message code="label.tipoPatente" />:</label></td>
							<td colspan="3"><form:select id="familiari[${status.index}].tipoPatente" path="familiari[${status.index}].tipoPatente">
									<form:option value=""></form:option>
									<form:options items="${vocabolarioTipiPatente}" />
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].tipoPatente" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td width="25%"><label><spring:message code="label.numPatente" />:</label></td>
							<td width="25%"><form:input path="familiari[${status.index}].numPatente" />
								<div>
									<form:errors path="familiari[${status.index}].numPatente" cssStyle="color:red" />
								</div></td>
							<td width="25%"><label><spring:message code="label.dataRilascioPatente" />:</label></td>
							<td width="25%"><form:input path="familiari[${status.index}].dataRilascioPatente" cssClass="data_input" type="text" />
								<div>
									<form:errors path="familiari[${status.index}].dataRilascioPatente" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td><label><spring:message code="label.organoRilascioPatente" />:</label></td>
							<td><form:select path="familiari[${status.index}].organoRilascioPatente">
									<form:option value="">
										<spring:message code="label.select.seleziona" />
									</form:option>
									<form:options items="${vocabolarioEntiRilascioPatente}" />
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].organoRilascioPatente" cssStyle="color:red" />
								</div></td>
							<td><label><spring:message code="label.provPatente" />:</label></td>
							<td><form:select id="familiari[${status.index}].provPatente" path="familiari[${status.index}].provPatente">
									<form:option value="">
										<spring:message code="label.select.seleziona" />
									</form:option>
									<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla" />
									<form:option value="-">EX-TERRITORI-ITALIANI</form:option>
									<form:option value="I">PROVINCIA SCONOSCIUTA</form:option>
									<form:option value="U1">DUPLICATO PATENTE U.C.O.</form:option>
								</form:select>
								<div>
									<form:errors path="familiari[${status.index}].provPatente" cssStyle="color:red" />
								</div></td>
						</tr>
						<tr>
							<td colspan="4"><label><spring:message code="label.titoloTarghe" /></label></td>
						</tr>
						<c:forEach var="i" begin="0" end="${numeroMaxVeicoliForm - 1}" varStatus="status2">
							<tr>
								<td><label><spring:message code="label.tipoVeicolo" />:</label></td>
								<td><form:select id="familiari[${status.index}].veicoli[${status2.index}].tipo" path="familiari[${status.index}].veicoli[${status2.index}].tipo">
										<form:option value=""></form:option>
										<form:options items="${vocabolarioTipiVeicolo}" />
									</form:select>
									<div>
										<form:errors path="familiari[${status.index}].veicoli[${status2.index}].tipo" cssStyle="color:red" />
									</div></td>
								<td><label><spring:message code="label.targaVeicolo" />:</label></td>
								<td><form:input path="familiari[${status.index}].veicoli[${status2.index}].targa" />
									<div>
										<form:errors path="familiari[${status.index}].veicoli[${status2.index}].targa" cssStyle="color:red" />
									</div></td>
							</tr>
						</c:forEach>
					</table>
				</fieldset>
			</c:forEach>
		</c:if>
		<!-- form d'inserimento dati del residente gia iscritto -->
		<legend>
			<spring:message code="label.legendIscritto" />
		</legend>
		<form:checkbox id="flagIscritto" path="flagIscritto" />&nbsp;
		<spring:message code="label.flagIscritto" />
		<br />
		<div id="inputIscritto">
			<table class="genericTable">
				<tr><td width="25%"><label><spring:message code="label.cognome" />*:</label></td>
					<td width="25%"><form:input path="iscrittoCognome" />
						<div><form:errors path="iscrittoCognome" cssStyle="color:red" />
						</div></td>
					<td width="25%"><label><spring:message code="label.nome" />*:</label></td>
					<td width="25%"><form:input path="iscrittoNome" />
						<div><form:errors path="iscrittoNome" cssStyle="color:red" />
						</div></td><td width="25%"><label><spring:message code="label.cf" />:</label></td><td width="25%"><form:input path="iscrittoCodiceFiscale" />
						<div>
							<form:errors path="iscrittoCodiceFiscale" cssStyle="color:red" />
						</div></td>
				</tr><tr>
					<td><label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
					</td><td><form:input path="iscrittoDataNascita" id="iscrittoDataNascitaDP" type="text" cssClass="data_input" />
						<div>
							<form:errors path="iscrittoDataNascita" cssStyle="color:red" />
						</div></td>
					<td><label><spring:message code="label.comN" />*:</label></td>
					<td colspan="2"><form:select id="iscrittoLuogoNascita" path="iscrittoLuogoNascita">
							<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
							<form:options items="${comuniList}" itemLabel="denominazione" itemValue="codiceIstatAN" /></form:select>
						<div>
							<form:errors path="iscrittoLuogoNascita" cssStyle="color:red" />
						</div></td>
				</tr>
			</table>
			<table class="genericTable">
				<tr>
					<td><form:radiobutton id="chkIscritto" path="iscrittoParentela" value="false" />&nbsp;<spring:message code="label.iscritto.parentelaNo" /><br /></td>
					<td><form:radiobutton id="chkIscritto" path="iscrittoParentela" value="true" />&nbsp;<spring:message code="label.iscritto.parentelaSi" /><br />
						<div id="iscrittoParentela">
							<form:select path="iscrittoTipoParentela">
								<form:option value="">
									<spring:message code="label.select.seleziona" />
								</form:option>
								<form:options items="${vocabolarioRelazioniDiParentela}" />
							</form:select>
							<form:errors path="iscrittoTipoParentela" cssStyle="color:red" />
						</div></td>
				</tr>
			</table>
		</div>
	</fieldset>
