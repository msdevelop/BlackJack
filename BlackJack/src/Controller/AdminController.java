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
	// declaration--------------------------------
	
	private LinkedList<ListModel>	userList;
	private AdminView				adminView;
	private String[]				playerArrayList;

	// constructor--------------------------------
	
	public AdminController()
	{
		this.userList = MainPage.xmlController.getLoginList();
		setPlayerArrayList();
		this.adminView = new AdminView(this, playerArrayList);
	}

	// methods-----------------------------------
	
	// button actionCommands
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
					int dialogResult = JOptionPane.showConfirmDialog(null, "M�chten Sie den Account wirklich endg�ltig l�schen?", "Achtung!", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) 
					{
						MainPage.xmlController.deletePlayer(getPlayerFromList());
						this.userList = MainPage.xmlController.getLoginList();
						setPlayerArrayList();
						adminView.updateUserList(this.playerArrayList);
						adminView.setPropUsername("");
						adminView.setPropChipcount(0);
						JOptionPane.showMessageDialog(null, "Der Account wurde erfolgreich gel�scht.");
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
							                "M�chten Sie das Passwort wirklich zur�cksetzen?",
							                "Achtung!",
							                JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								resetPassword();
								JOptionPane
								        .showMessageDialog(null,
								                "Das Passwort wurde erfolgreich zur�ckgesetzt.");
							}
						} catch (TransformerException e) {
							e.printStackTrace();
						}
			}
		}
	}
	
	//shows the selected users username and the chipcount
	public void showProperties()
	{
		int index = adminView.getSelectedItem();

		this.adminView.setPropUsername(this.userList.get(index).getUsername());
		this.adminView
				.setPropChipcount(this.userList.get(index).getChipcount());
	}

	//replaces the selected users password with a random 5 digit number
	public void resetPassword() throws TransformerException
	{
		int index = adminView.getSelectedItem();

		Random randomGenerator = new Random();
		Integer randomNumber = randomGenerator.nextInt(99999 - 10000) + 10000;
		String newPassword = randomNumber.toString();

		MainPage.xmlController.savePasswordToXML(this.userList.get(index)
				.getUsername(), newPassword, 1);
	}
	
	// set-block--------------------------------

	//generates an array with all users usernames
	public void setPlayerArrayList()
	{

		playerArrayList = new String[userList.size()];

		for (int i = 0; i < userList.size(); i++)
		{
			playerArrayList[i] = userList.get(i).getUsername();
		}
	}

	// get-block--------------------------------
	
	//returns the username of the selected item
	public String getPlayerFromList()
	{
		int index = adminView.getSelectedItem();

		return this.userList.get(index).getUsername();
	}
}
