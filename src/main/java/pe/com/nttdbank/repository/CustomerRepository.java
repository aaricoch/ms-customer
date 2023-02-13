package pe.com.nttdbank.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pe.com.nttdbank.model.Customer;

public interface CustomerRepository extends PanacheRepository<Customer> {
    List<Customer> getAll();

    Customer getById(Long id);

    Boolean Create(Customer customer);

    Boolean Edit(Customer customer);

    Boolean Delete(Long id);

    Long CountByDocument(int documentType, String documentNumber);
}
