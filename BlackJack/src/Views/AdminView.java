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
import java.awt.Font;

public class AdminView extends JFrame
{
	
	private static final long serialVersionUID = 1100530105788697653L;
	private JList userList;
	private JScrollPane scrollpane;
	private String[] playerArrayList;
	private JTextField propUsername;
	private JTextField propChipcount;
	
	public AdminView(AdminController controller, String[] paramPlayerArrayList)
	{
		setResizable(false);
		this.playerArrayList = paramPlayerArrayList;
		
		setMinimumSize(new Dimension(370, 400));
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		// JList <String> erst wenn UI fertig da sonst error on parse
		userList = new JList(this.playerArrayList);
		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 56, 150, 200);		
		scrollpane.setViewportView(userList);
		getContentPane().add(scrollpane);
		
		JButton btnEditUser = new JButton("Passwort zur\u00FCcksetzen");
		btnEditUser.setActionCommand("reset");
		btnEditUser.addActionListener(controller);
		btnEditUser.setBounds(170, 214, 175, 23);
		getContentPane().add(btnEditUser);
		
		JButton btnDeleteUser = new JButton("Account l\u00F6schen");
		btnDeleteUser.setActionCommand("delete");
		btnDeleteUser.addActionListener(controller);
		btnDeleteUser.setBounds(170, 180, 175, 23);
		getContentPane().add(btnDeleteUser);
		
		JButton btnShowProperties = new JButton("Detail anzeigen");
		btnShowProperties.setActionCommand("show");
		btnShowProperties.addActionListener(controller);
		btnShowProperties.setBounds(170, 146, 175, 23);
		getContentPane().add(btnShowProperties);
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setBounds(10, 285, 150, 23);
		getContentPane().add(lblBenutzername);
		
		JLabel lblChipcount = new JLabel("Chipcount:");
		lblChipcount.setBounds(10, 319, 150, 23);
		getContentPane().add(lblChipcount);
		
		JButton btnClose = new JButton("Abmelden");
		btnClose.setActionCommand("close");
		btnClose.addActionListener(controller);
		btnClose.setBounds(242, 56, 103, 54);
		getContentPane().add(btnClose);
		
		JLabel lblAdminverwaltung = new JLabel("Benutzerverwaltung");
		lblAdminverwaltung.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdminverwaltung.setBounds(86, 0, 193, 45);
		getContentPane().add(lblAdminverwaltung);
		
		propUsername = new JTextField();
		propUsername.setBounds(149, 286, 130, 20);
		getContentPane().add(propUsername);
		propUsername.setColumns(10);
		
		propChipcount = new JTextField();
		propChipcount.setBounds(149, 319, 130, 20);
		getContentPane().add(propChipcount);
		propChipcount.setColumns(10);
		
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
	
	public void updateUserList(String[] paramPlayerArrayList)
	{
		userList = new JList(paramPlayerArrayList);
		scrollpane.setViewportView(userList);
	}
	
	public void centerWindow()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
}
