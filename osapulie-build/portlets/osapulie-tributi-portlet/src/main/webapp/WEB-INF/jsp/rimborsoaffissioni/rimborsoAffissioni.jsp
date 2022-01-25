<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoUrlGenera">
	<portlet:param name="ope" value="generaRimborso" />
</portlet:actionURL>


<portlet:resourceURL var="rimborsoReportURL"
	id="rimborsoAffissioniReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="rimborsoAffissioni" />
<c:set var="dati" value="${datiRimbAffissioni}" />
<c:choose>
<c:when test="${!empty datiRimbAffissioni}">
<form:form id="${idForm}" action="${rimborsoUrlGenera}" method="post" commandName="datiRimbAffissioni" cssClass="rimborsiForm">
	<c:if test="${empty download}">
		<%@ include file="../common/rimborsiDatiAnagrafici.jsp"%>		
						
		<fieldset>
			<legend>
				<spring:message code="label.legend" />
			</legend>
			<label style="width: 250px"><strong><spring:message
					code="label.anno" />:</strong>
			</label>
			<select name="anno">
					<c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
				    	<option value="${i}" <c:if test="${i == datiRimbAffissioni.anno}"> selected="selected" </c:if> >${i}</option>
					</c:forEach>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="cambio" value="<spring:message code="button.modAnno" />"/><br/>
			<br/>	
			<label style="width: 250px"><strong><spring:message
					code="label.locali" />:</strong>
			</label><br/><br/>
			<table>
				<tr>
					<th>
						<spring:message code="label.num"/>*
					</th>
					<th>
						<spring:message code="label.sup"/>*
					</th>
					<th>
						<spring:message code="label.gg"/>*
					</th>			
					<th>
						<spring:message code="label.dovuto"/>*
					</th>
					<th>
						<spring:message code="label.versato"/>*
					</th>		
				</tr>
				<c:forEach var="i" begin="1" end="5" step="1">
				<tr>
					<td>
						<form:input path="num${i}" /> <strong><em><form:errors path="num${i}" cssStyle="color:red"/></em></strong> 	
					</td>
					<td>
						<form:input path="dim${i}" /> <strong><em><form:errors path="dim${i}" cssStyle="color:red"/></em></strong> 	
					</td>
					<td>
						<form:input path="gg${i}" />	<strong><em><form:errors path="gg${i}" cssStyle="color:red"/></em></strong> 	
					</td>	
					<td>
						<form:input path="dovuto${i}"  /> <strong><em><form:errors path="dovuto${i}" cssStyle="color:red"/></em></strong>
					</td>
					<td>
						<form:input path="versato${i}" /> <strong><em><form:errors path="versato${i}" cssStyle="color:red"/></em></strong>
					</td>				
				</tr>
				</c:forEach>				
			</table>			
			<br /><br/>	
		</fieldset>
		<%@ include file="../common/rimborsiDatiGenerali.jsp"%>
		<br />	
		<%@ include file="../common/footer.jsp"%>
		<br />	
		<div class="container_pulsante">
			<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
		</div>	
		</c:if>
		
		<c:if test="${!empty download}">
			<div class="container_pulsante" >
				<a href="${rimborsoReportURL}" class="custom_pulsante"> 
				   <spring:message code="link.dichiarazione" /> 
				</a>
				<span class="spacer"></span>
				<a href="<portlet:renderURL portletMode="view"/>" class="custom_pulsante"><spring:message code="button.home" /></a>
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