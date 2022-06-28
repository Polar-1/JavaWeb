/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : homeecms

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 28/06/2022 22:54:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `address_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (1, 2, '陈双全', '18259234126', '福建省厦门市海沧区xxx');
INSERT INTO `tb_address` VALUES (2, 2, '陈双全2', '18259234126', '福建省厦门市集美区理工路600号');
INSERT INTO `tb_address` VALUES (7, 2, '测试账号', '11031321323', '奥术大师大多奥术大师大所多');
INSERT INTO `tb_address` VALUES (9, 15, '范廷豪', '18350959185', '123');
INSERT INTO `tb_address` VALUES (11, 8, '范廷豪', '18350959185', '123');
INSERT INTO `tb_address` VALUES (13, 8, '胡雨欣', '18888888888', '厦门工学院菜鸟驿站\r\n');

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `cart_id` int(0) NOT NULL AUTO_INCREMENT,
  `product_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NOT NULL,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_price` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_quantity` int(0) NULL DEFAULT NULL,
  `product_style` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_photo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
INSERT INTO `tb_cart` VALUES (1, 7, 2, '跳舞兰仿真花干花', '30', 5, '0', '7.jpg');
INSERT INTO `tb_cart` VALUES (2, 13, 2, '木纹笔筒', '35', 2, '0', '13.jpg');
INSERT INTO `tb_cart` VALUES (3, 12, 2, '手绘画餐厅墙面装饰画', '35', 2, '0', '1592836702270.jpg');
INSERT INTO `tb_cart` VALUES (4, 16, 2, '花瓶', '56', 3, '0', '1593241026791.jpg');
INSERT INTO `tb_cart` VALUES (5, 1, 2, '仿木纹漆金裂纹台面花瓶系列套装', '20', 4, '0', '1.jpg');
INSERT INTO `tb_cart` VALUES (6, 7, 9, '跳舞兰仿真花干花', '30', 1, '0', '7.jpg');
INSERT INTO `tb_cart` VALUES (7, 10, 9, '跳舞兰仿真花干花4', '20', 2, '0', '1592655179968.jpg');
INSERT INTO `tb_cart` VALUES (8, 16, 15, '花瓶', '56', 3, '0', '1593241026791.jpg');
INSERT INTO `tb_cart` VALUES (9, 14, 15, '复古式插画深褐色玻璃瓶', '50', 4, '0', '1593235091938.jpg');
INSERT INTO `tb_cart` VALUES (14, 14, 8, '复古式插画深褐色玻璃瓶', '50', 1, '0', '1593235091938.jpg');
INSERT INTO `tb_cart` VALUES (15, 8, 8, '跳舞兰仿真花干花2', '20', 1, '0', '8.jpg');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_parentid` int(0) NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, '装饰摆件', 0);
INSERT INTO `tb_category` VALUES (2, '布艺软饰', 0);
INSERT INTO `tb_category` VALUES (3, '墙式壁挂', 0);
INSERT INTO `tb_category` VALUES (7, '干花花艺', 1);
INSERT INTO `tb_category` VALUES (8, '花瓶花器', 1);
INSERT INTO `tb_category` VALUES (9, '桌布罩件', 2);
INSERT INTO `tb_category` VALUES (10, '抱枕靠垫', 2);
INSERT INTO `tb_category` VALUES (13, '壁纸', 3);
INSERT INTO `tb_category` VALUES (17, '123', 1);
INSERT INTO `tb_category` VALUES (18, '123', 0);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(0) NOT NULL,
  `order_price` int(0) NOT NULL,
  `order_time` datetime(0) NOT NULL,
  `is_pay` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_ship` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_receipt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_id` int(0) NOT NULL DEFAULT 0,
  `product_id` int(0) NOT NULL,
  `product_quantity` int(0) NOT NULL,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_photo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_price` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (85, '16563807256478', 8, 56, '2022-06-28 09:45:26', '1', '0', '0', 10, 16, 1, '花瓶', '1593241026791.jpg', 56);
INSERT INTO `tb_order` VALUES (90, '16564255716168', 8, 105, '2022-06-28 22:12:52', '0', '0', '0', 0, 12, 3, '手绘画餐厅墙面装饰画', '1592836702270.jpg', 35);
INSERT INTO `tb_order` VALUES (91, '16564277333268', 8, 56, '2022-06-28 22:48:53', '1', '0', '0', 13, 16, 1, '花瓶', '1593241026791.jpg', 56);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `product_id` int(0) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_info` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_price` int(0) NOT NULL,
  `product_stock` int(0) NOT NULL,
  `product_fid` int(0) NULL DEFAULT NULL,
  `product_cid` int(0) NULL DEFAULT NULL,
  `product_photo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_style` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `product_sales` int(0) NULL DEFAULT 0,
  `product_time` datetime(0) NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (1, '仿木纹漆金裂纹台面花瓶系列套装', '', 20, 48, 1, 8, '1.jpg', '0', 20, '2022-06-23 09:59:33');
