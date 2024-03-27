package doancoso.example.hotel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngayDat")
    private Date ngayDat;

    @Column(name = "ngayNhan")
    private Date ngayNhan;

    @Column(name = "ngayTra")
    private Date ngayTra;

//    @Column(name = "CMND")
//    private String CMND;

    @Column(name = "userName")
    private String userName;

//    @Column(name = "note")
//    private String note;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "bill", cascade = CascadeType.REMOVE)
    private List<BillItem> items;

}
