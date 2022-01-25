<%@ include file="../common/common.jsp"%>

<%@ taglib uri="http://tags.osapulie.it" prefix="osapulie"%>

<head>
<style>
td.hide {
	display: none;
}
.overlay{
    position:fixed;
    top:0px;
    bottom:0px;
    left:0px;
    right:0px;
    z-index:100;
	cursor:pointer;
/*Trasperenza cross browser*/
opacity: .7; filter: alpha(opacity=70);
-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=70)";
}
div#load{
position: fixed;
    z-index: 2;
    top: 10px;
    left: 100px;
	right: 100px;
    width: 10px;
    height: 10px;
}
div#wait{
margin-left: 500px;
    margin-top: 250px;
}
</style>

</head>
<portlet:resourceURL var="searchListaComuniEsteriURL" id="searchListaComuniEsteri" escapeXml="false">
</portlet:resourceURL>
<portlet:resourceURL var="searchListaComuniURL" id="searchListaComuni" escapeXml="false">
</portlet:resourceURL>


<script type="text/javascript">

function setStatoEsteroSelect(select) {
if ($(select).val() == '') {
$(select).parent().parent().find('.campiNascitaEstero').hide();
$(select).parent().parent().find('.campiNascitaItalia').show();
} else {
$(select).parent().parent().find('.campiNascitaEstero').show();
$(select).parent().parent().find('.campiNascitaItalia').hide();
}
}
/**
* Carica la lista comuni data la provincia
*/
function setComuneSelect(select) {
var dataTosend = {
"query" : $(select).val()
};
var divComuneSelect = $(select).parent().parent().find('.comuneSelect');
var comuneSelect = $(divComuneSelect).find('select');
var loader = $(divComuneSelect).find(".loader");
$(loader).show();
$.ajax(
	{
		url : "${searchListaComuniURL}",
		cache : false,
		dataType : 'json',
		data : dataTosend,
		async : true,
		success : function(data) {
			$(comuneSelect).find("option").remove();
			$(comuneSelect).append(
					$("<option></option>").attr("value", "").text(
							" -- Seleziona -- "));
			$(comuneSelect).append(
					$("<option></option>").attr("value", "199999")
							.text(" -- IGNOTO -- "));
			$.each(data, function(i, comune) {
				$(comuneSelect).append(
						$("<option></option>").attr("value",
								comune.codice).text(
								comune.denominazione));
			});

		},
		error : function(jqXHR, exception) {
			console.log("ERRORE: " + exception);
		}
	}).always(function() {
$(loader).hide();
});

}

</script>

<portlet:actionURL var="dichiarazioneUrlGenera">
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL"
	id="dichiarazioneCambioResidenzaReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />

<div class="mainDiv dichiarazioneCambioResidenza">


	<form:form id="${idForm}" action="${dichiarazioneUrlGenera}"
		method="post" commandName="datiDichiarazione"
		enctype="multipart/form-data">
		<form:hidden path="hiddenLoadFileClick" />
		<form:hidden path="hiddenLoadFileIntestatarioEdiResPub" />
		<form:hidden path="hiddenLoadFileUsufruttuario" />
		<form:hidden path="hiddenLoadFileAssensoProprietario" />
		<form:hidden path="hiddenLoadFileAmpliamentoNucleoFamiliare" />
		<form:hidden path="hiddenLoadFileIntra" />
		<form:hidden path="hiddenLoadFileExtra" />
		<c:if test="${empty download}">
			<fieldset>
				<legend id="tipoDichiarazione">
					<spring:message code="label.tipoDichiarazione" />
				</legend>
				<form:hidden path="uuidOperazione" />
				<form:hidden path="ipAddress" />
				<table class="genericTable">
					<tr>
						<td id="domanda"><spring:message
								code="label.personeInteressate" /> <br /> <form:radiobutton
								id="ris1" name="risposta" path="personeInteressate" value="0" autocomplete="off" />&nbsp;<spring:message
								code="label.personeInteressate.risposta1" /> <br /> <form:radiobutton
								id="ris2" name="risposta" path="personeInteressate" value="1" autocomplete="off" />&nbsp;<spring:message
								code="label.personeInteressate.risposta2" /> <br /></td>


						<!-- tipo di dichiarazione da naascondere td class="hide" -->
						<td class="hide"><form:radiobutton id="tipoDich"
								path="tipoDichiarazione" value="altroComune" />&nbsp;<spring:message
								code="label.tipoDichiarazione.altroComune" /><br /> <form:radiobutton
								id="tipoDich" path="tipoDichiarazione" value="estero" />&nbsp;<spring:message
								code="label.tipoDichiarazione.estero" /><br /> <form:radiobutton
								id="tipoDich" path="tipoDichiarazione" value="aire" />&nbsp;<spring:message
								code="label.tipoDichiarazione.aire" /><br /> <c:choose>
								<c:when test="${datiDichiarazione.abilitaCambioAbitazione}">
									<form:radiobutton id="tipoDich" path="tipoDichiarazione"
										value="stessoComune" />&nbsp;<spring:message
										code="label.tipoDichiarazione.stessoComune" />
									<br />
								</c:when>
								<c:otherwise>
									<form:radiobutton id="tipoDich" path="tipoDichiarazione"
										value="" disabled="true" />&nbsp;<spring:message
										code="label.tipoDichiarazione.stessoComune" />
									<br />
								</c:otherwise>
							</c:choose> <form:radiobutton id="tipoDich" path="tipoDichiarazione"
								value="altro" />&nbsp;<spring:message
								code="label.tipoDichiarazione.altro" />
							<div id="altroMotivoInput" style="display: none;">
								<spring:message code="label.tipoDichiarazione.altroMotivo" />
								<form:radiobuttons path="altroMotivoDichiarazione"
									items="${vocabolarioAltriMotiviIscrizione}" />
								<form:errors path="altroMotivoDichiarazione"
									cssStyle="color:red" />
							</td>
								<div>
									<form:errors path="tipoDichiarazione" cssStyle="color:red" />
								</div>
					</tr>
				</table>
	</fieldset>
