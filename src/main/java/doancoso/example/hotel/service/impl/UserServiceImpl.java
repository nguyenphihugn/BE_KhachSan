package doancoso.example.hotel.service.impl;

import doancoso.example.hotel.dto.LoginDto;
import doancoso.example.hotel.dto.UserDto;
import doancoso.example.hotel.entity.Role;
import doancoso.example.hotel.entity.User;
import doancoso.example.hotel.repo.RoleRepo;
import doancoso.example.hotel.repo.UserRepo;
import doancoso.example.hotel.response.LoginResponse;
import doancoso.example.hotel.response.ResgisterRespone;
import doancoso.example.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;


    @Autowired()
    private PasswordEncoder passwordEncoder;
    @Override
    public ResgisterRespone addUser(UserDto userdto) {

        User user1 = userRepo.findByEmail(userdto.getEmail());
        Role role = roleRepo.getRoleIdByName("MEMBER");
        if(user1== null) {
            User user = new User(
//                    userdto.getId(),
//                    userdto.getUserName(),
//                    this.passwordEncoder.encode(userdto.getPassword()),
//                    userdto.getEmail(),
//                    userdto.getPhone(),
//                    role
            );
            user.setUserName(userdto.getUserName());
            user.setEmail(userdto.getEmail());
            user.setPassword(this.passwordEncoder.encode(userdto.getPassword()));
            user.setPhone( userdto.getPhone());
            user.setRole(role);
            userRepo.save(user);
//            Long userId = userRepo.getUserIdByUserName(user.getUserName());

//            if (roleId != 0 && userId != 0) {
//                userRepo.addRoleToUser(userId, roleId);
//            }
            return new ResgisterRespone("resgister Success", true);
        }else {
            return new ResgisterRespone("Email exits", false);
        }
    }

//    @Override
//    public LoginResponse loginUser(LoginDto loginDto) {
//        String msg ="";
//        User user1 = userRepo.findByEmail(loginDto.getEmail());
//
//
//        if(user1!=null){
//            String[] role = userRepo.getRolesOfUser(user1.getId());
//            int t = 0;
//            for (String roleName:role) {
//                if(roleName.equals("ADMIN")){
//                    t=1;
//                }
//            }
//            String password = loginDto.getPassword();
//            String encodePassword = user1.getPassword();
//            Boolean isPwdRight = passwordEncoder.matches(password, encodePassword);
//            if(isPwdRight){
//                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDto.getEmail(), encodePassword);
//
//                if (user.isPresent()){
//                    if(t==1){
//                        return new LoginResponse("Login Success", true, user1.getUserName(),user1.getId() ,"ADMIN");
//                    }
//                    else {
////                        return new LoginResponse("Login Success", true, user1.getUserName(), role);
//                        return new LoginResponse("Login Success", true, user1.getUserName(), user1.getId(),"USER");
//                    }
//
//                }
//                else {
//                    return new LoginResponse("Login Failed", false, "", null,null);
//                }
//            }
//            else {
//                return new LoginResponse("password Not Match", false,"", null,null);
//            }
//        }
//        else {
//            return new LoginResponse("Email not exits", false,"",null ,null);
//        }
//
//
//    }


















}
