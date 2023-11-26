package com.POC.UserService;

import com.POC.UserService.feignClient.feignRatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private feignRatingService ratingService;
	void createRating(){

	}

}
