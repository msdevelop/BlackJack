package Models;

public class CardModel
{
	// declaration------------------------------
	
	private String name;
	private int value;
	private boolean flag;
	
	// constructor------------------------------
	
	public CardModel(String inputName, int inputValue, boolean inputFlag)
	{
		name = inputName;
		value = inputValue;
		flag = inputFlag;
	}
	
	// set-block--------------------------------
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setValue(int newValue)
	{
		value = newValue;
	}
	
	public void setFlag(boolean newFlag)
	{
		flag = newFlag;
	}
	
	// get-block--------------------------------
	
	public String getName()
	{
		return name;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public boolean getFlag()
	{
		return flag;
	}
	
	
	
}
