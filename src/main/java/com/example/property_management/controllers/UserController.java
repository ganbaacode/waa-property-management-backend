package com.example.property_management.controllers;

import com.example.property_management.Services.UserService;
import com.example.property_management.entity.Property;
import com.example.property_management.entity.User;
import com.example.property_management.entity.dto.PropertyListDto;
import com.example.property_management.entity.dto.UserDto;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {


    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/dto")
    public List<UserDto> getAllUsersDto(){
        return userService.getAllUser().stream()
                .map(property -> modelMapper.map(property, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner")
    public List<UserDto> getOwner (){
        return userService.getOwner().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer")
    public List<UserDto> getCustomer (){
        return userService.getCustomer().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/admin")
    public List<UserDto> getAdmin (){
        return userService.getAdmin().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }



    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/dto/{id}")
    public UserDto getBriefUserById(@PathVariable Long id){
        return modelMapper.map(userService.getUserById(id),UserDto.class);
    }


    @GetMapping("/get-user-info")
    public User getByUsername(@RequestParam String email){
        return userService.getUserByUsername(email);
    }

    @PutMapping("/favorite/{prop_id}/{user_id}")
    public List<Property> addFavoriteList(@PathVariable Long prop_id,@PathVariable Long user_id){
        return userService.addPropertyToFavorite(prop_id,user_id);
    }

    @GetMapping("/favorite/{user_id}")
    public List<Property> getFavorite(@PathVariable Long user_id){
        return userService.getAllfavorite(user_id);
    }

    @PutMapping("/activate/{user_id}")
    public User activateUser(@PathVariable Long user_id){
        return userService.activateUser(user_id);
    }

    @PutMapping("/deactivate/{user_id}")
    public User deactivateUser(@PathVariable Long user_id){
        return userService.deactivateUser(user_id);
    }


}
