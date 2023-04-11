CREATE TABLE chat (
    id BIGINT PRIMARY KEY,
    bot_state VARCHAR(255),
    bot_last_active_command VARCHAR(255)
)