DROP DATABASE IF EXISTS laps;

CREATE DATABASE laps
DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

USE laps;

DROP TABLE IF EXISTS LEAVE_APPLICATION;
DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS MANAGER;
DROP TABLE IF EXISTS ADMINISTRATOR;
DROP TABLE IF EXISTS LEAVE_TYPES;
DROP TABLE IF EXISTS CALENDAR;

CREATE TABLE `laps`.`administrator` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `userid` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `laps`.`manager` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `userid` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `laps`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `userid` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `designation` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `mgr_id` INT NULL,
  `annualleavebal` VARCHAR(45) NULL,
  `medicalleavebal` VARCHAR(45) NULL,
  `compleavebal` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT MGRID_FK FOREIGN KEY (mgr_id) REFERENCES manager(id));
  
  CREATE TABLE `laps`.`leave_application` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employeeid` INT NULL,
  `daysapplied` INT NULL,
  `dateofapplication` DATETIME NULL,
  `startdate` DATETIME NULL,
  `enddate` DATETIME NULL,
  `status` VARCHAR(45) NULL,
  `reason` VARCHAR(200) NULL,
  `workdissemination` VARCHAR(45) NULL,
  `contactno` INT NULL,
  `managercomments` VARCHAR(200) NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `LID_FK`
    FOREIGN KEY (`employeeid`)
    REFERENCES `laps`.`employee` (`id`));
    
      
    CREATE TABLE `laps`.`leave_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `laps`.`calendar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `day_name` VARCHAR(9) NULL,
  `weekend_flag` CHAR(1) NULL,
  `holiday_flag` CHAR(1) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `laps`.`manager` (`id`, `name`, `userid`, `password`) VALUES ('1', 'Felicia', 'felicia', 'password');
INSERT INTO `laps`.`manager` (`id`, `name`, `userid`, `password`) VALUES ('2', 'Judy', 'judy', 'password');

INSERT INTO `laps`.`administrator` (`id`, `name`, `userid`, `password`) VALUES ('1', 'Dhilip', 'dhilip', 'password');
INSERT INTO `laps`.`administrator` (`id`, `name`, `userid`, `password`) VALUES ('2', 'Leesiong', 'leesiong', 'password');

INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('1', 'Chenghao', 'chenghao', 'password', 'developer', 'employee', '1', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('2', 'Dhilip', 'dhilip', 'password', 'developer', 'admin', '1', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('3', 'Felicia', 'felicia', 'password', 'teamlead', 'manager', '1', '18', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('4', 'Guoan', 'guoan', 'password', 'developer', 'employee', '1', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('5', 'Judy', 'judy', 'password', 'teamlead', 'manager', '2', '18', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('6', 'Kishore', 'kishore', 'password', 'developer', 'employee', '2', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('7', 'Leesiong', 'leesiong', 'password', 'developer', 'admin', '2', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('8', 'Yvonne', 'yvonne', 'password', 'developer', 'employee', '2', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('9', 'Xiaolin', 'xiaolin', 'password', 'developer', 'employee', '2', '14', '60', '0');

INSERT INTO `laps`.`leave_application` (`id`, `employeeid`, `daysapplied`, `dateofapplication`, `startdate`, `enddate`, `status`, `reason`, `workdissemination`, `contactno`, `managercomments`, `type`) VALUES ('1', '1', '5', '2019-05-18 00:00:00', '2019-06-01 00:00:00', '2019-06-05 00:00:00', 'Applied', 'annual leave', 'Kishore', '12345678', 'no comment', 'annual');

INSERT INTO `laps`.`leave_types` (`id`, `name`) VALUES ('1', 'Annual Leave');
INSERT INTO `laps`.`leave_types` (`id`, `name`) VALUES ('2', 'Medical Leave');
INSERT INTO `laps`.`leave_types` (`id`, `name`) VALUES ('3', 'Compensation Leave');



















