/**
 * 
 */

function registroProtocollo(){
	this.data=[];
	this.table={};
	this.templateProtocolloBtnOpen=function(){};
	this.templateProtocolloBtnRigenera=function(){};
	this.templateSpinner=function(){};
	this.lang='it';
	this.modal={
	core:new BootstrapDialog(),
	setMessage:function(msg){this.core.setMessage(msg);return this;},
	setTitle:function(title){this.core.setTitle(title);return this;},
	setType:function(type){this.core.setType(type);return this;},
	setClosable:function(o){this.core.setClosable(o);return this;},
	confirm:function(msg,f){BootstrapDialog.confirm(msg,f);return this;},
	open:function(){this.core.open();return this;},
	close:function(){this.core.close();return this;},
	closeAll:function(){
		$.each(BootstrapDialog.dialogs, function(id, dialog){
            dialog.close();
        });
	},
	danger:function(a){
		this.core.setClosable(true);
		this.core.setType(BootstrapDialog.TYPE_DANGER);
		this.core.setTitle(a||'Error');
		return this;
		},
	success:function(a){
		this.core.setClosable(true);
		this.core.setType(BootstrapDialog.TYPE_SUCCESS);
		this.core.setTitle(a||'Information');
		return this;
		},
	info:function(a){
		this.core.setClosable(true);
		this.core.setType(BootstrapDialog.TYPE_INFO);
		this.core.setTitle(a||'Information');
		return this;
		},
	def:function(a){
		this.core.setType(BootstrapDialog.TYPE_DEFAULT);
		this.core.setTitle(a||'Information');
		return this;
		},
	setButton:function(buttons){
		this.core.buttons=buttons;
		return this;
	},
	};
	this.dataFrom=function(){
		return $("#dataFrom").val();
	}
	this.dataTo=function(){
		return $("#dataTo").val() || $("#dataFrom").val();
	}
	this.setLang=function(a){
		this.lang=a;
	}
	this.getLang=function(){
		return this.lang;
	}
	this.initTable=function(data){
	var this_=this;
	this_.templateProtocolloBtnOpen=Handlebars.compile($("#templateProtocolloBtnOpen").html());
	this_.templateProtocolloBtnRigenera=Handlebars.compile($("#templateProtocolloBtnRigenera").html());
	this_.templateSpinner=Handlebars.compile($("#templateSpinner").html());
	
	this_.table=$('#tblSearchProtocollo').DataTable({
		data : data,
		pageLength: 25,
        "language" : {
            "url" : $('#lang_').val()
        },
        "responsive" : true,
        "pagingType": "full_numbers",
        "deferRender" : true,
		columns : [{
			title: "Data Registro",
			data : "dataUltimaReg",
			defaultContent: "",
			visible: true,
			orderable: false,
			width:"30%",
			render:function ( data, type, row, meta ) {
				var datatrasform=data?data.split('-'):['','','']
				return datatrasform[2]+'/'+datatrasform[1]+'/'+datatrasform[0];
			}
		}, {
			title: "Data Creazione",
			data : "dataCreazione",
			defaultContent: "",
			visible: true,
			orderable: false,
			width:"30%"
		},{
			title: "Nome",
			data : "fileName",
			defaultContent: "",
			visible: true,
			orderable: false,
			width:"30%"
		},{
			title: "Azione",
			data : null,
			defaultContent: "",
			visible: true,
			orderable: false,
			width:"10%"
		} ],
		columnDefs: [ {
		    targets: 3,
		    data: "flagGenerato",
		    render: function ( data, type, row, meta ) {
		    	 
		    	return row['flagGenerato']=='1'? this_.templateProtocolloBtnOpen(row) : (this_.templateProtocolloBtnRigenera(row));//'<a href="javascript:void(0)" onclick=\'regProto.modal.setMessage("'+data+'").open();\'>Download</a>';
		    }
		  } ]
	
	});
	return this_;
	}
 
}

registroProtocollo.prototype.searchProtocolli=function(){
	var _this=this;
      	   
	$.ajax({
			beforeSend: function( xhr ) {  $.blockUI({ message: '<h4> Attendere Prego </h4>' }) },
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/osconservazione/rest/protected/ltsProto.json",
            data: {from:regProto.dataFrom(),to:regProto.dataTo(),__:new Date().getTime()},
            dataType: "json",
            success: function (data){
           _this.table.destroy();
		   _this.initTable(data.payload);
            },
            error: function (jqXHR, exception){
            _this.modal.setMessage(_this.message('exception',jqXHR,exception)).danger().open();

            },
			complete:function(a){
				 $.unblockUI()
			}
			});
	  
}

registroProtocollo.prototype.download=function(url_){
	var this_=this;
	(function(a){ $.fileDownload(a,{
	successCallback:function (url) {  },
	failCallback:function (responseHtml, url,error) { 
	this_.modal.setMessage(this_.message('download_error',responseHtml,error)).danger().open(); }
	});
	})(url_)
}

