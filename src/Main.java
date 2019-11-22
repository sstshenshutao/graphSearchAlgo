import java.util.Scanner;

import core.Greedy;
import dataStructure.InputVertex;

public class Main {

	public static void main(String[] args) {

//		
//		//root: input the root coordinate, price = 0, flow = 0 
//		InputVertex root = new InputVertex(new Vertex(0, 0), 0, 0);
//		
//		
//		// Customers input: coordinate, price, flow
//		InputVertex A = new InputVertex(new Vertex(1, -1), 2, 1);
//		InputVertex B = new InputVertex(new Vertex(1, 1), -5, 1);
//		
//		Cost: priceCable, priceFlow
//		core.Greedy gre = new core.Greedy(root, 1, 0);
//		gre.getG().getAllVertices().forEach(x -> System.out.println(x));
//		gre.dynamicAdd(A);
//		gre.getG().getAllVertices().forEach(x -> System.out.println(x));
//		System.out.println("AAAAAAAAAAA");
//		gre.getG().printGraph();
//		System.out.println("----------");
//		gre.dynamicAdd(B);
//		gre.getG().printGraph();
//		System.out.println("die gesamte Profit ist: "+ gre.getG().getSumProfit())
		
		
				

		Scanner input = new Scanner(System.in);
		boolean rootFlag=false;
		Greedy gre = null;
		System.out.println("cost: please input the coordinate of root, priceCable and priceFlow");
		while (input.hasNextLine()) {
			String in = input.nextLine();
			
			if (!rootFlag) {
				String[] inpara = in.split(",");
				double x = Double.parseDouble(inpara[0].trim().substring(1));
				double y = Double.parseDouble(inpara[1].trim().substring(0, inpara[1].length()-1));
				double priceCable= Double.parseDouble(inpara[2].trim());
				double priceFlow= Double.parseDouble(inpara[3].trim());
				InputVertex root = new InputVertex(x,y,priceCable, priceFlow);
				gre= new Greedy(root, priceCable, priceFlow);
				rootFlag =true;
			}else {
				String[] inpara = in.split(",");
				double x = Double.parseDouble(inpara[0].trim().substring(1));
				double y = Double.parseDouble(inpara[1].trim().substring(0, inpara[1].length()-1));
				double expectPrice= Double.parseDouble(inpara[2].trim());
				double expectFlow= Double.parseDouble(inpara[3].trim());
				InputVertex newNode = new InputVertex(x,y,expectPrice, expectFlow);
				gre.dynamicAdd(newNode);
				gre.getG().printGraph();
				System.out.println("die gesamte Profit ist: "+ gre.getG().getSumProfit());
			}
			System.out.println("Customer: please input the coordinate of Customer, price and Flow");

		}
//		(0 ,0 ),1,0
//		
//		(1,1),2,1
//		(1,2),2,1
//		(1,-1),1,1
//		(1,-1),2,1
//		(1,3),6,1
//		(1,3),4,1
//		(1,0),3,1

	}
}
