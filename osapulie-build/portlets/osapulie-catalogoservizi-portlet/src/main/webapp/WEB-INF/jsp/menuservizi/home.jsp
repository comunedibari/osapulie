<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@ include file="../common/common.jsp"%>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>
<div class="mainDiv">
	<%
	String tipologiaParam = PortalUtil.getOriginalServletRequest(request).getParameter("tipologia");
	String areaTematicaParam = PortalUtil.getOriginalServletRequest(request).getParameter("areaTematica");
	String comuneParam = PortalUtil.getOriginalServletRequest(request).getParameter("comune");
	%>
	<c:set var="tipologiaParam" value="<%=tipologiaParam %>" />
	<c:set var="areaTematicaParam" value="<%=areaTematicaParam %>" />
	<c:set var="comuneParam" value="<%=comuneParam %>" />
	
	<div class="serviziPerTipologia">
		<div class="titoletto"><spring:message code="label.serviziPerTipologia"/></div>
		<ul>
			<c:forEach items="${tipiServizio}" var="tipoServizio" varStatus="count">
				<li id="tipoServzizio_${count.index}">
					<a <c:if test="${tipologiaParam == tipoServizio.id}">class="active"</c:if> href="${currentUrl}?tipologia=${tipoServizio.id}"><i class="fa fa-angle-double-right"></i>&nbsp;${tipoServizio.nome}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="serviziPerAreaTematica">
		<div class="titoletto"><spring:message code="label.serviziPerAreaTematica"/></div>
		<ul>
			<c:forEach items="${areeTematiche}" var="areaTematica" varStatus="count">
				<li id="areaTematica_${count.index}">
					<a <c:if test="${areaTematicaParam == areaTematica.id}">class="active"</c:if> href="${currentUrl}?areaTematica=${areaTematica.id}"><i class="fa fa-angle-double-right"></i>&nbsp;${areaTematica.nome}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<c:if test="${not empty comuni}">
		<div class="serviziPerComune">
			<div class="titoletto"><spring:message code="label.serviziPerComune"/></div>
			<ul>
				<c:forEach items="${comuni}" var="comune" varStatus="count">
					<li id="comune_${count.index}">
						<a <c:if test="${comuneParam == comune.id}">class="active"</c:if> href="${currentUrl}?${InnerPortletConstants.PARAMETRO_ID_COMUNE}=${comune.id}"><i class="fa fa-angle-double-right"></i>&nbsp;${comune.nome}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</div>