package com.cruds.model;

public class Supplier {

	private String name;
	private String email;
	public int product_id;

	public Supplier(String name, String email, int product_id) {
		this.name = name;
		this.email = email;
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "" + name + "\t" + email + "\t" + product_id;
	}
	
	

}
