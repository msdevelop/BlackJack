package Main;

import Controller.MenuController;
import Controller.XMLController;

public class MainPage
{
	public static XMLController xmlController;
	
	public MainPage()
	{
		xmlController = new XMLController();
		new MenuController();
	}
	
	public static void main(String[] args) 
	{
		new MainPage();
	}
}
