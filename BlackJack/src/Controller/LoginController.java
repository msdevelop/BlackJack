package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Main.MainPage;
import Views.LoginView;

public class LoginController implements ActionListener
{
	
	// declaration---------------------------------------
	
	private LoginView 		loginView;
	private String 			username, password;
	
	// constructor---------------------------------------

	public LoginController()
	{
		this.loginView = new LoginView(this);
		this.loginView.setVisible(true);
	}
	
	// methods-------------------------------------------
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("login"))
			this.verifyLogin();
		else
			if(e.getActionCommand().equals("createNewProfile"))
				this.createNewProfile();
	}
	
	// verifies if the given login data is correct
	public void verifyLogin()
	{
		username = loginView.getUsername();
		password = loginView.getPassword();
		
		int verify = MainPage.xmlController.verifyLogin(username, password);
		
		if(verify != -1)
		{
			new GameController(verify);
			this.loginView.dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "wrong login input");
		}
	}
	
	// creates a new profile
	public void createNewProfile()
	{
		
	}
	

}
