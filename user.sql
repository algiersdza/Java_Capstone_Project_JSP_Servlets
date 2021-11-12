CREATE TABLE `user`.`user` (
                               `FirstName` VARCHAR(45) DEFAULT NULL,
                               `LastName` VARCHAR(45) DEFAULT NULL,
                               `UserName` VARCHAR(45) DEFAULT NULL,
                               `Password` VARCHAR(45) DEFAULT NULL,
                               `EmailAddress` VARCHAR(45) DEFAULT NULL)
                               ENGINE = InnoDB DEFAULT CHARSET =utf8mb4 COLLATE=utf8mb4_0900_ai_ci;