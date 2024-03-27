package doancoso.example.hotel.response;

import lombok.Data;

@Data
public class ResgisterRespone {
    String message;
    Boolean status;

    public ResgisterRespone(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}
