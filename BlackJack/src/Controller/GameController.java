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
	private CardModel[]	beginCardStack	= new CardModel[52], playerStack = new CardModel[6], dealerStack = new CardModel[6];
	private int			playerBet, playerCount, dealerCount;

	// constructor--------------------------------

	public GameController()
	{
		this.gameView = new GameView(this);
		this.gameView.setVisible(true);
	}

	// methods-----------------------------------

	// button actions
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("placeBet"))
		{
			this.initializeGame();
		}
		else
			if (e.getActionCommand().equals("hit"))
			{
				this.hit();
			}
			else
				if (e.getActionCommand().equals("stay"))
				{
					this.stay();
				}
				else
					if(e.getActionCommand().equals("playAgain"))
					{
						this.playAgain();
					}
					else
						if (e.getActionCommand().equals("leaveTable"))
						{
							this.leaveTableToMenu();
						}
	}
	
	// player continues game with new card
	public void hit()
	{
		this.generateNewPlayerCard();
		gameView.setTextStatusBar("Sie erhalten eine weitere Karte.");
		gameView.updateUI();
		this.bitteWarten(2000);
		this.printPlayerCards();
		this.updatePlayerCardSum();
		
		if(checkPlayerCardSum() == 1)
			gameView.setTextStatusBar("Möchten Sie noch eine Karte?");
		else
			if(checkPlayerCardSum() == 2)
				this.playerLose();
			else
				{
					gameView.setTextStatusBar("Sie können keine weitere Karte ziehen. Dealer ist am Zug.");
					this.passTurn();
				}
	}

	// ends game after player loses
	public void playerLose()
	{
		gameView.disableHitStay();
		gameView.enablePlayAgain();
		gameView.setTextStatusBar("Sie haben leider verloren. Ihr Wetteinsatz wird eingezogen.");
	}
	
	// generates new game session
	public void playAgain()
	{
		new GameController();
		this.gameView.dispose();
	}
	
	// passes turn to computer
	public void passTurn()
	{
		gameView.disableHitStay();
	}
	
	// verifies if player is above 21 or equal to it or below
	public int checkPlayerCardSum()
	{
		int playerCardSum = Integer.parseInt(gameView.getCardSumPlayer());
		
		if(playerCardSum == 21)
		{
			return 3;
		}
		else
			if(playerCardSum > 21)
			{
				return 2;
			}
			else
				return 1;
		
	}
	
	// downgrades one ace at a time
	public boolean downGradeAce()
	{
		int point = 0;
		
		while(point == 0)
		{
			for(int i = 0; i < playerCount; i++)
			{
				if(playerStack[i].getValue() == 11)
				{
					playerStack[i].setValue(1);
					point = 1;
				}
			}
			if(point == 0)
				point = 2;
		}
		
		if(point == 1)
		{
			this.updatePlayerCardSum();
			return true;
		}
		else
			return false;
	}
	
	// player passes turn to computer
	public void stay()
	{
		
	}

	// leave current table and return to menu
	public void leaveTableToMenu()
	{
		this.gameView.dispose();
		new MenuController();
	}

	// cardStack is initialized; bet is placed
	public void initializeGame()
	{
		if(this.getBetInput())
			this.playGame();
	}

	// game starts after bet is placed
	public void playGame()
	{
		gameView.setTextStatusBar("Einsatz: " + playerBet + "€");
		this.initializeCardStack();
		this.generateStartCards();
		gameView.appendTextStatusBar("Karten werden ausgeteilt.");
		gameView.updateUI();
		this.bitteWarten(2000);
		this.printDealerCards();
		this.updateDealerCardSum();
		gameView.updateUI();
		this.bitteWarten(2000);
		this.printPlayerCards();
		this.updatePlayerCardSum();
		if(checkPlayerCardSum() == 1)
			gameView.setTextStatusBar("Möchten Sie noch eine Karte?");
		else
			if(checkPlayerCardSum() == 2)
				this.playerLose();
			else
				{
					gameView.setTextStatusBar("Sie können keine weitere Karte ziehen. Dealer ist am Zug.");
					this.passTurn();
				}
				
	}
	
	public void bitteWarten(int i)
	{
		try
		{
			Thread.sleep(i);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	// calculates the sum of all card values in the dealerStack
	public void updateDealerCardSum()
	{
		int cardSumDealer = 0;
		
		for (int i = 0; i < dealerCount; i++)
		{
			cardSumDealer = cardSumDealer + dealerStack[i].getValue();
		}
		
		String cardSumDealerOut = Integer.toString(cardSumDealer);
		gameView.setCardSumDealer(cardSumDealerOut);
	}
	
	// calculates the sum of all card values in the playerStack
	public void updatePlayerCardSum()
	{
		int cardSumPlayer = 0;
		
		for (int i = 0; i < playerCount; i++)
		{
			cardSumPlayer = cardSumPlayer + playerStack[i].getValue();
		}
		
		outerloop:
		while(cardSumPlayer > 21)
		{
			if(this.downGradeAce())
			{
				cardSumPlayer = (cardSumPlayer - 10);
			}
			else
			{
				break outerloop;
			}
		}
		
		String cardSumPlayerOut = Integer.toString(cardSumPlayer);
		gameView.setCardSumPlayer(cardSumPlayerOut);
	}
	
	// prints player cards
	public void printPlayerCards()
	{
		String playerCardsOutput = "";
		if(playerCount <= 6)
		{
			for (int i = 0; i < playerCount; i++)
			{
				playerCardsOutput = playerCardsOutput + "Karte " + (i + 1) + ": " + (playerStack[i].getName()) + "\n";
			}
		}
		else
		{
			for (int i = 0; i < playerCount; i++)
			{
				innerloop:
				for(int k = 0; k < 2; k++)
				{
					try
					{	
						playerCardsOutput = playerCardsOutput + "Karte " + (i + 1) + ": " + (playerStack[i].getName()) + "\t";
						i++;
					}
					catch(NullPointerException n)
					{
						break innerloop;
					}
				}
				i--;
				playerCardsOutput = playerCardsOutput + "\n";
			}
		}
		gameView.setPlayerOut(playerCardsOutput);
	}

	// prints dealer cards
	public void printDealerCards()
	{
		String dealerCardsOutput = "";
		if(dealerCount <= 6)
		{
			for (int i = 0; i < dealerCount; i++)
			{
				dealerCardsOutput = dealerCardsOutput + "Karte " + (i + 1) + ": " + (dealerStack[i].getName()) + "\n";
			}
		}
		else
		{
			for (int i = 0; i < dealerCount; i++)
			{
				innerloop:
				for(int k = 0; k < 2; k++)
				{
					try
					{	
						dealerCardsOutput = dealerCardsOutput + "Karte " + (i + 1) + ": " + (dealerStack[i].getName()) + "\t";
						i++;
					}
					catch(NullPointerException n)
					{
						break innerloop;
					}
				}
				i--;
				dealerCardsOutput = dealerCardsOutput + "\n";
			}
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

	// generates new card in the playerStack
	public void generateNewPlayerCard()
	{
		for (int i = 0; i < 1; i++)
		{
			int randomPos = generateRandomNumber();

			while (beginCardStack[randomPos].getCount() >= 5)
			{
				randomPos = generateRandomNumber();
			}

			playerStack[playerCount] = beginCardStack[randomPos];
			playerCount++;
		}	
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
