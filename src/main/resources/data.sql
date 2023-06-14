

INSERT INTO category (id, category_name) values(1, 'Groceries');
INSERT INTO category (id, category_name) values(2, 'Bills');
INSERT INTO category (id, category_name) values(3, 'Household');
INSERT INTO category (id, category_name) values(4, 'Clothing');
INSERT INTO category (id, category_name) values(5, 'Dining out');
INSERT INTO category (id, category_name) values(6, 'Alcohol');
INSERT INTO category (id, category_name) values(7, 'Transportation');


INSERT INTO expense (id, price, currency, description, category_id, date) values(1, 12, 'EUR', 'bus ticket', 7, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, date) values(2, 2, 'EUR', 'bread', 1, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, date) values(3, 100, 'EUR', 'fuel', 7, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, date) values(4, 15, 'EUR', 'lunch', 5, '2023-01-12');
INSERT INTO expense (id, price, currency, description, category_id, date) values(5, 18, 'EUR', 'whiskey', 6, '2023-01-12');

-- BCrypt encrypted password 'password' with strength 4
INSERT INTO login_user (id, username, password) values(1, 'michal95', '$2a$04$fM6f4Lk2e7.aboIw3aWZUOGErhAdfYKjPgZ2lb0vTflFjbewvYh52');