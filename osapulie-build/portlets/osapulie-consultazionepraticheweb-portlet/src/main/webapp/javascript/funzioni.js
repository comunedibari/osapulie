var festivita = new Array("01/01/2011","06/01/2011","25/04/2011","01/05/2011","02/06/2011","15/08/2011","01/11/2011","08/12/2011","25/12/2011","26/12/2011");
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function controllaData(campo)
{  
    valore=campo.value
    if (valore.length>0){
        if ((valore.length == 8 || valore.length==10 || valore.length == 12 || valore.length == 16 ))
        {
            valore = valore.replace(/-/g,"/");
            campo.value=valore;
            //ASSEGNO AD UNA VARIABILE IL CONTENUTO DEL CAMPO TESTO valore=document.mioForm.campo.value
            //ESTRAGGO I VALORE RELATIVI A GIORNO,MESE,ANNO
            if (valore.indexOf('/',0)==-1){
                var data = campo.value.substr(0,10);
                var ora = campo.value.substr(8,4);
                data_slash = data.substr(0,2)+'/'+data.substr(2,2)+'/'+data.substr(4,4);
                campo.value=data_slash
                if (valore.indexOf(':',0)==-1 && valore.length == 12){
                    ora_duepunti = ora.substr(0,2)+':'+ora.substr(2,2);
                    campo.value=campo.value+' '+ora_duepunti;
                }
            }
            valore=campo.value
            primacoppia=valore.substr(0,2);
            secondacoppia=valore.substr(3,2);
            quadrupla=valore.substr(6,4);
            //CONVERTO I VALORI STRINGA IN NUMERI
            numero=parseInt(primacoppia,10)
            numero1=parseInt(secondacoppia,10)
            numero2=parseInt(quadrupla);
            //estraggo le posizioni relative agli slash
            primoslash=valore.substr(2,1);
            secondoslash=valore.substr(5,1);
            //CALCOLO LA LUNGHEZZA DELLE VARIABILE CHE CONTENGONO I NUMERI
            primalunghezza=primacoppia.length;
            secondalunghezza=secondacoppia.length;
            terzalunghezza=quadrupla.length;
            if(valore.length == 16){
                ore = valore.substr(11,2);
                minuti = valore.substr(14,2);
                h=parseInt(ore);
                min=parseInt(minuti);
                duepunti = valore.substr(13,1);
                if(h > 23 || h <0){
                    alert("Ora non valida");
                    campo.focus();
                    return false;
                }
                if(min > 59 || min < 0){
                    alert("Minuti non validi");
                    campo.focus();
                    return false;
                }
            }
            if ((primalunghezza==2)&&(primoslash=="/")&&(numero>=1)&&(numero<=31)&&(secondalunghezza==2)&&(secondoslash=="/")&&(numero1>=1)&&(numero1<=12)&&(terzalunghezza==4)&&(numero2>=1800)&&(numero2<=3000))
            {
             if (( (numero1==11)||(numero1==4)||(numero1==6)||(numero1==9) )&&(numero==31))
             {
                    alert("Giorno non valido per il mese specificato");
                    campo.focus();
                    return false;
             }
             if  ((numero1==2)&&(numero>29))
             {
                    alert("Giorno non valido per il mese specificato");
                    campo.focus();
                    return false;
             }
            }
            else
            {
                alert("Formato data non valido, inserirla nel seguente formato:\ndd/mm/yyyy");
                campo.focus();
                return false;
            }
            var dataD = MakeDate(valore);
            w = dataD.getDay();
            aw = new Array("domenica","lunedi'","martedi'","mercoledi'","giovedi'","venerdi'","sabato");
            parent = campo.parentNode;
            br = document.createElement("br");
            testo = document.createElement("font");
            testo.className = "error";
            //testo.innerHTML="";
            var msg="";
            /*if(w==0 || w==6){
                risultato = aw[w];
                msg="Giorno della settimana: "+risultato;               
            }
            for( f=0; f < festivita.length;f++){
                if(valore.substr(0,10) == festivita[f]){
                    msg = "Giorno festivo";
                }
            }
            if(msg != ""){
               if(!(parent.lastChild instanceof HTMLFontElement)){
                testo.innerHTML = msg;
                parent.appendChild(br);
                parent.appendChild(testo);
               }else{
                   parent.lastChild.innerHTML = msg;
               }
           }else{
               if(parent.lastChild instanceof HTMLFontElement){
                parent.removeChild(parent.lastChild);
                parent.removeChild(parent.lastChild);
               }
           }*/
            return true;
         }
         else{
                alert("Formato data non valido, inserirla nel seguente formato:\ndd/mm/yyyy");
                campo.focus();
                return false;
            }
    }
}

