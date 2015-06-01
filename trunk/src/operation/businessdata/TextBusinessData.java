package operation.businessdata;

import operation.IBusinessData;

public class TextBusinessData implements IBusinessData{
	private String value = "";
	
	public TextBusinessData(String value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return value;
	}
}
