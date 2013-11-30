package Models;

public class CardModel
{
	// declaration------------------------------

	private String	name;
	private int		value, count;

	// constructor------------------------------

	public CardModel(String inputName, int inputValue)
	{
		name = inputName;
		value = inputValue;
		count = 0;
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

	public void setCount(int newCount)
	{
		count = newCount;
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

	public int getCount()
	{
		return count;
	}

}
