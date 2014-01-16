package Models;

public class ListModel
{
	// declaration------------------------------
	
	private String username, password;
	private int chipcount;
	
	// constructor------------------------------
	
	public ListModel(String user, String pw, int chp)
	{
		username = user;
		password = pw;
		chipcount = chp;
	}
	
	// set-block--------------------------------
	
	public void setUsername(String newUsername)
	{
		username = newUsername;
	}
	
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	public void setChipcount(int newChipcount)
	{
		chipcount = newChipcount;
	}
	
	// get-block--------------------------------
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public int getChipcount()
	{
		return chipcount;
	}
	
	
	
}
