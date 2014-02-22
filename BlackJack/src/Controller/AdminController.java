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
	
	LinkedList<ListModel> userList;
	AdminView adminView;
	public String[] playerArrayList;
	LinkedList<ListModel> paramUserList;
	
	public AdminController()
	{
		
		this.userList = MainPage.xmlController.getLoginList();
		setPlayerArrayList();
		this.adminView = new AdminView(this);
	}
	
	public void actionPerformed(ActionEvent aE) {
		if (aE.getActionCommand().equals("show")) {
			this.showProperties();
		} else
			if (aE.getActionCommand().equals("close")) {
				this.adminView.dispose();
				new MenuController();
			} else
				if (aE.getActionCommand().equals("delete")) {
					
					try {
						int dialogResult = JOptionPane
						        .showConfirmDialog(
						                null,
						                "M�chten Sie den Account wirklich endg�ltig l�schen?",
						                "Achtung!", JOptionPane.YES_NO_OPTION);
						if (dialogResult == JOptionPane.YES_OPTION) {
							MainPage.xmlController
							        .deletePlayer(getPlayerFromList());
							this.userList = MainPage.xmlController
							        .getLoginList();
							setPlayerArrayList();
							JOptionPane.showMessageDialog(null,
							        "Der Account wurde erfolgreich gel�scht.");
						}
					} catch (TransformerFactoryConfigurationError e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else
					if (aE.getActionCommand().equals("reset")) {
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
	}
	
	public void setPlayerArrayList() {
		
		playerArrayList = new String[userList.size()];
		
		for (int i = 0; i < userList.size(); i++) {
			playerArrayList[i] = userList.get(i).getUsername();
		}
	}
	
	public String getPlayerFromList() {
		int index = adminView.getSelectedItem();
		
		return this.userList.get(index).getUsername();
	}
	
	public void showProperties() {
		int index = adminView.getSelectedItem();
		
		this.adminView.setPropUsername(this.userList.get(index).getUsername());
		this.adminView
		        .setPropChipcount(this.userList.get(index).getChipcount());
	}
	
	public void resetPassword() throws TransformerException {
		int index = adminView.getSelectedItem();
		
		Random randomGenerator = new Random();
		Integer randomNumber = randomGenerator.nextInt(99999 - 10000) + 10000;
		String newPassword = randomNumber.toString();
		
		MainPage.xmlController.savePasswordToXML(this.userList.get(index)
		        .getUsername(), newPassword, 1);
	}
}
