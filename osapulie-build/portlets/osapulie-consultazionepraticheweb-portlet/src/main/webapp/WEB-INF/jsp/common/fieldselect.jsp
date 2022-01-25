<form:select path="${nomeCampo }" cssStyle="width:250px;">
<c:set var="check" value="" scope="page"/>
	<form:option value=""><spring:message code="label.select" /></form:option>
	
	<c:forEach var="item1" begin="0" items="${valoriCampo}">		
    	<form:option value="${item1}" >${item1}</form:option>
	</c:forEach>
</form:select>
<form:errors path="${nomeCampo }" cssStyle="color:red"/>