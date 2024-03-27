package doancoso.example.hotel.service.impl;

import doancoso.example.hotel.dto.BillDto;
import doancoso.example.hotel.entity.*;
import doancoso.example.hotel.repo.BillItemRepo;
import doancoso.example.hotel.repo.BillRepo;
import doancoso.example.hotel.repo.RoomRepo;
import doancoso.example.hotel.repo.UserRepo;
import doancoso.example.hotel.response.AddRespone;
import doancoso.example.hotel.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BillRepo billRepo;

    @Autowired
    private BillItemRepo billItemRepo;

    Map<Long, CartItem> shoppingCart = new HashMap<>();
    private double so_Dem = 2;

    @Override
    public AddRespone add(Long id) {
        Room product = roomRepo.findById(id).get();

        if (product != null) {

            if (product.getNumberRoom() != 0) {
//                so_Dem = sodem;
                CartItem item = new CartItem();
                item.setRoomId(id);
                item.setId(id);
                item.setName(product.getName());
                item.setDescription(product.getDescription());
                item.setPrice(product.getPrice());
                item.setQuantity(1);

                CartItem cartItem = shoppingCart.get(item.getRoomId());
                if (cartItem == null) {

                    shoppingCart.put(item.getRoomId(), item);


                    product.setNumberRoom(product.getNumberRoom() - 1);
                    roomRepo.save(product);
                    return new AddRespone("Add room success", true);
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    product.setNumberRoom(product.getNumberRoom() - 1);
                    roomRepo.save(product);
                    return new AddRespone("Update room success", true);
                }
            } else {
                return new AddRespone("room is out", false);
            }
        } else {
            return new AddRespone("Room not exits", false);
        }
    }

    @Override
    public String delete(Long id) {
        CartItem cartItem = shoppingCart.get(id);
        Room product = roomRepo.findById(id).get();
        product.setNumberRoom(product.getNumberRoom()+1);
        roomRepo.save(product);
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            return "Update success";
        } else {
            shoppingCart.remove(id);
            return "remove success";
        }

    }

    @Override
    public String clear(Long sodem) {
        if(shoppingCart != null) {
            shoppingCart.forEach((key, cartItem) -> {
                Room product = roomRepo.findById(key).get();
                product.setNumberRoom(product.getNumberRoom() + cartItem.getQuantity());
                roomRepo.save(product);
            });


            shoppingCart.clear();

        }
        so_Dem = sodem;
        return "clear success";

    }


    @Override
    public Collection<CartItem> getAllItems() {
        return shoppingCart.values();
    }

    @Override
    public double getAmount() {
        if(shoppingCart.size() == 0){
            return 0;
        }
        double tam = shoppingCart.values().stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();

        double tong = tam * so_Dem;
        return tong;
    }

    @Override
    @Transactional
    public String createBill(BillDto billDto) {
        User user = userRepo.findById(billDto.getUserId()).get();

        // Tạo hóa đơn mới
        Bill bill = new Bill();
        bill.setNgayDat(billDto.getNgayDat());
        bill.setNgayNhan(billDto.getNgayNhan());
        bill.setNgayTra(billDto.getNgayTra());
        bill.setUserName(user.getUserName());
        bill.setPhone(user.getPhone());
        bill.setUser(user);
        billRepo.save(bill);
        if(shoppingCart!=null) {
            // Lặp qua danh sách sản phẩm đã chọn và lưu vào BillItem
            shoppingCart.forEach((key, cartItem) -> {
                Room room = roomRepo.findById(key).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm với id " + key));
                ;
                BillItem billItem = new BillItem();
                billItem.setId(new BillItemId(bill.getId(), room.getId()));
                billItem.setBill(bill);
                billItem.setRoom(room);
                billItem.setSoLuong(cartItem.getQuantity());
                billItemRepo.save(billItem);
            });
        }
        shoppingCart.clear();
        return "success";

    }
}
