package GA.CrossOver;

import create_random_index.CreateRandomIndex;
import jsp.Ans;

public class PartiallyMatchedCrossover extends BasicCrossOver{
	
	public  PartiallyMatchedCrossover(Ans ans) {
		// TODO Auto-generated constructor stub
		super(ans);
	}
	

	@Override
	public Ans[] crossOver(Ans[] anss, double pc) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < anss.length; i++){
			if(Math.random() < pc){
				int targetIndex = CreateRandomIndex.getRandomIndex(anss.length);
				int index1 =  CreateRandomIndex.getRandomIndex(geneticLength);
				int index2 = getIndex2(index1, geneticLength);
				copyAns(anss[targetIndex].getAns());
				
				
			}
		}
		
		return anss;
	}
	
	private int[] PartiallyMatchedCrossover(int[] ans, int[] target,int index1,int index2){
		
	}
 	
	private int getIndex2(int index1,int geneticLength){
		return  index1 + CreateRandomIndex.getRandomIndex(geneticLength - index1);
	}
	
	
	
	

}
