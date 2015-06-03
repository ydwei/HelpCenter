package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import operation.IBusinessData;
import operation.Operation;
import operation.PathNode;
import operation.PriorityEnum;
import operation.businessdata.TextBusinessData;
import operationpath.PathFinder;

public class TestGraph {

	public static void main(String[] args) {
		String dataNames[] = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
		String operationNames[]=new String[]{"1","2","3","4","5","6","7","8","9","10"};
		
		HashMap<String,IBusinessData> datas = new HashMap<String,IBusinessData>();
		HashMap<String,Operation> operations = new HashMap<String,Operation>();
		ArrayList<Operation> operationLists = new ArrayList<Operation>();
		
		for(String name:dataNames)
		{
			datas.put(name, new TextBusinessData(name));
		}
		
		for(String name:operationNames)
		{
			Operation operation = new Operation();
			operation.setName(name);
			operation.setPriority(PriorityEnum.REGULAR);
			operations.put(name,operation);
			operationLists.add(operation);
		}
		
		operations.get("1").getRequirements().add(datas.get("A"));
		operations.get("1").getProducts().add(datas.get("B"));
		operations.get("1").getProducts().add(datas.get("F"));
		
		operations.get("2").getRequirements().add(datas.get("B"));
		operations.get("2").getProducts().add(datas.get("O"));
		operations.get("2").getProducts().add(datas.get("C"));
		
		operations.get("3").getRequirements().add(datas.get("C"));
		operations.get("3").getProducts().add(datas.get("D"));
		
		operations.get("4").getRequirements().add(datas.get("E"));
		operations.get("4").getProducts().add(datas.get("G"));

		operations.get("5").getRequirements().add(datas.get("F"));
		operations.get("5").getRequirements().add(datas.get("G"));
		operations.get("5").getRequirements().add(datas.get("K"));
		operations.get("5").getProducts().add(datas.get("J"));
		operations.get("5").getProducts().add(datas.get("I"));

		operations.get("6").getRequirements().add(datas.get("O"));
		operations.get("6").getRequirements().add(datas.get("J"));
		operations.get("6").getProducts().add(datas.get("L"));
		
		operations.get("7").getRequirements().add(datas.get("I"));
		operations.get("7").getProducts().add(datas.get("H"));
		
		operations.get("8").getRequirements().add(datas.get("M"));
		operations.get("8").getProducts().add(datas.get("K"));
		
		operations.get("9").getRequirements().add(datas.get("L"));
		operations.get("9").getProducts().add(datas.get("M"));
		operations.get("9").getProducts().add(datas.get("N"));
		
		operations.get("10").getRequirements().add(datas.get("D"));
		operations.get("10").getProducts().add(datas.get("C"));

		PathFinder.createPaths(operationLists);		
		PathFinder.printAllPaths(PathFinder.getPaths());
		
		//按照条件查找路径 
		ArrayList<IBusinessData> from = new ArrayList<IBusinessData> ();
		from.add(datas.get("E"));
		from.add(datas.get("A"));
		from.add(datas.get("M"));
		IBusinessData to = datas.get("N");
				
		
		ArrayList<LinkedList<PathNode>> paths = PathFinder.findPaths(from, to);
		
		System.out.println(" --- find ---");
		PathFinder.printAllPaths(paths);
				
	}
	
}
