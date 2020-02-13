import java.util.*;
import java.io.*;

public class ProductList implements Serializable
{
	 private static final long serialVersionUID = 1L;
	 private List<Product> products = new LinkedList();
	 private static ProductList productList;
	 
	 private ProductList()
	 {
		 
	 }
	 
	 public static ProductList instance()
	 {
		 if(productList == null)
		 {
			 return (productList = new ProductList());
		 } else {
			 return productList;
		 }
	 }
	 
	 // edits an existing product's information
	 public void editProduct(String id)
	 {
		 boolean found = false;
		 
		 for(int i = 0; i < products.size(); i++)
		 {
			 if(products.get(i).getID().equals(id))
			 {
				String input;
                Scanner scanner = new Scanner(System.in);
				
				products.get(i).toString();
				
				System.out.println("Enter new ID for Product: ");
				System.out.print("-->");
				input = scanner.nextLine();
				products.get(i).setProductID(input);
				
				System.out.println("Enter new name for Product: ");
				System.out.print("-->");
				input = scanner.nextLine();
				products.get(i).setProductName(input);
				
				found = true;
			 }
		 }
		 
		 if (found == false)
		 {
			 System.out.println("Product not found.");
		 }
	 }
	 
	 // inserts a new product into the list
	 public boolean insertProduct(Product product)
	 {
		 products.add(product);
		 return true;
	 }
	 
	 // iterates through product list
	 public Iterator<Product> getProducts()
	 {
		 return products.iterator();
	 }
	 
	 public String toString()
	 {
		 return products.toString();
	 }
}