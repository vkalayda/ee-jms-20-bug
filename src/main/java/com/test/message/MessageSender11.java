package com.test.message;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class MessageSender11 {

    @Resource(mappedName = "java:comp/DefaultJMSConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/TestMessageQueue")
    Queue testMessageQueue;

    public void send() {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(testMessageQueue);

            TextMessage message = session.createTextMessage("Hello world");

            messageProducer.send(message);

        } catch (JMSException ex) {
            throw new IllegalStateException(ex.getMessage(), ex);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException ex) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ex) {
                }
            }
        }
    }

}
