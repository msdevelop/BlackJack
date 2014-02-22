package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import Main.MainPage;
import Views.LoginView;

public class LoginController implements ActionListener
{
	
	// declaration---------------------------------------
	
	private LoginView loginView;
	private String username, password;
	
	// constructor---------------------------------------
	
	public LoginController()
	{
		this.loginView = new LoginView(this);
		this.loginView.setVisible(true);
	}
	
	// methods-------------------------------------------
	
	// button actionCommands
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) {
			this.verifyLogin();
		} else
			if (e.getActionCommand().equals("createNewProfile")) {
				JTextField userName = new JTextField();
				JTextField password = new JPasswordField();
				Object[] message = { "Benutzername:", userName, "Password:",
				        password };
				
				int passwordDialog = JOptionPane.showConfirmDialog(null,
				        message, "Neues Profil erstellen",
				        JOptionPane.OK_CANCEL_OPTION);
				if (passwordDialog == JOptionPane.OK_OPTION) {
					try {
						if ((userName.getText().length() >= 4)
						        && (userName.getText().length() <= 10)
						        && (password.getText().length() >= 4)
						        && (password.getText().length() <= 10)) {
							this.createNewProfile(userName.getText(),
							        password.getText(), "1");
						} else {
							JOptionPane
							        .showMessageDialog(
							                null,
							                "Benutzername und Passwort dürfen nicht länger als 10 und nicht kürzer als 4 Zeichen sein!");
						}
					} catch (TransformerFactoryConfigurationError e1) {
						e1.printStackTrace();
					} catch (TransformerException e1) {
						e1.printStackTrace();
					}
				}
			} else
				if (e.getActionCommand().equals("exit")) {
					this.loginView.dispose();
					new MenuController();
				}
		
	}
	
	// verifies if the given login data is correct
	public void verifyLogin() {
		
		username = loginView.getUsername();
		password = loginView.getPassword();
		
		int verify = MainPage.xmlController.verifyLogin(username, password);
		
		if (verify != -1 && verify != -2) {
			if (verify > 0) {
				new GameController(verify, username);
				this.loginView.dispose();
			} else {
				JOptionPane.showMessageDialog(null,
				        "Sie haben nicht genügend Chips!");
			}
		} else
			if (verify == -1) {
				JOptionPane.showMessageDialog(null,
				        "Benutzername oder Passwort falsch!");
			}
	}
	
	// creates a new profile
	public void createNewProfile(String user, String pw, String rang)
	        throws TransformerFactoryConfigurationError, TransformerException {
		
		try {
			MainPage.xmlController.addNodeToXML(user, pw, rang);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
