package CedroCRM.infra.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 150)
    private String name;
    @Column(nullable = false,length = 11)
    private String cpf;
    @Column
    private LocalDate registrationTime;
}
