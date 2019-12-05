package gr.cognity.ordermansys;

import gr.cognity.ordermansys.service.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdermansysApplication {

        	public static void main(String[] args) {
		SpringApplication.run(OrdermansysApplication.class, args);
	}

}
