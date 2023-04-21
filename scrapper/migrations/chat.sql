CREATE TABLE chat (
    id                      BIGINT          PRIMARY KEY,
    bot_state               VARCHAR(255),
    bot_last_active_command VARCHAR(255)
);

COMMENT ON COLUMN chat.id IS 'id телеграм чата';
COMMENT ON COLUMN chat.bot_state IS 'Определенное состояние бота после последней команды пользователя';
COMMENT ON COLUMN chat.bot_last_active_command IS 'Последняя команда пользователя';