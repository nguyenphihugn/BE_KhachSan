package doancoso.example.hotel.service.impl;

import doancoso.example.hotel.dto.HotelDto;
import doancoso.example.hotel.dto.RoomDto;
import doancoso.example.hotel.entity.Hotel;
import doancoso.example.hotel.entity.Province;
import doancoso.example.hotel.entity.Room;
import doancoso.example.hotel.repo.HotelRepo;
import doancoso.example.hotel.repo.ProvinceRepo;
import doancoso.example.hotel.repo.RoomRepo;
import doancoso.example.hotel.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private ProvinceRepo provinceRepo;
    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public List<HotelDto> getHotelByProvinceId(Long id) {

        try {

            Hotel[] hotels = hotelRepo.getHotelByProvinceId(id);
            List<HotelDto> hotelDtos = new ArrayList<HotelDto>();
            for (Hotel hotel : hotels) {

                if(roomRepo.getRoomByHotelId(hotel.getId()).length !=0){
                    double minPrice = roomRepo.getRoomMinPrice(hotel.getId());

                    HotelDto hotelDto = new HotelDto();
                    hotelDto.setId(hotel.getId());
                    hotelDto.setName(hotel.getName());
                    hotelDto.setAddress(hotel.getAddress());
                    hotelDto.setDescription(hotel.getDescription());
                    hotelDto.setDescription1(hotel.getDescription1());
                    hotelDto.setDescription2(hotel.getDescription2());
                    hotelDto.setEmail(hotel.getEmail());
                    hotelDto.setPhone(hotel.getPhone());
                    hotelDto.setImg(hotel.getImg());
                    hotelDto.setMinPrice(minPrice);
                    Province province = hotel.getProvince();
                    hotelDto.setProvinceName(province.getName());
                    hotelDtos.add(hotelDto);
                }else{
                    HotelDto hotelDto = new HotelDto();
                    hotelDto.setId(hotel.getId());
                    hotelDto.setName(hotel.getName());
                    hotelDto.setAddress(hotel.getAddress());
                    hotelDto.setDescription(hotel.getDescription());
                    hotelDto.setDescription1(hotel.getDescription1());
                    hotelDto.setDescription2(hotel.getDescription2());
                    hotelDto.setEmail(hotel.getEmail());
                    hotelDto.setPhone(hotel.getPhone());
                    hotelDto.setImg(hotel.getImg());
                    hotelDto.setMinPrice(0);
                    Province province = hotel.getProvince();
                    hotelDto.setProvinceName(province.getName());
                    hotelDtos.add(hotelDto);
                }



            }
            return hotelDtos;
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public List<RoomDto> getRoomByHotelId(Long id) {

        try {
            Room[] rooms = roomRepo.getRoomByHotelId(id);
            List<RoomDto> roomDtos = new ArrayList<RoomDto>();
            for (Room room : rooms) {
                RoomDto roomDto = new RoomDto();
                roomDto.setId(room.getId());
                roomDto.setName(room.getName());
                roomDto.setDescription(room.getDescription());
                roomDto.setImg(room.getImg());
                roomDto.setPrice(room.getPrice());
                roomDto.setNumberRoom(room.getNumberRoom());
                roomDto.setAcreage(room.getAcreage());
                Hotel hotel = room.getHotel();
                roomDto.setHotelName(hotel.getName());
                roomDtos.add(roomDto);
            }
            return roomDtos;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public HotelDto getHotelById(Long id) {

        try {

            Hotel hotel = hotelRepo.findById(id).get();
            HotelDto hotelDto = new HotelDto();

            hotelDto.setId(hotel.getId());
            hotelDto.setName(hotel.getName());
            hotelDto.setAddress(hotel.getAddress());
            hotelDto.setDescription(hotel.getDescription());
            hotelDto.setDescription1(hotel.getDescription1());
            hotelDto.setDescription2(hotel.getDescription2());
            hotelDto.setEmail(hotel.getEmail());
            hotelDto.setPhone(hotel.getPhone());
            hotelDto.setImg(hotel.getImg());
            hotelDto.setMinPrice(0);
            Province province = hotel.getProvince();
            hotelDto.setProvinceName(province.getName());

            return hotelDto;
        } catch (Exception ex) {
            return null;
        }

    }
}
