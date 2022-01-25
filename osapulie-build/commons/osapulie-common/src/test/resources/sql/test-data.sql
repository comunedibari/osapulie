-- Comuni.
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (1, '000001', '73100', 'Lecce', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (2, '000002', '73101', 'Lecce2', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (3, '000003', '73102', 'Lecce3', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (4, '000004', '73103', 'Lecce4', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (5, '000005', '73104', 'Lecce5', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (6, '000006', '73105', 'Lecce6', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (7, '000007', '73106', 'Lecce7', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (8, '000008', '73107', 'Lecce8', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (9, '000009', '73108', 'Lecce9', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (10, '000010', '73109', 'Lecce10', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (11, '000011', '73110', 'Lecce11', 'Comune di Lecce', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (12, '000012', '73111', 'NordSa12', 'Comune di Nord Salento #12', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (13, '000013', '73112', 'NordSa13', 'Comune di Nord Salento #13', 'http://dummy.foo.bar/services/comune' );
insert into tb_comune (id, codice_istat, cap, nome, descrizione, uri_servizio_gw ) values (14, '000014', '73113', 'NordSa14', 'Comune di Nord Salento #14', 'http://dummy.foo.bar/services/comune' );

-- Aree Aggregate
insert into tb_area_aggregata (id, nome, descrizione) values ( 1, 'Nord Salento', 'Area aggregata dei comuni del nord salento');
insert into tb_area_aggregata_comune (area_id, comune_id) values ( 1, 12);
insert into tb_area_aggregata_comune (area_id, comune_id) values ( 1, 13);
insert into tb_area_aggregata_comune (area_id, comune_id) values ( 1, 14);

-- Cittadini
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ( 'cittadino_1', '               1', 'Doe #1', 'John #1', '1', 'via De Pippis #1', 'citt1@pecmail.com', 1, null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ( 'cittadino_2', '               2', 'Doe #2', 'John #2', '2', 'via De Pippis #2', 'citt2@pecmail.com', 2, null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ( 'cittadino_3', '               3', 'Doe #3', 'John #3', '3', 'via De Pippis #3', 'citt3@pecmail.com', 3, null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ( 'cittadino_4', '               4', 'Doe #4', 'John #4', '4', 'via De Pippis #4', 'citt4@pecmail.com', 4, null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ( 'cittadino_5', '               5', 'Doe #5', 'John #5', '5', 'via De Pippis #5', 'citt5@pecmail.com', 5, null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ( 'cittadino_6', '               6', 'Doe #6', 'John #6', '6', 'via De Pippis #6', 'citt6@pecmail.com', 6, null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ('10169','mccmhl','Birtolo','Maria Michela','1','via xxx', 'birtolo@pecmail.com',1,null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ('22301','NGRGCR66S01E506X','Giancarlo','Giancarlo Negro','1','via rocco scotellaro', 'links@legalmail.it',1,null );
INSERT INTO tb_profilo_utente_cittadino (ID, codice_fiscale, cognome, nome, ind_nr_civico, ind_via, pec, fk_ind_comune, fk_professionista) VALUES ('11016','BRNGNNDJDJDJDJDJ','Bruno','Bruno Cogs','1','via rocco scotellaro', 'bruno@7cogs.com',1,null );

-- Professionisti
-- TODO 

-- Fascicolo Utente
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 1, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 2, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 3, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 4, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 5, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 6, 'cittadino_1' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 7, '10169' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 8, '11016' );
INSERT INTO tb_fascicolo_utente(ID,fk_cittadino) values ( 9, '22301' );

-- Anagrafica Servizi
INSERT INTO tb_servizio( id, nome_servizio, descrizione ) values ( 1, 'richiestaDatiAnagrafici', 'Richiesta dati anagrafici per Visura Posizione Anagrafica' );

-- Servizi erogati da questo nodo
INSERT INTO tb_servizio_erogato( id, fk_servizio, uri ) values ( 1, 1, 'http://localhost:8080/pdds/services/ServizioTest' );

-- La sequence per gli ID la modifichiamo manualmente altrimenti i test delle insert fallirebbero
UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 500 WHERE SEQ_NAME = 'SEQ_GEN';
