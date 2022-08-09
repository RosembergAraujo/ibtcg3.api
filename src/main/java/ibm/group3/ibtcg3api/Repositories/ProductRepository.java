package ibm.group3.ibtcg3api.Repositories;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
//    public Optional<CustomerModel> findByName(String name);
}
