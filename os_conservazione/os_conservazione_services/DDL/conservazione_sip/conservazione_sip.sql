-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: conservazione_sip
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `conservazione_sip`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `conservazione_sip` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `conservazione_sip`;

--
-- Table structure for table `conservazione_sip`
--

DROP TABLE IF EXISTS `conservazione_sip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conservazione_sip` (
  `id_conservazione` varchar(100) NOT NULL COMMENT 'Indica l''ID SIP ricevuto dal sistema di conservazione',
  `processato` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Indica se il record è stato processato. Il suo valore di default è 0 (false)\nQuando è processato sarà pari ad 1 (true)',
  `errore` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Indica se dopo il processamento è avvenuto qualche errore o meno\nIl valore di default è 0 ovvero false --> nessun errore',
  `sip_result_xml` longtext COMMENT 'In caso di errore viene salvato l''XML SIPResult.xml di risposta. Visualizzando questo XML si possono recuperare informazioni sull''errore',
  `data_processamento` datetime DEFAULT NULL COMMENT 'Datetime di processamento del record',
  `data_creazione` datetime NOT NULL COMMENT 'Data creazione del record',
  `folder` varchar(125) DEFAULT NULL COMMENT 'Nome del folder processato contenente i file log',
  PRIMARY KEY (`id_conservazione`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conservazione_sip`
--

LOCK TABLES `conservazione_sip` WRITE;
/*!40000 ALTER TABLE `conservazione_sip` DISABLE KEYS */;
/*!40000 ALTER TABLE `conservazione_sip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-05  9:10:11
