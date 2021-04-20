package com.palvelinohjelmointi.budjettori.web;

import java.util.ArrayList;
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

import com.palvelinohjelmointi.budjettori.domain.*;


@Controller
public class ItemController {
	@Autowired
	private ItemRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// login
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	//show all books
	@RequestMapping(value = {"/",  "/booklist"})
	public String booklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Item> studentListRest() {	
        return (List<Item>) repository.findAll();
    }   
    
    // RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Item> findStudentRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    } 
	
	
	//add new book
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Item());
    	model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	
	//save new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Item book) {
		repository.save(book);
			return "redirect:booklist";
	}
	
	//delete book
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:booklist";
	}
	
	//edit book
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String modifyBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
    	model.addAttribute("categories", crepository.findAll());
		return "modifybook";
	}
	
	/*
	//index
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String greetingForm(Model model) {
		return "index";
	}
	*/

}