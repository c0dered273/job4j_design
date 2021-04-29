-- CREATE TABLE IF NOT EXISTS brands(
--     id SERIAL PRIMARY KEY,
--     name VARCHAR NOT NULL
-- );
--
-- CREATE TABLE IF NOT EXISTS models(
--     id SERIAL PRIMARY KEY,
--     name VARCHAR NOT NULL
-- );

CREATE TABLE IF NOT EXISTS Authors(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS Books(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS books_authors(
    book_id INT,
    authors_id INT
);