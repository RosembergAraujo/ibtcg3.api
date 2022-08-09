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
    private Integer productsId;

    private String name;
    private double price;

    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn (name = "salesId")
    private List<SalesModel> sales;


}
