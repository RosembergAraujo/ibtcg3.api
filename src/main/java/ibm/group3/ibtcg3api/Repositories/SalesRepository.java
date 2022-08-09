package ibm.group3.ibtcg3api.Repositories;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Models.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesRepository extends JpaRepository<SalesModel, Integer> {

//    public Optional<SalesModel> findByCustomerId(CustomerModel customerId);
}
