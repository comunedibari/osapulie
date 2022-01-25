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
function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}	
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function visualizza(id,idImg){
	  if (document.getElementById){
	    if(document.getElementById(id).style.display == 'none'){
	      document.getElementById(id).style.display = 'block';
			document.getElementById(idImg).className="rot";
	    }else{
	      document.getElementById(id).style.display = 'none';
			document.getElementById(idImg).className="ori";
	    }
	  }
	}




