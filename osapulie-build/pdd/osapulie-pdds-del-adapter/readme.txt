===============================================================================
PDDS Adapter
===============================================================================

1. Contesto

Si tratta di un adapter che deve essere deployato sul Nodo PDDS per fornire l'interfaccia
necessaria al Nodo di Gestione di OS Apulie.

2. Installazione

 1. mvn clean install per buildare
 2. copiare target/*.jar in $PDDS_TOMCAT/webapps/pdds/WEB-INF/lib
 3. Aggiungere src/main/resources/server.config.fragment in $PDDS_TOMCAT/webapps/pdds/WEB-INF/server-config.wsdd
 4. rivviare il Nodo PDDS
 5. Verificare che il WSDL sia disponibile collegandosi a http://<pdds-tomcat-host>/pdds/services/PDDSAdapter?wsdl
 
3. Problemi noti

Potrebbero verificarsi dei problemi con l'utilizzo della PDDS con JAVA >= 1.6 a causa 
di ClassCastException dovuti a classi presenti in queste versioni della JDK che non
dovrebbero essere utilizzate.
Se ciò si dovesse verificare, aggiungere alle JAVA_OPTS del container le proprietà:

JAVA_OPTS="$JAVA_OPTS -Djavax.xml.soap.SOAPFactory=org.apache.axis.soap.SOAPFactoryImpl"
JAVA_OPTS="$JAVA_OPTS -Djavax.xml.soap.MessageFactory=org.apache.axis.soap.MessageFactoryImpl"
JAVA_OPTS="$JAVA_OPTS -Djavax.xml.soap.SOAPConnectionFactory=org.apache.axis.soap.SOAPConnectionFactoryImpl"