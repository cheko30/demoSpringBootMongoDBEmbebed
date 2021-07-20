package mx.com.santander.customer.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers_sequence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSequence {

    @Id
    private String id;
    private int seq;
}
