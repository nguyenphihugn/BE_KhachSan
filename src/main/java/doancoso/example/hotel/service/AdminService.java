package doancoso.example.hotel.service;

import doancoso.example.hotel.dto.HotelDto;
import doancoso.example.hotel.dto.ProvinceDto;
import doancoso.example.hotel.dto.RoomDto;
import doancoso.example.hotel.dto.UserDto;
import doancoso.example.hotel.entity.Hotel;
import doancoso.example.hotel.entity.Province;
import doancoso.example.hotel.entity.Room;
import doancoso.example.hotel.response.AddRespone;

import java.util.List;

public interface AdminService {
    public AddRespone saveRoom(RoomDto roomDto);
    public AddRespone saveprovince(ProvinceDto provinceDto);
    public AddRespone saveHotel(HotelDto hotelDto);
   
    public List<RoomDto> getAllRoom();

    public List<ProvinceDto> getAllProvince();
    public List<HotelDto> getAllHotel();
    public RoomDto getRoomById(Long id);

    public String deleteRoom(Long id);

    

    public String deleteHotel(Long id);

    public String deleteProvince(Long id);

    public HotelDto getHotelById(Long id);

    public ProvinceDto getProvinceById(Long id);
    public AddRespone editRoom(RoomDto roomDto);
    public AddRespone editHotel(HotelDto hotelDto);

    public AddRespone editProvince(ProvinceDto provinceDto);


    public List<UserDto> getAllUser();

    AddRespone editRoleUser(UserDto userDto);


    public List<String> getAllRole();
    public List<RoomDto> searchRoom(String search);
    public List<HotelDto> searchHotel(String search);

    public List<UserDto> searchUser(String search);


//    Object getAllUser();
}
