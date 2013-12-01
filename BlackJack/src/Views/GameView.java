package Views;

import javax.swing.JFrame;

import Controller.GameController;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSlider;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;

import java.awt.Component;

import javax.swing.Box;

import java.awt.TextArea;
import java.awt.Canvas;

import javax.swing.JMenuBar;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.JTree;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameView extends JFrame
{
	private JTextField	betInput;
	private JTextArea statusBar, betStatus, playerOut, dealerOut;
	private JButton btnLeaveTable, btnPlaceBet, btnStay, btnHit;

	// constructor----------------------------------------------------------------------------------

	public GameView(GameController controller)
	{
		getContentPane().setMinimumSize(new Dimension(4, 22));
		setMinimumSize(new Dimension(750, 530));
		getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(258, 0, 8, 662);
		separator.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(separator);

		JLabel lblHeadline = new JLabel("\u00DCberschrift f\u00FCr dieses Fenster");
		lblHeadline.setBounds(49, 11, 151, 25);
		getContentPane().add(lblHeadline);

		JLabel lblChipcount = new JLabel("Chipcount:");
		lblChipcount.setBounds(26, 57, 72, 14);
		getContentPane().add(lblChipcount);

		JLabel lblEinsatz = new JLabel("Bet:");
		lblEinsatz.setBounds(26, 120, 59, 14);
		getContentPane().add(lblEinsatz);

		btnPlaceBet = new JButton("Place Bet");
		btnPlaceBet.setBounds(159, 171, 89, 23);
		btnPlaceBet.setActionCommand("placeBet");
		btnPlaceBet.addActionListener(controller);
		getContentPane().add(btnPlaceBet);

		btnLeaveTable = new JButton("Leave table");
		btnLeaveTable.setBounds(26, 218, 222, 23);
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
		btnStay.setBounds(487, 421, 89, 23);
		btnStay.setEnabled(false);
		getContentPane().add(btnStay);

		btnHit = new JButton("Hit");
		btnHit.setBounds(586, 421, 89, 23);
		btnHit.setEnabled(false);
		getContentPane().add(btnHit);

		betInput = new JTextField();
		betInput.setBounds(162, 117, 86, 20);
		getContentPane().add(betInput);
		betInput.setColumns(10);

		statusBar = new JTextArea();
		statusBar.setBounds(305, 217, 400, 25);
		statusBar.setEditable(false);
		getContentPane().add(statusBar);
		
		JLabel lblSpielstatus = new JLabel("Spielstatus:");
		lblSpielstatus.setBounds(305, 192, 116, 14);
		getContentPane().add(lblSpielstatus);
		
		JLabel lblDealer = new JLabel("Dealer:");
		lblDealer.setBounds(324, 22, 59, 14);
		getContentPane().add(lblDealer);
		
		JLabel lblPlayer = new JLabel("Player:");
		lblPlayer.setBounds(324, 285, 66, 14);
		getContentPane().add(lblPlayer);
		
		betStatus = new JTextArea();
		betStatus.setBounds(165, 280, 72, 25);
		betStatus.setEditable(false);
		getContentPane().add(betStatus);
		
		JLabel lblBetPlaced = new JLabel("Bet placed:");
		lblBetPlaced.setBounds(26, 285, 72, 14);
		getContentPane().add(lblBetPlaced);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(233, 329, -80, 67);
		getContentPane().add(textArea);
		
		JTextArea chipcountStatus = new JTextArea();
		chipcountStatus.setEditable(false);
		chipcountStatus.setBounds(176, 47, 72, 25);
		getContentPane().add(chipcountStatus);
		
		JLabel lblSummePlayer = new JLabel("Summe Player:");
		lblSummePlayer.setBounds(26, 338, 89, 14);
		getContentPane().add(lblSummePlayer);
		
		JLabel lblSummeDealer = new JLabel("Summe Dealer:");
		lblSummeDealer.setBounds(26, 382, 89, 14);
		getContentPane().add(lblSummeDealer);
		
		JTextArea cardSumPlayer = new JTextArea();
		cardSumPlayer.setEditable(false);
		cardSumPlayer.setBounds(165, 329, 72, 25);
		getContentPane().add(cardSumPlayer);
		
		JTextArea cardSumDealer = new JTextArea();
		cardSumDealer.setEditable(false);
		cardSumDealer.setBounds(165, 377, 72, 25);
		getContentPane().add(cardSumDealer);
		
		playerOut = new JTextArea();
		playerOut.setEditable(false);
		playerOut.setBounds(324, 310, 351, 100);
		getContentPane().add(playerOut);
		
		dealerOut = new JTextArea();
		dealerOut.setEditable(false);
		dealerOut.setBounds(324, 52, 351, 100);
		getContentPane().add(dealerOut);

	}

	// methods--------------------------------------------------------------------

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
	
	// returns betInput
	public int getBet()									
	{
		return Integer.parseInt(betInput.getText());
	}
	
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
	
	// overwrites text in statusBar with new text
	public void setTextStatusBar(String statusText)
	{
		statusBar.setText(statusText);
	}
	
	// disables btnSetBet
	public void disableBtnSetBet()
	{
		btnPlaceBet.setEnabled(false);
	}
	
	// enables hit and stay btn
	public void activateHitStay()
	{
		btnStay.setEnabled(true);
		btnHit.setEnabled(true);
	}
	
	// prints betStatus
	public void setBetStatus(String betStatusInput)
	{
		betStatus.setText(betStatusInput);
	}
}
