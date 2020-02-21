import java.util.*;
import java.io.*;
import java.lang.*;

public class SoldBy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Supplier supplier;
	private float buyPrice;
	
	public SoldBy(Supplier s, float price) {
		this.supplier = s;
		this.buyPrice = price;
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