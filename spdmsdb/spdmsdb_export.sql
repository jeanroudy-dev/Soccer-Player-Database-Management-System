-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: spdmsdb
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '1fef32b8-c41c-11f0-ab77-e051d810c6ba:1-367';

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `player_id` int NOT NULL,
  `player_name` varchar(50) DEFAULT NULL,
  `player_position` varchar(50) DEFAULT NULL,
  `player_team` varchar(50) DEFAULT NULL,
  `matches_played` int DEFAULT NULL,
  `goals_scored` int DEFAULT NULL,
  `assists` int DEFAULT NULL,
  `minutes_played` int DEFAULT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'Kylian Mbappe','Forward','Real Madrid',25,22,8,2100),(2,'Erling Haaland','Forward','Manchester City',22,25,5,1900),(3,'Kevin De Bruyne','Midfielder','Manchester City',15,4,12,1100),(4,'Jude Bellingham','Midfielder','Real Madrid',20,15,10,1750),(5,'Mohamed Salah','Forward','Liverpool',24,18,9,2050),(6,'Virgil van Dijk','Defender','Liverpool',25,2,2,2250),(7,'Vinicius Junior','Forward','Real Madrid',21,12,11,1800),(8,'Bukayo Saka','Forward','Arsenal',24,13,12,2000),(10,'Harry Kane','Forward','Bayern Munich',22,28,8,1950),(11,'Robert','Defender','Bilbao',34,5,3,2300),(12,'Antoine Griezmann','Forward','Atletico Madrid',24,11,9,2000),(13,'Bruno Fernandes','Midfielder','Manchester United',25,8,10,2200),(14,'Son Heung-min','Forward','Tottenham',23,15,6,1900),(15,'Declan Rice','Midfielder','Arsenal',25,4,5,2200),(16,'Lautaro Martinez','Forward','Inter Milan',22,23,4,1850),(17,'Rafael Leao','Forward','AC Milan',21,7,8,1600),(18,'Alphonso Davies','Defender','Bayern Munich',18,1,3,1500),(19,'Federico Valverde','Midfielder','Real Madrid',24,3,5,2100),(20,'Martin Odegaard','Midfielder','Manchester United',23,7,8,1980),(21,'Jamal Musiala','Midfielder','Bayern Munich',19,10,6,1550),(22,'Florian Wirtz','Midfielder','Bayer Leverkusen',22,11,10,1850),(23,'Ruben Dias','Defender','Manchester City',21,0,1,1800),(24,'William Saliba','Defender','Arsenal',25,2,1,2250),(25,'Alisson Becker','Goalkeeper','Liverpool',20,0,0,1800),(26,'Jean Roudy','Striker','Real Madrid',26,25,15,2523),(30,'Will','Goalkeeper','Liverpool',56,45,23,3456);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-09  0:45:37
