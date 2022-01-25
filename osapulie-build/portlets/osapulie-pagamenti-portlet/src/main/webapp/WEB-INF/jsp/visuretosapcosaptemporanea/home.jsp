<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ include file="../common/common.jsp"%>
<portlet:actionURL var="visuraOsapTemporanea">
		<portlet:param name="action" value="visura" />		
</portlet:actionURL>
<%
	Calendar today = new GregorianCalendar();
%>
<div class="mainDiv">
	<form:form id="visura" action="${visuraOsapTemporanea}" method="post" cssClass="printForm"> 
		<div class="marginBottom10">
			<label><spring:message code="label.da"/>:</label>&nbsp;
			<liferay-ui:input-date
				dayParam="dayStart"
				dayValue="<%= today.get(Calendar.DATE) %>"
				disabled="<%= false %>"
				firstDayOfWeek="<%= today.getFirstDayOfWeek() - 1 %>"
				monthParam="monthStart"
				monthValue="<%= today.get(Calendar.MONTH) %>"
				yearParam="yearStart"
				yearValue="<%= today.get(Calendar.YEAR) %>"
				yearRangeStart="<%= today.get(Calendar.YEAR) - 100 %>"
				yearRangeEnd="<%= today.get(Calendar.YEAR) %>"
			/>&nbsp;&nbsp;
			<label><spring:message code="label.a"/>:</label>&nbsp;
			<liferay-ui:input-date
				dayParam="dayEnd"
				dayValue="<%= today.get(Calendar.DATE) %>"
				disabled="<%= false %>"
				firstDayOfWeek="<%= today.getFirstDayOfWeek() - 1 %>"
				monthParam="monthEnd"
				monthValue="<%= today.get(Calendar.MONTH) %>"
				yearParam="yearEnd"
				yearValue="<%= today.get(Calendar.YEAR) %>"
				yearRangeStart="<%= today.get(Calendar.YEAR) - 100 %>"
				yearRangeEnd="<%= today.get(Calendar.YEAR) %>"
			/>&nbsp;&nbsp;
			<input type="submit" name="invia" value="Invia"/>
		</div>
	</form:form>
</div>