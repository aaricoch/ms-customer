package pe.com.nttdbank.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pe.com.nttdbank.dto.CustomerDto;
import pe.com.nttdbank.model.Customer;
import pe.com.nttdbank.model.DocumentType;
import pe.com.nttdbank.repository.CustomerRepository;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.getAll();
        List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();

        customers.forEach(customer -> {
            customerDtos.add(toCustomerDto(customer));
        });

        return customerDtos;
    }

    public CustomerDto getById(Long id) {
        Customer customer = customerRepository.getById(id);
        if (customer == null) {
            return null;
        }
        return toCustomerDto(customer);
    }

    public Boolean Create(CustomerDto customerDto) {
        Customer customer = toCustomer(customerDto);

        if (CountByDocument(customer.getDocumentType().getIdDocumentType(), customer.getDocumentNumber()) > 0) {
            // throw new NullPointerException("Already registered customer");
            return false;
        }

        return customerRepository.Create(customer);
    }

    public Boolean Edit(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.getById(id);
        if (customer == null) {
            // throw new NullPointerException("Customer not fount");
            return false;
        }

        customer = toCustomer(customerDto);
        customer.setIdCustomer(id);

        return customerRepository.Edit(customer);
    }

    public Boolean Delete(Long id) {
        Customer customer = customerRepository.getById(id);
        if (customer == null) {
            // throw new NullPointerException("Customer not fount");
            return false;
        }

        return customerRepository.Delete(id);
    }

    public Long CountByDocument(int documentType, String documentNumber) {
        return customerRepository.CountByDocument(documentType, documentNumber);
    }

    private CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getIdCustomer());
        customerDto.setNombres(customer.getNames());
        customerDto.setApellidoPaterno(customer.getFirstLastName());
        customerDto.setApellidoMaterno(customer.getSecondLastName());
        customerDto.setRazonSocial(customer.getBusinessName());
        customerDto.setTipoDocumento(customer.getDocumentType().getIdDocumentType());
        customerDto.setNumeroDocumento(customer.getDocumentNumber());
        customerDto.setCorreo(customer.getEmail());

        return customerDto;
    }

    private Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setIdCustomer(customerDto.getId());
        customer.setNames(customerDto.getNombres());
        customer.setFirstLastName(customerDto.getApellidoPaterno());
        customer.setSecondLastName(customerDto.getApellidoMaterno());
        customer.setBusinessName(customerDto.getRazonSocial());
        DocumentType documentType = new DocumentType();
        documentType.setIdDocumentType(customerDto.getTipoDocumento());
        customer.setDocumentType(documentType);
        customer.setDocumentNumber(customerDto.getNumeroDocumento());
        customer.setEmail(customerDto.getCorreo());

        return customer;
    }

}
