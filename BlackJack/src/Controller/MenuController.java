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
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("startNewGame")) {
			new LoginController();
			this.menuView.dispose();
			
		} else
			if (e.getActionCommand().equals("exitMenu")) {
				System.exit(0);
			} else
				if (e.getActionCommand().equals("adminControl")) {
					String adminLogin = JOptionPane.showInputDialog(null,
					        "Admin Login", "Admin Login",
					        JOptionPane.OK_CANCEL_OPTION);
					if (adminLogin.equals("admin")) {
						
						new AdminController();
						this.menuView.dispose();
					}
				} else
					if (e.getActionCommand().equals("accountControl")) {
											
						JTextField userName = new JTextField();
						JTextField password = new JPasswordField();
						Object[] message = { "Username:", userName,
						        "Password:", password };
						int passwordDialog = JOptionPane.showConfirmDialog(null,
						        message, "Login", JOptionPane.OK_CANCEL_OPTION);
						if (passwordDialog == JOptionPane.OK_OPTION) {
							int verified = MainPage.xmlController.verifyLogin(userName.getText(), password.getText());
						    if (verified != -1) {
						    	 new AccountController(userName.getText());
								 this.menuView.dispose();
						    } else {
						    	JOptionPane.showMessageDialog(null, "wrong login input");  
						    }
						}
						
					}else if(e.getActionCommand().equals("highScores")) {
						new HighScoreController();
						 this.menuView.dispose();
					}
	}
}
