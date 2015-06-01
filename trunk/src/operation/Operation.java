package operation;

import java.util.ArrayList;

public class Operation {
	private String name="unset";
	private ArrayList<IBusinessData> requirements = new ArrayList<IBusinessData>();
	private ArrayList<IBusinessData> products = new ArrayList<IBusinessData>();
	private PriorityEnum priority;
	private IGuidance guidance;
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<IBusinessData> getRequirements() {
		return requirements;
	}
	public void setRequirements(ArrayList<IBusinessData> requirements) {
		this.requirements = requirements;
	}
	public ArrayList<IBusinessData> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<IBusinessData> products) {
		this.products = products;
	}
	public PriorityEnum getPriority() {
		return priority;
	}
	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}
	public IGuidance getGuidance() {
		return guidance;
	}
	public void setGuidance(IGuidance guidance) {
		this.guidance = guidance;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public String toString1()
	{
		
		StringBuilder buffer = new StringBuilder();
		
		
		buffer.append("Input:\n");
		for(IBusinessData data:requirements)
		{
			buffer.append(data+"\n");
		}
		buffer.append("\nOutput:\n");
		for(IBusinessData data:products)
		{
			buffer.append(data+"\n");
		}
		buffer.append("\nGuidance:\n");
		buffer.append(guidance+"\n");
		
		return buffer.toString();
	}
}
