package ibm.group3.ibtcg3api.Controllers;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Repositories.CustomerRepository;
import ibm.group3.ibtcg3api.ViewModel.CustomerCreateViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/customers")
public class ProductController {
    @Autowired
    private CustomerRepository _customerRepository;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object response = _customerRepository.findAll();
        });
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<Object> createStudent(@RequestBody CustomerCreateViewModel customer) {

        CustomerModel customerModel = new CustomerModel();
        BeanUtils.copyProperties(customer, customerModel);

        customerModel.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object response = _customerRepository.save(customerModel);
        });
    }



//    @PostMapping("/findByName")
//    public ResponseEntity<Object> findByName(@RequestBody Map<String, String> req) {
//
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
//            public final Object response = studentRepo.findByName(req.get("name"));;
//        });
//    }

}
