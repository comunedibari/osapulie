===============================================================================
 3rd Party libraries
===============================================================================

1. Introduzione

In maven i vari artifact da cui un'applicazione dipende vanno specificati uno
per uno all'interno del project POM. Per evitare di dover ripetere queste serie
di dichiarazioni, è stato creato un set di "meta-POM" che raggruppano logicamente
queste dipendenze (es., tutte le dipendenze di Spring, di EclipseLink, ...).
Le applicazioni devono quindi semplicemente dichiarare di dipendere da questo
speciali POM artifact che, però, devono essere installati manualmente nel 
repository maven locale (o di rete, se presente).

2. Pre-requisiti

Prima di installare questo package, assicurarsi che le librerie non disponibili su
repository maven pubblici (vedi misc-deps/) siano già presenti nel proprio repository 
locale.

2. Esecuzione

mvn clean install
(ed incrociate le dita!)
