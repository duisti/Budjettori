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
	
	//show all items
	@RequestMapping(value = {"/",  "/itemlist"})
	public String itemlist(Model model) {
		model.addAttribute("items", repository.findAll());
		return "itemlist";
	}
	
	// RESTful service to get all items
    @RequestMapping(value="/items", method = RequestMethod.GET)
    public @ResponseBody List<Item> studentListRest() {	
        return (List<Item>) repository.findAll();
    }   
    
    // RESTful service to get item by id
    @RequestMapping(value="/item/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Item> findStudentRest(@PathVariable("id") Long itemId) {	
    	return repository.findById(itemId);
    } 
	
	
	//add new item
	@RequestMapping(value = "/add")
	public String addItem(Model model) {
		model.addAttribute("item", new Item());
    	model.addAttribute("categories", crepository.findAll());
		return "additem";
	}
	
	
	//save new item
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Item item) {
		repository.save(item);
			return "redirect:itemlist";
	}
	
	//delete item
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") Long itemId, Model model) {
		repository.deleteById(itemId);
		return "redirect:itemlist";
	}
	
	//edit item
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String modifyItem(@PathVariable("id") Long itemId, Model model) {
		model.addAttribute("item", repository.findById(itemId));
    	model.addAttribute("categories", crepository.findAll());
		return "modifyitem";
	}
	
	/*
	//index
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String greetingForm(Model model) {
		return "index";
	}
	*/

}