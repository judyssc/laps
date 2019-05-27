DROP DATABASE IF EXISTS laps;

CREATE DATABASE laps
DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

USE laps;

DROP TABLE IF EXISTS LEAVE_APPLICATION;
DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS MANAGER;
DROP TABLE IF EXISTS ADMINISTRATOR;
DROP TABLE IF EXISTS LEAVE_TYPES;
DROP TABLE IF EXISTS CALENDER;

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
  `mgremail` VARCHAR(45) NULL,
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
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT MGRID_FK FOREIGN KEY (mgr_id) REFERENCES manager(id));
  
  CREATE TABLE `laps`.`leave_application` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NULL,
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
    FOREIGN KEY (`employee_id`)
    REFERENCES `laps`.`employee` (`id`));
    
      
    CREATE TABLE `laps`.`leave_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `laps`.`calender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `day_name` VARCHAR(9) NULL,
  `weekend_flag` VARCHAR(45) NULL,
  `holiday_flag` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `laps`.`manager` (`id`, `name`, `userid`, `password`, `mgremail`) VALUES ('1', 'Felicia', 'felicia', '5f4dcc3b5aa765d61d8327deb882cf99','abc12@gmail.com');
INSERT INTO `laps`.`manager` (`id`, `name`, `userid`, `password`, `mgremail`) VALUES ('2', 'Judy', 'judy', '5f4dcc3b5aa765d61d8327deb882cf99','abc12@gmail.com');

INSERT INTO `laps`.`administrator` (`id`, `name`, `userid`, `password`) VALUES ('1', 'Dhilip', 'dhilip', '5f4dcc3b5aa765d61d8327deb882cf99');
INSERT INTO `laps`.`administrator` (`id`, `name`, `userid`, `password`) VALUES ('2', 'Leesiong', 'leesiong', '5f4dcc3b5aa765d61d8327deb882cf99');

INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('1', 'Chenghao', 'chenghao', '5f4dcc3b5aa765d61d8327deb882cf99', 'developer', 'employee', '1', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('2', 'Dhilip', 'dhilip', '5f4dcc3b5aa765d61d8327deb882cf99', 'developer', 'admin', '1', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('3', 'Felicia', 'felicia', '5f4dcc3b5aa765d61d8327deb882cf99', 'teamlead', 'manager', '1', '18', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('4', 'Guoan', 'guoan', '5f4dcc3b5aa765d61d8327deb882cf99', 'developer', 'employee', '1', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('5', 'Judy', 'judy', '5f4dcc3b5aa765d61d8327deb882cf99', 'teamlead', 'manager', '2', '18', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('6', 'Kishore', 'kishore', '5f4dcc3b5aa765d61d8327deb882cf99', 'developer', 'employee', '2', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('7', 'Leesiong', 'leesiong', '5f4dcc3b5aa765d61d8327deb882cf99', 'developer', 'admin', '2', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('8', 'Yvonne', 'yvonne', '5f4dcc3b5aa765d61d8327deb882cf99', 'developer', 'employee', '2', '14', '60', '0');
INSERT INTO `laps`.`employee` (`id`, `name`, `userid`, `password`, `designation`, `type`, `mgr_id`, `annualleavebal`, `medicalleavebal`, `compleavebal`) VALUES ('9', 'Xiaolin', 'xiaolin', '5f4dcc3b5aa765d61d8327deb882cf99', 'developer', 'employee', '2', '14', '60', '0');

INSERT INTO `laps`.`leave_application` (`id`, `employee_id`, `daysapplied`, `dateofapplication`, `startdate`, `enddate`, `status`, `reason`, `workdissemination`, `contactno`, `managercomments`, `type`) VALUES ('1', '1', '5', '2019-05-18 00:00:00', '2019-06-01 00:00:00', '2019-06-05 00:00:00', 'Applied', 'annual leave', 'Kishore', '12345678', 'no comment', 'annual');

INSERT INTO `laps`.`leave_types` (`id`, `type`) VALUES ('1', 'Annual Leave');
INSERT INTO `laps`.`leave_types` (`id`, `type`) VALUES ('2', 'Medical Leave');
INSERT INTO `laps`.`leave_types` (`id`, `type`) VALUES ('3', 'Compensation Leave');

INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-01-01','New Year’s Day');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-02-05','Chinese New Yeary');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-02-06','Chinese New Year');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-04-19','Good Friday');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-05-01','Labour Day');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-05-19','Vesak Day');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-06-05','Hari Raya Puasa');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-08-09','National Day');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-08-11','Hari Raya Haji');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-10-27','Deepavali');
INSERT INTO `laps`.`calender`(`date`,`holiday_flag`)
VALUES('2019-12-25','Christmas Day');