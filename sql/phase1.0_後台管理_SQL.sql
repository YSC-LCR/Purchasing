
SET NAMES utf8;
--取消外來鍵約束
SET FOREIGN_KEY_CHECKS = 0;

create database eladmin;
use eladmin;
CREATE TABLE `prod_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `name` varchar(45) NOT NULL COMMENT '類別名稱',
  `create_date` datetime NOT NULL COMMENT '建立日期',
  `create_by` varchar(45) NOT NULL COMMENT '建立者',
  `update_date` datetime DEFAULT NULL COMMENT '修改者',
  `update_by` varchar(45) DEFAULT NULL COMMENT '修改時間',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `eladmin`.`prod_sub_category` (
  `sub_caregory_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `category_id` BIGINT(20) NULL COMMENT '類別流水號',
  `name` VARCHAR(45) NOT NULL COMMENT '子類名稱',
  `desc` VARCHAR(255) NULL COMMENT '子類描述',
  `create_date` DATETIME NOT NULL COMMENT '建立日期',
  `create_by` VARCHAR(45) NOT NULL COMMENT '建立者',
  `update_date` DATETIME NULL DEFAULT NULL COMMENT '修改時間',
  `update_by` VARCHAR(45) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`sub_caregory_id`),
  INDEX `fk_pc_psc_idx` (`category_id` ASC),
  CONSTRAINT `fk_pc_psc`
    FOREIGN KEY (`category_id`)
    REFERENCES `eladmin`.`prod_category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `eladmin`.`prod` (
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
    REFERENCES `eladmin`.`prod_sub_category` (`sub_caregory_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `eladmin`.`prod_price_info` (
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
    REFERENCES `eladmin`.`prod` (`prod_id`)
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






