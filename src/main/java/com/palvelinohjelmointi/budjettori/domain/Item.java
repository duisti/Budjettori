package com.palvelinohjelmointi.budjettori.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String description;
	private double price;
	private int amount;
	
	@ManyToOne
    @JoinColumn(name = "categoryid")
	@JsonManagedReference
    private Category category;
	


	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(String description, double price, int amount, Category category) {
		super();
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
		return "description [description=" + description + ", amount=" + amount + ", price per description=" + price + " category=" + this.getCategory() + "]";
		else
			return "description [description=" + description + ", amount=" + amount + ", price per description=" + price + "]";
	}
	
	
	

}
