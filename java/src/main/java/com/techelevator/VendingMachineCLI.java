package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

import Product.Candy;
import Product.Chip;
import Product.Drink;
import Product.Gum;
import Product.Sellable;
import materials.Funds;


public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, "Exit"};
	private static final String[] PURCHASE_MENU = { "Feed Money", "Select Product", "Finish Transaction", "Back" };
	private static final String[] FEED_MONEY_MENU = { "1", "5", "10", "Back" };

	private Menu menu;
	private Funds funds = new Funds();
	List<Sellable> sellableList = new ArrayList<Sellable>();
	Logs auditLogs = new Logs("Log.txt");

	

	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		auditLogs.writeToFile("Machine has started.");
		
		loadInventory();
		
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println("You picked: " + choice);
			
//			if the user selects display items, loop through the sellableList and show them all the items
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (Sellable item : this.sellableList) {
					System.out.println(item.getBrandCode() + "  $" + item.getBrandPrice() + "  " + item.getBrandName()
							+ "  " + item.getBrandQuantity() + " items remaining");
				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String selection = "";
				while (!selection.equals("Back")) {
					
//					Now we are navigating through the purchase menu
					selection = (String) menu.getChoiceFromOptions(PURCHASE_MENU);
					if (selection.equals("Feed Money")) {
						
						processMoney();
												
					} else if (selection.equals("Select Product")) {
						
						for (Sellable item : this.sellableList) {
							System.out.println(item.getBrandCode() + "  $" + item.getBrandPrice() + "  "
									+ item.getBrandName() + "  " + item.getBrandQuantity() + " items remaining");
						}

//						Ask the user what product they want to buy						
						Scanner userInput = new Scanner(System.in);
						System.out.println();
						System.out.println("Please enter the brand code of the item you would like to purchase: ");
						String userInputString = userInput.nextLine().toUpperCase();
						
						if (doesItemExist(userInputString)) {
						
							for (Sellable item : this.sellableList) {


							if (userInputString.equals(item.getBrandCode())) {

								if (funds.getBalance().compareTo(item.getBrandPrice()) < 0) {
									System.out.println("Add more money please");
									System.out.println("Your current balance is: $" + funds.getBalance());
									break;
								}

								System.out.println("You selected " + item.getBrandName() + " $" + item.getBrandPrice());
								
								if (item.getBrandQuantity() > 0) {
									item.reduceInventory();	
									funds.removeMoney(item.getBrandPrice());
									System.out.println("Money remaining in your balance: $" + funds.getBalance());
									item.makeNoise();
									auditLogs.writeToFile(item.getBrandName() + " " + item.getBrandCode() + " $" + (funds.getBalance().add(item.getBrandPrice()) + " $" + funds.getBalance()));
								} else {
									System.out.println("This item is sold out!");
								}
							}

							}

						} else {
							System.out.println("Invalid Brand Code");
						}

					} else if (selection.equals("Finish Transaction")) {
						
						auditLogs.writeToFile("GIVE CHANGE: $" + funds.getBalance() + " $0.00");
						getChange();
						funds.removeMoney(funds.getBalance());
						break;
					}

				}
			} else if(choice.equals("Exit")){
				System.out.println("Thanks for your money!");
				System.exit(0);
			}
		}
		
	}


	public boolean doesItemExist(String userInputString) {
		boolean doesItExist = false;
		for (Sellable item : this.sellableList) {
			if (userInputString.equals(item.getBrandCode())) {
				doesItExist = true;
			}
		}
		return doesItExist;
	}

	public void getChange() {

		BigDecimal oneHundred = new BigDecimal("100");
		BigDecimal customerChange = funds.getBalance().multiply(oneHundred);

		int custChangeInt = customerChange.intValue();

		int quarter, dime, nickel;

		quarter = custChangeInt / 25;
		custChangeInt = custChangeInt % 25;

		dime = custChangeInt / 10;
		custChangeInt = custChangeInt % 10;

		nickel = custChangeInt / 5;
		custChangeInt = custChangeInt % 5;

		System.out.println("Here's your change");
		System.out.println(quarter + " Quarters");
		System.out.println(dime + " Dimes");
		System.out.println(nickel + " Nickels");


	}

	public void processMoney() {

		String selection = "";
		while (!selection.equals("Back")) {
			selection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU);

//			auditLogs.writeToFile("You added " + selection);
			if (selection.equals("1")) {

				funds.setBalance(new BigDecimal(1));
				
			}
			if (selection.equals("5")) {

				funds.setBalance(new BigDecimal(5));
			}

			if (selection.equals("10")) {

				funds.setBalance(new BigDecimal(10));
			}
			
			if (!selection.equals("Back")) {
			auditLogs.writeToFile("FEED MONEY: $" + selection + "  $" + funds.getBalance());
			}
			
			System.out.println("You have $" + funds.getBalance());
			
		}
	}

	public void loadInventory() {
		File inputFile = new File("vendingmachine.csv");
		try {
			Scanner input = new Scanner(inputFile);

			while (input.hasNextLine()) {
				String path = input.nextLine();
				String[] dataArr = path.split("\\|");

				String prodCode = dataArr[0];
				String prodName = dataArr[1];
				BigDecimal price = new BigDecimal(dataArr[2]);

				if (dataArr[3].equals("Chip")) {
					Sellable ch = new Chip(prodCode, prodName, price);
					this.sellableList.add(ch);
				}
				if (dataArr[3].equals("Drink")) {
					Sellable d = new Drink(prodCode, prodName, price);
					this.sellableList.add(d);
				}

				if (dataArr[3].equals("Candy")) {
					Sellable ca = new Candy(prodCode, prodName, price);
					this.sellableList.add(ca);
				}
				if (dataArr[3].equals("Gum")) {
					Sellable g = new Gum(prodCode, prodName, price);
					this.sellableList.add(g);
				}
			}
		} catch (Exception e) {
			System.out.println("error loading file.");
		}

	}
	

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

		// Set up vending machine display

	}

}
//				
//			
//
//	}
//	
//	
//}
