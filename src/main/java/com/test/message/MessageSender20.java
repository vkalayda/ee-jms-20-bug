package com.test.message;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class MessageSender20 {

    @Resource(mappedName = "jms/TestMessageQueue")
    private Queue testMessageQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    public void send() {
        context.createProducer().send(testMessageQueue, "Hello world");
    }

}
