package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.MenuView;

public class MenuController implements ActionListener
{
	// declaration---------------------------------------

	private MenuView	menuView;

	// constructor---------------------------------------

	public MenuController()
	{
		this.menuView = new MenuView(this);
		this.menuView.setVisible(true);
	}

	// methods-------------------------------------------

	public void actionPerformed(ActionEvent e)
	{

		if (e.getActionCommand().equals("startNewGame"))
		{
			new LoginController();
			this.menuView.dispose();

		}
		else
			if (e.getActionCommand().equals("exitMenu"))
			{
				System.exit(0);
			}
		else
			if(e.getActionCommand().equals("adminControl"))
			{
				new AdminController();
				this.menuView.dispose();
			}
	}
}
