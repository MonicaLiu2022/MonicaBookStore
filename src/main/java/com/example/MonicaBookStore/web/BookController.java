package com.example.MonicaBookStore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.MonicaBookStore.model.Book;
import com.example.MonicaBookStore.model.BookRepository;
import com.example.MonicaBookStore.model.CategoryRepository;


@Controller
	public class BookController {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	// Login page
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	// Show book list
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String getBookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist"; //booklist.html
	}
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> studentListRest() {	
        return (List<Book>) repository.findAll();
    } 
 // RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }  
    // Add new book
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }  
    // Save new book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
     repository.save(book);
     return "redirect:booklist";
    }
    // Delete book
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
     repository.deleteById(bookId);
     return "redirect:../booklist";
    }
    // Edit book
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    	model.addAttribute("book", repository.findById(bookId));
    	model.addAttribute("categories", crepository.findAll());
    	return "editbook";
    }  
    
    
}