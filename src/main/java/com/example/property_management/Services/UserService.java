package com.example.property_management.Services;


import com.example.property_management.entity.Property;
import com.example.property_management.entity.Role;
import com.example.property_management.entity.User;
import com.example.property_management.entity.dto.UserDto;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PropertyRepository propertyRepository;

    public User getUserById(long id){
        return userRepository.findById(id).get();
    }


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String email){
        return userRepository.findUserByEmail(email);
    }

    public List<Property> addPropertyToFavorite(Long prop_id,Long user_id){
        Property myProp = propertyRepository.findById(prop_id).get();
        User myUser = userRepository.findById(user_id).get();
        myUser.getMylist().add(myProp);
        userRepository.save(myUser);
        return myUser.getMylist();
    }

    public List<Property> getAllfavorite(Long user_id){
        return userRepository.findById(user_id).get().getMylist();
    }

    public List<User> getOwner(){
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().getName().startsWith("O"))
                .collect(Collectors.toList());

    }

    public List<User> getCustomer(){
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().getName().startsWith("C"))
                .collect(Collectors.toList());

    }

    public List<User> getAdmin(){
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().getName().startsWith("A"))
                .collect(Collectors.toList());

    }

    public User activateUser(long user_id){
        User myUser = userRepository.findById(user_id).get();
        myUser.setIsactive(true);
        userRepository.save(myUser);
        return myUser;

    }

    public User deactivateUser(long user_id){
        User myUser = userRepository.findById(user_id).get();
        myUser.setIsactive(false);
        userRepository.save(myUser);
        return myUser;

    }



}
