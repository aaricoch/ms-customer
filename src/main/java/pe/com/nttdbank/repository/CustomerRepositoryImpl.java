package pe.com.nttdbank.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pe.com.nttdbank.common.StatusType;
import pe.com.nttdbank.model.Customer;

@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository {

    public List<Customer> getAll() {
        return Customer.list("state", StatusType.Active.value);
    }

    public Customer getById(Long id) {
        Optional<Customer> customer = Customer.findByIdOptional(id);
        if (!customer.isPresent()) {
            return null;
        }
        return customer.get();
    }

    @Transactional
    public Boolean Create(Customer customer) {
        Boolean result = false;

        customer.State = StatusType.Active.value;
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

        Customer customerEdit = getById(customer.getIdCustomer());

        customerEdit = merge(customerEdit, customer);
        customerEdit.AuditUpdateUser = 1;
        customerEdit.AuditUpdateDate = new Date();
        customerEdit.persist();

        if (customerEdit.isPersistent()) {
            result = true;
        }

        return result;
    }

    @Transactional
    public Boolean Delete(Long id) {
        Boolean result = false;
        Customer customer = getById(id);

        customer.State = StatusType.Inactive.value;
        customer.AuditDeleteUser = 1;
        customer.AuditDeleteDate = new Date();
        customer.persist();

        if (customer.isPersistent()) {
            result = true;
        }

        return result;
    }

    public Long CountByDocument(int documentType, String documentNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("iddocumenttype", documentType);
        params.put("documentnumber", documentNumber);
        params.put("state", StatusType.Active.value);

        String query = "iddocumenttype = :iddocumenttype and documentnumber = :documentnumber and state = :state";

        return Customer.count(query, params);
    }

    private Customer merge(Customer customer, Customer customerEdit){
        customer.setNames(customerEdit.getNames());
        customer.setFirstLastName(customerEdit.getFirstLastName());
        customer.setSecondLastName(customerEdit.getSecondLastName());
        customer.setBusinessName(customerEdit.getBusinessName());
        customer.setDocumentType(customerEdit.getDocumentType());
        customer.setDocumentNumber(customerEdit.getDocumentNumber());
        customer.setEmail(customerEdit.getEmail());

        return customer;
    }

}
