package ibm.group3.ibtcg3api.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerModel customer;

    private double total;

//    @OneToMany
//    @JoinColumn (name = "order_id")
//    private List<SalesModel> sales;
//teste
    private LocalDateTime createdAt;


}
