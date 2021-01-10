package Product;

import java.math.BigDecimal;

public class Drink extends Sellable{

	public Drink(String brandName, String brandCode, BigDecimal brandPrice) {
		super(brandName, brandCode, brandPrice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeNoise() {
		
		System.out.println("Glug Glug, Yum!"); 
	}



}
