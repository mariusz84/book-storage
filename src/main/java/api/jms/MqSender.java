package api.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MqSender {
    private static final Logger log = LoggerFactory.getLogger(MqSender.class);
    protected static Connection connection;
    protected static Session session;
    protected static Queue queue;

    public static void sendMessage(String firstName, String lastName) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                MqConnector.setupQueue();

                MessageProducer producer = MqConnector.getMessageProducer();
                Session session = MqConnector.getSession();

                TextMessage message = session.createTextMessage();
                message.setText("User[" + firstName + ", " + lastName + "]");
                producer.send(message);

                MqConnector.cleanUpQueue();
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        });
        executor.shutdown();
    }
}