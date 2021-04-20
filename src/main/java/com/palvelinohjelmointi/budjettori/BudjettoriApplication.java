package com.palvelinohjelmointi.budjettori;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.palvelinohjelmointi.budjettori.domain.*;
import com.palvelinohjelmointi.budjettori.web.*;


@SpringBootApplication
public class BudjettoriApplication {
	private static final Logger log = LoggerFactory.getLogger(BudjettoriApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BudjettoriApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ItemRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			//something here
			
			Category c1 = new Category("Groceries");
			Category c2 = new Category("Supplies and Parts");
			Category c3 = new Category("Miscellaneous");
			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);

			Item b1 = new Item("Salmon, 600g", 3.99, 2, crepository.findByName("Groceries").get(0));
			Item b2 = new Item("Toilet Paper, pack of 6", 2.29, 1, crepository.findByName("Supplies and Parts").get(0));
			
			brepository.save(b1);
			brepository.save(b2);
			
			//create some users and admins
			//password should be "user"
			User user = new User("user", "$2y$12$PpkDMTqee65vWWL4sgl3..qBeZmRQrEFK9fjLtV8CYjHKibmukbWu", "USER");
			//password should be "meidänmies"
			User admin = new User("admin", "$2y$12$EpjKs7XF4dLMiCc1yfUn9eobRa3N9QnicBUlI69mBJUtmxc573W32", "ADMIN");
			urepository.save(user);
			urepository.save(admin);
		};
	}

}
