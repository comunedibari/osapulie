$(document).ready(function(){
	
	if(typeof isLimitatoIscrizione !== 'undefined' && isLimitatoIscrizione=="true") {
		$("#formServizio").hide();
		$("#msgServizioLimitato").show();
	} else {
		$("#formServizio").show();
		$("#msgServizioLimitato").hide();
	}

	var statoEstero = $("#statoEsteroSelect").val();
	
	setStatoEsteroSelect(statoEstero);	
	
	
	$("#statoEsteroSelect").change(function(){
		statoEstero = $(this).val();
		setStatoEsteroSelect(statoEstero);		
	});
	
	/* aggiornamento pagina */
	$("#comuneResidenza").change(function(){
		var loader = $(this).next(".loader");
		$(loader).show();
		var formid= 'produrreDichiarazione';
		$("#comuneResidenzaParam").val($(this).val());
		$('#'+formid).submit();
	});
	
});

$(document).ready(function(){
	$("#inviaDichiarazioneButton").click(function(){
	    $("div#divLoading").addClass('show');
	});
});


function setStatoEsteroSelect(stato) {
	if(stato != "") {
		$(".campiNascitaEstero").show();
		$(".campiNascitaItalia").hide();
	} else {
		$(".campiNascitaEstero").hide();
		$(".campiNascitaItalia").show();
	}
}

/**
 * Carica la lista comuni data la provincia
 */
function setComuneSelect(select, url) {
	var dataTosend = {"query": $(select).val()};
	var divComuneSelect = $(select).parent().parent().find('.comuneSelect');
	var comuneSelect = $(divComuneSelect).find('select');
	var loader = $(divComuneSelect).find(".loader");
	$(loader).show();
	$.ajax({
		url: url,
		cache: false,
		dataType: 'json',
		data: dataTosend,
		async: true,
		success: function(data) {
			$(comuneSelect).find("option").remove();
			$(comuneSelect).append($("<option></option>").attr("value", "").text(" -- Seleziona -- ")); 
			$(comuneSelect).append($("<option></option>").attr("value", "199999").text(" -- IGNOTO -- ")); 
			$.each(data, function(i, comune) {
				$(comuneSelect).append($("<option></option>").attr("value", comune.codice).text(comune.denominazione)); 
			});
			
		},
		error: function (jqXHR, exception) {
			console.log("ERRORE: " + exception);
		}
	}).always(function() {
		$(loader).hide();
	});
}
