package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.GameView;
import Views.MenuView;

public class GameController implements ActionListener
{
	
	// declaration--------------------------------
	
	private GameView gameView;
	
	// constructor--------------------------------
	
	public GameController()
	{
		this.gameView = new GameView(this);
		this.gameView.setVisible(true);
	}
	
	// methods-----------------------------------
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
