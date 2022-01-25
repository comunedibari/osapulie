<%@ include file="../common/common.jsp"%>

<script type="text/javascript">
$(function(){
    $("#tree").dynatree({
    	clickFolderMode: 3,
    	fx: { height: "toggle", duration: 200 },
        autoCollapse: true,
        onCreate: function(node) {
			if (node.data.tipoPratica != null) {
				var tp = node.data.tipoPratica;
				list = [];
				for (var i=0, l=tp.length; i<l; i++) {
					var e = tp[i];
					list.push({title: e.title, id: e.id});
				}
				node.addChild(list);
			}		
		},
    	onActivate: function(node) {
    		var s = new Array();
    		node.visitParents(function(node) {
    			if (node.data.title != null) {
    				if (node.parent) {
                        s.unshift(node.data.title);
                	} 
    			}
    		}, true);
    		var res = s.join(" - ");
            $("#descrizione").text(res);
            $("#descrizione").val(node.data.title);
            $("#tipologia").val(node.data.id);
		},
        onDeactivate: function(node) {
        	$("#descrizione").text("");
            $("#descrizione").val("");
            $("#tipologia").val("");
		},
        children: ${elencoCategoriePraticheJson}
    });
  });
</script>
 

<!-- avanti -->
<portlet:actionURL var="inserisciUrl">
	<portlet:param name="action" value="newPraticaForm" />
</portlet:actionURL>

<portlet:renderURL var="annullaUrl" >	
</portlet:renderURL>
<portlet:renderURL var="home">
	<portlet:param name="action" value="home" />
</portlet:renderURL>

<div class="mainDiv">
	<fieldset>
			<legend><spring:message code="label.selezionaTipoPratica"/></legend>
			<form:form id="selectForm" commandName="datiPratica" action="${inserisciUrl}" method="post">				
				<table>					
					<tr>
						<!-- <td style="width:100px">
							<spring:message code="label.tipologia"/>:*&nbsp;
						</td> -->
						<td><span id="descrizione" name="descrizione"> - </span><br/>
						<form:hidden path="tipologia"/>
				<div id="tree"></div>	
						</td>
					</tr>					
				</table>
				<br />	
					<%@ include file="../common/footer.jsp"%>
				<br />	
				<div class="buttonsDiv">
					<input type="submit" value="<spring:message code="button.avanti" />" />					
						<a href="${annullaUrl}" title="<spring:message code="button.annulla" />">
							<spring:message code="button.annulla" />
						</a>
				</div>	
		</form:form>		
	</fieldset>			
</div>