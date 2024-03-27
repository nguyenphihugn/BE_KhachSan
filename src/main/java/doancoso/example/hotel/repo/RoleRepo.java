package doancoso.example.hotel.repo;

import doancoso.example.hotel.entity.Role;

import doancoso.example.hotel.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role getRoleIdByName(String roleName);

//    @Modifying
//    @Transactional
//    @Query(value="INSERT INTO user_role (user_id,role_id"+
//            "VALUES (?1,?2)", nativeQuery = true)
//    void addRoleToUser(Long userId, Long roleId);
    @Query("SELECT u.id FROM User u WHERE u.userName = ?1")
    Long getUserIdByUsernameLong(String username);
//    @Query(value = "SELECT r.name FROM role r INNER JOIN user_role ur"+
//            "ON r.id = ur.role_id WHERE ur.user_id = ?1", nativeQuery = true)
//    String[] getRolesOfUser(Long userId);
}
