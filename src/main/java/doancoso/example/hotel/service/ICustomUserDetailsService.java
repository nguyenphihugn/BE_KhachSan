package doancoso.example.hotel.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ICustomUserDetailsService {
    UserDetails loadUserById(Long id) throws Exception;

}
