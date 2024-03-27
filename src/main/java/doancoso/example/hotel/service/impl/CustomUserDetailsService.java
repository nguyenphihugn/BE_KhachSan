package doancoso.example.hotel.service.impl;

import doancoso.example.hotel.entity.CustomUserDetails;
import doancoso.example.hotel.entity.User;
import doancoso.example.hotel.repo.UserRepo;
import doancoso.example.hotel.service.ICustomUserDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements ICustomUserDetailsService, UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (userRepository.findByEmailAndStatus(username, true).isEmpty()) {
//            throw new UsernameNotFoundException(username);
//        }
        User user = userRepository.findByEmail(username);
        Set<GrantedAuthority> authoritySet;
        authoritySet = new HashSet<>();
        String role = user.getRole().getName();
        if(role.equals("ADMIN")){
            authoritySet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            authoritySet.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        }
//        else{
//            authoritySet.add(new SimpleGrantedAuthority("ROLE_" + role));
//        }
//        List<String> listAuth = generateAuthoriessByRoleId(user.getRole().getListRolePermissionScope());
//
//        for (String s: listAuth) {
//            authoritySet.add(new SimpleGrantedAuthority(s));
//        }
        return new CustomUserDetails(user, authoritySet, role);
    }

    @Override
    public UserDetails loadUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception();
        }
        User result = user.get();
        String role = result.getRole().getName();
        Set<GrantedAuthority> authoritySet;
        authoritySet = new HashSet<>();
        if(role.equals("ADMIN")){
            authoritySet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            authoritySet.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        }
        //ROLE_Admin
        // Customer
//        if(role.equals("MEMBER")){
//            authoritySet.add(new SimpleGrantedAuthority("ROLE_" + role));
//        }
        return new CustomUserDetails(result, authoritySet, role);
    }
}
