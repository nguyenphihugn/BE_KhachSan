package doancoso.example.hotel.repo;


import doancoso.example.hotel.entity.Hotel;
import doancoso.example.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.name = ?1 AND r.hotel.id = ?2")
    Room getRoomIdByNameAndHotel(String roomlName, Long id);

    @Query("SELECT r FROM Room r WHERE r.hotel.id = ?1")
    Room[] getRoomByHotelId(Long id);

    @Query("SELECT MIN(r.price) FROM Room r WHERE r.hotel.id = ?1")
    double getRoomMinPrice(Long id);

    @Query("""
            SELECT r FROM Room r
            WHERE r.name LIKE %?1%        
            OR r.hotel.name LIKE %?1%
            """)
    List<Room> searchRoom(String keyword);

}
