CREATE TABLE link (
    id  BIGINT          PRIMARY KEY,
    url VARCHAR(255)    NOT NULL UNIQUE
);

COMMENT ON COLUMN link.id IS 'id ссылки';
COMMENT ON COLUMN link.url IS 'строчная url ссылка';

-- Другие поля добавлю в следующей домашке