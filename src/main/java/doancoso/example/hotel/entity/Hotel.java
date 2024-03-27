package doancoso.example.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",length =255,nullable = false)
    private String name;

    @Column(name = "address",length =255,nullable = false)
    private String address;

    @Column(name = "description",length =2000,nullable = false)
    private String description;


    @Column(name = "description1",length =2000)
    private String description1;

    @Column(name = "description2",length =2000)
    private String description2;

    @Column(name = "email",length =255,nullable = false)
    private String email;


    @Column(name = "phone",length =45,nullable = false)
    private String phone;

    @Column(name = "img",length =255,nullable = false)
    private String img;


    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;


    @OneToMany (mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
