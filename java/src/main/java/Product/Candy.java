package Product;

import java.math.BigDecimal;

public class Candy extends Sellable{

	public Candy(String brandName, String brandCode, BigDecimal brandPrice) {
		super(brandName, brandCode, brandPrice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeNoise() {
		
		System.out.println("Much Munch, Yum!");
	}

}
