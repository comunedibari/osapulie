<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="changeSelectCategoriaImmobileUrl">
	<portlet:param name="action" value="changeSelectCategoriaImmobile" />
</portlet:actionURL>

<portlet:actionURL var="calcoloImuTotaleUrl">
	<portlet:param name="ope" value="calcoloImuTotale" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL" id="dichiarazioneReport" escapeXml="false">
</portlet:resourceURL>

<portlet:resourceURL var="caricaSelectByCategoriaURL" id="caricaSelectByCategoria"/> 

<c:set var="idForm" value="calcoloImu" />
<c:set var="dati" value="${datiCalImu}" />
<c:set var="numeroImmobiliDaVisura" value="${datiCalImu.numeroImmobiliDaVisura}" />

<div class="mainDiv">
	<c:choose>
		<c:when test="${!empty datiCalImu}">
			<form:form id="${idForm}" action="${calcoloImuTotaleUrl}" method="post" commandName="datiCalImu">
			
			<form:hidden id="flagNuovoImmobile" path="nuovoImmobile" />
				<c:if test="${empty download}">
					<%@ include file="../common/calcoloImuDatiAnagrafici.jsp"%>				
					<fieldset>
						<legend>
							<spring:message code="label.legend" />
						</legend>
						<div id="contenitoreTableRiassunto">
							<table class="genericTable elencoRisultati">
								<tr>
									<th>
										<spring:message code="label.indir"/>
									</th>
									<th>
										<spring:message code="label.foglioCatastale"/>
									</th>
									<th>
										<spring:message code="label.particella"/>
									</th>
									<th>
										<spring:message code="label.tipo"/>
									</th>
									<th>
										<spring:message code="label.sezione"/>
									</th>
									<th>
										<spring:message code="label.valore"/>
									</th>
									<th>
										<spring:message code="label.importo"/>
									</th>
									<th>
										<spring:message code="label.rimuovi"/>
									</th>
								</tr>
								
								<c:set var="annoPrecedente" value="false"/>
								<c:if test="${!empty situazione}">
									<c:set var="annoRiferimentoString">${situazione.annoRiferimento}</c:set>
									<c:if test="${annoCorrente != annoRiferimentoString}">
										<c:set var="annoPrecedente" value="true"/>
									</c:if>
								</c:if>
								<c:forEach items="${datiCalImu.datiRiepilogo}" var="datoRiepilogo" varStatus="i"> 
									<portlet:actionURL var="eliminaURL">
										<portlet:param name="op" value="eliminaImmobile" />
										<portlet:param name="id" value="${i.index}" />
									</portlet:actionURL>
									<tr>
										<td>
											<c:out value="${datoRiepilogo.indirizzo}"></c:out><c:if test="${annoPrecedente == 'true'}"><span class="red">*</span></c:if>
										</td>
										<td>
											<c:out value="${datoRiepilogo.foglio}"></c:out>
										</td>
										<td>
											<c:out value="${datoRiepilogo.num}"></c:out>
										</td>
										<td>
											<c:out value="${datoRiepilogo.quota}"></c:out>
										</td>
										<td>
											<c:out value="${datoRiepilogo.sezione}"></c:out>
										</td>
										<td>
											<c:out value="${datoRiepilogo.valoreimtable}"></c:out>
										</td>
										<td>
											<c:out value="${datoRiepilogo.totaleImmobile}"></c:out>
										</td>
										<td>
											<a href="${eliminaURL}" title="Elimina"  onclick="javascript:return(confirm('Vuoi eliminare l immobile?'))">Elimina</a>
																
	   									</td>
									</tr>
								</c:forEach>
							</table>
							<c:if test="${visuraPosizioneTributariaActive}">
								<c:if test="${annoPrecedente == 'true'}">
									<span class="red"><spring:message code="label.dati.noAnnoCorrente" arguments="${annoRiferimentoString}"/>.</span><br>
								</c:if>
								<span><spring:message code="label.dati.indicativi"/>.</span><br>
								</c:if>	
							<label>
								<spring:message code="label.totale" />
							</label>
							<c:out value="${datiCalImu.totale}"></c:out>
					 	</div>		
					</fieldset>
						
					<div class="container_pulsante">
						<a class="custom_pulsante evaluationServiceDownloadLink" href="${dichiarazioneReportURL}" id="generaf24">
							<spring:message code="button.generaf24semplificato" />
						</a>
						<%@ include file="../common/valutaservizio.jsp"%>
					</div>
					
					<div class="container_pulsante">
						<a class="custom_pulsante" id="nuovoImmobile">
							<spring:message code="button.aggiungiImmobile" />
						</a>
					</div>
						
					<form:hidden id="selectCambiata" path="selectCambiata" />
					<form:hidden path="numeroImmobiliDaVisura" />
					<form:hidden id="numeroDivCalcoloParziale" path="numeroDivCalcoloParziale" />
					
					<!-- SEZIONE DETTAGLIO IMMOBILI -->
					<div id="contenitoreDettagli">
						<c:if test="${not empty datiCalImu.datiRiepilogo}">
						<c:forEach items="${datiCalImu.datiRiepilogo}" var="datoRiepilogo" varStatus="status">
						<div id="contenitoreDettImm_${status.count}">
							<fieldset>
								<legend id="legend_$(this).text($(this).next().toggle());${status.count}">
									<spring:message code="legend.dettaglioImmobile" />${datoRiepilogo.indirizzo}
								</legend>
								<div>
							     <table class="genericTable">
									<tr>
									  <td colspan="6" align="center">
										<label>
										   <spring:message code="label.riepilogoVisura" />
										</label>
									  </td>
									</tr>
									<tr>
									  <td>
										<label>
										  <spring:message code="label.indir" />:
										</label>
									  </td>
										<td colspan="5">
										  <form:input path="datiRiepilogo[${status.index}].indirizzo" size="30" />
										   <div><form:errors path="datiRiepilogo[${status.index}].indirizzo" cssStyle="color:red"/></div>	
										</td>
									 </tr>
									 <tr>
										<td width="180px">
										  <label>
											<spring:message code="label.foglioCatastale" />:
										  </label>
										</td>
										<td width="180px">
										  <form:input path="datiRiepilogo[${status.index}].foglio" size="8" readonly="true" style="background-color:#DDDDDD"/>
										   <div><form:errors path="datiRiepilogo[${status.index}].foglio" cssStyle="color:red"/></div>
										</td>
										<td width="180px">
										  <label>
											<spring:message code="label.particella" />:
										  </label>
										 </td>
										 <td>
										   <form:input path="datiRiepilogo[${status.index}].num" size="8" readonly="true" style="background-color:#DDDDDD" />
											<div><form:errors path="datiRiepilogo[${status.index}].num" cssStyle="color:red"/></div>
										 </td>
										  <td>
											<label>
											  <spring:message code="label.sezione" />:
											</label>
										  </td>
										   <td>
											 <form:input path="datiRiepilogo[${status.index}].sezione" size="8" readonly="true" style="background-color:#DDDDDD" />
											 <div><form:errors path="datiRiepilogo[${status.index}].sezione" cssStyle="color:red"/></div>
											</td>
										</tr>
										<tr>
											<td colspan="6" align="center">
													<label>
														<spring:message code="label.calcoloImu" />
													</label>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<spring:message code="label.cat" />:
													</label>
												</td>
												<td colspan="5" id="tdSelectCategoria">
													<form:select name="datiRiepilogo[${status.index}].categoria" id="categoria${status.index}" path="datiRiepilogo[${status.index}].categoria">
														<form:option value="" label="--- Select ---"/>
		   												<form:options items="${listaCategorieImmobili}" itemLabel="tipologiaCategoriaImmobile.descrizione" itemValue="id"  />
		   											</form:select>	
													<div><form:errors path="datiRiepilogo[${status.index}].categoria" cssStyle="color:red"/></div>
												</td>
											</tr>
											
											<tr>
												<td>
													<label>
														<spring:message code="label.valore" />:
													</label>
												</td>
												<td colspan="5">
													<form:input path="datiRiepilogo[${status.index}].valoreim" size="8" />
													<div><form:errors path="datiRiepilogo[${status.index}].valoreim" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>	
												<td>
													<label>
														<spring:message code="label.possessoPerc" />:
													</label>
												</td>
												<td>
													<form:input path="datiRiepilogo[${status.index}].quota" size="10" />
													<div><form:errors path="datiRiepilogo[${status.index}].quota" cssStyle="color:red"/></div>
												</td>
												<td>
													<label>
														<spring:message code="label.possessoMesi" />:
													</label>
												</td>
												<td colspan="4">
													<form:input path="datiRiepilogo[${status.index}].quotaMesi" size="10" />
													<div><form:errors path="datiRiepilogo[${status.index}].quotaMesi" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<spring:message code="label.aliquota" />:
													</label>
												</td>
												<td colspan="5">
													<form:input path="datiRiepilogo[${status.index}].aliquota"  id="aliquota${status.index}" size="8" readonly="true" />
													<div><form:errors path="datiRiepilogo[${status.index}].aliquota" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
											  <td>
												<label>
												  <spring:message code="label.agevolazioni" />:
												</label>
											  </td>
											  <td colspan="5">
												<form:select id="agevolazioni${status.index}" name="datiRiepilogo[${status.index}].agevolazione" path="datiRiepilogo[${status.index}].agevolazione" style="width:200px" >
												 <c:choose>
													 <c:when test="${listaAgevolazioni!=null}">
			   											 <form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
			   										</c:when>
													 <c:otherwise>
														 <form:option value="NONE" label="NO"/>
													 </c:otherwise>
														</c:choose>
		   											</form:select>	
		   											
		   											<strong><em><form:errors path="datiRiepilogo[${status.index}].agevolazione" cssStyle="color:red"/></em></strong>
												</td>
											</tr>
										<tr>
										  <td>
											<label>
											  <spring:message code="label.det" />:
											</label>
										  </td>
										  <td colspan="5">
											<form:select id="detrazioni${status.index}" name="datiRiepilogo[${status.index}].detcasa" path="datiRiepilogo[${status.index}].detcasa" style="width:200px" >
											  <form:option value="NONE" label="NO"/>
		   										<c:if test="${listaDetrazioni!=null}">
		   											<form:options items="${listaDetrazioni}" itemLabel="descrizione" itemValue="id"  />
		   										</c:if>
		   									  </form:select>	
											 <div><form:errors path="datiRiepilogo[${status.index}].detcasa" cssStyle="color:red"/></div>
										  </td>
										</tr>	
										
										<tr>
										 <td>
										   <label>
											 <spring:message code="label.esenzione" />:
											</label>
										  </td>
										  <td colspan="5">
											<form:select id="esenzioni${status.index}" name="datiRiepilogo[${status.index}].esenzione" path="datiRiepilogo[${status.index}].esenzione" style="width:200px" >
											  <form:option value="NONE" label="NO"/>
		   										<c:if test="${listaEsenzioni!=null}">
		   											<form:options items="${listaEsenzioni}" itemLabel="descrizione" itemValue="id"  />
		   										</c:if>
		   									  </form:select>	
											 <div><form:errors path="datiRiepilogo[${status.index}].esenzione" cssStyle="color:red"/></div>
										  </td>
									   </tr>	
									   </table>
									   <div id="pertinenzeDiv${status.index}" style="display:none" >
											   <table class="genericTable" >
											   	   <tr id="trPertinenze_intestazione_${status.count}" style="background:#FAFAFA;" >
											  <td width="180px">
												<label><spring:message code="label.pertinenze"/>:</label>
											  </td>
											  <td width="180px">
												<label><spring:message code="label.pertC2"/>:</label>
											  </td>
											  <td width="180px">
												 <label><spring:message code="label.pertC6"/>:</label>
											  </td>
											   <td colspan="3">
												 <label><spring:message code="label.pertC7"/>:</label>
											  </td>
											</tr>	
											<tr id="trPertinenze_rendita_${status.count}" style="background:#FAFAFA;">
												<td>
												 <label>
													<spring:message code="label.valore" />:
												 </label>
													</td>
													<td>
														<form:input path="datiRiepilogo[${status.index}].pertRenditaCatC2" size="8" />
														<div><form:errors path="datiRiepilogo[${status.index}].pertRenditaCatC2" cssStyle="color:red"/></div>
													</td>
													<td>
														<form:input path="datiRiepilogo[${status.index}].pertRenditaCatC6" size="8" /> 
														<div><form:errors path="datiRiepilogo[${status.index}].pertRenditaCatC6" cssStyle="color:red"/></div>
													</td>
													<td colspan="3">
														<form:input path="datiRiepilogo[${status.index}].pertRenditaCatC7" size="8" />
														<div><form:errors path="datiRiepilogo[${status.index}].pertRenditaCatC7" cssStyle="color:red"/></div>
													</td>
												</tr>
												<tr id="trPertinenze_possesso_${status.count}" style="background:#FAFAFA;">
													<td>
														<label>
															<spring:message code="label.possessoPerc" />:
														</label>
													</td>
													<td>
														<form:input path="datiRiepilogo[${status.index}].pertPossessoPercC2" size="8" /> 
														<div><form:errors path="datiRiepilogo[${status.index}].pertPossessoPercC2" cssStyle="color:red"/></div>
													</td>
													<td>
														<form:input path="datiRiepilogo[${status.index}].pertPossessoPercC6" size="8" />
														<div><form:errors path="datiRiepilogo[${status.index}].pertPossessoPercC6" cssStyle="color:red"/></div>
													</td>
													<td colspan="3">
														<form:input path="datiRiepilogo[${status.index}].pertPossessoPercC7" size="8" />
														<div><form:errors path="datiRiepilogo[${status.index}].pertPossessoPercC7" cssStyle="color:red"/></div>
													</td>
												</tr>
												<tr id="trPertinenze_possessoMesi_${status.count}" style="background:#FAFAFA;">
													<td>
														<label>
															<spring:message code="label.possessoMesi" />:
														</label>
													</td>
													<td>
														<form:input path="datiRiepilogo[${status.index}].pertPossessoMesiC2" size="8" />
														<div><form:errors path="datiRiepilogo[${status.index}].pertPossessoMesiC2" cssStyle="color:red"/></div>
													</td>
													<td>
														<form:input path="datiRiepilogo[${status.index}].pertPossessoMesiC6" size="8" />
														<div><form:errors path="datiRiepilogo[${status.index}].pertPossessoMesiC6" cssStyle="color:red"/></div>
													</td>
													<td colspan="3">
														<form:input path="datiRiepilogo[${status.index}].pertPossessoMesiC7" size="8" />
														<div><form:errors path="datiRiepilogo[${status.index}].pertPossessoMesiC7" cssStyle="color:red"/></div>
													</td>
												<tr id="trPertinenze_immStorico_${status.count}" style="background:#FAFAFA;">
													<td>
														<label>
															<spring:message code="label.agevolazioni" />:
														</label>
													</td>
													<td>
														<form:select id="agevolazioniC2${status.index}" name="datiRiepilogo[${status.index}].pertAgevolazioneC2" path="datiRiepilogo[${status.index}].pertAgevolazioneC2" style="width:200px" >
															<c:choose>
																<c:when test="${listaAgevolazioni!=null}">
				   													<form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
				   												</c:when>
																<c:otherwise>
																	<form:option value="NONE" label="NO"/>
																</c:otherwise>
															</c:choose>
			   											</form:select>	
			   											<strong><em><form:errors path="datiRiepilogo[${status.index}].pertAgevolazioneC2" cssStyle="color:red"/></em></strong>
													</td>
													<td>
														<form:select id="agevolazioniC6${status.index}" name="datiRiepilogo[${status.index}].pertAgevolazioneC6" path="datiRiepilogo[${status.index}].pertAgevolazioneC6" style="width:200px" >
															<c:choose>
																<c:when test="${listaAgevolazioni!=null}">
				   													<form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
				   												</c:when>
																<c:otherwise>
																	<form:option value="NONE" label="NO"/>
																</c:otherwise>
															</c:choose>
			   											</form:select>	
			   											
			   											<strong><em><form:errors path="datiRiepilogo[${status.index}].pertAgevolazioneC6" cssStyle="color:red"/></em></strong>
													</td>
													<td colspan="3">
														<form:select name="datiRiepilogo[${status.index}].pertAgevolazioneC7" path="datiRiepilogo[${status.index}].pertAgevolazioneC7" id="agevolazioniC7${status.index}" style="width:200px" >
															<c:choose>
																<c:when test="${listaAgevolazioni!=null}">
				   													<form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
				   												</c:when>
																<c:otherwise>
																	<form:option value="NONE" label="NO"/>
																</c:otherwise>
															</c:choose>
			   											</form:select>	
			   											
			   											<strong><em><form:errors path="datiRiepilogo[${status.index}].pertAgevolazioneC7" cssStyle="color:red"/></em></strong>
													</td>
												</tr>
										   </table>
									   </div>
									   <!-- Sezione relativa alle pertinenze  -->
								
										<table class="genericTable">	
											<tr>
												<td>
													<label>
														<spring:message code="label.imposta"/>:
													</label>
												</td>
												<td colspan="5">
													<form:input path="datiRiepilogo[${status.index}].impostaImu" size="8" readonly="true" style="background-color:#DDDDDD" />
													<div><form:errors path="datiRiepilogo[${status.index}].impostaImu" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<spring:message code="label.importoDet"/>:
													</label>
												</td>
												<td colspan="5">
													<form:input path="datiRiepilogo[${status.index}].importoDetrazione" size="8" readonly="true" style="background-color:#DDDDDD" />
													<div><form:errors path="datiRiepilogo[${status.index}].importoDetrazione" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
		
												<td>
													<label>
														<spring:message code="label.importo"/>:
													</label>
												</td>
												<td colspan="5">
													<form:input path="datiRiepilogo[${status.index}].dapagar" id="dapagar" size="8" readonly="true" style="background-color:#DDDDDD" />
													<div><form:errors path="datiRiepilogo[${status.index}].dapagar" cssStyle="color:red"/></div>
												</td>
												<td>
													<input type="button" id="calcoloParziale_${status.index}" name="calcoloParziale_${status.index}" value="<spring:message code="button.dichiarazione" />" />
												</td>
											</tr> 
								</table>
							</div>
							</fieldset>			
							</div>
						</c:forEach>
						</c:if>
					</div>
					<%-- Form di inserimento  --%>
				<div id="contenitoreFormInserimento" style="display:none">
					<%@ include file="../calcoloimu/formImmobile.jsp"%>
				</div>	
				</c:if>
			</form:form>
		</c:when>
		<c:otherwise>
			<div>
				<strong><spring:message code="errore.pdds.codice3"/></strong>
			</div>								
		</c:otherwise>
	</c:choose>
