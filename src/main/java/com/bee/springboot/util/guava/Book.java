package com.bee.springboot.util.guava;

public class Book {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Book(int id) {
		super();
		this.id = id;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return ""+id;
	}
}

