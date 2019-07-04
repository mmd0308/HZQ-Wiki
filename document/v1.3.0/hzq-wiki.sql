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
-- Table structure for table `hw_article`
--

DROP TABLE IF EXISTS `hw_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL COMMENT '文章标题',
  `content` text COMMENT '文章内容 md格式',
  `content_html` text COMMENT '文章内容 html格式',
  `sequence` int(10) DEFAULT NULL COMMENT '排序',
  `hw_desc` varchar(512) DEFAULT NULL COMMENT '文章摘要\n',
  `hw_state` tinyint(1) DEFAULT NULL COMMENT '状态 1 草稿 2 发布',
  `hw_up` tinyint(1) DEFAULT NULL COMMENT '是否置顶 0 否 1是',
  `user_id` int(10) DEFAULT NULL,
  `create_by` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_article`
--

LOCK TABLES `hw_article` WRITE;
/*!40000 ALTER TABLE `hw_article` DISABLE KEYS */;
INSERT INTO `hw_article` VALUES (1,'HZQ-WIKI 简介','# HZQ-WIKI 简介\n\nHZQ-WIKI 是一个轻量级的企业知识分享与团队协同软件，可用于快速构建企业 Wiki 和团队知识分享平台。部署方便，使用简单，帮助团队构建一个信息共享、文档管理的协作环境。 \n\n# 主要功能\n\n### 已经实现:\n\n- [x] 文档空间管理：可以对空间进行编辑更改，成员添加等。\n- [x] 文档管理：添加和删除文档，成员添加等。\n- [x] 文档空间加密：可以设置项目公开状态，私有项目需要通过Token访问。\n- [x] 文档加密：可以设置项目公开状态，私有项目需要通过Token访问。\n- [x] 用户管理：管理员(admin)添加和修改用户等。\n- [x] 个人中心：对个人基础信息进行修改等。\n- [x] 密码修改：用户自己修改密码。\n- [x] 配置管理：根据客户需求，自定义一些站点信息\n- [x] 登陆日志: 查看登陆人员日志信息\n- [x] 文章管理: 草稿箱,文章编辑\n- [x] 文章展示: 文章浏览\n\n### 待实现\n- [ ] 系统日志\n- [ ] 项目导出：暂时到处PDF,WORD\n- [ ] 项目导入：支持Word，md\n\n\n# 主要技术\n### 前端技术\n- ```element-ui: 2.8.2 ```\n- ```jquery: 3.4.1 ```\n- ```js-cookie: 2.2.0 ```\n- ```mavon-editor: ^2.7.4 ```\n- ```vue: 2.5.17 ```\n- ```vue-avatar: ^2.1.7 ```\n- ```vue-image-crop-upload: ^2.5.0 ```\n- ```vue-router: 3.0.1 ```\n- ```vuex: 3.0.1 ```\n\n### 后端技术\n\n- ```Java: 11```\n- ```Spring-boot: 2.1.4.RELEASE ```\n- `spring-boot-starter-security:2.1.4.RELEASE`\n- `lombok:1.18.2`\n- `jjwt-api:0.10.57`\n- `mybatis-spring-boot-starter:1.3.4`\n- `mysql-connector-java:5.1.44`\n- `druid-spring-boot-starter:1.1.16`\n\n# 演示\n- 演示站点: [http://www.hzqing.com](http://www.hzqing.com)\n- 测试用户: guest \n- 密码: guest 【请勿修改密码】\n- 项目源码：[HZQ-WIKI](https://github.com/mmd0308/HZQ-Wiki)\n\n# 赞助 HZQ-WIKI\n\n\n\n| ![zfb.jpg](http://www.hzqing.com/fs/doc/1/images/201906/904036f2-98c4-41ca-aa89-b0e3c60203e0.jpg) |![wx.png](http://www.hzqing.com/fs/doc/1/images/201906/6939a71e-b507-4f99-9fd3-62d4ce76f30a.png)|\n|:-:|:-:|\n|支付宝|微信|\n\n\n\n\n\n\n\n','<h1><a id=\"HZQWIKI__0\"></a>HZQ-WIKI 简介</h1>\n<p>HZQ-WIKI 是一个轻量级的企业知识分享与团队协同软件，可用于快速构建企业 Wiki 和团队知识分享平台。部署方便，使用简单，帮助团队构建一个信息共享、文档管理的协作环境。</p>\n<h1><a id=\"_4\"></a>主要功能</h1>\n<h3><a id=\"_6\"></a>已经实现:</h3>\n<ul class=\"contains-task-list\">\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档空间管理：可以对空间进行编辑更改，成员添加等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档管理：添加和删除文档，成员添加等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档空间加密：可以设置项目公开状态，私有项目需要通过Token访问。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档加密：可以设置项目公开状态，私有项目需要通过Token访问。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 用户管理：管理员(admin)添加和修改用户等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 个人中心：对个人基础信息进行修改等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 密码修改：用户自己修改密码。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 配置管理：根据客户需求，自定义一些站点信息</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 登陆日志: 查看登陆人员日志信息</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文章管理: 草稿箱,文章编辑</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文章展示: 文章浏览</li>\n</ul>\n<h3><a id=\"_20\"></a>待实现</h3>\n<ul class=\"contains-task-list\">\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" disabled=\"\" type=\"checkbox\"> 系统日志</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" disabled=\"\" type=\"checkbox\"> 项目导出：暂时到处PDF,WORD</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" disabled=\"\" type=\"checkbox\"> 项目导入：支持Word，md</li>\n</ul>\n<h1><a id=\"_26\"></a>主要技术</h1>\n<h3><a id=\"_27\"></a>前端技术</h3>\n<ul>\n<li><code>element-ui: 2.8.2</code></li>\n<li><code>jquery: 3.4.1</code></li>\n<li><code>js-cookie: 2.2.0</code></li>\n<li><code>mavon-editor: ^2.7.4</code></li>\n<li><code>vue: 2.5.17</code></li>\n<li><code>vue-avatar: ^2.1.7</code></li>\n<li><code>vue-image-crop-upload: ^2.5.0</code></li>\n<li><code>vue-router: 3.0.1</code></li>\n<li><code>vuex: 3.0.1</code></li>\n</ul>\n<h3><a id=\"_38\"></a>后端技术</h3>\n<ul>\n<li><code>Java: 11</code></li>\n<li><code>Spring-boot: 2.1.4.RELEASE</code></li>\n<li><code>spring-boot-starter-security:2.1.4.RELEASE</code></li>\n<li><code>lombok:1.18.2</code></li>\n<li><code>jjwt-api:0.10.57</code></li>\n<li><code>mybatis-spring-boot-starter:1.3.4</code></li>\n<li><code>mysql-connector-java:5.1.44</code></li>\n<li><code>druid-spring-boot-starter:1.1.16</code></li>\n</ul>\n<h1><a id=\"_49\"></a>演示</h1>\n<ul>\n<li>演示站点: <a href=\"http://www.hzqing.com\" target=\"_blank\">http://www.hzqing.com</a></li>\n<li>测试用户: guest</li>\n<li>密码: guest 【请勿修改密码】</li>\n<li>项目源码：<a href=\"https://github.com/mmd0308/HZQ-Wiki\" target=\"_blank\">HZQ-WIKI</a></li>\n</ul>\n<h1><a id=\"_HZQWIKI_55\"></a>赞助 HZQ-WIKI</h1>\n<table>\n<thead>\n<tr>\n<th style=\"text-align:center\"><img src=\"http://www.hzqing.com/fs/doc/1/images/201906/904036f2-98c4-41ca-aa89-b0e3c60203e0.jpg\" alt=\"zfb.jpg\" /></th>\n<th style=\"text-align:center\"><img src=\"http://www.hzqing.com/fs/doc/1/images/201906/6939a71e-b507-4f99-9fd3-62d4ce76f30a.png\" alt=\"wx.png\" /></th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td style=\"text-align:center\">支付宝</td>\n<td style=\"text-align:center\">微信</td>\n</tr>\n</tbody>\n</table>\n',NULL,'HZQ-WIKI简介\nHZQ-WIKI是一个轻量级的企业知识分享与团队协同软件，可用于快速构建企业Wiki和团队知识分享平台。部署方便，使用简单，帮助团队构建一个信息共享、文档管理的协作环境。\n主要功能\n已经实现:\n\n文档空间管理：可以对空间进行编辑更改，成员添加等。\n文档管理：添加和删除文档，成员',2,0,1,1,'2019-07-04 15:26:07','2019-07-04 15:26:11');
/*!40000 ALTER TABLE `hw_article` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文档表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_doc`
--

LOCK TABLES `hw_doc` WRITE;
/*!40000 ALTER TABLE `hw_doc` DISABLE KEYS */;
INSERT INTO `hw_doc` VALUES (1,'HZQ-WIKI文档',1,'HZQ-WIKI文档',1,NULL,1,'2019-07-04 14:52:58',1,'2019-07-04 14:52:58');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文档内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_doc_content`
--

LOCK TABLES `hw_doc_content` WRITE;
/*!40000 ALTER TABLE `hw_doc_content` DISABLE KEYS */;
INSERT INTO `hw_doc_content` VALUES (1,1,-1,NULL,'HZQ-WIKI 简介','# HZQ-WIKI 简介\n\nHZQ-WIKI 是一个轻量级的企业知识分享与团队协同软件，可用于快速构建企业 Wiki 和团队知识分享平台。部署方便，使用简单，帮助团队构建一个信息共享、文档管理的协作环境。 \n\n# 主要功能\n\n### 已经实现:\n\n- [x] 文档空间管理：可以对空间进行编辑更改，成员添加等。\n- [x] 文档管理：添加和删除文档，成员添加等。\n- [x] 文档空间加密：可以设置项目公开状态，私有项目需要通过Token访问。\n- [x] 文档加密：可以设置项目公开状态，私有项目需要通过Token访问。\n- [x] 用户管理：管理员(admin)添加和修改用户等。\n- [x] 个人中心：对个人基础信息进行修改等。\n- [x] 密码修改：用户自己修改密码。\n- [x] 配置管理：根据客户需求，自定义一些站点信息\n- [x] 登陆日志: 查看登陆人员日志信息\n- [x] 文章管理: 草稿箱,文章编辑\n- [x] 文章展示: 文章浏览\n\n### 待实现\n- [ ] 系统日志\n- [ ] 项目导出：暂时到处PDF,WORD\n- [ ] 项目导入：支持Word，md\n\n\n# 主要技术\n### 前端技术\n- ```element-ui: 2.8.2 ```\n- ```jquery: 3.4.1 ```\n- ```js-cookie: 2.2.0 ```\n- ```mavon-editor: ^2.7.4 ```\n- ```vue: 2.5.17 ```\n- ```vue-avatar: ^2.1.7 ```\n- ```vue-image-crop-upload: ^2.5.0 ```\n- ```vue-router: 3.0.1 ```\n- ```vuex: 3.0.1 ```\n\n### 后端技术\n\n- ```Java: 11```\n- ```Spring-boot: 2.1.4.RELEASE ```\n- `spring-boot-starter-security:2.1.4.RELEASE`\n- `lombok:1.18.2`\n- `jjwt-api:0.10.57`\n- `mybatis-spring-boot-starter:1.3.4`\n- `mysql-connector-java:5.1.44`\n- `druid-spring-boot-starter:1.1.16`\n\n# 演示\n- 演示站点: [http://www.hzqing.com](http://www.hzqing.com)\n- 测试用户: guest \n- 密码: guest 【请勿修改密码】\n- 项目源码：[HZQ-WIKI](https://github.com/mmd0308/HZQ-Wiki)\n\n# 赞助 HZQ-WIKI\n\n\n\n| ![zfb.jpg](http://www.hzqing.com/fs/doc/1/images/201906/904036f2-98c4-41ca-aa89-b0e3c60203e0.jpg) |![wx.png](http://www.hzqing.com/fs/doc/1/images/201906/6939a71e-b507-4f99-9fd3-62d4ce76f30a.png)|\n|:-:|:-:|\n|支付宝|微信|\n\n\n\n\n\n\n\n','<h1><a id=\"HZQWIKI__0\"></a>HZQ-WIKI 简介</h1>\n<p>HZQ-WIKI 是一个轻量级的企业知识分享与团队协同软件，可用于快速构建企业 Wiki 和团队知识分享平台。部署方便，使用简单，帮助团队构建一个信息共享、文档管理的协作环境。</p>\n<h1><a id=\"_4\"></a>主要功能</h1>\n<h3><a id=\"_6\"></a>已经实现:</h3>\n<ul class=\"contains-task-list\">\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档空间管理：可以对空间进行编辑更改，成员添加等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档管理：添加和删除文档，成员添加等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档空间加密：可以设置项目公开状态，私有项目需要通过Token访问。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文档加密：可以设置项目公开状态，私有项目需要通过Token访问。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 用户管理：管理员(admin)添加和修改用户等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 个人中心：对个人基础信息进行修改等。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 密码修改：用户自己修改密码。</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 配置管理：根据客户需求，自定义一些站点信息</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 登陆日志: 查看登陆人员日志信息</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文章管理: 草稿箱,文章编辑</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" type=\"checkbox\"> 文章展示: 文章浏览</li>\n</ul>\n<h3><a id=\"_20\"></a>待实现</h3>\n<ul class=\"contains-task-list\">\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" disabled=\"\" type=\"checkbox\"> 系统日志</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" disabled=\"\" type=\"checkbox\"> 项目导出：暂时到处PDF,WORD</li>\n<li class=\"task-list-item\"><input class=\"task-list-item-checkbox\" disabled=\"\" type=\"checkbox\"> 项目导入：支持Word，md</li>\n</ul>\n<h1><a id=\"_26\"></a>主要技术</h1>\n<h3><a id=\"_27\"></a>前端技术</h3>\n<ul>\n<li><code>element-ui: 2.8.2</code></li>\n<li><code>jquery: 3.4.1</code></li>\n<li><code>js-cookie: 2.2.0</code></li>\n<li><code>mavon-editor: ^2.7.4</code></li>\n<li><code>vue: 2.5.17</code></li>\n<li><code>vue-avatar: ^2.1.7</code></li>\n<li><code>vue-image-crop-upload: ^2.5.0</code></li>\n<li><code>vue-router: 3.0.1</code></li>\n<li><code>vuex: 3.0.1</code></li>\n</ul>\n<h3><a id=\"_38\"></a>后端技术</h3>\n<ul>\n<li><code>Java: 11</code></li>\n<li><code>Spring-boot: 2.1.4.RELEASE</code></li>\n<li><code>spring-boot-starter-security:2.1.4.RELEASE</code></li>\n<li><code>lombok:1.18.2</code></li>\n<li><code>jjwt-api:0.10.57</code></li>\n<li><code>mybatis-spring-boot-starter:1.3.4</code></li>\n<li><code>mysql-connector-java:5.1.44</code></li>\n<li><code>druid-spring-boot-starter:1.1.16</code></li>\n</ul>\n<h1><a id=\"_49\"></a>演示</h1>\n<ul>\n<li>演示站点: <a href=\"http://www.hzqing.com\" target=\"_blank\">http://www.hzqing.com</a></li>\n<li>测试用户: guest</li>\n<li>密码: guest 【请勿修改密码】</li>\n<li>项目源码：<a href=\"https://github.com/mmd0308/HZQ-Wiki\" target=\"_blank\">HZQ-WIKI</a></li>\n</ul>\n<h1><a id=\"_HZQWIKI_55\"></a>赞助 HZQ-WIKI</h1>\n<table>\n<thead>\n<tr>\n<th style=\"text-align:center\"><img src=\"http://www.hzqing.com/fs/doc/1/images/201906/904036f2-98c4-41ca-aa89-b0e3c60203e0.jpg\" alt=\"zfb.jpg\" /></th>\n<th style=\"text-align:center\"><img src=\"http://www.hzqing.com/fs/doc/1/images/201906/6939a71e-b507-4f99-9fd3-62d4ce76f30a.png\" alt=\"wx.png\" /></th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td style=\"text-align:center\">支付宝</td>\n<td style=\"text-align:center\">微信</td>\n</tr>\n</tbody>\n</table>\n',0,1,'2019-07-04 14:55:14',1,'2019-07-04 14:55:18');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统登陆日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_login_log`
--

