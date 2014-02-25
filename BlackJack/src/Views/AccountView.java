package Views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controller.AccountController;
import Main.MainPage;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AccountView extends JFrame
{
	
	// declaration--------------------------------
	
	private JLabel lblAktuellerChipcount, lblNchstesLevelBei,
	        lblBentigteChipsBis;
	
	private static final long serialVersionUID = -2359659819216414869L;
	
	// constructor--------------------------------
	
	public AccountView(AccountController controller, String user, String rank)
	{
		setResizable(false);
		setTitle(user + " (" + rank + ") ");
		
		setMinimumSize(new Dimension(250, 360));
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnChangePassword = new JButton("Passwort \u00E4ndern");
		btnChangePassword.setActionCommand("changePassword");
		btnChangePassword.addActionListener(controller);
		btnChangePassword.setBounds(10, 128, 224, 40);
		getContentPane().add(btnChangePassword);
		
		JButton btnNeueChipsAnfordern = new JButton("Neue Chips anfordern");
		btnNeueChipsAnfordern.setActionCommand("newChips");
		btnNeueChipsAnfordern.addActionListener(controller);
		btnNeueChipsAnfordern.setBounds(10, 179, 224, 40);
		getContentPane().add(btnNeueChipsAnfordern);
		
		JButton btnClose = new JButton("Abmelden");
		btnClose.setActionCommand("close");
		btnClose.addActionListener(controller);
		btnClose.setBounds(10, 281, 224, 40);
		getContentPane().add(btnClose);
		
		JButton btnAccountLschen = new JButton("Account l\u00F6schen");
		btnAccountLschen.setActionCommand("delete");
		btnAccountLschen.addActionListener(controller);
		btnAccountLschen.setBounds(10, 230, 224, 40);
		getContentPane().add(btnAccountLschen);
		
		lblAktuellerChipcount = new JLabel("Aktueller Chipcount: "
		        + MainPage.xmlController.getChipcount(user));
		lblAktuellerChipcount.setBounds(10, 36, 224, 14);
		getContentPane().add(lblAktuellerChipcount);
		
		lblNchstesLevelBei = new JLabel("N\u00E4chstes Level bei "
		        + MainPage.xmlController.calculateTargetForNextRank(user));
		lblNchstesLevelBei.setBounds(10, 61, 224, 14);
		getContentPane().add(lblNchstesLevelBei);
		
		lblBentigteChipsBis = new JLabel(
		        "Chips bis zum n\u00E4chsten Level: "
		                + MainPage.xmlController.calculateDifferenceToNextrank(
		                        user, MainPage.xmlController
		                                .calculateTargetForNextRank(user)));
		lblBentigteChipsBis.setBounds(10, 86, 224, 14);
		getContentPane().add(lblBentigteChipsBis);
		
		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblInfo);
		
		this.centerWindow();
	}
	
	// methods-----------------------------------
	
	// centers the window relative to the screen resolution
	public void centerWindow() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
	
	public void updateView(String user) {
		
		lblAktuellerChipcount.setText("Aktueller Chipcount: "
		        + MainPage.xmlController.getChipcount(user));
		lblNchstesLevelBei.setText("N\u00E4chstes Level bei "
		        + MainPage.xmlController.calculateTargetForNextRank(user));
		lblBentigteChipsBis.setText("Chips bis zum n\u00E4chsten Level: "
                + MainPage.xmlController.calculateDifferenceToNextrank(
                        user, MainPage.xmlController
                                .calculateTargetForNextRank(user)));
	}
	
	// set-block--------------------------------
	
	// changes the windows title
	public void setNewTitle(String paramTitle) {
		setTitle(paramTitle);
	}
}
