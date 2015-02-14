package com.guendouz.tutdb;

import java.sql.Date;

public class Main {

	public static void main(String[] args) {
		Connexion connexion = new Connexion("Database.db");
		connexion.connect();
		
		Book book = new Book("9aa883e6-dfc0-4149-902a-4dbdfa22a408",
				"Systeme D'Information", "", 450, Date.valueOf("2012-11-01"),
				"");
		System.out.println(book.getPublished());
		connexion.addBook(book);

		connexion.close();
	}

}
