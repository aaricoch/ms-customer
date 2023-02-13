package pe.com.nttdbank.repository;

import java.util.List;

import pe.com.nttdbank.model.Customer;

public interface ICustomerRepository {
    List<Customer> getAll();

    Customer getById(Long id);

    Boolean Create(Customer customer);

    Boolean Edit(Customer customer);

    Boolean Delete(Long id);
}
