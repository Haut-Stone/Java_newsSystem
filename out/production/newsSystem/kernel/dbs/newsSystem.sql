/*
 Navicat Premium Data Transfer

 Source Server         : localDataBase
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : newsSystem

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 03/07/2018 23:08:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) COLLATE utf8_bin NOT NULL,
  `password` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1112 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, '石佳欢', '123');
INSERT INTO `admin` VALUES (2, '加藤惠', 'sakura');
INSERT INTO `admin` VALUES (3, '英梨梨', 'art');
INSERT INTO `admin` VALUES (4, '小混沌', 'paint');
INSERT INTO `admin` VALUES (5, '春希', 'haru');
COMMIT;

-- ----------------------------
-- Table structure for commonuser
-- ----------------------------
DROP TABLE IF EXISTS `commonuser`;
CREATE TABLE `commonuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) COLLATE utf8_bin NOT NULL,
  `password` varchar(60) COLLATE utf8_bin NOT NULL,
  `trueName` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `sid` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4357 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of commonuser
-- ----------------------------
BEGIN;
INSERT INTO `commonuser` VALUES (1, '去哪里', '123', '董丽君', '410392839293829392', '145393895831', 'mars');
INSERT INTO `commonuser` VALUES (2, '佐藤', '123', '', '', '', 'earth');
INSERT INTO `commonuser` VALUES (5, '张宇', '123', '张宇小王子', '12244', '123', '123');
INSERT INTO `commonuser` VALUES (4344, '冬马和纱', 'piano', '', '', '', '');
INSERT INTO `commonuser` VALUES (4345, '小木曾雪菜', 'song', '', '', '', '');
INSERT INTO `commonuser` VALUES (4346, '混沌老师', 'sai', '小混沌', '410738283728', '', '');
INSERT INTO `commonuser` VALUES (4351, '风之子', '34546758', '野原新之助', '', '15403', 'dt');
INSERT INTO `commonuser` VALUES (4354, 'test', '123', '', '', '', '');
INSERT INTO `commonuser` VALUES (4355, 'test2', '123', 'adfafsff', 'safaf', 'ffff', 'ffff');
INSERT INTO `commonuser` VALUES (4356, 'test123456', '123', '', '', '', '');
COMMIT;

-- ----------------------------
-- Table structure for magazine
-- ----------------------------
DROP TABLE IF EXISTS `magazine`;
CREATE TABLE `magazine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coverPath` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(60) COLLATE utf8_bin NOT NULL,
  `office` varchar(60) COLLATE utf8_bin NOT NULL,
  `cycle` varchar(60) COLLATE utf8_bin NOT NULL,
  `price` varchar(60) COLLATE utf8_bin NOT NULL,
  `intro` varchar(140) COLLATE utf8_bin NOT NULL,
  `classNumber` int(11) NOT NULL DEFAULT '2333',
  PRIMARY KEY (`id`),
  KEY `classNumber` (`classNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2333334 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of magazine
-- ----------------------------
BEGIN;
INSERT INTO `magazine` VALUES (1, 'time.png', '时代杂志', 'NYC', 'monthly', '300', '看川普那帅气犀利的眼神，是不是有香蕉君的神韵？\n从照片中散发出的浓浓gay气，让你看着他就感觉自己真的置身于新日暮里。\n作为年度人物出现在这里的川普，想必又将带领全球的比利人士，走上一个全新的高峰。', 1);
INSERT INTO `magazine` VALUES (2, 'beauty.jpeg', 'beauty', 'BBC', 'yearly', '100', '这只是一个测试杂志，我不懂', 2);
INSERT INTO `magazine` VALUES (3, 'hommes.jpeg', 'hommes', 'BCB', 'weakly', '399', '测试请看川普', 3);
INSERT INTO `magazine` VALUES (4, 'science.jpeg', 'science', 'DDX', 'yearly', '34', '测试请看川普', 4);
INSERT INTO `magazine` VALUES (5, 'vocue.jpeg', 'vocue', 'DD', 'monthly', '44', '测试请看川普', 3);
INSERT INTO `magazine` VALUES (6, '生活.jpeg', '生活', '朝日新闻', 'yearly', '3', '测试请看川普', 2);
INSERT INTO `magazine` VALUES (7, 'mens.jpeg', 'mens', '男人装', 'monthly', '33', '测试请看川普', 1);
INSERT INTO `magazine` VALUES (8, 'UI参考.png', '测试杂志', 'NYC', 'monthly', '300', '看川普那帅气犀利的眼神，是不是有香蕉君的神韵？\n从照片中散发出的浓浓gay气，让你看着他就感觉自己真的置身于新日暮里。\n作为年度人物出现在这里的川普，想必又将带领全球的比利人士，走上一个全新的高峰。', 2333);
COMMIT;

-- ----------------------------
-- Table structure for mClass
-- ----------------------------
DROP TABLE IF EXISTS `mClass`;
CREATE TABLE `mClass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2334 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of mClass
-- ----------------------------
BEGIN;
INSERT INTO `mClass` VALUES (1, '生活');
INSERT INTO `mClass` VALUES (2, '日常');
INSERT INTO `mClass` VALUES (3, '新闻');
INSERT INTO `mClass` VALUES (4, '打扮');
INSERT INTO `mClass` VALUES (5, '男人');
INSERT INTO `mClass` VALUES (6, '女人');
INSERT INTO `mClass` VALUES (7, '科学');
INSERT INTO `mClass` VALUES (9, '计算机');
INSERT INTO `mClass` VALUES (10, '自然');
INSERT INTO `mClass` VALUES (11, '艺术');
INSERT INTO `mClass` VALUES (2333, '未分类');
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `copiesNum` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `magazineToOrder` (`mid`),
  CONSTRAINT `magazineToOrder` FOREIGN KEY (`mid`) REFERENCES `magazine` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `commonuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (5, 4354, 5, 7, 1, 22000);
INSERT INTO `order` VALUES (6, 5, 5, 1, 1, 44);
INSERT INTO `order` VALUES (7, 5, 4, 5, 1, 170);
INSERT INTO `order` VALUES (8, 5, 6, 9, 1, 27);
INSERT INTO `order` VALUES (9, 5, 3, 8, 1, 3192);
INSERT INTO `order` VALUES (10, 5, 5, 6, 1, 264);
INSERT INTO `order` VALUES (11, 5, 3, 10, 1, 3990);
INSERT INTO `order` VALUES (12, 5, 2, 12, 1, 1200);
INSERT INTO `order` VALUES (16, 5, 4, 12, 1, 408);
INSERT INTO `order` VALUES (17, 5, 4, 12, 1, 408);
INSERT INTO `order` VALUES (18, 5, 4, 12, 1, 408);
INSERT INTO `order` VALUES (20, 5, 2, 1, 1, 100);
INSERT INTO `order` VALUES (22, 5, 2, 8, 6, 122);
INSERT INTO `order` VALUES (23, 5, 5, 1, 1, 30);
INSERT INTO `order` VALUES (24, 4351, 3, 4, 3, 1596);
INSERT INTO `order` VALUES (25, 4351, 7, 6, 7, 198);
INSERT INTO `order` VALUES (234, 4354, 8, 9, 10, 27000);
INSERT INTO `order` VALUES (235, 4355, 1, 1, 7, 2100);
INSERT INTO `order` VALUES (236, 4355, 6, 3, 11, 99);
INSERT INTO `order` VALUES (237, 4356, 1, 6, 7, 12600);
INSERT INTO `order` VALUES (238, 4356, 5, 9, 7, 2772);
INSERT INTO `order` VALUES (239, 4356, 3, 9, 7, 25137);
COMMIT;

-- ----------------------------
-- View structure for classreview
-- ----------------------------
DROP VIEW IF EXISTS `classreview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `classreview` AS select coalesce(`c`.`className`,'总数') AS `className`,sum(`o`.`totalPrice`) AS `totalPrice`,count(0) AS `orderNum`,sum(`o`.`copiesNum`) AS `copiesNum` from ((`order` `o` join `magazine` `m`) join `mclass` `c`) where ((`m`.`id` = `o`.`mid`) and (`c`.`id` = `m`.`classNumber`)) group by `c`.`className` with rollup;

-- ----------------------------
-- View structure for magazinereview
-- ----------------------------
DROP VIEW IF EXISTS `magazinereview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `magazinereview` AS select coalesce(`m`.`name`,'总数') AS `magazeinName`,sum(`o`.`totalPrice`) AS `totalPrice`,count(0) AS `orderNum`,sum(`o`.`copiesNum`) AS `totalCopiesNum` from (`order` `o` join `magazine` `m`) where (`m`.`id` = `o`.`mid`) group by `m`.`name` with rollup;

-- ----------------------------
-- View structure for seclassreview
-- ----------------------------
DROP VIEW IF EXISTS `seclassreview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `seclassreview` AS select `classreview`.`className` AS `className`,`classreview`.`totalPrice` AS `totalPrice`,`classreview`.`orderNum` AS `orderNum`,`classreview`.`copiesNum` AS `copiesNum` from `classreview` where (not(`classreview`.`totalPrice` in (select max(`classreview`.`totalPrice`) from `classreview`)));

-- ----------------------------
-- View structure for semagazinereview
-- ----------------------------
DROP VIEW IF EXISTS `semagazinereview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `semagazinereview` AS select `magazinereview`.`magazeinName` AS `magazeinName`,`magazinereview`.`totalPrice` AS `totalPrice`,`magazinereview`.`orderNum` AS `orderNum`,`magazinereview`.`totalCopiesNum` AS `totalCopiesNum` from `magazinereview` where (not(`magazinereview`.`totalPrice` in (select max(`magazinereview`.`totalPrice`) from `magazinereview`)));

-- ----------------------------
-- View structure for seuserriview
-- ----------------------------
DROP VIEW IF EXISTS `seuserriview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `seuserriview` AS select `userreview`.`userName` AS `userName`,`userreview`.`totalPrice` AS `totalPrice`,`userreview`.`orderNum` AS `orderNum`,`userreview`.`totalCopiesNum` AS `totalCopiesNum` from `userreview` where (not(`userreview`.`totalPrice` in (select max(`userreview`.`totalPrice`) from `userreview`)));

-- ----------------------------
-- View structure for userreview
-- ----------------------------
DROP VIEW IF EXISTS `userreview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `userreview` AS select coalesce(`u`.`username`,'总数') AS `userName`,sum(`o`.`totalPrice`) AS `totalPrice`,count(0) AS `orderNum`,sum(`o`.`copiesNum`) AS `totalCopiesNum` from (`order` `o` join `commonuser` `u`) where (`u`.`id` = `o`.`uid`) group by `u`.`username` with rollup;

SET FOREIGN_KEY_CHECKS = 1;
