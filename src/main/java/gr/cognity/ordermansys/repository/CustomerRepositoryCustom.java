/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.cognity.ordermansys.repository;

import gr.cognity.ordermansys.model.Customer;
import java.util.List;

/**
 *
 * @author nmpellias
 */
public interface CustomerRepositoryCustom {
    public List<Customer> fetchAllByStatusAndByAge(String status, int age);
    public Object fetchSumOfAgesGroupByStatus();
}





