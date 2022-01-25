# OSAPULIE AREA VASTA BARI

Il seguente readme ha l'obiettivo di descrivere brevemente i moduli che compongono il portale Osapulie per Area Vasta e descrivere i prerequisiti e gli step necessari alla costruzione dell'artefatto

### INFORMAZIONI GENERALI

Osapulie-build si compone dei seguenti moduli:

 - **commons**, composto dai moduli:
	- **common** *(metodi in comune tra le portlet)*
	- **common-audit** *(modulo per la gestione del log audit)*
	- **common-types** *(classi comuni generate da xsd)*
	- **shared** *(vedere paragrafo su configurazione liferay e tomcat)*
 - **osapulie-liferay-hook** *(in ambiente di sviluppo non utilizzato)*
 - **pdd**, che contiene tutte le classi utilizzate dalla porta di dominio  *(in ambiente di sviluppo attualmente non utilizzato)*
 - **portlets**, composto dai moduli:
	- **anagrafiche-portlet** *(portlet per la gestione dell'anagrafica degli utenti)*
	- **catalogoservizi-portlet** *(portlet di accesso e descrizione dei servizi)*
	- **consultazionepraticheweb-portlet** *(portlet per la consultazione delle pratiche associate agli utenti)*
	- **fascicoloutente-ws** *(non utilizzato)*
	- **pagamenti-portlet** *(portlet per la compilazione dei bolletini di pagamento)*
	- **profiloutente-portlet** *(portlet per la gestione del profilo degli utenti)*
	- **servizicomune-portlet** *(portlet per la gestione dei servizi associati ai comuni)*
	- **sociali-portlet** *(portlet per i servizi di assistenza e volontariato)*
	- **tributi-portlet** *(portlet per la gestione dei servizi finanziari e delle tasse)*
	- **utilities-portlet** *(portlet per la stampa, per i test e per la gestione degli errori)*
 - **themes**, che contiene il tema usato da osapulie
 - **utils**, che contiene una utility per la sincronizzazione tra modello jpa e db


### FILE DI CONFIGURAZIONE APPLICATIVI

Le configurazioni dei moduli sono gestite dai propri **application.properties** e da profili maven opportuni, e le configurazioni dipendenti dagli ambienti sono censite nei vari file **configuration.properties** dei profili.
Per la gestione delle properties sono utilizzati i filters di maven: all'interno del progetto principale, osapulie-build, si troveranno le directory filters con le sottodirectory legate ai vari profili.


### PREREQUISITI BUILDING APPLICAZIONE

Per poter buildare l'applicativo è necessario che siano soddisfatti i seguenti prerequisiti sulla macchina target su cui buildare

**JDK**: è necessario che la JDK sia installata. Si richiede installazione di *JDK 1.6*
**Maven**: è necessario che il software Apache Maven sia installato sulla macchina. Il software è stato testato su *Apache Maven 3.5.2*. Quindi una qualsiasi versione di Maven 3.x dovrebbe essere sufficiente

### DATABASE

I DB per lo sviluppo sono installati sulla macchina **192.168.11.193:3306**, a cui si accede con le credenziali root/root, e sono:
- *osapulie*
- *lportal_osapulie*

### CONFIGURAZIONE SERVER LDAP

Il software utilizza LDAP come repository degli utenti, un piccolo dump di LDAP di produzione è il file "*backup_ldap.ldif*".
Le credenziali di accesso a LDAP sono: username "*dc=Directory Manager,dc=servizionline,dc=it*" e password "*area_vasta_ldap_admin_2018*".
Per cambiare le credenziali di accesso, loggarsi come admin al portale e poi configurazione -> autenticazione -> ldap


### BUILDING ARTEFATTI SOFTWARE

I progetti si basano su maven. Per la compilazione e l'import degli stessi in un IDE (e.g.) i passi da effettuare sono i seguenti:

- configurare sulla macchina target apache maven e jdk (i test sono stati effettuati con apache maven 3.5.2 e JDK oracle 1.6)
- clonare il repository GIT utilizzando l'apposito URL
- installare le librerie necessarie nel repository locale di maven utilizzando **install-missing-deps.sh** per macchine linux oppure **install-missing-deps.bat** per macchine windows, presenti nella cartella `osapulie-build\3rd-party\misc-deps\`
- configurare il proprio IDE di riferimento affinché utilizzi il maven configurato sulla macchina di sviluppo
- importare i progetti come progetti maven esistenti
- configurare nel proprio IDE l'utilizzo di un servlet container (e.g. apache tomcat)
- aggiungere nel gitignore i file che non si vogliono tenere sotto tracciatura git (per esempio tutti i file di configurazione del proprio IDE)


La scelta dell'IDE è lasciata al team di sviluppo e sarà compito degli sviluppatori importare e configurare il progetto come meglio si crede. Si noti che è obbligatorio non committare e pushare su GIT file di configurazione relativi agli IDE. Questi file se presenti verranno cancellati


### DETTAGLI ORGANIZZAZIONE MAVEN

Per la configurazione del progetto vengono utilizzati i filtri di maven. Nella directory ***osapulie-build*** è presente la directory filters contenente le configurazioni per i vari ambienti. I profili di riferimento per entrambi i progetti sono:

- **sviluppo**: rappresenta il profilo attivo per default e orientato allo sviluppo sulle macchine di development
- **collaudo**:
- **produzione**: 

Per costruire i vari artefatti digitare dopo aver lanciato i file .sh o .bat indicati in precedenza è sufficiente digitare:

`mvn clean install -Dmaven.test.skip=true`: verranno creati gli artefatti dell'ambiente di sviluppo (**attivo di default**)

`mvn clean install -Dmaven.test.skip=true -P collaudo`: 

`mvn clean install -Dmaven.test.skip=true -P produzione`: 

`mvn clean install -Dmaven.test.skip=true -P test_torre`:  verranno creati gli artefatti dell'ambiente di test di torre annunziata coincidente con la macchina 10.0.5.9

### DETTAGLI ORGANIZZAZIONE LIFERAY


E' stato utilizzato **liferay-portal-6.0.6** con **tomcat-6.0.29**, aggiungendo alla cartella '*deploy*' di liferay i war delle portlet utili per il funzionamento del portale.

Prima dell'esecuzione del portale bisogna effettuare delle operazioni preliminari:

- In ambiente di sviluppo liferay 6.0.6 deve essere installato nella directory `C:\eng\liferay\` in modo che l'indirizzo della cartella di deploy sia esattamente `C:\eng\liferay\liferay-portal-6.0.6\deploy`. 
  Nel caso si voglia cambiare bisogna modificare anche l'indirizzo di autodeploy salvato in liferay, loggando, tramite interfaccia web, come admin e andando poi in control panel -> plugin installation -> Install more portlets -> configuration

- nella cartella `${catalina.base}\lib\ext` va inserito il .jar creato dalla compilazione del modulo common-shared (presente in `osapulie-build\commons\osapulie-shared\target`)
- nella cartella `${catalina.base}\lib\ext` va inserito il .jar common-audit-0.0.1.jar creato dalla compilazione del modulo common-audit (presente in `osapulie-build\commons\common-audit\target`)
- nella cartella `${catalina.base}\lib\ext` va inserito il .jar activemq-all-5.4.0.jar (presente in `osapulie-build\3rd-party\misc-deps\lib\other`)
- nella cartella `${catalina.base}\lib\ext` va inserito il .jar logback-classic-1.1.3.jar (presente in `osapulie-build\3rd-party\misc-deps\lib\other`)
- nella cartella `${catalina.base}\lib\ext` va inserito il .jar logback-core-1.1.3.jar (presente in `osapulie-build\3rd-party\misc-deps\lib\other`)
- nella cartella `${catalina.base}\lib\ext` va inserito il .jar slf4j-api-1.7.25.jar (presente in `osapulie-build\3rd-party\misc-deps\lib\other`)

- aggiungere al file context.xml, presente nella cartella `${catalina.base}\conf`, il seguente codice all'interno dei tag <Context></Context>
```	
	<Resource 	auth="Container" 
				driverClassName="com.mysql.jdbc.Driver" 
				maxActive="10" 
				maxIdle="30" 
				maxWait="10000" 
				name="jdbc/osapulie" 
				password="root" 
				timeBetweenEvictionRunsMillis="1800000" 
				type="javax.sql.DataSource" 
				url="jdbc:mysql://indirizzo_macchina_db:3306/osapulie" 
				username="root" 
				validationQuery="Select 1"/>
	<Resource 	auth="Container" 
				driverClassName="com.mysql.jdbc.Driver" 
				maxActive="10" 
				maxIdle="30" 
				maxWait="10000" 
				name="jdbc/LiferayPool" 
				password="root" 
				timeBetweenEvictionRunsMillis="1800000" 
				type="javax.sql.DataSource" 
				url="jdbc:mysql://indirizzo_macchina_db:3306/lportal_osapulie" 
				username="root" 
				validationQuery="Select 1"/>
```

- nella cartella `${catalina.base}\webapps\ROOT\WEB-INF\classes` deve essere creato, se non già esistente, il file **portal-ext.properties** ed inserire le seguenti proprietà:

	- **jdbc.default.jndi.name=jdbc/LiferayPool** (*per il binding con il db lportal_osapulie*)
	- **plugin.repositories.trusted=** (*per evitare la ricerca di plugin per liferay*)
    - **plugin.repositories.untrusted=** (*per evitare la ricerca di plugin per liferay*)
	- **plugin.notifications.enabled=false** (*per evitare di ricevere notifiche sui plugin*)
	- **terms.of.use.required=false** (*per evitare di dover accettare le condizioni d'uso di liferay*)
	- **com.liferay.portal.util.HttpImpl.proxy.auth.type=username-password** (*in caso di proxy*)
	- **com.liferay.portal.util.HttpImpl.proxy.username=**(*username proxy, se presente*)
	- **com.liferay.portal.util.HttpImpl.proxy.password=**(*password proxy, se presente*)
	- **auto.deploy.enabled=true** (*per abilitare l'autodeploy dei .war*)
	- **osapulie.deployment.scenario3.authentication.channels=CAS** (*per la definizione dello scenario utilizzato da ISA*)
	- **permissions.user.check.algorithm=5** (*per un bug della versione 6.0.6 di liferay*)	
	- **pec.mail.session.mail.smtp.enable=FALSE** (*per disabilitare/abilitare l'invio di pec*)
	- **wacom.signature.sdk=AgAkAEy2cKydAQVXYWNvbQ1TaWduYXR1cmUgU0RLAgKBAgJkAACIAwEDZQA** (*sdk wacom*)

- In caso di installazione su windows, bisogna modificare il file `C:\eng\liferay\liferay-portal-6.0.6\tomcat-6.0.29\bin\setenv.bat` cambiando -XX:MaxPermSize=256m in -XX:MaxPermSize=512m

	## OSAPULIE WSSTUB

	E' il progetto per la gestione della porta di dominio applicativa nell'ambiente di sviluppo. Per poter buildare l'applicativo è necessario che siano soddisfatti i seguenti prerequisiti sulla macchina target su cui buildare:

	- **JDK**: è necessario che la JDK sia installata. Si richiede installazione di *JDK 1.8*
	- **Maven**: è necessario che il software Apache Maven sia installato sulla macchina. Il software è stato testato su *Apache Maven 3.5.2*. Quindi una qualsiasi versione di Maven 3.x dovrebbe essere sufficiente
	- **Server**: è stato utilizzato **apache-tomcat-8.5.29**, usando la porta 8181.

### TAVOLETTA GRAFICA WACOM

Per il corretto funzionamento della tavoletta wacom per l'acquisizione della firma grafometrica di dovrà installare sul pc a cui collegarla:
- Wacom-Signature-SDK-xxx-3.20.4.msi
- Wacom-Signature-SDK-SigCaptX-1.18.1.msi

Se si presentano problemi di visualizzazione dello schermo sulla tavoletta grafica, bisogna installare sul pc i driver DisplayLink:
- https://www.displaylink.com/downloads
