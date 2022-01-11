package com.signature.redis;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import com.signature.redis.domain.Role;
import com.signature.redis.domain.User;
import com.signature.redis.cache.RoleCache;
import com.signature.redis.cache.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.signature.redis.*")
public class RedisOmApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisOmApplication.class, args);
	}

	@Autowired
	private UserCache userRepo;

	@Autowired
	private RoleCache roleRepo;

	@Bean
	CommandLineRunner loadTestData() {
		return args -> {
			userRepo.deleteAll();
			roleRepo.deleteAll();

			Role bass = Role.of("BASS");
			Role vocals = Role.of("VOCALS");
			Role guitar = Role.of("GUITAR");
			Role drums = Role.of("DRUMS");

			roleRepo.saveAll(List.of(bass, vocals, guitar, drums));

			User john = User.of("Zack", "de la Rocha", "zack@ratm.com", bass);
			User tim = User.of("Tim", "Commerford", "tim@ratm.com", vocals);
			User tom = User.of("Tom", "Morello", "tom@ratm.com", guitar);
			User brad = User.of("Brad", "Wilk", "brad@ratm.com", drums);

			userRepo.saveAll(List.of(john, tim, tom, brad));

			System.out.println(userRepo.findOneByLastName("Wilk").orElse(null));
		};
	}
}