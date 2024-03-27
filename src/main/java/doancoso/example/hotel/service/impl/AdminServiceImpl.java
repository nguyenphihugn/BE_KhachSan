package doancoso.example.hotel.service.impl;

import doancoso.example.hotel.dto.HotelDto;
import doancoso.example.hotel.dto.ProvinceDto;
import doancoso.example.hotel.dto.RoomDto;
import doancoso.example.hotel.dto.UserDto;
import doancoso.example.hotel.entity.*;

import doancoso.example.hotel.repo.*;
import doancoso.example.hotel.response.AddRespone;

import doancoso.example.hotel.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ProvinceRepo provinceRepo;
    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public AddRespone saveRoom(RoomDto roomDto) {


        Hotel hotel = hotelRepo.getHotelIdByName(roomDto.getHotelName());
        Room roomtest = roomRepo.getRoomIdByNameAndHotel(roomDto.getName(), hotel.getId());

        if (roomtest == null) {
            Room room = new Room();
            room.setName(roomDto.getName());
            room.setDescription(roomDto.getDescription());
            room.setImg(roomDto.getImg());
            room.setPrice(roomDto.getPrice());
            room.setNumberRoom(roomDto.getNumberRoom());
            room.setAcreage(roomDto.getAcreage());
            room.setHotel(hotel);
            roomRepo.save(room);

            return new AddRespone("Add success", true);
        } else {
            return new AddRespone("Room exits", false);
        }
    }

    @Override
    public AddRespone saveHotel(HotelDto hotelDto) {


        Province province = provinceRepo.getProvinceByName(hotelDto.getProvinceName());
        Hotel hoteltest = hotelRepo.getHotelByNameAndProvince(hotelDto.getName(), province.getId());

        if (hoteltest == null) {

            Hotel hotel = new Hotel();
            hotel.setName(hotelDto.getName());
            hotel.setAddress(hotelDto.getAddress());
            hotel.setDescription(hotelDto.getDescription());
            hotel.setDescription1(hotelDto.getDescription1());
            hotel.setDescription2(hotelDto.getDescription2());
            hotel.setEmail(hotelDto.getEmail());
            hotel.setPhone(hotelDto.getPhone());
            hotel.setImg(hotelDto.getImg());
            hotel.setProvince(province);
            hotelRepo.save(hotel);

            return new AddRespone("Add success", true);
        } else {
            return new AddRespone("Hotel exits", false);
        }
    }

    @Override
    public AddRespone saveprovince(ProvinceDto provinceDto) {
        Province provinceTest = provinceRepo.getProvinceByName(provinceDto.getName());
//        Hotel hoteltest = hotelRepo.getHotelByNameAndProvince(hotelDto.getName(), province.getId());

        if (provinceTest == null) {
            Province province = new Province();
            province.setName(provinceDto.getName());
            province.setImg(provinceDto.getImg());
            provinceRepo.save(province);
            return new AddRespone("Add success", true);
        } else {
            return new AddRespone("Province exits", false);
        }
    }


    @Override
    public AddRespone editRoom(RoomDto roomDto) {
        Hotel hotel = hotelRepo.getHotelIdByName(roomDto.getHotelName());
        Room roomtest = roomRepo.getRoomIdByNameAndHotel(roomDto.getName(), hotel.getId());

        if (roomtest == null || roomtest.getId() == roomDto.getId()) {

            Room room = roomRepo.findById(roomDto.getId()).get();
            if (room != null) {
                room.setName(roomDto.getName());
                room.setDescription(roomDto.getDescription());
                room.setImg(roomDto.getImg());
                room.setPrice(roomDto.getPrice());
                room.setNumberRoom(roomDto.getNumberRoom());
                room.setAcreage(roomDto.getAcreage());
                room.setHotel(hotel);
                roomRepo.save(room);

                return new AddRespone("Add success", true);
            } else {
                return new AddRespone("Room not exits", false);
            }
        } else {
            return new AddRespone("Room exits", false);
        }

    }

    @Override
    public AddRespone editHotel(HotelDto hotelDto) {

        Province province = provinceRepo.getProvinceByName(hotelDto.getProvinceName());
        Hotel hoteltest = hotelRepo.getHotelByNameAndProvince(hotelDto.getName(), province.getId());

        if (hoteltest == null || hoteltest.getId() == hotelDto.getId()) {

            Hotel hotel = hotelRepo.findById(hotelDto.getId()).get();
            if (hotel != null) {


                hotel.setName(hotelDto.getName());
                hotel.setAddress(hotelDto.getAddress());
                hotel.setDescription(hotelDto.getDescription());
                hotel.setDescription1(hotelDto.getDescription1());
                hotel.setDescription2(hotelDto.getDescription2());
                hotel.setEmail(hotelDto.getEmail());
                hotel.setPhone(hotelDto.getPhone());
                hotel.setImg(hotelDto.getImg());
                hotel.setProvince(province);


                hotelRepo.save(hotel);

                return new AddRespone("Add success", true);
            } else {
                return new AddRespone("Hotel not exits", false);
            }
        } else {
            return new AddRespone("Hotel exits", false);
        }
    }

    @Override
    public AddRespone editProvince(ProvinceDto provinceDto) {

        Province provinceTest = provinceRepo.getProvinceByName(provinceDto.getName());
        if (provinceTest == null || provinceTest.getId() == provinceDto.getId()) {

            Province province = provinceRepo.findById(provinceDto.getId()).get();
            if (province != null) {


                province.setName(provinceDto.getName());
                province.setImg(provinceDto.getImg());
                provinceRepo.save(province);

                return new AddRespone("Add success", true);
            } else {
                return new AddRespone("Province not exits", false);
            }
        } else {
            return new AddRespone("Province exits", false);
        }
    }

    @Override
    public AddRespone editRoleUser(UserDto userDto) {


        User userTest = userRepo.findById(userDto.getId()).get();
        if (userTest != null) {
            if (!userTest.getRole().getName().equals(userDto.getRoleName())) {

                Role role = roleRepo.getRoleIdByName(userDto.getRoleName());

                userTest.setRole(role);
                userRepo.save(userTest);
                return new AddRespone("Update role success", true);
            } else {
                return new AddRespone("Role exits", false);
            }
        }else {
            return new AddRespone("User not exits", false);
        }
    }

    @Override
    public List<String> getAllRole() {
        List<Role> roles = roleRepo.findAll();
        List<String> roleNames = new ArrayList<String>();

        for (Role role : roles) {
            roleNames.add(role.getName());
        }

        return roleNames;
    }


    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>();

        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserName(user.getUserName());
            userDto.setEmail(user.getEmail());
            userDto.setPhone(user.getPhone());
            userDto.setRoleName(user.getRole().getName());
            userDtos.add(userDto);
        }
        return userDtos;
    }




    @Override
    public List<RoomDto> getAllRoom() {
        List<Room> rooms = roomRepo.findAll();
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
    }

    @Override
    public List<RoomDto> searchRoom(String search) {
        List<RoomDto> roomDtos = new ArrayList<RoomDto>();
        if (search == null) {
            List<Room> rooms = roomRepo.findAll();
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
        } else {
            List<Room> rooms = roomRepo.searchRoom(search);
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
        }
    }

    @Override
    public List<HotelDto> searchHotel(String search) {
        List<HotelDto> hotelDtos = new ArrayList<HotelDto>();
        if (search == null) {
            List<Hotel> hotels = hotelRepo.findAll();
            for (Hotel hotel : hotels) {
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
                hotelDto.setProvinceName(hotel.getProvince().getName());
                hotelDtos.add(hotelDto);
            }
            return hotelDtos;
        } else {
            List<Hotel> hotels = hotelRepo.searchHotel(search);
            for (Hotel hotel : hotels) {
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
                hotelDto.setProvinceName(hotel.getProvince().getName());
                hotelDtos.add(hotelDto);
            }
            return hotelDtos;

        }
    }

    @Override
    public List<UserDto> searchUser(String search) {

        List<UserDto> userDtos = new ArrayList<UserDto>();
        if (search == null) {
            List<User> users = userRepo.findAll();
            for (User user : users) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUserName(user.getUserName());
                userDto.setEmail(user.getEmail());
                userDto.setPhone(user.getPhone());
                userDto.setRoleName(user.getRole().getName());
                userDtos.add(userDto);
            }
            return userDtos;
        } else {
            List<User> users = userRepo.searchUser(search);
            for (User user : users) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUserName(user.getUserName());
                userDto.setEmail(user.getEmail());
                userDto.setPhone(user.getPhone());
                userDto.setRoleName(user.getRole().getName());
                userDtos.add(userDto);
            }
            return userDtos;

        }
    }


    @Override
    public List<ProvinceDto> getAllProvince() {
        List<Province> provinces = provinceRepo.findAll();
        List<ProvinceDto> provinceDtos = new ArrayList<ProvinceDto>();
        for (Province province : provinces) {
            ProvinceDto provinceDto = new ProvinceDto();
            provinceDto.setId(province.getId());
            provinceDto.setName(province.getName());
            provinceDto.setImg(province.getImg());

            provinceDtos.add(provinceDto);
        }
        return provinceDtos;
    }


    @Override
    public List<HotelDto> getAllHotel() {
        List<Hotel> hotels = hotelRepo.findAll();
        List<HotelDto> hotelDtos = new ArrayList<HotelDto>();
        for (Hotel hotel : hotels) {
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

            hotelDto.setProvinceName(hotel.getProvince().getName());

            hotelDtos.add(hotelDto);
        }
        return hotelDtos;
    }

    @Override
    public RoomDto getRoomById(Long id) {
        Room room = roomRepo.findById(id).get();
        RoomDto roomDto = new RoomDto();
        if (room != null) {
            roomDto.setId(room.getId());
            roomDto.setName(room.getName());
            roomDto.setDescription(room.getDescription());
            roomDto.setImg(room.getImg());
            roomDto.setPrice(room.getPrice());
            roomDto.setNumberRoom(room.getNumberRoom());
            roomDto.setAcreage(room.getAcreage());
            Hotel hotel = room.getHotel();
            roomDto.setHotelName(hotel.getName());
            return roomDto;
        }
        return roomDto;
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Hotel hotel = hotelRepo.findById(id).get();
        HotelDto hotelDto = new HotelDto();
        if (hotel != null) {
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
            hotelDto.setProvinceName(hotel.getProvince().getName());

            return hotelDto;
        }
        return hotelDto;
    }

    @Override
    public ProvinceDto getProvinceById(Long id) {
        Province province = provinceRepo.findById(id).get();
        ProvinceDto provinceDto = new ProvinceDto();
        if (province != null) {
            provinceDto.setId(province.getId());
            provinceDto.setName(province.getName());
            provinceDto.setImg(province.getImg());
            return provinceDto;
        }
        return provinceDto;
    }


    @Override
    public String deleteRoom(Long id) {
        Room room = roomRepo.findById(id).get();
        if (room != null) {
            roomRepo.delete(room);
            return "Room delete success";
        }
        return "Room delete success";
    }

    @Override
    public String deleteHotel(Long id) {
        Hotel hotel = hotelRepo.findById(id).get();

        if (hotel != null) {
            hotelRepo.delete(hotel);
            return "Hotel delete success";
        }
        return "Hotel delete success";
    }

    @Override
    public String deleteProvince(Long id) {
        Province province = provinceRepo.findById(id).get();

        if (province != null) {
            provinceRepo.delete(province);
            return "Province delete success";
        }
        return "Province delete success";
    }


}
