package doancoso.example.hotel.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class BillDto {

    private Date ngayDat;


    private Date ngayNhan;


    private Date ngayTra;



//    private String userName;
//
//    private String phone;

    private Long userId;
}