</div>	
<script type="text/javascript">
	$(document).ready(function(){
		
		//Setto il sesso
		var sesso = "${datiCalImu.sesso}";
		$("#select_sesso").val(sesso);
		
		/*al caricamento della pagina, disabilito il pulsante per la generazione dell''F24 se non e' stato censito
		almeno un immobile
		*/
		var disabledF24=disabledF24button();
		if(disabledF24==true){
			$("#generaf24").addClass('disabled');
		    $("#generaf24").click(function(e){
				 //if(e.preventDefault)
				        e.preventDefault();
				   // else
				     //   e.returnValue = false;
		    });
		}
		
		/*quando carica la pagina, controllo se il flagNuovoImmobile e' settato a true. Se e' cosi, 
		devo far comparire il form per l'inserimento di un nuovo immobile*/
		var flagNuovoImmobile=$("#flagNuovoImmobile").val();
		
		if(flagNuovoImmobile=='true'){
			$("#contenitoreFormInserimento").show();
		}
		
		
		/*sul caricamento della pagina, controlla che tipo di categoria ha l'immobile. Se contiene
		la parola "pertinenze" allora bisogna visualizzare il div delle pertinenze, altrimenti
		lo si nasconde.*/
		
		
		$("select[id^='categoria']").each(function(){
			var idName=$(this).attr("id");
			var value=$('#'+idName+' option:selected').text();
			var tmp=idName.split("categoria");
			var id=tmp[1];
			var valoreSelectCategoria = $("#categoria"+id).val();

			setLists(id,valoreSelectCategoria);
			
			if(value.indexOf("pertinenze")>-1){
				console.log( $(this).attr("id") );
				$('#pertinenzeDiv'+id).show();
			}else{
				$('#pertinenzeDiv'+id).hide();
			}
		});

		$("select[id^='categoria']").change(function(){

			var idName=$(this).attr("id");
			var tmp=idName.split("categoria");
			var id=tmp[1];
			var value=$('#'+idName+' option:selected').text();
			var valoreSelectCategoria = $("#categoria"+id).val();
			
			console.log("valoreSelectCategoria  : "+valoreSelectCategoria);

			setLists(id,valoreSelectCategoria);

				if(value.indexOf("pertinenze")>-1){
					console.log( "id: "+id);
					$('#pertinenzeDiv'+id).show();
				}else{
					$('#pertinenzeDiv'+id).hide();
				}
			return false;

		});
		
		$("input[name^='calcoloParziale']").click(function(){
			var idName=$(this).attr("id");
			var tmp=idName.split("_");
			var id=tmp[1];
			console.log("idName= "+idName+" - id splittato= "+id);
			var formid= '${idForm}';
			console.log(formid);
			$('#numeroDivCalcoloParziale').val(id.trim());
			console.log("numeroDivCalcoloParziale = "+$('#numeroDivCalcoloParziale').val());
			$('#'+formid).submit();
			
		});
		
		$("#nuovoImmobile").click(function(){
			$("#flagNuovoImmobile").val("true");
			$("#contenitoreFormInserimento").show();

		});

		
		$("#chiudiInserimento_new").click(function(){
			var formid= '${idForm}';
			$("#numeroDivCalcoloParziale").val("reset");
			$("#categorianew").val('');
			$("#flagNuovoImmobile").val("false");
			$('#'+formid).submit();
		});

		$("legend[id^=legend]").click(function(){
			$(this).next().toggle();
		});


		$("#generaf24").click(function(){
			/*
			var flag=true;
			$("input[id*=dapagar]").each(function(){
				var value=$(this).val();
				if(value!=""){
					flag=false;
					return false;
				}
			});*/
			var flag=controlF24button();
			if(flag==true){
				alert("Censire almeno un immobile!");
				return false;
			}else{
				return true;
			}
			
		});
		
		/*
		*Per poter generare un modello F24
		* TRUE=pulsante disabilitato
		*  FALSE=pulsante abilitato
		*/
		function disabledF24button(){
			var flag=true;
			$("input[id*=dapagar]").each(function(){
				var value=$(this).val();
				if(value!=""){
					flag=false;
					return false;
				}
			});
			
			return flag;
		}
		
		/*Questo metodo effettua una chiamata ajax per recuperare le liste piu
		appropriate in base alla categoria scelta*/
		
		function setLists(id,valoreSelectCategoria){
			
			var url='${caricaSelectByCategoriaURL}';
			var data = '&valoreSelectCategoria='+valoreSelectCategoria;
			var datiRiepilogo = $.parseJSON('${immobiliJson}');
			console.log(datiRiepilogo);

			if(valoreSelectCategoria == ""){
				
					//set aliquota
					$("#aliquota"+id).val("");
					
					//set liste agevolazioni
					$("#agevolazioni"+id).empty();
				        $("#agevolazioni"+id).append(
				        	$('<option/>').val('NONE').text('NO')
				        		);

					$("select[name*='pertAgevolazioneC']").each(function(){
						$(this).empty();
						var name = $(this).attr("name");
				        $("select[name='"+name+"']").append(
					        	$('<option/>').val('NONE').text('NO')
		        		);
					});
					
						
					
					//set lista detrazioni
					$("#detrazioni"+id).empty();
			        $("#detrazioni"+id).append(
				        	$('<option/>').val('NONE').text('NO')
	        		);

					
					//set lista esenzioni
					$("#esenzioni"+id).empty();
			        $("#esenzioni"+id).append(
				        	$('<option/>').val('NONE').text('NO')
	        		);

				
			}else{
				
				$.ajax({
					url : url,
					method : "GET",
					dataType : "json",
					data : data,
					success : function(data) {
						console.log("TORNATO");
						if (data.isSuccess == "OK"){
							console.log("isSuccess: "+data.isSuccess);
			 				console.log("message: "+data.message);
			 				console.log("aliquota: "+data.aliquota);
		
			 				//set aliquota
			 				$("#aliquota"+id).val(data.aliquota);
			 				
				 				//set liste agevolazioni
				 			if(data.listaAgevolazioni!=null){
				 					$("#agevolazioni"+id).empty();
							        $("#agevolazioni"+id).append(
								        	$('<option/>').val('NONE').text('NO')
					        		);
					 				$.each(data.listaAgevolazioni, function(key, value) {   
					 			        $("#agevolazioni"+id).append(
					 			            $('<option/>').val(value.id).text( value.descrizione )
					 			        );
					 				  });
				 				
	
				 				$("select[name*='pertAgevolazioneC']").each(function(){
				 					$(this).empty();
				 					var name = $(this).attr("name");
				 			        $("select[name='"+name+"']").append(
				 			        		$('<option/>').val('NONE').text('NO')
				 						);
				 					$.each(data.listaAgevolazioni, function(key, value) {   
					 			        $("select[name='"+name+"']").append(
					 			            $('<option/>').val(value.id).text( value.descrizione )
				 						);
				 					});
				 				});
			 				
			 				}
			 					
							if(data.listaDetrazioni!=null){		 				
				 				//set lista detrazioni
				 				$("#detrazioni"+id).empty();
			 			        $("#detrazioni"+id).append(
			 			        		$('<option/>').val('NONE').text('NO')
			 						);
				 				$.each(data.listaDetrazioni, function(key, value) {   
				 			        $("#detrazioni"+id).append(
				 			            $('<option/>').val(value.id).text( value.descrizione )
				 			        );
				 			    });
							}	
							
							if(data.listaEsenzioni!=null){		 				
				 				//set lista esenzioni
				 				$("#esenzioni"+id).empty();
			 			        $("#esenzioni"+id).append(
			 			        		$('<option/>').val('NONE').text('NO')
			 						);
				 				$.each(data.listaEsenzioni, function(key, value) {   
				 			        $("#esenzioni"+id).append(
				 			            $('<option/>').val(value.id).text( value.descrizione )
				 			        );
				 			    });
							}	
			 				if(id != "new" && datiRiepilogo[id] != null){
				 				if(datiRiepilogo[id].agevolazione != ""){
				 					$("#agevolazioni"+id).val(datiRiepilogo[id].agevolazione);
				 				}
				 				if(datiRiepilogo[id].pertAgevolazioneC2 != ""){
				 					$("#agevolazioniC2"+id).val(datiRiepilogo[id].pertAgevolazioneC2);
				 				}
				 				if(datiRiepilogo[id].pertAgevolazioneC6 != ""){
				 					$("#agevolazioniC6"+id).val(datiRiepilogo[id].pertAgevolazioneC6);
				 				}
				 				if(datiRiepilogo[id].pertAgevolazioneC7 != ""){
				 					$("#agevolazioniC7"+id).val(datiRiepilogo[id].pertAgevolazioneC7);
				 				}
				 				if(datiRiepilogo[id].detcasa){
				 					$("#detrazioni"+id).val(datiRiepilogo[id].detcasa);
				 				}
				 				if(datiRiepilogo[id].esenzione){
				 					$("#esenzioni"+id).val(datiRiepilogo[id].esenzione);
				 				}
				 				
			 				}
							//alert("OK");
						}
						else {
							//alert("KO");
						}
		
					}
				,error:function(msg){
					console.log("ERRORE");
					console.log("msg: "+msg);
					
				}
				
				});
		  }		
		}
		
	});
</script>