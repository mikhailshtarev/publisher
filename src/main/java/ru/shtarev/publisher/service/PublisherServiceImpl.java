package ru.shtarev.publisher.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shtarev.publisher.client.PublisherClient;
import ru.shtarev.publisher.dto.Message;
import ru.shtarev.publisher.enumerated.Action;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    private final PublisherClient publisherClient;
    private final AtomicLong atomicLong = new AtomicLong(0);
    private final List<Thread> listThread = new ArrayList<>();

    @Autowired
    public PublisherServiceImpl(final PublisherClient publisherClient) {
        this.publisherClient = publisherClient;
    }

    @PostConstruct
    public void init() {

        final Runnable runnable = () -> {
            while (true) {
                publisherClient.sendMessage(createMessage());
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 5; i++) {
            listThread.add(new Thread(runnable));
        }
    }


    public void start() {
        listThread.forEach(Thread::start);
    }

    private Message createMessage() {
        Message message = new Message();
        Random random = new Random();
        message.setId(atomicLong.incrementAndGet());
        message.setMsisdn(100000000 + random.nextInt(900000000));
        int pick = random.nextInt(Action.values().length);
        message.setAction(Action.values()[pick]);
        message.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        log.info(message.toString());
        return message;
    }

}
