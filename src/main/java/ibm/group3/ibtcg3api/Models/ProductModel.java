package ibm.group3.ibtcg3api.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;
    private boolean isGeneric;
    private double price;
    private Integer amountInStock;

    private LocalDateTime createdAt;

//    @OneToMany
//    @JoinColumn (name = "product_id")
//    private List<SalesModel> sales;


}
