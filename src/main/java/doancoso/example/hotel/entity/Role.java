package doancoso.example.hotel.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",length =50,nullable = false)
    private String name;


//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> listUser;
}
