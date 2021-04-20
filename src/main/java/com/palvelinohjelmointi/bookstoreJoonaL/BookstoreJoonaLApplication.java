package com.palvelinohjelmointi.bookstoreJoonaL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.palvelinohjelmointi.bookstoreJoonaL.domain.*;
import com.palvelinohjelmointi.bookstoreJoonaL.web.*;


@SpringBootApplication
public class BookstoreJoonaLApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreJoonaLApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreJoonaLApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			//something here
			Category c1 = new Category("Mystery");
			Category c2 = new Category("Thriller");
			crepository.save(c1);
			crepository.save(c2);
			
			Book b1 = new Book("Uusi Kirja", "Meitsi", 1990, "1592195", crepository.findByName("Mystery").get(0));
			Book b2 = new Book("Toinen Kirja", "Meitsi", 1993, "1592155", crepository.findByName("Thriller").get(0));
			
			//create some users and admins
			//password should be "user"
			User user = new User("user", "$2y$12$PpkDMTqee65vWWL4sgl3..qBeZmRQrEFK9fjLtV8CYjHKibmukbWu", "USER");
			//password should be "meidänmies"
			User admin = new User("admin", "$2y$12$EpjKs7XF4dLMiCc1yfUn9eobRa3N9QnicBUlI69mBJUtmxc573W32", "ADMIN");
			urepository.save(user);
			urepository.save(admin);
			
			brepository.save(b1);
			brepository.save(b2);
		};
	}

}
