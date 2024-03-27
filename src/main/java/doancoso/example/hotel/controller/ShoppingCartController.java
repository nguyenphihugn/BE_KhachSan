package doancoso.example.hotel.controller;


import doancoso.example.hotel.dto.BillDto;
import doancoso.example.hotel.dto.RoomDto;
import doancoso.example.hotel.response.AddRespone;
import doancoso.example.hotel.response.ViewCartReponse;
import doancoso.example.hotel.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "Authorization")
@RequestMapping("api/v1/ShoppingCart")
public class ShoppingCartController {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String MEMBER = "ROLE_MEMBER";
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/view")
    public ResponseEntity<?> viewCart(Model model) {
        ViewCartReponse viewCartReponse = new ViewCartReponse(shoppingCartService.getAllItems(), shoppingCartService.getAmount());
        return new ResponseEntity<>(viewCartReponse, HttpStatus.OK);
    }


    @GetMapping(path = "/addCartItem/{id}")
    public ResponseEntity<?> addCartItem(@PathVariable Long id){
        AddRespone addRespone = shoppingCartService.add(id);
        return ResponseEntity.ok(addRespone);
    }


    @GetMapping(path = "/deleteCartItem/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long id){
        return new ResponseEntity<>(shoppingCartService.delete(id), HttpStatus.OK);
    }


    @GetMapping(path = "/clearCartItem/{id}")
    public ResponseEntity<?> clearCartItem(@PathVariable Long id){
        return new ResponseEntity<>(shoppingCartService.clear(id), HttpStatus.OK);
    }

    @PostMapping(path = "/CreateBill")
    public ResponseEntity<?> CreateBill(@RequestBody BillDto billDto){
        return new ResponseEntity<>(shoppingCartService.createBill(billDto), HttpStatus.OK);
    }


}
