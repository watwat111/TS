package GA.CrossOver;

import java.util.List;

import jsp.Ans;

public abstract class BasicCrossOver implements ICrossOver{
	protected int tmp[] ;
	protected int geneticLength;
	
	public BasicCrossOver(Ans ans) {
		// TODO Auto-generated constructor stub
		geneticLength = ans.getAns().length;
		tmp = new int[geneticLength];
	}
	
	public abstract List<Ans> crossOver(List<Ans> anss,double pc);
	
	protected void copyAns(int[] ans){
		for(int i = 0; i < geneticLength; i++){
			tmp[i] = ans[i];
		}
	}

}
