
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` binary(16) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `salary` double unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `person` VALUES (_binary '+žLgªC¦Ø„\êbÖ»','Name1','mor31e@gmail.com','New Yorkshire','2020-11-11', 3010);

