package com.example.MonicaBookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.MonicaBookStore.model.Book;
import com.example.MonicaBookStore.model.BookRepository;
import com.example.MonicaBookStore.model.Category;
import com.example.MonicaBookStore.model.CategoryRepository;

@SpringBootApplication
public class MonicaBookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(MonicaBookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MonicaBookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository,CategoryRepository crepository) {
		return (args) -> {
			
		log.info("save a couple of books");
		crepository.save(new Category("Sci-Fi"));
		crepository.save(new Category("Novel"));
		crepository.save(new Category("Contemporary"));

			
		Book book1 = new Book("A Farewell to Arms","Ernest Hemingway",1929,"1232323-21",15.23,crepository.findByName("Novel").get(0));
		Book book2 = new Book("Animal Farm","George Orwell",1945,"2212343-5",16.00, crepository.findByName("Contemporary").get(0));
		
		log.info("fetch all books");
		for (Book book: brepository.findAll()) {
			log.info(book.toString());
		}
		
		brepository.save(book1);
		brepository.save(book2);
	 // Your code...add some demo data to db
	};
	}

}
