package ibm.group3.ibtcg3api.ViewModel;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Models.SalesModel;
import lombok.Data;

import java.util.List;

@Data

public class OrderResultViewModel {

    private CustomerModel customer;

    private double total;

    private List<SaleResultInOrderViewModel> sales;

}
