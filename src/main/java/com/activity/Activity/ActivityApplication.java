package com.activity.Activity;

import com.activity.Activity.model.ERole;
import com.activity.Activity.model.Role;
import com.activity.Activity.model.User;
import com.activity.Activity.repository.RoleRepository;
import com.activity.Activity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class ActivityApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ActivityApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			Optional<User> existsUser = userRepository.findByUsername("Achraf");
			if(existsUser.isPresent()){
				System.out.println("User Already Exist");
				return;
			}
			User user = new User();
			user.setEmail("achraf@gmail.com");
			user.setPassword(passwordEncoder.encode("0210moreau"));
			user.setUsername("Achraf");
//
			Set<ERole> ERoles = Set.of(ERole.ROLE_ADMIN, ERole.ROLE_MODERATOR, ERole.ROLE_USER);
			ERoles.forEach(elm -> {
				Role roleToBeInserted = new Role(elm);
				roleRepository.save(roleToBeInserted);
			});

			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: role Was Not Found !"));
			System.out.println(role);
			roles.add(role);
			user.setRoles(roles);
			userRepository.save(user);
		};
	}
}
