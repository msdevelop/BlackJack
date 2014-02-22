package Views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controller.AccountController;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountView extends JFrame
{

    private static final long serialVersionUID = -2359659819216414869L;

	public AccountView(AccountController controller, String user) {
		setTitle(user);
		
	setMinimumSize(new Dimension(300, 300));
	getContentPane().setLayout(null);
	setVisible(true);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
	JButton btnChangePassword = new JButton("Passwort \u00E4ndern");
	btnChangePassword.setActionCommand("changePassword");
	btnChangePassword.addActionListener(controller);
	btnChangePassword.setBounds(10, 20, 200, 40);
	getContentPane().add(btnChangePassword);
	
	JButton btnNeueChipsAnfordern = new JButton("Neue Chips anfordern");
	btnNeueChipsAnfordern.setActionCommand("newChips");
	btnNeueChipsAnfordern.addActionListener(controller);
	btnNeueChipsAnfordern.setBounds(10, 80, 200, 40);
	getContentPane().add(btnNeueChipsAnfordern);
	
	JButton btnClose = new JButton("Beenden");
	btnClose.setActionCommand("close");
	btnClose.addActionListener(controller);
	btnClose.setBounds(10, 170, 140, 40);
	getContentPane().add(btnClose);
	
	this.centerWindow();
	}
	
	public void centerWindow()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
}
