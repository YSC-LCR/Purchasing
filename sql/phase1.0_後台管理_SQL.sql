
SET NAMES utf8;
--取消外來鍵約束
SET FOREIGN_KEY_CHECKS = 0;

create database eladmin;
use eladmin;

-- ----------------------------
-- Table structure for country_info
-- ----------------------------
CREATE TABLE `country_info` (
  `seq` bigint NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `code` varchar(45) DEFAULT NULL COMMENT '代號',
  `name` varchar(45) DEFAULT NULL COMMENT '名稱',
  `create_date` datetime DEFAULT NULL COMMENT '建立時間',
  `create_by` varchar(45) DEFAULT NULL COMMENT '建立者',
  `update_date` datetime DEFAULT NULL COMMENT '修改時間',
  `update_by` varchar(45) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='國家資訊';


-- ----------------------------
-- Table structure for exchange_rate
-- ----------------------------
CREATE TABLE `exchange_rate` (
  `seq` bigint NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `country_id` int DEFAULT NULL,
  `rate` decimal(10,3) DEFAULT NULL COMMENT '匯率',
  `create_date` varchar(45) DEFAULT NULL COMMENT '建立時間',
  `create_by` varchar(45) DEFAULT NULL COMMENT '建立者',
  `is_use` int DEFAULT NULL COMMENT '是否使用 (0:使用/1:不使用)',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='匯率';


-- ----------------------------
-- Table structure for shipping_info
-- ----------------------------
CREATE TABLE `shipping_info` (
  `seq` bigint NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `country_id` int DEFAULT NULL,
  `cost` int DEFAULT NULL COMMENT '費用',
  `type` int DEFAULT NULL COMMENT '運送方式(0:海運/1:空運)',
  `create_date` datetime DEFAULT NULL COMMENT '建立時間',
  `create_by` varchar(45) DEFAULT NULL COMMENT '建立者',
  `update_date` datetime DEFAULT NULL COMMENT '修改時間',
  `update_by` varchar(45) DEFAULT NULL COMMENT '修改者',
  `is_use` int DEFAULT NULL COMMENT '是否使用 (0:使用/1:不使用)',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='運費';


-- ----------------------------
-- Table structure for vendor_info
-- ----------------------------
CREATE TABLE `vendor_info` (
  `vendor_id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `vendor_name` varchar(225) DEFAULT NULL COMMENT '廠商名稱',
  `create_date` datetime DEFAULT NULL COMMENT '建立時間',
  `credate_by` varchar(45) DEFAULT NULL COMMENT '建立者',
  `update_date` datetime DEFAULT NULL COMMENT '修改時間',
  `update_by` varchar(45) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`vendor_id`),
  UNIQUE KEY `vendor_name_UNIQUE` (`vendor_name`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='廠商資訊';



-- ----------------------------
-- Table structure for backend_prod_category
-- ----------------------------
CREATE TABLE `backend_prod_category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `name` varchar(45) NOT NULL COMMENT '類別名稱',
  `create_date` datetime NOT NULL COMMENT '建立日期',
  `create_by` varchar(45) NOT NULL COMMENT '建立者',
  `update_date` datetime DEFAULT NULL COMMENT '修改者',
  `update_by` varchar(45) DEFAULT NULL COMMENT '修改時間',
  `delete_flag` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for backend_prod_sub_category
-- ----------------------------
CREATE TABLE `eladmin`.`backend_prod_sub_category` (
  `sub_category_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `category_id` BIGINT(20) NULL COMMENT '類別流水號',
  `name` VARCHAR(45) NOT NULL COMMENT '子類名稱',
  `sub_category_desc` VARCHAR(255) NULL COMMENT '子類描述',
  `create_date` DATETIME NOT NULL COMMENT '建立日期',
  `create_by` VARCHAR(45) NOT NULL COMMENT '建立者',
  `update_date` DATETIME NULL DEFAULT NULL COMMENT '修改時間',
  `update_by` VARCHAR(45) NULL DEFAULT NULL COMMENT '修改者',
  `delete_flag` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sub_category_id`),
  INDEX `fk_pc_psc_idx` (`category_id` ASC),
  CONSTRAINT `fk_pc_psc`
    FOREIGN KEY (`category_id`)
    REFERENCES `eladmin`.`backend_prod_category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------
-- Table structure for backend_prod
-- ----------------------------
CREATE TABLE `backend_prod` (
  `prod_id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `vendor_id` bigint DEFAULT NULL COMMENT '廠商編號',
  `name` varchar(45) NOT NULL COMMENT '商品名稱',
  `prod_desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `can_fly` varchar(2) DEFAULT NULL COMMENT '可空運',
  `can_sea` varchar(2) DEFAULT NULL COMMENT '可海運',
  `create_date` datetime NOT NULL COMMENT '建立日期',
  `create_by` varchar(45) NOT NULL COMMENT '建立者',
  `update_date` datetime DEFAULT NULL COMMENT '修改時間',
  `update_by` varchar(45) DEFAULT NULL COMMENT '修改者',
  `delete_flag` varchar(2) DEFAULT NULL,
  `counrty_id` int DEFAULT NULL COMMENT '國家代號',
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=460 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for backend_prod_subcategory_prod
-- ----------------------------
CREATE TABLE `backend_prod_subcategory_prod` (
  `seq` bigint NOT NULL AUTO_INCREMENT,
  `sub_category_id` bigint DEFAULT NULL COMMENT '副類別流水號',
  `prod_id` bigint DEFAULT NULL COMMENT '商品流水號',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 COMMENT='副類別_商品';


-- ----------------------------
-- Table structure for backend_prod_spec_info
-- ----------------------------
CREATE TABLE `backend_prod_spec_info` (
  `spec_info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '規格序號',
  `name` varchar(255) DEFAULT NULL COMMENT '規格名稱',
  `weight` int DEFAULT NULL COMMENT '商品重量',
  `create_by` varchar(255) DEFAULT NULL COMMENT '建立者',
  `create_date` datetime DEFAULT NULL COMMENT '建立日期',
  `update_by` varchar(255) DEFAULT NULL COMMENT '修改者',
  `update_date` datetime DEFAULT NULL COMMENT '修改時間',
  `delete_flag` varchar(2) DEFAULT NULL,
  `prod_id` bigint DEFAULT NULL COMMENT '商品序號',
  PRIMARY KEY (`spec_info_id`),
  KEY `fk_prod_spec_id_idx` (`prod_id`),
  CONSTRAINT `fk_prod_spec_id` FOREIGN KEY (`prod_id`) REFERENCES `backend_prod` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for backend_prod_price_info
-- ----------------------------
CREATE TABLE `backend_prod_price_info` (
  `price_info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `spec_info_id` bigint DEFAULT NULL COMMENT '規格流水號',
  `price_local` varchar(45) NOT NULL COMMENT '本地價格',
  `place_consumption` varchar(255) DEFAULT NULL COMMENT '購買地',
  `create_date` datetime NOT NULL COMMENT '建立日期',
  `create_by` varchar(45) NOT NULL COMMENT '建立者',
  `update_date` datetime DEFAULT NULL COMMENT '修改時間',
  `update_by` varchar(45) DEFAULT NULL COMMENT '修改者',
  `delete_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`price_info_id`),
  KEY `fk_ppi_spec_idx` (`spec_info_id`),
  CONSTRAINT `fk_ppi_spec` FOREIGN KEY (`spec_info_id`) REFERENCES `backend_prod_spec_info` (`spec_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for backend_prod_images
-- ----------------------------
CREATE TABLE `backend_prod_images` (
  `seq` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '檔案名稱',
  `order` bigint DEFAULT NULL COMMENT '順序',
  `is_main` int DEFAULT NULL COMMENT '是否主要圖片(Y:1/N:0)',
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '建立者',
  `create_date` datetime DEFAULT NULL COMMENT '建立日期',
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改者',
  `update_date` datetime DEFAULT NULL COMMENT '修改時間',
  `prod_id` bigint DEFAULT NULL COMMENT '商品序號',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for backend_prod_unit
-- ----------------------------
CREATE TABLE `backend_prod_unit` (
  `unit_id` int NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `name` varchar(45) NOT NULL COMMENT '''規格名稱''',
  `create_date` datetime NOT NULL COMMENT '建立日期',
  `create_by` varchar(45) NOT NULL COMMENT '''建立者''',
  `update_date` datetime DEFAULT NULL COMMENT '修改者',
  `update_by` varchar(45) DEFAULT NULL COMMENT '''修改時間''',
  `delete_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for backend_prod_unit_info
-- ----------------------------
CREATE TABLE `backend_prod_unit_info` (
  `unit_info_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `unit_info_desc` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` varchar(45) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `unit_id` int DEFAULT NULL,
  PRIMARY KEY (`unit_info_id`),
  KEY `fk_pu_pui_idx` (`unit_id`),
  CONSTRAINT `fk_pu_pui` FOREIGN KEY (`unit_id`) REFERENCES `backend_prod_unit` (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '創建日期',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外鏈',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '選單名稱',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '組件',
  `pid` bigint(20) NOT NULL COMMENT '上級選單ID',
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '圖示',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '連結位址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '2018-12-18 15:11:29', b'0', '系統管理', NULL, 0, 1, 'system', 'system');
INSERT INTO `menu` VALUES (2, '2018-12-18 15:14:44', b'0', '用戶管理', 'system/user/index', 1, 2, 'peoples', 'user');
INSERT INTO `menu` VALUES (3, '2018-12-18 15:16:07', b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role');
INSERT INTO `menu` VALUES (4, '2018-12-18 15:16:45', b'0', '許可權管理', 'system/permission/index', 1, 4, 'permission', 'permission');
INSERT INTO `menu` VALUES (5, '2018-12-18 15:17:28', b'0', '功能表管理', 'system/menu/index', 1, 5, 'menu', 'menu');
INSERT INTO `menu` VALUES (6, '2018-12-18 15:17:48', b'0', '系統監控', NULL, 0, 11, 'monitor', 'monitor');
INSERT INTO `menu` VALUES (7, '2018-12-18 15:18:26', b'0', '操作日誌', 'monitor/log/index', 6, 12, 'log', 'logs');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '別名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '創建日期',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名稱',
  `pid` int(11) NOT NULL COMMENT '上級許可權',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '超級管理員', '2018-12-03 12:27:48', 'ADMIN', 0);
INSERT INTO `permission` VALUES (2, '用戶管理', '2018-12-03 12:28:19', 'USER_ALL', 0);
INSERT INTO `permission` VALUES (7, '角色管理', '2018-12-03 12:28:19', 'ROLES_ALL', 0);
INSERT INTO `permission` VALUES (12, '許可權管理', '2018-12-09 20:11:37', 'PERMISSION_ALL', 0);
INSERT INTO `permission` VALUES (17, '選單管理', '2018-12-28 17:34:31', 'MENU_ALL', 0);
INSERT INTO `permission` VALUES (22, '活動管理', '2018-12-28 17:34:31', 'SLIDESHOW_ALL', 0);
INSERT INTO `permission` VALUES (27, 'Log查詢', '2018-12-28 17:34:31', 'LOG_ALL', 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '創建日期',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名稱',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '備註',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '2018-11-23 11:04:37', '管理員', '系統所有權');
INSERT INTO `role` VALUES (2, '2018-11-23 13:09:06', '普通用戶', '用於測試功能表');

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus`  (
  `menu_id` bigint(20) NOT NULL COMMENT '選單ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  INDEX `INDEX_roles_menus_1`(`role_id`) USING BTREE,
  CONSTRAINT `FK_roles_menus_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_roles_menus_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '頭像地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '創建日期',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '郵箱',
  `enabled` bigint(20) NULL DEFAULT NULL COMMENT '狀態：1啟用、0禁用',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密碼',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用戶名',
  `last_password_reset_time` datetime NULL DEFAULT NULL COMMENT '最後修改密碼的日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `INDEX_user_1`(`email`) USING BTREE,
  UNIQUE INDEX `INDEX_user_2`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'https://i.loli.net/2019/03/12/5c87788a4ffca.jpg', '2018-08-23 09:11:56', 'test1@gmail.com', 1, '14e1b600b1fd579f47433b88e8d85291', 'admin', '2019-01-17 09:53:21');
INSERT INTO `user` VALUES (2, 'https://i.loli.net/2019/03/12/5c87788a4ffca.jpg', '2018-12-27 20:05:26', 'test2@gmail.com', 1, '14e1b600b1fd579f47433b88e8d85291', 'test', NULL);


-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
CREATE TABLE `users_roles`  (
  `user_id` bigint(20) NOT NULL COMMENT '用戶ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `INDEX_users_roles_1`(`role_id`) USING BTREE,
  CONSTRAINT `FK_users_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_users_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (2, 2);

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
CREATE TABLE `roles_permissions`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '許可權ID',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `INDEX_roles_permissions_1`(`permission_id`) USING BTREE,
  CONSTRAINT `FK_roles_permissions_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_roles_permissions_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Records of roles_permissions
-- ----------------------------
INSERT INTO `roles_permissions` VALUES (1, 1);
INSERT INTO `roles_permissions` VALUES (2, 2);
INSERT INTO `roles_permissions` VALUES (2, 7);
INSERT INTO `roles_permissions` VALUES (2, 12);
INSERT INTO `roles_permissions` VALUES (2, 17);
INSERT INTO `roles_permissions` VALUES (2, 22);
INSERT INTO `roles_permissions` VALUES (2, 27);
INSERT INTO `roles_permissions` VALUES (2, 28);

