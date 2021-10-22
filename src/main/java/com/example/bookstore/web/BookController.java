package com.example.bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;


@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value= "/index", method=RequestMethod.POST)
	public String showIndex() {
		System.out.println("toimiiko?");
		//GO TO PAGE HELLO.HTML
		return "index";
	}
	
	@RequestMapping(value="/booklist")
	public String bookList(Model model) {
		
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value="/modify/{id}", method=RequestMethod.GET)
	public String modifyBook(@PathVariable("id") Long bookId, Model model) {
		System.out.println("OLLAANKO TÄÄLLÄ");
		Optional<Book> book = repository.findById(bookId);
		model.addAttribute("book", book);
		return "modifybook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }
}