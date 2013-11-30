package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Models.CardModel;
import Views.GameView;
import Views.MenuView;

public class GameController implements ActionListener
{

	// declaration--------------------------------

	private GameView	gameView;
	private CardModel[]	beginCardStack	= new CardModel[52];

	// constructor--------------------------------

	public GameController()
	{
		this.gameView = new GameView(this);
		this.gameView.setVisible(true);
	}

	// methods-----------------------------------

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("placeBet"))
		{
			this.initializeGame();
		}
		else
			if (e.getActionCommand().equals("leaveTable"))
			{
				this.leaveTableToMenu();
			}
	}

	// ----------------------------------------------

	public void leaveTableToMenu() // leave current table and return to menu
	{
		this.gameView.dispose(); // close gameView
		new MenuController(); // open new menuView
	}

	// ----------------------------------------------

	public void initializeGame()
	{
		this.initializeCardStack();
		this.getBet();
	}

	// ----------------------------------------------

	public int getBet() // returns the bet placed by the player
	{
		gameView.getBet();
		return 1;
	}

	// ----------------------------------------------

	public void initializeCardStack() // initialize beginCardStack with 52 cards
	{
		String color = "";
		int index = 0;

		for (int iColor = 0; iColor < 4; iColor++)
		{
			switch (iColor)
			{
				case 0:
					color = "Kreuz";
					break;
				case 1:
					color = "Pik";
					break;
				case 2:
					color = "Herz";
					break;
				case 3:
					color = "Karo";
					break;
			}

			for (int iNumber = 2; iNumber < 11; iNumber++)
			{
				beginCardStack[index] = new CardModel(color + " " + iNumber, iNumber);

				index++;
			}

			beginCardStack[index] = new CardModel(color + " Ass", 11);
			index++;
			beginCardStack[index] = new CardModel(color + " König", 10);
			index++;
			beginCardStack[index] = new CardModel(color + " Dame", 10);
			index++;
			beginCardStack[index] = new CardModel(color + " Bube", 10);
			index++;
		}
	}
}
