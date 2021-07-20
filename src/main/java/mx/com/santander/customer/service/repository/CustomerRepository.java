package mx.com.santander.customer.service.repository;

import mx.com.santander.customer.service.model.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, Integer> {
}
