package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Main.MainPage;
import Views.MenuView;

public class MenuController implements ActionListener
{
	// declaration---------------------------------------
	
	private MenuView menuView;
	
	// constructor---------------------------------------
	
	public MenuController()
	{
		this.menuView = new MenuView(this);
		this.menuView.setVisible(true);
	}
	
	// methods-------------------------------------------
	
	// button actionCommands
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("startNewGame")) {
			new LoginController();
			this.menuView.dispose();
			
		} else
			if (e.getActionCommand().equals("exitMenu")) {
				System.exit(0);
			} else
				if (e.getActionCommand().equals("adminControl")) {
					JTextField password = new JPasswordField();
					Object[] message = { "Passwort:", password };
					int passwordDialog = JOptionPane.showConfirmDialog(null, message,
					        "Login", JOptionPane.OK_CANCEL_OPTION);
					if (passwordDialog == JOptionPane.OK_OPTION && password.getText().equals("admin")) {
						
						new AdminController();
						this.menuView.dispose();
					}
				} else
					if (e.getActionCommand().equals("accountControl")) {
											
						JTextField userName = new JTextField();
						JTextField password = new JPasswordField();
						Object[] message = { "Benutzername:", userName,
						        "Password:", password };
						int passwordDialog = JOptionPane.showConfirmDialog(null,
						        message, "Login", JOptionPane.OK_CANCEL_OPTION);
						if (passwordDialog == JOptionPane.OK_OPTION) {
							int verified = MainPage.xmlController.verifyLogin(userName.getText(), password.getText());
						    if (verified != -1 && verified != -2)
						    {
						    	 new AccountController(userName.getText());
								 this.menuView.dispose();
						    } 
						    else if(verified == -1)
						    {
						    	JOptionPane.showMessageDialog(null, "Benutzername oder Passwort falsch!");  
						    }
						}
						
					}else if(e.getActionCommand().equals("highScores")) {
						new HighScoreController();
						 this.menuView.dispose();
					}
	}
}
