package mx.com.santander.customer.service.service;

import mx.com.santander.customer.service.model.CustomerEntity;

import java.util.List;

public interface CustomerService {

    CustomerEntity insert(CustomerEntity customer);
    CustomerEntity update(CustomerEntity customer);
    CustomerEntity findById(Integer id);
    List<CustomerEntity> findAll();
    Long delete(Integer id);




}
