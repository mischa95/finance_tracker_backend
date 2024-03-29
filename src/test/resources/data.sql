--
--CREATE TABLE category (
--    id bigint NOT NULL AUTO_INCREMENT,
--    category_name varchar(255) DEFAULT NULL,
--    primary key (id)
--    );
--
--CREATE TABLE user (
--    id bigint NOT NULL AUTO_INCREMENT,
--    birth_date date,
--    email varchar(255) DEFAULT NULL,
--    first_name varchar(255) DEFAULT NULL,
--    last_name varchar(255) DEFAULT NULL,
--    password varchar(255) DEFAULT NULL,
--    sex varchar(255) DEFAULT NULL,
--    username varchar(255) DEFAULT NULL,
--    primary key (id)
--    );
--
--create table expense (
--    id bigint NOT NULL AUTO_INCREMENT,
--    currency varchar(255) DEFAULT NULL,
--    date date,
--    description varchar(255) DEFAULT NULL,
--    price double precision DEFAULT NULL,
--    category_id bigint DEFAULT NULL,
--    user_id bigint DEFAULT NULL,
--    primary key (id)
--    );

INSERT INTO category (id, category_name) values(1, 'Groceries');
INSERT INTO category (id, category_name) values(2, 'Bills');
INSERT INTO category (id, category_name) values(3, 'Household');
INSERT INTO category (id, category_name) values(4, 'Clothing');
INSERT INTO category (id, category_name) values(5, 'Dining out');
INSERT INTO category (id, category_name) values(6, 'Alcohol');
INSERT INTO category (id, category_name) values(7, 'Transportation');

INSERT INTO user (id, username, password, first_name, last_name, email, birth_date, sex) values(1, 'michal95', '$2a$04$fM6f4Lk2e7.aboIw3aWZUOGErhAdfYKjPgZ2lb0vTflFjbewvYh52', 'Michal', 'Tyrala','michaltyrala@icloud.com', '1995-09-19', 'M');
INSERT INTO user (id, username, password, first_name, last_name, email, birth_date, sex) values(2, 'michal90', '$2a$04$fM6f4Lk2e7.aboIw3aWZUOGErhAdfYKjPgZ2lb0vTflFjbewvYh52', 'Michal', 'Tyrala','michaltyrala@icloud.com', '1995-09-19', 'M');


INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(1, 12.01, 'EUR', 'bus ticket', 7, 1, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(2, 2.01, 'EUR', 'bread', 1, 1, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(3, 100.10, 'EUR', 'fuel', 7, 1, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(4, 15.10, 'EUR', 'lunch', 5, 1, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(5, 18.10, 'EUR', 'whiskey', 6, 1, '2023-01-12');

INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(6, 12.11, 'EUR', 'train ticket', 7, 2, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(7, 2.01, 'EUR', 'cheese', 1, 2, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(8, 100.10, 'EUR', 'fuel', 7, 2, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(9, 15.10, 'EUR', 'dinner', 5, 2, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, user_id, date) values(10, 18.10, 'EUR', 'tequila', 6, 2, '2023-01-12');

