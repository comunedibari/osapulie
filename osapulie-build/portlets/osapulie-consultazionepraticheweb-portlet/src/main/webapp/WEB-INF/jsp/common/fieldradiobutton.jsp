<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
   <input type="radio" name="${nomeCampo }" value="${tokenName}" <c:if test="${valoreCampo == tokenName}">checked</c:if> />&nbsp;${tokenName}<br/>
</c:forTokens>
<form:errors path="${nomeCampo }" cssStyle="color:red"/>