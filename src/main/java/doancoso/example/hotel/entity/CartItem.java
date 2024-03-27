package doancoso.example.hotel.entity;

import lombok.Data;

@Data
public class CartItem {
    private Long RoomId;
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity =1;



}
