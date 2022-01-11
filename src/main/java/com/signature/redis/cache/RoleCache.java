package com.signature.redis.cache;

import com.redis.om.spring.repository.RedisDocumentRepository;
import com.signature.redis.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCache extends RedisDocumentRepository<Role, String> {
}
