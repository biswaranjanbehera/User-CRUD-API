package com.api.user.apiuser.repository;

import com.api.user.apiuser.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByEmail(String email);
    User findById(int userId);
}
