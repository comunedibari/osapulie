<div id="contenitoreFormInserimento">
					<fieldset>
						<legend>
							<spring:message code="label.formInserimento"/>
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
										  <form:input id="ni_indirizzo" path="indirizzo" size="30" />
										   <div><form:errors path="indirizzo" cssStyle="color:red"/></div>	
										</td>
									 </tr>
									 <tr>
										<td width="180px">
										  <label>
											<spring:message code="label.foglioCatastale" />:
										  </label>
										</td>
										<td width="180px">
										  <form:input id="ni_foglio" path="foglio" size="8" />
										   <div><form:errors path="foglio" cssStyle="color:red"/></div>
										</td>
										<td width="180px">
										  <label>
											<spring:message code="label.particella" />:
										  </label>
										 </td>
										 <td>
										   <form:input id="ni_num" path="num" size="8" />
											<div><form:errors path="num" cssStyle="color:red"/></div>
										 </td>
										  <td>
											<label>
											  <spring:message code="label.sezione" />:
											</label>
										  </td>
										   <td>
											 <form:input id="ni_sezione" path="sezione" size="8"/>
											 <div><form:errors path="sezione" cssStyle="color:red"/></div>
											</td>
										</tr>
										<tr>
											<td colspan="6" align="center">
													<label>
														<spring:message code="label.calcola" />
													</label>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<spring:message code="label.cat" />:
													</label>
												</td>
												<td colspan="5" id="tdSelectCategoria_new">
													<form:select name="categoria" id="categorianew" path="categoria">
														<form:option value="" label="--- Select ---"/>
		   												<form:options items="${listaCategorieImmobili}" itemLabel="tipologiaCategoriaImmobile.descrizione" itemValue="id"  />
		   											</form:select>	

													<div><form:errors path="categoria" cssStyle="color:red"/></div>
												</td>
											</tr>
											
											<tr>
												<td>
													<label>
														<spring:message code="label.valore" />:
													</label>
												</td>
												<td colspan="5">
													<form:input path="valoreim" id="valoreimnew" size="8" />
													<div><form:errors path="valoreim" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>	
												<td>
													<label>
														<spring:message code="label.possessoPerc" />:
													</label>
												</td>
												<td>
													<form:input path="quota" id="quotanew" size="10" />
													<div><form:errors path="quota" cssStyle="color:red"/></div>
												</td>
												<td>
													<label>
														<spring:message code="label.possessoMesi" />:
													</label>
												</td>
												<td colspan="4">
													<form:input path="quotaMesi" id="quotaMesinew" size="10" />
													<div><form:errors path="quotaMesi" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<spring:message code="label.aliquota" />:
													</label>
												</td>
												<td colspan="5">
													<form:input path="aliquota"  id="aliquotanew" size="8" readonly="true" />
													<div><form:errors path="aliquota" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
											  <td>
												<label>
												  <spring:message code="label.agevolazioni" />:
												</label>
											  </td>
											  <td colspan="5">
												<form:select id="agevolazioninew" name="agevolazione" path="agevolazione" style="width:200px" >
												 <c:choose>
													 <c:when test="${listaAgevolazioni!=null}">
			   											 <form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
			   										</c:when>
													 <c:otherwise>
														 <form:option value="NONE" label="NO"/>
													 </c:otherwise>
														</c:choose>
		   											</form:select>	
		   											
		   											<strong><em><form:errors path="agevolazione" cssStyle="color:red"/></em></strong>
												</td>
											</tr>
										<tr>
										  <td>
											<label>
											  <spring:message code="label.det" />:
											</label>
										  </td>
										  <td colspan="5">
											<form:select id="detrazioninew" name="detcasa" path="detcasa" style="width:200px" >
											  <form:option value="NONE" label="NO"/>
		   										<c:if test="${listaDetrazioni!=null}">
		   											<form:options items="${listaDetrazioni}" itemLabel="descrizione" itemValue="id"  />
		   										</c:if>
		   									  </form:select>	
											 <div><form:errors path="detcasa" cssStyle="color:red"/></div>
										  </td>
										</tr>	
										
										<tr>
										 <td>
										   <label>
											 <spring:message code="label.esenzione" />:
											</label>
										  </td>
										  <td colspan="5">
											<form:select id="esenzioninew" name="esenzione" path="esenzione" style="width:200px" >
											  <form:option value="NONE" label="NO"/>
		   										<c:if test="${listaEsenzioni!=null}">
		   											<form:options items="${listaEsenzioni}" itemLabel="descrizione" itemValue="id"  />
		   										</c:if>
		   									  </form:select>	
											 <div><form:errors path="esenzione" cssStyle="color:red"/></div>
										  </td>
									   </tr>	
									   </table>
									   <div id="pertinenzeDivnew" style="display:none" >
											   <table class="genericTable" >
											   	   <tr id="trPertinenze_intestazione_new" style="background:#FAFAFA;" >
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
											<tr id="trPertinenze_rendita_new" style="background:#FAFAFA;">
												<td>
												 <label>
													<spring:message code="label.valore" />:
												 </label>
													</td>
													<td>
														<form:input path="pertRenditaCatC2" id="ni_pertRenditaCatC2" size="8" />
														<div><form:errors path="pertRenditaCatC2" cssStyle="color:red"/></div>
													</td>
													<td>
														<form:input path="pertRenditaCatC6"  id="ni_pertRenditaCatC6" size="8" /> 
														<div><form:errors path="pertRenditaCatC6" cssStyle="color:red"/></div>
													</td>
													<td colspan="3">
														<form:input path="pertRenditaCatC7"  id="ni_pertRenditaCatC7" size="8" />
														<div><form:errors path="pertRenditaCatC7" cssStyle="color:red"/></div>
													</td>
												</tr>
												<tr id="trPertinenze_possesso_new" style="background:#FAFAFA;">
													<td>
														<label>
															<spring:message code="label.possessoPerc" />:
														</label>
													</td>
													<td>
														<form:input path="pertPossessoPercC2" id="ni_pertPossessoPercC2" size="8" /> 
														<div><form:errors path="pertPossessoPercC2" cssStyle="color:red"/></div>
													</td>
													<td>
														<form:input path="pertPossessoPercC6" id="ni_pertPossessoPercC6" size="8" />
														<div><form:errors path="pertPossessoPercC6" cssStyle="color:red"/></div>
													</td>
													<td colspan="3">
														<form:input path="pertPossessoPercC7" id="ni_pertPossessoPercC7" size="8" />
														<div><form:errors path="pertPossessoPercC7" cssStyle="color:red"/></div>
													</td>
												</tr>
												<tr id="trPertinenze_possessoMesi_new" style="background:#FAFAFA;">
													<td>
														<label>
															<spring:message code="label.possessoMesi" />:
														</label>
													</td>
													<td>
														<form:input path="pertPossessoMesiC2" id="ni_pertPossessoMesiC2" size="8" />
														<div><form:errors path="pertPossessoMesiC2" cssStyle="color:red"/></div>
													</td>
													<td>
														<form:input path="pertPossessoMesiC6" id="ni_pertPossessoMesiC6" size="8" />
														<div><form:errors path="pertPossessoMesiC6" cssStyle="color:red"/></div>
													</td>
													<td colspan="3">
														<form:input path="pertPossessoMesiC7" id="ni_pertPossessoMesiC7" size="8" />
														<div><form:errors path="pertPossessoMesiC7" cssStyle="color:red"/></div>
													</td>
												<tr id="trPertinenze_immStorico_new" style="background:#FAFAFA;">
													<td>
														<label>
															<spring:message code="label.agevolazioni" />:
														</label>
													</td>
													<td>
														<form:select id="ni_sel_pertAgevolazioneC2" name="pertAgevolazioneC2" path="pertAgevolazioneC2" style="width:200px" >
															<c:choose>
																<c:when test="${listaAgevolazioni!=null}">
				   													<form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
				   												</c:when>
																<c:otherwise>
																	<form:option value="NONE" label="NO"/>
																</c:otherwise>
															</c:choose>
			   											</form:select>	
			   											<strong><em><form:errors path="pertAgevolazioneC2" cssStyle="color:red"/></em></strong>
													</td>
													<td>
														<form:select id="ni_sel_pertAgevolazioneC6"  name="pertAgevolazioneC6" path="pertAgevolazioneC6" style="width:200px" >
															<c:choose>
																<c:when test="${listaAgevolazioni!=null}">
				   													<form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
				   												</c:when>
																<c:otherwise>
																	<form:option value="NONE" label="NO"/>
																</c:otherwise>
															</c:choose>
			   											</form:select>	
			   											
			   											<strong><em><form:errors path="pertAgevolazioneC6" cssStyle="color:red"/></em></strong>
													</td>
													<td colspan="3">
														<form:select id="ni_sel_pertAgevolazioneC7" name="pertAgevolazioneC7" path="pertAgevolazioneC7" style="width:200px" >
															<c:choose>
																<c:when test="${listaAgevolazioni!=null}">
				   													<form:options items="${listaAgevolazioni}" itemLabel="nome" itemValue="id"  />
				   												</c:when>
																<c:otherwise>
																	<form:option value="NONE" label="NO"/>
																</c:otherwise>
															</c:choose>
			   											</form:select>	
			   											
			   											<strong><em><form:errors path="pertAgevolazioneC7" cssStyle="color:red"/></em></strong>
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
													<form:input id="ni_impostaTasi" path="impostaTasi" size="8" readonly="true" style="background-color:#DDDDDD" />
													<div><form:errors path="impostaTasi" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<spring:message code="label.importoDet"/>:
													</label>
												</td>
												<td colspan="5">
													<form:input id="ni_importoDetrazione" path="importoDetrazione" size="8" readonly="true" style="background-color:#DDDDDD" />
													<div><form:errors path="importoDetrazione" cssStyle="color:red"/></div>
												</td>
											</tr>
											<tr>
		
												<td>
													<label>
														<spring:message code="label.importo"/>:
													</label>
												</td>
												<td colspan="5">
													<form:input id="ni_dapagar" path="dapagar" size="8" readonly="true" style="background-color:#DDDDDD" />
													<div><form:errors path="dapagar" cssStyle="color:red"/></div>
												</td>
												<td>
													<input type="button" id="calcoloParziale_new" name="calcoloParziale_new" value="<spring:message code="button.dichiarazione" />" />
													<input type="button" id="chiudiInserimento_new" name="chiudiInserimento_P" value="<spring:message code="button.annullaImmobile" />" />
												</td>
											</tr> 
								</table>
							</div>
							</fieldset>			
							</div>