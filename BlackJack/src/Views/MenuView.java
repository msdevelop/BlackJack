package Views;

import javax.swing.JFrame;

import Controller.MenuController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class MenuView extends JFrame
{

	// declaration--------------------------------
	
	private static final long serialVersionUID = 257876113542452239L;

	// constructor--------------------------------
	
	public MenuView(MenuController controller)
	{
		setResizable(false);
		setLocation(new Point(100, 100));
		setMinimumSize(new Dimension(220, 350));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Menu Black Jack");
		getContentPane().setLayout(null);
		
		JLabel lblMen = new JLabel("Men\u00FC:");
		lblMen.setHorizontalAlignment(SwingConstants.CENTER);
		lblMen.setBounds(45, 11, 106, 36);
		getContentPane().add(lblMen);
		
		JButton btnNewGame = new JButton("Neues Spiel");
		btnNewGame.setActionCommand("startNewGame");
		btnNewGame.addActionListener(controller);
		btnNewGame.setBounds(10, 60, 194, 37);
		getContentPane().add(btnNewGame);
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.setActionCommand("exitMenu");
		btnExitGame.addActionListener(controller);
		
		btnExitGame.setBounds(10, 248, 194, 36);
		getContentPane().add(btnExitGame);
		
		JButton btnAdminControlPanel = new JButton("Adminbereich");
		btnAdminControlPanel.setActionCommand("adminControl");
		btnAdminControlPanel.addActionListener(controller);
		btnAdminControlPanel.setBounds(10, 201, 194, 36);
		getContentPane().add(btnAdminControlPanel);
		
		JButton btnAccountControl = new JButton("Accountverwaltung");
		btnAccountControl.setActionCommand("accountControl");
		btnAccountControl.addActionListener(controller);
		btnAccountControl.setBounds(10, 156, 194, 34);
		getContentPane().add(btnAccountControl);
		
		JButton btnHighScores = new JButton("Highscore");
		btnHighScores.setActionCommand("highScores");
		btnHighScores.addActionListener(controller);
		btnHighScores.setBounds(10, 108, 194, 37);
		getContentPane().add(btnHighScores);
		
		this.centerWindow();
		
	}
	
	// methods-----------------------------------
	
	// centers the window relative to the screen resolution
	public void centerWindow()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
}
