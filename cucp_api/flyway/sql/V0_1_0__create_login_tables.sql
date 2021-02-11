-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cucp
-- -----------------------------------------------------
USE `cucp` ;

-- -----------------------------------------------------
-- Table `cucp`.`users`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cucp`.`users` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` VARCHAR(40) NOT NULL COMMENT '車種コード',
  `password` VARCHAR(60) NOT NULL COMMENT '車種名',
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `created_at` DATE NOT NULL COMMENT '作成日',
  `updated_at` DATE NOT NULL COMMENT '更新日',
  `deleted_at` DATE NULL DEFAULT NULL COMMENT '論理削除日',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

-- -----------------------------------------------------
-- Table `cucp`.`authorities_main`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cucp`.`authorities_main` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `authorities_name` VARCHAR(40) NOT NULL COMMENT '車種コード',
  `created_at` DATE NOT NULL COMMENT '作成日',
  `updated_at` DATE NOT NULL COMMENT '更新日',
  `deleted_at` DATE NULL DEFAULT NULL COMMENT '論理削除日',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `authorities_name_UNIQUE` (`authorities_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

-- -----------------------------------------------------
-- Table `cucp`.`authorities`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cucp`.`authorities` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` VARCHAR(40) NOT NULL COMMENT '車種コード',
  `authority_id` INT(11) UNSIGNED NOT NULL COMMENT '車種名',
  `created_at` DATE NOT NULL COMMENT '作成日',
  `updated_at` DATE NOT NULL COMMENT '更新日',
  `deleted_at` DATE NULL DEFAULT NULL COMMENT '論理削除日',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `authority_id_idx` (`authority_id` ASC),
  CONSTRAINT `username`
    FOREIGN KEY (`username`)
    REFERENCES `cucp`.`users` (`username`),
  CONSTRAINT `authority_id`
    FOREIGN KEY (`authority_id`)
    REFERENCES `cucp`.`authorities_main` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;