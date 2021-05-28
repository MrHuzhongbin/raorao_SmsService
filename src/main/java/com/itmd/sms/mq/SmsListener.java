package com.itmd.sms.mq;

import com.itmd.sms.util.SmsUtil;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;

    /**
     * 发送短信验证码类型
     * @param
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "Sms.verify.code.queue", durable = "true"),
            exchange = @Exchange(
                    value = "raorao.book.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = {"sms.verity.code"}))
    public void listenSendSms(Map<String,String>msg){
        if (CollectionUtils.isEmpty(msg)) {
            return;
        }
        String phone = msg.get("phone");
        if(StringUtils.isEmpty(phone)){
            return;
        }
        String code = msg.get("code");
        if(StringUtils.isEmpty(code)){
            return;
        }
        smsUtil.sendSms(phone,code);
    }
}
