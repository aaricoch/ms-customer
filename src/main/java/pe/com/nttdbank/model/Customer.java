package pe.com.nttdbank.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue
    private Long idCustomer;
    private String names;
    private String firstLastName;
    private String secondLastName;
    private String businessName;
    private String documentNumber;
    private Date birthdate;
    private String address;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocumentType", foreignKey = @ForeignKey(name = "FK_Customer_DocumentType_idDocumentType"))
    private DocumentType documentType;

    public Customer() {
        super();
    }
}
