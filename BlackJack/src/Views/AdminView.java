package Views;

import Controller.AdminController;
import Models.ListModel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AdminView extends JFrame
{
	
	private static final long serialVersionUID = 1100530105788697653L;
	JList userList;
	JEditorPane propUsername, propChipcount;
	
	public AdminView(AdminController controller)
	{
		setMinimumSize(new Dimension(900, 800));
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		// JList <String> erst wenn UI fertig da sonst error on parse
		userList = new JList(controller.playerArrayList);
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 67, 150, 200);		
		scrollpane.setViewportView(userList);
		getContentPane().add(scrollpane);
		
		JButton btnEditUser = new JButton("Passwort zur\u00FCcksetzen");
		btnEditUser.setActionCommand("reset");
		btnEditUser.addActionListener(controller);
		btnEditUser.setBounds(170, 244, 150, 23);
		getContentPane().add(btnEditUser);
		
		JButton btnDeleteUser = new JButton("Account l\u00F6schen");
		btnDeleteUser.setActionCommand("delete");
		btnDeleteUser.addActionListener(controller);
		btnDeleteUser.setBounds(170, 210, 150, 23);
		getContentPane().add(btnDeleteUser);
		
		JButton btnShowProperties = new JButton("Detail anzeigen");
		btnShowProperties.setActionCommand("show");
		btnShowProperties.addActionListener(controller);
		btnShowProperties.setBounds(170, 175, 150, 23);
		getContentPane().add(btnShowProperties);
		
		propUsername = new JEditorPane();
		propUsername.setEditable(false);
		propUsername.setBounds(170, 362, 150, 23);
		getContentPane().add(propUsername);
		
		propChipcount = new JEditorPane();
		propChipcount.setEditable(false);
		propChipcount.setBounds(170, 414, 150, 23);
		getContentPane().add(propChipcount);
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setBounds(10, 362, 150, 23);
		getContentPane().add(lblBenutzername);
		
		JLabel lblChipcount = new JLabel("Chipcount:");
		lblChipcount.setBounds(10, 414, 150, 23);
		getContentPane().add(lblChipcount);
		
		JButton btnClose = new JButton("Schlie\u00DFen");
		btnClose.setActionCommand("close");
		btnClose.addActionListener(controller);
		btnClose.setBounds(57, 515, 103, 23);
		getContentPane().add(btnClose);
		
		this.centerWindow();
	}
	
	public int getSelectedItem()
	{
		return userList.getSelectedIndex();
	}
	
	public void setPropUsername(String paramUsername)
	{
		this.propUsername.setText(paramUsername);
	}
	
	public void setPropChipcount(int paramChipcount)
	{
		this.propChipcount.setText(Integer.toString(paramChipcount));
	}
	
	public void centerWindow()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
}
