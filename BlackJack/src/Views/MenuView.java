package Views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import Controller.MenuController;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class MenuView extends JFrame implements Observer
{
	// constructor---------------------------------------------------
	
	public MenuView(MenuController controller)
	{
		setMinimumSize(new Dimension(150, 215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Black Jack");
		getContentPane().setLayout(null);
		
		JLabel lblMen = new JLabel("Men\u00FC:");
		lblMen.setHorizontalAlignment(SwingConstants.CENTER);
		lblMen.setBounds(10, 11, 47, 36);
		getContentPane().add(lblMen);
		
		JButton btnNewGame = new JButton("Neues Spiel");
		btnNewGame.setActionCommand("startNewGame");
		btnNewGame.addActionListener(controller);
		btnNewGame.setBounds(10, 60, 110, 23);
		getContentPane().add(btnNewGame);
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.setActionCommand("exitMenu");
		btnExitGame.addActionListener(controller);
		
		btnExitGame.setBounds(10, 104, 110, 23);
		getContentPane().add(btnExitGame);
		
	}
	
	// methods---------------------------------------------------------

	@Override
    public void update(Observable arg0, Object arg1) {
	    
    }
}
