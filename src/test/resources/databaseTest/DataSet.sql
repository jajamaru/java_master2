ALTER TABLE `PERSON` AUTO_INCREMENT = 1;
INSERT INTO `PERSON` (name, birthday) VALUES 
	('p1', '2000-01-01'),
	('p2', '2001-01-01'),
	('p3', '2002-01-01'),
	('p4', '2003-01-01'),
	('p5', '2004-01-01')
;

ALTER TABLE `FRIEND` AUTO_INCREMENT = 1;
INSERT INTO `FRIEND` (idPerson, idFriend) VALUES 
	('1', '2'),
	('1', '3'),
	('2', '4')
;