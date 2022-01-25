<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="uploadDichiarazioneUrl">
	<portlet:param name="ope" value="upload" />
</portlet:actionURL>
<div id="divLoading"> 
</div>
<%@ include file="./uploadfile.jsp"%>