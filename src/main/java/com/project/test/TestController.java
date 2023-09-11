package com.project.test;

import java.util.Scanner;

import com.project.dao.Itemdao;

public class TestController {

	public static void main(String[] args) {
      Itemdao idao=new Itemdao();
      boolean flag=true;
      while(flag) {
    	  Scanner sc=new Scanner(System.in);
    	  System.out.println("Enter the choice : \n"
    			  +"1 to create admin \n"
    			  +"2 to add item \n"
    			  +"3 to delete item \n"
    			  +"4 to save user \n"
    			  +"5 to add product \n"
    			  +"6 to remove product \n"
    			  +"7 to checkout cart \n"
    			  +"any other to quit \n");
    	  int option=sc.nextInt();
    	  switch (option) {
		case 1:
			idao.saveAdmin();
			break;

		case 2:
			idao.saveItem();
			break;
			
		case 3:
			idao.removeItemfromAdmin();
			break;
			
		case 4:
			idao.saveUser();
			break;
			
		case 5:
			idao.saveProducttocart();
			break;
			
		case 6:
			idao.removeProductfromCart();
			break;
			
		case 7:
			idao.cartCheckout();
			break;
		default:
			flag=false;
			System.out.println("program termintated");
			break;
		}
      }
//      int i=1;
//      while(i<=10) {
//    	  System.out.println( idao.saveItemToAdmin());
//    	  i++;
//      }
//      System.out.println(idao.saveItem());
//     System.out.println(idao.saveAdmin());
      
//      Item item=idao.findItem(1);
//      List<Item> items= new ArrayList<Item>();
//      items.add(item);
//      
//      Admin admin=idao.findAdmin(1);
//      admin.setItem(items);
//      
//      System.out.println(idao.updateAdmin(admin, 1));
      
//      System.out.println( idao.saveItemToAdmin());
      
//      System.out.println(idao.removeItemfromAdmin());
      
//      System.out.println(idao.findItembyName());
      
//      System.out.println(idao.saveUser());
      
//     idao.saveProducttocart();
      
//      System.out.println(idao.removeProductfromCart());
      
      
	}
}
