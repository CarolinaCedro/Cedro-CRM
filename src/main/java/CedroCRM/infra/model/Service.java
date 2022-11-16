package CedroCRM.infra.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @Column
    private BigDecimal value;
}
