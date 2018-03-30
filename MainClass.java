package org.cap.demo;

public class MainClass {

	public static void main(String[] args) {
		greetings();
		
		SalesClass obj=new SalesClass();
		obj.show();

	}
	
	public static void greetings(){
		System.out.println("Hello! Good Morning!");
	}

}
