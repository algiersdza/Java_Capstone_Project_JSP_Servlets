
-- variables for JDBC are located in ConnectionDao
-- create schema called user
-- two create table queries below with initial INSERT INTO supplied
-- admin role created with table creation
-- credentials for Login into application with admin privileges:
-- username: root
-- password: iamroot

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


CREATE TABLE IF NOT EXISTS `user`.`food`(
                                            `FoodID` int(3) AUTO_INCREMENT NOT NULL,
                                            `ItemName` VARCHAR(100) DEFAULT NULL,
                                            `ItemDesc` VARCHAR(200) DEFAULT 'no Description' NULL,
                                            `ItemPrice` float DEFAULT NULL,
                                            PRIMARY KEY (FoodID))
    ENGINE = InnoDB DEFAULT CHARSET =utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user`.food(ItemName, ItemDesc, ItemPrice) VALUES
                        ('Hand Pressed Burger','two beef patties, house pickles, lettuce, tomato, red onion, craft signature burger sauce',18.00);
INSERT INTO `user`.food(ItemName, ItemDesc, ItemPrice) VALUES
                        ('Baja Chicken Salad','naturally raised chicken, mixed greens, iceberg lettuce, roasted corn, pico de gallo, black beans, avocado, cilantro, radish, shredded cheddar, crispy tortilla strips, creamy jalapeno lime dressing',20.00);
INSERT INTO `user`.food(ItemName, ItemDesc, ItemPrice) VALUES
                        ('Kale Margherita','house made tomato sauce, cherry tomatoes, kale, mozzarella, grana padano cheese, basil pesto',19.50);

