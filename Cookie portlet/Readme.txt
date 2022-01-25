ISTRUZIONI PER L'INSTALLAZIONE DELLA PORTLET PER IL BANNER DEI COOKIE

 - Aggiungere al file sul server "portlet-ext.properties" le seguenti property:
 
	layout.static.portlets.start.column-1=cookiesnotification_WAR_cookiesnotificationportlet

	cookies.accepted.journalarticle.id=596014

	cookies.accepted.journalarticle.groupid=10157

	cookies.accepted.version=1.0
	
Dove 596014 è l'id dell'articolo (Banner) creato sul portale. Per modificare il testo del banner basta modificare l'articolo in questione direttamente da pagina web. 

 - Deployare sul server il war della portlet per i cookie: "cookies-notification-porltet.war"