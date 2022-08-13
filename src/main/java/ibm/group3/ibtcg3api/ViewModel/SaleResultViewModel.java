package ibm.group3.ibtcg3api.ViewModel;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Models.OrderModel;
import ibm.group3.ibtcg3api.Models.ProductModel;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data

public class SaleResultViewModel {

    private Integer saleId;

    private Integer amount;

    private CustomerModel customer;

    private ProductModel product;

    private OrderModel order;

    private LocalDateTime createdAt;

}
