package doancoso.example.hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BillItemId implements Serializable {
    @Column(name = "bill_id")
    private Long billId;

    @Column(name = "room_id")
    private Long roomId;
    public BillItemId() {}
    public BillItemId(Long billId, Long roomId) {
        this.billId = billId;
        this.roomId = roomId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
