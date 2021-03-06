package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;

import Main.MainPage;
import Models.CardModel;
import Views.GameView;

public class GameController implements ActionListener
{
	
	// declaration--------------------------------
	
	private GameView gameView;
	private CardModel[] beginCardStack = new CardModel[52],
	        playerStack = new CardModel[26], dealerStack = new CardModel[26];
	private static int chipCount;
	private int	playerBet;
	private int	playerCount = 0;
	private int	dealerCount = 0;
	private String username;
	
	// constructor--------------------------------
	
	public GameController(int chipC, String username)
	{
		this.gameView = new GameView(this, username,
		        MainPage.xmlController.getRankFromXML(username));
		this.gameView.setVisible(true);
		this.gameView.setTextStatusBar("Bitte t�tigen Sie ihren Wetteinsatz!");
		this.chipCount = chipC;
		this.gameView.updateChipcount(chipCount);
		this.username = username;
	}
	
	// methods-----------------------------------
	
	// button actionCommands
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("placeBet")) {
			try {
				this.initializeGame();
			} catch (TransformerException e1) {
				e1.printStackTrace();
			}
		} else
			if (e.getActionCommand().equals("hit")) {
				try {
					this.hit();
				} catch (TransformerException e1) {
					e1.printStackTrace();
				}
			} else
				if (e.getActionCommand().equals("stay")) {
					try {
						this.stay();
					} catch (TransformerException e1) {
						e1.printStackTrace();
					}
				} else
					if (e.getActionCommand().equals("playAgain")) {
						this.resetTable();
					} else
						if (e.getActionCommand().equals("leaveTable")) {
							int dialogResult = JOptionPane
							        .showConfirmDialog(
							                null,
							                "M�chten Sie das Spiel wirklich verlassen?",
							                "Achtung!",
							                JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								try {
									this.leaveTableToMenu();
								} catch (TransformerException e1) {
									e1.printStackTrace();
								}
							}
						} else
							if (e.getActionCommand().equals("info")) {
								JOptionPane
								        .showMessageDialog(
								                null,
								                "Sie erreichen das n�chste Level bei "
								                        + MainPage.xmlController.calculateTargetForNextRank(username)
								                        + " Chips. \nSie ben�tigen noch "
								                        + MainPage.xmlController.calculateDifferenceToNextrank(username, MainPage.xmlController.calculateTargetForNextRank(username))
								                        + " Chips bis zur n�chsten Stufe.");
							}
	}
	
	// verifies bet; if true starts game
	public void initializeGame() throws TransformerException {
		if (this.getBetInput())
			this.playGame();
	}
	
	// game starts after bet is placed
	public void playGame() throws TransformerException {
		gameView.setTextStatusBar("Einsatz: " + playerBet + "�");
		this.initializeCardStack();
		this.generateStartCards();
		gameView.appendTextStatusBar("Karten werden ausgeteilt.");
		gameView.updateUI();
		this.waitTimer(2000);
		this.printDealerCards();
		this.updateDealerCardSum();
		gameView.updateUI();
		this.waitTimer(2000);
		this.printPlayerCards();
		this.updatePlayerCardSum();
		gameView.enableHitStay();
		this.continueGamePlayer();
	}
	
	// auto plays game for dealer
	public void dealerTurn() throws TransformerException {
		gameView.setTextStatusBar("Dealer zieht eine Karte.");
		this.generateNewDealerCard();
		gameView.updateUI();
		this.waitTimer(2000);
		this.printDealerCards();
		this.updateDealerCardSum();
		this.continueGameDealer();
	}
	
	// player continues game with new card
	public void hit() throws TransformerException {
		this.generateNewPlayerCard();
		gameView.setTextStatusBar("Sie erhalten eine weitere Karte.");
		gameView.updateUI();
		this.waitTimer(2000);
		this.printPlayerCards();
		this.updatePlayerCardSum();
		this.continueGamePlayer();
	}
	
	// player passes turn to computer
	public void stay() throws TransformerException {
		gameView.disableHitStay();
		gameView.setTextStatusBar("Dealer ist am Zug.");
		this.dealerTurn();
	}
	
	// leave current table and return to menu
	public void leaveTableToMenu() throws TransformerException {
		MainPage.xmlController.saveChipcountToXML(username, chipCount);
		this.gameView.dispose();
		new MenuController();
	}
	
	// passes turn to computer
	public void autoPassTurn() throws TransformerException {
		gameView.disableHitStay();
		gameView.setTextStatusBar("Sie k�nnen keine weitere Karte ziehen. Dealer ist am Zug.");
		this.dealerTurn();
	}
	
	// resets all variables to default and clears gameView
	public void resetTable() {
		playerBet = 0;
		playerCount = 0;
		dealerCount = 0;
		beginCardStack = new CardModel[52];
		playerStack = new CardModel[26];
		dealerStack = new CardModel[26];
		gameView.enableBtnSetBet();
		gameView.enableBetInput();
		gameView.disablePlayAgain();
		gameView.clearTextFields();
		
	}
	
	// initialize beginCardStack with 52 cards
	public void initializeCardStack() {
		String color = "";
		int index = 0;
		for (int iColor = 0; iColor < 4; iColor++) {
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
			for (int iNumber = 2; iNumber < 11; iNumber++) {
				beginCardStack[index] = new CardModel(color + " " + iNumber,
				        iNumber);
				index++;
			}
			beginCardStack[index] = new CardModel(color + " Ass", 11);
			index++;
			beginCardStack[index] = new CardModel(color + " K�nig", 10);
			index++;
			beginCardStack[index] = new CardModel(color + " Dame", 10);
			index++;
			beginCardStack[index] = new CardModel(color + " Bube", 10);
			index++;
		}
	}
	
	// generates random cards at gamestart
	public void generateStartCards() {
		for (int i = 0; i < 2; i++) {
			int randomPos = generateRandomNumber();
			beginCardStack[randomPos].setCount((beginCardStack[randomPos]
			        .getCount()) + 1);
			playerStack[playerCount] = beginCardStack[randomPos];
			playerCount++;
		}
		int randomPos = generateRandomNumber();
		beginCardStack[randomPos].setCount((beginCardStack[randomPos]
		        .getCount()) + 1);
		dealerStack[dealerCount] = beginCardStack[randomPos];
		dealerCount++;
	}
	
	// generates new card in the playerStack
	public void generateNewPlayerCard() {
		for (int i = 0; i < 1; i++) {
			int randomPos = generateRandomNumber();
			while (beginCardStack[randomPos].getCount() >= 5) {
				randomPos = generateRandomNumber();
			}
			beginCardStack[randomPos].setCount((beginCardStack[randomPos]
			        .getCount()) + 1);
			playerStack[playerCount] = beginCardStack[randomPos];
			playerCount++;
		}
	}
	
	// generates new card in the dealerStack
	public void generateNewDealerCard() {
		for (int i = 0; i < 1; i++) {
			int randomPos = generateRandomNumber();
			while (beginCardStack[randomPos].getCount() >= 5) {
				randomPos = generateRandomNumber();
			}
			beginCardStack[randomPos].setCount((beginCardStack[randomPos]
			        .getCount()) + 1);
			dealerStack[dealerCount] = beginCardStack[randomPos];
			dealerCount++;
		}
	}
	
	// calculates the sum of all card values in the dealerStack; links to
	// downgradeAceDealer()
	public void updateDealerCardSum() {
		int cardSumDealer = 0;
		
		for (int i = 0; i < dealerCount; i++) {
			cardSumDealer = cardSumDealer + dealerStack[i].getValue();
		}
		
		outerloop: while (cardSumDealer > 21) {
			if (this.downgradeAceDealer()) {
				cardSumDealer = (cardSumDealer - 10);
			} else {
				break outerloop;
			}
		}
		
		String cardSumDealerOut = Integer.toString(cardSumDealer);
		gameView.setCardSumDealer(cardSumDealerOut);
	}
	
	// calculates the sum of all card values in the playerStack; links to
	// downgradeAcePlayer()
	public void updatePlayerCardSum() {
		int cardSumPlayer = 0;
		
		for (int i = 0; i < playerCount; i++) {
			cardSumPlayer = cardSumPlayer + playerStack[i].getValue();
		}
		
		outerloop: while (cardSumPlayer > 21) {
			if (this.downgradeAcePlayer()) {
				cardSumPlayer = (cardSumPlayer - 10);
			} else {
				break outerloop;
			}
		}
		
		String cardSumPlayerOut = Integer.toString(cardSumPlayer);
		gameView.setCardSumPlayer(cardSumPlayerOut);
	}
	
	// prints player cards
	public void printPlayerCards() {
		String playerCardsOutput = "";
		if (playerCount <= 6) {
			for (int i = 0; i < playerCount; i++) {
				playerCardsOutput = playerCardsOutput + "Karte " + (i + 1)
				        + ": " + (playerStack[i].getName()) + "\n";
			}
		} else {
			for (int i = 0; i < playerCount; i++) {
				innerloop: for (int k = 0; k < 2; k++) {
					try {
						playerCardsOutput = playerCardsOutput + "Karte "
						        + (i + 1) + ": " + (playerStack[i].getName())
						        + "\t";
						i++;
					} catch (NullPointerException n) {
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
	public void printDealerCards() {
		String dealerCardsOutput = "";
		if (dealerCount <= 6) {
			for (int i = 0; i < dealerCount; i++) {
				dealerCardsOutput = dealerCardsOutput + "Karte " + (i + 1)
				        + ": " + (dealerStack[i].getName()) + "\n";
			}
		} else {
			for (int i = 0; i < dealerCount; i++) {
				innerloop: for (int k = 0; k < 2; k++) {
					try {
						dealerCardsOutput = dealerCardsOutput + "Karte "
						        + (i + 1) + ": " + (dealerStack[i].getName())
						        + "\t";
						i++;
					} catch (NullPointerException n) {
						break innerloop;
					}
				}
				i--;
				dealerCardsOutput = dealerCardsOutput + "\n";
			}
		}
		gameView.setDealerOut(dealerCardsOutput);
	}
	
	// verifies if player is above 21 or equal to it or below
	public int checkPlayerCardSum() {
		int playerCardSum = Integer.parseInt(gameView.getCardSumPlayer());
		
		if (playerCardSum == 21) {
			return 3;
		} else
			if (playerCardSum > 21) {
				return 2;
			} else
				return 1;
		
	}
	
	// verifies if dealer is equal or above 17; euqal or below 16; equal or
	// above 21
	public int checkDealerCardSum() {
		int dealerCardSum = Integer.parseInt(gameView.getCardSumDealer());
		
		if (dealerCardSum == 21) {
			return 3;
		} else
			if (dealerCardSum > 21) {
				return 2;
			} else
				if (dealerCardSum >= 17)
					return 1;
				else
					return 0;
		
	}
	
	// calculates who won
	public void calculateWinner() throws TransformerException {
		int dealerCardSum = Integer.parseInt(gameView.getCardSumDealer());
		int playerCardSum = Integer.parseInt(gameView.getCardSumPlayer());
		
		if (dealerCardSum > playerCardSum)
			this.playerLose();
		else
			if (dealerCardSum == playerCardSum)
				this.gameDraw();
			else
				this.dealerLose();
	}
	
	// downgrades one ace at a time in playerStack
	public boolean downgradeAcePlayer() {
		int point = 0;
		
		outerloop: while (point == 0) {
			for (int i = 0; i < playerCount; i++) {
				if (playerStack[i].getValue() == 11) {
					playerStack[i].setValue(1);
					point = 1;
				}
			}
			break outerloop;
		}
		
		if (point == 1) {
			this.updatePlayerCardSum();
			return true;
		} else
			return false;
	}
	
	// downgrades one ace at a time in dealerStack
	public boolean downgradeAceDealer() {
		int point = 0;
		
		outerloop: while (point == 0) {
			for (int i = 0; i < dealerCount; i++) {
				if (dealerStack[i].getValue() == 11) {
					dealerStack[i].setValue(1);
					point = 1;
				}
			}
			break outerloop;
		}
		
		if (point == 1) {
			this.updateDealerCardSum();
			return true;
		} else
			return false;
	}
	
	// ends game after player loses
	public void playerLose() throws TransformerException {
		gameView.disableHitStay();
		gameView.enablePlayAgain();
		gameView.setTextStatusBar("Sie haben leider verloren. Ihr Wetteinsatz wird eingezogen.");
		if (chipCount == 0) {
			gameView.disablePlayAgain();
		}
	}
	
	// ends game after dealer loses
	public void dealerLose() throws TransformerException {
		int rank = Integer.parseInt(MainPage.xmlController.getRankFromXML(username));
		gameView.enablePlayAgain();
		int reward = ((playerBet * 2) + (playerBet / 100 * rank) + (rank*3));
		gameView.setTextStatusBar("Sie haben gewonnen. Gewinn wird ausgezahlt.");
		gameView.updateUI();
		this.waitTimer(2000);
		gameView.setTextStatusBar("Ihrem Konto wurden " + reward
		        + " � gutgeschrieben!");
		chipCount += reward;
		gameView.updateChipcount(chipCount);
		calculateRank(chipCount);
	}
	
	// ends game after draw
	public void gameDraw() throws TransformerException {
		chipCount += playerBet;
		gameView.updateChipcount(chipCount);
		gameView.enablePlayAgain();
		gameView.setTextStatusBar("Das Spiel endet unentschieden. Sie erhalten ihren Einsatz zur�ck!");
	}
	
	// decides in which way the game goes on in player case
	public void continueGamePlayer() throws TransformerException {
		if (checkPlayerCardSum() == 1)
			gameView.setTextStatusBar("M�chten Sie noch eine Karte?");
		else
			if (checkPlayerCardSum() == 2)
				this.playerLose();
			else {
				this.autoPassTurn();
			}
	}
	
	// decides in which way the game goes on in dealer case
	public void continueGameDealer() throws TransformerException {
		if (checkDealerCardSum() == 0)
			this.dealerTurn();
		else
			if (checkDealerCardSum() == 1) {
				gameView.setTextStatusBar("Dealer beendet seinen Zug.");
				gameView.updateUI();
				this.waitTimer(2000);
				this.calculateWinner();
			} else
				if (checkDealerCardSum() == 2) {
					this.dealerLose();
				} else
					this.calculateWinner();
	}
	
	// calculates the playerRank depending on his current chipcount
	public void calculateRank(int count) throws TransformerException {
		
//		int lvlUp = 0;
//		while (chipCount >= (Math.pow(2, ((Integer.parseInt(MainPage.xmlController.getRankFromXML(username))) + 1))) * 100) {
//			int rang = Integer.parseInt(MainPage.xmlController.getRankFromXML(username));
//			
//			MainPage.xmlController.saveNewRank(username,
//			        Integer.toString(rang + 1));
//			gameView.setNewTitle(username + " (" + (rang + 1) + ") ");
//			
//			lvlUp++;
//		}
//		
//		if (lvlUp != 0) {
//			JOptionPane.showMessageDialog(null, "Sie sind " + lvlUp
//			        + " Level aufgestiegen!");
//		}
//		
		
		
		 int rang = 0;
		 double requirement = 200;
		
		 while (count > requirement) {
		 rang++;
		 requirement = requirement * 1.5;
		 }
		 if (rang > Integer.parseInt(MainPage.xmlController
		.getRankFromXML(username))) {
				JOptionPane.showMessageDialog(null, "Sie sind " + (rang - (Integer.parseInt(MainPage.xmlController
						.getRankFromXML(username))))
						        + " Level aufgestiegen!");
		MainPage.xmlController.saveNewRank(username, Integer.toString(rang));
		gameView.setNewTitle(username + " (" + rang + ") ");

		}
	}
	
	// generates random number for card selction
	public int generateRandomNumber() {
		Random generator = new Random();
		int randomNmbr = generator.nextInt(51);
		return randomNmbr;
	}
	
	// wait method for simulation purpose
	public void waitTimer(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
		}
	}
	
	// get-block--------------------------------
	
	// gets the bet placed by the player
	public boolean getBetInput() {
		if (gameView.verifyBet() && (gameView.getBet()) <= chipCount) {
			playerBet = gameView.getBet();
			chipCount -= playerBet;
			gameView.updateChipcount(chipCount);
			gameView.setBetStatus(Integer.toString(playerBet));
			gameView.setTextBetInput("");
			gameView.disableBtnSetBet();
			gameView.disableBetInput();
			
			return true;
		} else {
			gameView.setTextStatusBar("Ung�ltiger Wetteinsatz!");
			return false;
		}
	}
	
	public static int getChipcount()
	{
		return chipCount;
	}
}