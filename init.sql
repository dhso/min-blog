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
  `article_title` varchar(255) NOT NULL,
  `article_content` text NOT NULL,
  `article_author` varchar(50) DEFAULT '匿名',
  `article_view` int(11) DEFAULT '0',
  `create_dt` datetime DEFAULT NULL,
  `update_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='博客文章';

-- Dumping data for table cms.cms_articles: ~4 rows (approximately)
DELETE FROM `cms_articles`;
/*!40000 ALTER TABLE `cms_articles` DISABLE KEYS */;
INSERT INTO `cms_articles` (`article_id`, `article_title`, `article_content`, `article_author`, `article_view`, `create_dt`, `update_dt`) VALUES
	(1, '第一篇文章第一篇文章第一篇文章第一篇文章第一篇文章', '<pre class="prettyprint lang-bsh">################SETENVIRONMENT VARIABLE################\nexportJAVA_HOME=/usr/java/jdk1.7.0_21\nexportM2_HOME=/usr/local/maven/apache-maven-3.0.3\nexportAUTO_HOME=${WORKSPACE}/automation\nexportPATH=${JAVA_HOME}/bin:${M2_HOME}/bin:${AUTO_HOME}:$PATH\nexportAVID_REPOSITORY=DEV_OES_GUI_1_0\nexportBASE_DIR=${WORKSPACE}/oes-gui\n \nrm -rf${BASE_DIR}\ngit clonessh://git@stash.acxiom.com:7999/ib/oes-gui.git\ncd${BASE_DIR}\ngitcheckout origin/development\nBUILD_FILE=rpmbuild.sh\nletRELEASE_NUMBER=${BUILD_NUMBER}+2\nsed -i -r&quot;s/^RELEASE.+/RELEASE=${RELEASE_NUMBER}/g&quot; ${BUILD_FILE}\nsh${BUILD_FILE}\ncd${WORKSPACE}\n \nrm -rf*.rpm\nmv `find. -type f -name &quot;*.rpm&quot; | egrep -v \'(\\.src\\.)\' | egrep -v \'BUILD\'` .\n \nRPM=`ls*.rpm`\necho${AVID_USERNAME}\npackageService--addPackages --repository ${AVID_REPOSITORY} --packages &quot;${RPM}&quot;</pre><br />\n', 'hadong', 0, '2015-03-24 16:29:29', '2015-05-06 16:13:23'),
	(2, '第二篇文章', '<p style="margin:0in;font-family:Calibri;font-size:10.0pt;" lang="zh-CN"></p><pre class="prettyprint lang-bsh">################SETENVIRONMENT VARIABLE################\nexportSOFTWARE_SET=DEV_OES_GUI_1_0_0\nexportSERVICE_NAME=OES_DEV_GUI_SERVICE\nexportINSTANCES=vm10877.global.shareddev.acxiom.net\n \ngridService--add-service --service ${SERVICE_NAME} --sw-set ${SOFTWARE_SET} --instance $INSTANCES\nsleep 60\ngridService--start-service --service ${SERVICE_NAME}</pre><br />\n', 'hadong', 0, '2015-03-24 16:32:46', '2015-05-06 16:13:27'),
	(3, '第四篇文章', '<p style="margin:0in;font-family:Calibri;font-size:10.0pt;" lang="zh-CN">	<br /></p><p style="margin:0in;font-family:Calibri;font-size:10.0pt;" lang="zh-CN">	<span style="color:#111111;font-family:宋体, 微软雅黑, Verdana, Helvetica;font-size:14px;line-height:25px;white-space:normal;background-color:#FFFFFF;">df命令参数功能：<br />检查文件系统的磁盘空间占用情况。可以利用该命令来获取硬盘被占用了多少空间，目前还剩下多少空间等信息。</span></p>', 'wengel', 0, '2015-03-24 16:33:23', '2015-05-06 16:13:30'),
	(4, 'asd', '<pre class="prettyprint lang-">1.Clone a repository in Stash\ngitclone ssh://git@stash.acxiom.com:7999/flw/flw.git\n2.set alias to replace the whole git repository URL\n• 1) Enter the cloned gitfolder\n• 2) run command:\n•git remote add origin ssh://git@stash.acxiom.com:7999/flw/flw.git\n•git push origin master\nHere, origin is the alias which you can use asthe whole git repository URL\n\n3. Modify and Check-in modified file\n•git status # check files status (Modified-untracked, Staged, Commited)\n•\n•UseAdd command to make file/folder in Staged status.\n•git add filename_or_Foldername # add file/folder which will be in Staged status\n•\n•Onceall needed files are in Staged status, then could commit them\n•Into gitrepository.\n•git commit\n•An editorial interface will be popedand commit message need to be filled out.\n•\n•Quickcommit command: use –a and –m option.\n•-ameans add the action to Stage not means add file, thus even need to remove afile from Stage, -a still needed.\n•git  commit  -a  -m ’added and commited new  files’\n•\n•Lookup commit history\n•git \n\n4.Branches\n•Listall branches\n•git branch\n•\n•Createnew branch\n•git branch mybranch\n•\n•Checkoutbranch\n•git checkout mybranch\n•\n•Mergebranch (merge mybranch to dev branch)\n•Aftercommit in mybranch, switch to branch need to bemerged into:\n•git checkout dev\n•git merge mybranch\n•git push origin dev\n</pre><br />\n', 'wengel', 0, '2015-04-01 18:11:36', '2015-05-06 16:13:34'),
	(6, '??mysql????', '<p style="white-space: normal;"><span style="font-family: ????, &#39;Microsoft YaHei&#39;; font-size: 11px;">???mysql???????&nbsp;<br/>??1?d:\\mysql\\bin\\&gt;mysql -h localhost -u root&nbsp;<br/>??//????????MySQL???&nbsp;<br/>??2?mysql&gt;GRANT ALL PRIVILEGES ON *.* TO &#39;root&#39;@&#39;%&#39;WITH GRANT OPTION&nbsp;<br/>??//?????????????&nbsp;<br/>??3?mysql&gt;FLUSH PRIVILEGES&nbsp;<br/>??//????&nbsp;<br/>??4?mysql&gt;EXIT&nbsp;<br/>??//??MySQL???&nbsp;<br/>?????????????????root??????<br/></span></p><p style="white-space: normal;"><br/></p><p style="white-space: normal;"><span style="font-family: ????, &#39;Microsoft YaHei&#39;; font-size: 11px;">??root????1? ?SET PASSWORD??<br/><br/>??mysql -u root<br/><br/>??mysql&gt; SET PASSWORD FOR &#39;root&#39;@&#39;localhost&#39; = PASSWORD(&#39;newpass&#39;);<br/><br/>??2??mysqladmin<br/><br/>??mysqladmin -u root password &quot;newpass&quot;<br/><br/>????root??????????????<br/><br/>??mysqladmin -u root password oldpass &quot;newpass&quot;<br/><br/>??3? ?UPDATE????user?<br/><br/>??mysql -u root<br/><br/>??mysql&gt; use mysql;<br/><br/>??mysql&gt; UPDATE user SET Password = PASSWORD(&#39;newpass&#39;) WHERE user = &#39;root&#39;;<br/><br/>??mysql&gt; FLUSH PRIVILEGES;<br/><br/>???root??????????<br/><br/>??mysqld_safe --skip-grant-tables&amp;<br/><br/>??mysql -u root mysql<br/><br/>??mysql&gt; UPDATE user SET password=PASSWORD(&quot;new password&quot;) WHERE user=&#39;root&#39;;<br/><br/>??mysql&gt; FLUSH PRIVILEGES;</span></p>', 'admin', 0, '2015-05-06 16:22:01', '2015-05-06 16:22:01'),
	(7, '??mysql????', '<p style="white-space: normal;"><span style="font-family: ????, &#39;Microsoft YaHei&#39;; font-size: 11px;">???mysql???????&nbsp;<br/>??1?d:\\mysql\\bin\\&gt;mysql -h localhost -u root&nbsp;<br/>??//????????MySQL???&nbsp;<br/>??2?mysql&gt;GRANT ALL PRIVILEGES ON *.* TO &#39;root&#39;@&#39;%&#39;WITH GRANT OPTION&nbsp;<br/>??//?????????????&nbsp;<br/>??3?mysql&gt;FLUSH PRIVILEGES&nbsp;<br/>??//????&nbsp;<br/>??4?mysql&gt;EXIT&nbsp;<br/>??//??MySQL???&nbsp;<br/>?????????????????root??????<br/></span></p><p style="white-space: normal;"><br/></p><p style="white-space: normal;"><span style="font-family: ????, &#39;Microsoft YaHei&#39;; font-size: 11px;">??root????1? ?SET PASSWORD??<br/><br/>??mysql -u root<br/><br/>??mysql&gt; SET PASSWORD FOR &#39;root&#39;@&#39;localhost&#39; = PASSWORD(&#39;newpass&#39;);<br/><br/>??2??mysqladmin<br/><br/>??mysqladmin -u root password &quot;newpass&quot;<br/><br/>????root??????????????<br/><br/>??mysqladmin -u root password oldpass &quot;newpass&quot;<br/><br/>??3? ?UPDATE????user?<br/><br/>??mysql -u root<br/><br/>??mysql&gt; use mysql;<br/><br/>??mysql&gt; UPDATE user SET Password = PASSWORD(&#39;newpass&#39;) WHERE user = &#39;root&#39;;<br/><br/>??mysql&gt; FLUSH PRIVILEGES;<br/><br/>???root??????????<br/><br/>??mysqld_safe --skip-grant-tables&amp;<br/><br/>??mysql -u root mysql<br/><br/>??mysql&gt; UPDATE user SET password=PASSWORD(&quot;new password&quot;) WHERE user=&#39;root&#39;;<br/><br/>??mysql&gt; FLUSH PRIVILEGES;</span></p>', 'admin', 0, '2015-05-06 16:22:32', '2015-05-06 16:22:32'),
	(8, 'lalala', '<p>lalalala<br/></p>', 'admin', 0, '2015-05-06 16:24:37', '2015-05-06 16:24:36');
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
	(1, 2),
	(6, 6),
	(8, 2),
	(8, 7);
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

