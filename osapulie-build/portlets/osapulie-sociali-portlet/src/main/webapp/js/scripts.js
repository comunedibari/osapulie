
$(document).ready(function() {
	
	//Valuto le impostazioni iniziali
	selectRuoloDiv($("#selectRuolo").find(":selected").val());
	selectDisabileDiv($("#selectTipo").find(":selected").val());
	 selectPassDiv($("#selectPass").find(":selected").val());
	 
	if($("#alimentiSiBtn").is(":checked")){
		 $("#alimentiSiRiquadro").show();
		 $("#alimentiNoRiquadro").hide();
	 }
	
	if($("#alimentiNoBtn").is(":checked")){
		 $("#alimentiSiRiquadro").hide();
		$("#alimentiNoRiquadro").show();
	}
	 
	if($("#appProprioSi").is(":checked")){
		$("#canoneDiv").hide();
	 }
	 
	if($("#appProprioNo").is(":checked")){
		 $("#canoneDiv").show();
	 }
	
	
	if($("#coniugatoBtnNo").is(":checked")){
		$("#coniugatoDiv").hide();
	 }
	 
	if($("#coniugatoBtnSi").is(":checked")){
		 $("#coniugatoDiv").show();
	 }
    
       
	if($("#comuniBtnNo").is(":checked")){
		$("#serviziComuneDiv").hide();
	 }
	 
	if($("#comuniBtnSi").is(":checked")){
		 $("#serviziComuneDiv").show();
	 }
	
	if($("#entiBtnNo").is(":checked")){
		$("#serviziEntiDiv").hide();
	 }
	 
	if($("#entiBtnSi").is(":checked")){
		 $("#serviziEntiDiv").show();
	 }
    
	$("#selectRuolo").change(function(){
		  selectRuoloDiv($(this).find(":selected").val());
	 });
	 
	$("#selectPass").change(function(){
		  selectPassDiv($(this).find(":selected").val());
	 });
	 
	
	 $("#alimentiSiBtn").change(function(){
		 if($(this).is(":checked")){
			 $("#alimentiSiRiquadro").show();
			 $("#alimentiNoRiquadro").hide();
		 }
	 });
	 $("#alimentiNoBtn").change(function(){
		 if($(this).is(":checked")){
			 $("#alimentiSiRiquadro").hide();
			 $("#alimentiNoRiquadro").show();
		 }
	 });
	 
	 
	 $("#appProprioSi").change(function(){
		 if($(this).is(":checked")){
			 $("#canoneDiv").hide();
		 }
		 
	 });
	 
	 $("#appProprioNo").change(function(){
		 if($(this).is(":checked")){
			 $("#canoneDiv").show();
		 }
	 });
	 
	 $("#entiBtnNo").change(function(){
		 if($(this).is(":checked")){
			 $("#serviziEntiDiv").hide();
		 }
		 
	 });
	 
	 $("#entiBtnSi").change(function(){
		 if($(this).is(":checked")){
			 $("#serviziEntiDiv").show();
		 }
	 });
	 
	 $("#comuniBtnNo").change(function(){
		 if($(this).is(":checked")){
			 $("#serviziComuneDiv").hide();
		 }
		 
	 });
	 
	 $("#comuniBtnSi").change(function(){
		 if($(this).is(":checked")){
			 $("#serviziComuneDiv").show();
		 }
	 });
	 
	 $("#coniugatoBtnSi").change(function(){
	 if($(this).is(":checked")){
		 $("#coniugatoDiv").show();
	 }
	 });
	 $("#coniugatoBtnNo").change(function(){
		 if($(this).is(":checked")){
			 $("#coniugatoDiv").hide();
		 }
		 });
	 
	$("#selectTipo").change(function(){
			selectDisabileDiv($(this).find(":selected").val());
		 });
       
	});

function selectRuoloDiv(selected){
	if(selected=='affine'){
		 $("#affinitaDiv").show();
		 $("#anzianoDiv").show();
		 $("#datiAnagA1").show();
	 }
	 if(selected=='parente'){
			 $("#anzianoDiv").show();
			 $("#affinitaDiv").hide();
			 $("#datiAnagA1").show();
	 }
	 if(selected=='a titolo personale'){
		 $("#anzianoDiv").hide();
		 $("#affinitaDiv").hide();
		 $("#datiAnagA1").hide();
	 }
}

function selectPassDiv(selected){
	if(selected=='rinnovo'){
		 $("#passDiv").show();
	 }
	 if(selected=='duplicato'){
			$("#passDiv").show();
	 }
	 if(selected=='duplicato per furto'){
		 $("#passDiv").show();
	 }
	 if(selected=='primo rilascio'){
		 $("#passDiv").hide();
	 }
	 if(selected=='contrassegno'){
		 $("#passDiv").hide();
	 }
}

function selectDisabileDiv(selected){
	 if(selected=='a titolo personale'){
		 $("#disabileDiv").hide();
	 }else{
		 $("#disabileDiv").show();
	 }
}
