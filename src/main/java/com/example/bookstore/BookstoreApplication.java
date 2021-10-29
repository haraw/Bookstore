package com.example.bookstore;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;

import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger Log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
	  return (args) -> {
		  Log.info("save a couple of books");
		  
		  crepository.save(new Category("SciFi"));
		  crepository.save(new Category("Romantic"));
		  crepository.save(new Category("Horror"));
		  
		  
		  repository.save(new Book("The New World Order", "Epperson", 1990, "123-344", 18.90, crepository.findByName("SciFi").get(0)));
		  repository.save(new Book("Hermanni", "Lassinen", 2021, "333-344", 9.90, crepository.findByName("Romantic").get(0)));
	  };
	  
	}

}
