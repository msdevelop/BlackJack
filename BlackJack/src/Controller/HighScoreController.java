package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import Main.MainPage;
import Models.ListModel;
import Views.HighScoreView;

public class HighScoreController implements ActionListener
{
	
	// declaration--------------------------------
	
	private LinkedList<ListModel> highScoreList = new LinkedList<ListModel>();
	private HighScoreView highScoreView;
	private String[] chipCountList;
	private String[] playerList;
	
	// constructor--------------------------------
	
	public HighScoreController()
	{
		highScoreList = MainPage.xmlController.getHighScoreList();
		createChipCountList(highScoreList);
		createPlayerList(highScoreList);
		this.highScoreView = new HighScoreView(this, chipCountList, playerList);
		this.highScoreView.setVisible(true);
	}
	
	// methods-----------------------------------
	
	// button actionCommands
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("close")) {
			new MenuController();
			this.highScoreView.dispose();
		}
		
	}
	
	// generates an array with all chipcount values
	public void createChipCountList(LinkedList<ListModel> list) {
		chipCountList = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {			
			chipCountList[i] = Integer.toString(list.get(i).getChipcount());
		}
	}
	
	//generates an array with all player names
	public void createPlayerList(LinkedList<ListModel> list) {
		playerList = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			playerList[i] = list.get(i).getUsername();
		}
	}
	
}
