<%@ include file="../common/common.jsp"%>

<script src="<%=request.getContextPath(  ) %>/javascript/funzioniTab.js" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/funzioni.js" type="text/javascript"></script>

<portlet:actionURL var="uploadDomandaUrl">
	<portlet:param name="ope" value="upload" />
</portlet:actionURL>
<portlet:renderURL var="annullaUrl" />

<div class="mainDiv">
<form:form id="editForm" modelAttribute="uploadItem" action="${uploadDomandaUrl}" method="post"	enctype="multipart/form-data">
	<fieldset>
		<legend>
			<spring:message code="label.legendUpload" />
		</legend>
		<table>
				<tr>
					<td>
						<spring:message code="label.domanda"/>:*&nbsp;
					</td>
					<td>
						<c:if test="${ empty uploadItem.documento}">
							<form:input path="generatedFile" type="file" />&nbsp;&nbsp;&nbsp;
							<form:errors path="generatedFile" cssStyle="color:red"/>
						</c:if>
						<c:if test="${! empty uploadItem.documento && !empty uploadItem.documento.uuid && uploadItem.documento.uuid != ''}">
										<br />
										<portlet:resourceURL var="downloadAllegato" id="downloadAllegato">
											<portlet:param name="uuidAllegato" value="${uploadItem.documento.uuid }" />
											<portlet:param name="fileNameAllegato" value="${uploadItem.documento.file.nome }" />
										</portlet:resourceURL>
										<a href="${downloadAllegato}" target="_blank"><spring:message code="link.download" /></a>
									
								</c:if>						
					</td>
				</tr>
			</table>
		</fieldset>					
				<br />	
				<%@ include file="../common/footer.jsp"%>
				<br/>		
				<div class="buttonsDiv">
					<a href="javascript:if(confirm('<spring:message code="message.confirmSend" />')) formLoading('editForm');" ><spring:message code="button.invia" /></a>
					<a href="${annullaUrl}" id="annulla" title="<spring:message code="button.annulla" />">
						<spring:message code="button.annulla" />
					</a>
				</div>	
</form:form>
</div>