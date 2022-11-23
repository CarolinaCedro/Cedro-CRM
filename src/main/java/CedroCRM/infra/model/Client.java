package CedroCRM.infra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    @NotNull(message = "{campo.name.obrigatorio}")
    private String name;
    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;
    @Column(name = "registration_time")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationTime;


    public Client(String name, String cpf, LocalDate registrationTime) {
        this.name = name;
        this.cpf = cpf;
        this.registrationTime = LocalDate.now();
    }
}
