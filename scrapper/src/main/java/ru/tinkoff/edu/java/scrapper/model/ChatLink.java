package ru.tinkoff.edu.java.scrapper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.tinkoff.edu.java.scrapper.dto.ChatLinkId;

@Data
@Accessors(chain = true)
@Entity
@IdClass(ChatLinkId.class)
public class ChatLink {
    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Id
    @Column(name = "link_id")
    private Long linkId;

    @ManyToOne
    @JoinColumn(name = "chat_id", insertable = false, updatable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "link_id", insertable = false, updatable = false)
    private Link link;
}