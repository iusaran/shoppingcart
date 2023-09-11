package com.project.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodid;
	private String prodname;
	private String proddescription;
	private String prodprice;
	private String prodsize;
	private int prodquantity;
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProddescription() {
		return proddescription;
	}
	public void setProddescription(String proddescription) {
		this.proddescription = proddescription;
	}
	public String getProdprice() {
		return prodprice;
	}
	public void setProdprice(String prodprice) {
		this.prodprice = prodprice;
	}
	public String getProdsize() {
		return prodsize;
	}
	public void setProdsize(String prodsize) {
		this.prodsize = prodsize;
	}
	public int getProdquantity() {
		return prodquantity;
	}
	public void setProdquantity(int prodquantity) {
		this.prodquantity = prodquantity;
	}
	@Override
	public String toString() {
		return "Product [prodid=" + prodid + ", prodname=" + prodname + ", proddescription=" + proddescription
				+ ", prodprice=" + prodprice + ", prodsize=" + prodsize + ", prodquantity=" + prodquantity + "]";
	}
	
	
	

}
