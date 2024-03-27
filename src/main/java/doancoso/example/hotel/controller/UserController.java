package doancoso.example.hotel.controller;


import doancoso.example.hotel.dto.LoginDto;
import doancoso.example.hotel.dto.UserDto;
import doancoso.example.hotel.entity.CustomUserDetails;

import doancoso.example.hotel.jwt.JwtTokenProvider;
import doancoso.example.hotel.response.LoginResponse;
import doancoso.example.hotel.response.ResgisterRespone;
import doancoso.example.hotel.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@SecurityRequirement(name = "Authorization")
@RequestMapping("api/v1/user")
public class UserController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;



    @PostMapping(path = "/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userdto){

//        String id = userService.addUser(userdto);
        ResgisterRespone resgisterRespone = userService.addUser(userdto);
        return ResponseEntity.ok(resgisterRespone);

    }


    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
//        LoginResponse loginResponse = userService.loginUser(loginDto);
//        return ResponseEntity.ok(loginResponse);

        String email = loginDto.getEmail();
        String pass = loginDto.getPassword();

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing email");
        }

        if (pass == null || pass.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing password");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(user);

//            UserDto userDTO = UserMapper.INSTANCE.toDTO(user.getUser());
            UserDto userDTO = new UserDto();
            userDTO.setId(user.getUser().getId());
            userDTO.setUserName(user.getUser().getUserName());
            userDTO.setEmail(user.getUser().getEmail());
            userDTO.setRoleName(user.getUser().getRole().getName());
            userDTO.setPhone(user.getUser().getPhone());

            return ResponseEntity.ok(new LoginResponse(userDTO, token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }



}
