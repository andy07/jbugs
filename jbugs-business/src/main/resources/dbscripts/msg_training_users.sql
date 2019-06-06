INSERT INTO msg_training.users (id, counter, email, first_name, last_name, mobile_number, password, status, username)
VALUES (1, 5, 'silviu.cucuiet@msggroup.com', 'Silviu-Mihnea', 'Cucuiet', '070000000', 'pass', true, 'javaMaster');
INSERT INTO msg_training.users (id, counter, email, first_name, last_name, mobile_number, password, status, username)
VALUES (2, 5, 'andrei.florea@msggroup.com', 'Andrei', 'Florea', '070000001', 'pass', true, 'andy07');
INSERT INTO msg_training.users (id, counter, email, first_name, last_name, mobile_number, password, status, username)
VALUES (3, 5, 'alexandra.dragos@msggroup.com', 'Alexandra', 'Dragos', '070000002', 'pass', true, 'Dr.Ale');
INSERT INTO msg_training.users (id, counter, email, first_name, last_name, mobile_number, password, status, username)
VALUES (4, 5, 'casi.campean@msggroup.com', 'Casi', 'Campean', '070000003', 'pass', true, 'Casi');
INSERT INTO msg_training.users (id, counter, email, first_name, last_name, mobile_number, password, status, username)
VALUES (5, 5, 'diana.stefan@msggroup.com', 'Diana', 'Stefan', '070000004', 'pass', true, 'Dia');


UPDATE `msg_training`.`users` SET `first_name`='Silviu Mihnea', `mobile_number`='+40700000000', `username`='cucuis' WHERE `id`='1';
UPDATE `msg_training`.`users` SET `mobile_number`='+40700000019', `username`='florea' WHERE `id`='2';
UPDATE `msg_training`.`users` SET `mobile_number`='+40700000027', `username`='dragoa' WHERE `id`='3';
UPDATE `msg_training`.`users` SET `mobile_number`='+40700000032', `username`='campec' WHERE `id`='4';
UPDATE `msg_training`.`users` SET `mobile_number`='+40700000048', `username`='stefad' WHERE `id`='5';


ALTER TABLE `msg_training`.`users`
CHANGE COLUMN `email` `email` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `first_name` `first_name` VARCHAR(20) NOT NULL ,
CHANGE COLUMN `last_name` `last_name` VARCHAR(20) NOT NULL ,
CHANGE COLUMN `mobile_number` `mobile_number` VARCHAR(20) NOT NULL ,
CHANGE COLUMN `password` `password` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `username` `username` VARCHAR(50) NOT NULL ;

ALTER TABLE `msg_training`.`users`
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC),
DROP INDEX `first_name_UNIQUE` ;

