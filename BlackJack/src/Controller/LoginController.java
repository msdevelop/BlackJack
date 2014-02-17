package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login"))
			this.verifyLogin();
		else
			if (e.getActionCommand().equals("createNewProfile"))
	            try {
	                this.createNewProfile(loginView.getUsername(), loginView.getPassword());
                } catch (TransformerFactoryConfigurationError e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
                } catch (TransformerException e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
                }
	}
	
	// verifies if the given login data is correct
	public void verifyLogin() {
		username = loginView.getUsername();
		password = loginView.getPassword();
		
		int verify = MainPage.xmlController.verifyLogin(username, password);
		
		if (verify != -1) {
			new GameController(verify, username);
			this.loginView.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "wrong login input");
		}
	}
	
	// creates a new profile
	public void createNewProfile(String user, String pw) throws TransformerFactoryConfigurationError, TransformerException {
			
		try {
			MainPage.xmlController.addNodeToXML(user, pw);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
