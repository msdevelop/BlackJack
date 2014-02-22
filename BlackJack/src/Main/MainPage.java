package Main;

import Controller.MenuController;
import Controller.XMLController;

public class MainPage
{
	
	// declaration--------------------------------
	
	public static XMLController xmlController;
	
	// constructor--------------------------------
	
	public MainPage()
	{
		xmlController = new XMLController();
		new MenuController();
	}
	
	// methods-----------------------------------
	
	public static void main(String[] args) 
	{
		new MainPage();
	}
}
