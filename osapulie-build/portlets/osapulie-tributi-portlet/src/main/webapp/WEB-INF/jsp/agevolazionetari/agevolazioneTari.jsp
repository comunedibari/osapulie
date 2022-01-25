<%@ include file="../common/common.jsp"%>

<portlet:resourceURL var="searchListaComuniEsteriURL" id="searchListaComuniEsteri" escapeXml="false"> 
</portlet:resourceURL>

<portlet:resourceURL var="searchListaComuniURL" id="searchListaComuni" escapeXml="false"> 
</portlet:resourceURL>

<script type="text/javascript">

var isLimitatoIscrizione = "${datiAgevolazioneTari.limitatoIscrizione}" ;

$(document).ready(function(){
	$.datepicker.setDefaults($.datepicker.regional['it']);  
	$(".data_input").datepicker({
		dateFormat: "dd/mm/yy"
	});
	
	$('#comuneNascitaEstero').select2({
		  theme: "classic",
		  language: "it",
		  minimumInputLength: 3,
		  ajax: {
		    url: "${searchListaComuniEsteriURL}",
		    dataType: 'json',
		    delay: 250,
		    data: function (params) {
		      return {
		        q: params.term, // search term
		        page: params.page
		      };
		    },
		    processResults: function (data) {
		    	
		    	var items = $.map(data, function (obj) {
	    		  obj.id = obj.id || obj.codice;
	    		  obj.text = obj.text || obj.denominazione;
	    		  return obj;
	    		});
		    	
		    	return {
		            results: items
	        	};
		    },
		    cache: true
		  }
	});
	$(".provinciaSelect").change(function(){
		setComuneSelect($(this), "${searchListaComuniURL}");		
	});
});
</script>

<portlet:actionURL var="dichiarazioneUrlGenera"> 
	<portlet:param name="ope" value="generaDichiarazione" />
</portlet:actionURL>

<portlet:resourceURL var="dichiarazioneReportURL" 
	id="agevolazioneTariReport" escapeXml="false">
</portlet:resourceURL>

<c:set var="idForm" value="produrreDichiarazione" />
<c:set var="dati" value="${datiAgevolazioneTari}" />
 
<div class="mainDiv agevolazioneTari">  
	<c:choose>
		<c:when test="${!empty datiAgevolazioneTari}">
			<form:form id="${idForm}" action="${dichiarazioneUrlGenera}" method="post" commandName="datiAgevolazioneTari">
				<c:if test="${empty download}"> 
				
					<input type="hidden" name="comuneResidenzaParam" id="comuneResidenzaParam" value="" />
				
					<%@ include file="./datiAnagrafici.jsp"%> 
					
					<div id="formServizio">
						<%@ include file="./datiImmobili.jsp"%> 
						<%@ include file="./datiAgevolazioni.jsp"%>   
						<%@ include file="./datiIsee.jsp"%>  
						<%@ include file="./datiCAF.jsp"%>  
						<%@ include file="../common/footer.jsp"%>
						<div class="container_pulsante">
							<input type="submit" name="bozza" value="<spring:message code="button.salva.bozza" />" />
							<span class="spacer"></span>
							<input type="submit" name="genera" value="<spring:message code="button.dichiarazione" />" />
						</div>
					</div>

					
					<div id="msgServizioLimitato" class="container_pulsante">
						<br>
						<p>
							<strong><spring:message code="label.servizioNonAttivo" /></strong>
						</p>
					</div>
				</c:if>
				<c:if test="${!empty download}">
					<p>
						<spring:message code="label.servizio.option1.part1" /><spring:message code="link.dichiarazione" /><spring:message code="label.servizio.option1.part2" /><br>
						<spring:message code="label.servizio.option2.part1" />
						<ul>
							<li><spring:message code="label.servizio.option2.part2" /></li>
							<li><spring:message code="label.servizio.option2.part3" /></li>
						</ul>
					</p>
					<div class="container_pulsante">
						<a class="custom_pulsante" href="${dichiarazioneReportURL}">
							<spring:message code="link.dichiarazione" />
						</a>
						<span class="spacer"></span>
						<a class="custom_pulsante" href="<portlet:renderURL portletMode="view"/>"><spring:message	code="button.home" /></a>
					</div>
				</c:if>
			</form:form>
			<c:remove var="i" scope="page" />
			<c:remove var="j" scope="page" />
		</c:when>
		<c:otherwise>
			<div>
				<spring:message code="errore.pdds.codice3"/>
			</div>								
		</c:otherwise>
	</c:choose>
</div>