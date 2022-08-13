package ibm.group3.ibtcg3api.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "sales")
public class SalesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleId;

    private Integer amount;

    @ManyToOne
    @JoinColumn (name = "customerId")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn (name = "productsId")
    private ProductModel product;

    @ManyToOne
    @JoinColumn (name = "orderId")
    private OrderModel order;

    private LocalDateTime createdAt;

}
