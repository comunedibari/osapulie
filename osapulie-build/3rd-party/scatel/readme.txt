===============================================================================
 Libreria SCATEL (RUPAR-Puglia)
===============================================================================

1. Introduzione

Il RUPAR-Puglia ha prodotto una libreria, SCATEL, per l'interfacciamento con le
Porte di Dominio (Delegate e Applicative). Dato che RUPAR non definisce un repository
maven, risulta essenziale definire questo meta-POM che raggruppa le libreria RUPAR
(pdds, NICA-api, e busta e-gov) e le dipendenze correlate (AXIS 1.4, IAIK per la 
crittografia).

Prima di installare questo package, assicurarsi che le librerie definite in ..\misc-deps
siano state installate manualmente nel proprio repository locale (o nell'eventuale
repository di rete locale, se disponibile)

2. Installazione

Questo package fa parte del pachetto "3rd-party" e, quindi, viene installato quando 
viene installato il primo.