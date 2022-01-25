<%@page import="it.osapulie.shared.service.SegnalazioneFoglia"%>
<%@page import="it.osapulie.shared.service.Segnalazione"%>

<%
String indice = String.valueOf(request.getAttribute("indice"));
request.removeAttribute("indice");
if(request.getAttribute("rigaSegnalazione_"+indice) != null){
	SegnalazioneFoglia segnalazione = (SegnalazioneFoglia) request.getAttribute("rigaSegnalazione_"+indice);
	request.removeAttribute("rigaSegnalazione_"+indice);
	if(segnalazione.isFoglia()){
%>
		<tr>
			<td><input type="checkbox" name="check_<%=indice %>" id="check_<%=indice %>" value="<%=segnalazione.isCheck()%>" <%=segnalazione.isCheck() ? "checked" : "" %> onclick="toggleStatus('#check_<%=indice %>','#valore_new_<%=indice %>');togglerRowStatus('#check_<%=indice %>','#valore_new_<%=indice %>');"/></td>
			<td><div><%=segnalazione.getChiave()%></div></td>
			<td width="170px"><%=segnalazione.getValore_old()%></td>
			<td width="130px"><input type="text" name="valore_new_<%=indice %>" id="valore_new_<%=indice %>" value="<%=segnalazione.getValore_new()%>"  class="inputTextNewValue <%=segnalazione.isCheck() ? "inputTextNewValueChecked" : "" %>"/></td>
		</tr> 
<%
	}else{
%>
		<tr>
			<td colspan="4" align="center">
			<%
			if(indice.equals("0")){
			%>
				<b>
			<%		
			}
			%>
				<%=segnalazione.getChiave()%>
			<%
			if(indice.equals("0")){
			%>
				</b>
			<%		
			}
			%>	
			</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3">
				<table class="noPadding" id="elencoSegnalazioni_<%=indice %>">
					<tbody> 
						<%
							int var = 0;
							for(SegnalazioneFoglia foglia :segnalazione.getListaSegnalazioni()){
									request.setAttribute("indice", indice+"_"+var+"");
								request.setAttribute("rigaSegnalazione_"+indice+"_"+var, foglia);
								%>
								<jsp:include page="rigoElencoSegnalazioni.jsp"/>
							<%
							var++;
							}
					 	%>	
					</tbody>
				</table>
			</td>
		</tr> 	 		
<%
	}
}
%>