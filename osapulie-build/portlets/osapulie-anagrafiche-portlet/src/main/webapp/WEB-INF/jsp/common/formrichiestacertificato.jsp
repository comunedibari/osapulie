<div class="mainDiv richiestaCertificato">
	<c:if test="${ empty datiAnagrafici.errore }">
		<form:form id="${idForm}" action="${richiestaCertificatoUrl}" method="post" commandName="datiAnagrafici">
		
			<input type="hidden" id="codiceFiscale" name="codiceFiscale" value="${componenteFamiglia.codiceFiscale}">
					<c:if test="${! empty datiAnagrafici}">
				<div class="marginBottom10">
					<label><strong><spring:message code="label.soggetto" />:</strong></label>&nbsp;&nbsp;
					<select name="codFisc">
						<c:forEach var="item" begin="0" items="${datiAnagrafici.componentiNucleoFamiliare}">
					    	<option value="${item.codiceFiscale}" <c:if test="${componenteFamiglia.codiceFiscale == item.codiceFiscale}"> selected="selected" </c:if> >${item.cognome} ${item.nome}</option>
						</c:forEach>
					</select>&nbsp;&nbsp;
					<input type="submit" name="cambio" value="<spring:message code="button.back" />"/>
				</div>
				<c:if test="${listaUsi!=null && !empty listaUsi}" >
					<fieldset>
						<legend>Uso della certificazione</legend>
						<select name="uso" id="uso">
							<c:forEach var="itemUsi" items="${listaUsi}" varStatus="i">
						    	<option value="${i.index}">${itemUsi}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;
						<div id="numero_bollo_div" style="display:none">
						 <label><spring:message code="label.numerobollo" />:</label>
						 <input id="numero_bollo" name="numero_bollo" type="text" />
						</div>
					</fieldset>
				</c:if>
				<fieldset>
					<legend>
						<spring:message code="label.riepilogo" />
					</legend>
					<table class="genericTable">
						<tr>
							<td width="180">
								<label><spring:message code="label.cf" />:</label>
							</td>
							<td >
								${componenteFamiglia.codiceFiscale}
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.nome" />:</label>
							</td>
							<td >
								${componenteFamiglia.nome}
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.cognome" />:</label>
							</td>
							<td >
								${componenteFamiglia.cognome}
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.dataN" />:</label>
							</td>
							<td >
								<fmt:formatDate pattern="dd/MM/yyyy" value="${componenteFamiglia.dataNascita.time}" />
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.comN" />:</label>
							</td>
							<td>
								${comuneNascita}
							</td>
						</tr>
						<tr>
							<td width="180">
								<label><spring:message code="label.indRes" />:</label>
							</td>
							<td>
								${datiAnagrafici.toponimoIndirizzo} ${datiAnagrafici.descrizioneVia} ${datiAnagrafici.numeroCivico}
								<c:if test="${!empty datiAnagrafici.esponente}">
									${datiAnagrafici.esponente}
								</c:if>
								<c:if test="${!empty datiAnagrafici.piano}">
									p. ${datiAnagrafici.piano}
								</c:if>
								<c:if test="${!empty datiAnagrafici.scala}">
									s. ${datiAnagrafici.scala}
								</c:if>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="container_pulsante">
					<c:if test="${mostraLink == 'si'}">
						 <c:choose>
						    <c:when test="${listaUsi!=null && !empty listaUsi}">
								<c:choose>
						    		<c:when test="${!empty comuneNascita}">
							 			<a href="${certificatoReportURL}&uso=0" class="custom_pulsante evaluationServiceDownloadLink" id="certificatoReportURL">
											<spring:message code="link.certificato"  />
										</a>
									</c:when>
						    		<c:otherwise>
						    		</c:otherwise>
								</c:choose>
							 </c:when>
						    <c:otherwise>
						    	<c:choose>
						    		<c:when test="${!empty comuneNascita}">
							 			<a href="${certificatoReportURL}" class="custom_pulsante evaluationServiceDownloadLink">
									 		<spring:message code="link.certificato"  />
										</a>
									</c:when>
						    		<c:otherwise>
						    		</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${mostraLink == 'no'}">
						<c:choose>
							<c:when test="${!empty dataNascitaCondition}">
								<strong><spring:message code="msg.nodownload.dataNascitaCondition" arguments="${dataNascitaCondition}"/></strong><span class="spacer"></span>
							</c:when>
							<c:otherwise>
								<strong><spring:message code="msg.nodownload" /></strong><span class="spacer"></span>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${mostraLinkReportPdf == 'si'}">
						<c:choose>
						    <c:when test="${listaUsi!=null && !empty listaUsi}">
								<a href="${reportPdfURL}&uso=0" class="custom_pulsante evaluationServiceDownloadLink" id="reportPDF">
									<spring:message code="link.reportPDF"  />
								</a>
							 </c:when>
						     <c:otherwise>
						 		<a href="${reportPdfURL}" class="custom_pulsante evaluationServiceDownloadLink" id="reportPDF">
									<spring:message code="link.reportPDF"  />
								</a>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${mostraLink == 'si' || mostraLinkReportPdf == 'si'}">
					<%@ include file="../common/valutaservizio.jsp"%>
					</c:if>
				</div>
			</c:if>
		</form:form>
	</c:if>
	<c:if test="${! empty datiAnagrafici.errore }">
		<div>
			<strong><spring:message code="errore.pdds.codice${datiAnagrafici.errore.codice }" /></strong>
		</div>
	</c:if>
