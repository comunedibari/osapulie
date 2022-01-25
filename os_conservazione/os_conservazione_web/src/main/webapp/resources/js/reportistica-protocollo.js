var reportistica_protocollo = new Object();	
var templateOperazioneInCorso = Handlebars.compile($("#templateOperazioneInCorso").html());
reportistica_protocollo.ricercaElementi = function(){
	var theUrl = $("#baseApplicationUrl").val();
	theUrl += "rest/protected/reportProtocollazione.json"
	$.ajax({
		beforeSend: function( xhr ) {  
			$.blockUI(	{ 
							message: templateOperazioneInCorso,
							css: { 
					            border: 'none', 
					            padding: '15px', 
					            backgroundColor: '#000', 
					            '-webkit-border-radius': '10px', 
					            '-moz-border-radius': '10px', 
					            opacity: .5, 
					            color: '#fff' 
					        }
						}) 
		},
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: theUrl,
        //data: {from:regProto.dataFrom(),to:regProto.dataTo(),__:new Date().getTime()},
        dataType: "json",
        success: function (data){
       
        },
        error: function (jqXHR, exception){

        },
		complete:function(a){
			 $.unblockUI()
		}
	});
  
};


$(function() {
	var myDate = new Date();
	myDate.setDate(myDate.getDate());	
	$('#datetimepickerFrom')
	.datetimepicker({
		defaultDate: myDate,
		locale : 'it',
		format: 'DD/MM/YYYY'
	}).on("dp.change", function (e) {
	    $('#datetimepickerTo').data("DateTimePicker").minDate(e.date);
	}).data("DateTimePicker").maxDate(myDate);
	
	$('#datetimepickerTo')
	.datetimepicker({
		locale : 'it',
		format: 'DD/MM/YYYY'
	}).data("DateTimePicker").maxDate(myDate).minDate(myDate);


	$("#reportProtocolloSubmit").click(function(evt){
		evt.preventDefault();
		reportistica_protocollo.ricercaElementi();
	});
});