<table class="genericTable">
	<tr id="infoNuovaIscrizione">
		<td colspan="6">
			<label>
				<spring:message code="label.infoNuovaIscrizione" />
			</label> 
		</td>
	</tr>
	<tr>
		<td width="180">
			<label>
				<spring:message code="label.tipoUtenza" />*:
			</label> 
		</td>
		<td colspan="5">
			<form:select id="tipoUtenzaSelect" path="tipoUtenza">
			   <form:option value="domestica" label="Domestica"/>
			   <form:option value="non_domestica" label="Non domestica"/>
			</form:select>
			<div><form:errors path="tipoUtenza" cssStyle="color:red"/></div>
		</td>
	</tr>
	<tr>
	 	<td width="180">
			<label>
				<spring:message code="label.tipoDichiarazione" />:
			</label> 
		</td>
		<td colspan="5">
			<div>
				<form:select id="select_tipo_dichiarazione" path="tipoDichiarazione">
				   <form:option value="iscrizione" label="Iscrizione"/>
				   <form:option value="variazione" label="Variazione"/>
				   <form:option value="cessazione" label="Cessazione"/>
				   <form:option value="trasferimento" label="Trasferimento"/>
				</form:select>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<label><spring:message code="label.aDecorrereDal" />*:</label>
		</td>
		<td colspan="5">
			<form:input path="aDecorrereDa" id="data_adecorrereda_input" size="10" cssClass="data_input"/>
			<div><form:errors path="aDecorrereDa" cssStyle="color:red"/></div>
		</td>
	</tr>
	<tr>
		<td>
			<label>
				<spring:message code="label.tipoContribuente" />*:
			</label> 
		</td>
		<td>
			<form:radiobutton cssClass="tipoPersonaRadio" name="tipoPersona" path="tipoPersona" value="fisica"/><spring:message code="label.tipoContribuenteFisico" />
			<form:radiobutton cssClass="tipoPersonaRadio" name="tipoPersona" path="tipoPersona" value="giuridica"/><spring:message code="label.tipoContribuenteGiuridico" />
			<div><form:errors path="tipoPersona" cssStyle="color:red"/></div>
		</td>
		<td>
			<label><spring:message	code="label.sesso" />*:</label>
		</td>
		<td colspan="3">
			<div>
				<form:radiobutton path="sesso" value="M" />M
				<form:radiobutton path="sesso" value="F" />F
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
					<c:if test="${not empty datiTari.comuneNascita}">
						<form:option selected="selected" value="${datiTari.comuneNascita}">${datiTari.comuneNascitaHidden}</form:option>
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
				<c:if test="${not empty datiTari.codiceNascitaEstero}"><form:option selected="selected" value="${datiTari.codiceNascitaEstero}">${datiTari.comuneNascitaEstero}</form:option></c:if>
			</form:select>
			<div><form:errors path="codiceNascitaEstero" cssStyle="color:red"/></div>
		</td>
	</tr>
	<tr>
		<td>
			<label><spring:message code="label.cf" />*:</label>
		</td>
		<td>
			<label>${datiTari.codiceFiscale}</label>
			<div><form:errors path="codiceFiscale" cssStyle="color:red"/></div>
		</td>
		<td>
			<label><spring:message code="label.provResidenza" />*:</label>
		</td>
		<td>
			<form:select cssClass="provinciaSelect" id="provinciaResidenza" path="provResidenza">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
				<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
			</form:select>
			<div><form:errors path="provResidenza" cssStyle="color:red"/></div>
		</td>
		<td>
			<label><spring:message code="label.comuneIscrizione" />*:</label>
		</td>
		<td>
			<div class="comuneSelect">
				<form:select id="comuneResidenza" path="comuneResidenza">
					<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
					<c:if test="${not empty datiTari.comuneResidenza}">
						<form:option selected="selected" value="${datiTari.comuneResidenza}">${datiTari.comuneResidenzaHidden}</form:option>
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
		<c:when test="${datiTari.indirizzoResidenzaConStradario}">
			<tr id="indirizzoResidenzaConStradario">
				<td width="180">
					<label><spring:message code="label.indir" />*:</label>
				</td>
				<td width="180" colspan="3">
					<osapulie:stradario id="indirizzoResidenza" 
						viaName="indirizzoResidenza" 
						civicoName="civicoResidenza"
						esponenteName="esponenteResidenza" 
						viaOptionValue="${datiTari.indirizzoResidenza}" 
						civicoOptionValue="${datiTari.civicoResidenza}" 
						esponenteValue="${datiTari.esponenteResidenza}" 
						viaOptionText="${datiTari.indirizzoResidenzaTextHidden}"
						civicoOptionText="${datiTari.civicoResidenzaTextHidden}" 
						viaTextHiddenName="indirizzoResidenzaTextHidden"
						civicoTextHiddenName="civicoResidenzaTextHidden"
						loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
					<div><form:errors path="indirizzoResidenza" cssStyle="color:red"/></div> 
					<div><form:errors path="esponenteResidenza" cssStyle="color:red"/></div> 
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
			<label><spring:message	code="label.cap" />*:</label>
		</td>
		<td width="180">
			<form:input path="capResidenza" maxlength="5"/>
			<div><form:errors path="capResidenza" cssStyle="color:red"/></div> 
		</td>
		<td width="180">
		</td>
		<td width="180">
		</td>
		<td>
		</td>
		<td>
		</td>		
	</tr>
	<tr class="requiredNoDom" style="display:none">
		<td width="180">
			<label><spring:message	code="label.inQualitaDi" />*:</label>
		</td>
		<td width="180">
			<form:input path="ruolo" />
			<div><form:errors path="ruolo" cssStyle="color:red"/></div> 
		</td>
		<td width="180">
			<label><spring:message	code="label.societa" />*:</label>
		</td>
		<td width="180">
			<form:input path="nomeSocieta" />
			<div><form:errors path="nomeSocieta" cssStyle="color:red"/></div> 
		</td>
		<td colspan="2"></td>
	</tr>
	<tr class="requiredNoDom piva" style="display:none">
		<td width="180">
			<label><spring:message	code="label.partitaIva" /><span class="piva">*</span>:</label>
		</td>
		<td width="180">
			<form:input path="partitaIva" />
			<div><form:errors path="partitaIva" cssStyle="color:red"/></div> 
		</td>	
		<td colspan="4"></td>	
	</tr>
	<tr class="requiredNoDom" style="display:none">
		<td width="180">
			<label><spring:message	code="label.comuneSedeLegale" />*:</label>
		</td>
		<td width="180">
			<form:select id="comuneSedeLegale" path="comuneSedeLegale">
				<form:option value=""></form:option>
				<form:options items="${comuniList}" itemLabel="denominazione" itemValue="codiceIstatAN"  />
			</form:select>
			<div><form:errors path="comuneSedeLegale" cssStyle="color:red"/></div> 
		</td>
		<td width="180">
			<label><spring:message	code="label.viaSedeLegale" />*:</label>
		</td>
		<td width="180">
			<form:input path="viaSedeLegale" />
			<div><form:errors path="viaSedeLegale" cssStyle="color:red"/></div> 
		</td>
		<td>
			<label><spring:message	code="label.numeroSedeLegale" />*:</label>
		</td>
		<td>
			<form:input path="numeroSedeLegale" />
			<div><form:errors path="numeroSedeLegale" cssStyle="color:red"/></div> 
		</td>
	</tr>
	<tr class="requiredNoDom" style="display:none">
		<td width="180">
			<label><spring:message	code="label.espSedeLegale" />:</label>
		</td>
		<td width="180">
			<form:input path="espSedeLegale" /> <div><form:errors path="espSedeLegale" cssStyle="color:red"/></div> 
		</td>
		<td width="180">
			<label><spring:message	code="label.scSedeLegale" />:</label>
		</td>
		<td width="180">
			<form:input path="scSedeLegale" />
			<div><form:errors path="scSedeLegale" cssStyle="color:red"/></div> 
		</td>
		<td>
			<label><spring:message	code="label.pSedeLegale" />:</label>
		</td>
		<td>
			<form:input path="pSedeLegale" />
			<div><form:errors path="pSedeLegale" cssStyle="color:red"/></div> 
		</td>
	</tr>
	<tr class="requiredNoDom" style="display:none">
		<td width="180">
			<label><spring:message	code="label.intSedeLegale" />:</label>
		</td>
		<td width="180">
			<form:input path="intSedeLegale" />
			<div><form:errors path="intSedeLegale" cssStyle="color:red"/></div> 
		</td>
		<td width="180">
			<label><spring:message	code="label.cap" />*:</label>
		</td>
		<td width="180">
			<form:input path="capSedeLegale" maxlength="5"/>
			<div><form:errors path="capSedeLegale" cssStyle="color:red"/></div> 
		</td>
		<td>
			<label><spring:message code="label.pec" />*:</label>
		</td>
		<td>
			<form:input path="pec" />
			<div><form:errors path="pec" cssStyle="color:red"/></div> 
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
		<td width="180">
			<label><spring:message code="label.fax" />:</label>
		</td>
		<td width="180">
			<form:input path="fax" />
			<div><form:errors path="fax" cssStyle="color:red"/></div>
		</td>
		<td></td>
		<td></td>
	</tr>
	<tr class="requiredNoDom rea" style="display:none">
		<td width="180">
			<div>
				<label><spring:message code="label.num.rea" />*:</label>
			</div>	
		</td>
		<td width="180">
			<div>
				<form:input path="numRea" />
				<div><form:errors path="numRea" cssStyle="color:red"/></div> 
			</div>	
		</td>
		<td width="180">
			<div>
				<label><spring:message code="label.provincia.rea" />*:</label>
			</div>	
		</td>
		<td width="180">
			<form:select id="provinciaRea" path="provinciaRea">
				<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
				<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
			</form:select>	
			<div><form:errors path="provinciaRea" cssStyle="color:red"/></div>
		</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="180">
		 <div>
			<label><spring:message code="label.estremi.documento" />*:</label>
		 </div>	
		</td>
		<td width="180">
	 	<div>
			<form:input path="estremiDocumento" />
			<div><form:errors path="estremiDocumento" cssStyle="color:red"/></div> 
		 </div>	
		</td>
		<td colspan="4"></td>
	</tr>
