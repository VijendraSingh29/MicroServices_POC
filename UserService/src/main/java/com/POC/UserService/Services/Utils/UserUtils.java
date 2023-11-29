package com.POC.UserService.Services.Utils;

import com.POC.UserService.DTO.UserResponse;
import com.POC.UserService.DTO.UserResponseDTO;
import com.POC.UserService.Entities.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserUtils {
    public UserResponse getMappingForUserResponse(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserResponse userDTO = modelMapper.map(user, UserResponse.class);
        return userDTO ;
    }
    public UserResponseDTO getMappingForUserResponseDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserResponseDTO userDTO = modelMapper.map(user, UserResponseDTO.class);
        return userDTO ;
    }
   public   List<UserResponseDTO> getMappingForAllUserResponse(List<User> user) {
        ModelMapper modelMapper = new ModelMapper();
        List<UserResponseDTO> userResponseList = Arrays.asList(modelMapper.map(user, UserResponseDTO[].class));
        return userResponseList ;
    }
    public MappingJacksonValue getResponseFilter(String[] addFilters, UserResponse userResponse) {
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept(addFilters);
        FilterProvider filter = new SimpleFilterProvider()
                .addFilter("myFilter", theFilter);
        MappingJacksonValue value = new MappingJacksonValue(userResponse);
        value.setFilters(filter);
        return value;
    }
}
