-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.17 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table cms.cms_articles
DROP TABLE IF EXISTS `cms_articles`;
CREATE TABLE IF NOT EXISTS `cms_articles` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '0',
  `article_thumb` varchar(255) NOT NULL DEFAULT ' ',
  `article_title` varchar(255) NOT NULL,
  `article_content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `article_view` int(11) DEFAULT '0',
  `create_dt` datetime DEFAULT NULL,
  `update_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='博客文章';

-- Dumping data for table cms.cms_articles: ~4 rows (approximately)
DELETE FROM `cms_articles`;
/*!40000 ALTER TABLE `cms_articles` DISABLE KEYS */;
INSERT INTO `cms_articles` (`article_id`, `category_id`, `article_thumb`, `article_title`, `article_content`, `user_id`, `article_view`, `create_dt`, `update_dt`) VALUES
	(1, 1, '', '第一篇文章', '这是正文', 1, 0, '2015-03-24 16:29:29', '2015-04-30 16:02:18'),
	(2, 1, '', '第二篇文章', '这是正文', 1, 0, '2015-03-24 16:32:46', '2015-04-30 16:02:20'),
	(3, 1, '', '第四篇文章', '这是正文', 2, 0, '2015-03-24 16:33:23', '2015-04-30 16:02:21'),
	(4, 1, '', 'asd', '<p>asd<br/></p>', 2, 0, '2015-04-01 18:11:36', '2015-04-30 16:02:24');
/*!40000 ALTER TABLE `cms_articles` ENABLE KEYS */;


-- Dumping structure for table cms.cms_article_tag
DROP TABLE IF EXISTS `cms_article_tag`;
CREATE TABLE IF NOT EXISTS `cms_article_tag` (
  `article_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='博客标签link表';

-- Dumping data for table cms.cms_article_tag: ~2 rows (approximately)
DELETE FROM `cms_article_tag`;
/*!40000 ALTER TABLE `cms_article_tag` DISABLE KEYS */;
INSERT INTO `cms_article_tag` (`article_id`, `tag_id`) VALUES
	(1, 1),
	(1, 2);
/*!40000 ALTER TABLE `cms_article_tag` ENABLE KEYS */;


-- Dumping structure for table cms.cms_categories
DROP TABLE IF EXISTS `cms_categories`;
CREATE TABLE IF NOT EXISTS `cms_categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_pid` int(11) DEFAULT '0',
  `category_name` varchar(255) NOT NULL,
  `update_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='博客分类';

-- Dumping data for table cms.cms_categories: ~1 rows (approximately)
DELETE FROM `cms_categories`;
/*!40000 ALTER TABLE `cms_categories` DISABLE KEYS */;
INSERT INTO `cms_categories` (`category_id`, `category_pid`, `category_name`, `update_dt`) VALUES
	(1, 0, '测试', '2015-03-24 16:31:45');
/*!40000 ALTER TABLE `cms_categories` ENABLE KEYS */;


-- Dumping structure for table cms.cms_tags
DROP TABLE IF EXISTS `cms_tags`;
CREATE TABLE IF NOT EXISTS `cms_tags` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(50) NOT NULL DEFAULT ' ',
  `update_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='博客标签';

-- Dumping data for table cms.cms_tags: ~2 rows (approximately)
DELETE FROM `cms_tags`;
/*!40000 ALTER TABLE `cms_tags` DISABLE KEYS */;
INSERT INTO `cms_tags` (`tag_id`, `tag_name`, `update_dt`) VALUES
	(1, ' java', '2015-04-30 16:02:52'),
	(2, ' js', '2015-04-30 16:08:11');
/*!40000 ALTER TABLE `cms_tags` ENABLE KEYS */;


-- Dumping structure for table cms.config
DROP TABLE IF EXISTS `config`;
CREATE TABLE IF NOT EXISTS `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table cms.config: ~2 rows (approximately)
DELETE FROM `config`;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` (`id`, `key`, `value`) VALUES
	(1, 'cms_name', '博客'),
	(3, 'cms_notification', '欢迎光临');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;


-- Dumping structure for table cms.permissions
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table cms.permissions: ~1 rows (approximately)
DELETE FROM `permissions`;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` (`id`, `permission`, `description`, `available`) VALUES
	(1, 'cms:article', NULL, 1);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;


-- Dumping structure for table cms.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table cms.roles: ~2 rows (approximately)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `role`, `description`, `available`) VALUES
	(1, 'admin', 'admin', 1),
	(2, 'user', 'user', 1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Dumping structure for table cms.roles_permissions
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE IF NOT EXISTS `roles_permissions` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cms.roles_permissions: ~2 rows (approximately)
DELETE FROM `roles_permissions`;
/*!40000 ALTER TABLE `roles_permissions` DISABLE KEYS */;
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES
	(1, 1),
	(2, 1);
/*!40000 ALTER TABLE `roles_permissions` ENABLE KEYS */;


-- Dumping structure for table cms.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table cms.users: ~2 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `salt`, `locked`) VALUES
	(1, 'admin', '40ece1b68be64912285e0c54452186b5', 'tps', 0),
	(2, 'user', '40ece1b68be64912285e0c54452186b5', 'tps', 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Dumping structure for table cms.user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cms.user_roles: ~3 rows (approximately)
DELETE FROM `user_roles`;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
