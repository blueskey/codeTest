package mq.message;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.ldap.ControlFactory;

/**
 * Created by Administrator on 2016/2/26 0026.
 */
public class MessageSender {
    public static final int SEND_NUM = 5;

    public static final String BPROKEN_URL = "tcp://localhost:61616";

    public static String DESTINATION = "hoo.mq.queue";

    public static void sendMessage(Session session, MessageProducer producer) throws Exception{
        for (int i = 0; i < SEND_NUM; i++) {

            String message = "发送消息第" + (i + 1) + "条";
            TextMessage textMessage = session.createTextMessage();
            System.out.println(textMessage);
            producer.send(textMessage);

        }
    }

    public static void run() throws Exception {
        Connection connection = null;
        Session session = null;
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BPROKEN_URL);

            connection = factory.createConnection();

            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(DESTINATION);

            MessageProducer producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session, producer);
            session.commit();
        } catch (Exception e) {
            throw e;
        }finally {
            if (null != session) {
                session.close();
            }
            if (null != connection) {
                connection.close();
            }
        }

    }

    public static void main(String[] args) throws Exception{
        MessageSender.run();
    }
}
