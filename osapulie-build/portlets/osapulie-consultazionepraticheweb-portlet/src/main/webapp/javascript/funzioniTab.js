var scheda='apertura';
var link='link_apertura'
function vis_consuntivazione()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("consuntivazione").style.display='block';
    document.getElementById("link_consuntivazione").className='activelink';
    scheda = 'consuntivazione';
    link='link_consuntivazione';
}
function vis_allegato()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("allegato").style.display='block';
    document.getElementById("link_allegato").className='activelink';
    scheda = 'allegato';
    link='link_allegato';
}
function vis_arch()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("archiviazione").style.display='block';
    document.getElementById("link_arch").className='activelink';
    scheda = 'archiviazione';
    link='link_arch';
}
function vis_apertura()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("apertura").style.display='block';
    document.getElementById("link_apertura").className='activelink';
    scheda = 'apertura';
    link='link_apertura';
}

function vis_campiaggiuntivi()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("campiaggiuntivi").style.display='block';
    document.getElementById("link_campiaggiuntivi").className='activelink';
    scheda = 'campiaggiuntivi';
    link='link_campiaggiuntivi';
}
function vis_attivita()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("attivita").style.display='block';
    document.getElementById("link_attivita").className='activelink';
    scheda = 'attivita';
    link='link_attivita';
}
function vis_pareri()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("pareri").style.display='block';
    document.getElementById("link_pareri").className='activelink';
    scheda = 'pareri';
    link='link_pareri';
}

function vis_modelli()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("modelli").style.display='block';
    document.getElementById("link_modelli").className='activelink';
    scheda = 'modelli';
    link='link_modelli';
}
function vis_attori()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("attori").style.display='block';
    document.getElementById("link_attori").className='activelink';
    scheda = 'attori';
    link='link_attori';
}
function vis_documenti()
{
    document.getElementById(scheda).style.display='none';
    document.getElementById(link).className='';
    document.getElementById("documenti").style.display='block';
    document.getElementById("link_documenti").className='activelink';
    scheda = 'documenti';
    link='link_documenti';
}

var vettoreTr =new Array();
var numTr = 1;
var i=0;
function aggiungiAllegati(){
    var table=document.getElementById("tableAllegati");
    var tbody = table.getElementsByTagName("TBODY")[0];

    if(tbody.childNodes.length == 2 && !document.all){
        numTr = 1;
        vettoreTr =new Array();
    }else if(tbody.childNodes.length == 1 && document.all){
        numTr = 1;
        vettoreTr =new Array();
    }

    var tr=document.createElement("tr");
    var th1=document.createElement("td");
    var th2=document.createElement("td");

    //creo il link per cancellare un tr
    var th3=document.createElement("td");
    var a=document.createElement("a");
    a.setAttribute("href", "javascript:eliminaAllegati("+numTr+")");
    var linkDel = document.createTextNode('Elimina');
    a.appendChild(linkDel);
    th3.appendChild(a);

    var inputText=document.createElement("input");
    inputText.setAttribute("type","text");
    inputText.setAttribute("name", "allegati["+i+"].nomeDocumento");
   // inputText.setAttribute("size", "30");
    inputText.setAttribute("maxlength", "255");

    var nome = document.createTextNode('Descrizione allegato ');
    
    th1.appendChild(nome);
    th2.appendChild(inputText);
    
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);

    tbody.appendChild(tr);
    vettoreTr[numTr]=tr;
    numTr++;
    i++;
}

function eliminaAllegati(elemento){
    var table=document.getElementById("tableAllegati");
    var tbody = table.getElementsByTagName("TBODY")[0];
    var tr = vettoreTr[elemento];
    tbody.removeChild(tr);
}

var vettoreTr2 =new Array();
var numTr2 = 1;
var j=0;
function aggiungiModelli(){
    var table=document.getElementById("tableModelli");
    var tbody = table.getElementsByTagName("TBODY")[0];

    if(tbody.childNodes.length == 2 && !document.all){
        numTr2 = 1;
        vettoreTr2 =new Array();
    }else if(tbody.childNodes.length == 1 && document.all){
        numTr2 = 1;
        vettoreTr2 =new Array();
    }

    var tr=document.createElement("tr");
    var th1=document.createElement("td");
    var th2=document.createElement("td");

    //creo il link per cancellare un tr
    var th3=document.createElement("td");
    var a=document.createElement("a");
    a.setAttribute("href", "javascript:eliminaModelli("+numTr2+")");
    var linkDel = document.createTextNode('Elimina');
    a.appendChild(linkDel);
    th3.appendChild(a);

    var inputText=document.createElement("input");
    inputText.setAttribute("type","text");
    inputText.setAttribute("name", "modelli["+j+"].nome");
    //inputText.setAttribute("size", "30");
    inputText.setAttribute("maxlength", "255");

    var inputFile=document.createElement("input");
    inputFile.setAttribute("type", "file");
    inputFile.setAttribute("name", "modelli["+j+"].contenuto");

    var nome = document.createTextNode('Descrizione modello ');
    var file = document.createTextNode('File: ');

    th1.appendChild(nome);
    th1.appendChild(inputText);
    th2.appendChild(file);
    th2.appendChild(inputFile);

    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);

    tbody.appendChild(tr);
    vettoreTr2[numTr2]=tr;
    numTr2++;
    j++;
}
function eliminaModelli(elemento){
    var table=document.getElementById("tableModelli");
    var tbody = table.getElementsByTagName("TBODY")[0];
    var tr = vettoreTr2[elemento];
    tbody.removeChild(tr);
}