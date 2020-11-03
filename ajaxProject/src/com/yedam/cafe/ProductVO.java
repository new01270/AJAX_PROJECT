package com.yedam.cafe;

public class ProductVO {
	private String itemNo;
	private String itemName;
	private int price;
	private String itemDesc;
	private double likeIt;
	private String category;
	private String itemImg;
	
	@Override
	public String toString() {
		return "Product [itemNo=" + itemNo + ", itemName=" + itemName + ", price=" + price + ", itemDesc=" + itemDesc
				+ ", likeIt=" + likeIt + ", category=" + category + ", itemImg=" + itemImg + "]";
	}
	
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public double getLikeIt() {
		return likeIt;
	}
	public void setLikeIt(double likeIt) {
		this.likeIt = likeIt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItemImg() {
		return itemImg;
	}
	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}
	
	
}
