package doancoso.example.hotel.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String userName;

    @Column(name="password", length = 255)
    private String password;

    @Column(name="email", length = 255)
    private String email;

    @Column(name="phone", length = 45)
    private String phone;


    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Bill> listBill;

}
