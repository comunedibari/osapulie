<c:if test="${evaluationServiceEnable}">
	<portlet:resourceURL var="serviceEvaluationUrl"	id="serviceEvaluation" escapeXml="false">
	</portlet:resourceURL>
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		$('.valutaServizioDiv').css("display", "none");
		
		var url = '${serviceEvaluationUrl}';
		if ($('.valutaServizioDiv').length){
			jQuery.ajax({
				   type: 'POST',
				   url: url,
				   async: false,
				   success: 
					 function(data){ 
					   if (!$('.evaluationServiceDownloadLink').length){
						   $('.valutaServizioDiv').css("display", "block");
					   }
					   $('#valutaServizioHref').attr("href", data);
				   	 },
				   error: function(data){ 
					   throw new Error("Si è veriricato un errore in updateInfoModificaMassiva.");
				   }
			});	
		}
		
		$(".evaluationServiceDownloadLink").click(
		    function(e) {   
		    	$('.valutaServizioDiv').css("display", "block");
		        e.preventDefault();
		        //open download link in new page
		        window.location.href = $(this).attr("href");
		    }
		);
	});
	</script>
	
	<div class="valutaServizioDiv">
		<a id="valutaServizioHref" class="custom_pulsante" href="#">
			<spring:message	code="label.valutaServizio" />
		</a>
	</div>
</c:if>
