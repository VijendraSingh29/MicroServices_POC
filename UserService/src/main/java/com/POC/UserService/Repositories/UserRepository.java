package com.POC.UserService.Repositories;

import com.POC.UserService.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}
