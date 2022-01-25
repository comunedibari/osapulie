<c:forTokens items="${valoriCampo}" delims="," var="tokenName" varStatus="status">
	<c:set var="check" value="" scope="page"/>
	<c:forTokens items="${valoreCampo}" delims="," var="valoriName" varStatus="status">
			<c:if test="${valoriName==tokenName}">
				<c:set var="check" value="checked" scope="page"/>
			</c:if>
	</c:forTokens>	
   <input type="checkbox" name="${nomeCampo }" value="${tokenName}" ${check} />&nbsp;${tokenName}<br/>
</c:forTokens>
<form:errors path="${nomeCampo }" cssStyle="color:red"/>