package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs {
	
	private File file;
	private PrintWriter pw;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	LocalDateTime dateTime = LocalDateTime.now();
	
	public Logs(String fileName) {
		
		this.file = new File(fileName);
		try {
			FileOutputStream outputStream = new FileOutputStream(file, true);
			this.pw = new PrintWriter(outputStream);
		
		} catch (FileNotFoundException e) {
			
			System.out.println("bad file");
		}
		
	}
	
	public void writeToFile(String message) {
		
		pw.write("[" + dtf.format(dateTime.now()) + "] " + message + "\n");
		pw.flush();
	}
	
}
