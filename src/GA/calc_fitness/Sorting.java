package GA.calc_fitness;

import java.util.Collections;
import java.util.List;

import jsp.Ans;

public class Sorting {
	private SortingAtMakeSpan sorting;
	
	public Sorting(){
		sorting = new SortingAtMakeSpan();
	}
	
	public void sorting(List<Ans> genetics){
		Collections.sort(genetics,sorting);
	}

}
