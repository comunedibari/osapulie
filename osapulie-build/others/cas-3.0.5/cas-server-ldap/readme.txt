===============================================================================
1. INTRODUZIONE
===============================================================================

Questo progetto contiene il codice modificato del modulo LDAP di CAS 3.0.5. La modifica ha coinvolto il 
file BindLdapAuthenticationHandler (riga 82).

Descrizione del problema: la libreria di ricerca LDAP (ldaptemplate), nel caso di utente dotato di smartcard 
ma che si registra tramite form, restituisce come principal name una stringa quotata (ossia, una stringa che 
è delimitata da doppi apici). Ad esempio: 

======= Va bene
String principal = cn=bruno,ou=persone,dc=servizionline,dc=it

Environment:
{java.naming.provider.url=ldap://localhost:389/, java.naming.factory.initial=com.sun.jndi.ldap.LdapCtxFactory, java.naming.security.principal=cn=bruno,ou=persone,dc=servizionline,dc=it, java.naming.security.authentication=simple, java.naming.security.credentials=bruno}

======= Non va bene
String principal = "cn=NGRGCR66S01E506X/7420050800086920.HoWzUj09sfM9TpjuIw2sejOcbbU\=",ou=persone,dc=servizionline,dc=it

--------------- Il Principal viene creato male (non deve avere apici)
Environment:
{java.naming.provider.url=ldap://localhost:389/, java.naming.factory.initial=com.sun.jndi.ldap.LdapCtxFactory, java.naming.security.principal="cn=NGRGCR66S01E506X/7420050800086920.HoWzUj09sfM9TpjuIw2sejOcbbU\=",ou=persone,dc=servizionline,dc=it, java.naming.security.authentication=simple, java.naming.security.credentials=osapulie11}

Errore:
javax.naming.NamingException: [LDAP: error code 1 - The provided value ""cn=NGRGCR66S01E506X/7420050800086920.HoWzUj09sfM9TpjuIw2sejOcbbU\=",ou=persone,dc=servizionline,dc=it" could not be parsed as a valid distinguished name because character '"' at position 0 is not allowed in an attribute name]

===============================================================================
1. BUILD & INSTALLAZIONE
===============================================================================

Esportare il progetto come JAR includendo le classi e la dir META-INF (niente librerie dipendenti).

Sostituire in $CAS_HOME/WEB-INF/lib il file cas-server-ldap-3.0.5.jar con quello prodotto.

