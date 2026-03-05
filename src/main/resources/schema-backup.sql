CREATE TABLE Category
(
    id IDENTITY PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE Hardware
(
    id IDENTITY PRIMARY KEY,
    name       VARCHAR(50)    NOT NULL,
    code       VARCHAR(255),
    price      DECIMAL(10, 2) NOT NULL,
    quantity   INT,
    categoryId INT,
    FOREIGN KEY (categoryId) REFERENCES Category (id)
);
