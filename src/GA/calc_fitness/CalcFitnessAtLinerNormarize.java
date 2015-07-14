package GA.calc_fitness;

import java.util.List;

import jsp.Ans;

public class CalcFitnessAtLinerNormarize extends CalcFitnessBase{

	@Override
	public List<Ans> calcFitness(List<Ans> list) {
		// TODO Auto-generated method stub
		sorting.sorting(list);
		
		int fitness = 1;
		int beforeMakeSpan = 0;
		
		for(int count  = 0; count < list.size(); count++){
			Ans ans = list.get(count);
			if(ans.getMakeSpan() == beforeMakeSpan){
				
			}
			else{
				fitness = count +1;
				beforeMakeSpan = ans.getMakeSpan();
			}
			ans.setFitness(fitness);
			System.out.println(ans.getFitness());
		}
		return list;
	}

}
