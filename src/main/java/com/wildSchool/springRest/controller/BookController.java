package com.wildSchool.springRest.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wildSchool.springRest.model.Book;
import com.wildSchool.springRest.repository.BookRepository;

@RestController
public class BookController {
	
	
	@Autowired
	BookRepository bookRepository;
	
	
	/**
	 * Show All Book
	 * @return
	 */
	@GetMapping("/books")
	public List<Book> showAllBook(){
		return bookRepository.findAll();
	}
	/**
	 * Show book by id
	 * @param id
	 * @return
	 */
	@GetMapping("/books/{id}")
	public Book showBookById(@PathVariable Long id) {
		return bookRepository.findById(id).get();
	}
	/**
	 * Add the new book
	 * @param book
	 * @return
	 */
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	/**
	 * Modification (update) the book and save
	 * @param id
	 * @param book
	 * @return
	 */
	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		Book bookOfModification = bookRepository.findById(id).get();
		bookOfModification.setTitle(book.getTitle());
		bookOfModification.setAuthor(book.getAuthor());
		bookOfModification.setDescription(book.getDescription());
		return bookRepository.save(bookOfModification);
	}
	/**
	 * remove the book
	 * @param id
	 * @return
	 */
	@DeleteMapping("/books/{id}")
	public boolean removeBook(@PathVariable Long id) {
		bookRepository.deleteById(id); 
		return true;
	}
	/**
	 * search by term
	 * @param body
	 * @return
	 */
	@PostMapping("/books/search")
	public List<Book> search(@RequestBody Map<String, String> body){
		String searchTerm = body.get("text");
		return bookRepository.findByTitleContainingOrAuthorContainingOrDescriptionContaining(searchTerm, searchTerm, searchTerm);
	}
}
