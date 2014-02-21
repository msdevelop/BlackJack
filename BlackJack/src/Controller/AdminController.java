package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import Main.MainPage;
import Models.ListModel;
import Views.AdminView;

public class AdminController implements ActionListener
{

	LinkedList<ListModel> userList;
	AdminView adminView;
	
	public AdminController()
	{
		this.userList = MainPage.xmlController.getLoginList();
		this.adminView = new AdminView(this, this.userList);
	}

	public void actionPerformed(ActionEvent aE)
	{
		if(aE.getActionCommand().equals("show"))
		{
			this.showProperties();
		}
	}
	
	public void showProperties()
	{
		int index = adminView.getSelectedItem();
		
		this.adminView.setPropUsername(this.userList.get(index).getUsername());
		this.adminView.setPropChipcount(this.userList.get(index).getChipcount());
	}
}
