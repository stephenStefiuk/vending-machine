package Product;

import java.math.BigDecimal;

public class Chip extends Sellable{

	public Chip(String brandName, String brandCode, BigDecimal brandPrice) {
		super(brandName, brandCode, brandPrice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeNoise() {
		// TODO Auto-generated method stub
		System.out.println("Crunch Crunch, Yum!");
	}

}
