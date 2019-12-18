CREATE DATABASE  IF NOT EXISTS `smart_health_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `smart_health_db`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: smart_health_db
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access_contract`
--

DROP TABLE IF EXISTS `access_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access_contract` (
  `contract_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `relationship` varchar(100) NOT NULL,
  `access_level` varchar(15) NOT NULL,
  `active` int(5) NOT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `access_contract_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_contract`
--

LOCK TABLES `access_contract` WRITE;
/*!40000 ALTER TABLE `access_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `access_log`
--

DROP TABLE IF EXISTS `access_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access_log` (
  `log_id` int(20) NOT NULL AUTO_INCREMENT,
  `access_time` datetime NOT NULL,
  `accessed_by` varchar(100) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `accessed_by` (`accessed_by`),
  CONSTRAINT `access_log_ibfk_1` FOREIGN KEY (`accessed_by`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_log`
--

LOCK TABLES `access_log` WRITE;
/*!40000 ALTER TABLE `access_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ailment`
--

DROP TABLE IF EXISTS `ailment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ailment` (
  `record_id` int(29) NOT NULL,
  `type` varchar(100) NOT NULL,
  `created` date NOT NULL,
  `descrption` varchar(255) NOT NULL,
  KEY `record_id` (`record_id`),
  CONSTRAINT `ailment_ibfk_1` FOREIGN KEY (`record_id`) REFERENCES `medical_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ailment`
--

LOCK TABLES `ailment` WRITE;
/*!40000 ALTER TABLE `ailment` DISABLE KEYS */;
/*!40000 ALTER TABLE `ailment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultation` (
  `record_id` int(29) NOT NULL,
  `type` varchar(100) NOT NULL,
  `created` date NOT NULL,
  `descrption` varchar(255) NOT NULL,
  KEY `record_id` (`record_id`),
  CONSTRAINT `consultation_ibfk_1` FOREIGN KEY (`record_id`) REFERENCES `medical_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation`
--

LOCK TABLES `consultation` WRITE;
/*!40000 ALTER TABLE `consultation` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency`
--

DROP TABLE IF EXISTS `emergency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emergency` (
  `emergency_id` int(20) NOT NULL AUTO_INCREMENT,
  `emergency_time` datetime NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `location_id` int(20) NOT NULL,
  `emergency_unit_id` int(20) NOT NULL,
  PRIMARY KEY (`emergency_id`),
  KEY `user_id` (`user_id`),
  KEY `location_id` (`location_id`),
  KEY `emergency_unit_id` (`emergency_unit_id`),
  CONSTRAINT `emergency_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emergency_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `geo_location` (`location_id`) ON UPDATE CASCADE,
  CONSTRAINT `emergency_ibfk_3` FOREIGN KEY (`emergency_unit_id`) REFERENCES `emergency_unit` (`emergency_unit_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency`
--

LOCK TABLES `emergency` WRITE;
/*!40000 ALTER TABLE `emergency` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_attendant`
--

DROP TABLE IF EXISTS `emergency_attendant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emergency_attendant` (
  `emergency_unit_id` int(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `middle_name` varchar(100) NOT NULL,
  `sir_name` varchar(100) DEFAULT NULL,
  `dob` date NOT NULL,
  `skill` varchar(255) NOT NULL,
  KEY `emergency_unit_id` (`emergency_unit_id`),
  CONSTRAINT `emergency_attendant_ibfk_1` FOREIGN KEY (`emergency_unit_id`) REFERENCES `emergency_unit` (`emergency_unit_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_attendant`
--

LOCK TABLES `emergency_attendant` WRITE;
/*!40000 ALTER TABLE `emergency_attendant` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergency_attendant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_unit`
--

DROP TABLE IF EXISTS `emergency_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emergency_unit` (
  `emergency_unit_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `health_unit_id` int(20) NOT NULL,
  `location_id` int(20) NOT NULL,
  `active` int(5) NOT NULL,
  PRIMARY KEY (`emergency_unit_id`),
  KEY `health_unit_id` (`health_unit_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `emergency_unit_ibfk_1` FOREIGN KEY (`health_unit_id`) REFERENCES `health_unit` (`health_unit_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emergency_unit_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `geo_location` (`location_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_unit`
--

LOCK TABLES `emergency_unit` WRITE;
/*!40000 ALTER TABLE `emergency_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergency_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geo_location`
--

DROP TABLE IF EXISTS `geo_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `geo_location` (
  `location_id` int(20) NOT NULL AUTO_INCREMENT,
  `longititude` decimal(10,8) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `error` decimal(10,9) NOT NULL,
  `location_descrption` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geo_location`
--

LOCK TABLES `geo_location` WRITE;
/*!40000 ALTER TABLE `geo_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `geo_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_unit`
--

DROP TABLE IF EXISTS `health_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_unit` (
  `health_unit_id` int(20) NOT NULL AUTO_INCREMENT,
  `location_id` int(20) NOT NULL DEFAULT '0',
  `descrption` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`health_unit_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `health_unit_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `geo_location` (`location_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_unit`
--

LOCK TABLES `health_unit` WRITE;
/*!40000 ALTER TABLE `health_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immunization`
--

DROP TABLE IF EXISTS `immunization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immunization` (
  `immune_id` int(20) NOT NULL AUTO_INCREMENT,
  `profile_id` varchar(100) NOT NULL,
  `administered` datetime NOT NULL,
  `descrption` varchar(255) NOT NULL,
  PRIMARY KEY (`immune_id`),
  KEY `profile_id` (`profile_id`),
  CONSTRAINT `immunization_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immunization`
--

LOCK TABLES `immunization` WRITE;
/*!40000 ALTER TABLE `immunization` DISABLE KEYS */;
/*!40000 ALTER TABLE `immunization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `madical_file`
--

DROP TABLE IF EXISTS `madical_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `madical_file` (
  `record_id` int(29) NOT NULL,
  `file_url` varchar(255) NOT NULL,
  `record_type` varchar(100) NOT NULL,
  `created` date NOT NULL,
  `descrption` varchar(255) NOT NULL,
  KEY `record_id` (`record_id`),
  CONSTRAINT `madical_file_ibfk_1` FOREIGN KEY (`record_id`) REFERENCES `medical_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `madical_file`
--

LOCK TABLES `madical_file` WRITE;
/*!40000 ALTER TABLE `madical_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `madical_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record`
--

DROP TABLE IF EXISTS `medical_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record` (
  `record_id` int(20) NOT NULL AUTO_INCREMENT,
  `patient_id` int(20) NOT NULL,
  `created` date NOT NULL,
  `practionner_id` varchar(100) DEFAULT NULL,
  `health_unit_id` int(20) DEFAULT NULL,
  `descrption` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `patient_id` (`patient_id`),
  KEY `practionner_id` (`practionner_id`),
  KEY `health_unit_id` (`health_unit_id`),
  CONSTRAINT `medical_record_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_record_ibfk_2` FOREIGN KEY (`practionner_id`) REFERENCES `practionner` (`practionner_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `medical_record_ibfk_3` FOREIGN KEY (`health_unit_id`) REFERENCES `health_unit` (`health_unit_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `patient_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  PRIMARY KEY (`patient_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_doctor`
--

DROP TABLE IF EXISTS `personal_doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_doctor` (
  `practionner_id` varchar(100) NOT NULL,
  `profile_id` varchar(100) NOT NULL,
  PRIMARY KEY (`practionner_id`,`profile_id`),
  KEY `profile_id` (`profile_id`),
  CONSTRAINT `personal_doctor_ibfk_1` FOREIGN KEY (`practionner_id`) REFERENCES `practionner` (`practionner_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `personal_doctor_ibfk_2` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_doctor`
--

LOCK TABLES `personal_doctor` WRITE;
/*!40000 ALTER TABLE `personal_doctor` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practionner`
--

DROP TABLE IF EXISTS `practionner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `practionner` (
  `user_id` varchar(100) NOT NULL,
  `practionner_id` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `skill` varchar(100) NOT NULL,
  `health_unit_id` varchar(100) NOT NULL,
  `descrption` varchar(255) NOT NULL,
  PRIMARY KEY (`practionner_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `practionner_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practionner`
--

LOCK TABLES `practionner` WRITE;
/*!40000 ALTER TABLE `practionner` DISABLE KEYS */;
/*!40000 ALTER TABLE `practionner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `record_id` int(29) NOT NULL,
  `medication` varchar(100) NOT NULL,
  `created` date NOT NULL,
  `descrption` varchar(255) NOT NULL,
  KEY `record_id` (`record_id`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`record_id`) REFERENCES `medical_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `real_time_data`
--

DROP TABLE IF EXISTS `real_time_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `real_time_data` (
  `real_time_id` int(20) NOT NULL AUTO_INCREMENT,
  `patient_id` int(20) NOT NULL,
  `record_type` varchar(100) NOT NULL,
  `created` datetime NOT NULL,
  `location_id` int(20) NOT NULL,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`real_time_id`),
  KEY `patient_id` (`patient_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `real_time_data_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `real_time_data_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `geo_location` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `real_time_data`
--

LOCK TABLES `real_time_data` WRITE;
/*!40000 ALTER TABLE `real_time_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `real_time_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surgery`
--

DROP TABLE IF EXISTS `surgery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surgery` (
  `record_id` int(29) NOT NULL,
  `created` date NOT NULL,
  `type` varchar(100) NOT NULL,
  `descrption` varchar(255) NOT NULL,
  KEY `record_id` (`record_id`),
  CONSTRAINT `surgery_ibfk_1` FOREIGN KEY (`record_id`) REFERENCES `medical_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surgery`
--

LOCK TABLES `surgery` WRITE;
/*!40000 ALTER TABLE `surgery` DISABLE KEYS */;
/*!40000 ALTER TABLE `surgery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `middle_name` varchar(100) NOT NULL,
  `sir_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone_nimber` varchar(64) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(15) NOT NULL,
  `nationality` varchar(100) NOT NULL,
  `profile_photo_url` varchar(100) DEFAULT NULL,
  `profile_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `profile_id` (`profile_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile` (
  `profile_id` varchar(100) NOT NULL,
  `blood_group` varchar(15) DEFAULT NULL,
  `special_needs` varchar(255) DEFAULT NULL,
  `complication` varchar(255) DEFAULT NULL,
  `insurance_information` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-14 12:48:17
