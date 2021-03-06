package Views;

import javax.swing.JFrame;

import Controller.GameController;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class GameView extends JFrame
{
	
	// declaration--------------------------------
	
	private static final long serialVersionUID = 4737685724946054014L;
	private JTextField	betInput;
	private JTextArea	statusBar, betStatus, playerOut, dealerOut, cardSumDealer, cardSumPlayer, chipcountStatus;
	private JButton		btnLeaveTable, btnPlaceBet, btnStay, btnHit, btnPlayAgain;

	// constructor--------------------------------

	public GameView(GameController controller, String user, String rank)
	{
		setTitle(user + " (" + rank + ") ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setMinimumSize(new Dimension(4, 22));
		setMinimumSize(new Dimension(750, 530));
		getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(258, 0, 8, 662);
		separator.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(separator);

		JLabel lblChipcount = new JLabel("Chipcount:");
		lblChipcount.setBounds(26, 57, 72, 14);
		getContentPane().add(lblChipcount);

		JLabel lblEinsatz = new JLabel("Wetteinsatz:");
		lblEinsatz.setBounds(26, 120, 72, 14);
		getContentPane().add(lblEinsatz);

		btnPlaceBet = new JButton("Place Bet");
		btnPlaceBet.setBounds(159, 171, 89, 23);
		btnPlaceBet.setActionCommand("placeBet");
		btnPlaceBet.addActionListener(controller);
		getContentPane().add(btnPlaceBet);

		btnLeaveTable = new JButton("Abmelden");
		btnLeaveTable.setBounds(9, 443, 239, 23);
		btnLeaveTable.setActionCommand("leaveTable");
		btnLeaveTable.addActionListener(controller);
		getContentPane().add(btnLeaveTable);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(258, 171, 481, 14);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(258, 265, 481, 14);
		getContentPane().add(separator_2);

		btnStay = new JButton("Stay");
		btnStay.setActionCommand("stay");
		btnStay.addActionListener(controller);
		btnStay.setBounds(487, 421, 89, 23);
		btnStay.setEnabled(false);
		getContentPane().add(btnStay);

		btnHit = new JButton("Hit");
		btnHit.setActionCommand("hit");
		btnHit.addActionListener(controller);
		btnHit.setBounds(586, 421, 89, 23);
		btnHit.setEnabled(false);
		getContentPane().add(btnHit);

		betInput = new JTextField();
		betInput.setBounds(162, 117, 86, 20);
		getContentPane().add(betInput);
		betInput.setColumns(10);

		statusBar = new JTextArea();
		statusBar.setFocusable(false);
		statusBar.setBounds(305, 217, 400, 25);
		statusBar.setEditable(false);
		getContentPane().add(statusBar);

		JLabel lblSpielstatus = new JLabel("Spielstatus:");
		lblSpielstatus.setBounds(305, 192, 116, 14);
		getContentPane().add(lblSpielstatus);

		JLabel lblDealer = new JLabel("Dealer:");
		lblDealer.setBounds(324, 22, 59, 14);
		getContentPane().add(lblDealer);

		JLabel lblPlayer = new JLabel("Ihre Karten:");
		lblPlayer.setBounds(324, 285, 66, 14);
		getContentPane().add(lblPlayer);

		betStatus = new JTextArea();
		betStatus.setFocusable(false);
		betStatus.setBounds(176, 280, 72, 25);
		betStatus.setEditable(false);
		getContentPane().add(betStatus);

		JLabel lblBetPlaced = new JLabel("Wetteinsatz:");
		lblBetPlaced.setBounds(26, 285, 72, 14);
		getContentPane().add(lblBetPlaced);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(233, 329, -80, 67);
		getContentPane().add(textArea);

		chipcountStatus = new JTextArea();
		chipcountStatus.setFocusable(false);
		chipcountStatus.setEditable(false);
		chipcountStatus.setBounds(176, 47, 72, 25);
		getContentPane().add(chipcountStatus);

		JLabel lblSummePlayer = new JLabel("Summe Player:");
		lblSummePlayer.setBounds(26, 382, 89, 14);
		getContentPane().add(lblSummePlayer);

		JLabel lblSummeDealer = new JLabel("Summe Dealer:");
		lblSummeDealer.setBounds(26, 334, 89, 14);
		getContentPane().add(lblSummeDealer);

		cardSumPlayer = new JTextArea();
		cardSumPlayer.setFocusable(false);
		cardSumPlayer.setEditable(false);
		cardSumPlayer.setBounds(176, 377, 72, 25);
		getContentPane().add(cardSumPlayer);

		cardSumDealer = new JTextArea();
		cardSumDealer.setFocusable(false);
		cardSumDealer.setEditable(false);
		cardSumDealer.setBounds(176, 329, 72, 25);
		getContentPane().add(cardSumDealer);

		playerOut = new JTextArea();
		playerOut.setFocusable(false);
		playerOut.setAutoscrolls(false);
		playerOut.setEditable(false);
		playerOut.setBounds(324, 310, 351, 100);
		getContentPane().add(playerOut);

		dealerOut = new JTextArea();
		dealerOut.setFocusable(false);
		dealerOut.setEditable(false);
		dealerOut.setBounds(324, 52, 351, 100);
		getContentPane().add(dealerOut);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 265, 266, 2);
		getContentPane().add(separator_3);
		
		btnPlayAgain = new JButton("Neues Spiel");
		btnPlayAgain.setEnabled(false);
		btnPlayAgain.setActionCommand("playAgain");
		btnPlayAgain.addActionListener(controller);
		btnPlayAgain.setBounds(10, 218, 238, 23);
		getContentPane().add(btnPlayAgain);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.setActionCommand("info");
		btnInfo.addActionListener(controller);
		btnInfo.setBounds(650, 468, 89, 23);
		getContentPane().add(btnInfo);
		
		this.centerWindow();
	}

	// methods-----------------------------------

	// verifys if input in betInput is a valid int
	public boolean verifyBet()
	{
		try
		{
			int bet = Integer.parseInt(betInput.getText());
			if (bet > 0)
				return true;
			else
				return false;
		}
		catch (NumberFormatException e)
		{
			return false;
		}

	}

	// updates text in statusBar
	public void updateUI()
	{
		this.update(getGraphics());
	}

	// centers the window relative to the screen resolution
	public void centerWindow()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
	
	// set-block--------------------------------
	
	// updates the textfield chipcountStatus with the new chipcount
	public void updateChipcount(int chipC)
	{
		String chipCount = Integer.toString(chipC);
		chipcountStatus.setText(chipCount);
	}
	
	// resets gameView textFields to default
	public void clearTextFields()
	{
		betInput.setText("");
		cardSumPlayer.setText("");
		cardSumDealer.setText("");
		dealerOut.setText("");
		playerOut.setText("");
		statusBar.setText("Bitte t�tigen Sie ihren Wetteinsatz.");
		betStatus.setText("");
	}
	
	// adds given text to the status bar
	public void appendTextStatusBar(String statusText)
	{
		statusBar.append(" ---------- " + statusText);
	}
	
	// disables btnSetBet
	public void disableBtnSetBet()
	{
		btnPlaceBet.setEnabled(false);
	}
	
	// enables btnSetBet
	public void enableBtnSetBet()
	{
		btnPlaceBet.setEnabled(true);
	}	

	// disables textfield betInput
	public void disableBetInput()
	{
		betInput.setEnabled(false);
	}
	
	// displays given text in the textfield betInput
	public void setTextBetInput(String paramText)
	{
		betInput.setText(paramText);
	}
	
	// enables textfield betInpu
	public void enableBetInput()
	{
		betInput.setEnabled(true);
	}
	
	// disables btnHit and Stay
	public void disableHitStay()
	{
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
	}
	
	// enables hit and stay btn
	public void enableHitStay()
	{
		btnStay.setEnabled(true);
		btnHit.setEnabled(true);
	}
	
	// enables btnPlayAgain
	public void enablePlayAgain()
	{
		btnPlayAgain.setEnabled(true);
	}

	// disables btnPlayAgain
	public void disablePlayAgain()
	{
		btnPlayAgain.setEnabled(false);
	}
	
	// prints player cards
	public void setPlayerOut(String playerOutInput)
	{
		playerOut.setText(playerOutInput);
	}

	// prints dealer cards
	public void setDealerOut(String dealerOutInput)
	{
		dealerOut.setText(dealerOutInput);
	}
	
	// overwrites text in statusBar with new text
	public void setTextStatusBar(String statusText)
	{
		statusBar.setText(statusText);
	}
	
	// prints betStatus
	public void setBetStatus(String betStatusInput)
	{
		betStatus.setText(betStatusInput + "�");
	}

	// prints player card sum
	public void setCardSumPlayer(String sumInput)
	{
		cardSumPlayer.setText(sumInput);
	}

	// prints dealer card sum
	public void setCardSumDealer(String sumInput)
	{
		cardSumDealer.setText(sumInput);
	}
	
	public void setNewTitle(String paramTitle) {
		setTitle(paramTitle);
	}
	
	// get-block--------------------------------
	
	// returns card sum of player
	public String getCardSumPlayer()
	{
		return cardSumPlayer.getText();
	}

	// returns card sum of dealer
	public String getCardSumDealer()
	{
		return cardSumDealer.getText();
	}

	// returns betInput
	public int getBet()
	{
		return Integer.parseInt(betInput.getText());
	}
}