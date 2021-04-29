CREATE TABLE IF NOT EXISTS brands(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS models(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    brand_id INT NOT NULL
);