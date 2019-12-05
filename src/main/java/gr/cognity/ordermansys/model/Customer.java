/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.cognity.ordermansys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author nmpellias
 */
@Document(collection = "customers")
public class Customer {

    @Id
    private String _id;
    private String user_id;
    private String name;
    private int age;
    private String status;

    public Customer() {
    }

    public Customer(String user_id, String name, int age, String status) {
        this.user_id = user_id;
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" + "_id=" + _id + ", user_id=" + user_id + ", name=" + name + ", age=" + age + ", status=" + status + '}';
    }

}