// ****************************************************************************************
// Confronta le due date ( precondizione : entrambe valorizzate).
// Return -1 se data1 < data2
// Return  0 se data1 = data2
// Return  1 se data1 > data2

function CfrData1Data2 (data1, data2){
    var dDatIni = new Date();
    var dDatFin = new Date();
    var sData1="";
    var sData2="";

    dDatIni=MakeDate(data1);
    dDatFin=MakeDate(data2);

    sData1=GiraData(dDatIni);
    sData2=GiraData(dDatFin);

    if (sData1 < sData2)
            {
            return -1;
            }
    if (sData1 == sData2)
            {
            return 0;
            }
    if (sData1 > sData2)
            {
            return 1;
            }

    return 9;
}

// ****************************************************************************************
function TogliZero(sText){
   nn=sText.indexOf('0',0);
   if (nn==0)
   {
   	sTT=sText.substring(1);
   }
   else
   {
	sTT=sText;
   }
   return sTT;
}

// ****************************************************************************************
function MakeDate(sDate) {
    var d=new Date();
    var gg=0;mm=0;aa=0;hh=0;mi=0;
    var nPos=0;nPos1=0;
    nPos=sDate.indexOf('/',0);
    if (nPos>0)
    {
       mm=0;
       aa=0;
       gg=parseInt(TogliZero(sDate.substring(0,nPos)));
       mm=parseInt(TogliZero(sDate.substring(nPos+1)));
       nPos1=sDate.indexOf('/',nPos+1);
       if (nPos1>0)
        {
          aa=parseInt(TogliZero(sDate.substring(nPos1+1)))
         }
        hh=parseInt(TogliZero(sDate.substr(11,2)));
        mi=parseInt(TogliZero(sDate.substr(14,2)));
    }
    else
    {
      gg=parseInt(TogliZero(sDate.substring(0,2)));
      mm=parseInt(TogliZero(sDate.substring(4,2)));
      aa=parseInt(TogliZero(sDate.substring(4)));
      hh=parseInt(TogliZero(sDate.substr(8,2)));
      mi=parseInt(TogliZero(sDate.substr(10,2)));
    }
     if (isNaN(gg))
            gg=0
     if (isNaN(mm))
            mm=0
     if (isNaN(aa))
            aa=0
     if (aa==0)
            aa=d.getFullYear();
     if (mm==0)
            mm=d.getMonth()+1;
     if(isNaN(hh))
        d=new Date(aa,mm-1,gg);
    else
        d=new Date(aa,mm-1,gg,hh,mi);
    return d;
}

// ****************************************************************************************
function GiraData(sData){
    var d=new Date();
    var gg=0;mm=0;aa=0;
    var sDataOut="";
    d=sData;
    aa=d.getFullYear();
    mm=d.getMonth()+1;
    gg=d.getDate();
    if (mm<10)
    {mm = "0" + mm;
    }
    if (gg<10)
    {gg = "0" + gg;
    }
    sDataOut=aa + "/" + mm + "/" + gg;
    return (sDataOut);
}

//*******************************************************
var form_ricerca = false;
function vis_form_ricerca(){
	if(!form_ricerca){
		document.getElementById('form_ricerca').style.display='block';		
		form_ricerca = true;
	}else{
		document.getElementById('form_ricerca').style.display='none';
		form_ricerca = false;
	}
		
}

function formLoading(formId){

	/*
	* Scegli tu testo e immagine da mettere ;)
	*/
	var testo = 'Attendere invio in corso...';
	var immagine = '/osapulie-consultazionepraticheweb-portlet/images/loader.gif';
	
	
	var loadingDiv = document.createElement( "div" );
	loadingDiv.id = "::loadingDiv";
	loadingDiv.style.cssText = 'color:white !important;position: fixed;width: 100%;height: 100%;top: 0px;left: 0px;z-index: 9999999999;background-color: rgba(0,0,0,0.5);';
	var loaderDiv = document.createElement( "div" );
	loaderDiv.id = "::loaderDiv";
	loaderDiv.style.cssText = 'position:relative; top:20%; width:200px; height: auto; margin: 0 auto; padding-top:30px; padding-bottom:30px; text-align:center;';
	var pText = document.createElement("p");
	var t = document.createTextNode(testo); 
	pText.appendChild(t);
	loaderDiv.appendChild(pText);
	var elem = document.createElement("img");
	elem.src = immagine;
	loaderDiv.appendChild(elem);
	loadingDiv.appendChild(loaderDiv);
	document.body.appendChild(loadingDiv);
	$('#'+formId).submit();
}