package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Views.LoginView;

public class LoginController implements ActionListener
{
	
	// declaration---------------------------------------
	
	private LoginView 		loginView;
	private BufferedReader 	buffReader;
	private FileReader		fileReader;
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
		
		try
		{
			fileReader = new FileReader("chipStatus.txt");
		}
		catch (FileNotFoundException e)
		{}
		
		buffReader = new BufferedReader(fileReader);
		
		try
		{
			outerloop:
			for(String line; (line = buffReader.readLine()) != null; )
			{
				if(line.equals(username) && (line = buffReader.readLine()) != null && line.equals(password))
				{
					line = buffReader.readLine();
					new GameController(line);
					loginView.dispose();
					break outerloop;
				}
			}
		}
		catch(IOException e)
		{}
	}
	
	// creates a new profile
	public void createNewProfile()
	{
		
	}
	

}
