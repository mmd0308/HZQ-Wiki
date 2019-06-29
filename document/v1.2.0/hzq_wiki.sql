CREATE DATABASE  IF NOT EXISTS `hzq_wiki` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hzq_wiki`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: hzq_wiki
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `hw_config`
--

DROP TABLE IF EXISTS `hw_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(45) DEFAULT NULL COMMENT '键 唯一',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='配置管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_config`
--

LOCK TABLES `hw_config` WRITE;
/*!40000 ALTER TABLE `hw_config` DISABLE KEYS */;
INSERT INTO `hw_config` VALUES (1,'CONFIG_TITLE','HZQ-WIKI',1,'2019-06-29 14:46:50'),(2,'CONFIG_RECORD','京ICP备XXXXXXXX号-1',1,'2019-06-29 14:46:50');
/*!40000 ALTER TABLE `hw_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw_doc`
--

DROP TABLE IF EXISTS `hw_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_doc` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '文档名称',
  `space_id` int(10) DEFAULT NULL COMMENT '项目空间外键',
  `remark` varchar(255) DEFAULT NULL COMMENT '项目描述',
  `visit_level` tinyint(1) DEFAULT NULL COMMENT '访问级别 0 私有的 1 公开的',
  `img` varchar(255) DEFAULT NULL COMMENT '项目图片',
  `create_by` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(10) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_doc`
--

LOCK TABLES `hw_doc` WRITE;
/*!40000 ALTER TABLE `hw_doc` DISABLE KEYS */;
/*!40000 ALTER TABLE `hw_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw_doc_content`
--

DROP TABLE IF EXISTS `hw_doc_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_doc_content` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `doc_id` int(10) DEFAULT NULL COMMENT '文档id',
  `parent_id` int(10) DEFAULT NULL COMMENT '父级别id',
  `version` varchar(45) DEFAULT NULL COMMENT '版本号,默认是latest 版本规则	年月日小时分钟秒/2019.04.16.16.23.23',
  `title` varchar(125) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容 mk格式',
  `content_html` text COMMENT '文档内容 html格式',
  `sequence` int(10) DEFAULT NULL COMMENT '排序',
  `create_by` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(10) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_doc_content`
--

LOCK TABLES `hw_doc_content` WRITE;
/*!40000 ALTER TABLE `hw_doc_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `hw_doc_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw_login_log`
--

DROP TABLE IF EXISTS `hw_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL COMMENT '登陆用户名称',
  `ip` varchar(45) DEFAULT NULL COMMENT '访问ip\n',
  `browser` varchar(45) DEFAULT NULL COMMENT '访问浏览器',
  `create_by` int(11) DEFAULT NULL COMMENT '登陆人id',
  `create_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `system` varchar(45) DEFAULT NULL COMMENT '操作系统',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统登陆日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_login_log`
--

LOCK TABLES `hw_login_log` WRITE;
/*!40000 ALTER TABLE `hw_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `hw_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw_space`
--

DROP TABLE IF EXISTS `hw_space`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_space` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '空间名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `visit_level` tinyint(1) DEFAULT '1' COMMENT '访问级别 0 私有的 1 公开的',
  `create_by` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(10) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='空间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_space`
--

LOCK TABLES `hw_space` WRITE;
/*!40000 ALTER TABLE `hw_space` DISABLE KEYS */;
/*!40000 ALTER TABLE `hw_space` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw_user`
--

DROP TABLE IF EXISTS `hw_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '用户名称',
  `username` varchar(45) DEFAULT NULL COMMENT '登陆名称',
  `password` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `create_by` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(10) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_user`
--

LOCK TABLES `hw_user` WRITE;
/*!40000 ALTER TABLE `hw_user` DISABLE KEYS */;
INSERT INTO `hw_user` VALUES (1,'admin','admin','$2a$10$/ZHzPnCsywCAQRMPQlqKoujlqPdysRZBQGPN9lXaAY5rNoRGmfyZi','admin@163.com','',NULL,-1,'2019-06-29 11:33:49',-1,'2019-06-29 11:33:49',NULL);
/*!40000 ALTER TABLE `hw_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw_user_doc`
--

DROP TABLE IF EXISTS `hw_user_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_user_doc` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `doc_Id` int(10) DEFAULT NULL,
  `privilege` tinyint(1) DEFAULT NULL COMMENT '空间成员操作权限   0 拥有者 1 浏览者 2 编辑者 3 管理员 \n\n',
  `create_by` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `expire_time` date DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户文档关系表\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_user_doc`
--

LOCK TABLES `hw_user_doc` WRITE;
/*!40000 ALTER TABLE `hw_user_doc` DISABLE KEYS */;
/*!40000 ALTER TABLE `hw_user_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw_user_space`
--

DROP TABLE IF EXISTS `hw_user_space`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_user_space` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `space_id` int(10) DEFAULT NULL,
  `privilege` tinyint(1) DEFAULT NULL COMMENT '空间成员操作权限   0 拥有者 1 浏览者 2 编辑者 3 管理员 \n\n',
  `create_by` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `expire_time` date DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户跟空间的关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_user_space`
--

LOCK TABLES `hw_user_space` WRITE;
/*!40000 ALTER TABLE `hw_user_space` DISABLE KEYS */;
/*!40000 ALTER TABLE `hw_user_space` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-29 14:58:06
