package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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

	// leave current table and return to menu
	public void leaveTableToMenu()
	{
		this.gameView.dispose();
		new MenuController();
	}

	// cardStack is initialized; bets are placed
	public void initializeGame()
	{
		if(this.getBetInput())
			this.playGame();
	}

	// game starts after bets are placed
	public void playGame()
	{
		this.initializeCardStack();
		this.generateStartCards();
		this.printCards();
	}

	// prints both player and dealer cards
	public void printCards()
	{
		this.printPlayerCards();
		this.printDealerCards();
	}

	// prints player cards
	public void printPlayerCards()
	{
		String playerCardsOutput = "";

		for (int i = 0; i < playerCount; i++)
		{
			playerCardsOutput = playerCardsOutput + (playerStack[i].getName()) + "\n";
		}

		gameView.setPlayerOut(playerCardsOutput);
	}

	// prints dealer cards
	public void printDealerCards()
	{
		String dealerCardsOutput = "";

		for (int i = 0; i < dealerCount; i++)
		{
			dealerCardsOutput = dealerCardsOutput + (dealerStack[i].getName()) + "\n";
		}

		gameView.setDealerOut(dealerCardsOutput);
	}

	// generates random cards at gamestart
	public void generateStartCards()
	{
		for (int i = 0; i < 2; i++)
		{
			int randomPos = generateRandomNumber();

			while (beginCardStack[randomPos].getCount() >= 5)
			{
				randomPos = generateRandomNumber();
			}

			playerStack[playerCount] = beginCardStack[randomPos];
			playerCount++;
		}

		int randomPos = generateRandomNumber();

		while (beginCardStack[randomPos].getCount() >= 5)
		{
			randomPos = generateRandomNumber();
		}

		dealerStack[dealerCount] = beginCardStack[randomPos];
		dealerCount++;
	}

	// generates random number for card selction
	public int generateRandomNumber()
	{
		Random generator = new Random();
		int randomNmbr = generator.nextInt(51);
		return randomNmbr;
	}

	// disables the button btnSetBet
	public void disableBet()
	{
		gameView.disableBtnSetBet();
	}

	// activates hit and stay btn
	public void activateGameBtns()
	{
		gameView.activateHitStay();
	}

	// gets the bet placed by the player
	public boolean getBetInput()
	{
		if (gameView.verifyBet())
		{
			playerBet = gameView.getBet();
			gameView.setTextStatusBar("Einsatz: " + playerBet);
			gameView.setBetStatus(Integer.toString(playerBet));
			this.disableBet();
			this.activateGameBtns();
			
			return true;
		}
		else
		{
			gameView.setTextStatusBar("Ungültiger Wetteinsatz!");
			return false;
		}
	}

	// initialize beginCardStack with 52 cards
	public void initializeCardStack()
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
