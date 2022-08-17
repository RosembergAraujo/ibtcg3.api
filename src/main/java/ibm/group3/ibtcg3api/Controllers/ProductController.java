package ibm.group3.ibtcg3api.Controllers;

import ibm.group3.ibtcg3api.Models.ProductModel;
import ibm.group3.ibtcg3api.Repositories.ProductRepository;
import ibm.group3.ibtcg3api.ViewModel.ProductCreateViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository _productRepository;

    public ProductController(ProductRepository _productRepository) {
        this._productRepository = _productRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object products = _productRepository.findAll();
        });
    }

    @PostMapping("/findById")
    public ResponseEntity<Object> findById(@RequestBody Map<String, Integer> req) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = _productRepository.findById(req.get("id"));
        });
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestBody Map<String, Integer> req) {

        Optional<ProductModel> product = _productRepository.findById(req.get("id"));

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Product not found!";
            });

        } else {
            _productRepository.deleteById(req.get("id"));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
                public final Object message = "Product deleted!";

            });
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(@RequestBody Map<String, Object> req) {


        ProductModel productModel = new ProductModel();

        try {
            productModel.setName((String) req.get("name"));
            productModel.setGeneric((Boolean) req.get("isGeneric"));
            productModel.setPrice((Double) req.get("price"));
            productModel.setAmountInStock((Integer) req.get("amountInStock"));
            productModel.setCreatedAt(LocalDateTime.now());
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                public final Object message = "Some field has wrong type! (Ex: \"price\": 5 is wrong, correct type is \"price\": 5.0)";
            });
        }



        return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
            public final Object message = _productRepository.save(productModel);
        });
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Object> updateProduct(@RequestBody Map<String, Object> req) {

        Optional<ProductModel> productModel = _productRepository.findById((Integer) req.get("id"));

        if (productModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Product not found!";
            });
        } else {
            ProductModel product = new ProductModel();
            BeanUtils.copyProperties(productModel.get(), product);

            if (req.get("name") != null) product.setName((String) req.get("name"));
            if (req.get("isGeneric") != null) product.setGeneric((boolean) req.get("isGeneric"));
            if (req.get("price") != null) product.setPrice((double) req.get("price"));
            if (req.get("amountInStock") != null) product.setPrice((double) req.get("amountInStock"));

            _productRepository.save(product);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
                public final Object message = product;
            });
        }

    }


}
