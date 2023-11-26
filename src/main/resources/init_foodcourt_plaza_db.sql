CREATE DATABASE  IF NOT EXISTS `foodcourt_plaza` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `foodcourt_plaza`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: foodcourt_plaza
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `category_table`
--

DROP TABLE IF EXISTS `category_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_table` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `restaurant_id` bigint NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_table`
--

LOCK TABLES `category_table` WRITE;
/*!40000 ALTER TABLE `category_table` DISABLE KEYS */;
INSERT INTO `category_table` VALUES (1,'Example Name',0),(2,'Example 2',0);
/*!40000 ALTER TABLE `category_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_table`
--

DROP TABLE IF EXISTS `dish_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_table` (
  `dish_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `url_image` varchar(255) DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `restaurant_id` bigint NOT NULL,
  PRIMARY KEY (`dish_id`),
  KEY `FKiypva9htwn7fyau9lns1j2v64` (`category_id`),
  KEY `FKa93um2gycclqjpq07varsvyvw` (`restaurant_id`),
  CONSTRAINT `FKa93um2gycclqjpq07varsvyvw` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant_table` (`restaurant_id`),
  CONSTRAINT `FKiypva9htwn7fyau9lns1j2v64` FOREIGN KEY (`category_id`) REFERENCES `category_table` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_table`
--

LOCK TABLES `dish_table` WRITE;
/*!40000 ALTER TABLE `dish_table` DISABLE KEYS */;
INSERT INTO `dish_table` VALUES (1,'New Example Description',_binary '\0','Example Name',8500,'www.example.com/image.png',1,12),(2,'New Example Description',_binary '','Example 2',8500,'www.example.com/image.png',2,12),(3,'Example3 Description',_binary '','Example 3',953300,'www.exampl3e.com/image.png',2,11);
/*!40000 ALTER TABLE `dish_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_dish_qty_table`
--

DROP TABLE IF EXISTS `order_dish_qty_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_dish_qty_table` (
  `dish_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `dish_qty` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`dish_id`,`order_id`),
  KEY `FK7hq4h4gicdofh34jtydhfyaem` (`order_id`),
  CONSTRAINT `FK5rlufmov74fb58ovh7vsyasf6` FOREIGN KEY (`dish_id`) REFERENCES `dish_table` (`dish_id`),
  CONSTRAINT `FK7hq4h4gicdofh34jtydhfyaem` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_dish_qty_table`
--

LOCK TABLES `order_dish_qty_table` WRITE;
/*!40000 ALTER TABLE `order_dish_qty_table` DISABLE KEYS */;
INSERT INTO `order_dish_qty_table` VALUES (1,18,5),(1,19,5),(2,17,5);
/*!40000 ALTER TABLE `order_dish_qty_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_table` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  `restaurant_id` bigint NOT NULL,
  `client_identity_number` int NOT NULL,
  `restaurant_employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKew3veuv1ycpu22fvcouiia34g` (`restaurant_id`),
  KEY `FKe40wn0jopaosi7o8l8wg4agi7` (`restaurant_employee_id`),
  CONSTRAINT `FKe40wn0jopaosi7o8l8wg4agi7` FOREIGN KEY (`restaurant_employee_id`) REFERENCES `restaurant_employee_table` (`employee_id`),
  CONSTRAINT `FKew3veuv1ycpu22fvcouiia34g` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant_table` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (17,'EN_PREPARACION',12,321,3),(18,'PENDIENTE',11,321,NULL),(19,'EN_PREPARACION',12,321,1);
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_employee_table`
--

DROP TABLE IF EXISTS `restaurant_employee_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_employee_table` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `UK_5epbd3nr2e6tpvwu1sf5au9fs` (`email`),
  KEY `FK8069mv0bmmxxpmw9jbl0d5clu` (`restaurant_id`),
  CONSTRAINT `FK8069mv0bmmxxpmw9jbl0d5clu` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant_table` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_employee_table`
--

LOCK TABLES `restaurant_employee_table` WRITE;
/*!40000 ALTER TABLE `restaurant_employee_table` DISABLE KEYS */;
INSERT INTO `restaurant_employee_table` VALUES (1,12,'employee@employee.com'),(3,12,'employee2@employee.com');
/*!40000 ALTER TABLE `restaurant_employee_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_table`
--

DROP TABLE IF EXISTS `restaurant_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_table` (
  `restaurant_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `nit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `url_logo` varchar(255) DEFAULT NULL,
  `owner_user_identity_number` int NOT NULL,
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_table`
--

LOCK TABLES `restaurant_table` WRITE;
/*!40000 ALTER TABLE `restaurant_table` DISABLE KEYS */;
INSERT INTO `restaurant_table` VALUES (10,'Example Address','Example Name','987654321','+573132222222','www.example.com/logo.png',123456789),(11,'Example Address','23516a1','123456789','+573132222222','www.example.com/logo.png',111),(12,'Example Address','23516a1','123456789','+573132222222','www.example.com/logo.png',111);
/*!40000 ALTER TABLE `restaurant_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-25 23:49:25