</div>


<script type="text/javascript">


	$(document).ready(function(){

		var valueSelected = $('#uso').val();
		var numeroBollo = $('#numero_bollo').val();
		var resourceUrl ='${certificatoReportURL}&uso='+valueSelected+'&bollo='+numeroBollo;
		var resourceUrl2 ='${reportPdfURL}&uso='+valueSelected+'&bollo='+numeroBollo;

		if(valueSelected == '0' ) {
			$("#numero_bollo_div").show();
			if (numeroBollo.length == 0) {
				disableLink('certificatoReportURL');
				disableLink('reportPDF');
			}
		}

		$('#uso').change(function() {

			valueSelected = $('#uso').val();
			numeroBollo = $('#numero_bollo').val();
			resourceUrl ='${certificatoReportURL}&uso='+valueSelected+'&bollo='+numeroBollo;
			resourceUrl2 ='${reportPdfURL}&uso='+valueSelected+'&bollo='+numeroBollo;

			if(valueSelected == '0'){
				$("#numero_bollo_div").show();
				if (numeroBollo.length == 0) {
					disableLink('certificatoReportURL');
					disableLink('reportPDF');
				}
				console.log("VALUE SELECTED (TRUE): "+valueSelected);

			}
			else {
				$("#numero_bollo_div").hide();
				$("#numero_bollo").val('');
				enableLink('certificatoReportURL', resourceUrl);
				enableLink('reportPDF', resourceUrl2);
				numeroBollo = '';
				console.log("VALUE SELECTED (FALSE): "+valueSelected);
				resourceUrl ='${certificatoReportURL}&uso='+valueSelected+'&bollo='+numeroBollo;
				$("#certificatoReportURL").attr("href", resourceUrl);
			}
		});

		$("#numero_bollo").keyup(function(){
			var uso = $('#uso').val();
			var num = $('#numero_bollo').val();

			if(uso == '0' && num.length == 0) {
				disableLink('certificatoReportURL');
				disableLink('reportPDF');
			}
			else {
				var url ='${certificatoReportURL}&uso='+uso+'&bollo='+num;
				var url2 ='${reportPdfURL}&uso='+uso+'&bollo='+num;
				enableLink('certificatoReportURL', url);
				enableLink('reportPDF', url2);
			}

		});

		$('a').live('click', function(e) {
		    if ($(this).attr('disabled') == 'disabled') {
		        e.preventDefault();
		    }
		});

		$('#certificatoReportURL').click(function(e) {
		     return false;
		});

		$('#reportPDF').click(function(e) {
		     return false;
		});

	});

	function enableLink(linkId, url) {
		$('#' + linkId).attr("href", url);
		$('#' + linkId).removeAttr('disabled');
	}

	function disableLink(linkId) {
		$('#' + linkId).attr("href", "#");
		$('#' + linkId).attr('disabled','disabled');
	}

</script>
