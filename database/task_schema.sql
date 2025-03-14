CREATE TABLE tasks
(
    id          UUID PRIMARY KEY,
    user_id     UUID          NOT NULL,
    title       VARCHAR(100)  NOT NULL,
    description VARCHAR(2500) NOT NULL,
    status      VARCHAR(10)   NOT NULL,
    version     INT           NOT NULL DEFAULT 0
);

CREATE INDEX user_id_idx ON tasks (user_id);

