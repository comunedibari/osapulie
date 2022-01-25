CREATE TABLE tb_area_aggregata (ID BIGINT NOT NULL, descrizione VARCHAR(256), nome VARCHAR(64) NOT NULL UNIQUE, PRIMARY KEY (ID));
CREATE TABLE tb_comune (id BIGINT NOT NULL, cap VARCHAR(6) NOT NULL UNIQUE, descrizione VARCHAR(512), logo LONGBLOB, nome VARCHAR(64) NOT NULL, PRIMARY KEY (id));
CREATE TABLE tb_profilo_utente_cittadino (ID VARCHAR(255) NOT NULL, codice_fiscale VARCHAR(16) NOT NULL UNIQUE, cognome VARCHAR(128) NOT NULL, nome VARCHAR(128) NOT NULL, ind_nr_civico VARCHAR(16), ind_via VARCHAR(256), fk_ind_comune BIGINT NOT NULL, PRIMARY KEY (ID));
CREATE TABLE tb_fascicolo_utente (ID BIGINT NOT NULL, fk_cittadino VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE tb_richiesta_servizio (ID BIGINT NOT NULL, data_richiesta DATETIME, data_risposta DATETIME, nome_servizio VARCHAR(128) NOT NULL, fk_comune BIGINT, fk_fascicolo BIGINT, PRIMARY KEY (ID));
--CREATE TABLE tb_servizio (ID BIGINT NOT NULL, descrizione VARCHAR(255) NOT NULL, nome_servizio VARCHAR(64) UNIQUE, PRIMARY KEY (ID));
--CREATE TABLE tb_servizio_erogato (ID BIGINT NOT NULL, uri VARCHAR(256) NOT NULL, fk_servizio BIGINT NOT NULL, PRIMARY KEY (ID));
CREATE TABLE tb_area_aggregata_comune (area_id VARCHAR(255) NOT NULL, comune_id BIGINT NOT NULL, PRIMARY KEY (area_id, comune_id));
ALTER TABLE tb_profilo_utente_cittadino ADD CONSTRAINT FK_tb_profilo_utente_cittadino_fk_ind_comune FOREIGN KEY (fk_ind_comune) REFERENCES tb_comune (id);
ALTER TABLE tb_fascicolo_utente ADD CONSTRAINT FK_tb_fascicolo_utente_fk_cittadino FOREIGN KEY (fk_cittadino) REFERENCES tb_profilo_utente_cittadino (ID);
ALTER TABLE tb_richiesta_servizio ADD CONSTRAINT FK_tb_richiesta_servizio_fk_fascicolo FOREIGN KEY (fk_fascicolo) REFERENCES tb_fascicolo_utente (ID);
ALTER TABLE tb_richiesta_servizio ADD CONSTRAINT FK_tb_richiesta_servizio_fk_comune FOREIGN KEY (fk_comune) REFERENCES tb_comune (id);
ALTER TABLE tb_servizio_erogato ADD CONSTRAINT FK_tb_servizio_erogato_fk_servizio FOREIGN KEY (fk_servizio) REFERENCES tb_servizio (ID);
ALTER TABLE tb_area_aggregata_comune ADD CONSTRAINT FK_tb_area_aggregata_comune_comune_id FOREIGN KEY (comune_id) REFERENCES tb_comune (id);
ALTER TABLE tb_area_aggregata_comune ADD CONSTRAINT FK_tb_area_aggregata_comune_area_id FOREIGN KEY (area_id) REFERENCES tb_area_aggregata (id);
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME));
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);

/**TABELLA SERVIZIO MODIFICATO**/
CREATE TABLE tb_servizio (ID BIGINT NOT NULL, descrizione VARCHAR(255) NOT NULL, nome_servizio VARCHAR(255) NOT NULL,codice_servizio VARCHAR(5) UNIQUE,uri VARCHAR(256) NOT NULL, PRIMARY KEY (ID));

drop table tb_servizio_erogato;


/*** modifica richiesta servizio ***/
alter table tb_richiesta_servizio add fk_delegante VARCHAR(255);
ALTER TABLE tb_richiesta_servizio ADD CONSTRAINT FK_tb_richiesta_servizio_fk_delegante FOREIGN KEY (fk_delegante) REFERENCES tb_profilo_utente_cittadino (ID);


CREATE TABLE tb_timbro_config (ID BIGINT NOT NULL, timbroDigitaleDomain VARCHAR(128), timbroDigitalePassword VARCHAR(128), timbroDigitalePin VARCHAR(128), timbroDigitaleServiceId INTEGER, timbroDigitaleSignServerHost VARCHAR(128), timbroDigitaleUsername VARCHAR(128), fk_id_comune_isa BIGINT, PRIMARY KEY (ID));
ALTER TABLE tb_timbro_config ADD CONSTRAINT FK_tb_timbro_config_fk_id_comune_isa FOREIGN KEY (fk_id_comune_isa) REFERENCES tb_comune_isa (ID);
