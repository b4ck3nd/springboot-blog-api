package org.springboot.blog.api;

import jakarta.annotation.PostConstruct;
import org.springboot.blog.api.model.Role;
import org.springboot.blog.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class BlogApiApplication {

	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

	@PostConstruct
	private void postConstruct() {
		Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");

		roleRepository.sa

	}

}
