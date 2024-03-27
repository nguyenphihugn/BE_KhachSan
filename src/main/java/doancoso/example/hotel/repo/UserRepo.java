package doancoso.example.hotel.repo;


import doancoso.example.hotel.entity.Room;
import doancoso.example.hotel.entity.User;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findOneByEmailAndPassword(String email, String password);


    @Query("SELECT u FROM User u WHERE u.email =?1")
    User findByEmail(String email);


    @Query("""
            SELECT r FROM User r
            WHERE r.userName LIKE %?1%        
            OR r.role.name LIKE %?1%
            OR r.email LIKE %?1%
            OR r.phone LIKE %?1%
            """)
    List<User> searchUser(String keyword);
//    @Query("SELECT u.id FROM User u WHERE u.userName = ?1")
//    Long getUserIdByUserName(String userName);
////    @Modifying
////    @Transactional
////    @Query(value="INSERT INTO user_role (user_id,role_id) "+ "VALUES (?1,?2)",nativeQuery = true)
////    void addRoleToUser(Long userId,Long roleId);
//
//    @Query(value = "SELECT r.name FROM role r INNER JOIN user_role ur "+
//            "ON r.id =ur.role_id WHERE ur.user_id =?1",nativeQuery = true)
//    String[] getRolesOfUser(Long userId);
}
