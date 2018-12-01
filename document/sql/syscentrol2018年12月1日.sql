/*
SQLyog v10.2 
MySQL - 5.5.28 : Database - syscentrol
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`syscentrol` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `syscentrol`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `name` varchar(128) DEFAULT NULL COMMENT 'name',
  `title` varchar(50) DEFAULT NULL COMMENT '菜单名meta-title',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `path` varchar(200) DEFAULT NULL COMMENT '菜单path   compenent',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标 meta-icon',
  `order_index` int(11) DEFAULT NULL COMMENT '排序（升序）',
  `description` varchar(200) DEFAULT NULL COMMENT '鼠标悬浮描述',
  `status` int(11) DEFAULT NULL COMMENT '0.无效1.有效2.已删除',
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`name`,`title`,`component`,`path`,`icon`,`order_index`,`description`,`status`,`created_time`,`update_time`,`created_by`,`update_by`,`remark`) values (1,0,'system_manager','系统管理',NULL,'index',NULL,NULL,'系统管理',1,'2018-06-19 12:26:06',NULL,'test',NULL,NULL),(2,1,'user_manager','用户管理',NULL,'index','icon-user',NULL,'用户管理',1,'2018-06-19 12:27:30',NULL,NULL,NULL,NULL),(3,1,'role_manager','角色管理',NULL,'index','icon-role',NULL,'角色管理',1,'2018-06-19 12:27:56',NULL,NULL,NULL,NULL),(4,1,'router_manager','菜单管理',NULL,'index','icon-menu',NULL,'菜单管理',1,'2018-06-19 12:28:19',NULL,NULL,NULL,NULL),(5,0,'permission','permission','@/views/layout/Layout','/icon',NULL,NULL,'图标',1,NULL,NULL,NULL,NULL,NULL),(6,5,'icon','icons','@/views/svg-icons/index','index','icon',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),(7,0,'example','example','@/views/layout/Layout','/example','example',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),(8,7,'CreateArticle','createArticle','@/views/example/create','create','edit',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),(10,7,'ArticleList','articleList','@/views/example/list','list','list',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),(11,0,'permission_page','permission_page','@/views/layout/Layout','/tab',NULL,NULL,'tab页',1,NULL,NULL,NULL,NULL,NULL),(12,11,'tab','tab','@/views/tab/index','index','tab',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `order_index` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(11) DEFAULT NULL COMMENT '0.无效1.有效2.删除',
  `is_superadmin` bit(1) DEFAULT NULL COMMENT '是否是超级管理员',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`name`,`order_index`,`status`,`is_superadmin`,`create_time`,`create_by`,`update_time`,`update_by`,`remark`) values (1,'superadmin','系统管理员',NULL,1,'','2018-06-19 13:07:32',NULL,NULL,NULL,NULL),(2,'activeadmin','活动管理员',NULL,1,'','2018-06-19 13:08:26',NULL,NULL,NULL,NULL),(3,'editor','编辑',NULL,1,'','2018-11-28 15:14:08',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,10),(1,11),(1,12);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `truename` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型（1.系统用户）',
  `email` varchar(100) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `weibo_id` varchar(64) DEFAULT NULL,
  `login_num` int(11) DEFAULT NULL COMMENT '登录次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后 一次登录时间',
  `reg_from` int(11) DEFAULT NULL COMMENT '注册来源（0.自己注册1.平台添加2.其他平台注册）',
  `status` int(11) DEFAULT NULL COMMENT '0.无效，1.有效，2.删除',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` datetime DEFAULT NULL,
  `avatar` varchar(1024) DEFAULT NULL COMMENT '头像',
  `desc` varchar(1024) DEFAULT NULL COMMENT '用户描述',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_name`,`password`,`truename`,`phone`,`user_type`,`email`,`nick_name`,`weibo_id`,`login_num`,`last_login_time`,`reg_from`,`status`,`create_time`,`create_by`,`update_time`,`update_by`,`avatar`,`desc`,`remark`) values (1,'admin','123456','默认用户','13552335655',1,'jsidjf@1278.com','nick','w451651561',32,'2018-06-22 13:52:51',1,1,'2018-06-14 13:34:33','system','2018-06-22 13:53:04','2018-06-22 13:53:06','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80','系统用户一个','sdfsdf');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(1,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
