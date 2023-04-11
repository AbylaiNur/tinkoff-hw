CREATE TABLE link (
    id BIGINT PRIMARY KEY,
    url VARCHAR(255) not null,
    chat_id INT not null,
    FOREIGN KEY (chat_id) REFERENCES chat(id)
)