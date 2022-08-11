package ibm.group3.ibtcg3api.Controllers;

import ibm.group3.ibtcg3api.Models.OrderModel;
import ibm.group3.ibtcg3api.Repositories.OrderRepository;
import ibm.group3.ibtcg3api.ViewModel.OrderCreateViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository _orderRepository;

    public OrderController(OrderRepository _orderRepository) {
        this._orderRepository = _orderRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = _orderRepository.findAll();
        });
    }

    @PostMapping("/findById")
    public ResponseEntity<Object> findById(@RequestBody Map<String, Integer> req) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = _orderRepository.findById(req.get("id"));
        });
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestBody Map<String, Integer> req) {

        Optional<OrderModel> order = _orderRepository.findById(req.get("id"));

        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Order not found!";
            });
        } else {
            _orderRepository.deleteById(req.get("id"));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
                public final Object message = "Order with id \"" + req.get("id") + "\" deleted!";
            });
        }
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<Object> createStudent(@RequestBody OrderCreateViewModel order) {

        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(order, orderModel);

        orderModel.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            //            public final Object message = _orderRepository.save(orderModel);
            public final Object message = "OK";
        });
    }
//
//    @PutMapping("/updateCustomer")
//    public ResponseEntity<Object> updateCustomer(@RequestBody Map<String, Object> req) {
//
//        System.out.println(req);
//        Optional<OrderModel> orderModel = _orderRepository.findById((Integer) req.get("id"));
//
//        if (orderModel.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
//                public final Object message = "Customer not found!";
//            });
//        }else {
//            OrderModel order = new OrderModel();
//            BeanUtils.copyProperties(orderModel.get(), order);
//
//            if (req.get("name") != null) order.setName((String) req.get("name"));
//            if (req.get("email") != null) order.setEmail((String) req.get("email"));
//            if (req.get("cpf") != null) order.setCpf((String) req.get("cpf"));
//
//            _orderRepository.save(order);
//
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
//                public final Object message = order;
//            });
//        }
//    }

}
