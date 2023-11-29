package com.POC.UserService.Services.Impl;

import com.POC.UserService.DTO.*;
import com.POC.UserService.Entities.User;
import com.POC.UserService.Exceptions.ResourceNotFoundException;
import com.POC.UserService.Repositories.UserRepository;
import com.POC.UserService.Services.UserService;
import com.POC.UserService.Services.Utils.UserUtils;
import com.POC.UserService.feignClient.feignRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
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

    @Autowired
    private UserUtils userUtils;

    @Override
    @Transactional
    public UserResponse saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User userRepo = userRepository.save(user);
        UserResponse userDTO = userUtils.getMappingForUserResponse(userRepo);
        return userDTO;

    }
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> all = userRepository.findAll();
        //   ("/getAll")  localhost:8083/ratings/getAll
        List<UserResponseDTO> userResponseList = userUtils.getMappingForAllUserResponse(all);

        List<UserResponseDTO> collect = userResponseList.stream().map(users ->
        {
            RatingDto[] ratingDataForUser = getAllRatingDataForUser(users.getUserId());
            //localhost:8082/hotels/getHotelById/c40148c8-6d58-40ab-8346-d327a69bab19
            //hotel rating fetch for each user

           /* for (RatingDto ratingDto : ratingDataForUser) {
                ratingDto.setHotelDto(getHotelRatingForUser(ratingDto.getHotelId()));
            }
            users.setRating(Arrays.asList(ratingDataForUser));*/
            List<HotelDto> hotelDto = getHotelDto(ratingDataForUser, users.getUserId());
            users.setRating(hotelDto);
            return users;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public UserResponseDTO getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not present in database for this given Id : " + userId));

        UserResponseDTO userResponse = userUtils.getMappingForUserResponseDTO(user);

        // using restTemplate exchange method
        RatingDto[] ratingDataForUser = getAllRatingDataForUser(userId);

        // get Hotel details for this user from rating

        //localhost:8082/hotels/getHotelById/c40148c8-6d58-40ab-8346-d327a69bab19
        //hotel rating fetch

        /*for (RatingDto ratingDto : ratingDataForUser) {
            ratingDto.setHotelDto(getHotelRatingForUser(ratingDto.getHotelId()));
        }*/
      //  userResponse.setRating(Arrays.asList(ratingDataForUser));
        userResponse.setRating(getHotelDto(ratingDataForUser,userId));
        return userResponse;
    }

    private List<HotelDto> getHotelDto(RatingDto[] ratingDataForUser,String userId ) {
        Set<String> hotelList = Arrays.asList(ratingDataForUser).stream().map(RatingDto::getHotelId).collect(Collectors.toSet());
        List<HotelDto> hotelDtos = new ArrayList<>() ;

        for (String hotelId:hotelList)
        {
            List<String> ratingList = Arrays.asList(ratingDataForUser).stream().filter(ratingDto1 -> ratingDto1.getHotelId().equals(hotelId) && ratingDto1.getUserId().equals(userId)).map(RatingDto::getRatingId).collect(Collectors.toList());
            HotelDto hotelRatingForUser = getHotelDetailByHotelId(hotelId);
            List<RatingDetailsDto> ratingDtoList = new ArrayList<>();
            for (String ratingId :ratingList) {
                RatingDetailsDto ratingByRatingId = getRatingByRatingId(ratingId);
                ratingDtoList.add(ratingByRatingId);
            }
            hotelRatingForUser.setRatingDtoList(ratingDtoList);
            hotelDtos.add(hotelRatingForUser);
        }
        return hotelDtos;
    }

    private RatingDetailsDto getRatingByRatingId(String ratingId) {
        RatingDetailsDto ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/getByRatingId/" + ratingId, RatingDetailsDto.class);
        return ratings;
    }


    RatingDto[] getAllRatingDataForUser(String userId) {
        RatingDto[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, RatingDto[].class);
        return ratings;
    }

    HotelDto getHotelDetailByHotelId(String hotelId) {
        HotelDto hotelDto = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/getHotelById/" + hotelId, HotelDto.class);
        return hotelDto;
    }

}
