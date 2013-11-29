package Views;

import javax.swing.JFrame;

import Controller.GameController;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GameView extends JFrame
{
	// declaration---------------------------------------------------------------------------------
	
	private JTextField textField;
	
	// constructor----------------------------------------------------------------------------------
	
	public GameView(GameController controller) {
		setMinimumSize(new Dimension(500, 400));
		setTitle("Game Black Jack");
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(20, 93, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEinsatz = new JLabel("Einsatz:");
		lblEinsatz.setBounds(20, 75, 46, 14);
		getContentPane().add(lblEinsatz);
		
		JLabel lblVerfgbareChips = new JLabel("Verf\u00FCgbare Chips");
		lblVerfgbareChips.setBounds(20, 50, 86, 14);
		getContentPane().add(lblVerfgbareChips);
		
		JLabel lblAnzahlChips = new JLabel("Anzahl Chips");
		lblAnzahlChips.setBounds(119, 50, 86, 14);
		getContentPane().add(lblAnzahlChips);
		
		JButton btnSetzen = new JButton("Setzen");
		btnSetzen.setBounds(116, 92, 89, 23);
		getContentPane().add(btnSetzen);
		
		JTextArea txtrGesetztegewonneneverloreneChips = new JTextArea();
		txtrGesetztegewonneneverloreneChips.setEditable(false);
		txtrGesetztegewonneneverloreneChips.setText("ges.\r\ngew.\r\nverl.\r\n\t Chips");
		txtrGesetztegewonneneverloreneChips.setBounds(263, 45, 182, 76);
		getContentPane().add(txtrGesetztegewonneneverloreneChips);
		
		JTextArea txtrkarteAss = new JTextArea();
		txtrkarteAss.setEditable(false);
		txtrkarteAss.setText("1.Karte: Ass\r\n2.Karte: 7\r\n\r\nSumme: 18");
		txtrkarteAss.setBounds(20, 167, 185, 131);
		getContentPane().add(txtrkarteAss);
		
		JLabel lblKartenbersicht = new JLabel("Karten\u00FCbersicht Spieler:");
		lblKartenbersicht.setBounds(20, 142, 115, 14);
		getContentPane().add(lblKartenbersicht);
		
		JButton btnStay = new JButton("stay");
		btnStay.setBounds(17, 309, 89, 23);
		getContentPane().add(btnStay);
		
		JButton btnHit = new JButton("hit");
		btnHit.setBounds(119, 309, 89, 23);
		getContentPane().add(btnHit);
		
		JTextArea txtrKarteKnig = new JTextArea();
		txtrKarteKnig.setEditable(false);
		txtrKarteKnig.setText("Karte 1: K\u00F6nig\r\nKarte 2: 8\r\n\r\nSumme: 18");
		txtrKarteKnig.setBounds(263, 167, 182, 131);
		getContentPane().add(txtrKarteKnig);
		
		JLabel lblKartenbersichtBank = new JLabel("Karten\u00FCbersicht Bank:");
		lblKartenbersichtBank.setBounds(260, 142, 106, 14);
		getContentPane().add(lblKartenbersichtBank);
		
		JTextArea txtrGamestatusbar = new JTextArea();
		txtrGamestatusbar.setEditable(false);
		txtrGamestatusbar.setText("unentschieden");
		txtrGamestatusbar.setBounds(263, 308, 182, 20);
		getContentPane().add(txtrGamestatusbar);
		
		
	}
}
