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
import com.example.MonicaBookStore.model.User;
import com.example.MonicaBookStore.model.UserRepository;


@SpringBootApplication
public class MonicaBookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(MonicaBookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MonicaBookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository,CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			
		log.info("save a couple of books");
		crepository.save(new Category("Sci-Fi"));
		crepository.save(new Category("Novel"));
		crepository.save(new Category("Contemporary"));

			
		Book book1 = new Book("A Farewell to Arms","Ernest Hemingway",1929,"1232323-21",15.23,crepository.findByName("Novel").get(0));
		Book book2 = new Book("Animal Farm","George Orwell",1945,"2212343-5",16.00, crepository.findByName("Contemporary").get(0));
		
		// Create users: admin/admin user/user
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		urepository.save(user1);
		urepository.save(user2);
					
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
