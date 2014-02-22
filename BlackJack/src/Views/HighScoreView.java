package Views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controller.HighScoreController;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class HighScoreView extends JFrame
{
	// JList playerList, chipCountList;
	
	public HighScoreView(HighScoreController controller,
	        String[] paramChipCountList, String[] paramPlayerList)
	{
		// playerList=new JList(paramPlayerList);
		// chipCountList=new JList(paramChipCountList);
		
		setTitle("Highscores");
		setMinimumSize(new Dimension(400, 500));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnBeenden = new JButton("Beenden");
		btnBeenden.setActionCommand("close");
		btnBeenden.addActionListener(controller);
		btnBeenden.setBounds(45, 402, 89, 23);
		getContentPane().add(btnBeenden);
		
		JTextArea nameArea = new JTextArea();
		int i = 0;
		for (i = 0; i < paramPlayerList.length; i++) {
			nameArea.append(i + 1 + ") " + paramPlayerList[i] + "\n");
		}
		for (i = i; i < 10; i++) {
			nameArea.append(i + 1 + ") " + "NN" + "\n");
		}
		nameArea.setBounds(45, 87, 141, 196);
		getContentPane().add(nameArea);
		
		JTextArea chipCountArea = new JTextArea();
		int j=0;
		for (j = 0; j < paramChipCountList.length; j++) {
			chipCountArea.append(paramChipCountList[j] + "\n");
		}
		chipCountArea.setBounds(185, 87, 89, 196);
		getContentPane().add(chipCountArea);
		
		setVisible(true);
		
		this.centerWindow();
		
	}
	
	public void centerWindow() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
}
