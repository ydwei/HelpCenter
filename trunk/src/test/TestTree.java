package test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import operation.Operation;
import operation.PriorityEnum;
import operation.businessdata.TextBusinessData;
import operation.businessdata.TextGuidance;
import operationpath.PathFinder;

public class TestTree {
	
	//创建三条业务数据
	private TextBusinessData outTradeNo = new TextBusinessData("outTradeNo");
	private TextBusinessData tradeNo = new TextBusinessData("tradeNo");
	private TextBusinessData price = new TextBusinessData("price");	

	private ArrayList<TextBusinessData> datas = new ArrayList<TextBusinessData>()
	{{
		add(outTradeNo);
		add(tradeNo);
		add(price);
	}};
	
	//创建三个操作
	@SuppressWarnings("serial")
	private ArrayList<Operation> operations = new ArrayList<Operation>()
	{{
		add(createGetTradeNo());
		add(createGetPriceWithTradeNo());
		add(createGetPriceWithBops());
	}};
						
	public static void main(String[] args) {
		
		TestTree otree = new TestTree();
		
		ArrayList<Operation> operations = otree.getOperations();
		
		for(Operation operation:operations)
		{
			System.out.println(operation+"\n===========================");
		}
		
		PathFinder.createPaths(operations);
		PathFinder.printAllPaths();
		
//		TestFrame frame = new TestFrame(otree);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
//		frame.setExtendedState( JFrame.MAXIMIZED_BOTH );
//		
//		frame.setVisible(true);  
	}
	
	public ArrayList<Operation> getOperations()
	{
		return operations;
	}
	
	public ArrayList<TextBusinessData> getBusinessDatas()
	{
		return datas;
	}
	
	public Operation createGetTradeNo()
	{
		Operation getTradeNo = new Operation();
		
		getTradeNo.setName("OutTradeNo2TradeNo");
		
		getTradeNo.getRequirements().add(outTradeNo);
		getTradeNo.getProducts().add(tradeNo);
		
		getTradeNo.setGuidance(new TextGuidance("grep outtradeno tradecore"));
		getTradeNo.setPriority(PriorityEnum.REGULAR);
		
		return getTradeNo;
	}
	
	public Operation createGetPriceWithTradeNo()
	{
		Operation getPrice = new Operation();
		
		getPrice.setName("trade2price");
		
		getPrice.getRequirements().add(tradeNo);
		getPrice.getProducts().add(price);
		
		getPrice.setGuidance(new TextGuidance("grep tradeno paycore"));
		getPrice.setPriority(PriorityEnum.REGULAR);
		
		return getPrice;
	}
	
	public Operation createGetPriceWithBops()
	{
		Operation getPrice = new Operation();
		
		getPrice.setName("outTradeNo2price");
		
		getPrice.getRequirements().add(outTradeNo);
		getPrice.getProducts().add(price);
		
		getPrice.setGuidance(new TextGuidance("http://bops.alipay.net/index.html"));
		getPrice.setPriority(PriorityEnum.REGULAR);
		return getPrice;
	}
}
