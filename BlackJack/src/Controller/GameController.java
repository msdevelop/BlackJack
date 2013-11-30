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
	private CardModel[]	beginCardStack	= new CardModel[52], playerStack = new CardModel[10], dealerStack = new CardModel[10];
	private int			playerBet, playerCount, dealerCount;

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
		this.getBetInput();
		this.playGame();
	}

	// ----------------------------------------------

	public void playGame()
	{
		this.generateStartCards();
	}
	
	public void generateStartCards() // TODO verify if chosen card has counter < 6; else generate new randomPos
	{
		playerCount = 0;
		dealerCount = 0;
		
		for(int i = 0; i < 2; i++)
		{
			int randomPos = generateRandomNumber();
			playerStack[playerCount] = beginCardStack[randomPos];
			playerCount++;
		}
		
		int randomPos = generateRandomNumber();
		dealerStack[dealerCount] = beginCardStack[randomPos];
		
	}
	
	public int generateRandomNumber()
	{
		return 1;
	}

	// ----------------------------------------------

	public void disableBet() // disables the button btnSetBet
	{
		gameView.disableBtnSetBet();
	}

	public void activateGameBtns() // activates hit and stay btn
	{
		gameView.activateHitStay();
	}

	// ----------------------------------------------

	public void getBetInput() // gets the bet placed by the player
	{
		if (gameView.verifyBet())
		{
			playerBet = gameView.getBet();
			gameView.setTextStatusBar("Einsatz: " + playerBet);
			gameView.setBetStatus(Integer.toString(playerBet));
			this.disableBet();
			this.activateGameBtns();
		}
		else
			gameView.setTextStatusBar("Ungültiger Wetteinsatz!");
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
				beginCardStack[index] = new CardModel(color + " " + iNumber,
						iNumber);

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
