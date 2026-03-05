INSERT INTO category (ID, NAME, DESCRIPTION)
VALUES (1, 'CPU', 'New');
INSERT INTO category (ID, NAME, DESCRIPTION)
VALUES (2, 'GPU', 'Novi code');
INSERT INTO category (ID, NAME, DESCRIPTION)
VALUES (3, 'MBO', 'New and used');
INSERT INTO category (ID, NAME, DESCRIPTION)
VALUES (4, 'RAM', 'New and used');
INSERT INTO category (ID, NAME, DESCRIPTION)
VALUES (5, 'Storage', 'New and used');
INSERT INTO category (ID, NAME, DESCRIPTION)
VALUES (6, 'Other', 'New and used');

INSERT INTO HARDWARE(name, code, price, quantity, category_id)
VALUES ('CPU Novi', 'Novi CPU', 50000, 2, 2);

INSERT INTO HARDWARE(name, code, price, quantity, category_id)
VALUES ('GPU1', 'GPU1 noviji', 500000, 1222, 4);

INSERT INTO HARDWARE(name, code, price, quantity, category_id)
VALUES ('Diska', 'SATA', 5000000, 113, 5);

INSERT INTO HARDWARE(name, code, price, quantity, category_id)
VALUES ('Motherboard', '123', 100000, 214, 6);