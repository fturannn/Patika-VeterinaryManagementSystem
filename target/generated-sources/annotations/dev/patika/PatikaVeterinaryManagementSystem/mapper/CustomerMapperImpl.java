package dev.patika.PatikaVeterinaryManagementSystem.mapper;

import dev.patika.PatikaVeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-07T14:09:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer asEntity(CustomerRequest customerRequest) {
        if ( customerRequest == null ) {
            return null;
        }

        Customer customer = new Customer();

        return customer;
    }

    @Override
    public CustomerResponse asOutput(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        return customerResponse;
    }

    @Override
    public List<CustomerResponse> asOutput(List<Customer> customer) {
        if ( customer == null ) {
            return null;
        }

        List<CustomerResponse> list = new ArrayList<CustomerResponse>( customer.size() );
        for ( Customer customer1 : customer ) {
            list.add( asOutput( customer1 ) );
        }

        return list;
    }

    @Override
    public void update(Customer entity, CustomerRequest request) {
        if ( request == null ) {
            return;
        }
    }
}
