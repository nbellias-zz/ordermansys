/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.cognity.ordermansys.repository;

import gr.cognity.ordermansys.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author nmpellias
 */
public interface CustomerRepository extends MongoRepository<Customer, String>, CustomerRepositoryCustom {
    
}




