-- possr.employees definition

CREATE TABLE `employees` (
  `employee_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `genre_id` bigint(20) unsigned NOT NULL,
  `curp` varchar(20) DEFAULT NULL,
  `nss` varchar(150) DEFAULT NULL,
  `rfc` varchar(15) DEFAULT NULL,
  `personal_phone` varchar(20) DEFAULT NULL,
  `personal_email` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `neighborhood_id` bigint(20) unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employees_unique` (`firstname`,`lastname`,`birthdate`,`genre_id`,`curp`,`nss`,`rfc`,`personal_phone`,`personal_email`,`address`,`neighborhood_id`),
  KEY `employees_genres_fk` (`genre_id`),
  KEY `employees_neighborhoods_fk` (`neighborhood_id`),
  CONSTRAINT `employees_genres_fk` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`),
  CONSTRAINT `employees_neighborhoods_fk` FOREIGN KEY (`neighborhood_id`) REFERENCES `neighborhoods` (`neighborhood_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;