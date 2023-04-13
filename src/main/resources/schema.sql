DROP TABLE IF EXISTS sk_example_table;

CREATE TABLE IF NOT EXISTS sk_example_table
(
id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
obj jsonb NOT NULL,
CONSTRAINT pk_example PRIMARY KEY (id)
);

INSERT INTO sk_example_table (obj) VALUES ('{"current": 0.0}');
