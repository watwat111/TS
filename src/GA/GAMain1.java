package GA;

import java.util.ArrayList;
import java.util.List;

import GA.CrossOver.BasicCrossOver;
import GA.CrossOver.PartiallyMatchedCrossover;
import GA.calc_fitness.CalcFitnessAtLinerNormarize;
import GA.calc_fitness.CalcFitnessBase;
import GA.calc_fitness.ICalcFitness;
import TS.UseTime;
import createFile.CreateFile;
import jsp.Ans;
import jsp.JspMain;
import jsp_result.ga_result.ResultAllGeneration;

public class GAMain1 extends JspMain {
	private int maxGeneration = 100;
	private int nowGeneration;
	private ResultAllGeneration result;
	private double pm = 0.5;
	private double pc = 0.5;
	private int population = 100;
	private BasicCrossOver crossOver;
	private List<Ans> anss;
	private int worthMakeSpan;
	private int bestMakeSpan;
	private CalcFitnessBase calcFitness;
	public GAMain1() {
		// TODO Auto-generated constructor stub
		name = "job3.txt";
		cycleNumber = 10;
		//System.out.println(111);
		init();
		// System.out.println(MasterPath);

		for (nowCycleNumber = 0; nowCycleNumber < cycleNumber; nowCycleNumber++) {
			useTime = new UseTime();
			//init();
			useTime.setStartTime(System.nanoTime());
			GA();
			useTime.setEndTime(System.nanoTime());
			result.setTime(useTime.getTime());

			result.setBestMakeSpan(foundBestMakeSpan);
			outResult(result, nowCycleNumber + ".ga");
		}
	}
	
	public void GA(){
		setMakeSpanAllGenetic();
		calcFitness.calcFitness(anss);
		anss = crossOver.crossOver(anss, pc);
		//testShowAllMakeSpan();
	}
	
	private void testShowAllMakeSpan(){
		for(Ans ans: anss){
			//System.out.println(12222);
			ans.showMakeSpan();
		}
	}
	
	private void setMakeSpanAllGenetic(){
		worthMakeSpan = 0;
		for(Ans ans:anss){
			int makespan = setGnt(ans.getAns());
			if(makespan > worthMakeSpan){
				worthMakeSpan = makespan;
			}
			if(makespan < bestMakeSpan){
				bestMakeSpan = makespan;
			}
			ans.setMakeSpan(makespan);
		}
	}
	
	public void init(){
		saveFilePath = CreateFile.createFileByData(masterPath, "\\GA\\" + name + "\\" + getSaveFileName());
		anss = new ArrayList<Ans>(population);
		for(int i = 0; i < population; i++){
			anss.add(new Ans(data));
			//System.out.println(111);
		}
		
		//System.out.println(anss.size());
		result = new ResultAllGeneration(name, maxGeneration, pm, pc);
		crossOver = new PartiallyMatchedCrossover(ans);
		calcFitness = new CalcFitnessAtLinerNormarize();
	}
	
	
	private String getSaveFileName(){
		return maxGeneration + "_" + pc + "_" + pm;
	}

	public static void main(String[] args) {
		GAMain1 main = new GAMain1();

	}

}
