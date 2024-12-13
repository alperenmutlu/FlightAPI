CREATE TABLE `flight` (
  `price` decimal(38,2) NOT NULL,
  `arrival_airport_id` bigint NOT NULL,
  `departure_airport_id` bigint NOT NULL,
  `departure_date` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `return_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKor550l1m73innd911e6nm8lj0` (`arrival_airport_id`),
  KEY `FKillsy04237nltbk2yryrbderb` (`departure_airport_id`),
  CONSTRAINT `FKillsy04237nltbk2yryrbderb` FOREIGN KEY (`departure_airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKor550l1m73innd911e6nm8lj0` FOREIGN KEY (`arrival_airport_id`) REFERENCES `airport` (`id`)
);
