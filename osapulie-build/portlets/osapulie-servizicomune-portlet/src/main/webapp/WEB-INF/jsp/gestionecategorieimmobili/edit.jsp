<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<portlet:actionURL var="saveEntityUrl">
	<portlet:param name="action" value="save" />
</portlet:actionURL>

<div class="mainDiv">
	<c:choose>
		<c:when test="${access}">
			<form:form id="saveForm" commandName="categoriaImmobileForm" method="post" action="${saveEntityUrl}">
				<form:input type="hidden"  value="${categoriaImmobileForm.anno}" path="anno" id="anno"/>
				<form:input type="hidden" path="idCategoriaImmobile"/>
				<div class="selectCategoriaImmobileDiv">
					<label for="selectCategoriaImmobile"><spring:message code="label.selezionaCategoriaImmobile"/>:</label>
					
					<form:select id="selectCategoriaImmobile" path="idTipologiaCategoriaImmobile" >
						<form:option value="0"><spring:message code="label.seleziona" /></form:option>
						<form:options items="${tipologieCategorieImmobili}" itemLabel="descrizione" itemValue="id"/>
					</form:select>
					<br>
					<form:errors path="idTipologiaCategoriaImmobile" cssStyle="color:red"/>
				</div>
				<br>
				<div class="selectBaseCalcoloDiv">
					<label for="selectBaseCalcolo"><spring:message code="label.baseDiCalcolo"/>:</label>
					
					<form:select id="selectBaseCalcolo" path="idBaseCalcolo" >
						<form:option value=""><spring:message code="label.seleziona" /></form:option>
						<c:forEach items="${basiDiCalcolo}" var="baseDiCalcolo">
							<form:option value="${baseDiCalcolo.id}">
								<c:out value="${baseDiCalcolo.descrizione} - ${baseDiCalcolo.valore}"/>
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="idTipologiaCategoriaImmobile" cssStyle="color:red"/>
				</div>
				<br>
				<div>
					<label for="coefficienteMoltiplicazione"><spring:message code="label.coefficienteMoltiplicazione"/>:</label>
					<form:input path="coefficienteMoltiplicazione"/>
					<form:errors path="coefficienteMoltiplicazione" cssStyle="color:red"/>
				</div>
				<br>
				<div class="tributiDiv">
					<c:forEach items="${categoriaImmobileForm.tributi}" var="tributo" varStatus="status">
						<form:input type="hidden"  value="${tributo.idTributo}" path="tributi[${status.index}].idTributo" name="tributi[${status.index}].idTributo"/>
						<form:input type="hidden" value="${tributo.nome}" path="tributi[${status.index}].nome" name="tributi[${status.index}].nome"/>
						<div class="tributoDiv">
							<fieldset>
								<legend><c:out value="${tributo.nome}"/></legend>
								<label for="tributi[${status.index}].aliquota"><spring:message code="label.aliquota"/>:</label>
								<form:input id="tributi[${status.index}].aliquota" path="tributi[${status.index}].aliquota"/>
								<div class="addEsenzioneDiv${status.index}">
									<a href="#" onclick="addEsenzione(${status.index});return false;"><i class="fa fa-plus-circle"></i>&nbsp;&nbsp;<spring:message code="label.aggiungiEsenzione"/></a>
								</div>
								<div id="esenzioneDiv${status.index}" class="esenzioniDiv">
									<c:forEach items="${tributo.esenzioni}" var="esenzione" varStatus="esenzioneStatus">
										<div id="esenzione_${status.index}_${esenzioneStatus.index}">
											<form:input path="tributi[${status.index}].esenzioni[${esenzioneStatus.index}].descrizione"/>&nbsp;
											<img onclick="deleteDiv('esenzione_${status.index}_${esenzioneStatus.index}');return false" class="deleteImg" src="<%=request.getContextPath() %>/images/imgo.jpg" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />"/>
										</div>
									</c:forEach>
								</div>
								<div class="addAgevolazioneDiv${status.index}">
									<a href="#" onclick="addAgevolazione(${status.index});return false;"><i class="fa fa-plus-circle"></i>&nbsp;&nbsp;<spring:message code="label.aggiungiAgevolazione"/></a>
								</div>
								<div id="agevolazioneDiv${status.index}" class="agevolazioniDiv">
									<c:forEach items="${tributo.agevolazioni}" var="agevolazione" varStatus="agevolazioneStatus">
										<div id="agevolazione_${status.index}_${agevolazioneStatus.index}">
											<form:input path="tributi[${status.index}].agevolazioni[${agevolazioneStatus.index}].nome"/>&nbsp;&nbsp;&nbsp;
											<form:input path="tributi[${status.index}].agevolazioni[${agevolazioneStatus.index}].valore"/>&nbsp;
											<img onclick="deleteDiv('agevolazione_${status.index}_${agevolazioneStatus.index}');return false" class="deleteImg" src="<%=request.getContextPath() %>/images/imgo.jpg" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />"/>
										</div>
									</c:forEach>
								</div>
								<div class="addDetrazioneDiv${status.index}">
									<a href="#" onclick="addDetrazione(${status.index},'${listaTipologieDetrazioni}');return false;"><i class="fa fa-plus-circle"></i>&nbsp;&nbsp;<spring:message code="label.aggiungiDetrazione"/></a>
								</div>
								<div id="detrazioneDiv${status.index}" class="detrazioniDiv">
									<c:forEach items="${tributo.detrazioni}" var="detrazione" varStatus="detrazioneStatus">
										<div id="detrazione_${status.index}_${detrazioneStatus.index}">
											<form:input path="tributi[${status.index}].detrazioni[${detrazioneStatus.index}].descrizione"/>&nbsp;&nbsp;&nbsp;
											<form:select id="tributi[${status.index}].detrazioni[${detrazioneStatus.index}]" path="tributi[${status.index}].detrazioni[${detrazioneStatus.index}].idTipologiaDetrazione">
												<form:option value=""><spring:message code="label.seleziona" /></form:option>
												<form:options items="${tipologieDetrazioni}" itemLabel="nome" itemValue="id"/>
											</form:select>
											&nbsp;&nbsp;&nbsp;
											<form:input path="tributi[${status.index}].detrazioni[${detrazioneStatus.index}].importo"/>&nbsp;
											<img onclick="deleteDiv('detrazione_${status.index}_${detrazioneStatus.index}');return false" class="deleteImg" src="<%=request.getContextPath() %>/images/imgo.jpg" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />"/>
										</div>
									</c:forEach>
								</div>
							</fieldset>
						</div>
					</c:forEach>
				</div>
				<div class="container_pulsante">
					<input name="Annulla" type="submit" value="<spring:message code="button.annulla" />"/>
					<span class="spacer"></span>
					<input name="Salva" type="submit" value="<spring:message code="button.salva" />" onclick="javascript:return(confirm('<spring:message code="message.confirmSave" />'))"/>
				</div>
			</form:form> 
		</c:when>
		<c:otherwise>
			<spring:message code="label.accessDenied"/>.
		</c:otherwise>
	</c:choose>
</div>