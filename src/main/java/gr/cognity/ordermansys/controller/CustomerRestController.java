/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.cognity.ordermansys.controller;

import gr.cognity.ordermansys.model.Customer;
import gr.cognity.ordermansys.repository.CustomerRepository;
import gr.cognity.ordermansys.service.KafkaConsumer;
import gr.cognity.ordermansys.service.KafkaProducer;
import java.io.IOException;
import java.util.Calendar;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author nmpellias
 */
@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private KafkaConsumer kafkaConsumer;

    @GetMapping()
    public List<Customer> list() {

        // Send to Kafka that someone has seen the list of Customers
        kafkaProducer.sendMessage("Someone has seen the list of Customers");

        return customerRepository.findAll();
    }

    @GetMapping("/query")
    public List<Customer> fetchAllByStatusAndByAge(@RequestParam String status, @RequestParam int age) {
        return customerRepository.fetchAllByStatusAndByAge(status, age);
    }

    @GetMapping("/kafka")
    public List<String> listKafkaMessages() {
        return kafkaConsumer.getMessageList();
    }

    @GetMapping("/ages")
    public Object fetchSumOfAges() {
        return customerRepository.fetchSumOfAgesGroupByStatus();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}")
    public Customer put(@PathVariable String id, @RequestBody Customer input) {
        return null;
    }

    @PostMapping
    public Customer post(@RequestBody Customer customer) {
        return customerRepository.insert(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Optional<Customer> customer =  customerRepository.findById(id);
        kafkaProducer.sendMessage("DELETED Customer " + customer.get().toString() + " AT " + Calendar.getInstance());
        customerRepository.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}


