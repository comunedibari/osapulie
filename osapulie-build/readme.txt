===============================================================================
 Build Progetto OSAPulie 
===============================================================================

1. Introduzione

La cartella "osapulie-build" contiene tutti i sorgenti che realizzano il portale
OSApulie. 
La cartella suddivide i moduli in base al loro contesto e mediante maven è
possibile generare tutti i compilati necessari al corretto funzionamento del sistema.

2. Esecuzione

Per la prima installazione eseguire gli eseguibili install-missing-deps.sh (o .bat) 
presenti nella folder "misc-deps".

Dunque lanciare il comando:

mvn clean install

N.B.: maven deve essere lanciato tramite JDK 1.6.x (e non 1.7.x)

3. Limitazioni

CAS (Central Authentication Service), presente sotto "others", non è installabile
mediante maven ma va installato tramite gli appositi script ant. 