package ibm.group3.ibtcg3api.ViewModel;

import lombok.Data;

@Data

public class ProductCreateViewModel {

    private String name;
    private double price;
    private boolean isGeneric;
    private Integer amountInStock;

}
