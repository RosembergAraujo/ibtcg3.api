package ibm.group3.ibtcg3api.Controllers;

import ibm.group3.ibtcg3api.Models.SalesModel;
import ibm.group3.ibtcg3api.Repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SalesController {

    /*
    private final SalesRepository _salesRepository;

    public SalesController(SalesRepository _salesRepository) {
        this._salesRepository = _salesRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = _salesRepository.findAll();
        });
    }

    @PostMapping("/findById")
    public ResponseEntity<Object> findById(@RequestBody Map<String, Integer> req) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = _salesRepository.findById(req.get("id"));
        });
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestBody Map<String, Integer> req) {

        Optional<SalesModel> sale = _salesRepository.findById(req.get("id"));

        if (sale.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Customer not found!";
            });
        } else {
            _salesRepository.deleteById(req.get("id"));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = "User deleted!";
            });
        }
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<Object> createStudent(@RequestBody CustomerCreateViewModel sale) {

        SalesModel saleModel = new SalesModel();
        BeanUtils.copyProperties(sale, saleModel);

        saleModel.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = _salesRepository.save(saleModel);
        });
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Object> updateCustomer(@RequestBody Map<String, Object> req) {

        System.out.println(req);
        Optional<SalesModel> saleModel = _salesRepository.findById((Integer) req.get("id"));

        if (saleModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Customer not found!";
            });
        }else {
            SalesModel sale = new SalesModel();
            BeanUtils.copyProperties(saleModel.get(), sale);

            if (req.get("name") != null) sale.setName((String) req.get("name"));
            if (req.get("email") != null) sale.setEmail((String) req.get("email"));
            if (req.get("cpf") != null) sale.setCpf((String) req.get("cpf"));

            _salesRepository.save(sale);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
                public final Object message = sale;
            });
        }

    }

    */



}
