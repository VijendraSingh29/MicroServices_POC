package com.POC.UserService.Services.Impl;

import com.POC.UserService.DTO.HotelDto;
import com.POC.UserService.DTO.RatingDto;
import com.POC.UserService.DTO.UserResponse;
import com.POC.UserService.Entities.User;
import com.POC.UserService.Exceptions.ResourceNotFoundException;
import com.POC.UserService.Repositories.UserRepository;
import com.POC.UserService.Services.UserService;
import com.POC.UserService.feignClient.feignRatingService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class userServicesImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingDto ratingDto;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private feignRatingService ratingService;

    @Override
    public UserResponse saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User save = userRepository.save(user);
        UserResponse userDTO = getMappingForUserResponse(user);
        return userDTO;

    }

    private UserResponse getMappingForUserResponse(User user) {
           ModelMapper modelMapper = new ModelMapper();
        UserResponse userDTO = modelMapper.map(user, UserResponse.class);
        return userDTO ;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userRepository.findAll();
        //   ("/getAll")  localhost:8083/ratings/getAll

        List<User> collect = all.stream().map(users ->
        {
            RatingDto[] ratingDataForUser = getAllRatingDataForUser(users.getUserId());
            //localhost:8082/hotels/getHotelById/c40148c8-6d58-40ab-8346-d327a69bab19
            //hotel rating fetch for each user

            for (RatingDto ratingDto : ratingDataForUser) {
                ratingDto.setHotelDto(getHotelRatingForUser(ratingDto.getHotelId()));
            }
            users.setRating(Arrays.asList(ratingDataForUser));
            return users;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not present in database for this given Id : " + userId));

        // using restTemplate exchange method
        RatingDto[] ratingDataForUser = getAllRatingDataForUser(userId);

        //localhost:8082/hotels/getHotelById/c40148c8-6d58-40ab-8346-d327a69bab19
        //hotel rating fetch

        for (RatingDto ratingDto : ratingDataForUser) {
            ratingDto.setHotelDto(getHotelRatingForUser(ratingDto.getHotelId()));
        }
        user.setRating(Arrays.asList(ratingDataForUser));
        return user;
    }

    @Override
    public FilterProvider getResponseFilter(String[] addFilters) {
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept(addFilters);
        FilterProvider filter = new SimpleFilterProvider()
                .addFilter("myFilter", theFilter);
        return filter;
    }

    RatingDto[] getAllRatingDataForUser(String userId) {
        RatingDto[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, RatingDto[].class);
        return ratings;
    }

    HotelDto getHotelRatingForUser(String hotelId) {
        HotelDto hotelDto = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/getHotelById/" + hotelId, HotelDto.class);
        return hotelDto;
    }


}
