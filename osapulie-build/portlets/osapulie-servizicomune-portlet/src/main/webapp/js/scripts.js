function aggiornaComuneResidenzaUtente(select) {

}

function openCloseDiv(div) {
	var ele = document.getElementById(div);
	if(ele.style.display == "block") {
		ele.style.display = "none";
	}
	else {
		ele.style.display = "block";
	}
}

function togglerRowStatus(checkboxName, inputtextName){

	if ($(checkboxName).is(':checked')) {
		$(inputtextName).css('border', '2px solid #F11F1F');
	} else {
		$(inputtextName).css('border', 'none');
		$(inputtextName).removeClass("inputTextNewValueChecked");
	} 
}

function updateAnno(action){
	$('#saveForm').attr('action',action);
	$('#saveForm').submit();
}

function deleteLogo(message, action){

	var result = confirm(message);
	if (result){
		$('#editForm').attr('action',action);
		$('#editForm').submit();
	}
}

function deleteDiv(divId){
	$("#" + divId).remove();
}

//Funzione che restituisce il "valore" dell'ultimo div inserito dinamicamente
function maxValue(idValue){
	var result = 0;
	
	$("div[id^='"+idValue+"']").each( function(){
		//estraggo il valore di "id"
		var id = $(this).attr('id');
		var idSplitted = id.split(idValue);
		var value= idSplitted[1];
		//aggiungo l'id appena estratto alla lista di id creata in precedenza
		if(value!='undefined'){
			result=value;
		}
	});
	return result;
}

function addEsenzione(iteration){
	var $esenzioneDiv = $('#esenzioneDiv' + iteration);
	var innerIteration = 0;
	
	innerIteration = maxValue("esenzione_"+iteration+"_");
	innerIteration++;
	
	//TODO sistemare bug duplicati (calcolare il max numero e non la length degli elementi)

	$esenzioneDiv.append('<div id="esenzione_' + iteration +'_' + innerIteration + '">' +
			'<input type="text" value="" name="tributi[' + iteration + '].esenzioni[' + innerIteration + '].descrizione" id="tributi' + iteration + '.esenzioni' + innerIteration + '.descrizione">' +
			'<img title="Elimina" alt="Elimina" src="/osapulie-servizicomune-portlet/images/imgo.jpg" class="deleteImg" onclick="deleteDiv(\'esenzione_' + iteration + '_' + innerIteration + '\');return false"></div>');
}

function addAgevolazione(iteration){
	var $agevolazioneDiv = $('#agevolazioneDiv' + iteration);
	var innerIteration = 0;
	
	innerIteration = maxValue("agevolazione_"+iteration+"_");
	innerIteration++;

	$agevolazioneDiv.append('<div id="agevolazione_' + iteration + '_' + innerIteration + '"> <input type="text" value="" name="tributi[' + iteration + '].agevolazioni[' + innerIteration + '].nome" id="tributi' + iteration + '.agevolazioni' + innerIteration + '.nome">&nbsp;&nbsp;&nbsp;' +
			'<input type="text" value="" name="tributi[' + iteration + '].agevolazioni[' + innerIteration + '].valore" id="tributi' + iteration + '.agevolazioni' + innerIteration + '.valore">&nbsp;' +
			'<img title="Elimina" alt="Elimina" src="/osapulie-servizicomune-portlet/images/imgo.jpg" class="deleteImg" onclick="deleteDiv(\'agevolazione_' + iteration + '_' + innerIteration + '\');return false"></div>');
}

function addDetrazione(iteration,options){
	
	var $detrazioneDiv = $('#detrazioneDiv' + iteration);
	var innerIteration = 0;
	
	innerIteration = maxValue("detrazione_"+iteration+"_");
	innerIteration++;
	
	var select='<select name="tributi[' + iteration + '].detrazioni[' + innerIteration + '].idTipologiaDetrazione" id="tributi' + iteration+'">&nbsp;&nbsp;&nbsp;'+options+'</select>';
	
	console.log("options: "+select);

	$detrazioneDiv.append('<div id="detrazione_' + iteration + '_' + innerIteration + '"><input type="text" value="" name="tributi[' + iteration + '].detrazioni[' + innerIteration + '].descrizione" id="tributi' + iteration + '.detrazioni' + innerIteration + '.descrizione">&nbsp;&nbsp;&nbsp;' +
			select + 
			'<input type="text" value="" name="tributi[' + iteration + '].detrazioni[' + innerIteration + '].importo" id="tributi' + iteration + '.detrazioni' + innerIteration + '.importo">&nbsp;' +
			'<img title="Elimina" alt="Elimina" src="/osapulie-servizicomune-portlet/images/imgo.jpg" class="deleteImg" onclick="deleteDiv(\'detrazione_' + iteration + '_' + innerIteration + '\');return false"></div>');
}


