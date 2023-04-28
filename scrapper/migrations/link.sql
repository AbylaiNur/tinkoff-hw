CREATE TABLE link (
    id              BIGSERIAL       PRIMARY KEY,
    url             VARCHAR(255)    NOT NULL UNIQUE,
    last_checked    TIMESTAMP,
    last_updated    TIMESTAMP
);

COMMENT ON COLUMN link.id IS 'id ссылки';
COMMENT ON COLUMN link.url IS 'строчная url ссылка';
COMMENT ON COLUMN link.last_checked IS 'последняя проверка ссылки на изменения';
COMMENT ON COLUMN link.last_updated IS 'последнее обновление';

-- Другие поля добавлю в следующей домашке