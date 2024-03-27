package doancoso.example.hotel.response;

import doancoso.example.hotel.entity.CartItem;
import lombok.Data;

import java.util.Collection;

@Data
public class ViewCartReponse {
    Collection<CartItem> viewCart;
    double tongTien = 0;

    public ViewCartReponse(Collection<CartItem> viewCart, double tongTien) {
        this.viewCart = viewCart;
        this.tongTien = tongTien;
    }
}
