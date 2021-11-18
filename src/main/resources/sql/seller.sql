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

 Date: 20/04/2021 17:14:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime(0) NOT NULL,
  `updated_at` datetime(0) NOT NULL,
  `remark_score` decimal(2, 1) NOT NULL DEFAULT 0.0,
  `disabled_flag` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES (1, '江苏和府餐饮管理有限公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 1.3, 0);
INSERT INTO `seller` VALUES (2, '北京烤鸭有限公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 0.3, 0);
INSERT INTO `seller` VALUES (3, '合肥食品制造有限公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 3.4, 0);
INSERT INTO `seller` VALUES (4, '青岛啤酒厂', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.1, 0);
INSERT INTO `seller` VALUES (5, '杭州轻食有限公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.6, 0);
INSERT INTO `seller` VALUES (6, '九竹食品加工公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.4, 0);
INSERT INTO `seller` VALUES (7, '奔潮食品加工公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 2.4, 0);
INSERT INTO `seller` VALUES (8, '百沐食品加工公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.6, 0);
INSERT INTO `seller` VALUES (9, '韩蒂衣食品加工公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.1, 0);
INSERT INTO `seller` VALUES (10, '城外食品加工公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 0.5, 0);
INSERT INTO `seller` VALUES (11, '雪兔食品加工公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 2.4, 0);
INSERT INTO `seller` VALUES (12, '琳德食品公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.5, 0);
INSERT INTO `seller` VALUES (13, '深圳市盛华莲蓉食品厂', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 3.3, 0);
INSERT INTO `seller` VALUES (14, '桂林聚德苑食品有限公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 2.9, 0);
INSERT INTO `seller` VALUES (15, '天津达瑞仿真蛋糕模型厂', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.4, 0);
INSERT INTO `seller` VALUES (16, '上海镭德杰喷码技术有限公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 1.5, 0);
INSERT INTO `seller` VALUES (17, '凯悦饭店集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 0.5, 0);
INSERT INTO `seller` VALUES (18, '卡尔森环球酒店公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 3.8, 0);
INSERT INTO `seller` VALUES (19, '喜达屋酒店集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 5.4, 0);
INSERT INTO `seller` VALUES (20, '最佳西方国际集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 3.7, 0);
INSERT INTO `seller` VALUES (21, '精品国际饭店公司', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 2.1, 0);
INSERT INTO `seller` VALUES (22, '希尔顿集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 5.5, 0);
INSERT INTO `seller` VALUES (23, '雅高集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 3.1, 0);
INSERT INTO `seller` VALUES (24, '万豪国际集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 4.9, 0);
INSERT INTO `seller` VALUES (25, '胜腾酒店集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 3.5, 0);
INSERT INTO `seller` VALUES (26, '洲际酒店集团', '2021-04-20 17:13:14', '2021-04-20 17:13:14', 2.7, 0);

SET FOREIGN_KEY_CHECKS = 1;
