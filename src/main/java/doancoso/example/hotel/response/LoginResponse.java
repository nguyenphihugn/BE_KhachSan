package doancoso.example.hotel.response;

import doancoso.example.hotel.dto.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UserDto userDTO;

    private String token;

    //    public LoginResponse(String message, Boolean status, String username, String[] roleName) {
//        this.message = message;
//        this.status = status;
//        this.username = username;
//        this.roleName = roleName;
//    }
}
