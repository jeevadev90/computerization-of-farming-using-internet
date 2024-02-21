package com.backend.velanmai;

import com.backend.velanmai.Entity.Role;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VelanmaiApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(VelanmaiApplication.class, args);
	}

	public void run(String... args)
	{
		User AdminAccount=userRepository.findByRole(Role.ADMIN);
		if(null==AdminAccount)
		{
			User user=new User();
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setName("admin");
			user.setEmail("jeeva1@gmail.com");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("1234"));
			userRepository.save(user);
		}
	}
}
