package mx.com.santander.customer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import java.util.Date;

@ApiModel(value = "Request", description = "Body request")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customers")
public class CustomerEntity {

    @Transient
    public static final String SEQUENCE_NAME = "customer_sequence";

    @ApiModelProperty(hidden = true)
    @Id
    private int id;

    @ApiModelProperty(value = "Customer Name", example = "Sergio")
    private String firstName;

    @ApiModelProperty(value = "Customer Lastname", example = "Peña")
    private String lastName;

    @ApiModelProperty(value = "Customer date of birth", example = "1990-01-30")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

}
