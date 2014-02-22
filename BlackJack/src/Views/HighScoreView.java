package Views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controller.HighScoreController;
import Main.MainPage;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;

public class HighScoreView extends JFrame
{
	
	// declaration--------------------------------
	
	private static final long serialVersionUID = 7575188349212040622L;
	
	// constructor--------------------------------
	
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
			
			if (i == 9) {
				nameArea.append(i
				        + 1
				        + ")  "
				        + paramPlayerList[i]
				        + " ("
				        + MainPage.xmlController
				                .getRankFromXML(paramPlayerList[i]) + ") "
				        + "\n");
				i++;
				break;
			} else {
				nameArea.append(i
				        + 1
				        + ")    "
				        + paramPlayerList[i]
				        + " ("
				        + MainPage.xmlController
				                .getRankFromXML(paramPlayerList[i]) + ") "
				        + "\n");
			}
		}
		for (i = i; i < 10; i++) {
			if (i == 9) {
				nameArea.append(i + 1 + ")  " + "NN" + "\n");
			} else {
				nameArea.append(i + 1 + ")    " + "NN" + "\n");
			}
		}
		nameArea.setBounds(33, 78, 141, 196);
		getContentPane().add(nameArea);
		
		JTextArea chipCountArea = new JTextArea();
		chipCountArea.setFocusable(false);
		chipCountArea.setEditable(false);
		int j = 0;
		for (j = 0; j < paramChipCountList.length; j++) {
			chipCountArea.append(paramChipCountList[j] + "\n");
			if (j == 9) {
				break;
			}
		}
		chipCountArea.setBounds(173, 78, 89, 196);
		getContentPane().add(chipCountArea);
		
		JLabel lblHighscore = new JLabel("Highscore");
		lblHighscore.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHighscore.setBounds(93, 0, 108, 50);
		getContentPane().add(lblHighscore);
		
		JLabel lblBenutzername = new JLabel("Benutzername");
		lblBenutzername.setBounds(33, 53, 108, 14);
		getContentPane().add(lblBenutzername);
		
		JLabel lblChipcount = new JLabel("Chipcount");
		lblChipcount.setBounds(173, 53, 89, 14);
		getContentPane().add(lblChipcount);
		
		setVisible(true);
		
		this.centerWindow();
		
	}
	
	// methods-----------------------------------
	
	// TODO COMMENT
	public void centerWindow() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
}