-- Dumping data for table cms.cms_categories: ~0 rows (approximately)
DELETE FROM `cms_categories`;
/*!40000 ALTER TABLE `cms_categories` DISABLE KEYS */;
INSERT INTO `cms_categories` (`category_id`, `category_pid`, `category_name`, `update_dt`) VALUES
	(1, 0, '测试', '2015-03-24 16:31:45');
/*!40000 ALTER TABLE `cms_categories` ENABLE KEYS */;


-- Dumping structure for table cms.cms_tags
DROP TABLE IF EXISTS `cms_tags`;
CREATE TABLE IF NOT EXISTS `cms_tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) NOT NULL DEFAULT ' ',
  `update_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COMMENT='博客标签';

-- Dumping data for table cms.cms_tags: ~2 rows (approximately)
DELETE FROM `cms_tags`;
/*!40000 ALTER TABLE `cms_tags` DISABLE KEYS */;
INSERT INTO `cms_tags` (`tag_id`, `tag_name`, `update_dt`) VALUES
	(1, 'java', '2015-05-04 21:58:31'),
	(2, 'js', '2015-05-04 21:58:44'),
	(6, 'mysql', '2015-05-06 16:21:57'),
	(7, 'linux', '2015-05-06 16:24:36');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table cms.permissions: ~2 rows (approximately)
DELETE FROM `permissions`;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` (`id`, `permission`, `description`, `available`) VALUES
	(1, 'cms:article:edit', NULL, 1),
	(2, 'cms:article:add', NULL, 1);
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

-- Dumping data for table cms.roles_permissions: ~3 rows (approximately)
DELETE FROM `roles_permissions`;
/*!40000 ALTER TABLE `roles_permissions` DISABLE KEYS */;
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
