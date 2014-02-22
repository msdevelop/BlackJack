package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import Main.MainPage;
import Models.ListModel;
import Views.AdminView;

public class AdminController implements ActionListener
{

	private LinkedList<ListModel>	userList;
	private AdminView				adminView;
	private String[]				playerArrayList;

	public AdminController()
	{

		this.userList = MainPage.xmlController.getLoginList();
		setPlayerArrayList();
		this.adminView = new AdminView(this, playerArrayList);
	}

	public void actionPerformed(ActionEvent aE) {
		if (aE.getActionCommand().equals("show")) 
		{
			if(adminView.getSelectedItem() != -1)
			{
				this.showProperties();
			}
		} 
		else if (aE.getActionCommand().equals("close"))
		{
				this.adminView.dispose();
				new MenuController();
		}
		else if (aE.getActionCommand().equals("delete"))
		{
			if(adminView.getSelectedItem() != -1)
			{		
				try
				{
					int dialogResult = JOptionPane.showConfirmDialog(null, "Möchten Sie den Account wirklich endgültig löschen?", "Achtung!", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) 
					{
						MainPage.xmlController.deletePlayer(getPlayerFromList());
						this.userList = MainPage.xmlController.getLoginList();
						setPlayerArrayList();
						adminView.updateUserList(this.playerArrayList);
						adminView.setPropUsername("");
						adminView.setPropChipcount(0);
						JOptionPane.showMessageDialog(null, "Der Account wurde erfolgreich gelöscht.");
					}
				}
				catch (TransformerFactoryConfigurationError e)
				{
					e.printStackTrace();
				}
				catch (TransformerException e) 
				{
					e.printStackTrace();
				}
			}
		}
		else if (aE.getActionCommand().equals("reset")) 
		{
			if(adminView.getSelectedItem() != -1)
			{
						try {
							int dialogResult = JOptionPane
							        .showConfirmDialog(
							                null,
							                "Möchten Sie das Passwort wirklich zurücksetzen?",
							                "Achtung!",
							                JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								resetPassword();
								JOptionPane
								        .showMessageDialog(null,
								                "Das Passwort wurde erfolgreich zurückgesetzt.");
							}
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
		}
	}

	public void setPlayerArrayList()
	{

		playerArrayList = new String[userList.size()];

		for (int i = 0; i < userList.size(); i++)
		{
			playerArrayList[i] = userList.get(i).getUsername();
		}
	}

	public String getPlayerFromList()
	{
		int index = adminView.getSelectedItem();

		return this.userList.get(index).getUsername();
	}

	public void showProperties()
	{
		int index = adminView.getSelectedItem();

		this.adminView.setPropUsername(this.userList.get(index).getUsername());
		this.adminView
				.setPropChipcount(this.userList.get(index).getChipcount());
	}

	public void resetPassword() throws TransformerException
	{
		int index = adminView.getSelectedItem();

		Random randomGenerator = new Random();
		Integer randomNumber = randomGenerator.nextInt(99999 - 10000) + 10000;
		String newPassword = randomNumber.toString();

		MainPage.xmlController.savePasswordToXML(this.userList.get(index)
				.getUsername(), newPassword, 1);
	}
}
