package ibm.group3.ibtcg3api.Repositories;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
//    public Optional<CustomerModel> findByName(String name);
}
