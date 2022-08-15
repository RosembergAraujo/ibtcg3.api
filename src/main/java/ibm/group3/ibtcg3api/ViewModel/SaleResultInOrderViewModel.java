package ibm.group3.ibtcg3api.ViewModel;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Models.OrderModel;
import ibm.group3.ibtcg3api.Models.ProductModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class SaleResultInOrderViewModel {

    private Integer saleId;

    private Integer amount;

    private ProductModel product;

}
