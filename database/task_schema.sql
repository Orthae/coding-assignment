CREATE TABLE tasks
(
    id          UUID PRIMARY KEY,
    user_id     UUID          NOT NULL,
    title       VARCHAR(100)  NOT NULL,
    description VARCHAR(1000) NOT NULL,
    status      VARCHAR(10)   NOT NULL
);

CREATE INDEX user_id_idx ON tasks (user_id);

