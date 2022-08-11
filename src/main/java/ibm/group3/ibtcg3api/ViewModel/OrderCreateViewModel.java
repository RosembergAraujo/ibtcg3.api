package ibm.group3.ibtcg3api.ViewModel;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Models.SalesModel;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data

public class OrderCreateViewModel {

    private Integer customerId;

    private List<SaleCreateViewModel> sales;

}