<div id="modulo" style="display: none;">
	<fieldset>
		<legend>
			<spring:message code="label.legendRichiedente" />
		</legend>
		<table class="genericTable">
			<tr>
				<td><label><spring:message code="label.cognome" />*:</label></td>
				<td><form:input path="cognome" />
					<div>
						<form:errors path="cognome" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message code="label.nome" />*:</label></td>
				<td><form:input path="nome" />
					<div>
						<form:errors path="nome" cssStyle="color:red" />
					</div></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label><spring:message code="label.dataN" />&nbsp;(dd/mm/yyyy)*:</label>
				</td>
				<td><form:input path="dataNascita" id="dataNascitaDP" size="10"
						cssClass="data_input" />
					<div>
						<form:errors path="dataNascita" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message code="label.sesso" />*:</label></td>
				<td><form:radiobutton path="sesso" value="M" />M <form:radiobutton
						path="sesso" value="F" />F
					<div>
						<form:errors path="sesso" cssStyle="color:red" />
					</div></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label><spring:message code="label.statoNascita" /></label>
				</td>
				<td><form:select cssClass="statoEsteroSelect"
						id="statoEsteroSelect" path="statoEsteroNascita"
						cssStyle="width: 100px;">
						<form:option value="">Italia</form:option>
						<form:option value="998">-- NON PRESENTE --</form:option>
						<form:options items="${listaStatiEsteri}" itemValue="codiceStato"
							itemLabel="denominazione" />
					</form:select>
					<div>
						<form:errors path="statoEsteroNascita" cssStyle="color:red" />
					</div></td>
				<td class="campiNascitaItalia" style="display: none"><label><spring:message
							code="label.provN" />*</label></td>
				<td class="campiNascitaItalia" style="display: none"><form:select
						cssClass="provinciaSelect" id="provinciaNascita"
						path="provinciaNascita">
						<form:option value="">
							<spring:message code="label.select.seleziona" />
						</form:option>
						<form:options items="${provinceList}" itemLabel="sigla"
							itemValue="sigla" />
					</form:select>
					<div>
						<form:errors path="provinciaNascita" cssStyle="color:red" />
					</div></td>
				<td class="campiNascitaItalia" style="display: none"><label><spring:message
							code="label.comN" />*:</label></td>
				<td class="campiNascitaItalia" style="display: none">
					<div class="comuneSelect">
						<form:select id="comuneNascita" path="comuneNascita">
							<form:option value="">
								<spring:message code="label.select.seleziona" />
							</form:option>
							<c:if test="${not empty datiDichiarazione.comuneNascita}">
								<form:option selected="selected"
									value="${datiDichiarazione.comuneNascita}">${datiDichiarazione.comuneNascitaHidden}</form:option>
							</c:if>
						</form:select>
						<div class="loader">
							<img src="${pageContext.request.contextPath}/images/loader.gif"
								alt="Caricamento..." />
						</div>
						<div class="reset"></div>
					</div>
					<div>
						<form:errors path="comuneNascita" cssStyle="color:red" />
					</div>
				</td>
				<td class="campiNascitaEstero" style="display: none"><label><spring:message
							code="label.comN" />*:</label></td>
				<td class="campiNascitaEstero" style="display: none" colspan="2">
					<form:select cssClass="comuneNascitaEsteroSelect"
						id="comuneNascitaEstero" path="comuneNascitaEstero"
						cssStyle="width:100%">
						<form:option value="">
							<spring:message code="label.select.seleziona" />
						</form:option>
						<c:if test="${not empty datiDichiarazione.comuneNascitaEstero}">
							<form:option selected="selected"
								value="${datiDichiarazione.comuneNascitaEstero}">${datiDichiarazione.comuneNascitaEsteroHidden}</form:option>
						</c:if>
					</form:select>
					<div>
						<form:errors path="comuneNascitaEstero" cssStyle="color:red" />
					</div>
				</td>
			</tr>
			<tr>
				<td><label><spring:message code="label.cf" />*:</label></td>
				<td><form:input path="codiceFiscale" />
					<div>
						<form:errors path="codiceFiscale" cssStyle="color:red" />
					</div>
					<div>
						<form:errors path="identificativoUtente" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message code="label.statoCivile" />*:</label>
				</td>
				<td><form:select path="statoCivile">
						<form:option value="">
							<spring:message code="label.select.seleziona" />
						</form:option>
						<form:options items="${vocabolarioStatiCivili}" />
					</form:select>
					<div>
						<form:errors path="statoCivile" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message code="label.cittadinanza" />*:</label>
				</td>
				<td><form:select path="cittadinanza" cssStyle="width: 100px;">
						<form:option value="">
							<spring:message code="label.select.seleziona" />
						</form:option>
						<form:option value="100">Italia</form:option>
						<form:option value="998">-- NON PRESENTE --</form:option>
						<form:options items="${listaStatiEsteri}" itemValue="codiceStato"
							itemLabel="denominazione" />
					</form:select>
					<div>
						<form:errors path="cittadinanza" cssStyle="color:red" />
					</div></td>
			</tr>

			<!-- campi per altro comune di provenienza -->


			<tr id="altroComuneInput" style="display: none;">
				<td><label><spring:message
							code="label.provinciaProvenienza" />*:</label></td>
				<td><form:select cssClass="provinciaSelect"
						id="provinciaResidenza" path="provinciaResidenza">
						<form:option value="">
							<spring:message code="label.select.seleziona" />
						</form:option>
						<form:options items="${provinceList}" itemLabel="sigla"
							itemValue="sigla" />
					</form:select>
					<div>
						<form:errors path="provinciaResidenza" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message
							code="label.comuneProvenienza" />*:</label></td>
				<td>
					<div class="comuneSelect">
						<form:select id="comuneNascita" path="comuneResidenza">
							<form:option value="">
								<spring:message code="label.select.seleziona" />
							</form:option>
							<c:if test="${not empty datiDichiarazione.comuneResidenza}">
								<form:option selected="selected"
									value="${datiDichiarazione.comuneResidenza}">${datiDichiarazione.comuneResidenzaHidden}</form:option>
							</c:if>
						</form:select>
						<div class="loader">
							<img src="${pageContext.request.contextPath}/images/loader.gif"
								alt="Caricamento..." />
						</div>
						<div class="reset"></div>
					</div>
					<div>
						<form:errors path="comuneResidenza" cssStyle="color:red" />
					</div>
				</td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td class="statoEsteroInputClass" style="display: none;"><label><spring:message
							code="label.statoEstero" />*:</label></td>
				<td class="statoEsteroInputClass" style="display: none;"><form:select
						path="statoEstero" cssStyle="width: 100px;">
						<form:option value="">
							<spring:message code="label.select.seleziona" />
						</form:option>
						<form:option value="998">-- NON PRESENTE --</form:option>
						<form:options items="${listaStatiEsteri}" itemValue="codiceStato"
							itemLabel="denominazione" />
					</form:select>
					<div>
						<form:errors path="statoEstero" cssStyle="color:red" />
					</div></td>
				<td class="aireInputClass" style="display: none;"><label><spring:message
							code="label.comuneIscrizioneAIRE" />*:</label></td>
				<td class="aireInputClass" style="display: none;"><form:select
						path="comuneIscrizioneAIRE">
						<form:option value="">
							<spring:message code="label.select.seleziona" />
						</form:option>
						<form:options items="${comuniList}" itemLabel="denominazione"
							itemValue="codiceIstatAN" />
					</form:select>
					<div>
						<form:errors path="comuneIscrizioneAIRE" cssStyle="color:red" />
					</div></td>
				<td colspan="2"></td>
			</tr>
			<tr id="indirizzoResidenza">
				<c:choose>
					<c:when test="${datiDichiarazione.stardarioResidenzaEnable}">
						<td><label><spring:message code="label.indRes" />*:</label>
						</td>
						<td colspan="5"><osapulie:stradario id="indirizzoResidenzaStradario" viaName="indirizzoResidenza" civicoName="civicoResidenza" esponenteName="esponenteResidenza" viaOptionValue="${datiDichiarazione.indirizzoResidenza}" civicoOptionValue="${datiDichiarazione.civicoResidenza}" esponenteValue="${datiDichiarazione.esponenteResidenza}" viaOptionText="${datiDichiarazione.viaTextHidden}" civicoOptionText="${datiDichiarazione.civicoTextHidden}" viaTextHiddenName="viaTextHidden" civicoTextHiddenName="civicoTextHidden" localitaHiddenName="localitaResidenza" localitaValue="${datiDichiarazione.localitaResidenza}" codiceViaHiddenName="codiceViaResidenza" codiceViaValue="${datiDichiarazione.codiceViaResidenza}" loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
							<div>
								<form:errors path="nuovaVia" cssStyle="color:red" />
							</div></td>
					</c:when>
					<c:otherwise>
						<td><label><spring:message
									code="label.viaProvenienza" />*:</label></td>
						<td><form:input path="indirizzoResidenza" />
							<div>
								<form:errors path="indirizzoResidenza" cssStyle="color:red" />
							</div></td>
						<td><label><spring:message
									code="label.civioProvenienza" />*:</label></td>
						<td><form:input path="civicoResidenza" />
							<div>
								<form:errors path="civicoResidenza" cssStyle="color:red" />
							</div></td>
						<td><label><spring:message
									code="label.esponenteProvenienza" />:</label></td>
						<td><form:input path="esponenteResidenza" />
							<div>
								<form:errors path="esponenteResidenza" cssStyle="color:red" />
							</div></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr id="indirizzoResidenzaStessoComune" style="display: none;">
				<td><label><spring:message code="label.interno" />:</label></td>
				<td><form:input path="internoResidenza" />
					<div>
						<form:errors path="internoResidenza" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message code="label.piano" />:</label></td>
				<td><form:input path="pianoResidenza" />
					<div>
						<form:errors path="pianoResidenza" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message code="label.scala" />:</label></td>
				<td><form:input path="scalaResidenza" />
					<div>
						<form:errors path="scalaResidenza" cssStyle="color:red" />
					</div></td>
			</tr>
		</table>
		<!-- Lavoro e titolo di studio -->
		<table class="genericTable">
			<tr>
				<td><label><spring:message code="label.professione" />:</label>
				</td>
				<td>
					<ul>
						<form:radiobuttons path="professione"
							items="${vocabolarioPosizioniProfessionali}" element="li" />
					</ul>
				</td>
			</tr>
			<tr>
				<td><label><spring:message
							code="label.condNonProfessionale" />:</label></td>
				<td>
					<ul>
						<form:radiobuttons path="condNonProfessionale"
							items="${vocabolarioCondizioniNonProfessionali}" element="li" />
					</ul>
				</td>
			</tr>
			<tr>
				<td><label><spring:message code="label.titoloStudio" />:</label>
				</td>
				<td>
					<ul>
						<form:radiobuttons path="titoloStudio"
							items="${vocabolarioTitoliDiStudio}" element="li" />
					</ul>
				</td>
			</tr>
		</table>

		<!-- Dati anagrafici avanzati -->
		<table class="genericTable">
			 <tr>
						<td>
							<label><spring:message code="label.telefono" />:</label>
						</td>
						<td>
							<form:input path="telefono" />
						   	<div><form:errors path="telefono" cssStyle="color:red"/></div>	
						</td>
						<td>
							<label><spring:message code="label.cellulare" />:</label>
						</td>
						<td>
							<form:input path="cellulare" />
						   	<div><form:errors path="cellulare" cssStyle="color:red"/></div>	
						</td>
					</tr>
					<tr>
						<td>
							<label><spring:message code="label.email" />:</label>
						</td>
						<td>
							<form:input path="email" />
						   	<div><form:errors path="email" cssStyle="color:red"/></div>	
						</td>
						<td>
							<label><spring:message code="label.pec" />:</label>
						</td>
						<td>
							<form:input path="pec" />
						   	<div><form:errors path="pec" cssStyle="color:red"/></div>	
						</td>
					</tr> --%>
			<tr>
				<td><label><spring:message code="label.tipoPatente" />:</label>
				</td>
				<td colspan="3"><form:select id="tipoPatente"
						path="tipoPatente">
						<form:option value=""></form:option>
						<form:options items="${vocabolarioTipiPatente}" />
					</form:select>
					<div>
						<form:errors path="tipoPatente" cssStyle="color:red" />
					</div></td>
			</tr>
			<tr>
				<td width="25%"><label><spring:message
							code="label.numPatente" />:</label></td>
				<td width="25%"><form:input path="numPatente" />
					<div>
						<form:errors path="numPatente" cssStyle="color:red" />
					</div></td>
				<td width="25%"><label><spring:message
							code="label.dataRilascioPatente" />:</label></td>
				<td width="25%"><form:input path="dataRilascioPatente"
						id="dataRilascioPatenteDP" type="text" cssClass="data_input" />
					<div>
						<form:errors path="dataRilascioPatente" cssStyle="color:red" />
					</div></td>
			</tr>
			<tr>
				<td><label><spring:message
							code="label.organoRilascioPatente" />:</label></td>
				<td><form:select path="organoRilascioPatente">
						<form:option value=""></form:option>
						<form:options items="${vocabolarioEntiRilascioPatente}" />
					</form:select>
					<div>
						<form:errors path="organoRilascioPatente" cssStyle="color:red" />
					</div></td>
				<td><label><spring:message code="label.provPatente" />:</label>
				</td>
				<td><form:select id="provPatente" path="provPatente">
						<form:option value=""></form:option>
						<form:options items="${provinceList}" itemLabel="sigla"
							itemValue="sigla" />
						<form:option value="-">EX-TERRITORI-ITALIANI</form:option>
						<form:option value="I">PROVINCIA SCONOSCIUTA</form:option>
						<form:option value="U1">DUPLICATO PATENTE U.C.O.</form:option>
					</form:select>
					<div>
						<form:errors path="provPatente" cssStyle="color:red" />
					</div></td>
			</tr>
			<tr>
				<td colspan="4"><label><spring:message
							code="label.titoloTarghe" /></label></td>
			</tr>

			<c:forEach var="i" begin="0" end="${numeroMaxVeicoliForm - 1}"
				varStatus="status">
				<tr>
					<td><label><spring:message code="label.tipoVeicolo" />:</label>
					</td>
					<td><form:select id="veicoli[${status.index}].tipo"
							path="veicoli[${status.index}].tipo">
							<form:option value=""></form:option>
							<form:options items="${vocabolarioTipiVeicolo}" />
						</form:select>
						<div>
							<form:errors path="veicoli[${status.index}].tipo"
								cssStyle="color:red" />
						</div></td>
					<td><label><spring:message code="label.targaVeicolo" />:</label>
					</td>
					<td><form:input path="veicoli[${status.index}].targa"
							maxlength="8" />
						<div>
							<form:errors path="veicoli[${status.index}].targa"
								cssStyle="color:red" />
						</div></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4"><br /> <spring:message
						code="label.dichiarazioni.mendaci" /></td>
			</tr>
		</table>
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="label.legend" />
		</legend>
		<table class="genericTable ">
			<tr>
				<td><label><spring:message code="label.nuovoIndir" />*:</label>
					<osapulie:stradario id="nuovoIndirizzo" viaName="nuovaVia" civicoName="nuovoNum" esponenteName="nuovoEsp" viaOptionValue="${datiDichiarazione.nuovaVia}" civicoOptionValue="${datiDichiarazione.nuovoNum}" esponenteValue="${datiDichiarazione.nuovoEsp}" viaOptionText="${datiDichiarazione.nuovaViaTextHidden}" civicoOptionText="${datiDichiarazione.nuovoNumTextHidden}" viaTextHiddenName="nuovaViaTextHidden" civicoTextHiddenName="nuovoNumTextHidden" localitaHiddenName="nuovaLocalitaHidden" localitaValue="${datiDichiarazione.nuovaLocalitaHidden}" codiceViaHiddenName="nuovoCodiceViaHidden" codiceViaValue="${datiDichiarazione.nuovoCodiceViaHidden}" loadingImageSrc="${pageContext.request.contextPath}/images/loader.gif" />
					<div>
						<form:errors path="nuovaVia" cssStyle="color:red" />
					</div></td>
			</tr>
		</table>
		<table class="genericTable ">
			<tr>
				<td><label><spring:message code="label.corte" /></label></td>
				<td><label><spring:message code="label.piano" />*</label></td>
				<td id="altro"><label><spring:message
							code="label.numeroPiano" />*</label></td>
				<td><label><spring:message code="label.scala" /></label></td>
				<td><label><spring:message code="label.interno" />*</label></td>
				<td><label><spring:message code="label.sezione" /></label></td>
				<td><label><spring:message code="label.foglio" /></label></td>
				<td><label><spring:message code="label.particella" /></label>
				</td>
				<td><label><spring:message code="label.subalterno" /></label>
				</td>
			</tr>
			<tr>
				<td><form:input path="nuovaCorte" size="3" maxlength="7" />
					<div>
						<form:errors path="nuovaCorte" cssStyle="color:red" />
					</div></td>
				<td><form:select path="nuovoPiano">
						<form:option value=""></form:option>
						<form:option value="St">Sottoscala[St]</form:option>
						<form:option value="Am">Ammezzato[Am]</form:option>
						<form:option value="Rt">Rialzato[Rt]</form:option>
						<form:option value="T">Terra[T]</form:option>
						<form:option value="NP">Non presente[NP]</form:option>
					</form:select>
					<div>
						<form:errors path="nuovoPiano" cssStyle="color:red" />
					</div></td>
				<td id="altroIn"><form:input path="dettaglioPianoResidenza"
						size="3" maxlength="2" />
					<div>
						<form:errors path="dettaglioPianoResidenza" cssStyle="color:red" />
					</div></td>
				<td><form:input path="nuovaScala" size="3" maxlength="2" />
					<div>
						<form:errors path="nuovaScala" cssStyle="color:red" />
					</div></td>
				<td><form:input path="nuovoInterno" size="3" maxlength="3" />
					<div>
						<form:errors path="nuovoInterno" cssStyle="color:red" />
					</div></td>
				<td><form:input path="sezione" size="3" />
					<div>
						<form:errors path="sezione" cssStyle="color:red" />
					</div></td>
				<td><form:input path="foglio" size="3" />
					<div>
						<form:errors path="foglio" cssStyle="color:red" />
					</div></td>
				<td><form:input path="particella" size="3" />
					<div>
						<form:errors path="particella" cssStyle="color:red" />
					</div></td>
				<td><form:input path="subalterno" size="3" />
					<div>
						<form:errors path="subalterno" cssStyle="color:red" />
					</div></td>
			</tr>
		</table>
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="label.legendStranieri" />
		</legend>
		<table class="genericTable">
			<tr>
				<td width="25%"><label><spring:message code="label.questura" />:</label></td>
				<td width="25%"><form:input path="questura" />
					<div>
						<form:errors path="questura" cssStyle="color:red" />
					</div></td>
				<td width="25%"><label><spring:message
							code="label.datSca" />&nbsp;(dd/mm/yyyy):</label></td>
				<td width="25%"><form:input path="dataScadenza"
						id="dataScadenzaDP" type="text" cssClass="data_input" />
					<div>
						<form:errors path="dataScadenza" cssStyle="color:red" />
					</div></td>
			</tr>
			<tr>
				<td width="25%"><label><spring:message
							code="label.datRil" />&nbsp;(dd/mm/yyyy):</label></td>
				<td width="25%"><form:input path="dataRilascio"
						id="dataRilascioDP" type="text" cssClass="data_input" />
					<div>
						<form:errors path="dataRilascio" cssStyle="color:red" />
					</div></td>
				<td width="25%"><label><spring:message
							code="label.numSogg" />:</label></td>
				<td width="25%"><form:input path="numSogg" />
					<div>
						<form:errors path="numSogg" cssStyle="color:red" />
					</div></td>
			</tr>
		</table>
	</fieldset>


	<jsp:include page="partUno.jsp" ></jsp:include>


	<!-- DATI RELTIVI ALL'ABITAZIONE  -->
	<fieldset>
		<legend>
			<spring:message code="label.datiAbitazione" />
		</legend>
		<table class="genericTable">
			<tr>
				<td colspan="2"><form:checkbox
						id="dichiarazioneTitoloAbitativo"
						path="dichiarazioneTitoloAbitativo" /><label><spring:message
							code="label.titoloAbitativo" /></label>&nbsp;<form:errors
						path="dichiarazioneTitoloAbitativo" cssStyle="color:red" />&nbsp;<form:errors
						path="titoloAbitativo" cssStyle="color:red" /></td>
			</tr>
		


		<!-- ----------------------------------------------------------------------------------------------------- -->
		<!-- Campi vecchio modulo -->

		<tr>
			<td><form:radiobutton path="titoloAbitativo" value="A" id="titoloAbitativoA"/></td>
			<td><spring:message code="label.titoloAbitativo.1a" /><br /></td>
		</tr>
		<tr>
			<td><form:radiobutton path="titoloAbitativo" value="B" id="titoloAbitativoB"/></td>
			<td><spring:message code="label.titoloAbitativo.2a" /> <form:input
					path="titoloAbitativoAgenzia1" size="20" /> <form:errors
					path="titoloAbitativoAgenzia1" cssStyle="color:red" /> <br /> <spring:message
					code="label.titoloAbitativo.2b" /> <form:input
					path="titoloAbitativoData1" id="titoloAbitativoData1"
					cssClass="data_input" size="6" /> <form:errors
					path="titoloAbitativoData1" cssStyle="color:red" /> <spring:message
					code="label.titoloAbitativo.2c" /> <form:input
					path="titoloAbitativoNumero1" size="6" /> <form:errors
					path="titoloAbitativoNumero1" cssStyle="color:red" /></td>
		</tr>
		<tr>
			<td><form:radiobutton path="titoloAbitativo" value="C" id="titoloAbitativoC"/></td>
			<td><spring:message code="label.titoloAbitativo.3" /></td>
		</tr>
		
		
				<tr><td colspan="2">
		<div id="allegaFileIntestatarioEdiResPub" style="display:none;">
			
			<b><spring:message code="label.documentazione.file" /></b>
			
			<fieldset>
					<legend>
						<spring:message code="label.altriAllegati" />*
					</legend>
					<table class="dichiarazioneFileTable">
						<tr>
							<td>
								<form:input path="fileIntestatarioEdiResPub" type="file" />&nbsp;&nbsp;&nbsp;
								<form:errors path="fileIntestatarioEdiResPub" cssStyle="color:red"/>
								<span class="container_pulsante" style="display:none;"><!-- silvio -->
									<input type="submit" name="inviaFileIntestatarioEdiResPub" id="inviaFileIntestatarioEdiResPub" value="Invia" />
								</span>
							</td>
						</tr>
					</table>
				</fieldset>
			
		</div>
		</td></tr>
		
		<tr>
			<td><form:radiobutton path="titoloAbitativo" value="D" id="titoloAbitativoD"/></td>
			<td><spring:message code="label.titoloAbitativo.4a" /> <form:input
					path="titoloAbitativoAgenzia2" size="20" /> <form:errors
					path="titoloAbitativoAgenzia2" cssStyle="color:red" /> <br /> <spring:message
					code="label.titoloAbitativo.4b" /> <form:input
					path="titoloAbitativoData2" id="titoloAbitativoData2"
					cssClass="data_input" size="6" /> <form:errors
					path="titoloAbitativoData2" cssStyle="color:red" /> <spring:message
					code="label.titoloAbitativo.4c" /> <form:input
					path="titoloAbitativoNumero2" size="6" /> <form:errors
					path="titoloAbitativoNumero2" cssStyle="color:red" /></td>
		</tr>
		<tr>
			<td><form:radiobutton path="titoloAbitativo" value="E" id="titoloAbitativoE"/></td>
			<td><spring:message code="label.titoloAbitativo.5" /><br /> <form:input
					path="titoloAbitativoAltro1" size="100" /> <form:errors
					path="titoloAbitativoAltro1" cssStyle="color:red" /></td>
		</tr>
							
		<tr><td colspan="2">
		<div id="allegaFileUsufruttuario" style="display:none;">
			
			<b><spring:message code="label.documentazione.file" /></b>
			
			<fieldset>
					<legend>
						<spring:message code="label.altriAllegati" />*
					</legend>
					<table class="dichiarazioneFileTable">
						<tr>
							<td>
								<form:input path="fileUsufruttuario" type="file" />&nbsp;&nbsp;&nbsp;
								<form:errors path="fileUsufruttuario" cssStyle="color:red"/>
								<span class="container_pulsante" style="display:none;"><!-- silvio -->
									<input type="submit" name="inviaFileUsufruttuario" id="inviaFileUsufruttuario" value="Invia" />
								</span><!-- silvio -->
							</td>
						</tr>
					</table>
				</fieldset>
			
		</div>
		</td></tr>
		<tr style="display:none;">
			<td><form:radiobutton path="titoloAbitativo" value="F" id="titoloAbitativoF"/></td>
			<td><spring:message code="label.titoloAbitativo.6" /><br /> <form:input
					path="titoloAbitativoAltro2" size="100" /> <form:errors
					path="titoloAbitativoAltro2" cssStyle="color:red" /></td>
		</tr>
		</table>
		<jsp:include page="modificheDichiarazioneCambioResidenza.jsp"></jsp:include>
	</fieldset>

	<fieldset id="tipoCambioAbitazione" style="display: none;">
		<legend>
			<spring:message code="label.tipoCambioAbitazione" />
		</legend>
		<table class="genericTable">
			<tr>
				<td><form:radiobutton path="tipoCambioAbitazione" value="1" />&nbsp;<spring:message
						code="label.tipoCambioAbitazione.interoNucleo" /></td>
				<td><form:radiobutton path="tipoCambioAbitazione" value="2" />&nbsp;<spring:message
						code="label.tipoCambioAbitazione.creazioneNucleo" /></td>
			</tr>
		</table>
		<div>
			<form:errors path="tipoCambioAbitazione" cssStyle="color:red" />
		</div>
	</fieldset>


	<!-- ------------------------------------------------------------------------------------------------------ -->

	<fieldset>
		<legend>
			<spring:message code="label.recapiti" />
		</legend>
		<table class="genericTable">
			<tr>
				<td width="25%"><label><spring:message
							code="label.provincia" />:</label></td>
				<td width="25%"><form:select cssClass="provinciaSelect"
						id="recProvincia" path="recProvincia">
						<form:option value=""></form:option>
						<form:options items="${provinceList}" itemLabel="sigla"
							itemValue="sigla" />
					</form:select></td>
				<td width="25%"><label><spring:message
							code="label.comune" />:</label></td>
				<td width="25%">
					<div class="comuneSelect">
						<form:select id="recComune" path="recComune">
							<form:option value=""></form:option>
							<c:if test="${not empty datiDichiarazione.recComune}">
								<form:option selected="selected"
									value="${datiDichiarazione.recComune}">${datiDichiarazione.recComuneHidden}</form:option>
							</c:if>
						</form:select>
						<div class="loader">
							<img src="${pageContext.request.contextPath}/images/loader.gif"
								alt="Caricamento..." />
						</div>
						<div class="reset"></div>
					</div>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label><spring:message code="label.via" />:</label></td>
				<td><form:input path="recVia" /></td>
				<td><label><spring:message code="label.civico" />: </label></td>
				<td><form:input path="recCivico" /> <form:errors
						path="recCivico" cssStyle="color:red" /></td>
				<td><label><spring:message code="label.esponente" />:
				</label></td>
				<td><form:input path="recEsponente" /></td>
			</tr>
			<tr>
				<td><label><spring:message code="label.telefono" />:</label></td>
				<td><form:input path="recTelefono" /></td>
				<td><label><spring:message code="label.cellulare" />:
				</label></td>
				<td><form:input path="recCellulare" /></td>
				<td><label><spring:message code="label.fax" />:</label></td>
				<td><form:input path="recFax" /></td>
			</tr>
			<tr>
				<td><label><spring:message code="label.email" />*: </label></td>
				<td><form:input path="recEmail" /> <form:errors
						path="recEmail" cssStyle="color:red" /></td>
				<td><label><spring:message code="label.pec" />: </label></td>
				<td colspan="2"><form:input path="recPec" /></td>
			</tr>
		</table>
	</fieldset>

	<c:if test="${isComuneBari}">
		<fieldset>
			<legend>
				<spring:message code="label.legendNoteInformative" />
			</legend>
			<span><spring:message code="label.comunebari.noteinformative" /></span>
			<p>
			<ol class="elencoNumerato">
				<li><spring:message code="label.comunebari.noteinformative.1" /></li>
				<li><spring:message code="label.comunebari.noteinformative.2" /></li>
				<li><spring:message code="label.comunebari.noteinformative.3" /></li>
				<li><spring:message code="label.comunebari.noteinformative.4" /></li>
				<li><spring:message code="label.comunebari.noteinformative.5" /></li>
				<li><spring:message code="label.comunebari.noteinformative.6" /></li>
				<li><spring:message code="label.comunebari.noteinformative.7" /></li>
				<li><spring:message code="label.comunebari.noteinformative.8" /></li>
				<li><spring:message code="label.comunebari.noteinformative.9" /></li>
			</ol>
			<p>

				<form:checkbox id="flagNoteInformative" path="flagNoteInformative" />
				&nbsp;
				<spring:message
					code="label.comunebari.noteinformative.allegato.descrizione" />
				&nbsp; <a
					href="https://egov.ba.it/sp/note_informative_dichiarazione.pdf"
					target="_BLANK" class="custom_pulsante"> <spring:message
						code="label.comunebari.noteinformative.allegato.link" />
				</a> <br>
				<form:errors path="flagNoteInformative" cssStyle="color:red" />
				&nbsp;
		</fieldset>
	</c:if>

	<fieldset>
		<legend>
			<spring:message code="label.legendAllegati" />
		</legend>
		<table class="genericTable">
			<tr>
				<td colspan="2"><label><spring:message
							code="label.allegato" /></label></td>
			</tr>
			<tr>
				<td width="180"><label><spring:message
							code="label.altroAllegato" /></label></td>
				<td><form:input path="altroAllegato" size="100" /></td>
			</tr>
		</table>
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="label.noteGenerali" />
		</legend>
		<table class="genericTable">
			<tr>
				<td><label><spring:message code="label.note" />:</label></td>
			</tr>
			<tr>
				<td><form:textarea path="noteGenerali" rows="3" cols="100" />
				</td>
			</tr>
			<tr>
				<td><label><spring:message code="label.notaInformativa" /></label></td>
			</tr>
		</table>
	</fieldset>

