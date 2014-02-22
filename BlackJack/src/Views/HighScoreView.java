package Views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controller.HighScoreController;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;

public class HighScoreView extends JFrame
{
	
	public HighScoreView(HighScoreController controller,
	        String[] paramChipCountList, String[] paramPlayerList)
	{
		setResizable(false);

		
		setTitle("Highscore");
		setMinimumSize(new Dimension(300, 380));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnBeenden = new JButton("Zur\u00FCck");
		btnBeenden.setActionCommand("close");
		btnBeenden.addActionListener(controller);
		btnBeenden.setBounds(173, 303, 89, 23);
		getContentPane().add(btnBeenden);
		
		JTextArea nameArea = new JTextArea();
		nameArea.setFocusable(false);
		nameArea.setEditable(false);
		int i = 0;
		for (i = 0; i < paramPlayerList.length; i++) {
			nameArea.append(i + 1 + ") " + paramPlayerList[i] + "\n");
		}
		for (i = i; i < 10; i++) {
			nameArea.append(i + 1 + ") " + "NN" + "\n");
		}
		nameArea.setBounds(33, 78, 141, 196);
		getContentPane().add(nameArea);
		
		JTextArea chipCountArea = new JTextArea();
		chipCountArea.setFocusable(false);
		chipCountArea.setEditable(false);
		int j=0;
		for (j = 0; j < paramChipCountList.length; j++) {
			chipCountArea.append(paramChipCountList[j] + "\n");
		}
		chipCountArea.setBounds(173, 78, 89, 196);
		getContentPane().add(chipCountArea);
		
		JLabel lblHighscore = new JLabel("Highscore");
		lblHighscore.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHighscore.setBounds(93, 0, 108, 50);
		getContentPane().add(lblHighscore);
		
		setVisible(true);
		
		this.centerWindow();
		
	}
	
	public void centerWindow() 
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
}
