package ru.shtarev.publisher.dto;

import lombok.Data;
import ru.shtarev.publisher.enumerated.Action;

@Data
public class Message {

    public long id;

    public long msisdn;

    public Action action;

    public long timestamp;
}
