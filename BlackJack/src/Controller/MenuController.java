package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.MenuView;

public class MenuController implements ActionListener
{
	private MenuView view;
	
	public MenuController()
	{
		this.view = new MenuView(this);
		this.view.setVisible(true);
	}
	
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
