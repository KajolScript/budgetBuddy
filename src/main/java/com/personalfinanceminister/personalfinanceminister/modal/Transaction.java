package com.personalfinanceminister.personalfinanceminister.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private String description;
    private double amount;
    private LocalDate date;
    private String type; // 'income' or 'expense'
    private String category;
    private String formattedDate;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getFormattedDate() {
        return formattedDate;
    }

	public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
        // Convert formatted date to LocalDate when setting
        this.date = LocalDate.parse(formattedDate);
    }
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", description=" + description + ", amount=" + amount + ", date=" + date
				+ ", type=" + type + ", category=" + category + "]";
	}

    // Getters and setters
    
    
}