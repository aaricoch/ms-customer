package pe.com.nttdbank.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import pe.com.nttdbank.dto.CustomerDto;
import pe.com.nttdbank.service.CustomerService;

@Path("/ms-customer/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @GET
    public Response getAll() {
        List<CustomerDto> customers = customerService.getAll();
        return Response.ok(customers).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long Id) {
        CustomerDto customerDto = customerService.getById(Id);
        if (customerDto == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(customerDto).build();
    }

    @POST
    public Response create(CustomerDto customerDto) {
        if (customerService.Create(customerDto)) {
            return Response.ok().status(Status.CREATED).build();
        }
        return Response.status(Status.CONFLICT).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long Id, CustomerDto customerDto) {
        if (customerService.Edit(Id, customerDto)) {
            return Response.noContent().build();
        }
        return Response.ok().status(Status.CONFLICT).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long Id) {
        if (customerService.Delete(Id)) {
            return Response.noContent().build();
        }
        return Response.ok().status(Status.CONFLICT).build();
    }
}
