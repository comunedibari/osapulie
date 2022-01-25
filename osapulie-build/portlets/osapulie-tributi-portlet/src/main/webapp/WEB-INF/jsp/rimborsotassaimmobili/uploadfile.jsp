<form:form modelAttribute="uploadItem" action="${uploadDichiarazioneUrl}" method="post"	enctype="multipart/form-data">
	<fieldset>
		<legend>
			<spring:message code="label.legendUpload" />
		</legend>
		<c:choose>
			<c:when test="${selectNumAllegati != null}">	
				<fieldset>
					<legend>
						<spring:message code="label.documentoGenerato" />*
					</legend>
					<table class="dichiarazioneFileTable">
						<tr>
							 <td>
							  <spring:message code="label.firmatoDigitalmente" />
								<form:radiobutton  path="signedFiles" value="false" /> <spring:message code="label.no"/>
								<form:radiobutton path="signedFiles" value="true"/> <spring:message code="label.si"/>
							 </td>
						</tr>
						<tr>
							<td>
								<form:input path="generatedFile" type="file" />&nbsp;&nbsp;&nbsp;
								<form:errors path="generatedFile" cssStyle="color:red"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<c:if test="${not empty uploadItem.multipartFiles}">
					<fieldset>
						<legend>
							<spring:message code="label.altriAllegati" />
						</legend>
						<table class="altriFileTable">
							<c:set var="i" value="0" scope="page" />
							<c:forEach var="item" items="${uploadItem.multipartFiles}">
								<tr>
									<td>
										<c:if test="${not empty descrAllegati[i]}">- ${descrAllegati[i]}<br/></c:if>
										<form:input path="multipartFiles" type="file" />
										&nbsp;&nbsp;&nbsp;
								<form:errors path="multipartFiles[${i}]" cssStyle="color:red"/>
									</td>
								</tr>
								<c:set var="i" value="${i+1}" scope="page" />	
							</c:forEach>
						</table>
					</fieldset>
				</c:if>		
				<br />	
				<%@ include file="../common/footer.jsp"%>
				<br/>		
				<div class="container_pulsante">
					<input type="submit" name="invia" value="Invia" />
					<a class="custom_pulsante" href="<portlet:renderURL portletMode="view"/>"><spring:message code="button.annulla" /></a>
				</div>
			</c:when>
			<c:otherwise>	
				<div class="descriptionDiv">
					<spring:message code="label.allegati.dichiarati" />
					<ul>
						<c:if test="${fn:length(fn:trim(datiRimbTassaImmobili.allegatoUno)) != 0}"><li>- ${datiRimbTassaImmobili.allegatoUno}</li></c:if>
						<c:if test="${fn:length(fn:trim(datiRimbTassaImmobili.allegatoDue)) != 0}"><li>- ${datiRimbTassaImmobili.allegatoDue}</li></c:if>
						<c:if test="${fn:length(fn:trim(datiRimbTassaImmobili.allegatoTre)) != 0}"><li>- ${datiRimbTassaImmobili.allegatoTre}</li></c:if>
						<c:if test="${fn:length(fn:trim(datiRimbTassaImmobili.allegatoQuattro)) != 0}"><li>- ${datiRimbTassaImmobili.allegatoQuattro}</li></c:if>
						<c:if test="${fn:length(fn:trim(datiRimbTassaImmobili.allegatoUno)) == 0 && fn:length(fn:trim(datiRimbTassaImmobili.allegatoDue)) == 0 && fn:length(fn:trim(datiRimbTassaImmobili.allegatoTre)) == 0 && fn:length(fn:trim(datiRimbTassaImmobili.allegatoQuattro)) == 0}"><li>- Nessuno</li></c:if>
					</ul>
				</div>
				<br/>

				<div class="descriptionDiv">
					<spring:message code="label.selezionaNumeroAllegatiDichiarazione" />
				</div>
				<br/>
				
				
				<div class="selectNumAllegatiDiv">
					<select name="numAllegatiSelect">
						<option value="0"><spring:message code="label.select.nessuno" /></option>
						<c:forEach var="item" begin="1" end="10"  varStatus="status">
							<option value="${item}"><c:out value="${item}"/></option>
						</c:forEach>
					</select>
					<input type="submit" name="selectNumAllegati" value="Seleziona" />
				</div>							
			</c:otherwise>
		</c:choose>
	</fieldset>	
</form:form>