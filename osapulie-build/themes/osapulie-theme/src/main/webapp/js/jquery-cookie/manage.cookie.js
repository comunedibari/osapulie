jQuery(document).ready(function () {
       
	  #* Aggiunta Link Cookie *#
	  #set($urCookie = $themeDisplay.getPortalURL()+$themeDisplay.getPathFriendlyURLPublic()+$themeDisplay.getLayout().getGroup().getFriendlyURL())

      jQuery.cookieCuttr({

        cookieAnalyticsMessage: "Questo sito fa uso di cookie (tecnici e analitici ad essi assimilabili) per migliorare l'esperienza di navigazione degli utenti e per raccogliere informazioni sull'utilizzo del sito stesso. Oltre ai precedenti il presente sito contiene componenti di terze parti (Facebook, Twitter, Google Map...) che utilizzano cookie di profilazione a scopi pubblicitari per i quali e' necessario prestre il consenso.<br/> Proseguendo nella navigazione nel sito si accetta l'uso di tutti i cookie di terze parti precedentemente elencati.<br/> Puo' conoscere i dettagli consultando la nostra informativa estesa",
        cookieWhatAreLinkText: "qui",
		cookieWhatAreTheyLink: "$urCookie/privacy",
		cookieAcceptButtonText: "OK"

      });
    });