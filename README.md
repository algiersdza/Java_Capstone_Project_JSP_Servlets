### Part III of Capstone Project

#### DDL
```
CREATE TABLE IF NOT EXISTS `user`.`user` (
`UserID` int(3) AUTO_INCREMENT NOT NULL,
`FirstName` VARCHAR(45) DEFAULT NULL,
`LastName` VARCHAR(45) DEFAULT NULL,
`UserName` VARCHAR(45) DEFAULT NULL,
`Password` VARCHAR(45) DEFAULT NULL,
`EmailAddress` VARCHAR(45) DEFAULT NULL,
`Role` VARCHAR(45) DEFAULT 'user' NULL,
PRIMARY KEY (UserID))
ENGINE = InnoDB DEFAULT CHARSET =utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user`.user (FirstName, LastName, UserName, Password, EmailAddress, Role) VALUES
('Root','Admin','root','iamroot','root@mail.com','admin');
```

```
root credentials for RestCo:
- username: root 
- password: iamroot

MYSQL credentials:
- root
- CST2355Database
```
This war file build changes:
- Cleaned code and refactored controller classes into one class User.
- Created Singleton Connection
- Created a builder for the User Class
- Modified code for cleaner readability 

```
Part 3 requirements.  
- [x] MVC design pattern  
- [x] Builder design pattern  
- [x] Singleton design pattern  
- [ ] Observer design pattern  
```