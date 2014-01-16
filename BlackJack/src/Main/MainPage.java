package Main;

import Controller.MenuController;
import Controller.XMLController;

public class MainPage
{
	public static XMLController xmlController;
	
	public static void main(String[] args) 
	{
		xmlController = new XMLController();
		new MenuController();
	}
}
