package doancoso.example.hotel.service;

import doancoso.example.hotel.dto.LoginDto;
import doancoso.example.hotel.dto.UserDto;
import doancoso.example.hotel.response.LoginResponse;
import doancoso.example.hotel.response.ResgisterRespone;

public interface UserService {
    ResgisterRespone addUser(UserDto userdto);

//    LoginResponse loginUser(LoginDto loginDto);
}
