package com.cruds.model;

import java.util.Date;

public class Order {

	private int id;
	public Date issue_date;
	public int product_id;

	public Order(int id, Date issue_date, int product_id) {
		this.id = id;
		this.issue_date = issue_date;
		this.product_id = product_id;
	}

	public int getId() {
		return id;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "" + id  + "\t" + issue_date  + "\t" + product_id;
	}
		

}
