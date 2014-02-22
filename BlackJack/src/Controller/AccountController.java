package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import Main.MainPage;
import Views.AccountView;

public class AccountController implements ActionListener
{

	// declaration--------------------------------

	private AccountView	accountView;
	private String		actualUser;

	// constructor--------------------------------

	public AccountController(String user)
	{
		actualUser = user;
		accountView = new AccountView(this, user);
	}

	// methods-----------------------------------

	// button actionCommands
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("changePassword"))
		{
			String passwordDialog = JOptionPane.showInputDialog(null,
					"Bitte geben Sie ihr neues Passwort ein: ",
					"Passwort ändern", JOptionPane.OK_CANCEL_OPTION);
			if (passwordDialog != null)
				if ((passwordDialog.length() >= 4)
						&& (passwordDialog.length() <= 10))
				{
					try
					{
						MainPage.xmlController.savePasswordToXML(actualUser,
								passwordDialog, 0);
					}
					catch (TransformerException e1)
					{
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null,
							"Ihr Passwort wurde erfolgreich geändert.");
				}
				else
					JOptionPane
							.showMessageDialog(null,
									"Das Passwort darf nicht länger als 10 und nicht kürzer als 4 Zeichen sein.");
		}
		else
			if (e.getActionCommand().equals("newChips"))
			{
				Boolean result = MainPage.xmlController
						.getChipCount(actualUser);
				if (result == true)
					try
					{
						MainPage.xmlController.saveChipcountToXML(actualUser,
								200);
						JOptionPane
								.showMessageDialog(null,
										"Ihnen wurden erfolgreich neue Chips zugeteilt.");
					}
					catch (TransformerFactoryConfigurationError e1)
					{
						e1.printStackTrace();
					}
					catch (TransformerException e1)
					{
						e1.printStackTrace();
					}
				else
					JOptionPane.showMessageDialog(null,
							"Sie besitzen noch Chips.");

			}
			else
				if (e.getActionCommand().equals("close"))
				{
					accountView.dispose();
					new MenuController();
				}
	}
}
