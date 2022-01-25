<%@page import="it.osapulie.shared.service.SegnalazioneFoglia"%>
<%@page import="it.osapulie.shared.service.Segnalazione"%>

<%
if(request.getAttribute("rigaSegnalazione") != null){
	SegnalazioneFoglia segnalazione = (SegnalazioneFoglia) request.getAttribute("rigaSegnalazione");
	request.removeAttribute("rigaSegnalazione");
	if(segnalazione.isFoglia() && segnalazione.isCheck()){
%>
	<li style="font-size:12px; ">
		<strong><%=segnalazione.getChiave()%></strong>
		<strike style="color: red;"><b><%=segnalazione.getValore_old()%></b></strike>
		<b style="color: green;"><%=segnalazione.getValore_new()%></b>
	</li>
 
<%
	}else if(segnalazione.isCheck()) {
		if(segnalazione.getChiave()!=null && !segnalazione.getChiave().equals("")){
%>
		<li><strong><%=segnalazione.getChiave()%></strong>
<%
		}
%>
			<ul>
<%
		for(SegnalazioneFoglia foglia :segnalazione.getListaSegnalazioni()){
			if(foglia.isCheck()){
				request.setAttribute("rigaSegnalazione", foglia);
%>
 				<jsp:include page="rigoInvioSegnalazioni.jsp"/>
<%
			}else if(!foglia.isCheck() && foglia.isFoglia()){
%>
				<li><%=foglia.getChiave()%> <%=foglia.getValore_old()%></li>
<%				
			}
		}
%>
			</ul>
<%
		if(segnalazione.getChiave()!=null && !segnalazione.getChiave().equals("")){
%>
		</li>
<%
		}
	}
}
%>