package doancoso.example.hotel.response;

import lombok.Data;

@Data
public class AddRespone {
    String message;
    Boolean status;

    public AddRespone(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}
