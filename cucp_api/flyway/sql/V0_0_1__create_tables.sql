-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cucp
-- -----------------------------------------------------
USE `cucp` ;

-- -----------------------------------------------------
-- Table `cucp`.`cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cucp`.`cars` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` VARCHAR(64) NOT NULL COMMENT '車種コード',
  `name` VARCHAR(64) NULL DEFAULT NULL COMMENT '車種名',
  `created_at` DATE NOT NULL COMMENT '作成日',
  `updated_at` DATE NOT NULL COMMENT '更新日',
  `deleted_at` DATE NULL DEFAULT NULL COMMENT '論理削除日',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `cucp`.`stores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cucp`.`stores` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '販売店ID',
  `name` VARCHAR(64) NULL DEFAULT NULL COMMENT '販売店名',
  `created_at` DATE NOT NULL COMMENT '作成日',
  `updated_at` DATE NOT NULL COMMENT '更新日',
  `deleted_at` DATE NULL DEFAULT NULL COMMENT '論理削除日',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `cucp`.`car_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cucp`.`car_details` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `car_id` INT(10) UNSIGNED NOT NULL COMMENT '車種ID',
  `store_id` INT(10) UNSIGNED NOT NULL COMMENT '販売店ID',
  `color` VARCHAR(64) NULL DEFAULT NULL COMMENT 'ボディカラー',
  `distance` DOUBLE NULL DEFAULT NULL COMMENT '走行距離',
  `transmission` VARCHAR(8) NULL DEFAULT NULL COMMENT '変速システム',
  `model_year` VARCHAR(8) NULL DEFAULT NULL COMMENT '年式',
  `url` VARCHAR(255) NULL DEFAULT NULL COMMENT 'URL',
  `note` VARCHAR(64) NULL DEFAULT NULL COMMENT '備考',
  `created_at` DATE NOT NULL COMMENT '作成日',
  `updated_at` DATE NOT NULL COMMENT '更新日',
  `deleted_at` DATE NULL DEFAULT NULL COMMENT '論理削除日',
  PRIMARY KEY (`id`),
  INDEX `fk_car_details_cars1_idx` (`car_id` ASC),
  INDEX `fk_car_details_stores1_idx` (`store_id` ASC),
  CONSTRAINT `fk_car_details_cars1`
    FOREIGN KEY (`car_id`)
    REFERENCES `cucp`.`cars` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_details_stores1`
    FOREIGN KEY (`store_id`)
    REFERENCES `cucp`.`stores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `cucp`.`prices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cucp`.`prices` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '価格情報ID',
  `car_detail_id` INT(10) UNSIGNED NOT NULL COMMENT '詳細ID',
  `price` DOUBLE NOT NULL COMMENT '価格',
  `date` DATE NOT NULL COMMENT '日付',
  `created_at` DATE NOT NULL COMMENT '作成日',
  `updated_at` DATE NOT NULL COMMENT '更新日',
  `deleted_at` DATE NULL DEFAULT NULL COMMENT '論理削除日',
  PRIMARY KEY (`id`),
  INDEX `fk_price_car_details1_idx` (`car_detail_id` ASC),
  CONSTRAINT `fk_price_car_details1`
    FOREIGN KEY (`car_detail_id`)
    REFERENCES `cucp`.`car_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;