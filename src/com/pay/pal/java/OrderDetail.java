package com.pay.pal.java;

public class OrderDetail {
	
	private String productName;
	private Float subtotal;
	private Float shipping;
	private Float tax;
	private Float total;
	
	public OrderDetail(String productName, String subtotal, String shipping, String tax, String total) {
		super();
		this.productName =  productName;
		this.subtotal = Float.parseFloat(subtotal);
		this.shipping = Float.parseFloat(shipping);
		this.tax = Float.parseFloat(tax);
		this.total = Float.parseFloat(total);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSubtotal() {
		System.out.println("Subtotal".concat(String.format("%,2f",subtotal)));
		//return String.format("%,2f",subtotal);
		return String.valueOf(subtotal);
	}
	public String getShipping() {
		System.out.println("Shipping".concat(String.format("%,2f",shipping)));
		//return String.format("%,2f",shipping);
		return String.valueOf(shipping);
	}
	
	public String getTax() {
		System.out.println("Taxa".concat(String.format("%,2f",tax)));
		//return String.format("%,2f",tax);
		return String.valueOf(tax);
	}
	
	public String getTotal() {
		System.out.println("Total".concat(String.format("%,2f",total)));
		//return String.format("%,2f",total);
		return String.valueOf(total);
	}
	

}
