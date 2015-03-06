ALTER TABLE `PERSON` AUTO_INCREMENT = 1;
INSERT INTO `PERSON` (name, birthday,sexe) VALUES 
	('p1', '2000-01-01', 'H'),
	('p2', '2001-01-01', 'F'),
	('p3', '2002-01-01', 'H'),
	('p4', '2003-01-01', 'F'),
	('p5', '2004-01-01', 'H')
;

ALTER TABLE `FRIEND` AUTO_INCREMENT = 1;
INSERT INTO `FRIEND` (idPerson, idFriend) VALUES 
	('1', '2'),
	('1', '3'),
	('2', '4')
;