package com.signature.redis.controllers;

import com.signature.redis.domain.User;
import com.signature.redis.cache.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserCache userCache;

  @PostMapping("/")
  public User save(@RequestBody User user) {
    return userCache.save(user);
  }
  
  @GetMapping("/q")
  public List<User> findByName(@RequestParam String firstName, @RequestParam String lastName) {
    return userCache.findByFirstNameAndLastName(firstName, lastName);
  }
  
  @GetMapping("/name/{lastName}")
  Optional<User> byName(@PathVariable("lastName") String lastName) {
    return userCache.findOneByLastName(lastName);
  }
  
  @GetMapping("/exists")
  boolean isEmailTaken(@RequestParam("email") String email) {
    return userCache.existsByEmail(email);
  }

}
