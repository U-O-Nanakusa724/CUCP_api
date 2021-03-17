ALTER TABLE `cucp`.`car_details`
ADD COLUMN `sold_flag` TINYINT NOT NULL DEFAULT 0 COMMENT '成約フラグ' AFTER `note`,
CHANGE COLUMN `distance` `distance` VARCHAR(16) NULL DEFAULT NULL COMMENT '走行距離' ;