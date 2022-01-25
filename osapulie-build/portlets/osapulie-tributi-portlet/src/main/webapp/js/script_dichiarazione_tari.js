$(document).ready(function(){

	var tipoUtenza = $("#tipoUtenzaSelect").val();
	var tipoDichiarazione = $("#select_tipo_dichiarazione").val();
	var tipoPersona = $(".tipoPersonaRadio:checked").val();
	var spedizione = $("#btn_invio").val();
	var modalitaRimborsoDom = "${datiTari.modalitaRimborsoDom}";
	var modalitaRimborsoNonDom = "${datiTari.modalitaRimborsoNonDom}" ;
	var statoEstero = $("#statoEsteroSelect").val();

	//mostro la select di tipologia dichiarazione corretta
	$("#select_domestica").show();
	$("#select_non_domestica").hide();

	$(".rea").hide();

	$("#domestica").show();
	$("#non_domestica").hide();
	$("#infoNuovaIscrizione").hide();

	$("#residenteSi").hide();
	$("#residenteNo").show();
	
	setStatoEsteroSelect(statoEstero);	
	
	setFormField(tipoUtenza, tipoDichiarazione, tipoPersona);

	if ($("#select_dom_rimborso").val() == 'rimborso' && $("input[name='modalitaRimborsoDom']:checked").val() == 'accredito') {
		$("#div_dom_iban").show();
	}
	else {
		$("#div_dom_iban").hide();
	}

	if ($("#select_nd_rimborso").val() == 'rimborso' && $("input[name='modalitaRimborsoNonDom']:checked").val() == 'accredito') {
		$("#div_nd_iban").show();
	}
	else {
		$("#div_nd_iban").hide();
	}

	$("#select_tipo_dichiarazione").change(function(){
		tipoDichiarazione =$(this).val();
		tipoUtenza = $("#tipoUtenzaSelect").val();
		tipoPersona = $(".tipoPersonaRadio:checked").val();
		setFormField(tipoUtenza, tipoDichiarazione, tipoPersona);
	});

	$("#select_non_domestica").change(function(){
		tipoDichiarazione = $(this).val();
		tipoUtenza = $("#tipoUtenzaSelect").val();
		tipoPersona = $(".tipoPersonaRadio:checked").val();
		setFormField(tipoUtenza, tipoDichiarazione, tipoPersona);
	});
	
	$(".tipoPersonaRadio").change(function(){
		tipoDichiarazione = $("#select_tipo_dichiarazione").val();
		tipoUtenza = $("#tipoUtenzaSelect").val();
		tipoPersona = $(this).val();
		setFormField(tipoUtenza, tipoDichiarazione, tipoPersona);
	});

	$("#select_dom_rimborso").change(function(){
		var value =$(this).val();

		$('input[name="modalitaRimborsoDom"]').attr('checked', false);
		$('input[name="ibanDom"]').val('');

		if(value == 'rimborso'){
			$('#div_dom_rimborso').show();
		}
		else{
			$('#div_dom_rimborso').hide();
		}
	});

	$("#select_nd_rimborso").change(function(){
		var value =$(this).val();

		$('input[name="modalitaRimborsoNonDom"]').attr('checked', false);
		$('input[name="ibanNonDom"]').val('');

		if(value == 'rimborso'){
			$('#div_nd_rimborso').show();
		}
		else{
			$('#div_nd_rimborso').hide();
		}
	});

	$("#select_is_residente").change(function(){
		var value =$(this).val();
		if(value == 'true'){
			$("#residenteSi").show();
			$("#residenteNo").hide();
			$("#nonResidente1").attr('checked', false);
			$("#totaleNucleoInResidenza").val('');
		}
		else{
			$("#residenteSi").hide();
			$("#residenteNo").show();
		}
	});

	$("#select_invio").change(function(){
		var value =$(this).val();
		if(value == 'altro'){
			$('#div_indirizzo_spedizione').show();
		}else{
			$('#div_indirizzo_spedizione').hide();
		}
	});

	$("input[id*='chk_']").click(function(){
		var idName = $(this).attr("id");
		var tmp = idName.split("chk_");
		var tipo = tmp[1];

		/* chiusura tutti gli altri div */
		$('#motivazioniFieldset [id^="div_"]').hide();

		if($(this).is(':checked')){
			$("#div_"+tipo).show();
		}
		else{
			$("#div_"+tipo).hide();
		}
	});
	$("input[name='modalitaRimborsoDom']").change(function() {
		if ($(this).val() == 'accredito') {
			$("#div_dom_iban").show();
		}
		else {
			$("#div_dom_iban").hide();
		}
	});
	$("input[name='modalitaRimborsoNonDom']").change(function() {
		if ($(this).val() == 'accredito') {
			$("#div_nd_iban").show();
		}
		else {
			$("#div_nd_iban").hide();
		}
	});
	
	if($("#select_is_residente").val() == 'true'){
		$("#residenteSi").show();
		$("#residenteNo").hide();
	}
	else{
		$("#residenteSi").hide();
		$("#residenteNo").show();
	}
	
	if($("#select_invio").val() == 'altro'){
		$('#div_indirizzo_spedizione').show();
	}
	else{
		$('#div_indirizzo_spedizione').hide();
	} 
	
	/* aggiornamento pagina comune residenza*/
	$("#comuneResidenza").change(function(){
		var loader = $(this).next(".loader");
		$(loader).show();
		var formid= 'produrreDichiarazione';
		$("#tipologiaUtenza").val($(this).val());
		$('#'+formid).submit();
	});

	/* aggiornamento pagina comune spedizione*/
	$("#comuneSpedizione").change(function(){
		var loader = $(this).next(".loader");
		$(loader).show();
		var formid= 'produrreDichiarazione';
		$("#tipologiaUtenza").val($(this).val());
		$('#'+formid).submit();
	});
	
	/* checkbox riduzioni */
	initRiduzione('DomIsc');
	initRiduzione('DomVar');
	initRiduzione('NonDomIsc');
	initRiduzione('NonDomVar');
	
	$("#statoEsteroSelect").change(function(){
		statoEstero = $(this).val();
		setStatoEsteroSelect(statoEstero);		
	});
});

