package doancoso.example.hotel.service;

import doancoso.example.hotel.dto.HotelDto;
import doancoso.example.hotel.dto.RoomDto;

import java.util.List;

public interface HomeService {
    public List<HotelDto> getHotelByProvinceId(Long id);
    public List<RoomDto> getRoomByHotelId(Long id);

    public HotelDto getHotelById(Long id);
}
