# Applicazione Gestione Magazzino

Per le configurazioni si utilizzano i profili di maven.
#
In particolare abbiamo i seguenti profili:
- **sviluppo**: profilo maven attivo di default. Va a prendere le configurazioni dinamiche dal file ${project.home}/filters/sviluppo/configuration.properties
- **collaudo**: profilo maven per ambiente di collaudo. Va a prendere le configurazioni dinamiche dal file ${project.home}/filters/collaudo/configuration.properties
- **produzione**: profilo maven per ambiente di produzione. Va a prendere le configurazioni dinamiche dal file ${project.home}/filters/produzione/configuration.properties

Per il progetto si utilizza
* JDK 1.8
* Tomcat 8
* Spring 5

Una volta clonato il progetto bisogna importarlo in eclipse come un progetto maven esistente
**NOTA** È stato utilizzato il content negotiato view resolver di spring. La view di default è HTML. Per invocare servizi rest ed ottenere JSON aggiungere l'estensione ***.json***. e.g. http://localhost:8080/GestioneRapportino/rest/recuperaModels.json
