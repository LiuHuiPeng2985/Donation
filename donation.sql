/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : donation

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 17/04/2022 18:06:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '受捐单位',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '受捐单位地址',
  `company_linkman` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `company_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (1, '韩红基金会', '北京朝阳区东三环北路', '韩红', '400-8080-980', '2022-04-12 09:15:25');
INSERT INTO `company_info` VALUES (2, '阿里巴巴基金会', '浙江省杭州市余杭区', '孙利军', '9510211', '2022-04-12 09:15:25');
INSERT INTO `company_info` VALUES (3, '见义勇为基金会', '北京市东城区东长安街', '贾春旺', '010-66266322', '2022-04-12 09:15:25');
INSERT INTO `company_info` VALUES (4, '古天乐慈善基金会', '香港西九龙', '古天乐', '17870839939', '2022-04-12 09:15:25');
INSERT INTO `company_info` VALUES (5, '马云公益基金会', '浙江省杭州市', '马云', '15712312301', '2022-04-12 04:47:54');
INSERT INTO `company_info` VALUES (6, '232基金会', '江西省南昌市南昌县软件动漫学院10栋232', '揭雄伟', '18322960025', '2022-04-12 05:30:05');

-- ----------------------------
-- Table structure for donation_info
-- ----------------------------
DROP TABLE IF EXISTS `donation_info`;
CREATE TABLE `donation_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户外键id',
  `project_id` int(11) NULL DEFAULT NULL COMMENT '捐赠项目外键id',
  `project_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '捐赠项目名',
  `donate_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `project_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目详情',
  `type` int(2) NULL DEFAULT NULL COMMENT '1代表现金，0代表物资',
  `donor` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '捐赠单位或人',
  `status` int(2) NULL DEFAULT NULL COMMENT '0是申请中，1是申请完成，2是申请失败',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of donation_info
-- ----------------------------
INSERT INTO `donation_info` VALUES (3, 5, 4, '援欧抗疫计划', '1514107755648303105', '马云公益基金会和阿里巴巴基金会联合援欧抗疫', 1, '张三', 1, '2022-04-13 13:06:34');

-- ----------------------------
-- Table structure for donation_project
-- ----------------------------
DROP TABLE IF EXISTS `donation_project`;
CREATE TABLE `donation_project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NULL DEFAULT NULL COMMENT '捐赠单位外键',
  `project_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `project_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目详情',
  `project_leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目负责人',
  `project_status` int(10) NULL DEFAULT NULL COMMENT '项目状态（0是筹款中，1是结束筹款）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of donation_project
-- ----------------------------
INSERT INTO `donation_project` VALUES (1, 1, '韩红爱心-就在乡间', '韩红爱心，就在乡间项目', '韩红', 1, '2020-03-15 08:00:00', '2020-04-15 08:00:00', '2021-03-15 08:00:00');
INSERT INTO `donation_project` VALUES (2, 1, '韩红爱心驰援武汉', '新冠肺炎疫情发生后，武汉封城，急需物资', '韩红', 1, '2020-03-15 08:00:00', '2020-04-15 08:00:00', '2021-03-15 08:00:00');
INSERT INTO `donation_project` VALUES (3, 5, '新型冠状病毒研究与防控', '马云呼吁并支持所有的新型冠状病毒研究与防控', '马云', 1, '2020-03-15 08:00:00', '2020-04-15 08:00:00', '2021-03-15 08:00:00');
INSERT INTO `donation_project` VALUES (4, 5, '援欧抗疫计划', '马云公益基金会和阿里巴巴基金会联合援欧抗疫', '马云', 0, '2020-03-15 08:00:00', '2022-06-15 08:00:00', '2021-03-15 08:00:00');

-- ----------------------------
-- Table structure for item_list
-- ----------------------------
DROP TABLE IF EXISTS `item_list`;
CREATE TABLE `item_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `donation_info_id` int(11) NULL DEFAULT NULL COMMENT '物品清单外键id',
  `currency` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种',
  `amount` float(10, 2) NULL DEFAULT NULL COMMENT '捐赠金额',
  `item_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '捐赠物名称',
  `item_amount` int(10) NULL DEFAULT NULL COMMENT '捐赠数量',
  `supplier` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  `standard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成标准',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_list
-- ----------------------------
INSERT INTO `item_list` VALUES (1, 3, '人名币', 100.00, NULL, NULL, NULL, NULL, '2022-04-13 13:06:34');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `notice_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '揭雄伟怒捐1元钱', '一男子揭某，当天被人嘲讽，一怒之下，砸出1元巨款', '2022-04-16 09:15:25');
INSERT INTO `notice` VALUES (2, '揭雄伟捐款', '揭雄伟为3.21东航事故捐款。', '2022-04-15 20:59:14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `type` bit(1) NULL DEFAULT NULL COMMENT '（1是管理员，0是普通用户）',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `local` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体地址',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '刘辉鹏', 'e10adc3949ba59abbe56e057f20f883e', b'1', '15770561981', '1', 22, '江西', '宜春', '高安市', '2456867076@qq.com', '2022-04-06 07:10:25');
INSERT INTO `user` VALUES (5, '张三', 'e10adc3949ba59abbe56e057f20f883e', b'0', '15789631245', '1', 21, '北京', '北京', '朝阳区', 'zhangsan@qq.com', '2022-04-13 12:57:54');

SET FOREIGN_KEY_CHECKS = 1;
