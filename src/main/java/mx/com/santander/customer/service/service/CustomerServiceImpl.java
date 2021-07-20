package mx.com.santander.customer.service.service;

import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import mx.com.santander.customer.service.model.CustomerEntity;
import mx.com.santander.customer.service.model.CustomerSequence;
import mx.com.santander.customer.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Long delete(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult deleteResult = mongoOperations.remove(query,CustomerEntity.class);
        return deleteResult.getDeletedCount();
    }

    @Override
    public CustomerEntity findById(Integer id) {
        return customerRepository.findById(id).orElse(new CustomerEntity());
    }

    @Override
    public CustomerEntity insert(CustomerEntity customer) {
        customer.setId(getSequenceNumber(CustomerEntity.SEQUENCE_NAME));
        return customerRepository.insert(customer);
    }

    @Override
    public CustomerEntity update(CustomerEntity customer) {
        CustomerEntity customerUpdated = new CustomerEntity();
        Query query = new Query(Criteria.where("firstName").is(customer.getFirstName()));
        CustomerEntity customerDB = mongoOperations.findOne(query, CustomerEntity.class);
        if(Objects.nonNull(customerDB)) {
            customerDB.setFirstName(customer.getFirstName());
            customerDB.setLastName(customer.getLastName());
            customerDB.setDateOfBirth(customer.getDateOfBirth());
            return mongoOperations.save(customerDB);
        }

        return customerUpdated;
    }

    /**
     * Get sequence number
     * @param sequenceName
     * @return sequence number
     */
    public int getSequenceNumber(String sequenceName){
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);

        CustomerSequence counter = mongoOperations
                .findAndModify(query,
                        update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                        CustomerSequence.class);

        return !Objects.isNull(counter) ? counter.getSeq(): 1;
    }
}
