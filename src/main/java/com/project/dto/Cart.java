package com.project.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartid;
	private double totalprice;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> product;
	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", totalprice=" + totalprice + ", product=" + product + "]";
	}
	public int getId() {
		return cartid;
	}
	public void setId(int id) {
		this.cartid = id;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	

}
