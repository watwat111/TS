package GA.Survive;

import java.util.ArrayList;
import java.util.List;

import jsp.Ans;

public class SurviveRulette extends SurviveBase{

	@Override
	public List<Ans> survive(List<Ans> list) {
		// TODO Auto-generated method stub
		List<Ans> newList = new ArrayList<Ans>(list.size());
		double scost = 0;
		for(int i = 0; i < list.size(); i++){
			scost += list.get(list.size()-1).getFitness() - list.get(i).getFitness();
		}
		
		for(int i = 0; i < list.size(); i++){
			double tmp = Math.random();
			double w = 0;
			boolean isAdd = false;
			do{
				for(int j = 0; j < list.size(); j++){
					w += list.get(j).getFitness();
					if(tmp < w / scost ){
						newList.add(list.get(j).clone());
						isAdd  = true;
						break;
					}
				}
			}while(!isAdd);
		}
		
		return newList;
	}

}
