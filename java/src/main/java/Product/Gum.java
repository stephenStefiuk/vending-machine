package Product;

import java.math.BigDecimal;

public class Gum extends Sellable{

	public Gum(String brandName, String brandCode, BigDecimal brandPrice) {
		super(brandName, brandCode, brandPrice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeNoise() {
		// TODO Auto-generated method stub
		System.out.println("Chew Chew, Yum!");
	}


}
