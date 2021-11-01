package com.example.bookstore;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger Log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
	  return (args) -> {
		  Log.info("save a couple of books");
		  
		  crepository.save(new Category("SciFi"));
		  crepository.save(new Category("Romantic"));
		  crepository.save(new Category("Horror"));
		  
		  
		  repository.save(new Book("The New World Order", "Epperson", 1990, "123-344", 18.90, crepository.findByName("SciFi").get(0)));
		  repository.save(new Book("Hermanni", "Lassinen", 2021, "333-344", 9.90, crepository.findByName("Romantic").get(0)));
		  
		  User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
	  };
	  
	}

}
