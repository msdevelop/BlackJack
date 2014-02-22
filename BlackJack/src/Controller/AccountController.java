package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import Main.MainPage;
import Views.AccountView;

public class AccountController implements ActionListener
{
	AccountView accountView;
	String actualUser;
	
	public AccountController(String user)
	{
		actualUser = user;
		this.accountView = new AccountView(this, user);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("changePassword")) {
			JTextField password = new JPasswordField();
			Object[] message = { "Passwort:", password };
			int passwordDialog = JOptionPane.showConfirmDialog(null, message,
			        "Login", JOptionPane.OK_CANCEL_OPTION);
			if (passwordDialog == JOptionPane.OK_OPTION) {
				try {
	                MainPage.xmlController.savePasswordToXML(actualUser,
	                        password.getText(),0);
                } catch (TransformerException e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
                }
				JOptionPane.showMessageDialog(null, "Ihr Passwort wurde erfolgreich geändert.");
			}
		} else
			if (e.getActionCommand().equals("newChips")) {
				Boolean result = MainPage.xmlController.getChipCount(actualUser);
				if(result==true) {
					try {
	                    MainPage.xmlController.saveChipcountToXML(actualUser, 200);
	                    JOptionPane.showMessageDialog(null, "Ihnen wurden erfolgreich neue Chips zugeteilt.");
                    } catch (TransformerFactoryConfigurationError e1) {
	                    // TODO Auto-generated catch block
	                    e1.printStackTrace();
                    } catch (TransformerException e1) {
	                    // TODO Auto-generated catch block
	                    e1.printStackTrace();
                    }
				}else {
					JOptionPane.showMessageDialog(null, "Sie besitzen noch Chips.");
				}
				
			} else
				if (e.getActionCommand().equals("close")) {
					this.accountView.dispose();
					new MenuController();
				}
		
	}
}
