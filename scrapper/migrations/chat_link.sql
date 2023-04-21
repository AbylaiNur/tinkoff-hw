CREATE TABLE chat_link (
    chat_id     BIGINT  NOT NULL,
    link_id     BIGINT  NOT NULL,
    PRIMARY KEY (chat_id, link_id),
    FOREIGN KEY (chat_id) REFERENCES chat(id),
    FOREIGN KEY (link_id) REFERENCES link(id)
);

COMMENT ON COLUMN chat_link.chat_id IS 'id телеграм чата';
COMMENT ON COLUMN chat_link.link_id IS 'id ссылки';