package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.MenuView;

public class MenuController implements ActionListener
{
	// declaration---------------------------------------
	
	private MenuView menuView;
	
	// constructor---------------------------------------
	
	public MenuController()
	{
		this.menuView = new MenuView(this);
		this.menuView.setVisible(true);
	}
	
	// methods-------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	
		if(e.getActionCommand().equals("Neues Spiel"))
		{
			new GameController();
			
		}else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
	}
}
