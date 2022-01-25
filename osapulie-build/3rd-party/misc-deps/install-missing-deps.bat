echo "Installazione librerie SCATEL nel repository MAVEN locale ..."
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=egov-busta-api -Dpackaging=jar -Dversion=1.0 -Dfile=lib\rupar\Busta.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=egov-nica-api -Dpackaging=jar -Dversion=2.5 -Dfile=lib\rupar\api-NICA-2.5.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=egov-pdds -Dpackaging=jar -Dversion=3.0 -Dfile=lib\rupar\pdds.3.0.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=egov-pdds-kitqualificazione -Dpackaging=jar -Dversion=3.0 -Dfile=lib\rupar\pdds.3.0.kitqualificazione.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=egov-pdds-qualificazioneJaxb -Dpackaging=jar -Dversion=3.0 -Dfile=lib\rupar\pdds.3.0.qualificazioneJaxb.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=gateway -Dpackaging=jar -Dversion=1.0 -Dfile=lib\rupar\PDDS.gateway.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=gateway-serviziTest -Dpackaging=jar -Dversion=1.0 -Dfile=lib\rupar\PDDS.gateway.serviziTest.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=egov-pdds-qualificazione -Dpackaging=jar -Dversion=3.0 -Dfile=lib\rupar\PDDS.qualificazione.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=it.puglia.rupar -DartifactId=sslclient -Dpackaging=jar -Dversion=1.0 -Dclassifier=by-innovapuglia -Dfile=lib\rupar\SSLClient.jar -DgeneratePom=true
rem "Installazione libreria javax.persistence-2.0.3.jar"
call mvn install:install-file -DgroupId=org.eclipse.persistence -DartifactId=javax.persistence -Dpackaging=jar -Dversion=2.0.3 -Dfile=lib\other\javax.persistence-2.0.3.jar -DgeneratePom=true
rem "Installazione libreria org.eclipse.persistence.core-2.2.1.jar"
call mvn install:install-file -DgroupId=org.eclipse.persistence -DartifactId=rg.eclipse.persistence.core -Dpackaging=jar -Dversion=2.2.1 -Dfile=lib\other\org.eclipse.persistence.core-2.2.1.jar -DgeneratePom=true
rem "Installazione libreria org.eclipse.persistence.jpa-2.2.1.jar"
call mvn install:install-file -DgroupId=org.eclipse.persistence -DartifactId=org.eclipse.persistence.jpa -Dpackaging=jar -Dversion=2.2.1 -Dfile=lib\other\org.eclipse.persistence.jpa-2.2.1.jar -DgeneratePom=true
rem "Installazione libreria org.eclipse.persistence.asm-2.2.1.jar"
call mvn install:install-file -DgroupId=org.eclipse.persistence -DartifactId=org.eclipse.persistence.asm -Dpackaging=jar -Dversion=2.2.1 -Dfile=lib\other\org.eclipse.persistence.asm-2.2.1.jar -DgeneratePom=true
rem "Installazione libreria org.eclipse.persistence.antlr-2.2.1.jar"
call mvn install:install-file -DgroupId=org.eclipse.persistence -DartifactId=org.eclipse.persistence.antlr -Dpackaging=jar -Dversion=2.2.1 -Dfile=lib\other\org.eclipse.persistence.antlr-2.2.1.jar -DgeneratePom=true
rem "Libreria Liferay per il crypt delle password"
call mvn install:install-file -DgroupId=org.vps.crypt -DartifactId=crypt -Dpackaging=jar -Dversion=1.6 -Dfile=lib\other\crypt.jar -DgeneratePom=true
rem "Libreria taglibs-unstandard.jar"
call mvn install:install-file -DgroupId=org.apache.taglibs -DartifactId=unstandard -Dpackaging=jar -Dversion=1.0.N20060829 -Dfile=lib\other\taglibs-unstandard.jar -DgeneratePom=true
rem "Libreria pecmailclient.jar"
call mvn install:install-file -DgroupId=it.linksmt.pec -DartifactId=pecmailclient -Dpackaging=jar -Dversion=1.0 -Dfile=lib\other\pecmailclient.jar -DgeneratePom=true
rem "Libreria signature-verification-1.0.jar"
call mvn install:install-file -DgroupId=it.linksmt.tools -DartifactId=signature-verification -Dpackaging=jar -Dversion=1.1 -Dfile=lib\other\signature-verification-1.1.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=classes12 -DartifactId=classes12 -Dpackaging=jar -Dversion=1.0 -Dfile=lib\other\classes12.jar -DgeneratePom=true
rem "Libreria aaf-adoc-client"
call mvn install:install-file -DgroupId=it.linksmt -DartifactId=aaf-adoc-client -Dpackaging=jar -Dversion=1.0 -Dfile=lib\other\aaf-adoc-client-1.0.jar -DgeneratePom=true
rem "Libreria pagamenti-adapter"
call mvn install:install-file -DgroupId=it.linksmt.pagamenti -DartifactId=pagamenti-adapter -Dpackaging=jar -Dversion=0.1 -Dfile=lib\other\pagamenti-adapter-0.1.jar -DgeneratePom=true

call mvn install:install-file -DgroupId=org.eclipse.persistence -DartifactId=org.eclipse.persistence.core -Dpackaging=jar -Dversion=2.2.1 -Dfile=lib\org.eclipse.persistence.core-2.2.1.jar -DgeneratePom=true

rem "Fatto!"
