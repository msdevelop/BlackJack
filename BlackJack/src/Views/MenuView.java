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

public class MenuView extends JFrame implements Observer
{
	public MenuView(MenuController controller)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("BlackJack");
		getContentPane().setLayout(null);
		
		JLabel lblMen = new JLabel("Men\u00FC:");
		lblMen.setHorizontalAlignment(SwingConstants.CENTER);
		lblMen.setBounds(10, 11, 47, 36);
		getContentPane().add(lblMen);
		
		JButton NeuesSpielButton = new JButton("Neues Spiel");
		NeuesSpielButton.addActionListener(controller);
		NeuesSpielButton.setBounds(10, 60, 110, 23);
		getContentPane().add(NeuesSpielButton);
		
		JButton ExitButton = new JButton("Exit");
		ExitButton.addActionListener(controller);
		
		ExitButton.setBounds(10, 94, 110, 23);
		getContentPane().add(ExitButton);
		
	}

	@Override
    public void update(Observable arg0, Object arg1) {
	    // TODO Auto-generated method stub
	    
    }
}
