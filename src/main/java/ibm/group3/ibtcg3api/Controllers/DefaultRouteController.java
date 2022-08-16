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
@RequestMapping("/")
public class DefaultRouteController {


    @GetMapping("/")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
            public final Object message = "To see the code https://github.com/RosembergAraujo/ibtcg3.api";
        });
    }

}
