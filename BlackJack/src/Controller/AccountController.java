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
		accountView = new AccountView(this, user, MainPage.xmlController.getRankFromXML(user));
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
						.verifyChipCount(actualUser);
				if (result == true)
					try
					{
						MainPage.xmlController.saveNewRank(actualUser, "1");
						accountView.setTitle(actualUser + " (" + 1 + ") ");
						MainPage.xmlController.saveChipcountToXML(actualUser,
								100);
						this.accountView.updateView(actualUser);
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
				} else
					if (e.getActionCommand().equals("delete")) {
						int dialogResult = JOptionPane
						        .showConfirmDialog(
						                null,
						                "Möchten Sie den Account wirklich endgültig löschen?",
						                "Achtung!", JOptionPane.YES_NO_OPTION);
						if (dialogResult == JOptionPane.YES_OPTION) {
							try {
								MainPage.xmlController.deletePlayer(actualUser);
								JOptionPane.showMessageDialog(null, "Der Account wurde erfolgreich gelöscht.");
								this.accountView.dispose();
								new MenuController();
							} catch (TransformerFactoryConfigurationError e1) {
								e1.printStackTrace();
							} catch (TransformerException e1) {
								e1.printStackTrace();
							}
						}
						
					}
	}
}