registroProtocollo.prototype.genera=function(data){
	var this_=this, data_=data,
	 dataArray=data?data.split('-'):['','',''],
			dataFormatt= dataArray[2]+'/'+dataArray[1]+'/'+dataArray[0];
	 
	this.modal.confirm(this_.message('confirm_genera',dataFormatt,'?'), function(result){
        if(result) {
       (function(data){
 		    $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/osconservazione/rest/protected/saveregistro.json",
            data: {dcdata:data,__:new Date().getTime()},
            dataType: "json",
            success: function (d){
            	if(d && d.payload[0]=='OK'){ 
    			setTimeout(function(){	
    				this_.modal.setClosable(true);
        			this_.modal.close();
    			this_.modal.confirm({
    	            title: 'INFO',
    	            message: this_.message('registro_caricato_download',dataFormatt),
    	            btnCancelLabel: 'Chiudi',
    	            btnOKLabel: 'Scarica Adesso!',
    				callback: function(r){
    	    				if(r){
					this_.download('/osconservazione/rest/protected/download?iddoc='+d.payload[1])		
    	    				setTimeout(function(){
	    					this_.searchProtocolli();
    	    				},2100);
    	    				}else{
	    					setTimeout(function(){
	        					this_.searchProtocolli();
	        				},1500);
    	    				}
    				}})
                 }, 4100);
    			}else if(d && d.payload[0]=='PRESENTE'){
    			this_.searchProtocolli();	
    			this_.modal.setMessage(this_.message('file_esistente')).info().open();	
    			}else{
    			this_.modal.setMessage(this_.message('error')).danger().open();	
    			}
            },
            error: function (jqXHR,exception){
            	
           this_.modal.setMessage(this_.message('exception',jqXHR,exception)).danger().open();
       
            },
			complete:function(a){ }
			
 		    });
			 
       })(data_)
       this_.modal.setMessage(this_.templateSpinner()).setClosable(false).def('Processo avviato').open();
        }else { }
    });
	
}

registroProtocollo.prototype.message=function(key){
	var this__=this;
	if(typeof this__.language[this__.getLang()][key]=="string")
	return this__.language[this__.getLang()][key].replace('{0}',arguments[1]||'').replace('{1}',arguments[2]||'').replace('{2}',arguments[3]||'')
	if(typeof this__.language[this__.getLang()][key]=="function")
	return this__.language[this__.getLang()][key].apply(this__,[arguments[1]||'',arguments[2]||'',arguments[3]||''])	
}

registroProtocollo.prototype.language={
		it:{
			file_esistente:'Operazione Annullata > File Esistente',
			confirm_genera:'Genera Registro Protocollo per la data {0} {1}',
			download_error:'Download Fallito... {0} {1}',
			success:'Caricamento eseguito con successo',
			error:'Caricamento Fallito! {0}',
			ajaxerror:'Error! {0}',
			registro_caricato_download:'Registro Protocollo del {0} Generato! Scarica Registro ?',
			exception: function (jqXHR,exception){
            	 var msg = '';
                 if (jqXHR.status === 0) {
                     msg = 'Connessione Assente.\n Verifica Rete.';
                 } else if (jqXHR.status == 404) {
                     msg = 'Errore Intreno cod: 404';
                 } else if (jqXHR.status == 500) {
                     msg = 'Errore Intreno cod: 005';
                 } else if (exception === 'parsererror') {
                     msg = 'Errore Intreno cod: 001';
                 } else if (exception === 'timeout') {
                     msg = 'Errore Intreno cod: 003';
                 } else if (exception === 'abort') {
                     msg = 'Errore Intreno: 004';
                 } else {
                     msg = 'Generic Error.\n';
                 }	
             	
                 return msg;
            }
		},
		en:{
			file_esistente:'Operazione Annullata > File Esistente',
			confirm_genera:'Genera Registro Protocollo per la data {0} {1}',
			download_error:'Download Fallito... {0} {1}',
			success:'Caricamento eseguito con successo',
			error:'Caricamento Fallito! {0}',
			ajaxerror:'Error! {0}',
			registro_caricato_download:'Registro Protocollo del {0} Generato! Scarica Registro ?',
			exception: function (jqXHR,exception){
           	 var msg = '';
                if (jqXHR.status === 0) {
                    msg = 'Connessione Assente.\n Verifica Rete.';
                } else if (jqXHR.status == 404) {
                    msg = 'Errore Intreno cod: 404';
                } else if (jqXHR.status == 500) {
                    msg = 'Errore Intreno cod: 005';
                } else if (exception === 'parsererror') {
                    msg = 'Errore Intreno cod: 001';
                } else if (exception === 'timeout') {
                    msg = 'Errore Intreno cod: 003';
                } else if (exception === 'abort') {
                    msg = 'Errore Intreno: 004';
                } else {
                    msg = 'Generic Error.\n';
                }	
            	
                return msg;
           }
		}
};

var regProto = new registroProtocollo();

$(document).ready(function() {
regProto.initTable([]);
$("#search-proto").bind("click",function(){regProto.searchProtocolli()});

});			



$(function() {
var myDate = new Date();
myDate.setDate(myDate.getDate() - 1);	

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
	
});


 