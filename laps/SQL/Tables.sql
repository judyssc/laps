DROP DATABASE IF EXISTS laps;

CREATE DATABASE laps;

USE laps;

DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS MANAGER;
DROP TABLE IF EXISTS ADMINISTRATOR;
DROP TABLE IF EXISTS ANNUAL_LEAVE;
DROP TABLE IF EXISTS MEDICAL_LEAVE;
DROP TABLE IF EXISTS COMPENSATION_LEAVE;
DROP TABLE IF EXISTS LEAVE_TYPES;
DROP TABLE IF EXISTS CALENDAR;

CREATE TABLE `laps`.`employee` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `userid` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  `managerid` VARCHAR(45) NULL,
  `annualleavebal` INT NULL,
  `medicalleavebal` INT NULL,
  `compleavebal` INT NULL,
  PRIMARY KEY (`id`));

