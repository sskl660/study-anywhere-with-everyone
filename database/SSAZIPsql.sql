-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_name` VARCHAR(16) NOT NULL,
  `user_email` VARCHAR(40) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `user_number` VARCHAR(16) NOT NULL,
  `user_term` INT NOT NULL,
  `user_graduated` TINYINT NOT NULL DEFAULT 0,
  `user_joindate` DATETIME NOT NULL,
  `user_totalcomplete` INT NULL,
  `user_image` LONGBLOB NULL,
  `user_git` VARCHAR(200) NULL,
  `user_blog` VARCHAR(200) NULL,
  `user_devstyle` VARCHAR(45) NULL,
  `user_mbti` VARCHAR(10) NULL,
  `user_wishfield` VARCHAR(45) NULL,
  `user_introduce` VARCHAR(200) NULL,
  `user_techstack` VARCHAR(100) NULL,
  `user_totaltime` INT NULL,
  `user_weekcomplete` INT NULL,
  `user_weektime` INT NULL,
  `user_follower_cnt` INT NULL,
  `user_following_cnt` INT NULL,
  PRIMARY KEY (`user_email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`challenge` (
  `challenge_no` INT NOT NULL AUTO_INCREMENT,
  `challenge_name` VARCHAR(45) NULL,
  `challenge_category` VARCHAR(45) NULL,
  `challenge_level` INT NULL,
  `challenge_capacity` INT NULL,
  `challenge_startdate` DATETIME NULL,
  `challenge_enddate` DATETIME NULL,
  `challenge_desc` VARCHAR(600) NULL,
  `challenge_task_cnt` INT NULL,
  PRIMARY KEY (`challenge_no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`task` (
  `task_no` INT NOT NULL AUTO_INCREMENT,
  `task_file` VARCHAR(45) NULL,
  `task_image` LONGBLOB NULL,
  `task_desc` VARCHAR(300) NULL,
  `task_content` VARCHAR(2000) NULL,
  `task_deadline` DATETIME NULL,
  `fk_challenge_task_no` INT NULL,
  `fk_user_task_email` VARCHAR(40) NULL,
  `task_index` INT NULL,
  PRIMARY KEY (`task_no`),
  INDEX `chanllenge_no_idx` (`fk_challenge_task_no` ASC) VISIBLE,
  INDEX `user_id_idx` (`fk_user_task_email` ASC) VISIBLE,
  CONSTRAINT `chanllenge_no`
    FOREIGN KEY (`fk_challenge_task_no`)
    REFERENCES `mydb`.`challenge` (`challenge_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_email`
    FOREIGN KEY (`fk_user_task_email`)
    REFERENCES `mydb`.`user` (`user_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comment` (
  `comment_content` VARCHAR(400) NOT NULL,
  `comment_date` DATETIME NULL DEFAULT current_timestamp,
  `fk_user_comment_email` VARCHAR(40) NULL,
  `comment_no` INT NOT NULL AUTO_INCREMENT,
  `fk_task_comment_no` INT NULL,
  INDEX `user_email` (`fk_user_comment_email` ASC) VISIBLE,
  PRIMARY KEY (`comment_no`),
  INDEX `task_no_idx` (`fk_task_comment_no` ASC) VISIBLE,
  CONSTRAINT `user_email`
    FOREIGN KEY (`fk_user_comment_email`)
    REFERENCES `mydb`.`user` (`user_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `task_no`
    FOREIGN KEY (`fk_task_comment_no`)
    REFERENCES `mydb`.`task` (`task_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`group` (
  `fk_challenge_group_no` INT NOT NULL,
  `fk_user_group_email` VARCHAR(40) NOT NULL,
  `group_username` VARCHAR(16) NULL,
  PRIMARY KEY (`fk_challenge_group_no`, `fk_user_group_email`),
  INDEX `user_email_idx` (`fk_user_group_email` ASC) VISIBLE,
  CONSTRAINT `challenge_no`
    FOREIGN KEY (`fk_challenge_group_no`)
    REFERENCES `mydb`.`challenge` (`challenge_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_email`
    FOREIGN KEY (`fk_user_group_email`)
    REFERENCES `mydb`.`user` (`user_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`follow` (
  `fk_user_follow_email` VARCHAR(40) NOT NULL,
  `follow_follower` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`fk_user_follow_email`, `follow_follower`),
  CONSTRAINT `user_email`
    FOREIGN KEY (`fk_user_follow_email`)
    REFERENCES `mydb`.`user` (`user_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`feed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`feed` (
  `fk_user_feed_email` VARCHAR(40) NULL,
  `feed_no` INT NOT NULL AUTO_INCREMENT,
  `feed_info` JSON NULL,
  `feed_eventtime` DATETIME NULL,
  PRIMARY KEY (`feed_no`),
  INDEX `user_email_idx` (`fk_user_feed_email` ASC) VISIBLE,
  CONSTRAINT `user_email`
    FOREIGN KEY (`fk_user_feed_email`)
    REFERENCES `mydb`.`user` (`user_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`galaxytop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`galaxytop` (
  `galaxytop_email` VARCHAR(45) NOT NULL,
  `galaxytop_weektime` INT NULL,
  `galaxytop_name` VARCHAR(16) NULL,
  PRIMARY KEY (`galaxytop_email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`challengetop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`challengetop` (
  `challengetop_email` VARCHAR(45) NOT NULL,
  `challengetop_weektime` INT NULL,
  `challengetop_name` VARCHAR(16) NULL,
  PRIMARY KEY (`challengetop_email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`like` (
  `like_email` VARCHAR(45) NOT NULL,
  `fk_task_like_no` INT NOT NULL,
  PRIMARY KEY (`like_email`, `fk_task_like_no`),
  INDEX `task_no_idx` (`fk_task_like_no` ASC) VISIBLE,
  CONSTRAINT `task_no`
    FOREIGN KEY (`fk_task_like_no`)
    REFERENCES `mydb`.`task` (`task_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
