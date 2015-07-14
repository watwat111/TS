package GA.calc_fitness;

import java.util.Comparator;

import jsp.Ans;

public class SortingAtMakeSpan implements Comparator<Ans>{

	@Override
	public int compare(Ans ans1, Ans ans2) {
		// TODO Auto-generated method stub
		double g = ans1.getMakeSpan() - ans2.getMakeSpan();
		if(g < 0){
			return 1;
		}
		else if(0 < g){
			return -1;
		}
		else 
			return 0;
		
	}

}
