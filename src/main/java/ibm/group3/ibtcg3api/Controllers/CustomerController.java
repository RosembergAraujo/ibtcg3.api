package ibm.group3.ibtcg3api.Controllers;

import ibm.group3.ibtcg3api.Models.CustomerModel;
import ibm.group3.ibtcg3api.Models.OrderModel;
import ibm.group3.ibtcg3api.Repositories.CustomerRepository;
import ibm.group3.ibtcg3api.Repositories.OrderRepository;
import ibm.group3.ibtcg3api.Repositories.SaleRepository;
import ibm.group3.ibtcg3api.ViewModel.CustomerCreateViewModel;
import ibm.group3.ibtcg3api.ViewModel.OrderResultViewInCustomerModel;
import ibm.group3.ibtcg3api.ViewModel.OrderResultViewModel;
import ibm.group3.ibtcg3api.ViewModel.SaleResultInOrderViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final OrderRepository _orderRepository;
    private final CustomerRepository _customerRepository;

    private final SaleRepository _saleRepository;

    public CustomerController(OrderRepository orderRepository,
                              CustomerRepository _customerRepository, SaleRepository saleRepository) {
        _orderRepository = orderRepository;
        this._customerRepository = _customerRepository;
        _saleRepository = saleRepository;
    }

    @PostMapping("/getTransactions")
    public ResponseEntity<Object> getAllTransactions(@RequestBody Map<String, Integer> req) {

        //Não consegui fazer uma query personalizada, confesso
        var allOrders = _orderRepository.findAll();
        var allSales = _saleRepository.findAll();

        var ordersResultArray = new ArrayList<OrderResultViewInCustomerModel>();

        for (var order : allOrders) {
            if(Objects.equals(order.getCustomer().getCustomerId(), req.get("id"))){
                var newOrderResult = new OrderResultViewInCustomerModel();
                newOrderResult.setTotal(order.getTotal());
                newOrderResult.setCustomer(order.getCustomer());
                newOrderResult.setOrderId(order.getOrderId());

                var saleResultArray = new ArrayList<SaleResultInOrderViewModel>();

                for (var sale : allSales) {
                    if(Objects.equals(sale.getOrder().getOrderId(), newOrderResult.getOrderId())){
                        var newSaleResult = new SaleResultInOrderViewModel();
                        newSaleResult.setSaleId(sale.getSaleId());
                        newSaleResult.setAmount(sale.getAmount());
                        newSaleResult.setProduct(sale.getProduct());

                        saleResultArray.add(newSaleResult);
                    }

                }
                newOrderResult.setSales(saleResultArray);
                ordersResultArray.add(newOrderResult);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
            public final Object orders = ordersResultArray;
        });
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
