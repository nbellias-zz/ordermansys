/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.cognity.ordermansys.service;

//import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Consumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmpellias
 */
@Service
public class KafkaConsumer extends Consumer {

    // private final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final List<String> messageList;

    public KafkaConsumer() {
        messageList = new ArrayList();
    }

    @KafkaListener(topics = "MYREPLTOPIC1", groupId = "1")
    public void consume1(String message) throws IOException {

        System.out.println(String.format("### -> Consume Message -> %s", message));
        messageList.add("1->" + message);
    }

    @KafkaListener(topics = "MYREPLTOPIC2", groupId = "1")
    public void consume2(String message) throws IOException {
        System.out.println(String.format("2### ->  Consume Message -> %s", message));
        messageList.add("2->" + message);
    }

    public List<String> getMessageList() {
        return this.messageList;
    }
}

