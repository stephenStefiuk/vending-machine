package Product;

import java.math.BigDecimal;

public abstract class Sellable {
	private String brandName;
	private String brandCode;
	private int brandQuantity;
	private BigDecimal brandPrice = new BigDecimal(0);

	public Sellable(String brandCode, String brandName, BigDecimal brandPrice) {

		this.brandName = brandName;
		this.brandCode = brandCode;
		this.brandPrice = brandPrice;
		this.brandQuantity = 5;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public int getBrandQuantity() {
		return brandQuantity;
	}

	public void setBrandQuantity(int brandQuantity) {
		this.brandQuantity = brandQuantity;
	}

	public BigDecimal getBrandPrice() {
		return brandPrice;
	}

	public void setBrandPrice(BigDecimal brandPrice) {
		this.brandPrice = brandPrice;
	}

	public abstract void makeNoise();

	public void reduceInventory() {
		this.brandQuantity -= 1;
	}

}
