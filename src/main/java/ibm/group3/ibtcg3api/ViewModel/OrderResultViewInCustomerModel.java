package ibm.group3.ibtcg3api.ViewModel;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import lombok.Data;

import java.util.List;

@Data

public class OrderResultViewInCustomerModel {

    private Integer orderId;

    private CustomerModel customer;

    private double total;

    private List<SaleResultInOrderViewModel> sales;

}
