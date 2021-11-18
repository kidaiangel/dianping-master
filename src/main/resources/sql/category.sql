/*
 Navicat Premium Data Transfer

 Source Server         : 自娱自乐 -- 点评网站
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : dianpingdb

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 20/04/2021 18:24:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NOT NULL,
  `updated_at` datetime(0) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `icon_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_unique_index`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '2019-06-10 15:33:37', '2019-06-10 15:33:37', '美食', '/static/image/firstpage/food_u.png', 99);
INSERT INTO `category` VALUES (2, '2019-06-10 15:34:34', '2019-06-10 15:34:34', '酒店', '/static/image/firstpage/snack_u.png', 98);
INSERT INTO `category` VALUES (3, '2019-06-10 15:36:36', '2019-06-10 15:36:36', '休闲娱乐', '/static/image/firstpage/bar_o.png', 97);
INSERT INTO `category` VALUES (4, '2019-06-10 15:37:09', '2019-06-10 15:37:09', '结婚', '/static/image/firstpage/jiehun.png', 96);
INSERT INTO `category` VALUES (5, '2019-06-10 15:37:31', '2019-06-10 15:37:31', '足疗按摩', '/static/image/firstpage/zuliao.png', 96);
INSERT INTO `category` VALUES (6, '2019-06-10 15:37:49', '2019-06-10 15:37:49', 'KTV', '/static/image/firstpage/ktv_u.png', 95);
INSERT INTO `category` VALUES (7, '2019-06-10 15:38:14', '2019-06-10 15:38:14', '景点', '/static/image/firstpage/jingdian.png', 94);
INSERT INTO `category` VALUES (8, '2019-06-10 15:38:35', '2019-06-10 15:38:35', '丽人', '/static/image/firstpage/liren.png', 93);

SET FOREIGN_KEY_CHECKS = 1;
