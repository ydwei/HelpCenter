package operation.businessdata;

import operation.IGuidance;

public class TextGuidance implements IGuidance{
	private String value = "";
	
	public TextGuidance(String value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return value;
	}
}
