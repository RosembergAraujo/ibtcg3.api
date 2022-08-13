package ibm.group3.ibtcg3api.Controllers;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Repositories.CustomerRepository;
import ibm.group3.ibtcg3api.ViewModel.CustomerCreateViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository _customerRepository;

    public CustomerController(CustomerRepository _customerRepository) {
        this._customerRepository = _customerRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
            public final Object customers = _customerRepository.findAll();
        });
    }

    @PostMapping("/findById")
    public ResponseEntity<Object> findById(@RequestBody Map<String, Integer> req) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object customer = _customerRepository.findById(req.get("id"));
        });
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestBody Map<String, Integer> req) {

        Optional<CustomerModel> customer = _customerRepository.findById(req.get("id"));

        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Customer not found!";
            });
        } else {
            _customerRepository.deleteById(req.get("id"));


            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
                public final Object message = "Customer Deleted!";
            });
        }
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerCreateViewModel customer) {

        CustomerModel customerModel = new CustomerModel();
        BeanUtils.copyProperties(customer, customerModel);

        customerModel.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object customer = _customerRepository.save(customerModel);
        });
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Object> updateCustomer(@RequestBody Map<String, Object> req) {

        System.out.println(req);
        Optional<CustomerModel> customerModel = _customerRepository.findById((Integer) req.get("id"));

        if (customerModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Customer not found!";
            });
        } else {
            CustomerModel customer = new CustomerModel();
            BeanUtils.copyProperties(customerModel.get(), customer);

            if (req.get("name") != null) customer.setName((String) req.get("name"));
            if (req.get("email") != null) customer.setEmail((String) req.get("email"));
            if (req.get("cpf") != null) customer.setCpf((String) req.get("cpf"));

            _customerRepository.save(customer);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
                public final Object updatedCustomer = customer;
            });
        }

    }

}