$(document).ready(function(){
	$("#inviaDichiarazioneButton").click(function(){
	    $("div#divLoading").addClass('show');
	});
});

function initRiduzione(suffix) {
	$.each($('.riduzioni'+suffix), function (index, value) {
		if ($(this).is(':checked')) {
			$(this).parent().children('#valoriRiduzione'+suffix + index).show();
		} 
		else {
			$(this).parent().children('#valoriRiduzione'+suffix + index).hide();
		}
	});
} 

function toggleRiduzione(checkbox,riduzione) {
	var ischecked = $(checkbox).is(':checked');
	
	var classCheckBox = $(checkbox).attr('class')
	$('.'+classCheckBox).prop('checked', false);
	
	$("ul[id*='valoriRiduzione']").each(function(){
		$(this).hide();
	});
	
	$(checkbox).prop('checked', ischecked);
	if(ischecked){
		$(riduzione).show();
	}
	else {
		$(riduzione).hide();
	}

	
} 

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

function setTableColumns(tipo_utenza, tipo_dichiarazione){
	var $tbl = $("#"+tipo_utenza+"_table");

	if(tipo_utenza == 'non_domestica'){

		$("#domestica").hide();
		$("#non_domestica").show();

		if(tipo_dichiarazione == 'cessazione'){
			$("#div_non_domestica_trasferimento").hide();
			$("#div_non_domestica_iscrizione").hide();
			$("#div_non_domestica_variazione").hide();
			$("#div_non_domestica_cessazione").show();
		}
		if(tipo_dichiarazione == 'iscrizione'){
			$("#div_non_domestica_trasferimento").hide();
			$("#div_non_domestica_iscrizione").show();
			$("#div_non_domestica_variazione").hide();
			$("#div_non_domestica_cessazione").hide();
		}
		if(tipo_dichiarazione == 'variazione'){
			$("#div_non_domestica_trasferimento").hide();
			$("#div_non_domestica_iscrizione").hide();
			$("#div_non_domestica_variazione").show();
			$("#div_non_domestica_cessazione").hide();
		}
		if(tipo_dichiarazione == 'trasferimento'){
			$("#div_non_domestica_trasferimento").show();
			$("#div_non_domestica_iscrizione").show();
			$("#div_non_domestica_variazione").hide();
			$("#div_non_domestica_cessazione").show();
		}
	}

	if(tipo_utenza == 'domestica'){

		$("#domestica").show();
		$("#non_domestica").hide();
		
		if(tipo_dichiarazione == 'cessazione'){
			$("#div_domestica_trasferimento").hide();
			$("#div_domestica_iscrizione").hide();
			$("#div_domestica_variazione").hide();
			$("#div_domestica_cessazione").show();
		}
		if(tipo_dichiarazione == 'iscrizione'){
			$("#div_domestica_trasferimento").hide();
			$("#div_domestica_iscrizione").show();
			$("#div_domestica_variazione").hide();
			$("#div_domestica_cessazione").hide();
		}
		if(tipo_dichiarazione == 'variazione'){
			$("#div_domestica_trasferimento").hide();
			$("#div_domestica_iscrizione").hide();
			$("#div_domestica_variazione").show();
			$("#div_domestica_cessazione").hide();
		}
		if(tipo_dichiarazione == 'trasferimento'){
			$("#div_domestica_trasferimento").show();
			$("#div_domestica_iscrizione").show();
			$("#div_domestica_variazione").hide();
			$("#div_domestica_cessazione").show();
		}
	}
}

