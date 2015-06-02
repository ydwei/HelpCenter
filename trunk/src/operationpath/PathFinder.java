package operationpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import operation.IBusinessData;
import operation.Operation;
import operation.PathNode;
import operation.businessdata.TextBusinessData;

public class PathFinder {
	
	private static ArrayList<LinkedList<PathNode>> paths = new ArrayList<LinkedList<PathNode>>();
	private static ArrayList<LinkedList<PathNode>> pathInfo = new ArrayList<LinkedList<PathNode>>();
	private static Stack<LinkedList<PathNode>> runningPath =new Stack<LinkedList<PathNode>>();
	
	public static void createPaths(ArrayList<Operation> operations)
	{
		
		initPathInfo(operations);
		
		runningPath.addAll(pathInfo);
		
		while(!runningPath.isEmpty())
		{
			LinkedList<PathNode> path = runningPath.pop();
			paths.add(path);
			
			for(LinkedList<PathNode> info:pathInfo)
			{
				if(path.getLast().equals(info.getFirst()))
				{
					if(path.contains(info.getLast()))
					{
						continue;
					}
					
					LinkedList<PathNode> newPath = clonePath(path);
					newPath.addLast(info.get(1));
					newPath.addLast(info.get(2));
					runningPath.push(newPath);
					
				}
			}
		}
		
		
	}
	
	//纪录所有的单节点路径，作为原始素材，在后续路径计算中做基本添加单元使用
	public static void initPathInfo(ArrayList<Operation> operations)
	{
		for(Operation operation:operations)
		{
			for(IBusinessData from:operation.getRequirements())
			{	
				for(IBusinessData to:operation.getProducts())
				{
					LinkedList<PathNode> path = new LinkedList<PathNode>();
					path.add(from);
					path.add(operation);
					path.add(to);
					pathInfo.add(path);
				}
				
				
			}
		}
	}
	
	
	public static LinkedList<PathNode> clonePath(LinkedList<PathNode> path)
	{
		LinkedList<PathNode> newPath = new LinkedList<PathNode>();
		
		for(PathNode node:path)
		{
			newPath.addLast(node);
		}
		
		return newPath;
	}
	
	public static ArrayList<LinkedList<PathNode>> findPaths(ArrayList<TextBusinessData> from,IBusinessData to)
	{
		ArrayList<LinkedList<PathNode>> result = new ArrayList<LinkedList<PathNode>>();
		
		for(TextBusinessData data:from)
		{
			
		}
		
		return result;
	}
	
	
	
	public static ArrayList<LinkedList<PathNode>> getPaths()
	{
		return paths;
	}
	
	public static void printAllPaths(ArrayList<LinkedList<PathNode>> allPaths)
	{
		for(LinkedList<PathNode> path:allPaths)
		{
			printPath(path);
			System.out.println();
		}
	}
	
	public static void printPath(LinkedList<PathNode> path)
	{
		for(PathNode node:path)
		{
			if(node instanceof Operation)
			{
				System.out.print(" => ["+node+"]");
			}
			else
			{
				System.out.print(" => "+node);
			}
		}
	}
}


