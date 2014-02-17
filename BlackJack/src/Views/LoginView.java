package Views;

import javax.swing.JFrame;

import Controller.LoginController;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;

public class LoginView extends JFrame
{
	// declaration--------------------------------
	
	private JTextField password, username;
	
	// constructor---------------------------------------
	
	public LoginView(LoginController controller)
	{
		setMinimumSize(new Dimension(360, 210));
		getContentPane().setLayout(null);
		
		username = new JTextField();
		username.setBounds(116, 21, 86, 20);
		getContentPane().add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(116, 71, 86, 20);
		getContentPane().add(password);
		password.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(31, 24, 75, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(31, 74, 75, 14);
		getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(controller);
		btnLogin.setBounds(227, 21, 86, 70);
		getContentPane().add(btnLogin);
		
		JButton btnCreateNewProfile = new JButton("Create new profile");
		btnCreateNewProfile.setActionCommand("createNewProfile");
		btnCreateNewProfile.addActionListener(controller);
		btnCreateNewProfile.setBounds(31, 116, 171, 32);
		getContentPane().add(btnCreateNewProfile);
		
		this.centerWindow();
	}

	// methods-------------------------------------------
	
	public void centerWindow()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
	
	public String getUsername()
	{
		return username.getText();
	}
	
	public String getPassword()
	{
		return password.getText();
	}
}
