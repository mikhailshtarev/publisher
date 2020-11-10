package ru.shtarev.publisher.client;

import ru.shtarev.publisher.dto.Message;

public interface PublisherClient {

    void sendMessage(Message message);

}
