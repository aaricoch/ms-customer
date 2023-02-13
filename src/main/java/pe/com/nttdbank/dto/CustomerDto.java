package pe.com.nttdbank.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {
    private Long id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String razonSocial;
    private int tipoDocumento;
    private String numeroDocumento;
    private String direccion;
    private String correo;

    public CustomerDto() {
    }
}
