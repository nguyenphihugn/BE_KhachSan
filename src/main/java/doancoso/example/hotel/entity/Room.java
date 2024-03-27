package doancoso.example.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "room")
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",length =255,nullable = false)
    private String name;

    @Column(name = "description",length =255,nullable = false)
    private String description;

    @Column(name = "img",length =255,nullable = false)
    private String img;

    @Column(name = "price")
    private Double price;

    @Column(name = "acreage")
    private int acreage;

    @Column(name = "numberRoom")
    private int numberRoom;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<BillItem> items;;
}
