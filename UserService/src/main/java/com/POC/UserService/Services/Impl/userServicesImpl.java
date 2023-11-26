package com.POC.UserService.Services.Impl;

import com.POC.Entities.Rating;
import com.POC.UserService.DTO.RatingDto;
import com.POC.UserService.Entities.User;
import com.POC.UserService.Exceptions.ResourceNotFoundException;
import com.POC.UserService.Repositories.UserRepository;
import com.POC.UserService.Services.UserService;
import com.POC.UserService.feignClient.feignRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Service
public class userServicesImpl implements UserService {

    @Autowired
   private  UserRepository userRepository ;

    @Autowired
    private RatingDto ratingDto;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private feignRatingService ratingService;
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not present in database for this given Id : " + userId));
        //localhost:8083/ratings/users/43d8ceb8-11c8-4c99-8f0c-4b2a99eeb456
      // RatingDto [] ratings = restTemplate.getForObject("http://localhost:8083/ratings/users/43d8ceb8-11c8-4c99-8f0c-4b2a99eeb456",RatingDto[].class);
       /* HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<RatingDto[]> exchange = restTemplate.exchange("http://localhost:8083/ratings/users/43d8ceb8-11c8-4c99-8f0c-4b2a99eeb456",
                HttpMethod.GET, entity,
                RatingDto[].class);*/
       /*
        List<RatingDto> ratingDtoList = new ArrayList<>();
        for(Rating r : ratings){
            RatingDto dto = new RatingDto();
            dto.setRating(r.getRating());
            dto.setFeedback(r.getFeedback());
            dto.setHotelId(r.getHotelId());
            dto.setRatingId(r.getRatingId());
            ratingDtoList.add(dto);
        }*/
        List<Rating> ratings = ratingService.getRatingByUser(user.getUserId());
        user.setRating(ratings);

        return user;
    }
}
