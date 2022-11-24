-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema CA3
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `CA3` ;

-- -----------------------------------------------------
-- Schema CA3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CA3` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `CA3` ;

-- -----------------------------------------------------
-- Table `CA3`.`fuel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`fuel` ;

CREATE TABLE IF NOT EXISTS `CA3`.`fuel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`users` ;

CREATE TABLE IF NOT EXISTS `CA3`.`users` (
  `user_name` VARCHAR(25) NOT NULL,
  `user_pass` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`profile` ;

CREATE TABLE IF NOT EXISTS `CA3`.`profile` (
  `email` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_profile_users1_idx` (`user_name` ASC) VISIBLE,
  CONSTRAINT `fk_profile_users1`
    FOREIGN KEY (`user_name`)
    REFERENCES `CA3`.`users` (`user_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`journey_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`journey_type` ;

CREATE TABLE IF NOT EXISTS `CA3`.`journey_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`journey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`journey` ;

CREATE TABLE IF NOT EXISTS `CA3`.`journey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `total_emission` FLOAT NOT NULL,
  `total_distance` FLOAT NOT NULL,
  `total_cost` FLOAT NOT NULL,
  `profile_id` INT NOT NULL,
  `journey_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_journey_profile1_idx` (`profile_id` ASC) VISIBLE,
  INDEX `fk_journey_journey_type1_idx` (`journey_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_journey_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `CA3`.`profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_journey_journey_type1`
    FOREIGN KEY (`journey_type_id`)
    REFERENCES `CA3`.`journey_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`roles` ;

CREATE TABLE IF NOT EXISTS `CA3`.`roles` (
  `role_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`role_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`transportation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`transportation` ;

CREATE TABLE IF NOT EXISTS `CA3`.`transportation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`trip` ;

CREATE TABLE IF NOT EXISTS `CA3`.`trip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `distance` FLOAT NOT NULL,
  `emission` FLOAT NOT NULL,
  `cost` FLOAT NOT NULL,
  `journey_id` INT NOT NULL,
  `fuel_id` INT NOT NULL,
  `transportation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_journey1_idx` (`journey_id` ASC) VISIBLE,
  INDEX `fk_trip_fuel1_idx` (`fuel_id` ASC) VISIBLE,
  INDEX `fk_trip_transportation1_idx` (`transportation_id` ASC) VISIBLE,
  CONSTRAINT `fk_trip_journey1`
    FOREIGN KEY (`journey_id`)
    REFERENCES `CA3`.`journey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_fuel1`
    FOREIGN KEY (`fuel_id`)
    REFERENCES `CA3`.`fuel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_transportation1`
    FOREIGN KEY (`transportation_id`)
    REFERENCES `CA3`.`transportation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA3`.`users_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA3`.`users_roles` ;

CREATE TABLE IF NOT EXISTS `CA3`.`users_roles` (
  `role_name` VARCHAR(20) NOT NULL,
  `user_name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`role_name`, `user_name`),
  INDEX `fk_roles_has_users_users1_idx` (`user_name` ASC) VISIBLE,
  INDEX `fk_roles_has_users_roles1_idx` (`role_name` ASC) VISIBLE,
  CONSTRAINT `fk_roles_has_users_roles1`
    FOREIGN KEY (`role_name`)
    REFERENCES `CA3`.`roles` (`role_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_has_users_users1`
    FOREIGN KEY (`user_name`)
    REFERENCES `CA3`.`users` (`user_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
