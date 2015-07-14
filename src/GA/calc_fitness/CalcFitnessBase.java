package GA.calc_fitness;

import java.util.List;

import jsp.Ans;

public abstract class CalcFitnessBase implements ICalcFitness{
	public abstract List<Ans> calcFitness(List<Ans> list);
	protected Sorting sorting;
	
	public CalcFitnessBase() {
		// TODO Auto-generated constructor stub
		sorting = new Sorting();
	}
}