</table>
<div>
	<label><spring:message	code="label.invio" />:</label>
	<table class="genericTable">
		<tr>
			<td colspan="8">
				<form:select id="select_invio" path="modalitaInvio">
				   <form:option value="residenza" label="Residenza e/o sede legale"/>
				   <form:option value="altro" label="Altro"/>
				</form:select>
			 	<div><form:errors path="modalitaInvio" cssStyle="color:red"/></div> 
			</td>
		</tr>
		
		<c:choose>
			<c:when test="${datiTari.indirizzoSpedizioneConStradario}">
				<tr id="indirizzoResidenzaConStradario">
					<td>
						<label><spring:message	code="label.prov" />*:</label>&nbsp;
					</td>
					<td>
						<form:select id="provinciaSpedizione" path="provinciaSpedizione" cssClass="provinciaSelect">
							<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
							<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
						</form:select>	
						<div><form:errors path="provinciaSpedizione" cssStyle="color:red"/></div>
					</td>
					<td>
						<label><spring:message	code="label.comune" />*:</label>&nbsp;
					</td>
					<td>
						<div class="comuneSelect">
							<form:select id="comuneSpedizione" path="comuneSpedizione">
								<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
								<c:if test="${not empty datiTari.comuneSpedizione}">
									<form:option selected="selected" value="${datiTari.comuneSpedizione}">${datiTari.comuneSpedizioneHidden}</form:option>
								</c:if>
							</form:select>
							<div class="loader">
							    <img src="${pageContext.request.contextPath}/images/loader.gif" alt="Caricamento..."/>
							</div>
							<div class="reset"></div>
							<div><form:errors path="comuneSpedizione" cssStyle="color:red"/></div>
						</div>
					</td>
					<td width="180">
						<label><spring:message code="label.indir" />*:</label>
					</td>
					<td width="180" colspan="3">
						<osapulie:stradario id="indirizzoSpedizione" 
							viaName="indirizzoSpedizione" 
							civicoName="civicoSpedizione"
							esponenteName="espSpedizione" 
							viaOptionValue="${datiTari.indirizzoSpedizione}" 
							civicoOptionValue="${datiTari.civicoSpedizione}" 
							esponenteValue="${datiTari.espSpedizione}" 
							viaOptionText="${datiTari.indirizzoSpedizioneTextHidden}"
							civicoOptionText="${datiTari.civicoSpedizioneTextHidden}" 
							viaTextHiddenName="indirizzoSpedizioneTextHidden"
							civicoTextHiddenName="civicoSpedizioneTextHidden"
							loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
						<div><form:errors path="indirizzoSpedizione" cssStyle="color:red"/></div> 
						<div><form:errors path="espSpedizione" cssStyle="color:red"/></div> 
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr id="div_indirizzo_spedizione">	
					<td>
						<label><spring:message	code="label.prov" />*:</label>&nbsp;
					</td>
					<td>
						<form:select id="provinciaSpedizione" path="provinciaSpedizione" cssClass="provinciaSelect">
							<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
							<form:options items="${provinceList}" itemLabel="sigla" itemValue="sigla"  />
						</form:select>	
						<div><form:errors path="provinciaSpedizione" cssStyle="color:red"/></div>
					</td>
					<td>
						<label><spring:message	code="label.comune" />*:</label>&nbsp;
					</td>
					<td>
						<div class="comuneSelect">
							<form:select id="comuneSpedizione" path="comuneSpedizione">
								<form:option value=""><spring:message code="label.select.seleziona" /></form:option>
								<c:if test="${not empty datiTari.comuneSpedizione}">
									<form:option selected="selected" value="${datiTari.comuneSpedizione}">${datiTari.comuneSpedizioneHidden}</form:option>
								</c:if>
							</form:select>
							<div class="loader">
							    <img src="${pageContext.request.contextPath}/images/loader.gif" alt="Caricamento..."/>
							</div>
							<div class="reset"></div>
							<div><form:errors path="comuneSpedizione" cssStyle="color:red"/></div>
						</div>
					</td>
					<td>
						<div>
							<label><spring:message	code="label.via" />*:</label>&nbsp;
							<form:input path="indirizzoSpedizione" />&nbsp;
							<div><form:errors path="indirizzoSpedizione" cssStyle="color:red"/></div>
						</div>
					</td>
					<td>
						<label><spring:message	code="label.num" />*:</label>&nbsp;
						<form:input path="civicoSpedizione" size="2" />&nbsp;
						<div><form:errors path="civicoSpedizione" cssStyle="color:red"/></div>
					</td>
					<td>
						<label><spring:message	code="label.esp" />:</label>&nbsp;
						<form:input path="espSpedizione" size="2" />&nbsp;
					</td>
					<td>
						<label><spring:message	code="label.cap" />*:</label>&nbsp;
						<form:input path="capSpedizione" maxlength="5"/>&nbsp;
						<div><form:errors path="capSpedizione" cssStyle="color:red"/></div>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>