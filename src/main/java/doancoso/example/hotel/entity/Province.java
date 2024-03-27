package doancoso.example.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;


@Data
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",length =50,nullable = false)
    private String name;

    @Column(name = "img",length =255,nullable = false)
    private String img;

    @OneToMany (mappedBy = "province", cascade = CascadeType.ALL)
    private List<Hotel> hotels;


}
