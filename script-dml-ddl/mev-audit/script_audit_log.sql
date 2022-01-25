
CREATE TABLE `tb_audit` (
  `ID` bigint(20) NOT NULL,
  `userCod` varchar(45) DEFAULT NULL,
  `giorno_mese_anno` varchar(12) DEFAULT NULL,
  `file_name` varchar(250) DEFAULT NULL,
  `path_filesystem` varchar(250) DEFAULT NULL,
  `checksum` varchar(256) DEFAULT NULL,
  `data_creazione` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_ultima_modifica` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `codice_registro` varchar(45) DEFAULT NULL,
  `stato` char(1) NOT NULL DEFAULT 'F',
  `cons` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `tb_registro_audit_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `puntatore_alfresco` varchar(250) DEFAULT NULL,
  `data_creazione` date DEFAULT NULL,
  `flag_generato` char(1) NOT NULL DEFAULT '0',
  `path_filesystem` varchar(250) DEFAULT NULL,
  `file_name` varchar(250) DEFAULT NULL,
  `destinatario` varchar(500) DEFAULT NULL,
  `checksum` varchar(256) DEFAULT NULL,
  `codice_amministrazione` varchar(15) DEFAULT NULL,
  `codice_aoo` varchar(15) DEFAULT NULL,
  `soggetto_produttore2` varchar(500) DEFAULT NULL,
  `soggetto_produttore` varchar(500) DEFAULT NULL,
  `responsabile` varchar(250) DEFAULT NULL,
  `oggetto` varchar(100) DEFAULT NULL,
  `codice_registro` varchar(45) DEFAULT NULL,
  `progressivo` smallint(6) DEFAULT NULL,
  `anno` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_attributi_servizio` (
  `id` varchar(45) NOT NULL,
  `nome_attributo` varchar(45) NOT NULL,
  `valore_attributo` varchar(255) NOT NULL,
  `id_transazione` varchar(45) NOT NULL,
  `data_creazione` datetime NOT NULL,
  `id_servizio` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tbservizio_idx` (`id_servizio`),
  CONSTRAINT `fk_tbservizio` FOREIGN KEY (`id_servizio`) REFERENCES `tb_servizio` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `tb_dwh_datamining` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ETA_RICHIEDENTE` int(11) DEFAULT NULL,
  `SESSO` varchar(9) DEFAULT NULL,
  `COD_SERVIZIO` varchar(15) DEFAULT NULL,
  `COD_CITTADINO` varchar(45) DEFAULT NULL,
  `DATA_RICHIESTA` datetime DEFAULT NULL,
  `UUID_OPERAZIONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_dwh_geolocalizzazione` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `INDIRIZZO_IP` varchar(45) DEFAULT NULL,
  `LATITUDINE` varchar(45) DEFAULT NULL,
  `LONGITUDINE` varchar(45) DEFAULT NULL,
  `CITTA` varchar(45) DEFAULT NULL,
  `REGIONE` varchar(45) DEFAULT NULL,
  `CAP` varchar(15) DEFAULT NULL,
  `IS_EUROPEO` tinyint(1) DEFAULT NULL,
  `COD_SERVIZIO` varchar(10) DEFAULT NULL,
  `COD_USER` varchar(45) DEFAULT NULL,
  `DATA_CREAZIONE` datetime DEFAULT NULL,
  `UUID_OPERAZIONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_dwh_tempi_medi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_INIZIO` datetime DEFAULT NULL,
  `DATA_FINE` datetime DEFAULT NULL,
  `TEMPO_ESECUZIONE` double DEFAULT NULL,
  `COD_SERVIZIO` varchar(10) DEFAULT NULL,
  `NOME_SERVIZIO` varchar(245) DEFAULT NULL,
  `COD_USER` varchar(45) DEFAULT NULL,
  `UUID_OPERAZIONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_dwh_servizio_attribute` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(45) DEFAULT NULL,
  `DATA_EVENTO` datetime DEFAULT NULL,
  `COMUNE` varchar(35) DEFAULT NULL,
  `HOST_APP` varchar(45) DEFAULT NULL,
  `SERVIZIO_CODE` varchar(45) DEFAULT NULL,
  `SERVIZIO_NOME` varchar(85) DEFAULT NULL,
  `CITTADINO_USERID` varchar(45) DEFAULT NULL,
  `CITTADINO_ETA` varchar(10) DEFAULT NULL,
  `CITTADINO_SESSO` varchar(10) DEFAULT NULL,
  `CITTADINO_COMUNE` varchar(25) DEFAULT NULL,
  `CITTADINO_PROVINCIA` varchar(25) DEFAULT NULL,
  `CITTADINO_REGIONE` varchar(25) DEFAULT NULL,
  `CITTADINO_AUTENTICAZIONE_FORTE` tinyint(1) DEFAULT NULL,
  `CITTADINO_LIVELLO_AUTENTICAZIONE` int(11) DEFAULT NULL,
  `CITTADINO_CANALE_AUTENTICAZIONE` varchar(35) DEFAULT NULL,
  `ENTE_TIPO` varchar(45) DEFAULT NULL,
  `ENTE_PARTITA_IVA` varchar(25) DEFAULT NULL,
  `ENTE_USERID` varchar(45) DEFAULT NULL,
  `ENTE_COMUNE` varchar(25) DEFAULT NULL,
  `ENTE_PROVINCIA` varchar(25) DEFAULT NULL,
  `ENTE_REGIONE` varchar(25) DEFAULT NULL,
  `SERVIZIO_PARAMETRO1` varchar(255) DEFAULT NULL,
  `SERVIZIO_PARAMETRO2` varchar(255) DEFAULT NULL,
  `SERVIZIO_PARAMETRO3` varchar(255) DEFAULT NULL,
  `SERVIZIO_URI` varchar(245) DEFAULT NULL,
  `SERVIZIO_PROTOCOLLO` varchar(45) DEFAULT NULL,
  `SERVIZIO_DATA_RICHIESTA` datetime DEFAULT NULL,
  `SERVIZIO_AUTENTICAZONE` tinyint(1) DEFAULT NULL,
  `SERVIZIO_INIZIO` datetime DEFAULT NULL,
  `SERVIZIO_FINE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- inserito nei alert di Stefano
--ALTER TABLE `osapulie`.`tb_delega` 
--ADD COLUMN `firma_grafometrica` TINYINT(1) NULL DEFAULT NULL AFTER `checksum`;
--commit;


CREATE DATABASE `conservazione_sip` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE conservazione_sip;

CREATE TABLE `conservazione_sip` (
  `id_conservazione` varchar(100) NOT NULL COMMENT 'Indica l''ID SIP ricevuto dal sistema di conservazione',
  `processato` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Indica se il record Ã¨ stato processato. Il suo valore di default Ã¨ 0 (false)\nQuando Ã¨ processato sarÃ  pari ad 1 (true)',
  `errore` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Indica se dopo il processamento Ã¨ avvenuto qualche errore o meno\nIl valore di default Ã¨ 0 ovvero false --> nessun errore',
  `sip_result_xml` longtext COMMENT 'In caso di errore viene salvato l''XML SIPResult.xml di risposta. Visualizzando questo XML si possono recuperare informazioni sull''errore',
  `data_processamento` datetime DEFAULT NULL COMMENT 'Datetime di processamento del record',
  `data_creazione` datetime NOT NULL COMMENT 'Data creazione del record',
  `folder` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id_conservazione`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- STEFANO FIRMA GRAFO--
-- inserimento delle nuove informazioni da salvare su DB
ALTER TABLE `osapulie`.`tb_delega` 
ADD COLUMN `n_documento` VARCHAR(25) NULL DEFAULT NULL AFTER `fk_comune_isa`,
ADD COLUMN `data_scadenza` VARCHAR(15) NULL DEFAULT NULL AFTER `n_documento`,
ADD COLUMN `rilasciato_da` VARCHAR(128) NULL DEFAULT NULL AFTER `data_scadenza`,
ADD COLUMN `cf_operatore` VARCHAR(16) NULL DEFAULT NULL AFTER `rilasciato_da`,
ADD COLUMN `checksum` VARCHAR(45) NULL DEFAULT NULL AFTER `cf_operatore`,
ADD COLUMN `firma_grafometrica` TINYINT(1) NULL DEFAULT NULL AFTER `checksum`,
ADD COLUMN `allegato_documento` LONGBLOB NULL DEFAULT NULL AFTER `firma_grafometrica`;


-- inserimento flag agg_operatori per settare la possibilità di aggiungere o no operatori
ALTER TABLE osapulie.tb_azienda 
ADD COLUMN agg_operatori TINYINT(1) NULL DEFAULT '0' COMMENT '' AFTER fk_profiloutentecittadino;

ALTER TABLE `osapulie`.`tb_azienda` 
CHANGE COLUMN `tipo` `tipo` VARCHAR(20) NULL DEFAULT NULL;

-- per tutti i CAF già presenti in tabella setto l'opzione di aggiunta operatori a 1 
UPDATE osapulie.tb_azienda 
SET agg_operatori = 1
WHERE tipo = 'CAF';


-- Fine Stefano --