function setFormField(tipo_utenza, tipo_dichiarazione, tipoPersona) {
	console.log("setFormField: "+tipo_utenza);
	console.log("setFormField: "+tipo_dichiarazione);
	
	if(tipo_dichiarazione != "iscrizione" && (typeof isLimitatoIscrizione !== 'undefined' && isLimitatoIscrizione=="true")) {
		$("#formServizio").hide();
		$("#msgServizioLimitato").show();
	} else {
		$("#formServizio").show();
		$("#msgServizioLimitato").hide();
	}


	if(tipo_utenza == 'domestica'){
		
		if(tipo_dichiarazione != "iscrizione" && (typeof isEscludiDomestiche !== 'undefined' && isEscludiDomestiche=="true")){
			$("#formServizio").hide();
			$("#msgServizioLimitato").show();
		} else {
			$("#formServizio").show();
			$("#msgServizioLimitato").hide();
		}
		
		$(".requiredNoDom").hide();

		$(".rea").hide();
		$(".piva").hide();
		
		if(tipo_dichiarazione == "iscrizione") {
			$("#infoNuovaIscrizione").show();			
		}else{
			$("#infoNuovaIscrizione").hide();						
		}
		
		if (tipoPersona == 'giuridica') {
			$(".rea").show();
			$(".piva").show();
		}
		
		setTableColumns(tipo_utenza, tipo_dichiarazione);

//		$("div[id*='div_']").each(function(){
//			var idName=$(this).attr("id");
//			if(idName == ("div_"+tipo_utenza+"_"+tipo_dichiarazione)){
//				$("#"+idName).show();
//			}
//			else{
//				$("#"+idName).hide();
//			}
//
//		});

		if(tipo_dichiarazione == "variazione" || tipo_dichiarazione == "cessazione") {
			$("#div_sgravio").show();
			if(tipo_dichiarazione == "cessazione"){
				$("#select_dom_rimborso option[value='compensazione']").remove();
			}
			else{
				$("#select_dom_rimborso").append('<option value="compensazione">Compensazione sul primo invio utile</option>');
			}
		} 
		else
			$("#div_sgravio").hide();

	}
	else {
		
		if(tipo_dichiarazione != "iscrizione" && (typeof isEscludiCommerciali !== 'undefined' && isEscludiCommerciali=="true")){
			$("#formServizio").hide();
			$("#msgServizioLimitato").show();
		} else {
			$("#formServizio").show();
			$("#msgServizioLimitato").hide();
		}
		
		if(tipo_dichiarazione == "iscrizione") {
			$("#infoNuovaIscrizione").show();			
		}else{
			$("#infoNuovaIscrizione").hide();						
		}
		
		$(".requiredNoDom").show();

		$("#select_non_domestica").show();
		$("#select_domestica").hide();

		$(".rea").show();

		$("#domestica").hide();
		$("#non_domestica").show();
		setTableColumns(tipo_utenza, tipo_dichiarazione);

		if(tipo_dichiarazione == "variazione" || tipo_dichiarazione == "cessazione") {
			$("#div_nd_sgravio").show();
			if(tipo_dichiarazione == "cessazione"){
				$("#select_nd_rimborso option[value='compensazione']").remove();
			}else{
				$("#select_nd_rimborso").append('<option value="compensazione">Compensazione sul primo invio utile</option>');
			}
		} else
			$("#div_nd_sgravio").hide();
	}

	if($('#select_dom_rimborso').val() == 'rimborso'){
		$("#div_dom_rimborso").show();
	}
	else{
		$("#div_dom_rimborso").hide();
	}

	if($('#select_nd_rimborso').val() == 'rimborso'){
		$("#div_nd_rimborso").show();
	}
	else{
		$("#div_nd_rimborso").hide();
	}

	if($("input[name='modalitaRimborsoDom']").val() == 'accredito'){
		$("#div_dom_iban").show();
	}
	else{
		$("#div_dom_iban").hide();
	}

	if($("input[name='modalitaRimborsoNonDom']").val() == 'accredito'){
		$("#div_nd_iban").show();
	}
	else{
		$("#div_nd_iban").hide();
	}

	$("input[id*='chk_']").each(function(){
		var idName=$(this).attr("id");
		var tmp = idName.split("chk_");
		var tipo = tmp[1];

		if($(this).is(':checked')){
			$("#div_"+tipo).show();
		}
		else{
			$("#div_"+tipo).hide();
		}
	});
}
