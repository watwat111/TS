package GA;

import TS.UseTime;
import jsp.JspMain;
import jsp_result.ga_result.ResultAllGeneration;

public class GAMain1 extends JspMain {
	private int maxGeneration = 100;
	private int nowGeneration;
	private ResultAllGeneration result;
	private double pm = 0.5;
	private double pc = 0.5;
	
	
	public GAMain1() {
		// TODO Auto-generated constructor stub
		name = "job3.txt";
		cycleNumber = 10;
		
		
		// System.out.println(MasterPath);

		for (nowCycleNumber = 0; nowCycleNumber < cycleNumber; nowCycleNumber++) {
			useTime = new UseTime();
			init();
			useTime.setStartTime(System.nanoTime());
			//ga
			useTime.setEndTime(System.nanoTime());
			result.setTime(useTime.getTime());

			result.setBestMakeSpan(foundBestMakeSpan);
			outResult(result, nowCycleNumber + ".ga");
		}
	}
	
	public void GA(){
		
	}
	
	public void init(){
		result = new ResultAllGeneration(name, maxGeneration, pm, pc);
	}

	public static void main(String[] args) {
		GAMain1 main = new GAMain1();

	}

}
