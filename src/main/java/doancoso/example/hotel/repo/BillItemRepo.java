package doancoso.example.hotel.repo;

import doancoso.example.hotel.entity.BillItem;
import doancoso.example.hotel.entity.BillItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillItemRepo extends JpaRepository<BillItem, BillItemId> {
}