<!-- Firma Grafometrica  -->
<c:if test="${datiDichiarazione.firmaGrafometrica}">
	<fieldset>
		<legend>
		<spring:message code="button.radio.label.firma.grafometrica" />
		</legend>

	</fieldset>
	</c:if>
<!-- ######  -->

	<div class="container_pulsante">
		<input type="submit" name="bozza"
			value="<spring:message code="button.salva.bozza" />" /> <span
			class="spacer"></span><input type="submit" name="genera"
			value="<spring:message code="button.dichiarazione" />"
			id="buttonConferma" style="display: none" />
	</div>
	</c:if>

	<c:if test="${!empty download}">
		<div class="container_pulsante">

			<div class="overlay" id="overlay" style="display: none;"></div>
			<div id="load" style="display: none;">
				<img src="http://malsup.com/jquery/block/busy.gif" id="wait"
					style="display: none; margin-left: 700px; margin-top: 300px;">
			</div>
			<a href="${dichiarazioneReportURL}" class="custom_pulsante"
				id="scarica"><spring:message code="link.dichiarazione" /></a> <span
				class="spacer"></span> <a
				href="<portlet:renderURL portletMode="view"/>"
				class="custom_pulsante"><spring:message code="button.home" /></a>
		</div>
	</c:if>
	</form:form>
</div>