package com.techelevator.view;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import materials.Funds;

public class FundsTest {

	@Test
	public void startingBalanceIsZero() {
		Funds x=new Funds();
		assertEquals(new BigDecimal(0), x.getBalance());
	}
	
	@Test
	public void addMoneyTest() {
		Funds x=new Funds();
		x.setBalance(new BigDecimal(5));
		
		assertEquals(new BigDecimal(5), x.getBalance());
	}
	@Test
	public void removeMoneyTest() {
		Funds x=new Funds();
		x.setBalance(new BigDecimal(5));
		x.removeMoney(new BigDecimal(5));
		assertEquals(new BigDecimal(0), x.getBalance());
	}

}

//Log Writer tests: Test.text 1. input test 2. read it back 3. see if it matches
//