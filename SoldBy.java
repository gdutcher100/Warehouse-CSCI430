import java.util.*;
import java.io.*;
import java.lang.*;

public class SoldBy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Product product;
	private Supplier supplier;
	private float buyPrice;
	
	public SoldBy(Product p, Supplier s, float price) {
		this.product = p;
		this.supplier = s;
		this.buyPrice = price;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public float getPrice() {
		return buyPrice;
	}
	
	public String toString() {
		return ("Supplier:" + supplier + " Price: " + buyPrice);
	}
}