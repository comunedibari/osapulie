Vengono di seguite descritte le configurazioni applicabili all'applicativo
per le personalizzazioni del caso.

1) Configurazione IDP

L'aggiunta/rimozione/configurazione degli IDP disponibli è effettuabile agendo
su alcuni file di configurazione:

- web.xml
Al suo interno è necessario definire la posizione del file "wayfconfig.xml" sulla 
macchina ospitante l'applicativo

- wayfconfig.xml
Rappresenta il file nel quale definire gli IDP, mediante tag "MetadataProvider".
Per ogni tag è possibile definire:

- displayName: nome dell'IDP
- identifier: identificativo univoco dell'IDP
- backingFile & url: file xml con i metadati dell'IDP
- logo: immagine (da inserire in /shibboleth-discovery-service/src/main/webapp/images)
  		del bottone che permette il redirect verso la pagine di login dell'IDP
- group: identificativo univoco del gruppo di cui farà parte l'IDP; deve essere 
		 sempre valorizzato se l'IDP è il primo
- groupLogo: immagine (da inserire in /shibboleth-discovery-service/src/main/webapp/images)
		 	 associata al gruppo di IDP
- buttonCssClass: classe css da assegnare al bottone che aggrega al suo interno gli IDP sites 

- GroupDescription: testo descrittivo associato al gruppo di IDP
- GroupLinks (opzionale): definisce gli eventuali link statici da aggiungere al gruppo di IDP
- Link (opzionale): nome (attributo "name") del link ed url (valore del tag) che compongono il tag <a>
		di un link
								 
N.B.: il primo tag "MetadataProvider" deve rappresentare il Service Provider in questione,
nel quale devono essere presenti, internamente al tag "md:SPSSODescriptor", le "md:Extensions"
utili ad individuare il discovery service (coppia di tag "init:RequestInitiator" e "idpdisc:DiscoveryResponse"
 per ogni AssertionConsumerService esistente (collegato tramite valore dell'attributo "index")).

2) Funzionalità avanzate
- Scenario con link di bypass per accesso al sistema con le credenziali locali (default)
  Rappresenta lo scenario in cui l'utente può scegliere tra più IDP, oppure scegliere di 
  bypassare l'accesso per procedere con il login locale al portale mediante link apposito.
- Scenario senza link di bypass
  E' lo scenario in cui il login locale al portale vuol essere trattato al pari dei login
  tramite IDP. Per l'attivazione di questa funzionalità è sufficiente creare un MetadataProvider 
  ad-hoc a cui far corrispondere un file XML  di IDP "fake" in cui l'attributo "entityID" deve essere
  valorizzato con la stringa "fake".  