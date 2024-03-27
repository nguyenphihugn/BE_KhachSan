package doancoso.example.hotel.repo;

import doancoso.example.hotel.entity.Hotel;
import doancoso.example.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel,Long> {

    @Query("SELECT r FROM Hotel r WHERE r.province.id = ?1")
    Hotel[] getHotelByProvinceId(Long id);
    @Query("SELECT r FROM Hotel r WHERE r.name = ?1")
    Hotel getHotelIdByName(String hotelName);

    @Query("SELECT r FROM Hotel r WHERE r.name = ?1 AND r.province.id = ?2")
    Hotel getHotelByNameAndProvince(String hotelName, Long id);


    @Query("""
            SELECT r FROM Hotel r
            WHERE r.name LIKE %?1%        
            OR r.province.name LIKE %?1%
            OR r.address LIKE %?1%
            OR r.phone LIKE %?1%
            OR r.email LIKE %?1%
            """)
    List<Hotel> searchHotel(String keyword);
}
