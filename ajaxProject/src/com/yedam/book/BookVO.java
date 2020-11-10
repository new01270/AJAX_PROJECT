package com.yedam.book;

public class BookVO {
	private int bookNo;
	private String bookTitle;
	private String bookAuthor;
	private int price;
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	// 생성자? 만드는이유??
	public BookVO(int bookNo, String bookTitle, String bookAuthor, int price) {
		super();
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.price = price;
	}
	public BookVO(String bookTitle, String bookAuthor, int price) {
		super();
		
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.price = price;
	}
	
	
	
	
	
	
}
