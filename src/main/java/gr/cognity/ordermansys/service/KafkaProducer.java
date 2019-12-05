/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.cognity.ordermansys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmpellias
 */
@Service
public class KafkaProducer {

    // private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String TOPIC1 = "MYREPLTOPIC1";
    private static final String TOPIC2 = "MYREPLTOPIC2";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        this.kafkaTemplate.send(TOPIC1, message);
        this.kafkaTemplate.send(TOPIC2, message);
    }
}

