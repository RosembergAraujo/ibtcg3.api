package ibm.group3.ibtcg3api.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String name;
    private String email;
    private String cpf;

    private LocalDateTime createdAt;

//    @OneToMany
//    @JoinColumn (name = "customer_id")
//    private List<SalesModel> sales;

}
