package GA;

import java.util.ArrayList;
import java.util.List;

import jsp.Ans;
import jsp.Data;

public class Genetics {

	private List<Ans> genetics;
	
	public Genetics(int population,Ans ans,Data data){
		genetics = new ArrayList<Ans>(population + 1);
		for(int i = 0 ; i < population; i++){
			genetics.add(new Ans(data));
		}
		genetics.add(population, ans);
		
	}
	
	
}
