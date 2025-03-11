CREATE TABLE users
(
    id       UUID PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role     VARCHAR(100) NOT NULL
);

CREATE INDEX username_idx ON users (username);