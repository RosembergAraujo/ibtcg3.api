package ibm.group3.ibtcg3api.Controllers;

import ibm.group3.ibtcg3api.Models.OrderModel;
import ibm.group3.ibtcg3api.Models.SalesModel;
import ibm.group3.ibtcg3api.Repositories.CustomerRepository;
import ibm.group3.ibtcg3api.Repositories.OrderRepository;
import ibm.group3.ibtcg3api.Repositories.ProductRepository;
import ibm.group3.ibtcg3api.Repositories.SaleRepository;
import ibm.group3.ibtcg3api.ViewModel.OrderCreateViewModel;
import ibm.group3.ibtcg3api.ViewModel.OrderResultViewModel;
import ibm.group3.ibtcg3api.ViewModel.SaleCreateViewModel;
import ibm.group3.ibtcg3api.ViewModel.SaleResultInOrderViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository _orderRepository;
    private final CustomerRepository _customerRepository;
    private final ProductRepository _productRepository;
    private final SaleRepository _saleRepository;

    public OrderController(
            OrderRepository _orderRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            SaleRepository saleRepository) {

        this._orderRepository = _orderRepository;
        this._customerRepository = customerRepository;
        this._productRepository = productRepository;
        this._saleRepository = saleRepository;
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
            public final Object orders = _orderRepository.findById(req.get("id"));
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
                public final Object message = "Order with id (" + req.get("id") + ") deleted!";
            });
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Object> createOrder(@RequestBody OrderCreateViewModel orderViewModel) {

        var orderModel = new OrderModel();
        orderModel.setCreatedAt(LocalDateTime.now());

        var customerRes = _customerRepository.findById(orderViewModel.getCustomerId());

        if (customerRes.isPresent()) {
            orderModel.setCustomer(customerRes.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Customer with this Id not found";
            });
        }

        var order = _orderRepository.save(orderModel);
        List<SaleCreateViewModel> salesViewModel = orderViewModel.getSales();
        List<SalesModel> sales = new ArrayList<SalesModel>();


        for (var saleView : salesViewModel) {
            var saleModelTemp = new SalesModel();

            saleModelTemp.setAmount(saleView.getAmount());
            saleModelTemp.setAmount(saleView.getAmount());
            saleModelTemp.setCreatedAt(order.getCreatedAt());
            saleModelTemp.setCustomer(order.getCustomer());
            saleModelTemp.setOrder(order);

            var product = _productRepository.findById(saleView.getProductId());
            if (product.isPresent()) { //Stock Control

                if(product.get().getAmountInStock() <= 0)
                {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                        public final Object message = "Product ("+product.get().getName()+") sold out";
                    });

                }else if (product.get().getAmountInStock() - saleModelTemp.getAmount() < 0){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                        public final Object message = "Product ("+product.get().getName()+") have only ("+product.get().getAmountInStock()+") left in stock" ;
                    });

                }else { //All right
                    product.get().setAmountInStock(product.get().getAmountInStock() - saleModelTemp.getAmount());
                    _productRepository.save(product.get());
                    saleModelTemp.setProduct(product.get());
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                    public final Object message = "Product with id: (" + saleView.getProductId() + ") not found";
                });
            }


            sales.add(saleModelTemp);
        }

        var salesFinal = _saleRepository.saveAll(sales);

        var salesResult = new ArrayList<SaleResultInOrderViewModel>();
        var orderResult = new OrderResultViewModel();
        orderResult.setTotal(0);
        for (var sale: salesFinal) {
            var salesResultTmp = new SaleResultInOrderViewModel();
            BeanUtils.copyProperties(sale, salesResultTmp);

            var price = sale.getProduct().getPrice() * sale.getAmount();
            if(sale.getProduct().isGeneric()) price *= 0.8; //Take off 20%
            orderResult.setTotal(orderResult.getTotal() + price);

            salesResult.add(salesResultTmp);
        }

        orderResult.setSales(salesResult);
        orderResult.setCustomer(order.getCustomer());
        order.setTotal(orderResult.getTotal());
        _orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
            public final Object order = orderResult;
        });
    }

}
