package app;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import dao.ConnexionBD;
import dao.Crud;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Crud crud = new Crud();
		ConnexionBD cBD = new ConnexionBD("inclusiv_projet");
		
		System.out.println("-----CREATE STATEMENT-----");
		crud.create(cBD, "Lita");
		System.out.println("-----READ RESULTSET-----");
		crud.read(cBD);
		System.out.println("-----UPDATE PREPARESTATEMENT-----");
		crud.update(cBD,"Ravao",1);
		crud.read(cBD);
		System.out.println("-----DELETE STATEMENT-----");
		crud.delete(cBD, "Ravao");
		crud.read(cBD);
		
		System.out.println("-----ROLLBACK-----");
		crud.insertAutoComit(cBD, "Naivolala");
		crud.read(cBD);
	}

}
