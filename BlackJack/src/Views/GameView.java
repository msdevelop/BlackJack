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

public class GameView extends JFrame
{
	
	// constructor----------------------------------------------------------------------------------
	
	public GameView(GameController controller) {
		setMinimumSize(new Dimension(750, 530));
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(258, 0, 8, 662);
		getContentPane().add(separator);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textPane.setText("100");
		textPane.setBounds(165, 47, 72, 25);
		getContentPane().add(textPane);
		
		JLabel lblHeadline = new JLabel("\u00DCberschrift f\u00FCr dieses Fenster");
		lblHeadline.setBounds(49, 11, 151, 25);
		getContentPane().add(lblHeadline);
		
		JLabel lblChipcount = new JLabel("Chipcount:");
		lblChipcount.setBounds(26, 57, 72, 14);
		getContentPane().add(lblChipcount);
		
		JLabel lblEinsatz = new JLabel("Bet:");
		lblEinsatz.setBounds(26, 120, 59, 14);
		getContentPane().add(lblEinsatz);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(165, 114, 72, 20);
		getContentPane().add(textPane_1);
		
		JButton btnPlaceBet = new JButton("Place Bet");
		btnPlaceBet.setBounds(159, 171, 89, 23);
		getContentPane().add(btnPlaceBet);
		
		JButton btnNewButton = new JButton("Leave table");
		btnNewButton.setBounds(26, 218, 222, 23);
		getContentPane().add(btnNewButton);
		
		JTextPane txtpnKarteHerz = new JTextPane();
		txtpnKarteHerz.setText("Karte 1: Herz Ass\r\nKarte 2: Karo 9");
		txtpnKarteHerz.setBounds(324, 47, 351, 100);
		getContentPane().add(txtpnKarteHerz);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(258, 180, 481, 14);
		getContentPane().add(separator_1);
		
		JTextPane txtpnStatusBar = new JTextPane();
		txtpnStatusBar.setText("Status Bar");
		txtpnStatusBar.setBounds(286, 205, 412, 25);
		getContentPane().add(txtpnStatusBar);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(258, 266, 481, 14);
		getContentPane().add(separator_2);
		
		JTextPane txtpnKartePik = new JTextPane();
		txtpnKartePik.setText("Karte 1: Pik K\u00F6nig\r\nKarte 2: Kreuz 2");
		txtpnKartePik.setBounds(324, 319, 351, 100);
		getContentPane().add(txtpnKartePik);
		
		JButton btnStay = new JButton("Stay");
		btnStay.setBounds(487, 430, 89, 23);
		getContentPane().add(btnStay);
		
		JButton btnHit = new JButton("Hit");
		btnHit.setBounds(586, 430, 89, 23);
		getContentPane().add(btnHit);
		
		
	}
}
