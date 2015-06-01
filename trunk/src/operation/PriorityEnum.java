package operation;

public enum PriorityEnum {
	REGULAR(3),INTERN(2),OUTSOURCE(1);
	
	private int priority=0;
	
	private PriorityEnum (int priority)
	{
		this.priority=priority;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public boolean Allow(PriorityEnum compareWith)
	{
		return compareWith.getPriority()>=this.priority;
	}
}
