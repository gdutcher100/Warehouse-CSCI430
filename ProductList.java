 import java.util.*;
import java.io.*;

public class ProductList implements Serializable
{
	 private static final long serialVersionUID = 1L;
	 private List<Product> products = new LinkedList<Product>();
	 private static ProductList productList;
	 
	 public ProductList()
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
	 

	// Returns the specified supplier by ID
	public Product getProduct(String productID)
	{
		for (int i = 0; i < products.size(); i++)
		{
			if (products.get(i).getID().equals(productID))
			{
				return products.get(i);
			}
		}
		return null;
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