package com.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.dto.Admin;
import com.project.dto.Cart;
import com.project.dto.Item;
import com.project.dto.Product;
import com.project.dto.User;

public class Itemdao {
	Scanner sc = new Scanner(System.in);
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("saran");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public Item saveItem() {
		Item item = new Item();
		System.out.println("enter the Itemname");
		String name = sc.nextLine();
		item.setItemname(name);
		System.out.println("enter the itemprice");
		String itemprice = sc.nextLine();
		item.setPrice(itemprice);
		System.out.println("enter the quantity ");
		int quantity = sc.nextInt();
		item.setQuantity(quantity);
		sc.nextLine();
		System.out.println("enter the description");
		String desc = sc.nextLine();
		item.setDescription(desc);
		et.begin();
		em.persist(item);
		et.commit();
		return item;
	}

	public Admin saveAdmin() {
		Admin admin = new Admin();
		System.out.println("enter the admin name : ");
		admin.setAdminname(sc.nextLine());
		System.out.println("enter the admin contact : ");
		admin.setContact(sc.nextLine());
		System.out.println("enter the admin email : ");
		admin.setEmail(sc.nextLine());
		System.out.println("enter the password : ");
		admin.setPassword(sc.nextLine());
		et.begin();
		em.persist(admin);
		et.commit();
		return admin;
	}

	public Admin findAdmin(int id) {
		return em.find(Admin.class, id);
	}

	public Item findItem(int id) {
		return em.find(Item.class, id);
	}

	public Item saveItemToAdmin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter admin id");
		int id = sc.nextInt();
		Admin exadmin = em.find(Admin.class, id);
		List<Item> items = exadmin.getItem();
		Item i = saveItem();
		items.add(i);
		exadmin.setItem(items);
		updateAdmin(exadmin, id);
		return i;
	}

	public Admin updateAdmin(Admin admin, int id) {
		Admin exadmin = findAdmin(id);
		if (exadmin != null) {
			et.begin();
			em.merge(admin);
			et.commit();
			return admin;
		} else {
			System.out.println("enter the valid id");
			return null;
		}
	}

	public Item removeItemfromAdmin() {
		System.out.println("enter admin id");
		int adminid = sc.nextInt();
		Admin admin = findAdmin(adminid);

		System.out.println("enter item id you want to delete");
		int itemid = sc.nextInt();
		Item i = findItem(itemid);
		if (admin != null) {
			if (admin.getItem().contains(i)) {
				List<Item> items = admin.getItem();
				items.remove(i);
				admin.setItem(items);
				updateAdmin(admin, admin.getAdminid());
				et.begin();
				em.remove(i);
				et.commit();
				return i;
			} else {
				System.out.println("item not present in the admin or item not exist");
				return null;
			}
		} else {
			System.out.println("admin dooes not exists with given id");
			return null;

		}

	}

	public Item findItembyName() {
		System.out.println("enter the item name");
		String itemname = sc.nextLine();
		Query query = em.createQuery("select i from Item i where itemname=?1");
		query.setParameter(1, itemname);
		Item i = (Item) query.getSingleResult();
		return (i);
	}

	public Item deleteitembynamefromadmin() {
		System.out.println("enter the admin id to find admin");
		Admin admin = findAdmin(sc.nextInt());
		Item item = findItembyName();
		if (admin.getItem().contains(item)) {
			List<Item> items = admin.getItem();
			items.remove(item);
			admin.setItem(items);

			updateAdmin(admin, admin.getAdminid());
			et.begin();
			em.remove(item);
			et.commit();
			return item;
		} else {
			System.out.println("item is not present");
			return null;
		}
	}

	public User saveUser() {
		User user = new User();
		System.out.println("enter the username ");
		user.setUsername(sc.nextLine());
		System.out.println("enter the contactdetails");
		user.setUsercontact(sc.nextLine());
		System.out.println("enter the usermail");
		user.setUseremail(sc.nextLine());
		System.out.println("enter the userpassword ");
		user.setUserpassword(sc.nextLine());
		user.setCart(new Cart());
		et.begin();
		em.persist(user);
		et.commit();
		return user;
	}

	public void saveProducttocart() {
		Product p = new Product();
		System.out.println("enter the itemid u want");
		int itemid = sc.nextInt();
		Item i = findItem(itemid);

		p.setProddescription(i.getDescription());
		p.setProdname(i.getItemname());
		p.setProdprice(i.getPrice());

		System.out.println("enter the size");
		p.setProdsize(sc.next());

		System.out.println("enter the quantity");
		int quantity = sc.nextInt();
		p.setProdquantity(quantity);

		saveProduct(p);

		System.out.println("enter the cartid");
		Cart cart = em.find(Cart.class, sc.nextInt());
		List<Product> prods = cart.getProduct();
		prods.add(p);
		cart.setProduct(prods);

//		double sum = cart.getTotalprice();
//		cart.setTotalprice(sum = sum + quantity * Double.parseDouble(i.getPrice()));

		double total = 0;
		for (Product pr : prods) {
			total = total + (Double.parseDouble(pr.getProdprice()) * pr.getProdquantity());
		}
		cart.setTotalprice(total);
		i.setQuantity(i.getQuantity() - quantity);

		updateCart(cart, cart.getId());
		updateItem(i, itemid);
	}

	public Item updateItem(Item i, int ideamid) {
		et.begin();
		em.merge(i);
		et.commit();
		return i;
	}

	public Product saveProduct(Product p) {
		et.begin();
		em.persist(p);
		et.commit();
		return p;
	}

	public Cart updateCart(Cart cart, int cartid) {
		Cart excart = em.find(Cart.class, cartid);
		if (excart != null) {
			cart.setId(cartid);
			et.begin();
			em.merge(cart);
			et.commit();
			return cart;
		} else {
			System.out.println("cart not present with given cartid");
			return null;
		}
	}

	public Product removeProductfromCart() {
		System.out.println("enter the cartid ");
		Cart cart = em.find(Cart.class, sc.nextInt());
		List<Product> exprod = cart.getProduct();
		System.out.println("enter the product id you want to delete");
		Product prod = em.find(Product.class, sc.nextInt());
		exprod.remove(prod);
		cart.setTotalprice(cart.getTotalprice() - prod.getProdquantity() * Integer.parseInt(prod.getProdprice()));
		cart.setProduct(exprod);

		et.begin();
		em.remove(prod);
		et.commit();
		return prod;
	}

	public void cartCheckout() {
		System.out.println("enter the cartid to checkout");
		int cartid = sc.nextInt();
		Cart excart = em.find(Cart.class, cartid);
		System.out.println("net payable amount is: " + excart.getTotalprice());

		System.out.println("cash accepted is: ");
		double cashaccepted = sc.nextDouble();

		while (cashaccepted < excart.getTotalprice()) {
			System.out.println("pay the whole amount");
			cashaccepted = sc.nextDouble();
		}

		double returnableamount = cashaccepted - excart.getTotalprice();

		if (returnableamount > 0) {
			System.out.println("take the amount: " + returnableamount);
			System.out.println("(*^_^*thank you for the shopping*^_^*");
		} else {
			System.out.println("(*^_^*thank you for the shopping*^_^*");
		}

	}

}
