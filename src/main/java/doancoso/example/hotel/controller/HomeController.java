package doancoso.example.hotel.controller;

import doancoso.example.hotel.service.HomeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@SecurityRequirement(name = "Authorization")
@RequestMapping("api/v1/home")
public class HomeController {
    @Autowired
    private HomeService homeService;



    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getHotelByProvinceId(@PathVariable Long id){
        return new ResponseEntity<>(homeService.getHotelByProvinceId(id), HttpStatus.OK);
    }

    @GetMapping(path = "/Room/{id}")
    public ResponseEntity<?> getRoomByHotelId(@PathVariable Long id){
        return new ResponseEntity<>(homeService.getRoomByHotelId(id), HttpStatus.OK);
    }

    @GetMapping(path = "/Hotel/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id){
        return new ResponseEntity<>(homeService.getHotelById(id), HttpStatus.OK);
    }
}