LOCK TABLES `hw_login_log` WRITE;
/*!40000 ALTER TABLE `hw_login_log` DISABLE KEYS */;
INSERT INTO `hw_login_log` VALUES (1,'admin','127.0.0.1','Chrome/75.0.3770.100',1,'2019-07-04 15:14:36','APPLE COMPUTER Mac OS X'),(2,'admin','127.0.0.1','Chrome/75.0.3770.100',1,'2019-07-04 15:17:29','APPLE COMPUTER Mac OS X');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='空间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_space`
--

LOCK TABLES `hw_space` WRITE;
/*!40000 ALTER TABLE `hw_space` DISABLE KEYS */;
INSERT INTO `hw_space` VALUES (1,'默认空间','默认空间',1,1,'2019-07-04 14:52:40',1,'2019-07-04 14:52:40');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户文档关系表\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_user_doc`
--

LOCK TABLES `hw_user_doc` WRITE;
/*!40000 ALTER TABLE `hw_user_doc` DISABLE KEYS */;
INSERT INTO `hw_user_doc` VALUES (1,1,1,0,1,'2019-07-04 14:52:58',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户跟空间的关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw_user_space`
--

LOCK TABLES `hw_user_space` WRITE;
/*!40000 ALTER TABLE `hw_user_space` DISABLE KEYS */;
INSERT INTO `hw_user_space` VALUES (1,1,1,0,1,'2019-07-04 14:52:40',NULL);
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

-- Dump completed on 2019-07-04 15:27:33
