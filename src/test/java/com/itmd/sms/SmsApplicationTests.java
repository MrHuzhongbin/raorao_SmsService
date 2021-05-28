package com.itmd.sms;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SmsApplicationTests {


    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void sendMQ(){
        Map<String,String> map=new HashMap<>();
        map.put("phone", "+8617347671224");
        map.put("code", "939123");
        try {
            amqpTemplate.convertAndSend("raorao.book.exchange","sms.verity.code",  map);
        } catch (Exception e) {
        }
    }
}
