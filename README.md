### Part III & IV of Capstone Project

The final build for the Capstone project. This project demonstrates the usage of .war files, design patterns, Taglibs and JSP.

Deployed on Tomcat 9.0.54.

Video Demo: https://youtu.be/xBViOFjkxME

#### DDL for user table
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

#### DDL for food table
```
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
- Created UserDao and FoodDao
- Created Singleton Connection
- Created a builder for the User Class, Food Class and CheckOut Class
- Created Observer Pattern NewEmailNotifier Class
- Modified code for cleaner readability
- add food to menu by admin role
- delete food from menu by admin role
- show menu to user role
- show menu to admin role
- disabled submit button on checkout-cart.jsp
- add to cart
- calculate total
- update total with quantity change
- restrict admin access when logged as user role.
```
Part 3 & 4 requirements.  
- [x] MVC design pattern  
- [x] Builder design pattern  FoodBuilder, UserBuilder, CheckOutBuilder
- [x] Singleton design pattern  ConnectionDao
- [x] Observer design pattern  observable ( UserBuilder : IObservable) | observer (NewEmailNotifier : IObserver)
```
