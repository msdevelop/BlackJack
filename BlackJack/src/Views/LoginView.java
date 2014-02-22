package Views;

import javax.swing.JFrame;

import Controller.LoginController;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class LoginView extends JFrame
{
	// declaration--------------------------------
	
	private static final long serialVersionUID = 4966222642786917578L;
	private JTextField username;
	private JPasswordField password;
	
	// constructor---------------------------------------
	
	public LoginView(LoginController controller)
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setMinimumSize(new Dimension(370, 180));
		getContentPane().setLayout(null);
		
		username = new JTextField();
		username.setBounds(137, 21, 86, 20);
		getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Benutzername:");
		lblUsername.setBounds(31, 24, 96, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Passwort:");
		lblPassword.setBounds(31, 68, 75, 14);
		getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Einloggen");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(controller);
		btnLogin.setBounds(248, 21, 96, 61);
		getContentPane().add(btnLogin);
		
		JButton btnCreateNewProfile = new JButton("Neues Profil erstellen");
		btnCreateNewProfile.setActionCommand("createNewProfile");
		btnCreateNewProfile.addActionListener(controller);
		btnCreateNewProfile.setBounds(31, 104, 171, 32);
		getContentPane().add(btnCreateNewProfile);
		
		JButton btnExit = new JButton("Zur\u00FCck");
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(controller);
		btnExit.setBounds(248, 104, 96, 32);
		getContentPane().add(btnExit);
		
		password = new JPasswordField();
		password.setBounds(137, 62, 86, 20);
		getContentPane().add(password);
		
		this.centerWindow();
	}

	// methods-------------------------------------------
	
	//TODO COMMENT
	public void centerWindow()
	{
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
	
	// get-block--------------------------------
	
	public String getUsername()
	{
		return username.getText();
	}
	
	public String getPassword()
	{
		return new String(password.getPassword());
	}
}
