<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="visuraAvvisiBonariAction">
	<portlet:param name="action" value="visura" />
</portlet:actionURL>

<div class="mainDiv">
	<form:form id="visura" action="${visuraAvvisiBonariAction}" method="post" cssClass="printForm">
		<div class="marginBottom10">
		<label><spring:message code="label.anno" />:</label>&nbsp;
			<select name="year">
				<c:forEach var="i" begin="${annoCorrente-5}" end="${annoCorrente}" step="1">
					<option value="${i}" <c:if test="${i == year}"> selected="selected" </c:if> >${i}</option>
				</c:forEach>
			</select>
			<input type="submit" name="invia" value='<spring:message code="button.cerca" />' />
		</div>
	</form:form>
</div>