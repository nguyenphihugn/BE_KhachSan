package doancoso.example.hotel.dto;


import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String phone;

    private String roleName;

}
