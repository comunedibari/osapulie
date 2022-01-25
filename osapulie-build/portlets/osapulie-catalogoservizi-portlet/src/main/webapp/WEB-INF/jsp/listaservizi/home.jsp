<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ include file="../common/common.jsp"%>
<%
ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
%>
<style>
div.ori{
color: black;
 background-color: white;
}
div.rot {
color: black;
 background-color: white;
	transform: rotate(90deg);
}

bb {
	color: #666;
    text-decoration: none;
    font-weight: bold;
  }
  
.overlay{
   
    position:fixed;
    top:0px;
    bottom:0px;
    left:0px;
    right:0px;
    z-index:100;
	cursor:pointer;
/*Trasperenza cross browser*/
opacity: .7; filter: alpha(opacity=70);
-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=70)";	

}

div#load{
position: fixed;
    z-index: 2;
    top: 10px;
    left: 100px;
	right: 100px;
    width: 10px;
    height: 10px;

}
div#wait{

margin-left: 500px;
    margin-top: 250px;
}

</style>


<script type="text/javascript">

 
    function test() { 
        $.ajax({ url: 'wait.php', cache: false }); 
    } 
	
 $(document).ready(function() { 
 
 $(document).ajaxStop(function (){
	
			$('#wait').fadeOut('slow'); 
	
	}); 

$('a').click(
	    function(){
					
			 $('#load').fadeIn('slow');
			// setTimeout( function(){$('#wait').fadeIn('slow')}, 100); 
			 $("#wait").fadeIn('slow')
		
			$('#overlay').fadeIn('slow');
			$('#overlay').fadeOut('slow'); 
			
});
});
</script>
<div class="mainDiv">
<div  class="overlay" id="overlay" style="display:none;"></div>   
<div  id="load" style="display:none;" > <img src="http://malsup.com/jquery/block/busy.gif" id="wait" style="display:none;margin-left: 700px;
    margin-top: 300px;"></div>

	<% 
	if (themeDisplay.getLayout().isPrivateLayout()) {
	%>
	<c:choose>
		<c:when test="${comuneSelezionato != null && comuneSelezionato.id != -1}">
			<div class="comuneSelezionatoTitle">
				<c:choose>
					<c:when test="${isProfiloAzienda}">
						<spring:message code="label.serviziPerAziendaComuneDi" arguments="${comuneSelezionato.nome}"/>
					</c:when>
					<c:otherwise>
						<spring:message code="label.serviziAlCittadinoComuneDi" arguments="${comuneSelezionato.nome}"/>
					</c:otherwise>
				</c:choose>
				<c:if test="${delega != null}">
					&nbsp;-&nbsp;<spring:message code="label.delegatoDa" arguments="${delega.delegante.cognome}&nbsp;${delega.delegante.nome},${delega.delegante.codiceFiscale}"/>
				</c:if>
			</div>
		</c:when>
		<c:otherwise>
			<p><spring:message code="label.primoAccesso.noComunePrferito" arguments="${profiloUtente.nome}&nbsp;${profiloUtente.cognome}"/></p>
			<p><spring:message code="label.primoAccesso.noComunePrferito1"/></p>
		</c:otherwise>
	</c:choose>
	<%} %>
	<c:choose>
		<c:when test="${!empty mappaServizi}">
			<c:forEach var="entry" items="${mappaServizi}" varStatus="counter">
				<div class="row">
					<div class="titoletto">
						<i class="fa fa-tag"></i>&nbsp;<c:out value="${entry.key.nome}"></c:out>
					</div>
					<c:if test="${!empty entry.value}">
						<ul class="elenco">
							<c:forEach var="servizio" items="${entry.value}" >
								<li>
									<c:set var="uriServizio" value="${servizio.uriScheda}"/>
									<c:if test="${!empty comune}">
										<c:set var="uriServizio" value="${servizio.uriScheda}?${InnerPortletConstants.PARAMETRO_ID_COMUNE}=${comune}"/>
									</c:if>
									<% 
									if (themeDisplay.getLayout().isPrivateLayout()) {
									%>
									<c:set var="uriServizio" value="${servizio.uri}"/>
									<%} %>	
									<c:if test="${servizio.codiceServizio=='AD16'}">
									<a>
									</c:if>
									<c:if test="${servizio.codiceServizio!='AD16'}">
							   		<a href="${uriServizio}">
							   		</c:if>
						   				<% 
										if (!themeDisplay.getLayout().isPrivateLayout() && (request.getAttribute("comune") == null || request.getAttribute("comune").equals(""))) {
										%>
						   				<i class="fa fa fa-circle-o"></i>&nbsp;
						   				<%} else {%>
						   				<!-- REGIONE_PUGLIA -->
						   				<c:set var="regionePugliaEnable" value="false" />
										<c:forEach var="authenticationChannel" items="${authenticationChannels}">
											<c:if test="${authenticationChannel eq AuthenticationChannel.REGIONE_PUGLIA}">
												<c:set var="regionePugliaEnable" value="true" />
											</c:if>
										</c:forEach>
						   				<c:if test="${regionePugliaEnable}">
							   				<c:choose>
								   				<c:when test="${servizio.autenticazioneForte}">
									   				<i class="fa fa-lock fa-lg marginRight5" title="<spring:message code="label.autenticazione.REGIONE_PUGLIA.forte"/>"></i>&nbsp;
								   				</c:when>
								   				<c:otherwise>
									   				<i class="fa fa-unlock fa-lg marginRight5" title="<spring:message code="label.autenticazione.REGIONE_PUGLIA.debole"/>"></i>&nbsp;
								   				</c:otherwise>
								   			</c:choose>
							   			</c:if>
							   			
							   			<!-- SPID -->
							   			<c:set var="spidEnable" value="false" />
							   			<c:forEach var="authenticationChannel" items="${authenticationChannels}">
											<c:if test="${authenticationChannel eq AuthenticationChannel.SPID}">
												<c:set var="spidEnable" value="true" />
											</c:if>
										</c:forEach>
							   			<c:if test="${spidEnable}">
							   				<i class="fa fa-spid-level${servizio.livelloAutenticazione} marginRight5" title="<spring:message code="label.autenticazione.SPID.livello" arguments="${servizio.livelloAutenticazione}"/>"></i>&nbsp;
							   			</c:if>
							   			<%} %>
										<c:if test="${servizio.codiceServizio!='AD16'}">
							   			<c:out value="${servizio.nomeServizio}"></c:out>
							   			</c:if>
									</a>
									<c:if test="${servizio.codiceServizio=='AD16'}">
										<bb><c:out value="${servizio.nomeServizio}"></c:out></bb>
							   			<button id="menu" onclick="visualizza('elencoDichiarazioni','bb')" style=" background-color: white; color: black;"><div id="bb" class="ori" >></div></button>
							   			
									<div class="descrizioneServizio">
									<ul style="display:none;" id="elencoDichiarazioni" >
									
									<li><a href="${uriServizio}" onauxclick="setCookie('tipoDichiarazione','altroComune', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)" onClick="setCookie('tipoDichiarazione','altroComune', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)" >Dichiarazione di residenza con provenienza da altro comune.</a></li>
									<li><a href="${uriServizio}" onauxclick="setCookie('tipoDichiarazione','estero', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)" onClick="setCookie('tipoDichiarazione','estero', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)">Dichiarazione di residenza con provenienza dall'estero.</a></li>
									<li><a href="${uriServizio}" onauxclick="setCookie('tipoDichiarazione','aire', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)" onClick="setCookie('tipoDichiarazione','aire', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)">Dichiarazione di residenza di cittadini italiani iscritti all'AIRE (Anagrafe degli italiani residenti all'estero) con provenienza dall'estero</a></li>
									<li><a href="${uriServizio}" onauxclick="setCookie('tipoDichiarazione','stessoComune', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)" onClick="setCookie('tipoDichiarazione','stessoComune', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)">Dichiarazione di cambiamento di abitazione nell'ambito dello stesso comune.</a></li>
									<li><a href="${uriServizio}" onauxclick="setCookie('tipoDichiarazione','altro', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)" onClick="setCookie('tipoDichiarazione','altro', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)">Iscrizione per ricomparsa da irreperibilita'</a></li>
									<li><a href="${uriServizio}" onauxclick="setCookie('tipoDichiarazione','altro', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)" onClick="setCookie('tipoDichiarazione','altro', 1) ; setCookie('descrizioneDichiarazione',$(this).html() ,1)">Iscrizione per ricomparsa per mancato rinnovo permesso di soggiorno</a></li>
									
									</ul>
									</div>
									
									</c:if>
									<c:if test="${servizio.codiceServizio!='AD16'}">
									<div class="descrizioneServizio">
										<c:out value="${servizio.descrizione}"/>
									</div>
									</c:if>
								</li>
							</c:forEach>	
						</ul>
					</c:if>
					<c:if test="${empty entry.value}">
						<spring:message code="label.noServizi"/>
					</c:if>	
				</div>	
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:if test="${comuneSelezionato != null && comuneSelezionato.id != -1}">
				<div>
					<spring:message code="label.noServizi"/>
				</div>		
			</c:if>						
		</c:otherwise>
	</c:choose>
</div>