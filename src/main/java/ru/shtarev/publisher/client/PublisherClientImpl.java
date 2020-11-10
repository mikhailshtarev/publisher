package ru.shtarev.publisher.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.shtarev.publisher.dto.Message;

@Component
public class PublisherClientImpl implements PublisherClient {

    private final RestTemplate restTemplate;
    @Value("${client.base.url}")
    private String baseUrl;

    @Autowired
    public PublisherClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendMessage(final Message message) {
        restTemplate.postForObject(baseUrl + "/messages", message, Message.class);
    }
}
