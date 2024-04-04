package com.HotelBooking.service;

import com.HotelBooking.dto.LoginDto;
import com.HotelBooking.dto.PropertyUserDto;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.repository.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PropertyUserRepository propertyUserRepository;

    public UserService(PropertyUserRepository propertyUserRepository) {
        this.propertyUserRepository = propertyUserRepository;
    }

        // this is for SignUp
    public PropertyUser addUser(PropertyUserDto dto) {
        PropertyUser user = new PropertyUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setUserRole(dto.getUserRole());
        user.setPassword(new BCrypt().hashpw(dto.getPassword(), BCrypt.gensalt(10)));
        propertyUserRepository.save(user);
        return user;

    }




    // this is for SignIn
    public boolean verifyLogin(LoginDto loginDto) {

        Optional<PropertyUser> opUser = propertyUserRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent())
        {
            PropertyUser user = opUser.get();
            return BCrypt.checkpw(loginDto.getPassword(), user.getPassword());
        }
        return false;
    }
}
//Option user (opUser) :-If we are now returning nothing at least it will not give any null pointer exception
// opUser:- password is encrypted there
// checkpw (check password)- return back boolean, it means return true or false