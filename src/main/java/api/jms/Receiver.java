package api.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

//    @JmsListener(destination = "jms/booksQueue", containerFactory = "ConnectionFactory")
//    public void receiveMessage(String email) {
//        System.out.println("Received <" + email + ">");
//    }

}
