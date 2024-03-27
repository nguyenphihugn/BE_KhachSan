package doancoso.example.hotel.repo;

import doancoso.example.hotel.entity.Hotel;
import doancoso.example.hotel.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepo extends JpaRepository<Province, Long > {

    @Query("SELECT r FROM Province r WHERE r.name = ?1")
    Province getProvinceByName(String provinceName);
}
