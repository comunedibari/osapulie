-- Comuni.
insert into tb_comune (id, cap, nome, descrizione) values (1, '73100', 'Lecce', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (2, '73101', 'Lecce2', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (3, '73102', 'Lecce3', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (4, '73103', 'Lecce4', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (5, '73104', 'Lecce5', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (6, '73105', 'Lecce6', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (7, '73106', 'Lecce7', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (8, '73107', 'Lecce8', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (9, '73108', 'Lecce9', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (10, '73109', 'Lecce10', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (11, '73110', 'Lecce11', 'Comune di Lecce');
insert into tb_comune (id, cap, nome, descrizione) values (12, '73111', 'NordSa12', 'Comune di Nord Salento #12');
insert into tb_comune (id, cap, nome, descrizione) values (13, '73112', 'NordSa13', 'Comune di Nord Salento #13');
insert into tb_comune (id, cap, nome, descrizione) values (14, '73113', 'NordSa14', 'Comune di Nord Salento #14');

--TimbroConfig
insert into tb_timbro_config values(1,'','password','aspin',2,'','CosignAS0021',1);

-- Aree Aggregate
insert into tb_area_aggregata (id, nome, descrizione) values ( 1, 'Nord Salento', 'Area aggregata dei comuni del nord salento');
insert into tb_area_aggregata_comune (area_id, comune_id) values ( 1, 12);
insert into tb_area_aggregata_comune (area_id, comune_id) values ( 1, 13);
insert into tb_area_aggregata_comune (area_id, comune_id) values ( 1, 14);

-- Cittadini
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ( 'cittadino_1', '               1', 'Doe #1', 'John #1', '1', 'via De Pippis #1', 1 );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ( 'cittadino_2', '               2', 'Doe #2', 'John #2', '2', 'via De Pippis #2', 2 );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ( 'cittadino_3', '               3', 'Doe #3', 'John #3', '3', 'via De Pippis #3', 3 );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ( 'cittadino_4', '               4', 'Doe #4', 'John #4', '4', 'via De Pippis #4', 4 );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ( 'cittadino_5', '               5', 'Doe #5', 'John #5', '5', 'via De Pippis #5', 5 );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ( 'cittadino_6', '               6', 'Doe #6', 'John #6', '6', 'via De Pippis #6', 6 );

INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ('10169','mccvtl','Birtolo','Maria Michela','1','via xxx',1);

INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, fk_ind_comune) VALUES ('16101','mccmhl','Rossi','Mario','1','via xxx',1);

insert into tb_profilo_utente_professionista values(1,'0123456789','10169');

insert into tb_delega values(1,0,'2012-02-13','2012-02-13',16101,10169,1);
insert into tb_delega_servizio values(36,1),(37,1),(38,1);

-- Fascicolo Utente
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 1, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 2, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 3, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 4, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 5, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 6, 'cittadino_1' );

INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 7, '10169' );

-- Anagrafica Servizi
/*INSERT INTO tb_servizio( id, nome_servizio, descrizione ) values ( 1, 'richiestaDatiAnagrafici', 'Richiesta dati anagrafici per Visura Posizione Anagrafica' );*/

INSERT INTO tb_servizio VALUES (1,'Il servizio consente la visualizzazione dei dati anagrafici per se stesso o per un componente del proprio nucleo familiare','VISURA POSIZIONE ANAGRAFICA','AV01','http://osapulie.sincon.it/it/web/guest/visura-posizione-anagrafica');
INSERT INTO tb_servizio VALUES (2,'Il servizio consente la visualizzazione dei dati elettorali per se stesso o per un componente del proprio nucleo familiare','VISURA POSIZIONE ELETTORALE','AV02','http://osapulie.sincon.it/it/web/guest/visura-posizione-elettorale');
INSERT INTO tb_servizio VALUES (3,'Il servizio consente la generazione del certificato contestuale di matrimonio con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO CONTESTUALE MATRIMONIO','AC03','http://osapulie.sincon.it/it/web/guest/richiesta-centificato-contestuale-matrimonio');
INSERT INTO tb_servizio VALUES (4,'Il servizio consente la generazione del certificato di cittadinanza con relativo download in formato elettronico PDF con timbro digitale.','RICHIESTA CERTIFICATO CITTADINANZA','AC04','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-cittadinanza');
INSERT INTO tb_servizio VALUES (5,'Il servizio consente la generazione del certificato di esistenza in vita con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO ESISTENZA IN VITA','AC05','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-in-vita');
INSERT INTO tb_servizio VALUES (6,'Il servizio consente la generazione del certificato di matrimonio con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO MATRIMONIO','AC06','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-matrimonio');
INSERT INTO tb_servizio VALUES (7,'Il servizio consente la generazione del certificato di morte con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO MORTE','AC07','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-morte');
INSERT INTO tb_servizio VALUES (8,'Il servizio consente la generazione del certificato di nascita con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO NASCITA','AC08','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-nascita');
INSERT INTO tb_servizio VALUES (9,'Il servizio consente la generazione del certificato di residenza con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO RESIDENZA','AC09','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-residenza');
INSERT INTO tb_servizio VALUES (10,'Il servizio consente la generazione del certificato di stato di famiglia con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO STATO FAMIGLIA','AC10','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-stato-famiglia');
INSERT INTO tb_servizio VALUES (11,'Il servizio consente la generazione del certificato di stato libero con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO STATO LIBERO','AC11','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-stato-libero');
INSERT INTO tb_servizio VALUES (12,'Il servizio consente la generazione del certificato di iscrizione alle liste elettorali con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO ISCRIZIONE LISTE ELETTORALI','AC12','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-iscrizione-liste-elettorali');
INSERT INTO tb_servizio VALUES (13,'Il servizio consente la generazione del certificato di vedovanza con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO VEDOVANZA','AC13','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-vedovanza');
INSERT INTO tb_servizio VALUES (14,'Il servizio consente la generazione del certificato dello storico delle variazioni domiciliari con relativo download in formato elettronico PDF con timbro digitale','RICHIESTA CERTIFICATO VARIAZIONI DOMICILIARI','AC14','http://osapulie.sincon.it/it/web/guest/richiesta-certificato-variazioni-domiciliari');
INSERT INTO tb_servizio VALUES (15,'Il servizio consente la generazione guidata di autocertificazioni non previste dai servizi precedenti','AUTOCERTIFICAZIONI','AA15','http://osapulie.sincon.it/it/web/guest/autocertificazioni');
INSERT INTO tb_servizio VALUES (16,'Il servizio consente la generazione della dichiarazione di cambio residenza e relativo inoltro al Comune','DICHIARAZIONE CAMBIO RESIDENZA','AD16','http://osapulie.sincon.it/it/web/guest/dichiarazione-cambio-residenza');
INSERT INTO tb_servizio VALUES (17,'Il servizio consente la generazione della dichiarazione di cambio domicilio e relativo inoltro al Comune','DICHIARAZIONE CAMBIO DOMICILIO','AD17','http://osapulie.sincon.it/it/web/guest/dichiarazione-cambio-domicilio');
INSERT INTO tb_servizio VALUES (18,'Il servizio consente la generazione della richiesta di iscrizione temporanea all\'anagrafe e relativo inoltro al Comune','ISCRIZIONE ALL\'ANAGRAFE TEMPORANEA','AD18','http://osapulie.sincon.it/it/web/guest/iscrizione-all-anagrafe-temporanea');
INSERT INTO tb_servizio VALUES (19,'Il servizio consente la generazione della richiesta di iscrizione all\'albo scrutatori di seggio e relativo inoltro al Comune','ISCRIZIONE ALL\'ALBO SCRUTATORI DI SEGGIO','AD19','http://osapulie.sincon.it/it/web/guest/iscrizione-albo-scrutatori-di-seggio');
INSERT INTO tb_servizio VALUES (20,'Il servizio consente la generazione della richiesta di iscrizione all\'albo presidenti di seggio e relativo inoltro al Comune','ISCRIZIONE ALL\'ALBO PRESIDENTI DI SEGGIO','AD20','http://osapulie.sincon.it/it/web/guest/iscrizione-albo-presidenti-di-seggio');
INSERT INTO tb_servizio VALUES (21,'Il servizio consente la generazione della dichiarazione ICI e relativo inoltro al Comune','DICHIARAZIONE ICI','TD01','http://osapulie.sincon.it/it/web/guest/dichiarazione-ici');
INSERT INTO tb_servizio VALUES (22,'Il servizio consente la generazione della comunicazione ICI e relativo inoltro al Comune','COMUNICAZIONE ICI','TD02','http://osapulie.sincon.it/it/web/guest/comunicazione-ici');
INSERT INTO tb_servizio VALUES (23,'Il servizio consente la generazione della dichiarazione TARSU e relativo inoltro al Comune','DICHIARAZIONE TARSU','TD05','http://osapulie.sincon.it/it/web/guest/dichiarazione-tarsu');

INSERT INTO tb_servizio VALUES (24,'','PAGAMENTO ICI','TP03','http://osapulie.sincon.it/it/web/guest/pagamento-ici');
INSERT INTO tb_servizio VALUES (25,'','RICHIESTA DI RIMBORSO ICI','TD04','http://osapulie.sincon.it/it/web/guest/richiesta-rimborso-ici');
INSERT INTO tb_servizio VALUES (26,'','PAGAMENTO TARSU','TP06','http://osapulie.sincon.it/it/web/guest/pagamento-tarsu');
INSERT INTO tb_servizio VALUES (27,'','RICHIESTA DI RIMBORSO TARSU','TD07','http://osapulie.sincon.it/it/web/guest/richiesta-rimborso-tarsu');
INSERT INTO tb_servizio VALUES (28,'','COSAP/TOSAP - Visura concessione/autorizzazione occupazione permanente spazi ed aree pubblici','TV08','http://osapulie.sincon.it/it/web/guest/visura-emporanea-cosap-tosap');
INSERT INTO tb_servizio VALUES (29,'','COSAP/TOSAP - Visura concessione/autorizzazione occupazione temporanea spazi ed aree pubblici','TV09','http://osapulie.sincon.it/it/web/guest/visura-temporanea-cosap-tosap');
INSERT INTO tb_servizio VALUES (30,'','PAGAMENTO COSAP/TOSAP','TP10','http://osapulie.sincon.it/it/web/guest/pagamento-cosap-tosap');
INSERT INTO tb_servizio VALUES (31,'','RICHIESTA DI RIMBORSO COSAP/TOSAP','TD11','http://osapulie.sincon.it/it/web/guest/richiesta-rimborso-cosap-tosap');
INSERT INTO tb_servizio VALUES (32,'','SERVIZI CIMITERIALI - VISURA POSIZIONE','TV12','http://osapulie.sincon.it/it/web/guest/servizi-cimiteriali');
INSERT INTO tb_servizio VALUES (33,'','PAGAMENTO SERVIZI CIMITERIALI','TP13','http://osapulie.sincon.it/it/web/guest/pagamento-servizi-cimiteriali');
INSERT INTO tb_servizio VALUES (34,'','RICHIESTA DI RIMBORSO SERVIZI CIMITERIALI','TD14','http://osapulie.sincon.it/it/web/guest/rimborso-servizi-cimiteriali');
INSERT INTO tb_servizio VALUES (35,'','PAGAMENTO IMPOSTA INSEGNE E PUBBLICITA\' (ICP)','TD15','http://osapulie.sincon.it/it/web/guest/pagamento-imposta-insegne');
INSERT INTO tb_servizio VALUES (36,'','RICHIESTA DI RIMBORSO ICP','TD16','http://osapulie.sincon.it/it/web/guest/richiesta-rimborso-icp');
INSERT INTO tb_servizio VALUES (37,'','PAGAMENTO AFFISSIONI','TD17','http://osapulie.sincon.it/it/web/guest/pagamento-affissioni');
INSERT INTO tb_servizio VALUES (38,'','RICHIESTA DI RIMBORSO PER PUBBLICHE AFFISSIONI','TD18','http://osapulie.sincon.it/it/web/guest/rimborso-pubbliche-affissioni');
INSERT INTO tb_servizio VALUES (39,'','VISUALIZZAZIONE POSIZIONE TRIBUTARIA','TV19','http://osapulie.sincon.it/it/web/guest/visualizzazione-posizione-tributaria');

update tb_servizio set uri = CONCAT('http://192.0.0.17:8080/',SUBSTR(uri,27));

-- Servizi erogati da questo nodo
--INSERT INTO tb_servizio_erogato( id, fk_servizio, uri ) values ( 1, 1, 'http://localhost:8080/pdds/services/ServizioTest' );

-- La sequence per gli ID la modifichiamo manualmente altrimenti i test delle insert fallirebbero
UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 500 WHERE SEQ_NAME = 'SEQ_GEN';
