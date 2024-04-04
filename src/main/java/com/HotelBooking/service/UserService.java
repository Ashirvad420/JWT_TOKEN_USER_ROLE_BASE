package com.HotelBooking.service;

import com.HotelBooking.dto.PropertyUserDto;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.repository.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private PropertyUserRepository propertyUserRepository;

    public UserService(PropertyUserRepository propertyUserRepository) {
        this.propertyUserRepository = propertyUserRepository;
    }


    public PropertyUser addUser(PropertyUserDto dto)
    {
        PropertyUser user = new PropertyUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setUserRole(dto.getUserRole());
        user.setPassword(new BCrypt().hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        propertyUserRepository.save(user);
        return user;

    }
}
