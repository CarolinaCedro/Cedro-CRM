package CedroCRM.infra.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;
    @NotEmpty(message = "{campo.name.obrigatorio}")
    private String name;
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF
    private String cpf;
    private LocalDate registrationTime;

}
