-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sm
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `citas`
--

DROP TABLE IF EXISTS `citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `citas` (
  `Paciente` varchar(40) DEFAULT NULL,
  `Doctor` varchar(20) DEFAULT NULL,
  `Situacion` varchar(40) DEFAULT NULL,
  `Hora` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `consultorio` varchar(40) DEFAULT NULL,
  `Especialidad` varchar(40) DEFAULT NULL,
  `HoraF` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citas`
--

LOCK TABLES `citas` WRITE;
/*!40000 ALTER TABLE `citas` DISABLE KEYS */;
INSERT INTO `citas` VALUES ('augustofv.2012@gmail.com','wernant300@gmail.com','Revision General',930,'2023-06-14','consultorio 1','Atencion Primaria',960),('augustofv.2012@gmail.com','wernant300@gmail.com','Revision General',990,'2023-06-14','consultorio 1','Atencion Primaria',1020),('augustofv.2012@gmail.com','wernant300@gmail.com','Revision General',1080,'2023-06-15','consultorio 1','Atencion Primaria',1110),('augustofv.2012@gmail.com','wernant300@gmail.com','Revision General',480,'2023-06-30','consultorio 1','Atencion Primaria',510),('augustofv.2012@gmail.com','wernant300@gmail.com','Revision General',420,'2023-06-30','consultorio 1','Atencion Primaria',450);
/*!40000 ALTER TABLE `citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultorios`
--

DROP TABLE IF EXISTS `consultorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultorios` (
  `nombre` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultorios`
--

LOCK TABLES `consultorios` WRITE;
/*!40000 ALTER TABLE `consultorios` DISABLE KEYS */;
INSERT INTO `consultorios` VALUES ('consultorio 1');
/*!40000 ALTER TABLE `consultorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctores`
--

DROP TABLE IF EXISTS `doctores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctores` (
  `email` varchar(200) DEFAULT NULL,
  `especialidad` varchar(40) DEFAULT NULL,
  `FeProxC` date DEFAULT NULL,
  `HoProxC` int DEFAULT NULL,
  `horI` int DEFAULT NULL,
  `horF` int DEFAULT NULL,
  `consultorio` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctores`
--

LOCK TABLES `doctores` WRITE;
/*!40000 ALTER TABLE `doctores` DISABLE KEYS */;
INSERT INTO `doctores` VALUES ('wernant300@gmail.com','Atencion Primaria',NULL,NULL,180,1260,'consultorio 1');
/*!40000 ALTER TABLE `doctores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad`
--

DROP TABLE IF EXISTS `especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad` (
  `nombre` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad`
--

LOCK TABLES `especialidad` WRITE;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` VALUES ('Atencion Primaria');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `situaciones`
--

DROP TABLE IF EXISTS `situaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `situaciones` (
  `nombre` varchar(40) DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  `especialidad` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `situaciones`
--

LOCK TABLES `situaciones` WRITE;
/*!40000 ALTER TABLE `situaciones` DISABLE KEYS */;
INSERT INTO `situaciones` VALUES ('Revision General',30,'Atencion Primaria');
/*!40000 ALTER TABLE `situaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `nombre` varchar(20) DEFAULT NULL,
  `apellidos` varchar(20) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `contra` varchar(30) DEFAULT NULL,
  `nivel` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('Dxjxvwr#Kdcho','Ioruhv#Ydujdv','augustofv.2012@gmail.com','456654',1),('Dgplq','Dgplq','flores.vargas.augustoh@gmail.com','456654',3),('pdulr','wruuhqwh','wernant300@gmail.com','456654',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sm'
--

--
-- Dumping routines for database 'sm'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-15 17:35:34
