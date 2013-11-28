package Controller;

import Views.GameView;
import Views.MenuView;

public class GameController
{
	private GameView view;
	
	public GameController()
	{
		this.view = new GameView(this);
		this.view.setVisible(true);
	}
}
