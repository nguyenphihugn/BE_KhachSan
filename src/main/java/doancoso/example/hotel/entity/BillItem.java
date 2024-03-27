package doancoso.example.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bill_item")
public class BillItem  {

    @EmbeddedId
    private BillItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("billId")
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private Room room;


    @Column(name = "so_luong")
    private int soLuong;



}
