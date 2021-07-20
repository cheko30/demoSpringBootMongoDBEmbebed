package mx.com.santander.customer.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.santander.customer.service.model.CustomerEntity;
import mx.com.santander.customer.service.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Api(tags = "customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @ApiOperation(value = "Get all customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomerEntity.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping()
    public ResponseEntity<List<CustomerEntity>> getAllCustomer() {
        List<CustomerEntity> customerList = customerService.findAll();
        if(customerList.isEmpty()) {
            return new ResponseEntity(customerList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(customerList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get customer by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomerEntity.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable Integer id) {
        CustomerEntity customer = customerService.findById(id);
        if(customer.getId() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @ApiOperation(value = "Save a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomerEntity.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping()
    public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customer){
        CustomerEntity customerResult = customerService.insert(customer);
        if(customerResult.getId() == 0){
            return new ResponseEntity(customerResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(customerResult, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomerEntity.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping()
    public ResponseEntity<CustomerEntity> updateCustomer(@RequestBody CustomerEntity customer) {
        CustomerEntity customerUpdated = customerService.update(customer);
        if(customerUpdated.getId() == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customerUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) {
        Long deleted = customerService.delete(id);
        if(deleted == 0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
