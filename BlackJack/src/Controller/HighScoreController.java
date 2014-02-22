package Controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import Main.MainPage;
import Models.ListModel;
import Views.HighScoreView;

public class HighScoreController implements ActionListener
{
	private LinkedList<ListModel> highScoreList = new LinkedList<ListModel>();
	private HighScoreView highScoreView;
	private String[] chipCountList;
	private String[] playerList;
	
	public HighScoreController()
	{
		highScoreList = MainPage.xmlController.getHighScoreList();
		createChipCountList(highScoreList);
		createPlayerList(highScoreList);
		this.highScoreView = new HighScoreView(this, chipCountList, playerList);
		this.highScoreView.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("close")) {
			new MenuController();
			this.highScoreView.dispose();
		}
		
	}
	
	public void createChipCountList(LinkedList<ListModel> list) {
		chipCountList = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			Integer tmp = list.get(i).getChipcount();
			chipCountList[i] = tmp.toString();
		}
	}
	
	public void createPlayerList(LinkedList<ListModel> list) {
		playerList = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			playerList[i] = list.get(i).getUsername();
		}
	}
	
}
