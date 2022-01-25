<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="visuraPosizioneTributaria">
		<portlet:param name="action" value="visura" />		
</portlet:actionURL>

<div class="mainDiv">
	<form:form id="visura" action="${visuraPosizioneTributaria}" method="post" cssClass="printForm"> 
		<div class="marginBottom10">
			<label><spring:message code="label.annoInizio" />:</label>&nbsp;
			<select name="startYear">
			  <c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
			      <option value="${i}" <c:if test="${i == startYear}"> selected="selected" </c:if> >${i}</option>
			   </c:forEach>
			</select>
			<label><spring:message code="label.annoFine" />:</label>&nbsp;
			<select name="endYear">
			  <c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
			      <option value="${i}" <c:if test="${i == datiRimbCosapTosap.anno}"> selected="selected" </c:if> >${i}</option>
			   </c:forEach>
			</select>
			<input type="submit" name="invia" value='<spring:message code="button.cerca" />'/>
		</div>
	</form:form>
</div>