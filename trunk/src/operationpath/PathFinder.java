package operationpath;

import java.util.ArrayList;
import java.util.LinkedList;

import operation.IBusinessData;
import operation.Operation;

public class PathFinder {
	
	private static ArrayList<LinkedList<Operation>> paths = new ArrayList<LinkedList<Operation>>();
	
	public static void createPaths(ArrayList<Operation> operations)
	{
		for(Operation operation:operations)
		{
			LinkedList<Operation> path = new LinkedList<Operation>();
			path.addLast(operation);
			appendNextPathPoint(path,operation,operations);
		}
		
	}
	
	public static boolean appendNextPathPoint(LinkedList<Operation> path,
			Operation currentOperation,ArrayList<Operation> operations)
	{
		
		for(IBusinessData data:currentOperation.getProducts())
		{
			ArrayList<Operation> children = getAllOperationsWithInput(operations,data);
			if(children.size()==0)
			{
				paths.add(path);
				continue;
			}
			
			for(Operation child: children)
			{	
				LinkedList<Operation> newPath = clonePath(path);
				newPath.addLast(child);
				appendNextPathPoint(newPath,child,operations);
			}
		}
		
		return true;
	}
	
	public static LinkedList<Operation> clonePath(LinkedList<Operation> path)
	{
		LinkedList<Operation> newPath = new LinkedList<Operation>();
		
		for(Operation operation:path)
		{
			newPath.addLast(operation);
		}
		
		return newPath;
	}
	
	public static ArrayList<Operation> getAllOperationsWithInput(ArrayList<Operation> operations,
			IBusinessData data)
	{
		ArrayList<Operation> values = new ArrayList<Operation>();
		
		for(Operation operation:operations)
		{
			if(operation.getRequirements().contains(data))
			{
				values.add(operation);
			}
		}
		
		return values;
	}
	
	public static void printAllPaths()
	{
		for(LinkedList<Operation> path:paths)
		{
			printPath(path);
			System.out.println();
		}
	}
	
	public static void printPath(LinkedList<Operation> path)
	{
		for(Operation operation:path)
		{
			System.out.print(" => "+operation.getName());
		}
	}
}
