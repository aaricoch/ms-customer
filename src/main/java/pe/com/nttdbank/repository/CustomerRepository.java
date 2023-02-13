package pe.com.nttdbank.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pe.com.nttdbank.model.Customer;

@ApplicationScoped
public class CustomerRepository implements ICustomerRepository {

    public List<Customer> getAll() {
        List<Customer> customers = Customer.listAll();
        // List<Customer> customers = Customer.list("status", StatusType.Active);
        return customers;
    }

    public Customer getById(Long id) {
        Optional<Customer> customer = Customer.findByIdOptional(id);
        if (customer.isPresent()){
            return null;
        }
        return customer.get();
    }

    @Transactional
    public Boolean Create(Customer customer) {
        Boolean result = false;
        
        customer.State = 1;
        customer.AuditCreateUser = 1;
        customer.AuditCreateDate = new Date();

        customer.persist();
        if (customer.isPersistent()) {
            result = true;
        }

        return result;
    }

    @Transactional
    public Boolean Edit(Customer customer) {
        Boolean result = false;

        customer.AuditUpdateUser = 1;
        customer.AuditUpdateDate = new Date();
        customer.persist();

        if (customer.isPersistent()) {
            result = true;
        }

        return result;
    }

    @Transactional
    public Boolean Delete(Long id) {
        Boolean result = false;
        Customer customer = getById(id);

        customer.State = 0;
        customer.AuditDeleteUser = 1;
        customer.AuditDeleteDate = new Date();
        customer.persist();

        if (customer.isPersistent()) {
            result = true;
        }

        return result;
    }

}
