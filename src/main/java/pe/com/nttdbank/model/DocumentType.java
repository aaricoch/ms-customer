package pe.com.nttdbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class DocumentType {
    @Id
    @GeneratedValue
    private int idDocumentType;
    private String documentTypeName;

    public DocumentType() {
    }

}
