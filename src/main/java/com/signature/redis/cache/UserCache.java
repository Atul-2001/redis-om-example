package com.signature.redis.cache;

import com.redis.om.spring.repository.RedisDocumentRepository;
import com.signature.redis.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCache extends RedisDocumentRepository<User, String> {
  
  Optional<User> findOneByLastName(String lastName);

  List<User> findByFirstNameAndLastName(String firstName, String lastName);
  
  boolean existsByEmail(String email);
}
