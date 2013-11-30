package Models;

public class CardModel
{
	// declaration------------------------------
	
	private String name, color;
	private int value;
	private boolean flag;
	
	// constructor------------------------------
	
	public CardModel(String inputName, int inputValue, boolean inputFlag, String inputColor)
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
	
	public void setColor(String newColor)
	{
		color = newColor;
	}
	
	// get-block--------------------------------
	
	public String getName()
	{
		return name;
	}
	
	public String getColor()
	{
		return color;
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
