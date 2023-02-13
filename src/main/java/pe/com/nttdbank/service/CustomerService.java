package pe.com.nttdbank.service;

import java.util.List;

import pe.com.nttdbank.dto.CustomerDto;

public interface CustomerService {
    List<CustomerDto> getAll();

    CustomerDto getById(Long id);

    Boolean Create(CustomerDto customerDto);

    Boolean Edit(Long id, CustomerDto customerDto);

    Boolean Delete(Long id);

    Long CountByDocument(int documentType, String documentNumber);
}
