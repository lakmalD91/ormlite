package com.example.models;

import com.j256.ormlite.field.DatabaseField;


public class Item {
	@DatabaseField(id =true)
	String itemname;
	@DatabaseField
	String itemcat;
	@DatabaseField
	Double itemprice;
	
	public Item(){
		
	}
	
	public Item(String name,String category, Double price){
		this.itemname = name;
		this.itemcat = category;
		this.itemprice = price;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemcat() {
		return itemcat;
	}

	public void setItemcat(String itemcat) {
		this.itemcat = itemcat;
	}

	public Double getItemprice() {
		return itemprice;
	}

	public void setItemprice(Double itemprice) {
		this.itemprice = itemprice;
	}
	
	
	
}
