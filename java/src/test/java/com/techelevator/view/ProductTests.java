package com.techelevator.view;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import Product.Candy;
import Product.Chip;
import Product.Drink;
import Product.Gum;
import Product.Sellable;
import junit.framework.Assert;

public class ProductTests {

	@Test
	public void chipNameIsRight() {
		
		Sellable x= new Chip( "V2","Pringles", new BigDecimal(34.99));
		String expectedResult="Pringles";
		String actualResult=x.getBrandName();
		assertEquals(expectedResult, actualResult);
		
			}
	@Test
	public void candyCodeIsRight() {
		
		Sellable x= new Candy( "V2","Snickers", new BigDecimal(34.99));
		String expectedResult="V2";
		String actualResult=x.getBrandCode();
		assertEquals(expectedResult, actualResult);
		
			}
	@Test
	public void drinkPriceIsRight() {
		
		Sellable x= new Drink( "V2","Coke", new BigDecimal(34.99));
		BigDecimal expectedResult=new BigDecimal(34.99);
		BigDecimal actualResult=x.getBrandPrice();
		assertEquals(expectedResult, actualResult);
		
			}
	@Test
	public void gumQuantityIsRight() {
		
		Sellable x= new Gum( "V2","Bubble Yum", new BigDecimal(34.99));
		int expectedResult=5;
		int actualResult=x.getBrandQuantity();
		assertEquals(expectedResult, actualResult);
		
			}
	

}
