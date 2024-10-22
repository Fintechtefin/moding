-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: payment
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.20.04.1

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

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `approved_at` datetime(6) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `requested_at` datetime(6) DEFAULT NULL,
  `payment_method_id` int DEFAULT NULL,
  `payment_status_id` int DEFAULT NULL,
  `payment_key` varchar(255) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FKjii2n6db6cje3km5ybsbgo8cl` (`payment_method_id`),
  KEY `FKkggljyawkbwc50f0fentflj8t` (`payment_status_id`),
  CONSTRAINT `FKjii2n6db6cje3km5ybsbgo8cl` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`payment_method_id`),
  CONSTRAINT `FKkggljyawkbwc50f0fentflj8t` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_status` (`payment_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'2024-03-30 09:54:26.000000',62,'2024-03-30 09:54:07.000000',1,1,'xMBvpmjnoD4yKeq5bgrpomkAMwWaPJVGX0lzW6YOQJ1w9NLR'),(2,'2024-03-30 15:59:13.000000',63,'2024-03-30 15:58:56.000000',1,5,'zJ4xY7m0kODnyRpQWGrNWKOPWqeKkB8Kwv1M9ENjbeoPaZdL'),(3,'2024-04-01 22:15:25.146092',74,'2024-04-01 22:15:25.146053',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(4,'2024-04-01 22:15:33.992306',75,'2024-04-01 22:15:33.992290',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(5,'2024-04-01 22:15:43.062131',76,'2024-04-01 22:15:43.062123',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(6,'2024-04-01 22:15:53.104153',77,'2024-04-01 22:15:53.104140',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(7,'2024-04-01 22:16:23.129813',78,'2024-04-01 22:16:23.129799',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(8,'2024-04-01 22:16:23.202619',81,'2024-04-01 22:16:23.202605',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(9,'2024-04-01 22:16:23.268410',82,'2024-04-01 22:16:23.268398',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(10,'2024-04-01 22:16:23.332401',83,'2024-04-01 22:16:23.332388',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(11,'2024-04-01 22:16:28.143101',79,'2024-04-01 22:16:28.143086',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(12,'2024-04-01 22:16:28.146118',80,'2024-04-01 22:16:28.146108',1,4,'gpMwnkjKyO6BYq7GWPVvgaKa5z9Gym'),(13,'2024-04-02 15:24:58.000000',103,'2024-04-02 15:24:39.000000',1,5,'tviva202404030024390MXR3'),(14,'2024-04-02 15:29:58.000000',104,'2024-04-02 15:29:41.000000',1,5,'tviva202404030029413KU84'),(15,'2024-04-03 00:32:02.000000',107,'2024-04-03 00:31:42.000000',1,5,'tviva20240403093142Zigr1'),(16,'2024-04-03 15:39:47.000000',111,'2024-04-03 15:39:29.000000',1,5,'tviva2024040400392967Vb3'),(17,'2024-04-03 16:26:42.000000',112,'2024-04-03 16:26:24.000000',1,5,'tviva202404040126248Bzh5'),(18,'2024-04-03 16:31:28.000000',113,'2024-04-03 16:31:08.000000',1,4,'tviva20240404013108ZjK97'),(19,NULL,117,'2024-04-03 16:36:59.000000',1,4,'tviva20240404013659X3YP3'),(20,'2024-04-03 16:37:23.000000',118,'2024-04-03 16:36:55.000000',1,4,'tviva20240404013655X3Yx5'),(21,'2024-04-03 16:37:23.000000',119,'2024-04-03 16:36:55.000000',1,4,'tviva202404040136556clK6'),(22,'2024-04-03 16:40:54.000000',120,'2024-04-03 16:40:25.000000',1,5,'tviva20240404014025ZkjC6'),(23,'2024-04-03 17:42:01.000000',121,'2024-04-03 17:41:42.000000',1,5,'tviva202404040241423m4X5'),(24,'2024-04-03 18:15:15.000000',122,'2024-04-03 18:15:00.000000',1,5,'tviva202404040315006gWl1'),(25,'2024-04-03 18:27:30.000000',123,'2024-04-03 18:27:16.000000',1,5,'tviva202404040327168Hwv7'),(26,'2024-04-03 18:28:51.000000',124,'2024-04-03 18:28:41.000000',1,4,'tviva20240404032841Zprx7'),(27,'2024-04-03 19:26:36.000000',125,'2024-04-03 19:26:10.000000',1,4,'tviva202404040426103q6R9'),(28,'2024-04-03 19:44:41.000000',126,'2024-04-03 19:44:21.000000',1,5,'tviva202404040444216k5e4'),(29,'2024-04-03 19:47:41.000000',127,'2024-04-03 19:47:03.000000',1,5,'tviva202404040447030tjO4'),(30,'2024-04-03 19:47:41.000000',128,'2024-04-03 19:47:26.000000',1,5,'tviva202404040447266kaI4'),(31,'2024-04-03 19:51:52.000000',129,'2024-04-03 19:51:18.000000',1,4,'tviva20240404045118XbTa4'),(32,'2024-04-03 19:53:26.000000',130,'2024-04-03 19:53:05.000000',1,4,'tviva20240404045305Zs2w8'),(33,'2024-04-03 19:53:29.000000',131,'2024-04-03 19:53:11.000000',1,4,'tviva202404040453110ttt3'),(34,'2024-04-03 19:53:34.000000',132,'2024-04-03 19:53:16.000000',1,4,'tviva20240404045316XbWv1'),(35,'2024-04-04 00:14:50.000000',133,'2024-04-04 00:14:31.000000',1,4,'tviva202404040914313GY08'),(36,'2024-04-04 00:23:32.000000',134,'2024-04-04 00:23:19.000000',1,4,'tviva20240404092319ZJnS0'),(37,'2024-04-04 00:25:20.000000',135,'2024-04-04 00:25:07.000000',1,5,'tviva2024040409250781M33'),(38,'2024-04-04 00:43:30.000000',136,'2024-04-04 00:43:19.000000',1,4,'tviva202404040943196EuY0'),(39,'2024-04-04 01:35:57.000000',137,'2024-04-04 01:35:46.000000',1,4,'tviva202404041035468ewR3');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_cancel`
--

DROP TABLE IF EXISTS `payment_cancel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_cancel` (
  `payment_cancel_id` bigint NOT NULL,
  `amount` int DEFAULT NULL,
  `payment_cancel_created_at` datetime(6) DEFAULT NULL,
  `approved_at` datetime(6) DEFAULT NULL,
  `requested_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`payment_cancel_id`),
  CONSTRAINT `FKkem1neg4dflg10a5ym0yrqfje` FOREIGN KEY (`payment_cancel_id`) REFERENCES `payment` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_cancel`
--

LOCK TABLES `payment_cancel` WRITE;
/*!40000 ALTER TABLE `payment_cancel` DISABLE KEYS */;
INSERT INTO `payment_cancel` VALUES (1,20000,NULL,'2024-03-30 09:54:26.000000','2024-03-30 09:54:07.000000'),(2,20000,NULL,'2024-03-30 15:59:13.000000','2024-03-30 15:58:56.000000'),(13,16000,NULL,'2024-04-02 15:24:58.000000','2024-04-02 15:24:39.000000'),(14,16000,NULL,'2024-04-02 15:29:58.000000','2024-04-02 15:29:41.000000'),(15,16000,NULL,'2024-04-03 00:32:02.000000','2024-04-03 00:31:42.000000'),(16,16000,NULL,'2024-04-03 15:39:47.000000','2024-04-03 15:39:29.000000'),(17,16000,NULL,'2024-04-03 16:26:42.000000','2024-04-03 16:26:24.000000'),(22,16000,NULL,'2024-04-03 16:40:54.000000','2024-04-03 16:40:25.000000'),(23,16000,NULL,'2024-04-03 17:42:01.000000','2024-04-03 17:41:42.000000'),(24,16000,NULL,'2024-04-03 18:15:15.000000','2024-04-03 18:15:00.000000'),(25,16000,NULL,'2024-04-03 18:27:30.000000','2024-04-03 18:27:16.000000'),(28,16000,NULL,'2024-04-03 19:44:41.000000','2024-04-03 19:44:21.000000'),(29,16000,NULL,'2024-04-03 19:47:41.000000','2024-04-03 19:47:03.000000'),(30,16000,NULL,'2024-04-03 19:47:41.000000','2024-04-03 19:47:26.000000'),(37,16000,NULL,'2024-04-04 00:25:20.000000','2024-04-04 00:25:07.000000');
/*!40000 ALTER TABLE `payment_cancel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `payment_method_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'카드'),(2,'가상계좌'),(3,'간편결제'),(4,'휴대폰'),(5,'계좌이체'),(6,'문화상품권'),(7,'도서문화상품권'),(8,'게임문화상품권');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_status`
--

DROP TABLE IF EXISTS `payment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_status` (
  `payment_status_id` int NOT NULL AUTO_INCREMENT,
  `payment_status_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_status`
--

LOCK TABLES `payment_status` WRITE;
/*!40000 ALTER TABLE `payment_status` DISABLE KEYS */;
INSERT INTO `payment_status` VALUES (1,'READY'),(2,'IN_PROGRESS'),(3,'WAITING_FOR_DEPOSIT'),(4,'DONE'),(5,'CANCELED'),(6,'PARTIAL_CANCELED'),(7,'ABORTED'),(8,'EXPIRED'),(9,NULL);
/*!40000 ALTER TABLE `payment_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-04  1:39:27
