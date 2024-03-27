package doancoso.example.hotel.controller;


import doancoso.example.hotel.dto.HotelDto;
import doancoso.example.hotel.dto.ProvinceDto;
import doancoso.example.hotel.dto.RoomDto;

import doancoso.example.hotel.dto.UserDto;
import doancoso.example.hotel.response.AddRespone;

import doancoso.example.hotel.service.AdminService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@SecurityRequirement(name = "Authorization")
@RequestMapping("api/v1/admin")
public class AdminController {
    public static final String ADMIN = "ROLE_ADMIN";
    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/addRoom")
    @Secured({ADMIN})
    public ResponseEntity<?> addRoom(@RequestBody RoomDto roomDto ){
        AddRespone addRespone = adminService.saveRoom(roomDto);
        return ResponseEntity.ok(addRespone);
    }
    @PostMapping(path = "/addHotel")
    @Secured({ADMIN})
    public ResponseEntity<?> addHotel(@RequestBody HotelDto hotelDto ){
        AddRespone addRespone = adminService.saveHotel(hotelDto);
        return ResponseEntity.ok(addRespone);
    }

    @PostMapping(path = "/addProvince")
    @Secured({ADMIN})
    public ResponseEntity<?> addProvince(@RequestBody ProvinceDto provinceDto ){
        AddRespone addRespone = adminService.saveprovince(provinceDto);
        return ResponseEntity.ok(addRespone);
    }


    @GetMapping("/Room")
    public ResponseEntity<?> getAllRoom(){
        return new ResponseEntity<>(adminService.getAllRoom(), HttpStatus.OK);
    }

    @GetMapping("/Province")
    public ResponseEntity<?> Province(){
        return new ResponseEntity<>(adminService.getAllProvince(), HttpStatus.OK);
    }

    @GetMapping("/Hotel")
    public ResponseEntity<?> getAllHotel(){
        return new ResponseEntity<>(adminService.getAllHotel(), HttpStatus.OK);
    }


    @GetMapping("/User")
    @Secured({ADMIN})
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(adminService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/Role")
    @Secured({ADMIN})
    public ResponseEntity<?> getAllRole(){
        return new ResponseEntity<>(adminService.getAllRole(), HttpStatus.OK);
    }


    @PostMapping(path = "/editRoleUser")
    @Secured({ADMIN})
    public ResponseEntity<?> editRoleUser(@RequestBody UserDto userDto){
        AddRespone addRespone = adminService.editRoleUser(userDto);
        return ResponseEntity.ok(addRespone);
    }



    @GetMapping(path = "/RoomEdit/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id){
        return new ResponseEntity<>(adminService.getRoomById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/HotelEdit/{id}")
    public ResponseEntity<?> HotelEdit(@PathVariable Long id){
        return new ResponseEntity<>(adminService.getHotelById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/ProvinceEdit/{id}")
    public ResponseEntity<?> ProvinceEdit(@PathVariable Long id){
        return new ResponseEntity<>(adminService.getProvinceById(id), HttpStatus.OK);
    }






    @GetMapping(path = "/deleteRoom/{id}")
    @Secured({ADMIN})
    public ResponseEntity<?> deleteRoom(@PathVariable Long id){
        return new ResponseEntity<>(adminService.deleteRoom(id), HttpStatus.OK);
    }

    @GetMapping(path = "/deleteHotel/{id}")
    @Secured({ADMIN})
    public ResponseEntity<?> deleteHotel(@PathVariable Long id){
        return new ResponseEntity<>(adminService.deleteHotel(id), HttpStatus.OK);
    }

    @GetMapping(path = "/deleteProvince/{id}")
    @Secured({ADMIN})
    public ResponseEntity<?> deleteProvince(@PathVariable Long id){
        return new ResponseEntity<>(adminService.deleteProvince(id), HttpStatus.OK);
    }



    @PostMapping(path = "/editRoom")
    @Secured({ADMIN})
    public ResponseEntity<?> editRoom(@RequestBody RoomDto roomDto){
        AddRespone addRespone = adminService.editRoom(roomDto);
        return ResponseEntity.ok(addRespone);
    }

    @PostMapping(path = "/editHotel")
    @Secured({ADMIN})
    public ResponseEntity<?> editHotel(@RequestBody HotelDto hotelDto){
        AddRespone addRespone = adminService.editHotel(hotelDto);
        return ResponseEntity.ok(addRespone);
    }

    @PostMapping(path = "/editProvince")
    @Secured({ADMIN})
    public ResponseEntity<?> editProvince(@RequestBody ProvinceDto provinceDto){
        AddRespone addRespone = adminService.editProvince(provinceDto);
        return ResponseEntity.ok(addRespone);
    }




    @GetMapping("/Room/Search")
    public ResponseEntity<?> getAllProducts(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(adminService.searchRoom(name), HttpStatus.OK);
    }

    @GetMapping("/Hotel/Search")
    public ResponseEntity<?> getAllHotel(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(adminService.searchHotel(name), HttpStatus.OK);
    }

    @GetMapping("/User/Search")
    public ResponseEntity<?> getAllUser(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(adminService.searchUser(name), HttpStatus.OK);
    }
}
