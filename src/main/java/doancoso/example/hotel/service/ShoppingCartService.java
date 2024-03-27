package doancoso.example.hotel.service;

import doancoso.example.hotel.dto.BillDto;
import doancoso.example.hotel.entity.CartItem;
import doancoso.example.hotel.response.AddRespone;

import java.util.Collection;

public interface ShoppingCartService {
    public AddRespone add(Long id);

    public String delete(Long id);
    public String clear(Long sodem);

    Collection<CartItem> getAllItems();
    double getAmount();

    public String createBill(BillDto billDto);
}
