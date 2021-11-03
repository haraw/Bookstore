package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;



@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;
    
    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByLastnameShouldReturnStudent() {
        List<Book> books = repository.findByTitle("Johnson");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Epperson");
    }
    
    @Test
    public void createNewBookt() {
    	Book book = new Book("Mikki Hiiri", "Carl Barks", 1890, "523-344", 28.90, crepository.findByName("SciFi").get(0));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
   // (String title, String author, int year, String isbn, double price, Category category)
    
    @Test
    public void deleteNewBook() {
		List<Book> books = repository.findByTitle("The New World Order");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBook = repository.findByTitle("The New World Order");
		assertThat(newBook).hasSize(0);
     }

}

