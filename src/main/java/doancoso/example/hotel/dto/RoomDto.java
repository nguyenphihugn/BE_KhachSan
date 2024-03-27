package doancoso.example.hotel.dto;


import lombok.Data;

@Data
public class RoomDto {


    private Long id;

    private String name;


    private String description;


    private String img;


    private Double price;


    private int acreage;


    private int numberRoom;

    private String hotelName;
}
