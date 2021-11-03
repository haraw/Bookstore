package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;


@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Kirjojen lisäys
	@RequestMapping(value= "/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book()); 
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value={"/booklist","/"})
	public String bookList(Model model) {
		
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	// Kirjojen muuttaminen
	@RequestMapping(value="/modify/{id}", method=RequestMethod.GET)
	public String modifyBook(@PathVariable("id") Long bookId, Model model) {
		System.out.println("MUOKKAUS");
		Optional<Book> book = repository.findById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("categories", crepository.findAll());
		return "modifybook";
	}
	// Tallentaminen
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }
	// Poisto
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
    	repository.deleteById(id);
        return "redirect:../booklist";
    }
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// REST-toiminnot
	
	@GetMapping("/kirjat")
    public @ResponseBody List<Book> booklistREST() {	
        return (List<Book>) repository.findAll();
    }
	
	@GetMapping("/kirjat/{id}")
    public @ResponseBody Optional<Book> findBookREST(@PathVariable("id") Long id) {	
    	return repository.findById(id);
    }
}