INSERT INTO `tb_product` VALUES (7, '跳舞兰仿真花干花', '', 30, 176, 1, 7, '7.jpg', '0', 59, '2022-06-23 10:04:56');
INSERT INTO `tb_product` VALUES (8, '跳舞兰仿真花干花2', '', 20, 35, 1, 7, '8.jpg', '0', 89, '2022-06-23 10:15:15');
INSERT INTO `tb_product` VALUES (9, '跳舞兰仿真花干花3', '', 20, 30, 1, 7, '1592655156977.jpg', '0', 37, '2022-06-23 10:17:16');
INSERT INTO `tb_product` VALUES (10, '跳舞兰仿真花干花4', '', 20, 40, 1, 7, '1592655179968.jpg', '0', 79, '2022-06-23 10:19:35');
INSERT INTO `tb_product` VALUES (11, '新中式装饰壁挂客厅轻奢墙面装饰', '', 130, 40, 3, 13, '1592828240086.jpg', '0', 0, '2022-06-23 10:21:05');
INSERT INTO `tb_product` VALUES (12, '手绘画餐厅墙面装饰画', '', 35, 60, 3, 13, '1592836702270.jpg', '0', 0, '2022-06-23 10:23:46');
INSERT INTO `tb_product` VALUES (13, '木纹笔筒', '', 35, 37, 1, 8, '13.jpg', '0', 0, '2022-06-23 10:28:19');
INSERT INTO `tb_product` VALUES (14, '复古式插画深褐色玻璃瓶', '', 50, 80, 1, 8, '1593235091938.jpg', '0', 0, '2022-06-23 15:35:04');
INSERT INTO `tb_product` VALUES (15, '帆船壁挂壁画客房客厅壁画', '', 36, 78, 3, 13, '1593235194484.jpg', '0', 0, '2022-06-23 15:37:27');
INSERT INTO `tb_product` VALUES (16, '花瓶', '花瓶详细信息', 56, 56, 1, 7, '1593241026791.jpg', '0', 0, '2022-06-23 15:39:53');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未知昵称',
  `user_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_vip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_viptime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_photo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0.jpg',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '管理员', 'admin', '女', '0', '0', '2', '1.jpg');
INSERT INTO `tb_user` VALUES (2, 'yoyling', '测试小熊', 'yoyling', '男', '0', '0', '1', '2.jpg');
INSERT INTO `tb_user` VALUES (3, '88', '88', '88', '男', '0', '0', '1', '3.jpg');
INSERT INTO `tb_user` VALUES (4, '2', '2', '2', '男', '0', '0', '1', '4.jpg');
INSERT INTO `tb_user` VALUES (5, '3', '3', '3', '女', '0', '0', '2', '5.jpg');
INSERT INTO `tb_user` VALUES (6, '8', '8', '8', '男', '0', '0', '2', '6.jpg');
INSERT INTO `tb_user` VALUES (7, '999', '无昵称哦哦', '999', '女', '0', '0', '1', '7.jpg');
INSERT INTO `tb_user` VALUES (8, '111', '无昵称哦', '11', NULL, '0', '0', '1', 'default_0.jpg');
INSERT INTO `tb_user` VALUES (9, 'asd', '失眠', 'asd', '女', '0', '0', '1', '9.png');
INSERT INTO `tb_user` VALUES (10, '123', '无昵称哦', '123', NULL, '0', '0', '1', 'default_0.jpg');
INSERT INTO `tb_user` VALUES (11, '123456', '无昵称哦', '123', NULL, '0', '0', '1', '11.png');
INSERT INTO `tb_user` VALUES (12, '11', '无昵称哦', '122', NULL, '0', '0', '1', 'default_0.jpg');
INSERT INTO `tb_user` VALUES (13, '888', '无昵称哦', '888', NULL, '0', '0', '1', 'default_0.jpg');
INSERT INTO `tb_user` VALUES (14, '1', '无昵称哦', '1', NULL, '0', '0', '1', 'default_0.jpg');
INSERT INTO `tb_user` VALUES (15, 'tom001', '无昵称哦', '123456', NULL, '0', '0', '1', 'default_0.jpg');
INSERT INTO `tb_user` VALUES (16, 'wdnmd', '无昵称哦', 'q23', NULL, '0', '0', '1', 'default_0.jpg');
INSERT INTO `tb_user` VALUES (17, 'tom002', '无昵称哦', '123456', NULL, '0', '0', '1', 'default_0.jpg');

SET FOREIGN_KEY_CHECKS = 1;
