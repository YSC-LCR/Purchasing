
SET NAMES utf8;
--取消外來鍵約束
SET FOREIGN_KEY_CHECKS = 0;

create database eladmin;
use eladmin;
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

CREATE TABLE `eladmin`.`backend_prod` (
  `prod_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `sub_category_id` BIGINT(20) NULL DEFAULT NULL COMMENT '子類別流水號',
  `name` VARCHAR(45) NOT NULL COMMENT '商品名稱',
  `desc` VARCHAR(255) NULL DEFAULT NULL COMMENT '商品描述',
  `weight` INT NULL DEFAULT NULL COMMENT '商品重量',
  `can_fly` VARCHAR(2) NULL DEFAULT NULL COMMENT '可空運',
  `can_sea` VARCHAR(2) NULL DEFAULT NULL COMMENT '可海運',
  `create_date` DATETIME NOT NULL COMMENT '建立日期',
  `create_by` VARCHAR(45) NOT NULL COMMENT '建立者',
  `update_date` DATETIME NULL DEFAULT NULL COMMENT '修改時間',
  `update_by` VARCHAR(45) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`prod_id`),
  INDEX `fk_prod_psc_idx` (`sub_category_id` ASC),
  CONSTRAINT `fk_prod_psc`
    FOREIGN KEY (`sub_category_id`)
    REFERENCES `eladmin`.`backend_prod_sub_category` (`sub_caregory_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `eladmin`.`backend_prod_price_info` (
  `price_info_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `prod_id` BIGINT(20) NULL DEFAULT NULL COMMENT '商品流水號',
  `price_local` VARCHAR(45) NOT NULL COMMENT '本地價格',
  `place_consumption` VARCHAR(255) NULL DEFAULT NULL COMMENT '購買地',
  `create_date` DATETIME NOT NULL COMMENT '建立日期',
  `create_by` VARCHAR(45) NOT NULL COMMENT '建立者',
  `update_date` DATETIME NULL DEFAULT NULL COMMENT '修改時間',
  `update_by` VARCHAR(45) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`price_info_id`),
  INDEX `fk_ppi_prod_idx` (`prod_id` ASC),
  CONSTRAINT `fk_ppi_prod`
    FOREIGN KEY (`prod_id`)
    REFERENCES `eladmin`.`backend_prod` (`prod_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `backend_prod_unit` (
  `unit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `name` varchar(45) NOT NULL COMMENT '''規格名稱''',
  `create_date` datetime NOT NULL COMMENT '建立日期',
  `create_by` varchar(45) NOT NULL COMMENT '''建立者''',
  `update_date` datetime DEFAULT NULL COMMENT '修改者',
  `update_by` varchar(45) DEFAULT NULL COMMENT '''修改時間''',
  `delete_flag` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

CREATE TABLE `backend_prod_unit_info` (
  `unit_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `unit_info_desc` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` varchar(45) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`unit_info_id`),
  KEY `fk_pu_pui_idx` (`unit_id`),
  CONSTRAINT `fk_pu_pui` FOREIGN KEY (`unit_id`) REFERENCES `backend_prod_unit` (`unit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

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


-- ----------------------------
-- Records of backend_prod_images
-- ----------------------------
CREATE TABLE `eladmin`.`backend_prod_images` (
  `seq` BIGINT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '檔案名稱',
  `order` BIGINT NULL DEFAULT NULL COMMENT '順序',
  `is_main` INT NULL DEFAULT NULL COMMENT '是否主要圖片(Y:1/N:0)',
  `create_by` VARCHAR(255) NULL DEFAULT NULL COMMENT '建立者',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '建立日期',
  `update_by` VARCHAR(255) NULL DEFAULT NULL COMMENT '修改者',
  `update_date` DATETIME NULL DEFAULT NULL COMMENT '修改時間',
  `prod_id` BIGINT NULL DEFAULT NULL COMMENT '商品序號',
  PRIMARY KEY (`seq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- ----------------------------
-- Records of backend_prod_spec_info
-- ----------------------------
CREATE TABLE `eladmin`.`backend_prod_spec_info` (
  `spec_info_id` BIGINT ZEROFILL NOT NULL COMMENT '規格序號',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '規格名稱',
  `unit` VARCHAR(255) NULL DEFAULT NULL COMMENT '單位',
  `create_by` VARCHAR(255) NULL DEFAULT NULL COMMENT '建立者',
  `create_date` DATETIME NULL DEFAULT NULL COMMENT '建立日期',
  `update_by` VARCHAR(255) NULL DEFAULT NULL COMMENT '修改者',
  `update_date` DATETIME NULL DEFAULT NULL COMMENT '修改時間',
  `prod_id` BIGINT NULL DEFAULT NULL COMMENT '商品序號',
  PRIMARY KEY (`spec_info_id`),
  INDEX `fk_prod_spec_id_idx` (`prod_id` ASC) VISIBLE,
  CONSTRAINT `fk_prod_spec_id`
    FOREIGN KEY (`prod_id`)
    REFERENCES `eladmin`.`backend_prod` (`prod_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
	
	CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `eladmin`.`backend_prod_view` AS
    SELECT 
        UUID() AS `vid`,
        `bp`.`prod_id` AS `prod_id`,
        `bpc`.`name` AS `category_name`,
        `bpsc`.`name` AS `sub_category_name`,
        `bp`.`name` AS `prod_name`,
        `bpsi`.`prod_spec` AS `prod_spec`,
        `bppi`.`prod_price` AS `prod_price`,
        `bp`.`prod_desc` AS `prod_desc`,
        `bpsc`.`sub_category_id` AS `sub_category_id`,
        `bpc`.`category_id` AS `category_id`,
        `bp`.`weight` AS `weight`,
        `bp`.`can_fly` AS `can_fly`,
        `bp`.`can_sea` AS `can_sea`
    FROM
        ((((`eladmin`.`backend_prod` `bp`
        JOIN `eladmin`.`backend_prod_sub_category` `bpsc` ON ((`bpsc`.`sub_category_id` = `bp`.`sub_category_id`)))
        JOIN `eladmin`.`backend_prod_category` `bpc` ON ((`bpc`.`category_id` = `bpsc`.`category_id`)))
        LEFT JOIN (SELECT 
            `eladmin`.`backend_prod_price_info`.`prod_id` AS `prod_id`,
                CONCAT(`eladmin`.`backend_prod_price_info`.`place_consumption`, '/', MIN(`eladmin`.`backend_prod_price_info`.`price_local`)) AS `prod_price`
        FROM
            `eladmin`.`backend_prod_price_info`
        GROUP BY `eladmin`.`backend_prod_price_info`.`prod_id`) `bppi` ON ((`bppi`.`prod_id` = `bp`.`prod_id`)))
        LEFT JOIN (SELECT 
            `bpsi`.`prod_id` AS `prod_id`,
                GROUP_CONCAT(IFNULL(`bpsi`.`unit`, ''), CONVERT( `bpui`.`name` USING UTF8MB4)
                    SEPARATOR '/') AS `prod_spec`
        FROM
            (`eladmin`.`backend_prod_spec_info` `bpsi`
        JOIN `eladmin`.`backend_prod_unit_info` `bpui` ON ((`bpui`.`unit_info_id` = `bpsi`.`unit_info_id`)))
        GROUP BY `bpsi`.`prod_id`) `bpsi` ON ((`bpsi`.`prod_id` = `bp`.`prod_id`)))


