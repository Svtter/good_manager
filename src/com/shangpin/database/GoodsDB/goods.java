package com.shangpin.database.GoodsDB;

import java.io.Serializable;

@SuppressWarnings("serial")
public class goods implements Serializable{
	
	String name;
	double price;
	int num;
	public goods() {
	}
	public goods(String name, double price, int num) {
		super();
		this.name = name;
		this.price = price;
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String toString(){
		return name+"		"+price+"		"+num;
		
	}
}
