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
	
	//通过已知条件和目的地求可用路径
	public static ArrayList<LinkedList<PathNode>> findPaths(ArrayList<IBusinessData> from,IBusinessData to)
	{
		//所有可以确定获得的数据集合
		ArrayList<IBusinessData> reacherable = new ArrayList<IBusinessData>();
		reacherable.addAll(from);
		
		//将所有可能的路径放入运行堆栈中，等待处理
		Stack<LinkedList<PathNode>> runningPath =new Stack<LinkedList<PathNode>>();
		ArrayList<LinkedList<PathNode>> possible = getPossiblePath(from,to);
		runningPath.addAll(possible);
		System.out.println(" ---- all possible ---");
		printAllPaths(possible);
		
		ArrayList<LinkedList<PathNode>> result = new ArrayList<LinkedList<PathNode>>();
		
		boolean hasNewInfo = false;
		Stack<LinkedList<PathNode>> tempStack =new Stack<LinkedList<PathNode>>();
		
		while(!runningPath.isEmpty())
		{
			
			LinkedList<PathNode> path = runningPath.pop();
			
			
			System.out.println("Pasering:"+pathToString(path));
			
			for(int index=0;index<path.size();index++)
			{
				PathNode currentNode = path.get(index);
				if(currentNode instanceof IBusinessData)
				{
					if(reacherable.contains(currentNode))
					{
						continue;
					}
					reacherable.add((IBusinessData)currentNode);
					hasNewInfo=true;
					continue;
				}
				else if(currentNode instanceof Operation)
				{
					if(matchRequirement((Operation)currentNode,reacherable))
					{
						continue;
					}
					else
					{
						tempStack.push(path);
						System.out.println("puushback:"+pathToString(path));
						break;
					}
				}
			}
			
			if(!tempStack.contains(path))
			{
				result.add(path);
				System.out.println("add to result:"+pathToString(path));
			}
			
			if(runningPath.isEmpty())
			{
				if(hasNewInfo)
				{
					runningPath.addAll(tempStack);
					tempStack.removeAllElements();
					hasNewInfo = false;
					
					continue;
				}
				
				break;
			}
			
		}
		
		return result;
	}
	
	//根据目的地求所有可用路径
	public static ArrayList<LinkedList<PathNode>> findPaths(IBusinessData to)
	{
		ArrayList<LinkedList<PathNode>> result = new ArrayList<LinkedList<PathNode>>();
		
		for(LinkedList<PathNode> path : paths)
		{
			if(path.getLast().equals(to))
			{
				result.add(path);
			}
		}
		
		return result;
	}
	
	
	private static boolean matchRequirement(Operation operation,ArrayList<IBusinessData> reacherable)
	{
		for(IBusinessData data:operation.getRequirements())
		{
			if(!reacherable.contains(data))
			{
				return false;
			}
		}
		return true;
	}
	
	private static ArrayList<LinkedList<PathNode>> getPossiblePath(ArrayList<IBusinessData> from,IBusinessData to)
	{
		ArrayList<LinkedList<PathNode>> result = new ArrayList<LinkedList<PathNode>>();
		
		for(IBusinessData node:from)
		{
			for(LinkedList<PathNode> path : paths)
			{
				if(path.getFirst().equals(node) && path.getLast().equals(to))
				{
					result.add(path);
				}
			}
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
	
	public static String pathToString(LinkedList<PathNode> path)
	{
		StringBuilder str = new StringBuilder();
		
		for(PathNode node:path)
		{
			if(node instanceof Operation)
			{
				str.append(" => ["+node+"]");
			}
			else
			{
				str.append(" => "+node);
			}
		}
		
		return str.toString();
	}
}


