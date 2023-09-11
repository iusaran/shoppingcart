package com.project.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminid;
	private String adminname;
	private String adminemail;
	private String adminpassword;
	private String admincontact;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Item> item;
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", adminname=" + adminname + ", email=" + adminemail + ", password=" + adminpassword
				+ ", contact=" + admincontact + ", item=" + item + "]";
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getEmail() {
		return adminemail;
	}
	public void setEmail(String email) {
		this.adminemail = email;
	}
	public String getPassword() {
		return adminpassword;
	}
	public void setPassword(String password) {
		this.adminpassword = password;
	}
	public String getContact() {
		return admincontact;
	}
	public void setContact(String contact) {
		this.admincontact = contact;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}

}
