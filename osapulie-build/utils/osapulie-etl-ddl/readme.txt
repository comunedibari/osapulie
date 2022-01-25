===============================================================================
 Generatore di script DDL 
===============================================================================

1. Introduzione

Quando si cambia il modello JPA (le classi) bisogna modificare lo schema del DB
(MySQL): questa utility si occupa di generare gli script (di create e drop) e 
salvarli in target/ (ci sono delle limitazioni: vedi dopo).

2. Esecuzione

mvn exec:java -Dexec.mainClass="it.osapulie.etl.ddl.DDLGeneratorTool" -Dexec.classpathScope=runtime

3. Limitazioni note

* Assicurarsi che il files src/main/resources/ddl-persistence.xml sia effettivamente
  aggiornato con l'elenco di tutte le classi JPA utilizzate nel sistema, 
  altrimenti lo schema potrebbe (nel migliore dei casi) risultare incompleto.
* E' supportato solo MySQL out-of-the-box: per altri dialect consultare il codice!
