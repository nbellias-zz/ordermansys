/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.cognity.ordermansys.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import gr.cognity.ordermansys.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

/**
 *
 * @author nmpellias
 */
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Customer> fetchAllByStatusAndByAge(String status, int age) {
        Query q = new Query();
        q.addCriteria(Criteria.where("status").is(status));
        q.addCriteria(Criteria.where("age").is(age));

        return mongoTemplate.find(q, Customer.class);
    }

    @Override
    public Object fetchSumOfAgesGroupByStatus() {

        // db.customers.aggregate([{$group:{_id:"$status",total:{$sum:"$age"}}}])
        Aggregation agg = newAggregation(
                match(Criteria.where("name").is("Nikos")),
                // group("status").sum("age").as("total"),
                 //project("total").and("hosting").previousOperation(),
                sort(Sort.Direction.ASC, "status")
        );

        //Convert the aggregation result into a List
        AggregationResults<Object> groupResults
                = mongoTemplate.aggregate(agg, Customer.class, Object.class);
        List<Object> result = groupResults.getMappedResults();

        return result;
    }
}









